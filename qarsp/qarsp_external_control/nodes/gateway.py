#!/usr/bin/python

import roslib
roslib.load_manifest("qarsp_external_control")
import rospy
import actionlib
import tf

import threading

from move_base_msgs.msg import MoveBaseAction, MoveBaseGoal

import xmlrpclib

from SimpleXMLRPCServer import SimpleXMLRPCServer
from SimpleXMLRPCServer import SimpleXMLRPCRequestHandler

class RequestHandler(SimpleXMLRPCRequestHandler):
    rpc_paths = ('/RPC2',)

client = None
client_thread = None

def waitForResult():
    global client
    client.wait_for_result()
    s = xmlrpclib.ServerProxy('http://192.168.2.30:8002')
    s.missionComplete()

def dispatch(x, y):
    """Dispatches the device"""
    global client, client_thread
    if client == None:
        print "Client is null, not dispatching"
        return False
    print "Dispatching to", x, y
    destination = MoveBaseGoal()
    destination.target_pose.header.frame_id = "odom"
    destination.target_pose.header.stamp = rospy.Time.now()
    
    destination.target_pose.pose.position.x = x
    destination.target_pose.pose.position.y = y
    quat = tf.transformations.quaternion_from_euler(0, 0, 0)
    destination.target_pose.pose.orientation.z = quat[2]
    destination.target_pose.pose.orientation.w = quat[3]
    
    client.send_goal(destination)
    
    client_thread = threading.Thread(target=waitForResult)
    client_thread.start()
    
    return True

def main():
    global client
    rospy.init_node("qarsp_gateway")
    server = SimpleXMLRPCServer(("", 8003),
                                requestHandler=RequestHandler)
    
    server.register_function(dispatch)
    
    s = xmlrpclib.ServerProxy('http://192.168.2.30:8002')
    
    s.register("192.168.2.10",8003)
    
    client = actionlib.SimpleActionClient('move_base', MoveBaseAction)
    client.wait_for_server()
    
    server.serve_forever()
    

###  If Main  ###
if __name__ == '__main__':
    main()


