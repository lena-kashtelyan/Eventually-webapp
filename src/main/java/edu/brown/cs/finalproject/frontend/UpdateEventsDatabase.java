package edu.brown.cs.finalproject.frontend;

import spark.QueryParamsMap;
import spark.Request;
import spark.Response;
import spark.Route;

/**
 * Private class to handle the serving of the map ftl
 * template.
 */
public class UpdateEventsDatabase extends BackendInteraction implements Route {

  /**
   * The handle method.
   * @param req
   *          The request object.
   * @param res
   *          The response object.
   */
  @Override
  public Object handle(Request req, Response res) {
    System.out.println("in update");
    QueryParamsMap qm = req.queryMap();
    String latStr = qm.value("latitude");
    String lngStr = qm.value("longitude");
    String zoomStr = qm.value("zoom");
    if (latStr != null && lngStr != null && zoomStr != null) {
      try {
        double lat = Double.parseDouble(latStr);
        double lng = Double.parseDouble(lngStr);
        int zoom = Integer.parseInt(zoomStr);
        int radius = zoomToRadius(zoom);
        facebookDataManager.requestPublicEvents(lat, lng, radius);
      } catch (Exception e) {
        System.out.println("ERROR: Problem fetching public facebook events.");
      }
    }
    return res;
  }

  private int zoomToRadius(int zoom) {
    return 500;
  }

}
