package edu.brown.cs.finalproject.frontend;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cartodb.CartoDBClientIF;
import com.cartodb.CartoDBException;
import com.cartodb.impl.ApiKeyCartoDBClient;
import com.google.common.collect.ImmutableMap;

import edu.brown.cs.finalproject.credentials.AuthToken;
import edu.brown.cs.finalproject.database.BrowseResultsHolder;
import edu.brown.cs.finalproject.database.DatabaseManager;
import edu.brown.cs.finalproject.entities.Event;
import spark.ModelAndView;
import spark.QueryParamsMap;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;

/**
 * Private visible class to handle the serving of the login
 * ftl template.
 */
public class BrowseView extends BackendInteraction
    implements TemplateViewRoute {
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
    String location = qm.value("location");
    String sliderValue = qm.value("radius");
    String floorTime = qm.value("floorTime");
    String ceilingTime = qm.value("ceilingTime");
    String byProximity = qm.value("byProximity");
    String byPopularity = qm.value("byPopularity");

    if (authString != null) {
      AuthToken authToken = AuthToken.generateAuthToken(authString);
      if (auth.verifyAuthToken(username, authToken)) {

        // if location is null, then we present the default
        // list of events
        if (location == null) {
          BrowseResultsHolder browseResults = DatabaseManager
              .getUpcomingEventsWithinProximitySortedByPopularity(
                  41.826144690402, -71.403125740801, 1500, 30, username);

          List<Event> events = browseResults.getEvents();
          HashMap<String, Boolean> userSavedEvents = browseResults
              .getUserSavedEvents();
          HashMap<String, Boolean> userAttendingEvents = browseResults
              .getUserAttendingEvents();

          Map<Object, Object> data = ImmutableMap.builder()
              .put("title", "Browse").put("events", events)
              .put("auth", authToken.toString()).put("username", username)
              .put("userSavedEvents", userSavedEvents)
              .put("userAttendingEvents", userAttendingEvents).build();
          return new ModelAndView(data, "browse.ftl");
        } else {
          double radius = Double.valueOf(sliderValue) * 1609.34;
          String query = String.format("SELECT cdb_geocode_street_point('%s');",
              location);

          String geomPoint = new String(
              "0101000020E61000009B45DE28E8D851C05F0CE544BBEA4440");
          try {
            CartoDBClientIF cartoDBCLient = new ApiKeyCartoDBClient(
                "cs32finalproject", "ad54038628d84dceb55a7adb81eddfcf9976e994");
            geomPoint = cartoDBCLient.request(query).getRows().get(0)
                .toString();
            geomPoint = geomPoint.substring(geomPoint.indexOf("=") + 1,
                geomPoint.length() - 1);
          } catch (CartoDBException e) {
            e.printStackTrace();
          }

          query = String.format("SELECT ST_AsLatLonText('%s', 'D.DDDDDDDD ')",
              geomPoint);

          double latitude = 41.825772;
          double longitude = -71.403278;
          try {
            CartoDBClientIF cartoDBCLient = new ApiKeyCartoDBClient(
                "cs32finalproject", "ad54038628d84dceb55a7adb81eddfcf9976e994");
            geomPoint = cartoDBCLient.request(query).getRows().get(0)
                .toString();
            int latStart = geomPoint.indexOf("=") + 1;
            int latEnd = geomPoint.indexOf(" ");
            try {
              latitude = Double
                  .parseDouble(geomPoint.substring(latStart, latEnd));
            } catch (NumberFormatException e) {
              e.printStackTrace();
            }

            geomPoint = geomPoint.substring(latEnd + 2);
            int lngEnd = geomPoint.indexOf(" ");
            try {
              longitude = Double.parseDouble(geomPoint.substring(0, lngEnd));
            } catch (NumberFormatException e) {
              e.printStackTrace();
            }
          } catch (CartoDBException e) {
            e.printStackTrace();
          }
          System.out.println(floorTime);
          System.out.println(ceilingTime);
          System.out.println(latitude);
          System.out.println(longitude);
          System.out.println(radius);
          System.out.println(username);
          
          facebookDataManager.requestPublicEvents(latitude, longitude, (int) radius);

          BrowseResultsHolder browseResults = null;
          if (byProximity.equals("false")) {
            browseResults = DatabaseManager.filterOrderByPopularity(floorTime,
                ceilingTime, latitude, longitude, radius, username);
          } else {
            browseResults = DatabaseManager.filterOrderByLocation(floorTime,
                ceilingTime, latitude, longitude, radius, username);
          }

          List<Event> events = browseResults.getEvents();
          HashMap<String, Boolean> userSavedEvents = browseResults
              .getUserSavedEvents();
          HashMap<String, Boolean> userAttendingEvents = browseResults
              .getUserAttendingEvents();

          Map<Object, Object> data = ImmutableMap.builder()
              .put("title", "Browse").put("events", events)
              .put("auth", authToken.toString()).put("username", username)
              .put("userSavedEvents", userSavedEvents)
              .put("userAttendingEvents", userAttendingEvents).build();

          return new ModelAndView(data, "browse.ftl");

        }

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
