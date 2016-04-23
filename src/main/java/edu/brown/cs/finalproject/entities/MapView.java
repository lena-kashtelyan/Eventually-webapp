package edu.brown.cs.finalproject.entities;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

import edu.brown.cs.finalproject.credentials.AuthToken;
import edu.brown.cs.finalproject.frontend.BackendInteraction;
import spark.ModelAndView;
import spark.QueryParamsMap;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;

public class MapView extends BackendInteraction implements TemplateViewRoute {

  @Override
  public ModelAndView handle(Request req, Response res) {
    QueryParamsMap qm = req.queryMap();
    String authString = qm.value("auth");
    if (authString != null) {
      AuthToken authToken = AuthToken.generateAuthToken(authString);
      if (auth.verifyAuthToken(authToken)) {
        Map<Object, Object> data = ImmutableMap.builder().put("title", "Map")
            .put("auth", authToken).build();
        return new ModelAndView(data, "map.ftl");
      } else {
        Map<String, String> data = ImmutableMap.of("title", "Map");
        return new ModelAndView(data, "map.ftl");
      }
    } else {
      Map<String, String> data = ImmutableMap.of("title", "Map");
      return new ModelAndView(data, "map.ftl");
    }
  }
}
