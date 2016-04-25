package edu.brown.cs.finalproject.search;

import java.io.IOException;
import java.sql.SQLException;
import com.google.gson.JsonObject;
import edu.brown.cs.finalproject.database.PublicFBEventsWriter;

public class FacebookDataManager {
	
	public FacebookDataManager() {
		
	}
	
	/**
	 * This method updates the CartoDB database with public events within a given radius from a given location
	 * @param latitude
	 * @param longitude
	 * @param radius, in meters
	 * @param accessToken, a general access token from Facebook
	 */
	public void requestPublicEvents(double latitude, double longitude, int radius, String accessToken) {
		
		try {
			new PublicFBEventsFinder();
		} catch (Exception e1) {
			e1.printStackTrace();
			System.out.println("ERROR: Problem with running the public events application.");
		}
		
		JsonObject publicEvents = null;
		try {
			publicEvents = PublicFBEventsFinder.requestEvents(latitude, longitude, radius, accessToken);
		} catch (IOException e1) {
			e1.printStackTrace();
			System.out.println("ERROR: Fetching public Facebook events.");
		}
		
		System.out.println(publicEvents);
    	PublicFBEventsWriter publicFBEventsWriter = new PublicFBEventsWriter();
    	try {
			publicFBEventsWriter.updateDB(publicEvents);
		} catch (SQLException | IOException e) {
			e.printStackTrace();
			System.out.println("Problem updating database with public venues.");
		}
		
		
	}

}
