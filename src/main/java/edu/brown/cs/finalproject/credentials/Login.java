package edu.brown.cs.finalproject.credentials;

public class Login {

  private String usernameOrEmail;
  private String rawPassword;

  public Login(String userId, String password) {
    usernameOrEmail = userId;
    rawPassword = password;
  }

  public String getUsernameOrEmail() {
    return usernameOrEmail;
  }

  public String getRawPassword() {
    return rawPassword;
  }
}
