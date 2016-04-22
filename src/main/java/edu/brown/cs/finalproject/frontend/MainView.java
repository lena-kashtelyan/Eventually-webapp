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
 * Private class to handle the serving of the main ftl
 * template.
 */
public class MainView extends BackendInteraction implements TemplateViewRoute {
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
            .put("title", "CS32: Final Project")
            .put("auth", authToken.toString()).build();
        return new ModelAndView(data, "map.ftl");
      } else {
        Map<String, Object> data = ImmutableMap.of("title",
            "CS32: Final Project");
        return new ModelAndView(data, "login.ftl");
      }
    } else {
      Map<String, Object> data = ImmutableMap.of("title",
          "CS32: Final Project");
      return new ModelAndView(data, "login.ftl");
    }
  }
}
