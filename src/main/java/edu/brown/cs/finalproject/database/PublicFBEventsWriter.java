package edu.brown.cs.finalproject.database;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;

import com.cartodb.CartoDBClientIF;
import com.cartodb.CartoDBException;
import com.cartodb.impl.ApiKeyCartoDBClient;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import edu.brown.cs.finalproject.categorization.EventCategorizer;

/*
 * This class updates the CartoDB events table with new
 * events
 */
public class PublicFBEventsWriter {

  public PublicFBEventsWriter() {
  }

  @SuppressWarnings("deprecation")
  public void updateDB(JsonObject jsonResults)
      throws SQLException, IOException {
    JsonArray eventsArray = new Gson().fromJson(jsonResults.get("events"),
        JsonArray.class);

    int eventsArraySize = eventsArray.size();

    if (eventsArraySize > 40) {
      System.out.println("started building lists");

      int firstStart = 0;
      int firstEnd = eventsArraySize / 4;
      int secondStart = firstEnd + 1;
      int secondEnd = eventsArraySize / 2;
      int thirdStart = secondEnd + 1;
      int thirdEnd = 3 * eventsArraySize / 4;
      int fourthStart = thirdEnd + 1;
      int fourthEnd = eventsArraySize - 1;

      JsonArray firstArray = new JsonArray();
      for (int i = firstStart; i <= firstEnd; i++) {
        firstArray.add(eventsArray.get(i));
      }

      JsonArray secondArray = new JsonArray();
      for (int i = secondStart; i <= secondEnd; i++) {
        secondArray.add(eventsArray.get(i));
      }

      JsonArray thirdArray = new JsonArray();
      for (int i = thirdStart; i <= thirdEnd; i++) {
        thirdArray.add(eventsArray.get(i));
      }

      JsonArray fourthArray = new JsonArray();
      for (int i = fourthStart; i <= fourthEnd; i++) {
        fourthArray.add(eventsArray.get(i));
      }

      PublicFbEventsWriterThread thread1 = new PublicFbEventsWriterThread(
          firstArray);
      PublicFbEventsWriterThread thread2 = new PublicFbEventsWriterThread(
          secondArray);
      PublicFbEventsWriterThread thread3 = new PublicFbEventsWriterThread(
          thirdArray);
      PublicFbEventsWriterThread thread4 = new PublicFbEventsWriterThread(
          fourthArray);

      thread1.start();
      thread2.start();
      thread3.start();
      thread4.start();

      try {
        thread1.join();
        thread2.join();
        thread3.join();
        thread4.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
        System.out.println("ERROR: Problem when waiting for a thread to die.");
      }
    } else {
      for (JsonElement eventElement : eventsArray) {
        processEvent(eventElement);
      }
    }
  }

  @SuppressWarnings("deprecation")
  static void processEvent(JsonElement eventElement) {

    JsonObject eventJSON = new Gson().fromJson(eventElement, JsonObject.class);

    String venueName = eventJSON.get("venueName").toString().replace("\"", "");
    venueName = URLEncoder.encode(venueName);

    JsonObject venueLocationJSON = new Gson()
        .fromJson(eventJSON.get("venueLocation"), JsonObject.class);
    String venueLat = venueLocationJSON.get("latitude").toString();
    String venueLong = venueLocationJSON.get("longitude").toString();

    StringBuilder geomPointBuilder = new StringBuilder();
    geomPointBuilder.append("ST_SetSRID(ST_Point(");
    geomPointBuilder.append(venueLong);
    geomPointBuilder.append(",");
    geomPointBuilder.append(venueLat);
    geomPointBuilder.append("),4326)");
    String geomPoint = geomPointBuilder.toString();

    String eventId = eventJSON.get("eventId").toString().replace("\"", "'");
    String eventName = eventJSON.get("eventName").toString().replace("\"", "");
    eventName = URLEncoder.encode(eventName);

    String eventProfilePicture = null;
    if (!eventJSON.get("eventCoverPicture").toString().equals("null")) {
      eventProfilePicture = URLEncoder.encode(
          eventJSON.get("eventCoverPicture").toString().replace("\"", ""));
    } else if (!eventJSON.get("eventProfilePicture").equals("null")) {
      eventProfilePicture = URLEncoder.encode(
          eventJSON.get("eventProfilePicture").toString().replace("\"", ""));
    } else {
      eventProfilePicture = URLEncoder.encode(
          "https://s-media-cache-ak0.pinimg.com/564x/f3/69/b4/f369b42357a27eb40068f675f62366ce.jpg");
    }

    String eventDescription = eventJSON.get("eventDescription").toString()
        .replace("\\", "*")
        .replaceAll("[^a-zA-Z0-9,.;-_><|:'!?$*()/\\[\\]\\{\\}\\@\\#\\^\\& ]",
            "")
        .replace("*", "\\");

    if (eventDescription.length() > 4500) {
      eventDescription = eventDescription.substring(0, 4500);
      eventDescription += "...";
    }
    eventDescription = URLEncoder.encode(eventDescription);

    EventCategorizer eventCategorizer = new EventCategorizer();
    String eventCategory = eventCategorizer.categorize(eventName,
        eventDescription);

    // Obtaining a java.util.Date from a date&time string.
    String eventStarttimeString = eventJSON.get("eventStarttime").toString()
        .replace("\"", "");
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
    Date eventStarttime = null;
    try {
      eventStarttime = formatter.parse(eventStarttimeString);
    } catch (ParseException e) {
      e.printStackTrace();
      System.out.println(
          "ERROR: Unable to convert a facebook date string into a java.util.Date.");
    }
    // Calculating the end time of an event by adding four
    // hours to the starting time
    Date eventEndtime = DateUtils.addHours(eventStarttime, 4);
    String eventEndtimeString = eventEndtime.toString();
    eventEndtimeString = eventEndtimeString.substring(4).replaceAll("E[D,S]T",
        "");
    eventStarttimeString = eventStarttimeString.replace("T", " ").substring(0,
        eventStarttimeString.length() - 5);

    JsonObject eventStats = new Gson().fromJson(eventJSON.get("eventStats"),
        JsonObject.class);
    int eventAttendingCount = Integer
        .parseInt(eventStats.get("attendingCount").toString());
    int eventDeclinedCount = Integer
        .parseInt(eventStats.get("declinedCount").toString());
    int eventMaybeCount = Integer
        .parseInt(eventStats.get("maybeCount").toString());
    int eventNoReplyCount = Integer
        .parseInt(eventStats.get("noreplyCount").toString());

    String query = String.format(
        "WITH n(eventid,name,venuename,the_geom,origintype,creatorid,startdate,enddate,category,public,description,attendingcount,declinedcount,maybecount,noreplycount,eventphoto)"
            + " AS (VALUES (%s,decode_url_part('%s'),decode_url_part('%s'),%s,'facebook',null,'%s','%s','%s',true,decode_url_part('%s'),%s,%s,%s,%s,decode_url_part('%s'))),upsert AS (UPDATE events o SET name=n.name,venuename=n.venuename,the_geom=n.the_geom,origintype=n.origintype,creatorid=n.creatorid,startdate=to_timestamp(n.startdate,'YYYY-MM-dd HH24:MI:SS'),enddate=to_timestamp(n.enddate,'Mon DD HH24:MI:SS  YYYY'),category=n.category,public=n.public,description=n.description,attendingcount=n.attendingcount,declinedcount=n.declinedcount,maybecount=n.maybecount,noreplycount=n.noreplycount,eventphoto=n.eventphoto "
            + "FROM n WHERE o.eventid=n.eventid RETURNING o.eventid) INSERT INTO events (eventid,name,venuename,the_geom,origintype,creatorid,startdate,enddate,category,public,description,attendingcount,declinedcount,maybecount,noreplycount,eventphoto) SELECT n.eventid,n.name,n.venuename,n.the_geom,n.origintype,n.creatorid,to_timestamp(n.startdate,'YYYY-MM-dd HH24:MI:SS'),to_timestamp(n.enddate,'Mon DD HH24:MI:SS  YYYY'),n.category,n.public,n.description,n.attendingcount,n.declinedcount,n.maybecount,n.noreplycount,n.eventphoto FROM n "
            + "WHERE n.eventid NOT IN (SELECT eventid FROM upsert);",
        eventId, eventName, venueName, geomPoint, eventStarttimeString,
        eventEndtimeString, eventCategory, eventDescription,
        eventAttendingCount, eventDeclinedCount, eventMaybeCount,
        eventNoReplyCount, eventProfilePicture);
    query = query.replace("+", "%20");

    try {
      CartoDBClientIF cartoDBCLient = new ApiKeyCartoDBClient(
          "cs32finalproject", "ad54038628d84dceb55a7adb81eddfcf9976e994");
      cartoDBCLient.request(query);
    } catch (CartoDBException e) {
      e.printStackTrace();
    }

  }
}
