package edu.brown.cs.finalproject.main;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;
import java.util.Map;

import joptsimple.OptionParser;
import joptsimple.OptionSet;
import spark.ExceptionHandler;
import spark.ModelAndView;
import spark.QueryParamsMap;
import spark.Request;
import spark.Response;
import spark.Spark;
import spark.TemplateViewRoute;
import spark.template.freemarker.FreeMarkerEngine;

import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;

import edu.brown.cs.finalproject.credentials.SignUp;
import edu.brown.cs.finalproject.database.Database;
import freemarker.template.Configuration;

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
    if (options.has("gui")) {
      runSparkServer();
    } else {
      System.out.println("helloworld");
      // lines to instantiate tables in the database and create indices;
      // uncomment and change login name if you want to reset the tables
      try {
        Database db = new Database("database/finalproject.db");
      } catch (ClassNotFoundException | SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      // new DatabaseFactory(db).createAndIndexTables();
      System.out.println("all done");
    }
  }

  /**
   * Creates the spark free marker engine
   */
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
   * The Gson for conversion
   */
  private final static Gson GSON = new Gson();

  class FrontHandler implements TemplateViewRoute {
    @Override
    public ModelAndView handle(Request req, Response res) {
      System.out.println("In FrontHandler");
      Map<String, Object> variables = ImmutableMap.of("title", "Hopper Map");
      return new ModelAndView(variables, "home.ftl");
    }
  }

  class LogInHandler implements TemplateViewRoute {
    @Override
    public ModelAndView handle(Request req, Response res) {
      System.out.println("In LoginHandler");
      Map<String, Object> variables = ImmutableMap.of("title", "Hopper LogIn");
      return new ModelAndView(variables, "login.ftl");
    }
  }

  class SignUpHandler implements TemplateViewRoute {
    @Override
    public ModelAndView handle(Request req, Response res) {
      System.out.println("In SignUpHandler");
      QueryParamsMap qm = req.queryMap();
      String[] fields = new String[7];
      fields[0] = qm.value("username");
      fields[1] = qm.value("name");
      fields[2] = qm.value("password");
      fields[3] = qm.value("question-one");
      fields[4] = qm.value("answer-one");
      fields[5] = qm.value("question-two");
      fields[6] = qm.value("answer-two");
      SignUp.addUser(fields, Database.getConnection());
      Map<String, Object> variables = ImmutableMap.of("title", "Hopper SignUp");
      return new ModelAndView(variables, "signup.ftl");
    }
  }

  class ForgotHandler implements TemplateViewRoute {
    @Override
    public ModelAndView handle(Request req, Response res) {
      System.out.println("In ForgotHandler");
      Map<String, Object> variables = ImmutableMap.of("title",
          "Hopper ForgotPassword");
      return new ModelAndView(variables, "forgot.ftl");
    }
  }

  class ForgotResponseHandler implements TemplateViewRoute {
    @Override
    public ModelAndView handle(Request req, Response res) {
      System.out.println("In ForgotResponseHandler");
      Map<String, Object> variables = ImmutableMap.of("title",
          "Hopper ForgotPasswordResponse");
      return new ModelAndView(variables, "response.ftl");
    }
  }

  class BrowseHandler implements TemplateViewRoute {
    @Override
    public ModelAndView handle(Request req, Response res) {
      System.out.println("In BrowseHandler");
      Map<String, Object> variables = ImmutableMap.of("title", "Hopper Browse");
      return new ModelAndView(variables, "browse.ftl");
    }
  }

  class StorystreamHandler implements TemplateViewRoute {
    @Override
    public ModelAndView handle(Request req, Response res) {
      System.out.println("In StorystreamHandler");
      Map<String, Object> variables = ImmutableMap.of("title",
          "Hopper StorystreamHandlerResponse");
      return new ModelAndView(variables, "storystream.ftl");
    }
  }

  /**
   * Handles the printing of exceptions
   */
  static class ExceptionPrinter implements ExceptionHandler {
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

  /*
   * Runs the spark server
   */
  private void runSparkServer() {
    Spark.setPort(2222);
    Spark.externalStaticFileLocation("src/main/resources/static");
    Spark.exception(Exception.class, new ExceptionPrinter());

    FreeMarkerEngine freeMarker = createEngine();
    Spark.get("/home", new FrontHandler(), freeMarker);
    Spark.get("/login", new LogInHandler(), freeMarker);
    Spark.get("/signup", new SignUpHandler(), freeMarker);
    Spark.get("/forgot", new ForgotHandler(), freeMarker);
    Spark.get("/response", new ForgotResponseHandler(), freeMarker);
    Spark.get("/browse", new BrowseHandler(), freeMarker);
    Spark.get("/stream", new StorystreamHandler(), freeMarker);
  }
}
