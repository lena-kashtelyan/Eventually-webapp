package edu.brown.cs.finalproject.entities;

public class UserBean extends EntityBean implements User {
  private String username;
  private String password;
  private String fullname;
  private String q1;
  private String a1;
  private String q2;
  private String a2;
  private String usermediapath;

  public UserBean(String id, String username, String password, String fullname,
      String q1, String a1, String q2, String a2, String usermediapath) {
    super(id);
    // TODO Auto-generated constructor stub
    this.username = username;
    this.password = password;
    this.fullname = fullname;
    this.q1 = q1;
    this.a1 = a1;
    this.q2 = q2;
    this.a2 = a2;
    this.usermediapath = usermediapath;
  }

  @Override
  public String getUsername() {
    // TODO Auto-generated method stub
    return username;
  }

  @Override
  public String getPassword() {
    // TODO Auto-generated method stub
    return password;
  }

  @Override
  public String setPassword(String newPassword) {
    // TODO Auto-generated method stub
    password = newPassword;
    return password;
  }

  @Override
  public String getFullName() {
    // TODO Auto-generated method stub
    return fullname;
  }

  @Override
  public String setFullName(String newFullName) {
    // TODO Auto-generated method stub
    fullname = newFullName;
    return fullname;
  }

  @Override
  public String getQ1() {
    // TODO Auto-generated method stub
    return q1;
  }

  @Override
  public String setQ1(String q1) {
    // TODO Auto-generated method stub
    this.q1 = q1;
    return q1;
  }

  @Override
  public String getA1() {
    // TODO Auto-generated method stub
    return a1;
  }

  @Override
  public String setA1(String a1) {
    // TODO Auto-generated method stub
    this.a1 = a1;
    return a1;
  }

  @Override
  public String getQ2() {
    // TODO Auto-generated method stub
    return q2;
  }

  @Override
  public String setQ2(String q2) {
    // TODO Auto-generated method stub
    this.q2 = q2;
    return q2;
  }

  @Override
  public String getA2() {
    // TODO Auto-generated method stub
    return a2;
  }

  @Override
  public String setA2(String a2) {
    // TODO Auto-generated method stub
    this.a2 = a2;
    return a2;
  }

  @Override
  public String getUserMediaPath() {
    // TODO Auto-generated method stub
    return usermediapath;
  }

  @Override
  public String setUserMediaPath(String userMediaPath) {
    // TODO Auto-generated method stub
    usermediapath = userMediaPath;
    return userMediaPath;
  }

}
