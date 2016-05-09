package edu.brown.cs.finalproject.frontend;

import java.util.ArrayList;
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
public class MyEventsView extends BackendInteraction implements TemplateViewRoute {

  private String htmlUrl;

  MyEventsView(String htmlUrl) {
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
    System.out.println("in myeventsview");
    QueryParamsMap qm = req.queryMap();
    String authString = qm.value("auth");
    String username = qm.value("username");

    System.out.println("auth: " + authString);
    System.out.println("username: " + username);

    if (authString != null) {
      AuthToken authToken = AuthToken.generateAuthToken(authString);
      if (auth.verifyAuthToken(username, authToken)) {
          //list of events marked "saved" and "attending" in chronological order (most immediate on top)
          List<Event> upcoming = new ArrayList<Event>();
          //list of past events marked "saved" and "attending" in reverse-chronological order (most recent on top)
          List<Event> past = new ArrayList<Event>();
          //list of suggested events, to be worked on later
          List<Event> suggested = new ArrayList<Event>();
        try {
          //fill the lists
            
          Map<Object, Object> data = ImmutableMap.builder()
              .put("title", "My Events").put("upcoming", upcoming)
              .put("past", past).put("suggested", suggested)
              .put("username", username).put("auth", authToken.toString()).build();
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