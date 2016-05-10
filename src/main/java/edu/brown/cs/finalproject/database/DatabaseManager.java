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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.cartodb.CartoDBClientIF;
import com.cartodb.CartoDBException;
import com.cartodb.impl.ApiKeyCartoDBClient;
import com.cartodb.model.CartoDBResponse;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import edu.brown.cs.finalproject.entities.Event;
import edu.brown.cs.finalproject.entities.EventProxy;
import edu.brown.cs.finalproject.entities.Media;
import edu.brown.cs.finalproject.entities.MediaProxy;
import edu.brown.cs.finalproject.entities.User;
import edu.brown.cs.finalproject.entities.UserProxy;

public class DatabaseManager {
  private Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
      "cloud_name", "df1bylm3l", "api_key", "411248546735325", "api_secret",
      "M04dGcHdQhfUDM95fOQVXiG_Vjk"));

  public DatabaseManager() {
    // Empty Constructor for Now
  }

  public static boolean addEvent(String Name, String creatorID,
      String startDate, String endDate, String location, String category,
      String description, String origintype, String url) {

    String eventID = UUID.randomUUID().toString();
    String eventphoto;
    if (url == null) {
      eventphoto = url;
    } else {

      switch (category) {
      case "nightlife":
        eventphoto = "https://s-media-cache-ak0.pinimg.com/564x/f3/9b/4e/f39b4e783589f8137f833bb0b08c83b1.jpg";
        break;
      case "public lecture":
        eventphoto = "https://www.york.ac.uk/media/communications/publiclecturesinfo/publiclectures/publecs2.jpg";
        break;
      case "workshop":
        eventphoto = "http://beta.custompractice.co.uk/wp-content/uploads/2010/10/Custom-Practice-Classical-Acting-Workshop-004.jpg";
        break;
      case "food fest":
        eventphoto = "http://www.urban-people.co.uk/wp-content/uploads/2015/04/foodfest1.jpg";
        break;
      case "movies & art":
        eventphoto = "https://s-media-cache-ak0.pinimg.com/564x/f2/e1/78/f2e1783249efe7b2b0a1d8abf03e93b6.jpg";
        break;
      case "theater & performance":
        eventphoto = "https://s-media-cache-ak0.pinimg.com/564x/2e/0b/6d/2e0b6dcfa198dfed546c19c11f1c111c.jpg";
        break;
      case "religious & cultural celebration":
        eventphoto = "https://s-media-cache-ak0.pinimg.com/564x/ef/b7/c7/efb7c7d618619ce45f357d1eb5be8860.jpg";
        break;
      case "sports":
        eventphoto = "https://s-media-cache-ak0.pinimg.com/564x/b8/92/3b/b8923b793663d5584df61d619fad9d93.jpg";
        break;
      case "other":
        eventphoto = "https://s-media-cache-ak0.pinimg.com/564x/2d/e5/19/2de519935da8beaad7ceac2fd31cb2da.jpg";
        break;
      default:
        eventphoto = "https://s-media-cache-ak0.pinimg.com/564x/2d/e5/19/2de519935da8beaad7ceac2fd31cb2da.jpg";
      }
    }

    String venuename = location;
    if (venuename.contains(",")) {
      venuename = venuename.substring(0, venuename.indexOf(","));
    }

    String query = String
        .format(
            "INSERT INTO events (eventid,name,origintype,creatorid,startdate,category,description,attendingcount,declinedcount,maybecount,noreplycount,eventphoto,enddate,public,venuename) "
                + "VALUES ('%s', '%s', '%s', '%s', %s, '%s', '%s', 0, 0, 0, 0, '%s',%s,false,'%s');",
            eventID, Name, origintype, creatorID, startDate, category,
            description, eventphoto, endDate, venuename);

    System.out.println(query);
    String update = String.format(
        "UPDATE events SET the_geom = cdb_geocode_street_point('%s') "
            + "WHERE eventid = '%s';", location, eventID);
    System.out.println(update);

    try {
      CartoDBClientIF cartoDBCLient = new ApiKeyCartoDBClient(
          "cs32finalproject", "ad54038628d84dceb55a7adb81eddfcf9976e994");
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

  public static String addUser(String username, String fullname,
      String userMediaPath, String fbAccessToken) {

    Connection conn = Database.getConnection();
    String query = "INSERT INTO users (username, fullname, userMediaPath, fbAccessToken) VALUES (?,?,?,?);";

    try (PreparedStatement prep = conn.prepareStatement(query)) {
      prep.setString(1, username);
      prep.setString(2, fullname);
      prep.setString(3, userMediaPath);
      prep.setString(4, fbAccessToken);
      prep.addBatch();
      prep.executeBatch();
      return username;
    } catch (NullPointerException | SQLException n) {
      n.printStackTrace();
      return null;
    }
  }

  public static boolean addMedia(String eventID, String username, String path,
      String timestamp, String type) {

    Connection conn = Database.getConnection();
    String query = "INSERT INTO visual_media (mediaID, eventID, username, path, timestamp, type) VALUES (?,?,?,?,?,?);";

    try (PreparedStatement prep = conn.prepareStatement(query)) {
      String mediaID = UUID.randomUUID().toString();
      prep.setString(1, mediaID);
      prep.setString(2, eventID);
      prep.setString(3, username);
      prep.setString(4, path);
      prep.setString(5, timestamp);
      prep.setString(6, type);
      prep.addBatch();
      prep.executeBatch();
      return true;
    } catch (NullPointerException | SQLException n) {
      n.printStackTrace();
      return false;
    }
  }

  public static List<Media> getMedia(String eventID) {
    Connection conn = Database.getConnection();
    String query = "Select mediaID FROM visual_media WHERE eventID = ?";
    List<Media> storystream = new ArrayList<>();

    try (PreparedStatement prep = conn.prepareStatement(query)) {
      prep.setString(1, eventID);
      try (ResultSet rs = prep.executeQuery()) {
        while (rs.next()) {
          String mediaID = rs.getString(1);
          Media mediaProxy = new MediaProxy(mediaID);
          storystream.add(mediaProxy);
        }
      }
    } catch (SQLException s) {
      s.printStackTrace();
    }
    return storystream;
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

  public static boolean addAttendee(String userID, String eventID) {
    Connection conn = Database.getConnection();
    String query = "INSERT INTO going (eventID,username) "
        + "SELECT ?, ? "
        + "WHERE NOT EXISTS (SELECT 1 FROM going WHERE eventID = ? AND username = ?);";

    System.out.println(query);
    System.out.println(eventID);
    System.out.println(userID);

    try (PreparedStatement prep = conn.prepareStatement(query)) {
      prep.setString(1, eventID);
      prep.setString(2, userID);
      prep.setString(3, eventID);
      prep.setString(4, userID);
      prep.addBatch();
      prep.executeBatch();
      System.out.println(prep.toString());
    } catch (SQLException s) {
      s.printStackTrace();
      return false;
    }
    return true;
  }

  public static boolean addProfilePicture(String username, String url) {
    Connection conn = Database.getConnection();
    String query = "UPDATE users SET userMediaPath=? WHERE username=?";

    try (PreparedStatement prep = conn.prepareStatement(query)) {
      prep.setString(1, url);
      prep.setString(2, username);
      prep.addBatch();
      prep.executeBatch();
      return true;
    } catch (SQLException s) {
      s.printStackTrace();
      return false;
    }
  }

  public static boolean addEventPicture(String eventID, String url) {

    String query = String.format(
        "UPDATE events SET eventphoto='%s' WHERE eventid='%s'", url, eventID);
    try {
      CartoDBClientIF cartoDBCLient = new ApiKeyCartoDBClient(
          "cs32finalproject", "ad54038628d84dceb55a7adb81eddfcf9976e994");
      cartoDBCLient.request(query);
      return true;
    } catch (CartoDBException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      return false;
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

  public static boolean checkAttending(String eventID, String username) {
    Connection conn = Database.getConnection();
    String query = "SELECT EXISTS(SELECT 1 FROM going WHERE eventID = ? AND username = ? LIMIT 1);";
    String isSavedString = null;

    try (PreparedStatement prep = conn.prepareStatement(query)) {
      prep.setString(1, eventID);
      prep.setString(2, username);
      try (ResultSet rs = prep.executeQuery()) {
        while (rs.next()) {
          isSavedString = rs.getString(1);
        }
      }
    } catch (SQLException s) {
      s.printStackTrace();
    }

    if (isSavedString.equals("1")) {
      return true;
    } else {
      return false;
    }
  }

  public static boolean removeAttending(String userID, String eventID) {

    Connection conn = Database.getConnection();
    String query = "DELETE FROM going " + "WHERE eventID = ? AND username = ?;";

    try (PreparedStatement prep = conn.prepareStatement(query)) {
      prep.setString(1, eventID);
      prep.setString(2, userID);
      prep.addBatch();
      prep.executeBatch();
      System.out.println(prep.toString());
    } catch (SQLException s) {
      s.printStackTrace();
      return false;
    }

    return true;
  }

  public static boolean addInterested(String userID, String eventID) {
    Connection conn = Database.getConnection();
    String query = "INSERT INTO interested (eventID,username) "
        + "SELECT ?, ? "
        + "WHERE NOT EXISTS (SELECT 1 FROM interested WHERE eventID = ? AND username = ?);";

    System.out.println(query);
    System.out.println(eventID);
    System.out.println(userID);

    try (PreparedStatement prep = conn.prepareStatement(query)) {
      prep.setString(1, eventID);
      prep.setString(2, userID);
      prep.setString(3, eventID);
      prep.setString(4, userID);
      prep.addBatch();
      prep.executeBatch();
      System.out.println(prep.toString());
    } catch (SQLException s) {
      s.printStackTrace();
      return false;
    }
    return true;
  }

  public static boolean removeInterested(String userID, String eventID) {

    Connection conn = Database.getConnection();
    String query = "DELETE FROM interested "
        + "WHERE eventID = ? AND username = ?;";
    boolean didRemove = false;

    try (PreparedStatement prep = conn.prepareStatement(query)) {
      prep.setString(1, eventID);
      prep.setString(2, userID);
      prep.addBatch();
      prep.executeBatch();
      System.out.println(prep.toString());
    } catch (SQLException s) {
      s.printStackTrace();
      return false;
    }
    return true;
  }

  public static List<User> getInterested(String eventID) {
    Connection conn = Database.getConnection();
    String query = "SELECT username FROM interested WHERE eventID=?;";
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

  public static boolean checkInterested(String eventID, String username) {
    Connection conn = Database.getConnection();
    String query = "SELECT EXISTS(SELECT 1 FROM interested WHERE eventID = ? AND username = ? LIMIT 1);";
    String isSavedString = null;

    try (PreparedStatement prep = conn.prepareStatement(query)) {
      prep.setString(1, eventID);
      prep.setString(2, username);
      try (ResultSet rs = prep.executeQuery()) {
        while (rs.next()) {
          isSavedString = rs.getString(1);
        }
      }
    } catch (SQLException s) {
      s.printStackTrace();
    }

    if (isSavedString.equals("1")) {
      return true;
    } else {
      return false;
    }
  }

  public static List<Event> getEvents() {
    List<Event> events = new ArrayList<>();
    try {
      String query = "Select eventid from events;";
      CartoDBClientIF cartoDBCLient = new ApiKeyCartoDBClient(
          "cs32finalproject", "ad54038628d84dceb55a7adb81eddfcf9976e994");
      CartoDBResponse<Map<String, Object>> res = cartoDBCLient.request(query);
      for (int j = 0; j < res.getTotal_rows(); j++) {
        String eventID = (String) res.getRows().get(j).get("eventid");
        System.out.println(eventID);
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
          "cs32finalproject", "ad54038628d84dceb55a7adb81eddfcf9976e994");
      CartoDBResponse<Map<String, Object>> res = cartoDBCLient.request(query);
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

  /**
   * Returns a List<Event> the specified number of upcoming events within a
   * specified proximity to a specified location; sorted by proximity
   * 
   * @param latitute
   *          , double
   * @param longitude
   *          , double
   * @param radius
   *          , double
   * @param username
   *          , String
   * @return BrowseResultsHolder, which contains List<Event>, and two HashMaps
   *         indicating the user's 'saved' and 'attending' statuses for all
   *         events
   */
  public static BrowseResultsHolder getUpcomingEventsWithinProximitySortedByProximity(
      double latitude, double longitude, double radius, int limit,
      String username) {
    List<Event> events = new ArrayList<>();
    HashMap<String, Boolean> userSavedEvents = new HashMap<String, Boolean>();
    HashMap<String, Boolean> userAttendingEvents = new HashMap<String, Boolean>();
    try {

      DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
      Date date = new Date();

      StringBuilder queryBuilder = new StringBuilder();
      queryBuilder
          .append("SELECT eventid FROM events WHERE enddate>to_timestamp('");
      queryBuilder.append(dateFormat.format(date));
      queryBuilder
          .append("', 'YYYY/MM/dd HH24:MI:SS') AND ST_Distance(the_geom::geography, ST_SetSRID(ST_Point(");
      queryBuilder.append(longitude);
      queryBuilder.append(", ");
      queryBuilder.append(latitude);
      queryBuilder.append("),4326)::geography) < ");
      queryBuilder.append(radius);
      queryBuilder
          .append(" ORDER BY ST_Distance(the_geom::geography, ST_SetSRID(ST_Point(");
      queryBuilder.append(longitude);
      queryBuilder.append(", ");
      queryBuilder.append(latitude);
      queryBuilder.append("),4326)::geography) ASC LIMIT ");
      queryBuilder.append(limit);
      String query = queryBuilder.toString();
      System.out.println(query);

      CartoDBClientIF cartoDBCLient = new ApiKeyCartoDBClient(
          "cs32finalproject", "ad54038628d84dceb55a7adb81eddfcf9976e994");
      CartoDBResponse<Map<String, Object>> res = cartoDBCLient.request(query);
      for (int j = 0; j < res.getTotal_rows(); j++) {
        String eventID = (String) res.getRows().get(j).get("eventid");
        Event event = new EventProxy(eventID);
        events.add(event);

        userSavedEvents.put(eventID, checkInterested(eventID, username));
        userAttendingEvents.put(eventID, checkAttending(eventID, username));
      }
    } catch (CartoDBException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    return new BrowseResultsHolder(events, userSavedEvents, userAttendingEvents);
  }

  /**
   * Returns a List<Event> the specified number of upcoming events within a
   * specified proximity to a specified location; sorted by popularity
   * 
   * @param latitute
   *          , double
   * @param longitude
   *          , double
   * @param radius
   *          , double
   * @param username
   *          , String
   * @return BrowseResultsHolder, which contains List<Event>, and two HashMaps
   *         indicating the user's 'saved' and 'attending' statuses for all
   *         events
   */
  public static BrowseResultsHolder getUpcomingEventsWithinProximitySortedByPopularity(
      double latitude, double longitude, double radius, int limit,
      String username) {
    List<Event> events = new ArrayList<>();
    HashMap<String, Boolean> userSavedEvents = new HashMap<String, Boolean>();
    HashMap<String, Boolean> userAttendingEvents = new HashMap<String, Boolean>();
    try {

      DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
      Date date = new Date();

      StringBuilder queryBuilder = new StringBuilder();
      queryBuilder
          .append("SELECT eventid FROM events WHERE enddate>to_timestamp('");
      queryBuilder.append(dateFormat.format(date));
      queryBuilder
          .append("', 'YYYY/MM/dd HH24:MI:SS') AND ST_Distance(the_geom::geography, ST_SetSRID(ST_Point(");
      queryBuilder.append(longitude);
      queryBuilder.append(", ");
      queryBuilder.append(latitude);
      queryBuilder.append("),4326)::geography) < ");
      queryBuilder.append(radius);
      queryBuilder.append(" ORDER BY attendingCount DESC LIMIT ");
      queryBuilder.append(limit);
      String query = queryBuilder.toString();
      System.out.println(query);

      CartoDBClientIF cartoDBCLient = new ApiKeyCartoDBClient(
          "cs32finalproject", "ad54038628d84dceb55a7adb81eddfcf9976e994");
      CartoDBResponse<Map<String, Object>> res = cartoDBCLient.request(query);
      for (int j = 0; j < res.getTotal_rows(); j++) {
        String eventID = (String) res.getRows().get(j).get("eventid");
        Event event = new EventProxy(eventID);
        events.add(event);

        userSavedEvents.put(eventID, checkInterested(eventID, username));
        userAttendingEvents.put(eventID, checkAttending(eventID, username));
      }
    } catch (CartoDBException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }

    return new BrowseResultsHolder(events, userSavedEvents, userAttendingEvents);
  }

  public static List<Event> getPastEvents(String username) {
    List<Event> allevents = new ArrayList<>();
    String query = String.format("SELECT eventid FROM going WHERE username=?;");
    Connection conn = Database.getConnection();

    try (PreparedStatement prep = conn.prepareStatement(query)) {
      prep.setString(1, username);
      try (ResultSet rs = prep.executeQuery()) {
        while (rs.next()) {
          String eventID = rs.getString(1);
          Event eventproxy = new EventProxy(eventID);
          allevents.add(eventproxy);
        }
      } catch (ClassNotFoundException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    } catch (SQLException s) {
      s.printStackTrace();
    }
    Date currdate = new Date();
    List<Event> pastevents = new ArrayList<>();
    for (Event event : allevents) {
      String endDate = event.getEndDate();
      endDate = endDate.replace('T', ' ');
      endDate = endDate.replaceAll("Z", "");
      System.out.println(endDate);
      Timestamp timestamp = Timestamp.valueOf(endDate);
      if (currdate.getTime() > timestamp.getTime()) {
        pastevents.add(event);
      }
    }
    return pastevents;
  }

  public static List<Event> getFutureEvents(String username) {
    List<Event> allevents = new ArrayList<>();
    String query = String
        .format("SELECT eventID FROM interested WHERE username=? UNION SELECT "
            + "eventID FROM going WHERE username=?;");
    Connection conn = Database.getConnection();

    try (PreparedStatement prep = conn.prepareStatement(query)) {
      prep.setString(1, username);
      prep.setString(2, username);
      try (ResultSet rs = prep.executeQuery()) {
        while (rs.next()) {
          String eventID = rs.getString(1);
          Event eventproxy = new EventProxy(eventID);
          allevents.add(eventproxy);
        }
      } catch (ClassNotFoundException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    } catch (SQLException s) {
      s.printStackTrace();
    }
    Date currdate = new Date();
    List<Event> futureevents = new ArrayList<>();
    for (Event event : allevents) {

      String endDate = event.getEndDate();
      endDate = endDate.replace('T', ' ');
      endDate = endDate.replaceAll("Z", "");
      System.out.println(endDate);
      Timestamp timestamp = Timestamp.valueOf(endDate);
      if (currdate.getTime() < timestamp.getTime()) {
        futureevents.add(event);
      }
    }
    return futureevents;
  }

  public static List<Event> getSuggestedEvents(String username, double lat,
      double lng) {
    List<Event> allevents = new ArrayList<>();
    String query = String
        .format("SELECT eventID FROM interested WHERE username=? UNION SELECT "
            + "eventID FROM going WHERE username=?;");
    Connection conn = Database.getConnection();

    try (PreparedStatement prep = conn.prepareStatement(query)) {
      prep.setString(1, username);
      prep.setString(2, username);
      try (ResultSet rs = prep.executeQuery()) {
        while (rs.next()) {
          String eventID = rs.getString(1);
          Event eventproxy = new EventProxy(eventID);
          allevents.add(eventproxy);
        }
      } catch (ClassNotFoundException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    } catch (SQLException s) {
      s.printStackTrace();
    }

    int nightlife = 0;
    int publiclecture = 0;
    int workshop = 0;
    int foodfest = 0;
    int moviesandart = 0;
    int theaterandperformance = 0;
    int religiousandcultural = 0;
    int sports = 0;
    int other = 0;

    for (Event event : allevents) {
      String category = event.getCategory();

      switch (category) {
      case "nightlife":
        nightlife = +1;
      case "public lecture":
        publiclecture = +1;
      case "workshop":
        workshop = +1;
      case "food fest":
        foodfest = +1;
      case "movies & art":
        moviesandart = +1;
      case "theater & performance":
        theaterandperformance = +1;
      case "religious & cultural celebration":
        religiousandcultural = +1;
      case "sports":
        sports = +1;
      case "other":
        other = +1;
      }
    }

    Map<String, Integer> ratios = new HashMap<>();
    ratios.put("nightlife", nightlife);
    ratios.put("public lecture", publiclecture);
    ratios.put("workshop", workshop);
    ratios.put("food fest", foodfest);
    ratios.put("movies & art", moviesandart);
    ratios.put("theater & performace", theaterandperformance);
    ratios.put("religious & cultural celebration", religiousandcultural);
    ratios.put("sports", sports);

    // List<String> categories = new ArrayList<>();
    int max = Integer.MIN_VALUE;
    String cat1 = null;
    String cat2 = null;
    String cat3 = null;

    for (String category : ratios.keySet()) {
      if (ratios.get(category) > max) {
        max = ratios.get(category);
        cat1 = category;
      }
    }
    // categories.add(cat1);
    max = Integer.MIN_VALUE;
    for (String category : ratios.keySet()) {
      if (ratios.get(category) > max && !category.equals(cat1)) {
        max = ratios.get(category);
        cat2 = category;
      }
    }
    // categories.add(cat2);
    max = Integer.MIN_VALUE;
    for (String category : ratios.keySet()) {
      if (ratios.get(category) > max && !category.equals(cat1)
          && !category.equals(cat2)) {
        max = ratios.get(category);
        cat3 = category;
      }
    }
    // categories.add(cat3);
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    Date date = new Date();
    System.out.println(cat1);
    System.out.println(cat2);
    System.out.println(cat3);

    String filter = String
        .format(
            "SELECT eventid FROM events WHERE enddate>to_timestamp('%s', 'YYYY/MM/dd HH24:MI:SS')"
                + "AND (category='%s' OR category='%s' OR category='%s')"
                + "AND ST_Distance(the_geom::geography, ST_SetSRID(ST_Point(%f, %f), 4326)::geography) < 16100"
                + "ORDER BY attendingcount DESC LIMIT 10",
            dateFormat.format(date), cat1, cat2, cat3, lng, lat);
    List<Event> suggested = new ArrayList<>();

    try {
      CartoDBClientIF cartoDBCLient = new ApiKeyCartoDBClient(
          "cs32finalproject", "ad54038628d84dceb55a7adb81eddfcf9976e994");
      CartoDBResponse<Map<String, Object>> res = cartoDBCLient.request(filter);
      for (int j = 0; j < res.getTotal_rows(); j++) {
        String eventID = (String) res.getRows().get(j).get("eventid");
        System.out.println(eventID);
        Event event = new EventProxy(eventID);
        suggested.add(event);
      }
    } catch (CartoDBException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }

    return suggested;
  }

}
