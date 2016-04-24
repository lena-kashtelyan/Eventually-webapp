package edu.brown.cs.finalproject.entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VenueProxy extends EntityProxy<Venue> implements Venue {

  public VenueProxy(String id) {
    super(id);
  }

  @Override
  public String getName() {
    getInternal();
    return internal.getName();
  }

  @Override
  public double getLat() {
    getInternal();
    return internal.getLat();
  }

  @Override
  public double getLng() {
    getInternal();
    return internal.getLng();
  }

  @Override
  public double setLat(double lat) {
    getInternal();
    return internal.setLat(lat);
  }

  @Override
  public double setLng(double lng) {
    getInternal();
    return internal.getLng();
  }

  @Override
  protected void pullFromDB(Connection conn) {
    String query = "SELECT * FROM venue WHERE venueID=?;";
    String name;
    double lat;
    double lng;

    try (PreparedStatement prep = conn.prepareStatement(query)) {
      prep.setString(1, id);
      try (ResultSet rs = prep.executeQuery()) {
        name = rs.getString(2);
        lat = rs.getDouble(3);
        lng = rs.getDouble(4);
      }
    } catch (NullPointerException | SQLException n) {
      System.out.println("ERROR: ID not in User Table");
      internal = null;
      return;
    }

    internal = new VenueBean(id, name, lat, lng);
  }

}
