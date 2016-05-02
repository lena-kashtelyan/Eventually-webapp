package edu.brown.cs.finalproject.credentials;

import com.stormpath.sdk.account.Account;
import com.stormpath.sdk.account.PasswordResetToken;
import com.stormpath.sdk.application.Application;
import com.stormpath.sdk.authc.AuthenticationRequest;
import com.stormpath.sdk.authc.AuthenticationResult;
import com.stormpath.sdk.authc.UsernamePasswordRequests;
import com.stormpath.sdk.resource.ResourceException;

public class Authenticator {
  StormPathApplication stormPath;

  public Authenticator(StormPathApplication stormPathApp) {
    stormPath = stormPathApp;
  }

  public AuthToken createAccount(SignUp signup) {
    Account account = stormPath.getStormPathClient().instantiate(Account.class);
    account.setGivenName(signup.getFirstName());
    account.setSurname(signup.getLastName());
    account.setUsername(signup.getUsername());
    account.setEmail(signup.getEmail());
    account.setPassword(signup.getPassword());
    // If we decide that we need custom data
    // CustomData customData = account.getCustomData();
    // customData.put("test", "testval");
    try {
      stormPath.getStormPathApplication().createAccount(account);
      return AuthToken.generateAuthToken(account);
    } catch (ResourceException e) {
      throw new RuntimeException(e);
    }
  }

  public boolean verifyAuthToken(String username, AuthToken authToken) {
    return authToken.verify(username);
  }

  public AuthToken authenticate(Login login) {
    @SuppressWarnings("rawtypes")
    AuthenticationRequest request = UsernamePasswordRequests.builder()
        .setUsernameOrEmail(login.getUsernameOrEmail())
        .setPassword(login.getRawPassword()).build();

    // Now let's authenticate the account with the
    // application:
    try {
      AuthenticationResult result = stormPath.getStormPathApplication()
          .authenticateAccount(request);
      Account account = result.getAccount();
      return AuthToken.generateAuthToken(account);
    } catch (ResourceException ex) {
      System.out.println(ex.getStatus() + " " + ex.getMessage());
      throw new RuntimeException("Invalid login");
    }
  }

  public AuthToken forgotPassword(Forgot forgot) {
    Application app = stormPath.getStormPathApplication();
    PasswordResetToken passwordResetToken = app
        .sendPasswordResetEmail(forgot.getUserEmail());
    try {
      Account account = passwordResetToken.getAccount();
      return AuthToken.generateAuthToken(account);
    } catch (ResourceException e) {
      throw new RuntimeException(e.getLocalizedMessage());
    }
  }
}
