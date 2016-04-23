package edu.brown.cs.finalproject.credentials;

import java.util.Objects;

import com.stormpath.sdk.account.Account;

public class AuthToken {

  private String authToken;

  private AuthToken(Account account) {
    authToken = "testString";
  }

  private AuthToken(String authStr) {
    authToken = authStr;
  }

  @Override
  public boolean equals(Object o) {
    try {
      AuthToken a = (AuthToken) o;
      return a.authToken.equals(authToken);
    } catch (ClassCastException e) {
      return false;
    }
  }

  @Override
  public int hashCode() {
    return Objects.hash(authToken);
  }

  @Override
  public String toString() {
    return authToken;
  }

  public static AuthToken generateAuthToken(Account account) {
    return new AuthToken(account);
  }

  public static AuthToken generateAuthToken(String authStr) {
    return new AuthToken(authStr);
  }
}
