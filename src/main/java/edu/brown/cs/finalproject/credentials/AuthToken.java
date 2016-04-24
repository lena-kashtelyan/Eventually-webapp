package edu.brown.cs.finalproject.credentials;

import java.util.Objects;
import java.util.UUID;

import com.stormpath.sdk.account.Account;

public class AuthToken {

  private String authToken;

  private AuthToken(Account account) {
    /*
     * Code that adds auth token to our database.
     */
    UUID token = UUID.randomUUID();
    String tokenString = token.toString();
    authToken = tokenString;
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
