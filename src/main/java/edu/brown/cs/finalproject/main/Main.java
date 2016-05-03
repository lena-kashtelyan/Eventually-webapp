package edu.brown.cs.finalproject.main;

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
import edu.brown.cs.finalproject.search.FacebookDataManager;
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
    DatabaseManager dbManager = new DatabaseManager();
    FacebookDataManager facebookDataManager = new FacebookDataManager();

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
    try {
      DatabaseFactory.createAndIndexTables();
    } catch (Exception e) {
      System.out.println("Database already created.");
    }
    // new DatabaseFactory().createAndIndexTables();
    System.out.println("all done");

    if (options.has("gui")) {
      new BackendInteraction(auth, dbManager, facebookDataManager,
          stormPathApp);
      SparkServer server = new MapsSparkServer();
      server.runSparkServer();
      // lines to instantiate tables in the database and
      // create indices;

    } else {

      // THIS IS HOW WE FETCH PUBLIC FACEBOOK EVENTS AND
      // UPDATE CARTODB events TABLE

      // dbManager.addUser("ipetrov", "/course/kdasflsdf;",
      // "jfdajdajfadkljfda");
      // System.out.println(dbManager.getUsersFBAccessToken("ipetrov"));
      // dbManager.setUsersFBAccessToken("ipetrov",
      // "newfbaccesstoken");
      // System.out.println(dbManager.getUsersFBAccessToken("ipetrov"));
      // System.out.println(dbManager.getUsersMediaPath("ipetrov"));
      // dbManager.setUsersMediaPath("ipetrov", "/cdaklaf");
      // System.out.println(dbManager.getUsersMediaPath("ipetrov"));

      // try {
      // new PublicFBEventsFinder();
      // } catch (Exception e1) {
      // e1.printStackTrace();
      // System.out.println("ERROR: Problem with running the
      // public events application.");
      // }
      //
      // JsonObject publicEvents = null;
      // try {
      // publicEvents =
      // PublicFBEventsFinder.requestEvents(41.826119,
      // -71.403112, 1000);
      // } catch (IOException e1) {
      // e1.printStackTrace();
      // System.out.println("ERROR: Fetching public Facebook
      // events.");
      // }
      //
      // System.out.println(publicEvents);
      // PublicFBEventsWriter publicFBEventsWriter = new
      // PublicFBEventsWriter();
      // try {
      // publicFBEventsWriter.updateDB(publicEvents);
      // } catch (SQLException | IOException e) {
      // e.printStackTrace();
      // System.out.println("Problem updating database with
      // public venues.");
      // }

      // EventsByName eventsByName = new EventsByName();

    }

  }
}
