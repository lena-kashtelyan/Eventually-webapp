package edu.brown.cs.finalproject.frontend;

import java.util.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

import org.apache.commons.lang3.time.DateUtils;

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
    String creatorID = qm.value("username");
    String eventName = qm.value("eventName");
    String date = qm.value("date");
    String time = qm.value("time");
    String description = qm.value("description");
    String[] dates = date.split("/");
    String[] times = time.split(":");
    String datetime = String.format("'%s-%s-%s %s:%s:00'", dates[2], dates[0], dates[1], times[0], times[1]);
    
    String startdatetimePrep = datetime.replace("'", "");
    SimpleDateFormat formatter = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	Date eventStarttime = null;
	try {
		eventStarttime = formatter.parse(startdatetimePrep);
		System.out.println(eventStarttime);
	} catch (ParseException e) {
		e.printStackTrace();
		System.out
				.println("ERROR: Unable to convert a date string into a java.util.Date.");
	}
	// Calculating the end time of an event by adding four hours to the starting time
	Date eventEndtime = DateUtils.addHours(eventStarttime, 4);
	
	String eventEndtimeString = eventEndtime.toString();
	eventEndtimeString = eventEndtimeString.substring(4).replaceAll("E[D,S]T", "");
	
	String enddatetimeString = String.format("to_timestamp('%s','Mon DD HH24:MI:SS  YYYY')", eventEndtimeString);

	System.out.println(enddatetimeString);
	System.out.println("}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}]");
    
    String timestamp = String.format("to_timestamp(%s,'YYYY-MM-dd HH24:MI:SS')", datetime);
    String location = qm.value("location");
    String facebookAdd = qm.value("facebookAdd");
    String type;
    if (facebookAdd != null) {
      type = "facebook";
    } else {
      type = "internal";
    }
    String category = qm.value("category");
    
    boolean result = DatabaseManager.addEvent(eventName, creatorID, timestamp, enddatetimeString, location, category, description, type);
    System.out.println(result);
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
