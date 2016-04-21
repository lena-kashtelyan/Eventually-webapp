package edu.brown.cs.finalproject.credentials;

import java.util.Objects;

import com.stormpath.sdk.account.Account;

public class AuthToken {

  private String authToken;

  private AuthToken(Account account) {
    authToken = "testString";
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

  static AuthToken generateAuthToken(Account account) {
    return new AuthToken(account);
  }
}
