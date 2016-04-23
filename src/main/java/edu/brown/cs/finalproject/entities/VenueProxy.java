package edu.brown.cs.finalproject.entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VenueProxy extends EntityProxy<Venue> implements Venue {

  public VenueProxy(String id) {
    // TODO Auto-generated constructor stub
    super(id);
  }

  @Override
  public String getName() {
    // TODO Auto-generated method stub
    getInternal();
    return internal.getName();
  }

  @Override
  public double getLat() {
    // TODO Auto-generated method stub
    getInternal();
    return internal.getLat();
  }

  @Override
  public double getLng() {
    // TODO Auto-generated method stub
    getInternal();
    return internal.getLng();
  }

  @Override
  public double setLat(double lat) {
    // TODO Auto-generated method stub
    getInternal();
    return internal.setLat(lat);
  }

  @Override
  public double setLng(double lng) {
    // TODO Auto-generated method stub
    getInternal();
    return internal.getLng();
  }

  @Override
  protected void pullFromDB(Connection conn) {
    // TODO Auto-generated method stub
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
