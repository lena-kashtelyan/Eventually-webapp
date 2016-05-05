package edu.brown.cs.finalproject.frontend;

import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableMap;

import edu.brown.cs.finalproject.credentials.AuthToken;
import edu.brown.cs.finalproject.database.DatabaseManager;
import edu.brown.cs.finalproject.entities.Event;
import edu.brown.cs.finalproject.eventsSorter.EventsSorter;
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
    if (authString != null) {
      AuthToken authToken = AuthToken.generateAuthToken(authString);
      if (auth.verifyAuthToken(username, authToken)) {
        List<Event> events = DatabaseManager.getUpcomingEvents();
        
        //EventsSorter eventSorter = new EventsSorter();
//    	events = eventSorter.sortEventsByLocation(events, 41.826144690402, -71.403125740801);	// if we want to store based on location
    	//events = eventSorter.sortEventsByAttendingCount(events);	// if we want to store based on Attending Count
        
        
        /*
         * this will need to change once we get get the list
         * of events all at once!
         */
        System.out.println(events.size());
        Map<Object, Object> data = ImmutableMap.builder().put("title", "Browse")
            .put("events", events).put("auth", authToken.toString())
            .put("username", username).build();
        return new ModelAndView(data, "browse.ftl");
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
