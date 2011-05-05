package net.classSample.GoogleMaps;

public class Device {
	
	public Integer id;
	public Integer listViewID;
	public double lat;
	public double lng;
	public Integer status;
	public String statusLabel;
	public double heading;
	public boolean active;
	public Integer type;
	public String typeLabel;
	

    protected Device(Integer id, Integer listViewID, double lat, double lng, double heading, Integer status, Integer type) {

        this.id = id;
        this.listViewID = listViewID;
        this.lat = lat;
        this.lng = lng;
        this.heading = heading;
        this.status = status;
        if(status == 0)
        	this.statusLabel = "Inactive - Waiting";
        else if(status == 1)
        	this.statusLabel = "Active - Stationary";
        else if (status == 5)
        	this.statusLabel = "Active - Mobile";
        else if (status == 10)
        	this.statusLabel = "ERROR";
        this.type = type;
        this.active = false;
        if(type == 1)
        	this.typeLabel = "RMB";
        else if (type == 2)
        	this.typeLabel = "ARD";

    }



    public String toString() {

        return this.id + " (" + this.typeLabel + ", " + this.lat + ", " + this.lng + ", "+ this.heading 
        + ", "+ this.status + ", " + ")";

    }



    public boolean equals(Object o) {

        return o instanceof Device && ((Device) o).id.compareTo(id) == 0;

    }
    

}

