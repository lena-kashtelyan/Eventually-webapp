package edu.brown.cs.finalproject.frontend;

import java.util.List;
import java.util.Map;

import spark.ModelAndView;
import spark.QueryParamsMap;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;

import com.google.common.collect.ImmutableMap;

import edu.brown.cs.finalproject.credentials.AuthToken;
import edu.brown.cs.finalproject.database.DatabaseManager;
import edu.brown.cs.finalproject.entities.Event;
import edu.brown.cs.finalproject.entities.EventProxy;
import edu.brown.cs.finalproject.entities.Media;
import edu.brown.cs.finalproject.entities.User;

/**
 * Private visible class to handle the serving of the login ftl template.
 */
public class EventView extends BackendInteraction implements TemplateViewRoute {

  private String htmlUrl;

  EventView(String htmlUrl) {
    this.htmlUrl = htmlUrl;
  }

  /**
   * The handle method.
   * 
   * @param req
   *          The request object.
   * @param res
   *          The response object.
   */
  @Override
  public ModelAndView handle(Request req, Response res) {
    System.out.println("in eventview");
    QueryParamsMap qm = req.queryMap();
    String authString = qm.value("auth");
    String username = qm.value("username");
    String eventID = qm.value("eventID");
    System.out.println("auth: " + authString);
    System.out.println("username: " + username);
    System.out.println("event ID: " + eventID);
    // String eventID = req.params(":something");
    if (authString != null) {
      AuthToken authToken = AuthToken.generateAuthToken(authString);
      if (auth.verifyAuthToken(username, authToken)) {
        Event event;
        List<User> attendees;
        List<Media> storystream;
        try {
          event = new EventProxy(eventID);
          attendees = DatabaseManager.getAttendees(eventID);
          storystream = DatabaseManager.getMedia(eventID);
          System.out.println(storystream.size());
          
          boolean isSaved = DatabaseManager.checkInterested(eventID, username);
          boolean isAttending = DatabaseManager.checkAttending(eventID, username);
          
          Map<Object, Object> data = ImmutableMap.builder()
              .put("title", "Event").put("event", event)
              .put("eventID", eventID).put("stream", storystream)
              .put("saved", isSaved).put("attending", isAttending)
              .put("username", username).put("auth", authToken.toString())
              .put("attendees", attendees).build();
          return new ModelAndView(data, htmlUrl);
        } catch (ClassNotFoundException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
          Map<Object, Object> data = ImmutableMap.builder()
              .put("title", "Ooops").build();
          return new ModelAndView(data, "oops.ftl");
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