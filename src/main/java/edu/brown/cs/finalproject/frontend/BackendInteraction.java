package edu.brown.cs.finalproject.frontend;

import com.google.gson.Gson;

import edu.brown.cs.finalproject.credentials.Authenticator;
import edu.brown.cs.finalproject.credentials.StormPathApplication;
import edu.brown.cs.finalproject.database.DatabaseManager;
//import edu.brown.cs.finalproject.search.FacebookDataManager;
import edu.brown.cs.finalproject.search.FacebookDataManager2;

public class BackendInteraction {

  protected static Authenticator auth;

  protected static DatabaseManager dbManager;

  protected static FacebookDataManager2 facebookDataManager;

  protected static StormPathApplication stormPath;

  protected final static Gson GSON = new Gson();

  protected BackendInteraction() {

  }

  public BackendInteraction(Authenticator authenticate,
      DatabaseManager databaseManager, FacebookDataManager2 fbDataManager,
      StormPathApplication stormPathApp) {
    auth = authenticate;
    dbManager = databaseManager;
    facebookDataManager = fbDataManager;
    stormPath = stormPathApp;
  }
}
