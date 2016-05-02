package edu.brown.cs.finalproject.frontend;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

import edu.brown.cs.finalproject.credentials.AuthToken;
import edu.brown.cs.finalproject.credentials.Forgot;
import spark.QueryParamsMap;
import spark.Request;
import spark.Response;
import spark.Route;

public class ForgotHandler extends BackendInteraction implements Route {
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
    String userEmail = qm.value("email");
    Forgot forgot = new Forgot(userEmail);
    if (userEmail != null) {
      AuthToken authToken = auth.forgotPassword(forgot);
      String username = AuthToken.getUsername(authToken);
      if (auth.verifyAuthToken(username, authToken)) {
        Map<String, Object> data = ImmutableMap.<String, Object> builder()
            .put("title", "Map").put("auth", authToken.toString())
            .put("username", username).put("redirect", "/map.ftl").build();
        return GSON.toJson(data);
      } else {
        throw new RuntimeException(
            "Oops! Something went wrong, please try again.");
      }
    } else {
      Map<String, Object> data = ImmutableMap.<String, Object> builder()
          .put("title", "Forgot Password").put("redirect", "/forgot").build();
      return GSON.toJson(data);
    }
  }
}
