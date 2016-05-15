package edu.brown.cs.finalproject.credentials;

public class SignUp {
  private String firstName, lastName, username, email, password;

  public SignUp(String firstName, String lastName, String username,
      String email, String password) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.username = username;
    this.email = email;
    this.password = password;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getUsername() {
    return username;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }
}
