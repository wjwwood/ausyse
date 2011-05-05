package net.classSample.GoogleMaps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class ServerController {
	
	public  ArrayList<String> deviceList;
	private JSONArray devicejArray;
	public String ipAddress;
	
	public String KEY_121 = "http://192.168.2.30:8888/dump_device_table.php"; 
	public String KEY_122 = "http://192.168.2.30:8888/updateinfo.php"; 
	public String KEY_123 = "http://192.168.2.30:8888/checkinfo_orders.php";
	public String KEY_124 = "http://192.168.2.30:8888/add_order_record.php"; 
	public String KEY_125 = "http://192.168.2.30:8888/update_order_record.php"; 
	
	public ServerController(String ip)
	{
		this.ipAddress = ip;
		this.KEY_121 = "http://" + this.ipAddress + "/dump_device_table.php";
		this.KEY_122 = "http://" + this.ipAddress + "/updateinfo.php";
		this.KEY_123 = "http://" + this.ipAddress + "/checkinfo_orders.php";
		this.KEY_124 = "http://" + this.ipAddress + "/add_order_record.php";
		this.KEY_125 = "http://" + this.ipAddress + "/update_order_record.php";
	}
	
	public void ReInit(String ip)
	{
		this.ipAddress = ip;
		this.KEY_121 = "http://" + this.ipAddress + "/dump_device_table.php";
		this.KEY_122 = "http://" + this.ipAddress + "/updateinfo.php";  
		this.KEY_123 = "http://" + this.ipAddress + "/checkinfo_orders.php";
		this.KEY_124 = "http://" + this.ipAddress + "/add_order_record.php";
		this.KEY_125 = "http://" + this.ipAddress + "/update_order_record.php";
	}

	public ArrayList<String> getDeviceList() {
		InputStream is = null;

		String result = "";
		String returnString = "";
		ArrayList<String> list = new ArrayList<String>(); // this is what we return
		// the year data to send
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("type", "1")); // get all devices of type 1

		// http post
		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(KEY_121);
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			is = entity.getContent();

		} catch (Exception e) {
			Log.e("log_tag", "Error in http connection " + e.toString());
		}

		// convert response to string
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "iso-8859-1"), 8);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			is.close();
			result = sb.toString();
		} catch (Exception e) {
			Log.e("log_tag", "Error converting result " + e.toString());
		}
		// parse json data
		try {
			JSONArray jArray = new JSONArray(result);
			this.devicejArray = new JSONArray(result); // catch the data
			for (int i = 0; i < jArray.length(); i++) {
				JSONObject json_data = jArray.getJSONObject(i);
				Log.i("log_tag", "id: " + json_data.getInt("ID") + ", lat: "
						+ json_data.getDouble("lat") + ", lng: "
						+ json_data.getDouble("lng") + ", ststus code: "
						+ json_data.getDouble("status_code") + ", heading: "
						+ json_data.getDouble("heading"));
				// Get an output to the screen
				returnString += "\n\t" + jArray.getJSONObject(i);
			}

			// JSONArray jsonArray = (JSONArray)jsonObject;
			if (jArray != null) {
				for (int i = 0; i < jArray.length(); i++) {
					list.add(jArray.get(i).toString());
				}
			}
		} catch (JSONException e) {
			Log.e("log_tag", "Error parsing data " + e.toString());
		}
		// return returnString;
		return list;
	}

	public JSONArray getDeviceListJ() {
		InputStream is = null;

		String result = "";
		String returnString = "";
		ArrayList<String> list = new ArrayList<String>(); // this is what we return
		// the year data to send
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("type", "1")); // get all devices of type 1

		// http post
		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(KEY_121);
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			is = entity.getContent();

		} catch (Exception e) {
			Log.e("log_tag", "Error in http connection " + e.toString());
		}

		// convert response to string
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "iso-8859-1"), 8);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			is.close();
			result = sb.toString();
		} catch (Exception e) {
			Log.e("log_tag", "Error converting result " + e.toString());
		}
		// parse json data
		try 
		{
			JSONArray jArray = new JSONArray(result);
			this.devicejArray = new JSONArray(result); // catch the data
			for (int i = 0; i < jArray.length(); i++) {
				JSONObject json_data = jArray.getJSONObject(i);
				Log.i("log_tag", "id: " + json_data.getInt("ID") + ", lat: "
						+ json_data.getDouble("lat") + ", lng: "
						+ json_data.getDouble("lng") + ", ststus code: "
						+ json_data.getDouble("status_code") + ", heading: "
						+ json_data.getDouble("heading"));
				// Get an output to the screen
				returnString += "\n\t" + jArray.getJSONObject(i);
			}

			// JSONArray jsonArray = (JSONArray)jsonObject;
			if (jArray != null) {
				for (int i = 0; i < jArray.length(); i++) {
					list.add(jArray.get(i).toString());
				}
			}
		} catch (JSONException e) {
			Log.e("log_tag", "Error parsing data " + e.toString());
		}
		// return returnString;
		return this.devicejArray;
	}

	public void postDeviceUpdate(Integer id, double lat, double lng, Integer status, double heading) {
		// Create a new HttpClient and Post Header
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(KEY_122);

		try {
			// Add your data
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
			nameValuePairs.add(new BasicNameValuePair("id", "1"));
			nameValuePairs.add(new BasicNameValuePair("lat", "32.000"));
			nameValuePairs.add(new BasicNameValuePair("lng", "-85.000"));
			nameValuePairs.add(new BasicNameValuePair("status", "1"));
			nameValuePairs.add(new BasicNameValuePair("heading", "-45.00"));
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			// Execute HTTP Post Request
			HttpResponse response = httpclient.execute(httppost);

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}
	}
	
	public boolean checkOrderExistence(Integer ownerID)
	{
		InputStream is = null;
		String result = "";
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(KEY_123);
		
		try {
			// Add your data
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
			nameValuePairs.add(new BasicNameValuePair("owner_id", ownerID.toString()));
			
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			// Execute HTTP Post Request
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			//is = entity.getContent();
			
			// convert response to string
			try {
				//HttpEntity entity = response.getEntity();
				is = entity.getContent();
				BufferedReader reader = new BufferedReader(new InputStreamReader(
						is, "iso-8859-1"), 8);
				StringBuilder sb = new StringBuilder();
				String line =  reader.readLine();
				is.close();
				result = line;
			} catch (Exception e) {
				Log.e("log_tag", "Error converting result " + e.toString());
			}

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}
		boolean res = false;
		Integer val;
		val = Integer.parseInt(result);
		if(val == 0)
		{
			Log.d("ListViewOnClick", "This order DNE.............................");
			res = false;  
		}
		else if( val == 1)
		{
			Log.d("ListViewOnClick", "This order exists.........................."); 
			res = true;
		}
		return res;
	}
	
	public void postNewOrder(Integer deviceID, Double lat, Double lng, Integer ownerID, Integer
			priority)
	{
		// Create a new HttpClient and Post Header
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(KEY_124);

		try {
			// Add your data
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
			nameValuePairs.add(new BasicNameValuePair("deviceID", deviceID.toString()));
			nameValuePairs.add(new BasicNameValuePair("lat", lat.toString()));
			nameValuePairs.add(new BasicNameValuePair("lng", lng.toString()));
			nameValuePairs.add(new BasicNameValuePair("status", "-1"));
			nameValuePairs.add(new BasicNameValuePair("priority",priority.toString()));
			nameValuePairs.add(new BasicNameValuePair("ownerID",ownerID.toString()));
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			// Execute HTTP Post Request
			HttpResponse response = httpclient.execute(httppost);

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}
	}
	
	public void postOrderUpdate(Integer deviceID, Double lat, Double lng, Integer ownerID, Integer
			priority) {
		// Create a new HttpClient and Post Header
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(KEY_125);

		try {
			// Add your data
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
			nameValuePairs.add(new BasicNameValuePair("ID", "1"));
			nameValuePairs.add(new BasicNameValuePair("deviceID", deviceID.toString()));
			nameValuePairs.add(new BasicNameValuePair("lat", lat.toString()));
			nameValuePairs.add(new BasicNameValuePair("lng", lng.toString()));
			nameValuePairs.add(new BasicNameValuePair("status", "-1"));
			nameValuePairs.add(new BasicNameValuePair("priority",priority.toString()));
			nameValuePairs.add(new BasicNameValuePair("ownerID",ownerID.toString()));
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			// Execute HTTP Post Request
			HttpResponse response = httpclient.execute(httppost);

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}
	}
	
	

}
