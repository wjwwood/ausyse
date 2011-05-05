package net.classSample.GoogleMaps;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import net.classSample.GoogleMaps.MapsActivity;

import net.classSample.GoogleMaps.R;

//import com.MapTest.FriendLocationLookup;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.view.View.OnCreateContextMenuListener;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;


public class MainActivity extends Activity  {
	
	private HashMap<String, Location> friendLocations;
	private ArrayList<String> friendDistanceList;
	private Location currentLocation;
	private ArrayAdapter<String> aa;
	private LocationManager locationManager;
	private Criteria criteria;
	
	public  ArrayList<String> deviceLabelList;
	public  ArrayList<String> deviceList;
	public  ArrayList<Device> deviceActualList;
	private JSONArray devicejArray;
	public  ListView lv;
	public Integer selectedDeviceID;
	public Device activeDevice;
	ServerController server;
	private Button buttonIP;
	private EditText myText;
	//Video Stream Stuff
	Canvas c;
	Boolean isCamWindowOpen;
	Button buttonCamWindow;
	TextView mResultText;
	private Preview mPreview;
	SocketCamera mCamera;
	int cameraCurrentlyLocked = 0;
	int defaultCameraId = 1;
	private SurfaceHolder holder;
	SurfaceView camSurface;
	
	protected static final int CONTEXTMENU_SETACTIVEITEM = 0;
	protected static final int CONTEXTMENU_VIEWDEVSTREAM = 1;
	
	static final private int MENU_ITEM_MAP = Menu.FIRST;
	static final private int MENU_ITEM_REFRESH = Menu.FIRST + 1;
	
	/**
	   * Refresh the hash of contact names / physical locations.
	   */
	public void refreshFriendLocations() 
	{
		//friendLocations = FriendLocationLookup.GetFriendLocations(getApplicationContext());
	}
	
	@Override
	public void onCreate(Bundle icicle) {
	  super.onCreate(icicle);
	  setContentView(R.layout.main);
	  mPreview = new Preview(this);
	  this.activeDevice = null;
	  this.deviceActualList = new ArrayList<Device>();
	  this.deviceList = new ArrayList<String>();
	  this.deviceLabelList = new ArrayList<String>();
	  this.devicejArray = new JSONArray();
	  this.selectedDeviceID = -1;
	  //this.server = new ServerController ("192.168.1.100");
	  this.server = new ServerController ("192.168.2.30:8888");
	  //this.server = new ServerController ("192.168.2.30");
	  // ---Update Device List---
	  deviceList = server.getDeviceList();
	  //refreshListView();
	  
	  // Bind the ListView to an ArrayList of strings.
	  friendDistanceList = new ArrayList<String>();
	  
	  this.lv = (ListView)findViewById(R.id.myListView);
	  //aa = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, friendDistanceList);
	  aa = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, deviceLabelList);
	  lv.setAdapter(aa);
	  
	  
    // Get a reference to the LocationManager.	  
    locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);

    // Define a set of criteria used to select a location provider.
    criteria = new Criteria();
    criteria.setAccuracy(Criteria.ACCURACY_FINE);
    criteria.setAltitudeRequired(false);
    criteria.setBearingRequired(false);
    criteria.setCostAllowed(true);
    criteria.setPowerRequirement(Criteria.POWER_LOW);
    
    // Refresh the hash of contact locations.
    refreshFriendLocations();
    
    //
    LayoutInflater inflater = (LayoutInflater) MainActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	// inflate our view from the corresponding XML file
	View layout = inflater.inflate(R.layout.popup, (ViewGroup)findViewById(R.id.popup_menu_root));
	//this.mPreview = (Preview) layout.findViewById(R.id.popup_menu_button1)
	  /*
	  camSurface = (SurfaceView) layout.findViewById(R.id.camsurface);
	  int h = camSurface.getHeight();
	  int w = camSurface.getWidth();
	  */
    //
    
    //Setup menu buttons
    this.myText = (EditText) findViewById(R.id.serverIPTextView);
    this.myText.setInputType(InputType.TYPE_NULL);
    this.myText.setOnTouchListener(new View.OnTouchListener() {
        public boolean onTouch(View v, MotionEvent event) {
        myText.setInputType(InputType.TYPE_CLASS_TEXT);
        myText.onTouchEvent(event); // call native handler
        return true; // consume touch even
        }

    });
    
    
    this.buttonIP = (Button) findViewById(R.id.ButtonIP);
    this.buttonIP.setOnClickListener(new View.OnClickListener() {
		public void onClick(View view) { 
			String ip = myText.getText().toString();
			server = new ServerController(ip);
		}
	});
    
    //Camera stuff
    this.isCamWindowOpen = false;
    //this.buttonCamWindow = (Button) findViewById(R.id.ButtonCamWindow);
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
    if (location != null)
      refreshListView();

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
    TextView myLocationText = (TextView)findViewById(R.id.myLocationText);
    myLocationText.setText("Your Current Position is:\n" + latLongString + "\n" + addressString);
	}

	/**
	 * Update the ArrayList that's bound to the ListView
	 * to show the current distance of each contact to
	 * your current physical location.
	 */
  private void refreshListView() 
  {
	  String provider = locationManager.getBestProvider(criteria, true);

	  friendDistanceList.clear();
	  this.deviceList = server.getDeviceList();
	  this.devicejArray = server.getDeviceListJ();
	  this.deviceLabelList.clear();
	  this.deviceActualList.clear();
	  if(deviceList.size() > 0)
	  {
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
					Location location = locationManager.getLastKnownLocation(provider);
					String str = "Device "+id + " (No Location)";
					if(location!=null){
					location.setLatitude(lat);
					location.setLongitude(lng);					
					int distance = (int)currentLocation.distanceTo(location);					
			        str = "Device "+id + " (" + String.valueOf(distance) + "m)";
					}
			        this.deviceLabelList.add(str);
			        Device d = new Device(id, i, lat, lng, heading, status, type);
			        this.deviceActualList.add(d);
			  }
		  }catch (JSONException e) 
		  {
			  Log.e("log_tag", "Error parsing data " + e.toString());
		  }
		  
	  }
    aa.notifyDataSetChanged();
    initListView();
  }
  
  @Override
  public void onStart() {
    super.onStart();
    
    // Find a Location Provider to use.
    String provider = locationManager.getBestProvider(criteria, true);

    // Update the GUI with the last known position.
    Location location = locationManager.getLastKnownLocation(provider);
    updateWithNewLocation(location);

    // Register the LocationListener to listen for location changes
    // using the provider found above.
    locationManager.requestLocationUpdates(provider, 2000, 10, locationListener);
  }
  
  @Override 
  public void onStop() {
    // Unregister the LocationListener to stop updating the
    // GUI when the Activity isn't visible.
    locationManager.removeUpdates(locationListener);

    super.onStop();
  }
  
  public void onPause()
  {
	  locationManager.removeUpdates(locationListener);
	  if (mCamera != null) {
          mPreview.setCamera(null);
          try {
			mCamera.wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
          mCamera = null;
      }

	  super.onPause();
  }
  /*
  public void onResume()
  {
	 // mCamera = SocketCamera.open();
      //cameraCurrentlyLocked = defaultCameraId;
      //mPreview.setCamera(mCamera);
  } */
    
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    super.onCreateOptionsMenu(menu);
    menu.add(0, MENU_ITEM_MAP, Menu.NONE, R.string.menu_item_map);
    menu.add(0, MENU_ITEM_REFRESH, Menu.NONE, R.string.menu_item_refresh);
    return true;
  }
  
  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    super.onOptionsItemSelected(item);

    switch (item.getItemId()) {
      // Check for each known menu item
      case (MENU_ITEM_MAP):
        // Start the Map Activity
    	Intent theIntent = new Intent(this, MapsActivity.class); 
      	theIntent.putExtra("activeDevice", this.selectedDeviceID);
    	startActivity(theIntent);
        //startActivity(new Intent(this, MapsActivity.class));
        return true;
      case (MENU_ITEM_REFRESH) :
        // Refresh the Friend Location hash
        refreshFriendLocations();
        refreshListView();
        return true;
    }
    
    // Return false if you have not handled the menu item.
    return false;
  }
  
  private void initListView() {
      /* Loads the items to the ListView. */
      //refreshFavListItems();

	  this.lv.setOnCreateContextMenuListener(new OnCreateContextMenuListener() {
		  
          public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {

              menu.setHeaderTitle("ContextMenu");
              
              menu.add(0, CONTEXTMENU_SETACTIVEITEM, 1, "Set Active Device");
              menu.add(0, CONTEXTMENU_VIEWDEVSTREAM, 1, "View Data Stream");

         }
     });
	  
	  this.lv.setOnItemClickListener(new AdapterView.OnItemClickListener() 
	  {
		  @Override
		  public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) 
		  {
			  Object o = lv.getItemAtPosition(position);
			  Log.v("ListViewOnClick", "This is clicked..........................");
			  //selectedDeviceID = position;
			  Builder abd = new AlertDialog.Builder(MainActivity.this);
			  abd.setMessage(
					  "Device Info" + "\n" 
					  + "ID:        " + deviceActualList.get(position).id + "\n"
					  + "Type:      " + deviceActualList.get(position).typeLabel + "\n"
					  + "Status:    " + deviceActualList.get(position).statusLabel + "\n"
					  + "Latitude:  " + deviceActualList.get(position).lat + "\n"
					  + "Longitude: " + deviceActualList.get(position).lng + "\n"
					  + "Heading:   " + deviceActualList.get(position).heading + "\n");
			  abd.show();
		  }
	  });
	  
	  this.lv.setOnItemLongClickListener( new AdapterView.OnItemLongClickListener()
	  { 
		  @Override
		  public boolean onItemLongClick(AdapterView<?> av, View v, int pos, long id) 
		  {
			  //onLongListItemClick(v,pos,id);
			  selectedDeviceID = deviceActualList.get(pos).id;
			 
			  return false;
		  }
	  }); 

	  
	  /*this.lv.setOnItemClickListener(new OnItemClickListener() {

	        public void onItemClick(AdapterView<?> a, View v, int position, long id) {
	            // TODO Auto-generated method stub
	            //System.out.println("Item is clicked");
	            Log.v("ListViewOnClick", "This is clicked..........................");
	            //selectedDeviceID = position;
	            
	            Builder abd = new AlertDialog.Builder(MainActivity.this);

	            abd.setMessage("" + lv.getItemAtPosition(position)+"/n");

	            abd.show();
	        }	
	    });
	    */

 }
  
// ===========================================================
// Popup Window Stuff
// ===========================================================
  
  private PopupWindow pw;
  private void initiatePopupWindow() {
	// get the instance of the LayoutInflater
	  LayoutInflater inflater = (LayoutInflater) MainActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	  // inflate our view from the corresponding XML file
	  View layout = inflater.inflate(R.layout.popup, (ViewGroup)findViewById(R.id.popup_menu_root));
	  // create a 100px width and 200px height popup window
	  pw = new PopupWindow(layout, 360, 360, true);
	  
	  WebView webView = (WebView)  layout.findViewById(R.id.camWeb);
	  webView.getSettings().setJavaScriptEnabled(true);
	  webView.loadUrl("http://192.168.2.30:8888/map.html");

	  webView.setWebViewClient(new MyWebViewClient());
	  
	  // set actions to buttons we have in our popup
	  //this.mPreview = (Preview) layout.findViewById(R.id.popup_menu_button1)
	  
	  /*
	  camSurface = (SurfaceView) layout.findViewById(R.id.camsurface);
	  this.holder = camSurface.getHolder();
	  //holder = camSurface.getHolder();
	  
	  this.mCamera = SocketCamera.open();
	  mPreview.setCamera(mCamera);
	  //mPreview.switchCamera(mCamera);
	  try 
	  {
		  mCamera.setPreviewDisplay(holder);
		  
	  } catch (IOException e) 
	  {
		// TODO Auto-generated catch block
		e.printStackTrace();
	  }
	  mCamera.startPreview();
	  */
	  
      //setContentView(mPreview);
	  Button button1 = (Button)layout.findViewById(R.id.popup_menu_button1);
	  button1.setOnClickListener(new OnClickListener() {
	      @Override
	      public void onClick(View vv) {
	          // close the popup
	    	  /*
	    	  mCamera.stopPreview();
	    	  */
	          pw.dismiss();
	      }
	  });
	  /*
	  Button button2 = (Button)layout.findViewById(R.id.popup_menu_button2);
	  button2.setOnClickListener(new OnClickListener() {
	      @Override
	      public void onClick(View vv) {
	          Toast.makeText(MainActivity.this, "Hello", Toast.LENGTH_LONG).show();
	      }
	  });
	  Button button3 = (Button)layout.findViewById(R.id.popup_menu_button3);
	  button3.setOnClickListener(new OnClickListener() {
	      @Override
	      public void onClick(View vv) {
	          finish();
	      }
	  });
	  // finally show the popup in the center of the window
	  
      
      */
	  pw.showAtLocation(layout, Gravity.CENTER, 0, 0);
  }
   
  private OnClickListener cancel_button_click_listener = new OnClickListener() {
      public void onClick(View v) {
          pw.dismiss();
      }
  };


// ===========================================================
// Methods from SuperClass/Interfaces
// ===========================================================

  public boolean onContextItemSelected(MenuItem aItem) {
	  
      AdapterContextMenuInfo info = (AdapterContextMenuInfo) aItem.getMenuInfo();
      Device favContexted;
     
      Integer itemId;
      switch (aItem.getItemId()) {
          case CONTEXTMENU_SETACTIVEITEM:
        	  //Get the actual record in the jArray of the item corresponding to aItem.getItemId()
        	  itemId = selectedDeviceID;
        	  itemId = 0;
              favContexted = (Device) this.deviceActualList.get(itemId);
              this.activeDevice = favContexted;
              //the TextView to show your current address.
              TextView myLocationText = (TextView)findViewById(R.id.myActiveDeviceText);
              myLocationText.setText("Active Device ID: " + favContexted.id);
              //this.lv.findViewById(itemId).setBackgroundColor(0xfff00000);
              //fakeFavs.remove(favContexted);
              //refreshFavListItems();
              return true;    
          case CONTEXTMENU_VIEWDEVSTREAM:
        	  itemId = selectedDeviceID;
        	  itemId = 0;
              favContexted = (Device) this.deviceActualList.get(itemId);
              initiatePopupWindow();
              return true;
      }
      return false;

  }
  
  //CAMERA POPUP STUFF
  
  
  
}
