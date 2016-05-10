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
    System.out.println(eventID);
    String username = qm.value("username");
    String timestamp = qm.value("timestamp");
    String type = qm.value("type");
    if (type.equals("image")) {
      url = "http://res.cloudinary.com/df1bylm3l/image/upload/" + url;
    }
    System.out
        .format(
            "In mediahandler, adding! Type: %s, url: %s, eventID: %s, username: %s, timestamp: %s",
            type, url, eventID, username, timestamp);
    return DatabaseManager.addMedia(eventID, username, url, timestamp, type);
  }

}
