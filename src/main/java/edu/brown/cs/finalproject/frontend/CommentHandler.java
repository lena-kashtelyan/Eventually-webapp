package edu.brown.cs.finalproject.frontend;

import spark.QueryParamsMap;
import spark.Request;
import spark.Response;
import spark.Route;
import edu.brown.cs.finalproject.database.DatabaseManager;

public class CommentHandler extends BackendInteraction implements Route {

  @Override
  public Object handle(Request req, Response res) {
    System.out.println("in attendhandler");
    QueryParamsMap qm = req.queryMap();
    String authString = qm.value("auth");
    String username = qm.value("username");
    String eventID = qm.value("eventID");
    String comment = qm.value("comment");
    return null;
//    return DatabaseManager.addAttendee(username, eventID);
  }
}
