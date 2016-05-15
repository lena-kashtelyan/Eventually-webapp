package edu.brown.cs.finalproject.main;

import java.io.IOException;
import java.sql.SQLException;

import com.stormpath.sdk.directory.CreateDirectoryRequest;
import com.stormpath.sdk.directory.Directories;
import com.stormpath.sdk.directory.Directory;
import com.stormpath.sdk.provider.Providers;
import com.stormpath.sdk.tenant.Tenant;

import edu.brown.cs.finalproject.credentials.Authenticator;
import edu.brown.cs.finalproject.credentials.StormPathApplication;
import edu.brown.cs.finalproject.database.Database;
import edu.brown.cs.finalproject.database.DatabaseFactory;
import edu.brown.cs.finalproject.database.DatabaseManager;
import edu.brown.cs.finalproject.frontend.BackendInteraction;
import edu.brown.cs.finalproject.frontend.MapsSparkServer;
import edu.brown.cs.finalproject.frontend.SparkServer;
import edu.brown.cs.finalproject.search.FacebookDataManager2;
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
    parser.accepts("new");
    OptionSet options = parser.parse(args);

    StormPathApplication stormPathApp = new StormPathApplication(
        "cs32FinalProject");
    Authenticator auth = new Authenticator(stormPathApp);
    DatabaseManager dbManager = new DatabaseManager();

    try {
      Runtime.getRuntime().exec("pkill npm");
      Runtime.getRuntime().exec("pkill node");
    } catch (IOException e) {
      e.printStackTrace();
      System.out.println("ERROR: Problem executing pkill commands.");
    }

    FacebookDataManager2 facebookDataManager2 = null;
    try {
      facebookDataManager2 = new FacebookDataManager2();

    } catch (IOException e1) {
      e1.printStackTrace();
      System.out.println("ERROR: Problem instantiating FacebookDataManager2.");
    }

    /*
     * This try block is for setting up the facebook
     * directory on stormpath. Should only need to be used
     * once.
     */
    try {
      Directory directory = stormPathApp.getStormPathClient()
          .instantiate(Directory.class);
      directory.setName("facebook-directory");
      directory.setDescription("Facebook directory");

      String FACEBOOK_ID = "220099498366885";
      String FACEBOOK_SECRET = "8a0e23ef1bc9e94213c881e53b2d7343";

      CreateDirectoryRequest request = Directories
          .newCreateRequestFor(directory)
          .forProvider(Providers.FACEBOOK.builder().setClientId(FACEBOOK_ID)
              .setClientSecret(FACEBOOK_SECRET).build())
          .build();

      Tenant tenant = stormPathApp.getStormPathClient().getCurrentTenant();
      directory = tenant.createDirectory(request);
    } catch (Exception e) {
    }

    Database db = null;
    try {
      db = new Database("database/finalproject.db");
    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
      System.out.println("ERROR: Accessing the database file.");
    }
    if (options.has("new")) {
      try {
        System.out.println("here");
        DatabaseFactory.createAndIndexTables();
      } catch (Exception e) {
        System.out.println("Database already created.");
      }
    }

    if (options.has("gui")) {
      new BackendInteraction(auth, dbManager, facebookDataManager2,
          stormPathApp);
      SparkServer server = new MapsSparkServer();
      server.runSparkServer();
    } else {

    }

  }
}
