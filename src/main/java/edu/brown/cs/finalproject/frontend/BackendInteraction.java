package edu.brown.cs.finalproject.frontend;

import com.google.gson.Gson;

import edu.brown.cs.finalproject.credentials.Authenticator;

public class BackendInteraction {

  protected static Authenticator auth;

  protected final static Gson GSON = new Gson();

  protected BackendInteraction() {

  }

  public BackendInteraction(Authenticator authenticate) {
    auth = authenticate;
  }
}
