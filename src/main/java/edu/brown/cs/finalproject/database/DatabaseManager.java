package edu.brown.cs.finalproject.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.cartodb.CartoDBClientIF;
import com.cartodb.CartoDBException;
import com.cartodb.impl.ApiKeyCartoDBClient;
import com.cartodb.model.CartoDBResponse;

import edu.brown.cs.finalproject.entities.Event;
import edu.brown.cs.finalproject.entities.EventProxy;

public class DatabaseManager {

	public DatabaseManager() {
		// Empty Constructor for Now
	}

	public static boolean addInternalEvent(String Name, String originType,
			String creatorID, Timestamp startDate, double latitude,
			double longitude, boolean ispublic, String category,
			String description) {

		String eventID = UUID.randomUUID().toString();
		String query = String
				.format("INSERT INTO events VALUES (NULL, NULL, NULL, '%s', NULL, '%s', '%s', %f, %f, NULL, '%s', NULL, 'internal', %b, '%s', NULL);",
						creatorID, description, eventID, latitude, longitude,
						Name, ispublic, startDate.toString());
		try {
			CartoDBClientIF cartoDBCLient = new ApiKeyCartoDBClient(
					"cs32finalproject",
					"ad54038628d84dceb55a7adb81eddfcf9976e994");
			cartoDBCLient.request(query);
		} catch (CartoDBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

		Event newEvent;
		try {
			newEvent = new EventProxy(eventID);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static List<Event> getEvents() {
		List<Event> events = new ArrayList<>();
		try {
			String query = "Select eventid from events;";
			CartoDBClientIF cartoDBCLient = new ApiKeyCartoDBClient(
					"cs32finalproject",
					"ad54038628d84dceb55a7adb81eddfcf9976e994");
			CartoDBResponse<Map<String, Object>> res = cartoDBCLient
					.request(query);
			for (int j = 0; j < res.getTotal_rows(); j++) {
				String eventID = (String) res.getRows().get(j).get("eventid");
				Event event = new EventProxy(eventID);
				events.add(event);
			}
		} catch (CartoDBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return events;
	}
	
	public String addUser(String username, String userMediaPath, String fbAccessToken) {
		
	     Connection conn = Database.getConnection();
	     String query = "INSERT INTO users (username, userMediaPath, fbAccessToken) VALUES (?,?,?);";
	     
	     try (PreparedStatement prep = conn.prepareStatement(query)) {
	       prep.setString(1, username);
	       prep.setString(2, userMediaPath);
	       prep.setString(3, fbAccessToken);
	       prep.addBatch();
	       prep.executeBatch();
	       return username;
	     } catch (NullPointerException | SQLException n) {
	       n.printStackTrace();
	       return null;
	     }
	}
	
	public String getUsersFBAccessToken(String username) {
	     Connection conn = Database.getConnection();
	     String query = "SELECT fbAccessToken from users WHERE username=?;";
	     String fbAccessToken = null;
	     try (PreparedStatement prep = conn.prepareStatement(query)) {
	       prep.setString(1, username);
			try (ResultSet rs = prep.executeQuery()) {
				while (rs.next()) {
					fbAccessToken = rs.getString(1);
				}
			}
	       return fbAccessToken;
	     } catch (NullPointerException | SQLException n) {
	       n.printStackTrace();
	       return null;
	     }
	}
	
	public String setUsersFBAccessToken(String username, String newFBAccessToken) {
		Connection conn = Database.getConnection();
	     String query = "UPDATE users SET fbAccessToken=? WHERE username=?;";
	     
	     try (PreparedStatement prep = conn.prepareStatement(query)) {
	       prep.setString(1, newFBAccessToken);
	       prep.setString(2, username);
	       prep.addBatch();
	       prep.executeBatch();
	       return newFBAccessToken;
	     } catch (NullPointerException | SQLException n) {
	       n.printStackTrace();
	       return null;
	     }
	}
	
	public String getUsersMediaPath(String username) {
	     Connection conn = Database.getConnection();
	     String query = "SELECT userMediaPath from users WHERE username=?;";
	     String userMediaPath = null;
	     try (PreparedStatement prep = conn.prepareStatement(query)) {
	       prep.setString(1, username);
			try (ResultSet rs = prep.executeQuery()) {
				while (rs.next()) {
					userMediaPath = rs.getString(1);
				}
			}
	       return userMediaPath;
	     } catch (NullPointerException | SQLException n) {
	       n.printStackTrace();
	       return null;
	     }
	}
	
	public String setUsersMediaPath(String username, String newMediaPath) {
		Connection conn = Database.getConnection();
	     String query = "UPDATE users SET userMediaPath=? WHERE username=?;";
	     
	     try (PreparedStatement prep = conn.prepareStatement(query)) {
	       prep.setString(1, newMediaPath);
	       prep.setString(2, username);
	       prep.addBatch();
	       prep.executeBatch();
	       return newMediaPath;
	     } catch (NullPointerException | SQLException n) {
	       n.printStackTrace();
	       return null;
	     }
	}

}
