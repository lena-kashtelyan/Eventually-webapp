package edu.brown.cs.finalproject.entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.brown.cs.finalproject.database.Database;

public class UserProxy extends EntityProxy<User> implements User {

  public UserProxy(String id) {
    super(id);
  }

  @Override
  protected void pullFromDB() {
    String userquery = "SELECT * FROM users WHERE username=?;";
    String fullname;
    String usermediapath;
    String fbToken;
    Connection conn = Database.getConnection();

    try (PreparedStatement prep = conn.prepareStatement(userquery)) {
      prep.setString(1, id);
      try (ResultSet rs = prep.executeQuery()) {
        fullname = rs.getString(2);
        usermediapath = rs.getString(3);
        fbToken = rs.getString(4);
      }
    } catch (NullPointerException | SQLException n) {
      System.out.println("ERROR: username not in User Table");
      internal = null;
      return;
    }

    internal = new UserBean(id, fullname,
        usermediapath, fbToken);
  }

  @Override
  public String getUserMediaPath() {
    getInternal();
    return internal.getUserMediaPath();
  }

  @Override
  public String setUserMediaPath(String userMediaPath) {
    getInternal();
    return internal.setUserMediaPath(userMediaPath);
  }

  @Override
  public String getFullName() {
    getInternal();
    return internal.getFullName();
  }

  @Override
  public String getFBtoken() {
    getInternal();
    return internal.getFBtoken();
  }

}
