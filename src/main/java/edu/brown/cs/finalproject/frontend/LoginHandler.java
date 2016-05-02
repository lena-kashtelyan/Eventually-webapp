package edu.brown.cs.finalproject.frontend;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

import edu.brown.cs.finalproject.credentials.AuthToken;
import edu.brown.cs.finalproject.credentials.Login;
import spark.QueryParamsMap;
import spark.Request;
import spark.Response;
import spark.Route;

public class LoginHandler extends BackendInteraction implements Route {
  /**
   * The handle method.
   * @param req
   *          The request object.
   * @param res
   *          The response object.
   */
  @Override
  public Object handle(Request req, Response res) {
    QueryParamsMap qm = req.queryMap();
    // ACTUALLY ONLY USERNAME CAN BE USED!
    // DO NOT SUGGEST ON THE FRONT END THAT THE USER CAN
    // LOG IN WITH THEIR EMAIL. THIS SHOULD BE CHANGED
    // BUT I AM NOT WORRYING ABOUT IT FOR THE DEMO.
    String usernameOrEmail = qm.value("usernameOrEmail");
    String rawPassword = qm.value("password");
    Login login = new Login(usernameOrEmail, rawPassword);
    try {
      AuthToken authToken = auth.authenticate(login);
      System.out.println(auth.verifyAuthToken(usernameOrEmail, authToken));
      if (auth.verifyAuthToken(usernameOrEmail, authToken)) {
        Map<String, Object> data = ImmutableMap.<String, Object> builder()
            .put("title", "Map").put("auth", authToken.toString())
            .put("username", usernameOrEmail).put("redirect", "/map.ftl")
            .build();
        return GSON.toJson(data);
      } else {
        throw new RuntimeException(
            "Oops! Something went wrong, please try again.");
      }
    } catch (RuntimeException e) {
      e.printStackTrace();
      Map<String, Object> data = ImmutableMap.<String, Object> builder()
          .put("title", "Login").put("error", e.getLocalizedMessage())
          .put("redirect", "/login").build();
      return GSON.toJson(data);
    }
  }
}
