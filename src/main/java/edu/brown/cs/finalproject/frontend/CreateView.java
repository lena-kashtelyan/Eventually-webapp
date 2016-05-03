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
public class CreateView extends BackendInteraction
    implements TemplateViewRoute {
  private String htmlUrl;

  CreateView(String htmlUrl) {
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
    String username = qm.value("username");
    if (authString != null && username != null) {
        System.out.println("in createview, no nulls");
      AuthToken authToken = AuthToken.generateAuthToken(authString);
      if (auth.verifyAuthToken(username, authToken)) {
          System.out.println("in createview, verified auth");
        Map<Object, Object> data = ImmutableMap.builder().put("title", "Browse")
            .put("auth", authToken.toString()).put("username", username)
            .build();
        return new ModelAndView(data, htmlUrl);
      } else {
        // malicious user, redirect to login
          System.out.println("in createview, failed to verify token");
        Map<Object, Object> data = ImmutableMap.builder().put("title", "Login")
            .build();
        return new ModelAndView(data, "login.ftl");
      }
    } else {
      // no authentification token, redirect to login
        System.out.println("in createview, no auth or username");
      Map<Object, Object> data = ImmutableMap.builder().put("title", "Login")
          .build();
      return new ModelAndView(data, "login.ftl");
    }
  }
}
