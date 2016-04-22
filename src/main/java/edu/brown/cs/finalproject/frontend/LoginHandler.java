package edu.brown.cs.finalproject.frontend;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

import edu.brown.cs.finalproject.credentials.AuthToken;
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
    String usernameOrEmail = qm.value("usernameOrEmail");
    String rawPassword = qm.value("password");
    System.out.println(usernameOrEmail + " " + rawPassword);
    try {
      System.out.println("Before auth");
      AuthToken authToken = auth.authenticate(usernameOrEmail, rawPassword);
      if (auth.verifyAuthToken(authToken)) {
        Map<String, Object> data = ImmutableMap.<String, Object> builder()
            .put("title", "Map").put("auth", authToken)
            .put("redirect", "/map.ftl").build();
        return data;
      } else {
        throw new RuntimeException(
            "Oops! Something went wrong, please try again.");
      }
    } catch (RuntimeException e) {
      Map<String, Object> data = ImmutableMap.<String, Object> builder()
          .put("title", "Login").put("error", e.getLocalizedMessage())
          .put("redirect", "/login").build();
      return data;
    }
  }
}
