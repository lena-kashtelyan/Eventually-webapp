package edu.brown.cs.finalproject.frontend;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;

/**
 * Private visible class to handle the serving of the login
 * ftl template.
 */
public class LoginView implements TemplateViewRoute {
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
    Map<String, Object> data = ImmutableMap.<String, Object> builder()
        .put("title", "Login").build();
    return new ModelAndView(data, htmlUrl);
  }
}
