package edu.brown.cs.finalproject.entities;

public class UserBean extends EntityBean implements User {

  private String fullname;
  private String fbToken;
  private String usermediapath;

  public UserBean(String id, String fullname,
      String usermediapath, String fbToken) {
    super(id);
    // TODO Auto-generated constructor stub
    this.fullname = fullname;
    this.usermediapath = usermediapath;
    this.fbToken = fbToken;
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
  public String getFullName() {
    // TODO Auto-generated method stub
    return fullname;
  }

  @Override
  public String getFBtoken() {
    // TODO Auto-generated method stub
    return fbToken;
  }

}
