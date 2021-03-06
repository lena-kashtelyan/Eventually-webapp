package edu.brown.cs.finalproject.frontend;

import java.util.Map;

import spark.QueryParamsMap;
import spark.Request;
import spark.Response;
import spark.Route;

import com.google.common.collect.ImmutableMap;

import edu.brown.cs.finalproject.credentials.AuthToken;
import edu.brown.cs.finalproject.credentials.SignUp;
import edu.brown.cs.finalproject.database.DatabaseManager;

public class SignupHandler extends BackendInteraction implements Route {

  @Override
  public Object handle(Request req, Response res) {
    QueryParamsMap qm = req.queryMap();
    String firstName = qm.value("firstName");
    String lastName = qm.value("lastName");
    String fullName = firstName + " " + lastName;
    String username = qm.value("username");
    String email = qm.value("email");
    String password = qm.value("password");
    // String path = qm.value("path");
    SignUp signup = new SignUp(firstName, lastName, username, email, password);
    try {
      AuthToken authToken = auth.createAccount(signup);
      if (auth.verifyAuthToken(username, authToken)) {
        System.out.println("here");
        DatabaseManager.addUser(username, fullName, "http://sighttosee.com/images/vendor/default-profile.png", null);
        Map<String, Object> data = ImmutableMap.<String, Object> builder()
            .put("title", "Map").put("auth", authToken.toString())
            .put("username", username).put("redirect", "/map.ftl").build();
        return GSON.toJson(data);
      } else {
        throw new RuntimeException(
            "Oops! Something went wrong, please try again.");
      }
    } catch (RuntimeException e) {
      Map<String, Object> data = ImmutableMap.<String, Object> builder()
          .put("title", "Signup").put("error", e.getLocalizedMessage())
          .put("redirect", "/signup").build();
      return GSON.toJson(data);
    }
  }

}
