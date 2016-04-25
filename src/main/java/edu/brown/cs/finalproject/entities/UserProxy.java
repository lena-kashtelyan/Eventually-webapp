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
    String userquery = "SELECT * FROM users WHERE userID=?;";
    String username;
    String password;
    String fullname;
    String q1;
    String a1;
    String q2;
    String a2;
    String usermediapath;
    Connection conn = Database.getConnection();

    try (PreparedStatement prep = conn.prepareStatement(userquery)) {
      prep.setString(1, id);
      try (ResultSet rs = prep.executeQuery()) {
        username = rs.getString(2);
        fullname = rs.getString(3);
        password = rs.getString(4);
        q1 = rs.getString(5);
        a1 = rs.getString(6);
        q2 = rs.getString(7);
        a2 = rs.getString(8);
        usermediapath = rs.getString(9);
      }
    } catch (NullPointerException | SQLException n) {
      System.out.println("ERROR: ID not in User Table");
      internal = null;
      return;
    }

    internal = new UserBean(id, username, password, fullname, q1, a1, q2, a2,
        usermediapath);
  }

  @Override
  public String getUsername() {
    getInternal();
    return internal.getUsername();
  }

  @Override
  public String getPassword() {
    getInternal();
    return internal.getPassword();
  }

  @Override
  public String setPassword(String newPassword) {
    getInternal();
    return internal.setPassword(newPassword);
  }

  @Override
  public String getFullName() {
    getInternal();
    return internal.getFullName();
  }

  @Override
  public String setFullName(String newFullName) {
    getInternal();
    return internal.setFullName(newFullName);
  }

  @Override
  public String getQ1() {
    getInternal();
    return internal.getQ1();
  }

  @Override
  public String setQ1(String q1) {
    getInternal();
    return internal.setQ1(q1);
  }

  @Override
  public String getA1() {
    getInternal();
    return internal.getA1();
  }

  @Override
  public String setA1(String a1) {
    getInternal();
    return internal.setA1(a1);
  }

  @Override
  public String getQ2() {
    getInternal();
    return internal.getQ2();
  }

  @Override
  public String setQ2(String q2) {
    getInternal();
    return internal.setQ2(q2);
  }

  @Override
  public String getA2() {
    getInternal();
    return internal.getA2();
  }

  @Override
  public String setA2(String a2) {
    getInternal();
    return internal.setA2(a2);
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

  public static UserProxy byUserName(String username) throws SQLException {
    String query = "SELECT userID FROM users WHERE username=? LIMIT 1";
    Connection conn = Database.getConnection();
    try (PreparedStatement prep = conn.prepareStatement(query)) {
      prep.setString(1, username);
      try (ResultSet rs = prep.executeQuery()) {
        String userID = rs.getString(1);
        UserProxy user = new UserProxy(userID);
        return user;
      }
    }
  }
}
