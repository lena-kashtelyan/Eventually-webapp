package edu.brown.cs.finalproject.frontend;

import spark.QueryParamsMap;
import spark.Request;
import spark.Response;
import spark.Route;

public class CreateHandler extends BackendInteraction implements Route {

  @Override
  public Object handle(Request req, Response res) {
    // TODO Auto-generated method stub
    QueryParamsMap qm = req.queryMap();
    String type;
    String eventName = qm.value("eventName");
    String date = qm.value("date");
    String time = qm.value("time");
    String location = qm.value("location");
    String category = qm.value("category");
    String facebook = qm.value("facebookAdd");
    if (facebook.equals("yes")) {
      type = "facebook";
    } else {
      type = "internal";
    }
    
    return null;
  }

}
