package edu.brown.cs.finalproject.frontend;

import edu.brown.cs.finalproject.database.DatabaseManager;
import spark.QueryParamsMap;
import spark.Request;
import spark.Response;
import spark.Route;

public class ProfilePictureHandler extends BackendInteraction implements Route {

  @Override
  public Object handle(Request req, Response res) {
    QueryParamsMap qm = req.queryMap();
    String photoID = qm.value("photoid");
    String url = "http://res.cloudinary.com/df1bylm3l/image/upload/" + photoID;
    String username = qm.value("username");
    boolean result = DatabaseManager.addProfilePicture(username, url);
    return GSON.toJson(result);
  }

}
