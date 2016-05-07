package edu.brown.cs.finalproject.search;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import edu.brown.cs.finalproject.database.PublicFBEventsWriter;

public class FacebookDataManager2 {

	public FacebookDataManager2() throws IOException {

		File file = new File(System.getProperty("user.dir")
				+ "/lib/node_modules/facebook-events-by-location");
		Runtime.getRuntime().exec("npm start", null, file);
		
    	try {
	    Thread.sleep(8000);                 //1000 milliseconds is one second.
	} catch(InterruptedException ex) {
	    Thread.currentThread().interrupt();
	}
		
		System.out.println("success");
		

	}
	
	  /**
	   * This method updates the CartoDB database with public
	   * events within a given radius from a given location
	   * @param latitude
	   * @param longitude
	   * @param radius,
	   *          in meters
	   * @param accessToken,
	   *          a general access token from Facebook
	   */
	public synchronized void requestPublicEvents(double latitude, double longitude,
		      int radius) {
		
		
		JsonObject publicEvents = null;
		try {
			publicEvents = queryEvents(latitude, longitude, radius);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("ERROR: Problem fetching facebook public events.");
		}
		
//		System.out.println(publicEvents);
	    PublicFBEventsWriter publicFBEventsWriter = new PublicFBEventsWriter();
	    try {
	      publicFBEventsWriter.updateDB(publicEvents);
	    } catch (SQLException | IOException e) {
	      e.printStackTrace();
	      System.out.println("Problem updating database with public venues.");
	    }
		
	}
	
	public static JsonObject queryEvents(double latitude, double longitude,
		      int radius) throws IOException {

		    StringBuilder urlBuilder = new StringBuilder();
		    urlBuilder.append("http://localhost:3000/events?lat=");
		    urlBuilder.append(latitude);
		    urlBuilder.append("&lng=");
		    urlBuilder.append(longitude);
		    urlBuilder.append("&distance=");
		    urlBuilder.append(radius);
		    urlBuilder
		        .append("&sort=time&access_token=220099498366885|8a0e23ef1bc9e94213c881e53b2d7343");

		    System.out.println(urlBuilder.toString());

		    URL url = new URL(urlBuilder.toString());

		    HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();

		    httpConn.setRequestMethod("GET");

		    BufferedReader in = null;
		    StringBuffer response = null;

		    in = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
		    String inputLine;
		    response = new StringBuffer();

		    while ((inputLine = in.readLine()) != null) {
		      response.append(inputLine);
		    }
		    in.close();

		    System.out.println("Done with fetching public Facebook events.");

		    JsonParser parser = new JsonParser();
		    JsonObject results = parser.parse(response.toString()).getAsJsonObject();

		    return results;
		  }

}
