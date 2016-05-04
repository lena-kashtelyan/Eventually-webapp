package edu.brown.cs.finalproject.database;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.commons.lang3.time.DateUtils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/*
 * This class updates the CartoDB events table with new events
 */
public class PublicFBEventsWriter {

	public PublicFBEventsWriter() {
	}

	public void updateDB(JsonObject jsonResults) throws SQLException,
			IOException {
		JsonArray eventsArray = new Gson().fromJson(jsonResults.get("events"),
				JsonArray.class);

		System.out.println(eventsArray);

		for (JsonElement eventElement : eventsArray) {
			JsonObject eventJSON = new Gson().fromJson(eventElement,
					JsonObject.class);

			System.out.println(eventJSON);
			System.out.println("venue id: "
					+ eventJSON.get("venueId").toString().replace("\"", ""));
			System.out.println("venue name: "
					+ eventJSON.get("venueName").toString().replace("\"", ""));
//			String venueId = eventJSON.get("venueId").toString()
//					.replace("\"", "");
			String venueName = eventJSON.get("venueName").toString()
					.replaceAll("[^a-zA-Z ]", "");

			JsonObject venueLocationJSON = new Gson().fromJson(
					eventJSON.get("venueLocation"), JsonObject.class);
			System.out.println("venue lat: "
					+ venueLocationJSON.get("latitude").toString());
			System.out.println("venue long: "
					+ venueLocationJSON.get("longitude").toString());
			String venueLat = venueLocationJSON.get("latitude").toString();
			String venueLong = venueLocationJSON.get("longitude").toString();

//			String venueCoverPicture = eventJSON.get("venueCoverPicture")
//					.toString().replace("\"", "");
//			String venueProficePicture = eventJSON.get("venueProfilePicture")
//					.toString().replace("\"", "");
			String eventId = eventJSON.get("eventId").toString()
					.replace("\"", "'");
			String eventName = eventJSON.get("eventName").toString()
					.replaceAll("[^a-zA-Z ]", "");
//			String eventCoverPicture = eventJSON.get("eventCoverPicture")
//					.toString().replace("\"", "");
			String eventProfilePicture = URLEncoder.encode(eventJSON.get("eventProfilePicture").toString().replace("\"", "'"));
//					.replace("\"", "'").replace("/","a").replace("&","a").replace("?","a")
//					.replace("=", "a").replace(".","a").replace(":","a").replace("-","a").replace("_", "a").replaceAll("[^a-zA-Z ]", "");
			String eventDescription = eventJSON.get("eventDescription")
					.toString().replaceAll("[^a-zA-Z ]", "");//.replace("\"", "'");
			if (eventDescription.length() > 4500) {
				eventDescription = eventDescription.substring(0, 4500);
			}
//			eventDescription = URLEncoder.encode(eventDescription);
					
					
					
					//

			// Obtaining a java.util.Date from a date&time string.
			String eventStarttimeString = eventJSON.get("eventStarttime")
					.toString().replace("\"", "");
			SimpleDateFormat formatter = new SimpleDateFormat(
					"yyyy-MM-dd'T'HH:mm:ssZ");
			Date eventStarttime = null;
			try {
				eventStarttime = formatter.parse(eventStarttimeString);
				System.out.println(eventStarttime);
			} catch (ParseException e) {
				e.printStackTrace();
				System.out
						.println("ERROR: Unable to convert a facebook date string into a java.util.Date.");
			}
			// Calculating the end time of an event by adding four hours to the starting time
			Date eventEndtime = DateUtils.addHours(eventStarttime, 4);
			String eventEndtimeString = eventEndtime.toString();
			eventEndtimeString = eventEndtimeString.substring(4).replaceAll("E[D,S]T", "");
			eventStarttimeString = eventStarttimeString.replace("T", " ")
					.substring(0, eventStarttimeString.length() - 5);

//			String eventDistance = eventJSON.get("eventDistance").toString()
//					.replace("\"", "");
//			String eventTimeFromNow = eventJSON.get("eventTimeFromNow")
//					.toString();

			JsonObject eventStats = new Gson().fromJson(
					eventJSON.get("eventStats"), JsonObject.class);
			int eventAttendingCount = Integer.parseInt(eventStats.get(
					"attendingCount").toString());
			int eventDeclinedCount = Integer.parseInt(eventStats.get(
					"declinedCount").toString());
			int eventMaybeCount = Integer.parseInt(eventStats.get("maybeCount")
					.toString());
			int eventNoReplyCount = Integer.parseInt(eventStats.get(
					"noreplyCount").toString());

			// System.out.println("venueCoverPicture: " + venueCoverPicture);
			// System.out.println("venueProficePicture: " +
			// venueProficePicture);
			// System.out.println("eventId: " + eventId);
			// System.out.println("eventName: " + eventName);
			 System.out.println("eventProfilePicture: " +
			 eventProfilePicture);
			// System.out.println("eventDescription: " + eventDescription);
			// System.out.println("eventCoverPicture: " + eventCoverPicture);
			// System.out.println("eventDistance: " + eventDistance);
			// System.out.println("eventTimeFromNow: " + eventTimeFromNow);
			// System.out.println("eventAttendingCount: " +
			// eventAttendingCount);
			// System.out.println("eventDeclinedCount: " + eventDeclinedCount);
			// System.out.println("eventMaybeCount: " + eventMaybeCount);
			// System.out.println("eventNoReplyCount: " + eventNoReplyCount);
			
			System.out.println("eventId: " + eventId);
			System.out.println("eventName: " + "'" + eventName + "'");
			System.out.println("venueName: " + "'" + venueName + "'");
			System.out.println("venueLat: " + venueLat);
			System.out.println("venueLong: " + venueLong);
			System.out.println("eventStarttimeString: " + "'" + eventStarttimeString + "'");
			System.out.println("eventEndtimeString: " + "'" + eventEndtimeString + "'");
			System.out.println("eventDescription: " + "'" + eventDescription + "'");
			System.out.println("eventAttendingCount: " + eventAttendingCount);
			System.out.println("eventDeclinedCount: " + eventDeclinedCount);
			System.out.println("eventMaybeCount: " + eventMaybeCount);
			System.out.println("eventNoReplyCount: " + eventNoReplyCount);

			StringBuilder urlBuilder = new StringBuilder();
			urlBuilder
					.append("https://cs32finalproject.cartodb.com/api/v2/sql?q=WITH%20n(");
			urlBuilder
					.append("eventid,name,venuename,latitude,longitude,origintype,creatorid,startdate,enddate,category,public,description,attendingcount,declinedcount,maybecount,noreplycount,eventphoto)");
			urlBuilder.append("%20AS%20(%20VALUES");
			urlBuilder.append(" (");
			urlBuilder.append(eventId);
			urlBuilder.append(",");
			urlBuilder.append("'" + eventName + "'");
			urlBuilder.append(",");
			urlBuilder.append("'" + venueName + "'");
			urlBuilder.append(",");
			urlBuilder.append(venueLat);
			urlBuilder.append(",");
			urlBuilder.append(venueLong);
			urlBuilder.append(",");
			urlBuilder.append("'facebook'");
			urlBuilder.append(",");
			urlBuilder.append("null");
			urlBuilder.append(",");
			urlBuilder.append("'" + eventStarttimeString + "'");
			urlBuilder.append(",");
			urlBuilder.append("'" + eventEndtimeString + "'");
			urlBuilder.append(",");
			urlBuilder.append("'performance'");
			urlBuilder.append(",");
			urlBuilder.append(true);
			urlBuilder.append(",");
			urlBuilder.append("'" + eventDescription + "'");
			urlBuilder.append(",");
			urlBuilder.append(eventAttendingCount);
			urlBuilder.append(",");
			urlBuilder.append(eventDeclinedCount);
			urlBuilder.append(",");
			urlBuilder.append(eventMaybeCount);
			urlBuilder.append(",");
			urlBuilder.append(eventNoReplyCount);
			urlBuilder.append(",");
			urlBuilder.append(eventProfilePicture);
			urlBuilder.append(") ), ");
			urlBuilder
					.append("upsert AS ( UPDATE events o SET name=n.name, venuename=n.venuename, latitude=n.latitude, longitude=n.longitude, origintype=n.origintype, creatorid=n.creatorid, startdate=to_timestamp(n.startdate,'YYYY-MM-dd HH24:MI:SS'), enddate=to_timestamp(n.enddate,'Mon DD HH24:MI:SS  YYYY'), category=n.category, public=n.public, description=n.description, attendingcount=n.attendingcount, declinedcount=n.declinedcount, maybecount=n.maybecount, noreplycount=n.noreplycount, eventphoto=n.eventphoto ");
			urlBuilder
					.append("FROM n WHERE o.eventid = n.eventid RETURNING o.eventid ) ");
			urlBuilder
					.append("INSERT INTO events (eventid,name,venuename,latitude,longitude,origintype,creatorid,startdate,enddate,category,public,description,attendingcount,declinedcount,maybecount,noreplycount,eventphoto) SELECT n.eventid, n.name, n.venuename, n.latitude, n.longitude, n.origintype, n.creatorid, to_timestamp(n.startdate,'YYYY-MM-dd HH24:MI:SS'), to_timestamp(n.enddate,'Mon DD HH24:MI:SS  YYYY'), n.category, n.public, n.description, n.attendingcount, n.declinedcount, n.maybecount, n.noreplycount, n.eventphoto FROM n ");
			urlBuilder
					.append("WHERE n.eventid NOT IN ( SELECT eventid FROM upsert );");
			urlBuilder
					.append("&api_key=ad54038628d84dceb55a7adb81eddfcf9976e994");

			System.out.println(urlBuilder.toString());

			URL url = new URL(urlBuilder.toString().replace(" ", "%20")
					.replace("'", "%27"));

			HttpURLConnection httpConn = (HttpURLConnection) url
					.openConnection();

			httpConn.setRequestMethod("GET");

			BufferedReader in = null;
			StringBuffer response = null;

			in = new BufferedReader(new InputStreamReader(
					httpConn.getInputStream()));
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
