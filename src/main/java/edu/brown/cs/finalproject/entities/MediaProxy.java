package edu.brown.cs.finalproject.entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.brown.cs.finalproject.database.Database;

public class MediaProxy extends EntityProxy<Media> implements Media {

  public MediaProxy(String id) {
    super(id);

  }

  @Override
  public String getEventID() {
    getInternal();
    return internal.getEventID();
  }

  @Override
  public String getUserName() {
    getInternal();
    return internal.getUserName();
  }

  @Override
  public String getPath() {
    getInternal();
    return internal.getPath();
  }

  @Override
  public String getType() {
    getInternal();
    return internal.getType();
  }

  @Override
  public String getTimeStamp() {
    getInternal();
    return internal.getTimeStamp();
  }

  @Override
  protected void pullFromDB() {
    String userquery = "SELECT * FROM visual_media WHERE mediaID=?;";
    String eventID;
    String path;
    String username;
    String timestamp;
    String type;

    Connection conn = Database.getConnection();

    try (PreparedStatement prep = conn.prepareStatement(userquery)) {
      prep.setString(1, id);
      try (ResultSet rs = prep.executeQuery()) {
        eventID = rs.getString(2);
        username = rs.getString(3);
        path = rs.getString(4);
        timestamp = rs.getString(5);
        type = rs.getString(6);
      }
    } catch (NullPointerException | SQLException n) {
      System.out.println("ERROR: username not in User Table");
      internal = null;
      return;
    }

    internal = new MediaBean(id, eventID, username, path, timestamp, type);

  }

}
