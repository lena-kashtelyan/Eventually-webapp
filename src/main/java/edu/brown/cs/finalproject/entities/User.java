package edu.brown.cs.finalproject.entities;

public interface User extends Entity {

  public String getUsername();

  public String getPassword();

  public String setPassword(String newPassword);

  public String getFullName();

  public String setFullName(String newFullName);

  public String getQ1();

  public String setQ1(String q1);

  public String getA1();

  public String setA1(String a1);

  public String getQ2();

  public String setQ2(String q2);

  public String getA2();

  public String setA2(String a2);

  public String getUserMediaPath();

  public String setUserMediaPath(String userMediaPath);

}
