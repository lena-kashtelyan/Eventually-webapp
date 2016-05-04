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
        + "password TEXT NOT NULL,"
        + "firstname TEXT NOT NULL,"
        + "lastname TEXT NOT NULL,"
        + "email TEXT NOT NULL,"
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

}
