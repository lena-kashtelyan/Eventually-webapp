package edu.brown.cs.finalproject.frontend;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import freemarker.template.Configuration;
import spark.ExceptionHandler;
import spark.Request;
import spark.Response;
import spark.Spark;
import spark.template.freemarker.FreeMarkerEngine;

public class MapsSparkServer implements SparkServer {

  @Override
  public void runSparkServer() {
    Spark.externalStaticFileLocation("src/main/resources/static");
    Spark.exception(Exception.class, new ExceptionPrinter());
    FreeMarkerEngine freeMarker = createEngine();

    Spark.get("/", new MainView(), freeMarker);
    Spark.get("/map", new MapView(), freeMarker);
    Spark.get("/login", new LoginView("login.ftl"), freeMarker);
    Spark.post("/login", new LoginHandler());
    Spark.get("/forgot", new ForgotView("forgot.ftl"), freeMarker);
    Spark.post("/forgot", new ForgotHandler());
    Spark.get("/signup", new SignupView("signup.ftl"), freeMarker);
    Spark.post("/signup", new SignupHandler());
    Spark.get("/browse", new BrowseView("browse.ftl"), freeMarker);
    // Spark.get("/myevents", null);
    Spark.get("/create", new CreateView("create.ftl"), freeMarker);
    Spark.post("/create", new CreateHandler());
    Spark.get("/account", new AccountView("account.ftl"), freeMarker);
    // Spark.get("/event/?", null);
    // Spark.get("/storystream/?", null);
    // Spark.put("/post/?", null);
    // Spark.post("/myaccount", null);
    // Spark.post("/changepassword", null);

  }

  private static FreeMarkerEngine createEngine() {
    Configuration config = new Configuration();
    File templates = new File("src/main/resources/spark/template/freemarker");
    try {
      config.setDirectoryForTemplateLoading(templates);
    } catch (IOException ioe) {
      System.out.printf("ERROR: Unable use %s for template loading.\n",
          templates);
      System.exit(1);
    }
    return new FreeMarkerEngine(config);
  }

  /**
   * Private static method to handle exceptions and
   * forwarding that to the GUI.
   */
  private static class ExceptionPrinter implements ExceptionHandler {
    /**
     * The handle method.
     * @param e
     *          The exception throw.
     * @param req
     *          The request object.
     * @param res
     *          The response object.
     */
    @Override
    public void handle(Exception e, Request req, Response res) {
      res.status(500);
      StringWriter stacktrace = new StringWriter();
      try (PrintWriter pw = new PrintWriter(stacktrace)) {
        pw.println("<pre>");
        e.printStackTrace(pw);
        pw.println("</pre>");
      }
      res.body(stacktrace.toString());
    }
  }

}
