package edu.brown.cs.finalproject.database;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
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
	
	public void updateDB(JsonObject jsonResults) throws SQLException, IOException {
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
				Date eventStarttime = null;
				try {
					eventStarttime = formatter.parse(eventStarttimeString);
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
				
				
				
//				StringBuilder urlBuilder = new StringBuilder();
//				urlBuilder.append("https://cs32finalproject.cartodb.com/api/v2/sql?q=INSERT%20INTO%20events%20(name)%20VALUES%20(\"jo\")&api_key=ad54038628d84dceb55a7adb81eddfcf9976e994");
				
//				StringBuilder urlBuilder = new StringBuilder();
//				urlBuilder.append("http://cs32finalproject.cartodb.com/api/v2/sql?q=");
//				urlBuilder.append("INSERT INTO events (eventid, name, venuename, origintype, creatorid, "
//						+ "startdate, enddate, category, description, public?, attendingCount, declinedCount, maybeCount, noReplyCount)"
//						+ " VALUES (");
//				urlBuilder.append(eventId);	// eventid
//				urlBuilder.append(",");
//				urlBuilder.append(eventName);	// name
//				urlBuilder.append(",");
//				urlBuilder.append(venueName);	// venuename
//				urlBuilder.append(",");
//				urlBuilder.append("facebook");	// origintype
//				urlBuilder.append(",");
//				urlBuilder.append("null");	// creatorid
//				urlBuilder.append(",");
//				urlBuilder.append(eventStarttime.toString());	// startdate
//				urlBuilder.append(",");
//				urlBuilder.append("null");	// enddate
//				urlBuilder.append(",");
//				urlBuilder.append("null");	// category
//				urlBuilder.append(",");
//				urlBuilder.append(eventDescription);	//description
//				urlBuilder.append(",");
//				urlBuilder.append(true);	// public?
//				urlBuilder.append(",");
//				urlBuilder.append(eventAttendingCount);	// attendingCount
//				urlBuilder.append(",");
//				urlBuilder.append(eventDeclinedCount);	// declinedCount
//				urlBuilder.append(",");
//				urlBuilder.append(eventMaybeCount);	// maybeCount
//				urlBuilder.append(",");
//				urlBuilder.append(eventNoReplyCount);	// noReplyCount
//				urlBuilder.append(")&api_key=ad54038628d84dceb55a7adb81eddfcf9976e994");
				
				StringBuilder urlBuilder = new StringBuilder();
				urlBuilder.append("https://cs32finalproject.cartodb.com/api/v2/sql?q=WITH%20n(");
				urlBuilder.append("eventid,name,attendingCount)");
				
//				"https://cs32finalproject.cartodb.com/api/v2/sql?q=WITH%20n(eventid,name,attendingCount)%20AS%20(%20VALUES%20(%27123%27,%27a%27,12),%20(%27456%27,%27b%27,18),%20(%27789%27,%27c%27,4)%20),%20upsert%20AS%20(%20UPDATE%20events%20o%20SET%20name=n.name,%20attendingCount=n.attendingCount%20FROM%20n%20WHERE%20o.eventid%20=%20n.eventid%20RETURNING%20o.eventid%20)%20INSERT%20INTO%20events%20(eventid,name,attendingCount)%20SELECT%20n.eventid,%20n.name,%20n.attendingCount%20FROM%20n%20WHERE%20n.eventid%20NOT%20IN%20(%20SELECT%20eventid%20FROM%20upsert%20);&api_key=ad54038628d84dceb55a7adb81eddfcf9976e994";
				
				
				System.out.println(urlBuilder.toString());
				
				URL url = new URL(urlBuilder.toString().replace(" ", "%20"));

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
				
				System.out.println("Done with updating events table on CartoDB.");	
				
				
				
				
				
			}

		
	}
	
}
