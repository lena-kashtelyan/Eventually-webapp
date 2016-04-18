package edu.brown.cs.finalproject.credentials;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {

  public static boolean validLogin(String username, String password,
      Connection conn) {
    String query = "SELECT COUNT(username) FROM users WHERE username=? AND password=?";
    try (PreparedStatement prep = conn.prepareStatement(query)) {
      prep.setString(1, username);
      prep.setString(2, password);
      try (ResultSet rs = prep.executeQuery()) {
        int count = rs.getInt(1);
        if (count != 0) {
          return true;
        } else {
          return false;
        }
      }
    } catch (NullPointerException | SQLException n) {
      n.printStackTrace();
      return false;
    }
  }
}
