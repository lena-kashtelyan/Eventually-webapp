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
    return id;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public double getLat() {
    return lat;
  }

  @Override
  public double getLng() {
	  return lng;
  }

  @Override
  public double setLat(double lat) {
    this.lat = lat;
    return lat;
  }

  @Override
  public double setLng(double lng) {
    this.lng = lng;
    return lng;
  }
  
}
