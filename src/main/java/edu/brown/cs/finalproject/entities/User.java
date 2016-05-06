package edu.brown.cs.finalproject.entities;

public interface User extends Entity {

  public String getFullName();
  
  public String getUserMediaPath();

  public String setUserMediaPath(String userMediaPath);
  
  public String getFBtoken();

}
