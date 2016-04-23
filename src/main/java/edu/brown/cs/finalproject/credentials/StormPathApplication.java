package edu.brown.cs.finalproject.credentials;

import java.util.Properties;

import com.stormpath.sdk.api.ApiKey;
import com.stormpath.sdk.api.ApiKeys;
import com.stormpath.sdk.application.Application;
import com.stormpath.sdk.application.ApplicationList;
import com.stormpath.sdk.application.Applications;
import com.stormpath.sdk.client.Client;
import com.stormpath.sdk.client.ClientBuilder;
import com.stormpath.sdk.client.Clients;
import com.stormpath.sdk.tenant.Tenant;

public class StormPathApplication {
  private Application app;
  private Client client;

  public StormPathApplication(String projectName) {
    Properties properties = new Properties();
    properties.setProperty("apiKey.id", "13OQ8XCJWEG1R4W56V267M7V9");
    properties.setProperty("apiKey.secret",
        "EdhsJ5/la4tslzkOH+FhvGpfbxKLptEFN7sZTP1ljNI");

    ApiKey apiKey = ApiKeys.builder().setProperties(properties).build();

    ClientBuilder cb = Clients.builder();
    client = cb.setApiKey(apiKey).build();

    Tenant tenant = client.getCurrentTenant();

    ApplicationList applications = tenant.getApplications(
        Applications.where(Applications.name().eqIgnoreCase(projectName)));
    app = applications.iterator().next();
  }

  public Application getStormPathApplication() {
    return app;
  }

  public Client getStormPathClient() {
    return client;
  }
}
