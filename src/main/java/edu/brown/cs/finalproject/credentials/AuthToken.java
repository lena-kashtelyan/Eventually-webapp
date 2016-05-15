package edu.brown.cs.finalproject.credentials;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.UUID;

import com.stormpath.sdk.account.Account;

public class AuthToken {

  private static Map<String, AuthToken> users = new HashMap<>();

  private String authToken;

  private AuthToken(Account account) {
    /*
     * Code that adds auth token to our database. Actually,
     * just using an in-memory hashmap.
     */
    String username = account.getUsername();
    UUID token = UUID.randomUUID();
    String tokenString = token.toString();
    authToken = tokenString;
    users.put(username, this);
  }

  private AuthToken(String authStr) {
    authToken = authStr;
  }

  public boolean verify(String username) {
    System.out.println(users);
    if (!users.containsKey(username))
      return false;
    if (!this.equals(users.get(username)))
      return false;
    return true;
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

  public static String getUsername(AuthToken token) {
    for (Entry<String, AuthToken> entry : users.entrySet()) {
      if (entry.getValue().equals(token)) {
        return entry.getKey();
      }
    }
    throw new RuntimeException("Username not found.");
  }
}
