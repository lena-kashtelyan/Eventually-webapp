package edu.brown.cs.finalproject.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * A class to provide the connection to our local database
 *
 */
public final class Database {

  private static Connection conn;

  public Database(String db) throws SQLException, ClassNotFoundException {
    Class.forName("org.sqlite.JDBC");
    String urltoDB = "jdbc:sqlite:" + db;
    conn = DriverManager.getConnection(urltoDB);

    Statement stat = conn.createStatement();
    stat.executeUpdate("PRAGMA foreign_keys = ON;");
  }

  public static Connection getConnection() {
    return conn;
  }

  public void closeConn() throws SQLException {
    conn.close();
  }

  public interface Operation<T> {
    T executeWith(Connection c) throws SQLException;
  }

}
