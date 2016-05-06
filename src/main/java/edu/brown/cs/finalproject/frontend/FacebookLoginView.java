package edu.brown.cs.finalproject.frontend;

import spark.Request;
import spark.Response;
import spark.Route;

public class FacebookLoginView extends BackendInteraction implements Route {

  private static final String FACEBOOK_ID = "220099498366885";
  private static final String REDIRECT_URI = "http://b8c4a6b0.ngrok.io/fbr";

  @Override
  public Object handle(Request req, Response res) {
    res.redirect("https://www.facebook.com/dialog/oauth?client_id="
        + FACEBOOK_ID + "&redirect_uri=" + REDIRECT_URI + "&scope=email");
    return null;
  }

}
