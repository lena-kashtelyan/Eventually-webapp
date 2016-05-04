package edu.brown.cs.finalproject.entities;

public interface User extends Entity {

  public String getPassword();

  public String setPassword(String newPassword);
  
  public String getEmail();

  public String getFirstName();

  public String getLastName();

  public String getUserMediaPath();

  public String setUserMediaPath(String userMediaPath);
  
  public String getFBtoken();

}
