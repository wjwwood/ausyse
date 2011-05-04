import pymysql
import time

import threading

import select
import sys
import pybonjour

from utm import *

import xmlrpclib
from SimpleXMLRPCServer import SimpleXMLRPCServer
from SimpleXMLRPCServer import SimpleXMLRPCRequestHandler

class Device():
    def __init__(self, device):
        self.id = device[0]
        self.type = device[1]
        self.lat = device[2]
        self.long = device[3]
        self.heading = device[4]
        self.status_code = device[5]
    
    def __eq__(self, other): 
        # if not isinstance(other, Device) or type(other): raise NotImplementedError
        return self.id==other
    

class Order():
    def __init__(self, order):
        self.id = order[0]
        self.lat = order[1]
        self.long = order[2]
        self.status = order[3]
        self.priority = order[4]
        self.owner_id = order[5]
    
    def __eq__(self, other): 
        # if not isinstance(other, Order): raise NotImplementedError
        return self.id==other
    

class RequestHandler(SimpleXMLRPCRequestHandler):
    rpc_paths = ('/RPC2',)

class QarspDispatcher():
    """This class monitors the database, connects to devices, and dispatches orders to devices"""
    def __init__(self):
        self.dbconn = None
        self.cursor = None
        self.bonjour_thread = None
        self.xmlrpc_thread = None
        self.xmlrpc_server = None
        self.device_list = []
        self.device_addrs = []
        self.orders_list = []
        self.dispatched_orders = []
        self.connectToDatabase()
    
    def connectToDatabase(self):
        """Connects to the database"""
        self.dbconn = pymysql.connect(host='127.0.0.1', port=8889, user='qarsp', passwd='syse2011', db='qarsp_db')
        self.cursor = self.dbconn.cursor()
    
    def setupXMLRPC(self):
        """Starts the XMLRPC Server for communicating with the QARSP's"""
        self.xmlrpc_server = SimpleXMLRPCServer(("", 8002), requestHandler=RequestHandler)
        
        self.xmlrpc_server.register_function(self.register_qarsp, 'register')
        self.xmlrpc_server.register_function(self.missionComplete_qarsp, 'missionComplete')
        
        self.xmlrpc_thread = threading.Thread(target=self.xmlrpc_server.serve_forever)
        self.xmlrpc_thread.start()
    
    def dnsregister_callback(self, sdRef, flags, errorCode, name, regtype, domain):
        if errorCode == pybonjour.kDNSServiceErr_NoError:
            print 'Registered service:'
            print '  name    =', name
            print '  regtype =', regtype
            print '  domain  =', domain
    
    def setupBonjour(self):
        """Registers the DNS service"""
        name = "QarspService"
        regtype = "_qarsp._tcp"
        port = 1234
        
        self.sdRef = \
            pybonjour.DNSServiceRegister(name = name, regtype = regtype, port = port, callBack = self.dnsregister_callback)
        self.bonjour_thread = threading.Thread(target=self.processBonjour)
        self.bonjour_thread.start()
    
    def shutdown(self):
        """Close open sockets"""
        self.running = False
        if(self.xmlrpc_server != None):
            self.xmlrpc_server.shutdown()
        if(self.cursor != None):
            self.cursor.close()
        if(self.dbconn != None):
            self.dbconn.close()
        if(self.bonjour_thread != None):
            self.bonjour_thread.join(1)
        if(self.xmlrpc_thread != None):
            self.xmlrpc_thread.join(1)
        self.sdRef.close()
    
    def processBonjour(self):
        """Waits for devices to connect"""
        while self.running:
            ready = select.select([self.sdRef], [], [], 1)
            if len(ready) != 0 and self.sdRef in ready[0]:
                pybonjour.DNSServiceProcessResult(self.sdRef)
    
    def monitorDatabase(self):
        """Periodically checks the database for updated orders and device positions"""
        self.running = True
        self.setupXMLRPC()
        self.setupBonjour()
        while self.running:
            # Grab the latest device and orders data
            self.cursor.execute("SELECT * FROM device_loc")
            self.device_list = []
            for device in self.cursor:
                device = Device(device)
                self.device_list.append(device)
            self.cursor.execute("SELECT * FROM orders_db")
            self.orders_list = []
            for order in self.cursor:
                order = Order(order)
                self.orders_list.append(order)
            
            # Look for devices with assignments and non-zero locations
            for device in self.device_list:
                if device.lat != 0 and device.long != 0 and device.status_code > 0:
                    try:
                        order = self.orders_list[self.orders_list.index(device.status_code)]
                    except ValueError as error:
                        pass
                    try:
                        self.dispatched_orders.index(order.id)
                    except ValueError as error:
                        if order.lat != 0 and order.long != 0:
                            # Dispatch the order
                            self.dispatchOrder(order, device)
                            self.dispatched_orders.append(order)
            time.sleep(1)
    
    def register_qarsp(self, addr, port):
        """Allows a qarsp to register itself"""
        self.device_addrs.append((addr, port))
        query = "INSERT INTO device_loc (type, lat, lng, heading, status_code) VALUES (1, 32.606216, -85.486671, 0, -1)"
        self.cursor.execute(query)
        print "Device added with address: %s:%d" % (addr,port)
        return True
    
    def missionComplete_qarsp(self, qarsp_id):
        """Allows a qarsp to notify the dispatcher of a mission completion"""
        return True
    
    def dispatchOrder(self, order, device):
        """Dispatches an order to a given device"""
        try:
            s = xmlrpclib.ServerProxy('http://%s:%d' % self.device_addrs[self.device_list.index(device)])
            # Convert Lat to Long
            order_x, order_y = latlong2utm(order.lat, order.long)
            device_x, device_y = latlong2utm(device.lat, device.long)
            # s.dispatch(order_x-device_x, order_y-device_y)
            s.dispatch(0, 5)
            print "Dispatching device", device.id, "to", order.lat, order.long, "from", device.lat, device.long
        except Exception as error:
            print "Error dispatching qarsp:", str(error)
    

if __name__ == '__main__':
    qd = None
    try:
        qd = QarspDispatcher()
        qd.monitorDatabase()
    except Exception as error:
        print str(error)
    finally:
        if qd != None:
            qd.shutdown()
