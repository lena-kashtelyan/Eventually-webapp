package edu.brown.cs.finalproject.credentials;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ForgotPassword {

  public static boolean checkUserName(String username, Connection conn) {
    String query = "SELECT COUNT(username) FROM users WHERE username=?";
    try (PreparedStatement prep = conn.prepareStatement(query)) {
      prep.setString(1, username);
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

  public static String[] getQuestions(String username, Connection conn) {
    if (checkUserName(username, conn)) {
      String query = "SELECT q1,q2 FROM users WHERE username=?";
      try (PreparedStatement prep = conn.prepareStatement(query)) {
        prep.setString(1, username);
        try (ResultSet rs = prep.executeQuery()) {
          String q1 = rs.getString(1);
          String q2 = rs.getString(2);
          String[] answers = new String[] { q1, q2 };
          return answers;
        }
      } catch (NullPointerException | SQLException n) {
        n.printStackTrace();
        return null;
      }
    } else {
      System.out.println("username not in database");
      return null;
    }
  }

  public static boolean checkAnswers(String[] answers, String username,
      Connection conn) {
    if (checkUserName(username, conn)) {
      String query = "SELECT a1,a2 FROM users WHERE username=?";
      try (PreparedStatement prep = conn.prepareStatement(query)) {
        prep.setString(1, username);
        try (ResultSet rs = prep.executeQuery()) {
          String a1 = rs.getString(1);
          String a2 = rs.getString(2);
          if (answers[0].equals(a1) && answers[1].equals(a2)) {
            return true;
          } else {
            return false;
          }
        }
      } catch (NullPointerException | SQLException n) {
        n.printStackTrace();
        return false;
      }
    } else {
      System.out.println("username not in database");
      return false;
    }
  }

}
