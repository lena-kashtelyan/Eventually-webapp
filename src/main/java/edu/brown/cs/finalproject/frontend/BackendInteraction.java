package edu.brown.cs.finalproject.frontend;

import com.google.gson.Gson;

import edu.brown.cs.finalproject.credentials.Authenticator;
import edu.brown.cs.finalproject.credentials.StormPathApplication;
import edu.brown.cs.finalproject.database.DatabaseManager;
import edu.brown.cs.finalproject.search.FacebookDataManager;

public class BackendInteraction {

  protected static Authenticator auth;

  protected static DatabaseManager dbManager;

  protected static FacebookDataManager facebookDataManager;

  protected static StormPathApplication stormPath;

  protected final static Gson GSON = new Gson();

  protected BackendInteraction() {

  }

  public BackendInteraction(Authenticator authenticate,
      DatabaseManager databaseManager, FacebookDataManager fbDataManager,
      StormPathApplication stormPathApp) {
    auth = authenticate;
    dbManager = databaseManager;
    facebookDataManager = fbDataManager;
    stormPath = stormPathApp;
  }
}
