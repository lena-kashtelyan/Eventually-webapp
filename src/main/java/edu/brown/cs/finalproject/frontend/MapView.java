package edu.brown.cs.finalproject.frontend;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;

/**
 * Private class to handle the serving of the map ftl
 * template.
 */
public class MapView implements TemplateViewRoute {

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
        .put("title", "Map").build();
    return new ModelAndView(data, "map.ftl");
  }

}
