package edu.brown.cs.finalproject.frontend;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.stormpath.sdk.account.Account;
import com.stormpath.sdk.application.Application;
import com.stormpath.sdk.provider.ProviderAccountRequest;
import com.stormpath.sdk.provider.ProviderAccountResult;
import com.stormpath.sdk.provider.Providers;

import edu.brown.cs.finalproject.credentials.AuthToken;
import spark.QueryParamsMap;
import spark.Request;
import spark.Response;
import spark.Route;

public class FacebookLoginResponse extends BackendInteraction implements Route {

  private static final String FACEBOOK_ID = "220099498366885";
  private static final String FACEBOOK_SECRET = "8a0e23ef1bc9e94213c881e53b2d7343";
  private static final String applicationHref = "https://api.stormpath.com/v1/applications/76713eIdUzokAFDoD4WtP7";
  private static final String REDIRECT_URI = "http://1a040c51.ngrok.io/fbr";

  @Override
  public Object handle(Request req, Response res) {
    QueryParamsMap qm = req.queryMap();
    String authCode = qm.value("code");

    try {
      String query = "client_id=" + FACEBOOK_ID + "&redirect_uri="
          + REDIRECT_URI + "&client_secret=" + FACEBOOK_SECRET + "&code="
          + authCode;
      URL url = new URL(
          "https://graph.facebook.com/v2.3/oauth/access_token?" + query);

      // make connection
      URLConnection urlc = url.openConnection();
      urlc.setRequestProperty("Accept-Charset",
          java.nio.charset.StandardCharsets.UTF_8.name());

      // get result
      InputStream response = urlc.getInputStream();
      StringBuilder sb = new StringBuilder();
      try (Scanner scanner = new Scanner(response)) {
        String responseBody = scanner.useDelimiter("\\A").next();
        sb.append(responseBody);
      }
      String responseString = sb.toString();

      // regular expressions for getting auth back from
      // response
      Pattern pattern = Pattern
          .compile("\\{\"(access_token)\":\"([A-z0-9]*)\"(.*)\\}");
      Matcher match = pattern.matcher(responseString);
      match.find();
      // gets auth token from facebook
      String authenticate = match.group(2);

      // creates account or gets account from storm path
      try {
        Application application = stormPath.getStormPathClient()
            .getResource(applicationHref, Application.class);
        ProviderAccountRequest request = Providers.FACEBOOK.account()
            .setAccessToken(authenticate).build();

        ProviderAccountResult result = application.getAccount(request);
        Account account = result.getAccount();
        System.out.println("email = " + account.getEmail());
        System.out.println("new account? =" + result.isNewAccount());
        if (result.isNewAccount()) {
          dbManager.addUser(account.getUsername(), null, authenticate);
        } else {
          dbManager.setUsersFBAccessToken(account.getUsername(), authenticate);
        }
        try {
          String username = account.getUsername();
          AuthToken authToken = AuthToken.generateAuthToken(account);
          if (auth.verifyAuthToken(username, authToken)) {
            res.redirect("/map?" + "title=Map" + "&auth=" + authToken.toString()
                + "&username=" + username);
            return null;
          } else {
            throw new RuntimeException(
                "Oops! Something went wrong, please try again.");
          }
        } catch (RuntimeException e) {
          e.printStackTrace();
          res.redirect(
              "/login?" + "title=Login" + "error=" + e.getLocalizedMessage());
          return null;
        }
      } catch (Exception e) {
        System.out.println("This shouldn't throw an exception");
        e.printStackTrace();
        res.redirect(
            "/login?" + "title=Login" + "error=" + e.getLocalizedMessage());
        return null;
      }
    } catch (Exception e) {
      e.printStackTrace();
      res.redirect(
          "/login?" + "title=Login" + "error=" + e.getLocalizedMessage());
      return null;
    }
  }
}
