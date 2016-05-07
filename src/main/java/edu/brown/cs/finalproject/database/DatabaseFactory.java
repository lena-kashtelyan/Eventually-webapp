package edu.brown.cs.finalproject.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
        + "username TEXT PRIMARY KEY     NOT NULL," + "fullname TEXT NOT NULL,"
        + "userMediaPath	TEXT," + "fbAccessToken TEXT" + ");";
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
        + "username TEXT NOT NULL,"
        + "FOREIGN KEY(username) REFERENCES users(username));";

    try (PreparedStatement prep = conn.prepareStatement(schema)) {
      prep.execute();
    }

    String addIndex = "CREATE INDEX going_eventID ON going (eventID);";
    try (PreparedStatement prep = conn.prepareStatement(addIndex)) {
      prep.execute();
    }

    addIndex = "CREATE INDEX going_username ON going (username);";
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

    String schema = "CREATE TABLE external(" + "username TEXT NOT NULL,"
        + "externalID TEXT NOT NULL,"
        + "FOREIGN KEY(username) REFERENCES users(username));";
    try (PreparedStatement prep = conn.prepareStatement(schema)) {
      prep.execute();
    }

    String addIndex = "CREATE INDEX external_username ON external (username);";
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
        + "eventID TEXT NOT NULL," + "username TEXT NOT NULL,"
        + "path TEXT NOT NULL," + "timestamp DATETIME NOT NULL,"
        + "PRIMARY KEY(mediaID),"
        + "FOREIGN KEY(username) REFERENCES users(username));";
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
        + "username TEXT NOT NULL,"
        + "FOREIGN KEY(username) REFERENCES users(username));";
    try (PreparedStatement prep = conn.prepareStatement(schema)) {
      prep.execute();
    }

    String addIndex = "CREATE INDEX invited_eventID ON invited (eventID);";
    try (PreparedStatement prep = conn.prepareStatement(addIndex)) {
      prep.execute();
    }

    addIndex = "CREATE INDEX invited_username ON invited (username);";
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
        + "username TEXT NOT NULL,"
        + "FOREIGN KEY(username) REFERENCES users(username));";
    try (PreparedStatement prep = conn.prepareStatement(schema)) {
      prep.execute();
    }

    String addIndex = "CREATE INDEX interested_eventID ON interested (eventID);";
    try (PreparedStatement prep = conn.prepareStatement(addIndex)) {
      prep.execute();
    }

    addIndex = "CREATE INDEX interested_username ON interested (username);";
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
        + "username TEXT NOT NULL,"
        + "fullname TEXT NOT NULL, PRIMARY KEY (facebookID),"
        + "FOREIGN KEY(username) REFERENCES users(username));";
    try (PreparedStatement prep = conn.prepareStatement(schema)) {
      prep.execute();
    }

    String addIndex = "CREATE INDEX nonusers_username ON nonusers (username);";
    try (PreparedStatement prep = conn.prepareStatement(addIndex)) {
      prep.execute();
    }
  }

}
