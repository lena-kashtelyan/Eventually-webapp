package edu.brown.cs.finalproject.frontend;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

import com.google.common.collect.ImmutableMap;

import edu.brown.cs.finalproject.database.DatabaseManager;
import spark.QueryParamsMap;
import spark.Request;
import spark.Response;
import spark.Route;

public class CreateHandler extends BackendInteraction implements Route {

  @Override
  public Object handle(Request req, Response res) {
    // TODO Auto-generated method stub
    System.out.println("in create handler");
    QueryParamsMap qm = req.queryMap();
    System.out.println("here");
    String creatorID = qm.value("username");
    String eventName = qm.value("eventName");
    String date = qm.value("date");
    String time = qm.value("time");
    System.out.println(date);
    System.out.println(time);
    String description = qm.value("description");
    System.out.println(description);
    String[] dates = date.split("/");
    String[] times = time.split(":");
    String datetime = String.format("'%s-%s-%s %s:%s:00'", dates[2], dates[0], dates[1], times[0], times[1]);
    String timestamp = String.format("to_timestamp(%s,'YYYY-MM-dd HH24:MI:SS')", datetime);
    double lat = Double.parseDouble(qm.value("lat"));
    double lng = Double.parseDouble(qm.value("lng"));
    String facebookAdd = qm.value("facebookAdd");
    System.out.println(facebookAdd);
    
    String category = qm.value("category");
    
    boolean result = DatabaseManager.addEvent(eventName, creatorID, timestamp, lat, lng, category, description, facebookAdd);
    if (result) {
      Map<String, Object> data = ImmutableMap.<String, Object> builder()
          .put("redirect", "/map.ftl").build();
      return GSON.toJson(data);
    } else {
      Map<String, Object> data = ImmutableMap.<String, Object> builder()
          .put("redirect", "/create.ftl").build();
      return GSON.toJson(data);
    }
  }

}
