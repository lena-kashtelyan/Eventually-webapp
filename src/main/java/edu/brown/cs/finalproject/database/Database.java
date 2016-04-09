package edu.brown.cs.finalproject.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

	private static String URL;

	public Database(String pathToDB) {
		URL = "jdbc:sqlite:" + pathToDB;
	}

	static {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException cnfe) {
			System.out.println("ERROR: Class not found");
			System.exit(1);
		}
	}

	public Connection getConnection() {
		try {
			Connection conn = DriverManager.getConnection(URL);
			Statement stmt = conn.createStatement();

			stmt.executeUpdate("PRAGMA foreign_keys = ON;");
			return conn;
		} catch (SQLException se) {
			throw new RuntimeException(se);
		}
	}
	
	public String toString() {
		return URL;
	}

	public interface Operation<T> {
		T executeWith(Connection c) throws SQLException;
	}
}

