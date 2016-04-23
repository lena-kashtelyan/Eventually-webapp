package edu.brown.cs.finalproject.entities;

public class VenueBean implements Venue {
  String id;
  String name;
  double lat;
  double lng;
  
  public VenueBean(String id, String name, double lat, double lng) {
    this.id = id;
    this.name = name;
    this.lat = lat;
    this.lng = lng;
  }

  @Override
  public String getID() {
    // TODO Auto-generated method stub
    return id;
  }

  @Override
  public String getName() {
    // TODO Auto-generated method stub
    return name;
  }

  @Override
  public double getLat() {
    // TODO Auto-generated method stub
    return lat;
  }

  @Override
  public double getLng() {
    // TODO Auto-generated method stub
    return lng;
  }

  @Override
  public double setLat(double lat) {
    // TODO Auto-generated method stub
    this.lat = lat;
    return lat;
  }

  @Override
  public double setLng(double lng) {
    // TODO Auto-generated method stub
    this.lng = lng;
    return lng;
  }
  
}
