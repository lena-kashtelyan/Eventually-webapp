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
    String password;
    String firstname;
    String lastname;
    String email;
    String usermediapath;
    String fbToken;
    Connection conn = Database.getConnection();

    try (PreparedStatement prep = conn.prepareStatement(userquery)) {
      prep.setString(1, id);
      try (ResultSet rs = prep.executeQuery()) {
        password = rs.getString(2);
        firstname = rs.getString(3);
        lastname = rs.getString(4);
        email = rs.getString(5);
        usermediapath = rs.getString(6);
        fbToken = rs.getString(7);
      }
    } catch (NullPointerException | SQLException n) {
      System.out.println("ERROR: ID not in User Table");
      internal = null;
      return;
    }

    internal = new UserBean(id, password, email, firstname, lastname,
        usermediapath, fbToken);
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
  public String getEmail() {
    getInternal();
    return internal.getEmail();
  }

  @Override
  public String getFirstName() {
    getInternal();
    return internal.getFirstName();
  }

  @Override
  public String getLastName() {
    getInternal();
    return internal.getLastName();
  }

  @Override
  public String getFBtoken() {
    getInternal();
    return internal.getFBtoken();
  }

}
