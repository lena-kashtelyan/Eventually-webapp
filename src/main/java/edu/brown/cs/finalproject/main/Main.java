package edu.brown.cs.finalproject.main;

import java.io.IOException;
import java.sql.SQLException;

import com.google.gson.JsonObject;
import com.stormpath.sdk.account.Account;
import com.stormpath.sdk.application.Application;
import com.stormpath.sdk.directory.CreateDirectoryRequest;
import com.stormpath.sdk.directory.Directories;
import com.stormpath.sdk.directory.Directory;
import com.stormpath.sdk.provider.ProviderAccountRequest;
import com.stormpath.sdk.provider.ProviderAccountResult;
import com.stormpath.sdk.provider.Providers;
import com.stormpath.sdk.tenant.Tenant;

import edu.brown.cs.finalproject.credentials.Authenticator;
import edu.brown.cs.finalproject.credentials.Login;
import edu.brown.cs.finalproject.credentials.SignUp;
import edu.brown.cs.finalproject.credentials.StormPathApplication;
import edu.brown.cs.finalproject.database.Database;
import edu.brown.cs.finalproject.database.DatabaseManager;
import edu.brown.cs.finalproject.database.PublicFBEventsWriter;
import edu.brown.cs.finalproject.frontend.BackendInteraction;
import edu.brown.cs.finalproject.frontend.MapsSparkServer;
import edu.brown.cs.finalproject.frontend.SparkServer;
import edu.brown.cs.finalproject.search.FacebookDataManager;
import edu.brown.cs.finalproject.search.PublicFBEventsFinder;
import joptsimple.OptionParser;
import joptsimple.OptionSet;

public class Main {
  public static void main(String[] args) {
    new Main(args).run();
  }

  private String[] args;

  private Main(String[] args) {
    this.args = args;
  }

  private void run() {
    OptionParser parser = new OptionParser();
    parser.accepts("gui");
    OptionSet options = parser.parse(args);

    StormPathApplication stormPathApp = new StormPathApplication(
        "cs32FinalProject");
    Authenticator auth = new Authenticator(stormPathApp);

    SignUp test = new SignUp("Cole", "hansen", "chansen2",
        "cole_hansen@brown.edu", "P@ssword1");

    try {
      Directory directory = stormPathApp.getStormPathClient()
          .instantiate(Directory.class);
      directory.setName("test-facebook-directory");
      directory.setDescription("Test Facebook directory");

      CreateDirectoryRequest request = Directories
          .newCreateRequestFor(directory)
          .forProvider(
              Providers.FACEBOOK.builder().setClientId("220099498366885")
                  .setClientSecret("8a0e23ef1bc9e94213c881e53b2d7343").build())
          .build();

      Tenant tenant = stormPathApp.getStormPathClient().getCurrentTenant();
      directory = tenant.createDirectory(request);
    } catch (Exception e) {
    }

    try {
      String applicationHref = "https://api.stormpath.com/v1/applications/76713eIdUzokAFDoD4WtP7";
      Application application = stormPathApp.getStormPathClient()
          .getResource(applicationHref, Application.class);
      ProviderAccountRequest request = Providers.FACEBOOK.account()
          .setAccessToken(
              "EAADILehqV6UBAFuM85mSo9lSY6h8rgBE4QFj5SBDFBSyE5yyxSjpBbZCXHJ3hwj4OA50uuTzptGVrnAoL6pM8oKAv6KQLbo4CX9rRea099p7kUNqPjXU6Is9yTyZCe5rSxFnOBPzQ28ZBmXAGVhfS5a0txy9TreTTmdHDoxSwZDZD")
          .build();

      ProviderAccountResult result = application.getAccount(request);
      Account account = result.getAccount();
      System.out.println(account.getEmail());
      System.out.println(result.isNewAccount());
    } catch (Exception e) {
      System.out.println("This shouldn't throw an exception");
      e.printStackTrace();
    }

    try {
      auth.createAccount(test);
    } catch (RuntimeException e) {
      System.out.println(e.getMessage());
    }

    Login login = new Login("chansen2", "P@ssword1");
    try {
      System.out.println(auth.authenticate(login));
    } catch (Exception e) {
      e.printStackTrace();
    }

    DatabaseManager dbManager = new DatabaseManager();
    FacebookDataManager facebookDataManager = new FacebookDataManager();

    Database db = null;
    try {
      db = new Database("database/finalproject.db");
    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
      System.out.println("ERROR: Accessing the database file.");
    }
    // new DatabaseFactory().createAndIndexTables();
    System.out.println("all done");

    if (options.has("gui")) {
      new BackendInteraction(auth, dbManager, facebookDataManager);
      SparkServer server = new MapsSparkServer();
      server.runSparkServer();
      // lines to instantiate tables in the database and
      // create indices;

    } else {

      // THIS IS HOW WE FETCH PUBLIC FACEBOOK EVENTS AND
      // UPDATE CARTODB events TABLE

      try {
        new PublicFBEventsFinder();
      } catch (Exception e1) {
        e1.printStackTrace();
        System.out.println(
            "ERROR: Problem with running the public events application.");
      }

      JsonObject publicEvents = null;
      try {
        publicEvents = PublicFBEventsFinder.requestEvents(41.826119, -71.403112,
            1000);
      } catch (IOException e1) {
        e1.printStackTrace();
        System.out.println("ERROR: Fetching public Facebook events.");
      }

      System.out.println(publicEvents);
      PublicFBEventsWriter publicFBEventsWriter = new PublicFBEventsWriter();
      try {
        publicFBEventsWriter.updateDB(publicEvents);
      } catch (SQLException | IOException e) {
        e.printStackTrace();
        System.out.println("Problem updating database with public venues.");
      }

      // EventsByName eventsByName = new EventsByName();

    }

  }
}
