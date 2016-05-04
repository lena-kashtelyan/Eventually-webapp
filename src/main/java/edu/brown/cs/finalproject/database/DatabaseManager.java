package edu.brown.cs.finalproject.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.cartodb.CartoDBClientIF;
import com.cartodb.CartoDBException;
import com.cartodb.impl.ApiKeyCartoDBClient;
import com.cartodb.model.CartoDBResponse;

import edu.brown.cs.finalproject.entities.Event;
import edu.brown.cs.finalproject.entities.EventProxy;
import edu.brown.cs.finalproject.entities.User;
import edu.brown.cs.finalproject.entities.UserProxy;

public class DatabaseManager {

  public DatabaseManager() {
    // Empty Constructor for Now
  }

  public static boolean addEvent(String Name, String creatorID,
      String startDate, String endDate, String location, String category,
      String description, String origintype) {

    String eventID = UUID.randomUUID().toString();

    String eventphoto = "https://s-media-cache-ak0.pinimg.com/564x/f3/69/b4/f369b42357a27eb40068f675f62366ce.jpg";
    if (category.equals("social gathering")) {
      eventphoto = "https://s-media-cache-ak0.pinimg.com/564x/2d/e5/19/2de519935da8beaad7ceac2fd31cb2da.jpg";
    } else if (category.equals("performance")) {
      eventphoto = "https://s-media-cache-ak0.pinimg.com/564x/f3/9b/4e/f39b4e783589f8137f833bb0b08c83b1.jpg";
    } else if (category.equals("academic event")) {
      eventphoto = "https://s-media-cache-ak0.pinimg.com/564x/4e/f7/29/4ef7299074efa998232fd99a340fda57.jpg";
    }

    System.out.println(startDate);
    System.out.println("**************************************************************************************");

    String query = String
        .format("INSERT INTO events (eventid,name,origintype,creatorid,startdate,category,description,attendingcount,declinedcount,maybecount,noreplycount,eventphoto,enddate) "
            + "VALUES ('%s', '%s', '%s', '%s', %s, '%s', '%s', 0, 0, 0, 0, '%s',%s);",
            eventID, Name, origintype, creatorID, startDate,
            category, description, eventphoto, endDate);
    System.out.println(query);
    String update = String.format(
        "UPDATE events SET the_geom = cdb_geocode_street_point('%s') "
            + "WHERE eventid = '%s';", location, eventID);
    System.out.println(update);

    try {
      CartoDBClientIF cartoDBCLient = new ApiKeyCartoDBClient(
          "cs32finalproject",
          "ad54038628d84dceb55a7adb81eddfcf9976e994");
      cartoDBCLient.request(query);
      cartoDBCLient.request(update);
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

  public static String addUser(String username, String fullname, String userMediaPath,
      String fbAccessToken) {

    Connection conn = Database.getConnection();
    String query = "INSERT INTO users (username, fullname, password, userMediaPath, fbAccessToken) VALUES (?,?,?);";

    try (PreparedStatement prep = conn.prepareStatement(query)) {
      prep.setString(1, username);
      prep.setString(2, fullname);
      prep.setString(3, userMediaPath);
      prep.setString(4,  fbAccessToken);
      prep.addBatch();
      prep.executeBatch();
      return username;
    } catch (NullPointerException | SQLException n) {
      n.printStackTrace();
      return null;
    }
  }

  public String getUsersFBAccessToken(String username) {
    Connection conn = Database.getConnection();
    String query = "SELECT fbAccessToken from users WHERE username=?;";
    String fbAccessToken = null;
    try (PreparedStatement prep = conn.prepareStatement(query)) {
      prep.setString(1, username);
      try (ResultSet rs = prep.executeQuery()) {
        while (rs.next()) {
          fbAccessToken = rs.getString(1);
        }
      }
      return fbAccessToken;
    } catch (NullPointerException | SQLException n) {
      n.printStackTrace();
      return null;
    }
  }

  public String setUsersFBAccessToken(String username, String newFBAccessToken) {
    Connection conn = Database.getConnection();
    String query = "UPDATE users SET fbAccessToken=? WHERE username=?;";

    try (PreparedStatement prep = conn.prepareStatement(query)) {
      prep.setString(1, newFBAccessToken);
      prep.setString(2, username);
      prep.addBatch();
      prep.executeBatch();
      return newFBAccessToken;
    } catch (NullPointerException | SQLException n) {
      n.printStackTrace();
      return null;
    }
  }

  public String getUsersMediaPath(String username) {
    Connection conn = Database.getConnection();
    String query = "SELECT userMediaPath from users WHERE username=?;";
    String userMediaPath = null;
    try (PreparedStatement prep = conn.prepareStatement(query)) {
      prep.setString(1, username);
      try (ResultSet rs = prep.executeQuery()) {
        while (rs.next()) {
          userMediaPath = rs.getString(1);
        }
      }
      return userMediaPath;
    } catch (NullPointerException | SQLException n) {
      n.printStackTrace();
      return null;
    }
  }

  public String setUsersMediaPath(String username, String newMediaPath) {
    Connection conn = Database.getConnection();
    String query = "UPDATE users SET userMediaPath=? WHERE username=?;";

    try (PreparedStatement prep = conn.prepareStatement(query)) {
      prep.setString(1, newMediaPath);
      prep.setString(2, username);
      prep.addBatch();
      prep.executeBatch();
      return newMediaPath;
    } catch (NullPointerException | SQLException n) {
      n.printStackTrace();
      return null;
    }
  }
  
  public static void addAttendee(String userID, String eventID) {
    Connection conn = Database.getConnection();
    String query = "INSERT INTO going VALUES(?,?);";
    
    try (PreparedStatement prep = conn.prepareStatement(query)) {
      prep.setString(1, eventID);
      prep.setString(2,  userID);
      prep.addBatch();
      prep.executeBatch();
    } catch (SQLException s) {
      s.printStackTrace();
    }
  }
  
  public static List<User> getAttendees(String eventID) {
    Connection conn = Database.getConnection();
    String query = "SELECT username FROM going WHERE eventID=?;";
    List<User> attendees = new ArrayList<>();  
    
    try (PreparedStatement prep = conn.prepareStatement(query)) {
      prep.setString(1, eventID);
      try (ResultSet rs = prep.executeQuery()) {
        while (rs.next()) {
          String username = rs.getString(1);
          User userproxy = new UserProxy(username);
          attendees.add(userproxy);
        }
      }
    } catch (SQLException s) {
      s.printStackTrace();
    }
    return attendees;
  }

  public static List<Event> getEvents() {
    List<Event> events = new ArrayList<>();
    try {
      String query = "Select eventid from events;";
      CartoDBClientIF cartoDBCLient = new ApiKeyCartoDBClient(
          "cs32finalproject",
          "ad54038628d84dceb55a7adb81eddfcf9976e994");
      CartoDBResponse<Map<String, Object>> res = cartoDBCLient
          .request(query);
      for (int j = 0; j < res.getTotal_rows(); j++) {
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

  // changed getEvents into getUpcomingEvents (Ivaylo)
  // fetches from CartoDB all events with an enddate timestamp after the
  // current time
  public static List<Event> getUpcomingEvents() {
    List<Event> events = new ArrayList<>();
    try {

      DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
      Date date = new Date();
      // System.out.println(dateFormat.format(date)); //2014/08/06
      // 15:59:48

      StringBuilder queryBuilder = new StringBuilder();
      queryBuilder
      .append("SELECT eventid FROM events WHERE enddate>to_timestamp('");
      queryBuilder.append(dateFormat.format(date));
      queryBuilder.append("', 'YYYY/MM/dd HH24:MI:SS')");
      String query = queryBuilder.toString();
      System.out.println(query);

      CartoDBClientIF cartoDBCLient = new ApiKeyCartoDBClient(
          "cs32finalproject",
          "ad54038628d84dceb55a7adb81eddfcf9976e994");
      CartoDBResponse<Map<String, Object>> res = cartoDBCLient
          .request(query);
      for (int j = 0; j < res.getTotal_rows(); j++) {
        String eventID = (String) res.getRows().get(j).get("eventid");
        Event event = new EventProxy(eventID);
        events.add(event);
      }
    } catch (CartoDBException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    return events;
  }
}
