package edu.brown.cs.finalproject.database;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/*
 * This class updates the local venue database table with new venues
 */
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
				System.out.println("venue id: " + eventJSON.get("venueId").toString().replace("\"", ""));
				System.out.println("venue name: " + eventJSON.get("venueName").toString().replace("\"", ""));
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
				
				String venueCoverPicture = eventJSON.get("venueCoverPicture").toString().replace("\"", "");
				String venueProficePicture = eventJSON.get("venueProfilePicture").toString().replace("\"", "");
				String eventId = eventJSON.get("eventId").toString().replace("\"", "");
				String eventName = eventJSON.get("eventName").toString().replace("\"", "");
				String eventCoverPicture = eventJSON.get("eventCoverPicture").toString().replace("\"", "");
				String eventProfilePicture = eventJSON.get("eventProfilePicture").toString().replace("\"", "");
				String eventDescription = eventJSON.get("eventDescription").toString().replace("\"", "");
				
				// Obtaining a java.util.Date from a date&time string.
				String eventStarttimeString = eventJSON.get("eventStarttime").toString().replace("\"", "");
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
				try {
					Date eventStarttime = formatter.parse(eventStarttimeString);
					System.out.println(eventStarttime);
				} catch (ParseException e) {
					e.printStackTrace();
					System.out.println("ERROR: Unable to convert a facebook date string into a java.util.Date.");
				}
				
				
				String eventDistance = eventJSON.get("eventDistance").toString().replace("\"", "");
				String eventTimeFromNow = eventJSON.get("eventTimeFromNow").toString();
				
				JsonObject eventStats = new Gson().fromJson(eventJSON.get("eventStats"), JsonObject.class);
				int eventAttendingCount = Integer.parseInt(eventStats.get("attendingCount").toString());
				int eventDeclinedCount = Integer.parseInt(eventStats.get("declinedCount").toString());
				int eventMaybeCount = Integer.parseInt(eventStats.get("maybeCount").toString());
				int eventNoReplyCount = Integer.parseInt(eventStats.get("noreplyCount").toString());
				
				System.out.println("venueCoverPicture: " + venueCoverPicture);
				System.out.println("venueProficePicture: " + venueProficePicture);
				System.out.println("eventId: " + eventId);
				System.out.println("eventName: " + eventName);
				System.out.println("eventProfilePicture: " + eventProfilePicture);
				System.out.println("eventDescription: " + eventDescription);
				System.out.println("eventCoverPicture: " + eventCoverPicture);
				System.out.println("eventDistance: " + eventDistance);
				System.out.println("eventTimeFromNow: " + eventTimeFromNow);
				System.out.println("eventAttendingCount: " + eventAttendingCount);
				System.out.println("eventDeclinedCount: " + eventDeclinedCount);
				System.out.println("eventMaybeCount: " + eventMaybeCount);
				System.out.println("eventNoReplyCount: " + eventNoReplyCount);
				
				
			}
		}

		
	}
	
}
