package edu.brown.cs.finalproject.frontend;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

import edu.brown.cs.finalproject.credentials.AuthToken;
import spark.ModelAndView;
import spark.QueryParamsMap;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;

/**
 * Private visible class to handle the serving of the login
 * ftl template.
 */
public class LoginView extends BackendInteraction implements TemplateViewRoute {
  private String htmlUrl;

  LoginView(String htmlUrl) {
    this.htmlUrl = htmlUrl;
  }

  /**
   * The handle method.
   * @param req
   *          The request object.
   * @param res
   *          The response object.
   */
  @Override
  public ModelAndView handle(Request req, Response res) {
    QueryParamsMap qm = req.queryMap();
    String authString = qm.value("auth");
    if (authString != null) {
      AuthToken authToken = AuthToken.generateAuthToken(authString);
      if (auth.verifyAuthToken(authToken)) {
        Map<Object, Object> data = ImmutableMap.builder()
            .put("alert",
                "You are already logged in. Please log out to log into another account.")
            .put("auth", authToken).build();
        return new ModelAndView(data, "map.ftl");
      } else {
        Map<String, Object> data = ImmutableMap.<String, Object> builder()
            .put("title", "Login").build();
        return new ModelAndView(data, htmlUrl);
      }
    } else {
      Map<String, Object> data = ImmutableMap.<String, Object> builder()
          .put("title", "Login").build();
      return new ModelAndView(data, htmlUrl);
    }
  }
}
