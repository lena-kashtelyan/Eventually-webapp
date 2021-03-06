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
        System.out.println(zoom);
        System.out.println(radius);
        facebookDataManager.requestPublicEvents(lat, lng, radius);
      } catch (Exception e) {
        System.out.println("ERROR: Problem fetching public facebook events.");
      }
    }
    System.out.println("Returning response.");
    return res;
  }

  private int zoomToRadius(int zoom) {
	  int radius = 1000;
	  if (zoom <= 16) {
		  radius = 2500;
	  }
	  
	  if (zoom <= 13) {
		  radius = 4000;
	  }
	  
	  if (zoom <= 9) {
		  radius = 1;
	  }
    return radius;
  }

}
