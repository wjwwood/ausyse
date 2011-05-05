package net.classSample.GoogleMaps;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

//import com.MapTest.R;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;
import com.google.android.maps.MapView.LayoutParams;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import net.classSample.GoogleMaps.ServerController;
import net.classSample.GoogleMaps.MyItemizedOverlay;
import com.readystatesoftware.mapviewballoons.*;

public class MapsActivity extends MapActivity {
	/** Called when the activity is first created. */

	public MapView mapView; // this holds our map
	public MapController mc;
	public GeoPoint p, lastTouched; // in this example it holds our location
	//private TextView latituteField;
	//private TextView longitudeField;
	public  ArrayList<String> deviceList;
	private JSONArray devicejArray;
	private Integer UIMode=0;
	private Integer selectedDevice;
	private Button button01;
	private Button button02;
	private Button button03;
	LocationManager lm;
	LocationListener ll;
	MyLocation myLocation;
	ServerController server;
	public Device activeDevice;
	public  ArrayList<Device> deviceActualList;
	private Location currentLocation;
	private Criteria criteria;
	
	//map overlay code
	Drawable drawable;
	List<Overlay> listOfOverlays;
	MyItemizedOverlay itemizedOverlay;
	
	
	@SuppressWarnings("deprecation")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		Log.d("Debug", "Informative Log Message.");
		super.onCreate(savedInstanceState);
		this.selectedDevice=getIntent().getIntExtra("activeDevice", 0);
		//this.UIMode = 0;
		this.deviceList = new ArrayList<String>();
		this.deviceActualList = new ArrayList<Device>();
		this.devicejArray = new JSONArray();
		//this.lm = (LocationManager) getSystemService(LOCATION_SERVICE);
		//this.ll = new mylocationlistener();
		
		this.myLocation = new MyLocation();
		
		// Get a reference to the LocationManager.	  
		this.lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
		
		//lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 50, 500, ll);

	    // Define a set of criteria used to select a location provider.
	    criteria = new Criteria();
	    criteria.setAccuracy(Criteria.ACCURACY_FINE);
	    criteria.setAltitudeRequired(false);
	    criteria.setBearingRequired(false);
	    criteria.setCostAllowed(true);
	    criteria.setPowerRequirement(Criteria.POWER_LOW);
	    //String provider = this.lm.getBestProvider(criteria, true);

		//this.server = new ServerController ("192.168.1.100");
		//this.server = new ServerController ("131.204.218.121");
	    this.server = new ServerController ("192.168.2.30:8888");
	    //this.server = new ServerController ("192.168.2.104");
	   
		//setContentView(R.layout.main);
		setContentView(R.layout.main_orig);
		//latituteField = (TextView) findViewById(R.id.TextView02);
		//longitudeField = (TextView) findViewById(R.id.TextView04);

		mapView = (MapView) findViewById(R.id.mapView);
		mapView.setBuiltInZoomControls(true);
		LinearLayout zoomLayout = (LinearLayout) findViewById(R.id.zoom);
		View zoomView = mapView.getZoomControls();

		zoomLayout.addView(zoomView, new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		mapView.displayZoomControls(true);
		mapView.setSatellite(true);

		mc = mapView.getController();
		String coordinates[] = { "32.605987", "-85.487432" };
		double lat = Double.parseDouble(coordinates[0]);
		double lng = Double.parseDouble(coordinates[1]);
		/*
		 * Note that for this class the latitude and longitude of a location are
		 * represented in micro degrees. This means that they are stored as
		 * integer values. For a latitude value of 40.747778, you need to
		 * multiply it by 1e6 to obtain 40747778.
		 */
		p = new GeoPoint((int) (lat * 1E6), (int) (lng * 1E6));
		lastTouched = new GeoPoint((int) 0.00, (int) 0.00);

		mc.animateTo(p);
		mc.setZoom(17);
		// ---Update Device List---
		this.deviceList = server.getDeviceList();
		this.devicejArray = server.getDeviceListJ();
		// ---Put a marker down for each device (active)---

		// ---Add a location marker---
		//MapOverlay mapOverlay = new MapOverlay();
		this.listOfOverlays = mapView.getOverlays();
		//listOfOverlays.clear();
		
		// first overlay
		this.drawable = getResources().getDrawable(R.drawable.marker);
		this.itemizedOverlay = new MyItemizedOverlay(drawable, mapView);
		
		

		//Button button = (Button) findViewById(R.id.Button02);
		this.button02 = (Button) findViewById(R.id.Button02);
		this.button02.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				// Switch mode from NEUTRAL to WAYPOINT
				waypointModeToggle();
				String statusRep = "";
				if (UIMode == 0)
					statusRep = "Map Mode Activated";
				if (UIMode == 1)
					statusRep = "Waypoint Mode Activated";

				Toast.makeText(getBaseContext(), statusRep, Toast.LENGTH_SHORT)
						.show();
			}
		});
		
		this.button01 = (Button) findViewById(R.id.Button01);
		this.button01.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) { 
				showLocation(view);
			}
		});
		
		this.button03 = (Button) findViewById(R.id.Button03);
		this.button03.setVisibility(4);
		this.button03.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				//check if user already has an order on the queue
				//	call server.'check order existence' function
				//		if true, change that order
				//		if false, add a new order to the ordersdb
				boolean orderExists = server.checkOrderExistence(1);
				if(orderExists)
				{
					//change that order
					server.postOrderUpdate(selectedDevice, (Double) (lastTouched.getLatitudeE6()/1E6),
							(Double)(lastTouched.getLongitudeE6()/1E6), 1, 1);
				}
				else
				{
					//add new order to db
					server.postNewOrder(selectedDevice, (Double) (lastTouched.getLatitudeE6()/1E6),
							(Double)(lastTouched.getLongitudeE6()/1E6), 1, 1);
				}
			}
		});
		
		 TextView myLocationTextMap = (TextView)findViewById(R.id.myActiveDeviceTextMap);
		 myLocationTextMap.setText("Active Device ID: " + selectedDevice);
         
		initDeviceList();
		//mapView.setBuiltInZoomControls(true);
		mapView.invalidate();
	}
	public void initDeviceList()
	{
		ArrayList<GeoPoint> activeDevices = new ArrayList<GeoPoint>();
		//converts JSON Device Array into Device objects
		 try 
		  {
			  for (int i = 0; i < this.devicejArray.length(); i++) 
			  {
					JSONObject json_data = this.devicejArray.getJSONObject(i);
					double lat = json_data.getDouble("lat");
					double lng = json_data.getDouble("lng");
					double heading = json_data.getDouble("heading");
					Integer id = json_data.getInt("ID");
					Integer status = json_data.getInt("status_code");
					Integer type = json_data.getInt("type");
			        Device d = new Device(id, i, lat, lng, heading, status, type);
			        this.deviceActualList.add(d);
			  }
		  }catch (JSONException e) 
		  {
			  Log.e("log_tag", "Error parsing data " + e.toString());
		  }
		  
			try {
				for (int i = 0; i < devicejArray.length(); i++) {
					JSONObject json_data = devicejArray.getJSONObject(i);
					double lat = json_data.getDouble("lat");
					double lng = json_data.getDouble("lng");
					double heading = json_data.getDouble("heading");
					Integer id = json_data.getInt("ID");
					Integer status = json_data.getInt("status_code");
					Integer type = json_data.getInt("type");
					p = new GeoPoint((int) (lat * 1E6), (int) (lng * 1E6));
					Device d = new Device(id, i, lat, lng, heading, status, type);
			        deviceActualList.add(d);
					activeDevices.add(p);
					// get lat/lng from deviceList

				}
		  for (int j = 0; j < activeDevices.size(); j++) {
				//Point screenPt = new Point();
				//mapView.getProjection().toPixels(activeDevices.get(j),	screenPt);
				//Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.map_marker);
				String title = "Device ID: " + deviceActualList.get(j).id;
				String info = "Info" + "\n" 
				  + "Type:      " + deviceActualList.get(j).typeLabel + "\n"
				  + "Status:    " + deviceActualList.get(j).statusLabel + "\n"
				  + "Latitude:  " + deviceActualList.get(j).lat + "\n"
				  + "Longitude: " + deviceActualList.get(j).lng + "\n"
				  + "Heading:   " + deviceActualList.get(j).heading + "\n";
				OverlayItem overlayItem = new OverlayItem(activeDevices.get(j), title, info);
				itemizedOverlay.addOverlay(overlayItem);
				
				//canvas.drawBitmap(bmp, screenPt.x, screenPt.y - 19, null);
			}
			}catch (JSONException e) {
				Log.e("log_tag", "Error parsing data " + e.toString());
			}
			this.listOfOverlays.add(this.itemizedOverlay);
			MapOverlay orderOverlay = new MapOverlay();
			this.listOfOverlays.add(orderOverlay);
			final MapController mc = mapView.getController();
			mc.animateTo(activeDevices.get(0));
			mc.setZoom(16);
	}
	
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		MenuInflater inflater = getMenuInflater(); 
		inflater.inflate(R.layout.options_menu, menu); 
		return true; 
	}
	
	public boolean onOptionsItemSelected(MenuItem item) 
	{
		switch (item.getItemId()) { 
		case R.id.satellite:
			mapView.setSatellite(true); 
			mapView.setStreetView(false); 
			mapView.invalidate(); 
			return true; 
		case R.id.map:
			mapView.setSatellite(false); 
			mapView.setStreetView(true); 
			mapView.invalidate(); 
			return true; 
		} 
		return false; 
	}
	 private final LocationListener locationListener = new LocationListener() {
		    public void onLocationChanged(Location location) {
		      updateWithNewLocation(location);
		    }
		   
		    public void onProviderDisabled(String provider){
		      updateWithNewLocation(null);
		    }

		    public void onProviderEnabled(String provider) {}

		    public void onStatusChanged(String provider, int status, Bundle extras) {}
		  };
	
		  private void updateWithNewLocation(Location location) {
			  // Update your current location
			  currentLocation = location;

			  // Refresh the ArrayList of contacts

			  // Geocode your current location to find an address.
			  String latLongString = "";
			  String addressString = "No address found";
			  
		    if (location != null) {
		      double lat = location.getLatitude();
		      double lng = location.getLongitude();
		      latLongString = "Lat:" + lat + "\nLng:" + lng;

		      Geocoder gc = new Geocoder(this, Locale.getDefault());
		      try {
		        List<Address> addresses = gc.getFromLocation(lat, lng, 1);
		        StringBuilder sb = new StringBuilder();
		        if (addresses.size() > 0) {
		          Address address = addresses.get(0);
		  
		          sb.append(address.getLocality()).append("\n");
		          sb.append(address.getCountryName());
		        }
		        addressString = sb.toString();
		      } catch (IOException e) {}
		    } else {
		      latLongString = "No location found";
		    }

		    // Update the TextView to show your current address.
		    TextView myLocationText = (TextView)findViewById(R.id.myLocationTextMap);
		    myLocationText.setText("Your Current Position is:\n" + latLongString + "\n" + addressString);
			}
		  
		  @Override
		  public void onStart() {
		    super.onStart();
		    
		    // Find a Location Provider to use.
		    String provider = this.lm.getBestProvider(criteria, true);

		    // Update the GUI with the last known position.
		    Location location = this.lm.getLastKnownLocation(provider);
		    updateWithNewLocation(location);

		    // Register the LocationListener to listen for location changes
		    // using the provider found above.
		    this.lm.requestLocationUpdates(provider, 2000, 10, locationListener);
		  }
		  
	@Override
	protected void onResume() {
		/*
		 * onResume is is always called after onStart, even if the app hasn't been
		 * paused
		 *
		 * add location listener and request updates every 1000ms or 10m
		 */
		//lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 10f, this.ll);
		super.onResume();
	}

	@Override
	protected void onPause() {
		/* GPS, as it turns out, consumes battery like crazy */
		this.lm.removeUpdates(locationListener);
		
		super.onResume();
	}
	
	public void onStop()
	  {
		  this.lm.removeUpdates(locationListener);

		  super.onPause();
	  }

	
	public void showLocation(View view) {
		switch (view.getId()) {
		case R.id.Button01:
			//lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, ll);
			//LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
			Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
			
			if (location != null) {
				
				String latStr = Double.toString(location.getLatitude());
				String lngStr = Double.toString(location.getLongitude());
				Log.v(latStr, "showLocation: X");
				Log.v(lngStr, "showLocation: X");
				/*
				Double lat = (Double) (location.getLatitude());
				Double lng = (Double) (location.getLongitude());
				//latituteField.setText(String.valueOf(lat));
				//longitudeField.setText(String.valueOf(lng));
				 * */
			} else {
				//latituteField.setText("GPS not available");
				//longitudeField.setText("GPS not available");
			}
			break;
		}
	}

	

	public void waypointModeToggle() {
		if (this.UIMode == 0) // If in NETRAL MODE
		{
			this.UIMode = 1; // Change to WAYPOINT MODE
			this.button03.setVisibility(0);
		}
		else if (this.UIMode == 1) // if in WAYPOINT MODE
		{
			this.UIMode = 0; // Change to NETRAL MODE
			this.button03.setVisibility(4);
		}
	}

	public void selfDestruct(View view) {
		// Kabloey
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}

	

	class MapOverlay extends Overlay {
		// GeoPoint lastTouched; //position of your last touch
		@Override
		public boolean draw(Canvas canvas, MapView mapView, boolean shadow,
				long when) {
			super.draw(canvas, mapView, shadow);
			// ---translate the GeoPoint to screen pixels---
			Point screenPts = new Point();
			Point screenPts2 = new Point();
			mapView.getProjection().toPixels(p, screenPts);
			mapView.getProjection().toPixels(lastTouched, screenPts2);
			/*
			deviceActualList.clear();
			// using an array
			ArrayList<GeoPoint> activeDevices = new ArrayList<GeoPoint>();
			try {
				for (int i = 0; i < devicejArray.length(); i++) {
					JSONObject json_data = devicejArray.getJSONObject(i);
					double lat = json_data.getDouble("lat");
					double lng = json_data.getDouble("lng");
					double heading = json_data.getDouble("heading");
					Integer id = json_data.getInt("ID");
					Integer status = json_data.getInt("status_code");
					Integer type = json_data.getInt("type");
					p = new GeoPoint((int) (lat * 1E6), (int) (lng * 1E6));
					Device d = new Device(id, lat, lng, heading, status, type);
			        deviceActualList.add(d);
					activeDevices.add(p);
					// get lat/lng from deviceList

				}
				*/
				/*
				for (int j = 0; j < activeDevices.size(); j++) {
					//Point screenPt = new Point();
					//mapView.getProjection().toPixels(activeDevices.get(j),	screenPt);
					//Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.map_marker);
					String title = "Device ID: " + deviceActualList.get(j).id;
					String info = "Info" + "\n" 
					  + "Type:      " + deviceActualList.get(j).typeLabel + "\n"
					  + "Status:    " + deviceActualList.get(j).statusLabel + "\n"
					  + "Latitude:  " + deviceActualList.get(j).lat + "\n"
					  + "Longitude: " + deviceActualList.get(j).lng + "\n"
					  + "Heading:   " + deviceActualList.get(j).heading + "\n";
					OverlayItem overlayItem = new OverlayItem(activeDevices.get(j), title, info);
					itemizedOverlay.addOverlay(overlayItem);
					
					//canvas.drawBitmap(bmp, screenPt.x, screenPt.y - 19, null);
				}
				
			} catch (JSONException e) {
				Log.e("log_tag", "Error parsing data " + e.toString());
			}
			*/

			// ---add the personal location marker---
			Bitmap bmp = BitmapFactory.decodeResource(getResources(),
					R.drawable.bluetarget);
			canvas.drawBitmap(bmp, screenPts.x, screenPts.y - 19, null);
			Bitmap bmp2 = BitmapFactory.decodeResource(getResources(),
					R.drawable.redtarget);
			canvas.drawBitmap(bmp2, screenPts2.x, screenPts2.y - 19, null);
			// ---add locations of active devices

			return true;
		}

		public boolean onTouchEvent(MotionEvent event, MapView mapView) {
			if (UIMode == 1) {
				// ---when user lifts his finger---
				if (event.getAction() == 1) {
					GeoPoint p = mapView.getProjection().fromPixels(
							(int) event.getX(), (int) event.getY());
					lastTouched = p;
					Toast.makeText(
							getBaseContext(),
							p.getLatitudeE6() / 1E6 + "," + p.getLongitudeE6()
									/ 1E6, Toast.LENGTH_SHORT).show();
				}
			}
			return false;
		}
	}
	
}
