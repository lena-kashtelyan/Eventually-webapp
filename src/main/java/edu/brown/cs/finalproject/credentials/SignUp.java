package edu.brown.cs.finalproject.credentials;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import edu.brown.cs.finalproject.database.Database;

public class SignUp {

  public static boolean isUnique(String username) {
    Connection conn = Database.getConnection();
    String query = "SELECT COUNT(username) FROM users WHERE username=?";
    try (PreparedStatement prep = conn.prepareStatement(query)) {
      prep.setString(1, username);
      try (ResultSet rs = prep.executeQuery()) {
        int count = rs.getInt(1);
        if (count == 0) {
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

  /**
   * @param fields
   *          - array of fields for new user ordered by the SQL table
   * @param conn
   *          - connection to database
   * @return true if user was added, false if not
   */
  public static boolean addUser(String[] fields) {
    if (fields.length != 8) {
      System.out.println("fields must contain exactly 8 values");
      return false;
    }

    Connection conn = Database.getConnection();

    if (isUnique(fields[0])) {
      String query = "INSERT INTO users VALUES (?,?,?,?,?,?,?,?,?)";
      try (PreparedStatement prep = conn.prepareStatement(query)) {
        prep.setString(1, UUID.randomUUID().toString());
        for (int i = 0; i < fields.length; i++) {
          prep.setString(i + 2, fields[i]);
        }
        prep.addBatch();
        prep.executeBatch();
        return true;
      } catch (NullPointerException | SQLException n) {
        n.printStackTrace();
        return false;
      }
    } else {
      return false;
    }
  }

}
