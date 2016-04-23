package edu.brown.cs.finalproject.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class PublicFBEventsWriter {
	
	public PublicFBEventsWriter() {
	}
	
	public void updateDB(JsonObject jsonResults) throws SQLException {
		
		try (Connection conn = Database.getConnection()) {
			
			JsonArray eventsArray = new Gson().fromJson(jsonResults.get("events"), JsonArray.class);
			
			System.out.println(eventsArray);
			
			for (JsonElement eventElement : eventsArray) {
				JsonObject eventJSON = new Gson().fromJson(eventElement, JsonObject.class);
				
				System.out.println(eventJSON);
				System.out.println("venue id " + eventJSON.get("venueId").toString().replace("\"", ""));
				System.out.println("venue name " + eventJSON.get("venueName").toString().replace("\"", ""));
				String venueId = eventJSON.get("venueId").toString().replace("\"", "");
				String venueName = eventJSON.get("venueName").toString().replace("\"", "");
				
				JsonObject venueLocationJSON = new Gson().fromJson(eventJSON.get("venueLocation"), JsonObject.class);
				System.out.println("venue lat: " + venueLocationJSON.get("latitude").toString());
				System.out.println("venue long: " + venueLocationJSON.get("longitude").toString());
				String venueLat = venueLocationJSON.get("latitude").toString();
				String venueLong = venueLocationJSON.get("longitude").toString();
				
				try (PreparedStatement prep = conn
						.prepareStatement("INSERT OR IGNORE INTO venue (venueID, name, latitude, longitude) VALUES (?,?,?,?);")) {
					prep.setString(1, venueId);
					prep.setString(2, venueName);
					prep.setString(3, venueLat);
					prep.setString(4, venueLong);

					prep.execute();

				}
				

				
				
				
			}
		}
		
		
		
		
		
		
		
	}
	
	
	
	

}
