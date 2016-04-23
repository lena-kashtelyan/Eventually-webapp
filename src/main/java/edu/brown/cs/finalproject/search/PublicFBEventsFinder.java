package edu.brown.cs.finalproject.search;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.UnknownServiceException;
import java.sql.Time;
import java.util.Date;
import java.util.Iterator;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import edu.brown.cs.finalproject.entities.Event;
import edu.brown.cs.finalproject.entities.EventBean;
import edu.brown.cs.finalproject.entities.User;

public class PublicFBEventsFinder {
	
	public PublicFBEventsFinder() throws Exception {
		File file = new File(System.getProperty("user.dir")
				+ "/lib/node_modules/facebook-events-by-location");
		Runtime.getRuntime().exec("npm start", null, file);

	}
	
	public static JsonObject requestEvents(double latitude, double longitude, int radius, String accessToken) throws IOException {
		
		JsonObject publicEvents = new JsonObject();
    	try {
    		System.out.println("starting to request events");
    		publicEvents = queryEvents(latitude, longitude, radius, accessToken);
		} catch (IOException e) {
			try {
				try {
					Thread.sleep(1500);
				} catch (InterruptedException e1) {
					System.out.println("ERROR: Waiting thread has been interrupted.");
				}
				
				publicEvents = queryEvents(latitude, longitude, radius, accessToken);
				}
				catch (IOException e2) {
					try {
						Thread.sleep(3500);
					} catch (InterruptedException e4) {
						System.out.println("ERROR: Waiting thread has been interrupted.");
					}
					try {
						publicEvents = queryEvents(latitude, longitude, radius, accessToken);
					}
					catch (IOException e3) {
						e3.printStackTrace();
						System.out.println("ERROR: Fetching public events from Facebook.");
					}
				}
			}
    	
    	Runtime.getRuntime().exec("pkill npm");
    	Runtime.getRuntime().exec("pkill node");
    	
    	return publicEvents;		
	}
	
	public static JsonObject queryEvents(double latitude, double longitude, int radius, String accessToken) throws IOException {

		StringBuilder urlBuilder = new StringBuilder();
		urlBuilder.append("http://localhost:3000/events?lat=");
		urlBuilder.append(latitude);
		urlBuilder.append("&lng=");
		urlBuilder.append(longitude);
		urlBuilder.append("&distance=");
		urlBuilder.append(radius);
		urlBuilder.append("&sort=time&access_token=");
		urlBuilder.append(accessToken);
		
		System.out.println(urlBuilder.toString());
		
		URL url = new URL(urlBuilder.toString());

		HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();

		httpConn.setRequestMethod("GET");
		
		BufferedReader in = null;
		StringBuffer response = null;
		

		in = new BufferedReader(
				new InputStreamReader(httpConn.getInputStream()));
		String inputLine;
		response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
//		System.out.println(response.toString());
		System.out.println("Done");	
		
		JsonParser parser = new JsonParser();
		JsonObject results = parser.parse(response.toString()).getAsJsonObject();
		
		return results;
	}

}
