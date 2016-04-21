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
    Map<String, Object> titleMap = ImmutableMap.of("title",
        "CS32: Final Project");

    QueryParamsMap qm = req.queryMap();
    String authString = qm.value("auth");
    if (authString != null) {
      AuthToken authToken = AuthToken.generateAuthToken(authString);
      if (auth.verifyAuthToken(authToken)) {
        return new ModelAndView(titleMap, "map.ftl");
      } else {
        return new ModelAndView(titleMap, "login.ftl");
      }
    } else {
      return new ModelAndView(titleMap, "login.ftl");
    }
  }
}
