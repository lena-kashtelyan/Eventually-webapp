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
    String userEmail = qm.value("userEmail");
    Forgot forgot = new Forgot(userEmail);
//    Login login = new Login(usernameOrEmail, rawPassword);
//    try {
//      AuthToken authToken = auth.authenticate(login);
//      if (auth.verifyAuthToken(authToken)) {
//        Map<String, Object> data = ImmutableMap.<String, Object> builder()
//            .put("title", "Map").put("auth", authToken.toString())
//            .put("redirect", "/map.ftl").build();
//        return GSON.toJson(data);
//      } else {
//        throw new RuntimeException(
//            "Oops! Something went wrong, please try again.");
//      }
//    } catch (RuntimeException e) {
      Map<String, Object> data = ImmutableMap.<String, Object> builder()
          .put("title", "Login").put("error", null)
          .put("redirect", "/login").build();
      return GSON.toJson(data);
 //   }
  }
}
