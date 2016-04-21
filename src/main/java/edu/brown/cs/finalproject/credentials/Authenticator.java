package edu.brown.cs.finalproject.credentials;

import com.stormpath.sdk.account.Account;
import com.stormpath.sdk.directory.CustomData;

public class Authenticator {
  StormPathApplication app;

  public Authenticator(StormPathApplication stormPathApp) {
    app = stormPathApp;
  }

  public AuthToken createAccount(SignUp signup) {
    Account account = app.getStormPathClient().instantiate(Account.class);
    account.setGivenName(signup.getFirstName());
    account.setSurname(signup.getLastName());
    account.setUsername(signup.getUsername());
    account.setEmail(signup.getEmail());
    account.setPassword(signup.getPassword());
    CustomData customData = account.getCustomData();
    customData.put("test", "testval");
    return AuthToken.generateAuthToken(account);
  }

  public boolean authenticate(String auth) {
    return true;
  }
}
