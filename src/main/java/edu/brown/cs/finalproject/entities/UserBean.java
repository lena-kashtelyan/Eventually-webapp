package edu.brown.cs.finalproject.entities;

public class UserBean extends EntityBean implements User {

  private String fullname;
  private String fbToken;
  private String usermediapath;

  public UserBean(String id, String fullname, String usermediapath,
      String fbToken) {
    super(id);
    this.fullname = fullname;
    this.usermediapath = usermediapath;
    this.fbToken = fbToken;
  }

  @Override
  public String getUserMediaPath() {
    return usermediapath;
  }

  @Override
  public String setUserMediaPath(String userMediaPath) {
    usermediapath = userMediaPath;
    return userMediaPath;
  }

  @Override
  public String getFullName() {
    return fullname;
  }

  @Override
  public String getFBtoken() {
    return fbToken;
  }

}
