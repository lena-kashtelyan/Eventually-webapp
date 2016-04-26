package edu.brown.cs.finalproject.database;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.cartodb.CartoDBClientIF;
import com.cartodb.CartoDBException;
import com.cartodb.impl.ApiKeyCartoDBClient;
import com.cartodb.model.CartoDBResponse;

import edu.brown.cs.finalproject.entities.Event;
import edu.brown.cs.finalproject.entities.EventProxy;

public class DatabaseManager {
	
	  public DatabaseManager() {
	    //Empty Constructor for Now
	  }

	  public static boolean addInternalEvent(String Name,
      String creatorID, String startDate, double latitude, double longitude, String category, String description) {
	    
	    String eventID = UUID.randomUUID().toString();
	    
	    String query = String.format("INSERT INTO events (eventid,name,latitude,longitude,origintype,creatorid,startdate,category,description,attendingcount,declinedcount,maybecount,noreplycount) "
	        + "VALUES ('%s', '%s', %f, %f, 'internal', '%s', %s, '%s', '%s', 0, 0, 0, 0);",
	        eventID, Name, latitude, longitude, creatorID, startDate, category, description );
	    System.out.println(query);
	    try {
      CartoDBClientIF cartoDBCLient= new ApiKeyCartoDBClient("cs32finalproject", "ad54038628d84dceb55a7adb81eddfcf9976e994");
      cartoDBCLient.request(query);
    } catch (CartoDBException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      return false;
    }
	    
	    Event newEvent;
      try {
        newEvent = new EventProxy(eventID);
      } catch (ClassNotFoundException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        return false;
      }
	    return true;
	  }
	  
	  public static List<Event> getEvents() {
	    List<Event> events = new ArrayList<>();
	    try {
	      String query = "Select eventid from events;";
      CartoDBClientIF cartoDBCLient= new ApiKeyCartoDBClient("cs32finalproject", "ad54038628d84dceb55a7adb81eddfcf9976e994");
      CartoDBResponse<Map<String, Object>> res = cartoDBCLient.request(query);
      for (int j=0; j< res.getTotal_rows(); j++) {
        String eventID = (String) res.getRows().get(j).get("eventid");
        Event event = new EventProxy(eventID);
        events.add(event);
      }
    } catch (CartoDBException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
	    return events;
	  }
	  
}
