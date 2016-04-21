package edu.brown.cs.finalproject.frontend;

import edu.brown.cs.finalproject.credentials.Authenticator;

public class BackendInteraction {

  protected static Authenticator auth;

  protected BackendInteraction() {

  }

  public BackendInteraction(Authenticator authenticate) {
    auth = authenticate;
  }
}
