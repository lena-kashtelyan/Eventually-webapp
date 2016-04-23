package edu.brown.cs.finalproject.entities;

public interface Venue extends Entity {
  public String getID();
  
  public String getName();
   
  public double getLat();
  
  public double getLng();
  
  public double setLat(double lat);
  
  public double setLng(double lng);
}
