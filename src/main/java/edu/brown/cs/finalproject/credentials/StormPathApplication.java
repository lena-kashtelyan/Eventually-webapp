package edu.brown.cs.finalproject.credentials;

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
    ClientBuilder cb = Clients.builder();
    client = cb.build();
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
