package edu.brown.cs.finalproject.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import edu.brown.cs.finalproject.entities.User;
import edu.brown.cs.finalproject.entities.UserProxy;

public final class DatabaseFactory {

  public static void createAndIndexTables() {
    try {
      createusersTable();
    } catch (SQLException e) {
      e.printStackTrace();
      System.out.println("ERROR: Problem in creating users table.");
    }

//    try {
//      createeventsTable();
//    } catch (SQLException e) {
//      e.printStackTrace();
//      System.out.println("ERROR: Problem in creating events table.");
//    }

    try {
      creategoingTable();
    } catch (SQLException e) {
      e.printStackTrace();
      System.out.println("ERROR: Problem in creating going table.");
    }

    try {
      createexternalTable();
    } catch (SQLException e) {
      e.printStackTrace();
      System.out.println("ERROR: Problem in creating going table.");
    }

    try {
      createVisualMediaTable();
    } catch (SQLException e) {
      e.printStackTrace();
      System.out.println("ERROR: Problem in creating going table.");
    }

//    try {
//      createvenueTable();
//    } catch (SQLException e) {
//      e.printStackTrace();
//      System.out.println("ERROR: Problem in creating going table.");
//    }

    try {
      createinvitedTable();
    } catch (SQLException e) {
      e.printStackTrace();
      System.out.println("ERROR: Problem in creating going table.");
    }

    try {
      createinterestedTable();
    } catch (SQLException e) {
      e.printStackTrace();
      System.out.println("ERROR: Problem in creating going table.");
    }

    try {
      createnonusersTable();
    } catch (SQLException e) {
      e.printStackTrace();
      System.out.println("ERROR: Problem in creating going table.");
    }
  }

  private static void createusersTable() throws SQLException {
    Connection conn = Database.getConnection();
    String dropPrevTableQuery = "DROP TABLE IF EXISTS users;";
    try (PreparedStatement prep = conn.prepareStatement(dropPrevTableQuery)) {
      prep.execute();
    }

    String schema = "CREATE TABLE users("
        + "username TEXT PRIMARY KEY     NOT NULL,"
        + "userMediaPath	TEXT," 
        + "fbAccessToken TEXT"   
        + ");";
    try (PreparedStatement prep = conn.prepareStatement(schema)) {
      prep.execute();
    }

    String addIndex = "CREATE INDEX users_username ON users (username);";
    try (PreparedStatement prep = conn.prepareStatement(addIndex)) {
      prep.execute();
    }

  }

//  private static void createeventsTable() throws SQLException {
//    Connection conn = Database.getConnection();
//
//    String dropPrevTableQuery = "DROP TABLE IF EXISTS events;";
//    try (PreparedStatement prep = conn.prepareStatement(dropPrevTableQuery)) {
//      prep.execute();
//    }
//
//    String schema = "CREATE TABLE events("
//        + "eventID TEXT PRIMARY KEY     NOT NULL,"
//        + "name           TEXT    NOT NULL,"
//        + "venueID            TEXT    NOT NULL,"
//        + "originType            TEXT," + "creatorID            TEXT,"
//        + "startDate        DATE     NOT NULL,"
//        + "startTime         DATETIME     NOT NULL,"
//        + "endTime         DATETIME," + "category         TEXT,"
//        + "public         BOOLEAN     NOT NULL," + "description	TEXT" + ");";
//    try (PreparedStatement prep = conn.prepareStatement(schema)) {
//      prep.execute();
//    }
//
//    String addIndex = "CREATE INDEX events_origintype ON events (origintype);";
//    try (PreparedStatement prep = conn.prepareStatement(addIndex)) {
//      prep.execute();
//    }
//
//    addIndex = "CREATE INDEX events_creatorID ON events (creatorID);";
//    try (PreparedStatement prep = conn.prepareStatement(addIndex)) {
//      prep.execute();
//    }
//
//    addIndex = "CREATE INDEX events_startDate ON events (startDate);";
//    try (PreparedStatement prep = conn.prepareStatement(addIndex)) {
//      prep.execute();
//    }
//
//    addIndex = "CREATE INDEX events_startTime ON events (startTime);";
//    try (PreparedStatement prep = conn.prepareStatement(addIndex)) {
//      prep.execute();
//    }
//
//    addIndex = "CREATE INDEX events_category ON events (category);";
//    try (PreparedStatement prep = conn.prepareStatement(addIndex)) {
//      prep.execute();
//    }
//
//    addIndex = "CREATE INDEX events_public ON events (public);";
//    try (PreparedStatement prep = conn.prepareStatement(addIndex)) {
//      prep.execute();
//    }
//
//  }

  private static void creategoingTable() throws SQLException {
    Connection conn = Database.getConnection();

    String dropPrevTableQuery = "DROP TABLE IF EXISTS going;";
    try (PreparedStatement prep = conn.prepareStatement(dropPrevTableQuery)) {
      prep.execute();
    }

    String schema = "CREATE TABLE going(" + "eventID TEXT NOT NULL,"
        + "userID TEXT NOT NULL,"
        + "FOREIGN KEY(userID) REFERENCES users(userID));";
    
    try (PreparedStatement prep = conn.prepareStatement(schema)) {
      prep.execute();
    }

    String addIndex = "CREATE INDEX going_eventID ON going (eventID);";
    try (PreparedStatement prep = conn.prepareStatement(addIndex)) {
      prep.execute();
    }

    addIndex = "CREATE INDEX going_userID ON going (userID);";
    try (PreparedStatement prep = conn.prepareStatement(addIndex)) {
      prep.execute();
    }

  }

  private static void createexternalTable() throws SQLException {
    Connection conn = Database.getConnection();

    String dropPrevTableQuery = "DROP TABLE IF EXISTS external;";
    try (PreparedStatement prep = conn.prepareStatement(dropPrevTableQuery)) {
      prep.execute();
    }

    String schema = "CREATE TABLE external(" + "userID TEXT NOT NULL,"
        + "externalID TEXT NOT NULL,"
        + "FOREIGN KEY(userID) REFERENCES users(userID));";
    try (PreparedStatement prep = conn.prepareStatement(schema)) {
      prep.execute();
    }

    String addIndex = "CREATE INDEX external_userID ON external (userID);";
    try (PreparedStatement prep = conn.prepareStatement(addIndex)) {
      prep.execute();
    }

  }

  private static void createVisualMediaTable() throws SQLException {
    Connection conn = Database.getConnection();

    String dropPrevTableQuery = "DROP TABLE IF EXISTS visual_media;";
    try (PreparedStatement prep = conn.prepareStatement(dropPrevTableQuery)) {
      prep.execute();
    }

    String schema = "CREATE TABLE visual_media(" + "mediaID TEXT NOT NULL,"
        + "eventID TEXT NOT NULL," + "userID TEXT NOT NULL,"
        + "path TEXT NOT NULL," + "timestamp DATETIME NOT NULL,"
        + "PRIMARY KEY(mediaID),"
        + "FOREIGN KEY(userID) REFERENCES users(userID));";
    try (PreparedStatement prep = conn.prepareStatement(schema)) {
      prep.execute();
    }

    String addIndex = "CREATE INDEX visual_media_eventID ON visual_media (eventID);";
    try (PreparedStatement prep = conn.prepareStatement(addIndex)) {
      prep.execute();
    }

  }

//  private static void createvenueTable() throws SQLException {
//    Connection conn = Database.getConnection();
//
//    String dropPrevTableQuery = "DROP TABLE IF EXISTS venue;";
//    try (PreparedStatement prep = conn.prepareStatement(dropPrevTableQuery)) {
//      prep.execute();
//    }
//
//    String schema = "CREATE TABLE venue(" + "venueID TEXT NOT NULL,"
//        + "name TEXT NOT NULL," + "latitude TEXT NOT NULL,"
//        + "longitude TEXT NOT NULL," + "PRIMARY KEY(venueID));";
//    try (PreparedStatement prep = conn.prepareStatement(schema)) {
//      prep.execute();
//    }
//
//  }

  private static void createinvitedTable() throws SQLException {
    Connection conn = Database.getConnection();

    String dropPrevTableQuery = "DROP TABLE IF EXISTS invited;";
    try (PreparedStatement prep = conn.prepareStatement(dropPrevTableQuery)) {
      prep.execute();
    }

    String schema = "CREATE TABLE invited(" + "eventID TEXT NOT NULL,"
        + "userID TEXT NOT NULL,"
        + "FOREIGN KEY(userID) REFERENCES users(userID));";
    try (PreparedStatement prep = conn.prepareStatement(schema)) {
      prep.execute();
    }

    String addIndex = "CREATE INDEX invited_eventID ON invited (eventID);";
    try (PreparedStatement prep = conn.prepareStatement(addIndex)) {
      prep.execute();
    }

    addIndex = "CREATE INDEX invited_userID ON invited (userID);";
    try (PreparedStatement prep = conn.prepareStatement(addIndex)) {
      prep.execute();
    }

  }

  private static void createinterestedTable() throws SQLException {
    Connection conn = Database.getConnection();

    String dropPrevTableQuery = "DROP TABLE IF EXISTS interested;";
    try (PreparedStatement prep = conn.prepareStatement(dropPrevTableQuery)) {
      prep.execute();
    }

    String schema = "CREATE TABLE interested(" + "eventID TEXT NOT NULL,"
        + "userID TEXT NOT NULL,"
        + "FOREIGN KEY(userID) REFERENCES users(userID));";
    try (PreparedStatement prep = conn.prepareStatement(schema)) {
      prep.execute();
    }

    String addIndex = "CREATE INDEX interested_eventID ON interested (eventID);";
    try (PreparedStatement prep = conn.prepareStatement(addIndex)) {
      prep.execute();
    }

    addIndex = "CREATE INDEX interested_userID ON interested (userID);";
    try (PreparedStatement prep = conn.prepareStatement(addIndex)) {
      prep.execute();
    }

  }

  private static void createnonusersTable() throws SQLException {
    Connection conn = Database.getConnection();

    String dropPrevTableQuery = "DROP TABLE IF EXISTS nonusers;";
    try (PreparedStatement prep = conn.prepareStatement(dropPrevTableQuery)) {
      prep.execute();
    }

    String schema = "CREATE TABLE nonusers(" + "facebookID TEXT NOT NULL,"
        + "userID TEXT NOT NULL,"
        + "fullname TEXT NOT NULL, PRIMARY KEY (facebookID),"
        + "FOREIGN KEY(userID) REFERENCES users(userID));";
    try (PreparedStatement prep = conn.prepareStatement(schema)) {
      prep.execute();
    }

    String addIndex = "CREATE INDEX nonusers_userID ON nonusers (userID);";
    try (PreparedStatement prep = conn.prepareStatement(addIndex)) {
      prep.execute();
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
    String query = "SELECT userid FROM going WHERE eventID=?;";
    List<User> attendees = new ArrayList<>();  
    
    try (PreparedStatement prep = conn.prepareStatement(query)) {
      prep.setString(1, eventID);
      try (ResultSet rs = prep.executeQuery()) {
        while (rs.next()) {
          String userid = rs.getString(1);
          User userproxy = new UserProxy(userid);
          attendees.add(userproxy);
        }
      }
    } catch (SQLException s) {
      s.printStackTrace();
    }
    return attendees;
  }
  
  
  
//  public static String addVenue(String name, double lat, double lng) {
//     Connection conn = Database.getConnection();
//     String query = "INSERT INTO users VALUES (?,?,?,?)";
//     
//     try (PreparedStatement prep = conn.prepareStatement(query)) {
//       String id = UUID.randomUUID().toString();
//       prep.setString(1, id );
//       prep.setString(2, name);
//       prep.setDouble(2, lat);
//       prep.setDouble(4, lng);
//       prep.addBatch();
//       prep.executeBatch();
//       return id;
//     } catch (NullPointerException | SQLException n) {
//       n.printStackTrace();
//       return null;
//     }
//  }
// 
//  public static String addEvent(String name, String venueID, String originType, String creatorid, Date startDate,
//      Time startTime, Time endTime, boolean isPublic, String category, String description) {
//    Connection conn = Database.getConnection();
//    String query = "INSERT INTO events VALUES (?,?,?,?, ?,?,?,?, ?,?,?)";
//    
//    try (PreparedStatement prep = conn.prepareStatement(query)) {
//      String id = UUID.randomUUID().toString();
//      prep.setString(1, id );
//      prep.setString(2, name);
//      prep.setString(3, name);
//      prep.setString(4, name);
//      prep.setString(5, name);
//      prep.setDate(6, startDate);
//      prep.setTime(7, startTime);
//      prep.setTime(8, endTime);
//      prep.setBoolean(9, isPublic);
//      prep.setString(10, category);
//      prep.setString(11, description);
//      prep.addBatch();
//      prep.executeBatch();
//      return id;
//    } catch (NullPointerException | SQLException n) {
//      n.printStackTrace();
//      return null;
//    }
// }

}
