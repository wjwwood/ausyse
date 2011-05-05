package net.classSample.GoogleMaps;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class mylocationlistener implements LocationListener {
    @Override
    public void onLocationChanged(Location location) {
        if (location != null) {
        Log.d("LOCATION CHANGED", location.getLatitude() + "");
        Log.d("LOCATION CHANGED", location.getLongitude() + "");
        /*
        Toast.makeText(this,
            location.getLatitude() + "" + location.getLongitude(),
            Toast.LENGTH_LONG).show();*/
        }
    }
    @Override
    public void onProviderDisabled(String provider) {
    }
    @Override
    public void onProviderEnabled(String provider) {
    }
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }
    }