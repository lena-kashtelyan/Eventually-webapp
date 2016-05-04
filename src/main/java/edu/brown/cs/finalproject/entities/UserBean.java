package edu.brown.cs.finalproject.entities;

public class UserBean extends EntityBean implements User {

  private String password;
  private String firstName;
  private String lastName;
  private String email;
  private String fbToken;
  private String usermediapath;

  public UserBean(String id, String password, String email, String firstName, String lastName,
      String usermediapath, String fbToken) {
    super(id);
    // TODO Auto-generated constructor stub
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
    this.usermediapath = usermediapath;
    this.email = email;
    this.fbToken = fbToken;
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

  @Override
  public String getEmail() {
    // TODO Auto-generated method stub
    return email;
  }

  @Override
  public String getFirstName() {
    // TODO Auto-generated method stub
    return firstName;
  }

  @Override
  public String getLastName() {
    // TODO Auto-generated method stub
    return lastName;
  }

  @Override
  public String getFBtoken() {
    // TODO Auto-generated method stub
    return fbToken;
  }

}
