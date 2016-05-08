package edu.brown.cs.finalproject.frontend;

import spark.QueryParamsMap;
import spark.Request;
import spark.Response;
import spark.Route;
import edu.brown.cs.finalproject.database.DatabaseManager;

public class MediaHandler extends BackendInteraction implements Route {

  @Override
  public Object handle(Request req, Response res) {
    // TODO Auto-generated method stub
    QueryParamsMap qm = req.queryMap();
    String url = qm.value("url");
    String eventID = qm.value("eventID");
    String username = qm.value("username");
    String timestamp = qm.value("timestamp");
    String type = qm.value("type");
    return DatabaseManager.addMedia(eventID, username, url, timestamp, type);
  }

}