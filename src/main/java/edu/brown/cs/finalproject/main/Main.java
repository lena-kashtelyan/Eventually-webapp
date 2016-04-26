package edu.brown.cs.finalproject.main;

import java.io.IOException;
import java.sql.SQLException;

import com.google.gson.JsonObject;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.FacebookClient.AccessToken;
import com.restfb.Version;
import com.restfb.scope.ScopeBuilder;

import edu.brown.cs.finalproject.credentials.Authenticator;
import edu.brown.cs.finalproject.credentials.Login;
import edu.brown.cs.finalproject.credentials.SignUp;
import edu.brown.cs.finalproject.credentials.StormPathApplication;
import edu.brown.cs.finalproject.database.Database;
import edu.brown.cs.finalproject.database.DatabaseManager;
import edu.brown.cs.finalproject.database.PublicFBEventsWriter;
import edu.brown.cs.finalproject.frontend.MapsSparkServer;
import edu.brown.cs.finalproject.frontend.SparkServer;
import edu.brown.cs.finalproject.search.FacebookDataManager;
import edu.brown.cs.finalproject.frontend.BackendInteraction;
import edu.brown.cs.finalproject.frontend.MapsSparkServer;
import edu.brown.cs.finalproject.frontend.SparkServer;
import edu.brown.cs.finalproject.search.EventsByName;
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
				System.out
						.println("ERROR: Problem with running the public events application.");
			}

			JsonObject publicEvents = null;
			try {
				publicEvents = PublicFBEventsFinder.requestEvents(41.826119,
						-71.403112, 1000);
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
				System.out
						.println("Problem updating database with public venues.");
			}
      
//       EventsByName eventsByName = new EventsByName();

    }

  }
}
