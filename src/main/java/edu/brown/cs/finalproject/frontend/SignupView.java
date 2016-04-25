package edu.brown.cs.finalproject.frontend;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

import edu.brown.cs.finalproject.credentials.AuthToken;
import spark.ModelAndView;
import spark.QueryParamsMap;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;

public class SignupView extends BackendInteraction
    implements TemplateViewRoute {

  private String htmlUrl;

  SignupView(String htmlUrl) {
    this.htmlUrl = htmlUrl;
  }

  @Override
  public ModelAndView handle(Request req, Response res) {
    QueryParamsMap qm = req.queryMap();
    String authString = qm.value("auth");
    String errorString = qm.value("error");
    if (authString != null) {
      AuthToken authToken = AuthToken.generateAuthToken(authString);
      if (auth.verifyAuthToken(authToken)) {
        Map<Object, Object> data = ImmutableMap.builder()
            .put("alert",
                "You have already signed up. Please log out to log into another account.")
            .put("auth", authToken.toString()).build();
        return new ModelAndView(data, "map.ftl");
      } else {
        if (errorString == null) {
          Map<Object, Object> data = ImmutableMap.builder()
              .put("title", "Signup").build();
          return new ModelAndView(data, htmlUrl);
        } else {
          Map<Object, Object> data = ImmutableMap.builder()
              .put("title", "Signup").put("error", errorString).build();
          return new ModelAndView(data, htmlUrl);
        }
      }
    } else {
      if (errorString == null) {
        Map<Object, Object> data = ImmutableMap.builder().put("title", "Signup")
            .build();
        return new ModelAndView(data, htmlUrl);
      } else {
        Map<Object, Object> data = ImmutableMap.builder().put("title", "Signup")
            .put("error", errorString).build();
        return new ModelAndView(data, htmlUrl);
      }
    }
  }

}
