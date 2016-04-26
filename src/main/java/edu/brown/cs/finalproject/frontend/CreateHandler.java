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
    QueryParamsMap qm = req.queryMap();
    String type;
    String creatorID = qm.value("username");
    String eventName = qm.value("eventName");
    String date = qm.value("date");
    String time = qm.value("time");
    String description = qm.value("description");
    String datetime = date+" "+time+":00";
    DateFormat dateFormat = new SimpleDateFormat("mm/dd/yyyy hh:mm:ss");
    Date formatdate;
    try {
      formatdate = (Date) dateFormat.parse(datetime);
    } catch (ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      Map<String, Object> data = ImmutableMap.<String, Object> builder()
          .put("redirect", "/create.ftl").build();
      return GSON.toJson(data);
    }
    long converted = formatdate.getTime();
    Timestamp timestamp = new Timestamp(converted);
    String location = qm.value("location");
    String coords[] = location.split(",");
    double lat = Double.parseDouble(coords[0]);
    double lng = Double.parseDouble(coords[1]);
    String category = qm.value("category");
    String facebook = qm.value("facebookAdd");
    if (facebook.equals("yes")) {
      type = "facebook";
    } else {
      type = "internal";
    }
    
    boolean result = DatabaseManager.addInternalEvent(eventName, type, creatorID, timestamp, lat, lng, true, category, description);
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
