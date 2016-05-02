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
public class AccountView extends BackendInteraction
    implements TemplateViewRoute {
  private String htmlUrl;

  AccountView(String htmlUrl) {
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
    if (authString != null) {
      AuthToken authToken = AuthToken.generateAuthToken(authString);
      if (auth.verifyAuthToken(username, authToken)) {
        Map<Object, Object> data = ImmutableMap.builder()
            .put("title", "Account").put("name", "John Jannotti")
            .put("username", username).put("auth", authToken.toString())
            .build();
        return new ModelAndView(data, htmlUrl);
      } else {
        // malicious user, redirect to login
        Map<Object, Object> data = ImmutableMap.builder().put("title", "Login")
            .build();
        return new ModelAndView(data, "login.ftl");
      }
    } else {
      // no authentification token, redirect to login
      Map<Object, Object> data = ImmutableMap.builder().put("title", "Login")
          .build();
      return new ModelAndView(data, "login.ftl");
    }
  }
}