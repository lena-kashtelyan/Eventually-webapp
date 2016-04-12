package edu.brown.cs.finalproject.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public final class DatabaseFactory {

  public static void createAndIndexTables() {
    try {
      createUsersTable();
    } catch (SQLException e) {
      e.printStackTrace();
      System.out.println("ERROR: Problem in creating USERS table.");
    }

    try {
      createEventsTable();
    } catch (SQLException e) {
      e.printStackTrace();
      System.out.println("ERROR: Problem in creating EVENTS table.");
    }

    try {
      createGoingTable();
    } catch (SQLException e) {
      e.printStackTrace();
      System.out.println("ERROR: Problem in creating GOING table.");
    }

    try {
      createExternalTable();
    } catch (SQLException e) {
      e.printStackTrace();
      System.out.println("ERROR: Problem in creating GOING table.");
    }

    try {
      createVisualMediaTable();
    } catch (SQLException e) {
      e.printStackTrace();
      System.out.println("ERROR: Problem in creating GOING table.");
    }

    try {
      createVenueTable();
    } catch (SQLException e) {
      e.printStackTrace();
      System.out.println("ERROR: Problem in creating GOING table.");
    }

    try {
      createInvitedTable();
    } catch (SQLException e) {
      e.printStackTrace();
      System.out.println("ERROR: Problem in creating GOING table.");
    }

    try {
      createInterestedTable();
    } catch (SQLException e) {
      e.printStackTrace();
      System.out.println("ERROR: Problem in creating GOING table.");
    }

    try {
      createNonUsersTable();
    } catch (SQLException e) {
      e.printStackTrace();
      System.out.println("ERROR: Problem in creating GOING table.");
    }
  }

  private static void createUsersTable() throws SQLException {
    Connection conn = Database.getConnection();
    String dropPrevTableQuery = "DROP TABLE IF EXISTS USERS;";
    try (PreparedStatement prep = conn.prepareStatement(dropPrevTableQuery)) {
      prep.execute();
    }

    String schema = "CREATE TABLE USERS("
        + "userID TEXT PRIMARY KEY     NOT NULL,"
        + "username           CHAR(30)    NOT NULL,"
        + "fullname            TEXT	NOT NULL,"
        + "password        CHAR(30)	NOT NULL," + "Q1         TEXT	NOT NULL,"
        + "A1         TEXT	NOT NULL," + "Q2         TEXT	NOT NULL,"
        + "A2         TEXT	NOT NULL," + "userMediaPath	TEXT	NOT NULL" + ");";
    try (PreparedStatement prep = conn.prepareStatement(schema)) {
      prep.execute();
    }

    String addIndex = "CREATE INDEX users_username ON USERS (username);";
    try (PreparedStatement prep = conn.prepareStatement(addIndex)) {
      prep.execute();
    }

  }

  private static void createEventsTable() throws SQLException {
    Connection conn = Database.getConnection();

    String dropPrevTableQuery = "DROP TABLE IF EXISTS EVENTS;";
    try (PreparedStatement prep = conn.prepareStatement(dropPrevTableQuery)) {
      prep.execute();
    }

    String schema = "CREATE TABLE EVENTS("
        + "eventID TEXT PRIMARY KEY     NOT NULL,"
        + "name           TEXT    NOT NULL,"
        + "venueID            TEXT    NOT NULL,"
        + "originType            TEXT," + "creatorID            TEXT,"
        + "startDate        DATE     NOT NULL,"
        + "startTime         DATETIME     NOT NULL,"
        + "endTime         DATETIME," + "category         TEXT,"
        + "public         BOOLEAN     NOT NULL," + "description	TEXT" + ");";
    try (PreparedStatement prep = conn.prepareStatement(schema)) {
      prep.execute();
    }

    String addIndex = "CREATE INDEX events_origintype ON EVENTS (origintype);";
    try (PreparedStatement prep = conn.prepareStatement(addIndex)) {
      prep.execute();
    }

    addIndex = "CREATE INDEX events_creatorID ON EVENTS (creatorID);";
    try (PreparedStatement prep = conn.prepareStatement(addIndex)) {
      prep.execute();
    }

    addIndex = "CREATE INDEX events_startDate ON EVENTS (startDate);";
    try (PreparedStatement prep = conn.prepareStatement(addIndex)) {
      prep.execute();
    }

    addIndex = "CREATE INDEX events_startTime ON EVENTS (startTime);";
    try (PreparedStatement prep = conn.prepareStatement(addIndex)) {
      prep.execute();
    }

    addIndex = "CREATE INDEX events_category ON EVENTS (category);";
    try (PreparedStatement prep = conn.prepareStatement(addIndex)) {
      prep.execute();
    }

    addIndex = "CREATE INDEX events_public ON EVENTS (public);";
    try (PreparedStatement prep = conn.prepareStatement(addIndex)) {
      prep.execute();
    }

  }

  private static void createGoingTable() throws SQLException {
    Connection conn = Database.getConnection();

    String dropPrevTableQuery = "DROP TABLE IF EXISTS GOING;";
    try (PreparedStatement prep = conn.prepareStatement(dropPrevTableQuery)) {
      prep.execute();
    }

    String schema = "CREATE TABLE GOING(" + "eventID TEXT NOT NULL,"
        + "userID TEXT NOT NULL,"
        + "FOREIGN KEY(userID) REFERENCES USERS(userID),"
        + "FOREIGN KEY(eventID) REFERENCES EVENTS(eventID)" + ");";
    try (PreparedStatement prep = conn.prepareStatement(schema)) {
      prep.execute();
    }

    String addIndex = "CREATE INDEX going_eventID ON GOING (eventID);";
    try (PreparedStatement prep = conn.prepareStatement(addIndex)) {
      prep.execute();
    }

    addIndex = "CREATE INDEX going_userID ON GOING (userID);";
    try (PreparedStatement prep = conn.prepareStatement(addIndex)) {
      prep.execute();
    }

  }

  private static void createExternalTable() throws SQLException {
    Connection conn = Database.getConnection();

    String dropPrevTableQuery = "DROP TABLE IF EXISTS EXTERNAL;";
    try (PreparedStatement prep = conn.prepareStatement(dropPrevTableQuery)) {
      prep.execute();
    }

    String schema = "CREATE TABLE EXTERNAL(" + "userID TEXT NOT NULL,"
        + "externalID TEXT NOT NULL,"
        + "FOREIGN KEY(userID) REFERENCES USERS(userID));";
    try (PreparedStatement prep = conn.prepareStatement(schema)) {
      prep.execute();
    }

    String addIndex = "CREATE INDEX external_userID ON EXTERNAL (userID);";
    try (PreparedStatement prep = conn.prepareStatement(addIndex)) {
      prep.execute();
    }

  }

  private static void createVisualMediaTable() throws SQLException {
    Connection conn = Database.getConnection();

    String dropPrevTableQuery = "DROP TABLE IF EXISTS VISUAL_MEDIA;";
    try (PreparedStatement prep = conn.prepareStatement(dropPrevTableQuery)) {
      prep.execute();
    }

    String schema = "CREATE TABLE VISUAL_MEDIA(" + "mediaID TEXT NOT NULL,"
        + "eventID TEXT NOT NULL," + "userID TEXT NOT NULL,"
        + "path TEXT NOT NULL," + "timestamp DATETIME NOT NULL,"
        + "PRIMARY KEY(mediaID),"
        + "FOREIGN KEY(userID) REFERENCES USERS(userID),"
        + "FOREIGN KEY(eventID) REFERENCES EVENTS(eventID));";
    try (PreparedStatement prep = conn.prepareStatement(schema)) {
      prep.execute();
    }

    String addIndex = "CREATE INDEX visual_media_eventID ON VISUAL_MEDIA (eventID);";
    try (PreparedStatement prep = conn.prepareStatement(addIndex)) {
      prep.execute();
    }

  }

  private static void createVenueTable() throws SQLException {
    Connection conn = Database.getConnection();

    String dropPrevTableQuery = "DROP TABLE IF EXISTS VENUE;";
    try (PreparedStatement prep = conn.prepareStatement(dropPrevTableQuery)) {
      prep.execute();
    }

    String schema = "CREATE TABLE VENUE(" + "venueID TEXT NOT NULL,"
        + "name TEXT NOT NULL," + "latitude FLOAT NOT NULL,"
        + "longitude FLOAT NOT NULL," + "PRIMARY KEY(venueID));";
    try (PreparedStatement prep = conn.prepareStatement(schema)) {
      prep.execute();
    }

  }

  private static void createInvitedTable() throws SQLException {
    Connection conn = Database.getConnection();

    String dropPrevTableQuery = "DROP TABLE IF EXISTS INVITED;";
    try (PreparedStatement prep = conn.prepareStatement(dropPrevTableQuery)) {
      prep.execute();
    }

    String schema = "CREATE TABLE INVITED(" + "eventID TEXT NOT NULL,"
        + "userID TEXT NOT NULL,"
        + "FOREIGN KEY(userID) REFERENCES USERS(userID),"
        + "FOREIGN KEY(eventID) REFERENCES EVENTS(eventID));";
    try (PreparedStatement prep = conn.prepareStatement(schema)) {
      prep.execute();
    }

    String addIndex = "CREATE INDEX invited_eventID ON INVITED (eventID);";
    try (PreparedStatement prep = conn.prepareStatement(addIndex)) {
      prep.execute();
    }

    addIndex = "CREATE INDEX invited_userID ON INVITED (userID);";
    try (PreparedStatement prep = conn.prepareStatement(addIndex)) {
      prep.execute();
    }

  }

  private static void createInterestedTable() throws SQLException {
    Connection conn = Database.getConnection();

    String dropPrevTableQuery = "DROP TABLE IF EXISTS INTERESTED;";
    try (PreparedStatement prep = conn.prepareStatement(dropPrevTableQuery)) {
      prep.execute();
    }

    String schema = "CREATE TABLE INTERESTED(" + "eventID TEXT NOT NULL,"
        + "userID TEXT NOT NULL,"
        + "FOREIGN KEY(userID) REFERENCES USERS(userID),"
        + "FOREIGN KEY(eventID) REFERENCES EVENTS(eventID));";
    try (PreparedStatement prep = conn.prepareStatement(schema)) {
      prep.execute();
    }

    String addIndex = "CREATE INDEX interested_eventID ON INTERESTED (eventID);";
    try (PreparedStatement prep = conn.prepareStatement(addIndex)) {
      prep.execute();
    }

    addIndex = "CREATE INDEX interested_userID ON INTERESTED (userID);";
    try (PreparedStatement prep = conn.prepareStatement(addIndex)) {
      prep.execute();
    }

  }

  private static void createNonUsersTable() throws SQLException {
    Connection conn = Database.getConnection();

    String dropPrevTableQuery = "DROP TABLE IF EXISTS NONUSERS;";
    try (PreparedStatement prep = conn.prepareStatement(dropPrevTableQuery)) {
      prep.execute();
    }

    String schema = "CREATE TABLE NONUSERS(" + "facebookID TEXT NOT NULL,"
        + "userID TEXT NOT NULL,"
        + "fullname TEXT NOT NULL, PRIMARY KEY (facebookID),"
        + "FOREIGN KEY(userID) REFERENCES USERS(userID));";
    try (PreparedStatement prep = conn.prepareStatement(schema)) {
      prep.execute();
    }

    String addIndex = "CREATE INDEX nonusers_userID ON NONUSERS (userID);";
    try (PreparedStatement prep = conn.prepareStatement(addIndex)) {
      prep.execute();
    }
  }

}
