package edu.brown.cs.finalproject.credentials;

import com.stormpath.sdk.account.Account;
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

  public boolean verifyAuthToken(AuthToken authToken) {
    return true;
  }

  public AuthToken authenticate(String usernameOrEmail, String rawPassword) {
    // Capture the username and password, such as via an
    // SSL-encrypted web HTML form. We'll just simulate a
    // form lookup and use the values we used above:

    // Create an authentication request using the
    // credentials
    System.out.println("It should be workin here?");
    @SuppressWarnings("rawtypes")
    AuthenticationRequest request = UsernamePasswordRequests.builder()
        .setUsernameOrEmail(usernameOrEmail).setPassword(rawPassword).build();

    // Now let's authenticate the account with the
    // application:
    try {
      System.out.println("Right before auth");
      AuthenticationResult result = stormPath.getStormPathApplication()
          .authenticateAccount(request);
      System.out.println("Authenticated?");
      Account account = result.getAccount();
      return AuthToken.generateAuthToken(account);
    } catch (ResourceException ex) {
      System.out.println(ex.getStatus() + " " + ex.getMessage());
      throw new RuntimeException("Invalid login");
    }
  }
}
