package edu.brown.cs.finalproject.main;

import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;

import com.cartodb.CartoDBException;
import com.google.gson.JsonObject;

import edu.brown.cs.finalproject.credentials.Authenticator;
import edu.brown.cs.finalproject.credentials.Login;
import edu.brown.cs.finalproject.credentials.SignUp;
import edu.brown.cs.finalproject.credentials.StormPathApplication;
import edu.brown.cs.finalproject.database.Database;
import edu.brown.cs.finalproject.database.DatabaseManager;
import edu.brown.cs.finalproject.database.PublicFBEventsWriter;
import edu.brown.cs.finalproject.frontend.BackendInteraction;
import edu.brown.cs.finalproject.frontend.MapsSparkServer;
import edu.brown.cs.finalproject.frontend.SparkServer;
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

//    StormPathApplication stormPathApp = new StormPathApplication(
//        "cs32FinalProject");
//    Authenticator auth = new Authenticator(stormPathApp);

//    SignUp test = new SignUp("Cole", "hansen", "chansen2",
//        "cole_hansen@brown.edu", "P@ssword1");

//    try {
//      auth.createAccount(test);
//    } catch (RuntimeException e) {
//      System.out.println(e.getMessage());
//    }

    // System.out.println("helloworld");
//    Login login = new Login("chansen2", "P@ssword1");
//    System.out.println(auth.authenticate(login));
    
    DatabaseManager dbManager = new DatabaseManager();
    FacebookDataManager facebookDataManager = new FacebookDataManager();

    if (options.has("gui")) {
//      new BackendInteraction(auth, dbManager, facebookDataManager);
      SparkServer server = new MapsSparkServer();
      server.runSparkServer();
      // lines to instantiate tables in the database and
      // create indices;
      Database db = null;
      try {
        db = new Database("database/finalproject.db");
      } catch (ClassNotFoundException | SQLException e) {
        e.printStackTrace();
        System.out.println("ERROR: Accessing the database file.");
      }
      // new DatabaseFactory().createAndIndexTables();
      System.out.println("all done");

    } else {

      // THIS IS HOW WE FETCH PUBLIC FACEBOOK EVENTS AND
      // UPDATE CARTODB events TABLE

//       try {
//    	   new PublicFBEventsFinder();
//       } catch (Exception e1) {
//       e1.printStackTrace();
//       System.out.println("ERROR: Problem with running the public events application.");
//       }
//      
//       JsonObject publicEvents = null;
//       try {
//       publicEvents =
//       PublicFBEventsFinder.requestEvents(41.826119, -71.403112, 1000, "CAACEdEose0cBAOwPOheliuQnVYBYWhfDMpiPN93V58q0KxLH5XxBuZANAKFbLDHHPG8S8h5KZAo0pV6rJWzJTMLK8OnWpH192N1nY6jN4jHm3eDhK1Tp2r88OhZBLzxp5w0jZCKgsBZB03vX8nJ4ZAZC5RkgbMEy7dJYsCSZBoTSkeBiLVCUgPyJaNIDZBIcZCPCZCpESi1GPTmkUPHAnkgsQDQ");
//       } catch (IOException e1) {
//       e1.printStackTrace();
//       System.out.println("ERROR: Fetching public Facebook events.");
//       }
//      
//      
//       System.out.println(publicEvents);
//       PublicFBEventsWriter publicFBEventsWriter = new
//       PublicFBEventsWriter();
//       try {
//       publicFBEventsWriter.updateDB(publicEvents);
//       } catch (SQLException | IOException e) {
//       e.printStackTrace();
//       System.out.println("Problem updating database with public venues.");
//       }
      
      // EventsByName eventsByName = new EventsByName();
    }

  }
}

// /**
// * Creates the spark free marker engine
// */
// private static FreeMarkerEngine createEngine() {
// Configuration config = new Configuration();
// File templates = new
// File("src/main/resources/spark/template/freemarker");
// try {
// config.setDirectoryForTemplateLoading(templates);
// } catch (IOException ioe) {
// System.out.printf("ERROR: Unable use %s for template
// loading.\n",
// templates);
// System.exit(1);
// }
// return new FreeMarkerEngine(config);
// }
//
// /**
// * The Gson for conversion
// */
// private final static Gson GSON = new Gson();
//
// class FrontHandler implements TemplateViewRoute {
// @Override
// public ModelAndView handle(Request req, Response res) {
// System.out.println("In FrontHandler");
// Map<String, Object> variables = ImmutableMap.of("title",
// "Hopper Map");
// return new ModelAndView(variables, "home.ftl");
// }
// }
//
// class LogInHandler implements TemplateViewRoute {
// @Override
// public ModelAndView handle(Request req, Response res) {
// System.out.println("In LoginHandler");
// QueryParamsMap qm = req.queryMap();
// String username = qm.value("username");
// String password = qm.value("password");
// boolean valid = Login.validLogin(username, password);
// if (valid) {
// try {
// UserProxy curr_user = UserProxy.byUserName(username);
// } catch (SQLException e) {
// // TODO Auto-generated catch block
// e.printStackTrace();
// }
// } else {
// // Handle case where user did not provide valid
// // login
// }
// Map<String, Object> variables = ImmutableMap.of("title",
// "Hopper LogIn");
// return new ModelAndView(variables, "login.ftl");
// }
// }
//
// class SignUpHandler implements TemplateViewRoute {
// @Override
// public ModelAndView handle(Request req, Response res) {
// System.out.println("In SignUpHandler");
// QueryParamsMap qm = req.queryMap();
// String[] fields = new String[8];
// fields[0] = qm.value("username");
// fields[1] = qm.value("name");
// fields[2] = qm.value("password");
// fields[3] = qm.value("question-one");
// fields[4] = qm.value("answer-one");
// fields[5] = qm.value("question-two");
// fields[6] = qm.value("answer-two");
// fields[7] = "/defaultpath";
// System.out.println(fields[0]);
// // SignUp.addUser(fields);
// Map<String, Object> variables = ImmutableMap.of("title",
// "Hopper SignUp");
// return new ModelAndView(variables, "signup.ftl");
// }
// }
//
// class ForgotHandler implements TemplateViewRoute {
// @Override
// public ModelAndView handle(Request req, Response res) {
// System.out.println("In ForgotHandler");
// Map<String, Object> variables = ImmutableMap.of("title",
// "Hopper ForgotPassword");
// return new ModelAndView(variables, "forgot.ftl");
// }
// }
//
// class ForgotResponseHandler implements TemplateViewRoute
// {
// @Override
// public ModelAndView handle(Request req, Response res) {
// System.out.println("In ForgotResponseHandler");
// Map<String, Object> variables = ImmutableMap.of("title",
// "Hopper ForgotPasswordResponse");
// return new ModelAndView(variables, "response.ftl");
// }
// }
//
// class BrowseHandler implements TemplateViewRoute {
// @Override
// public ModelAndView handle(Request req, Response res) {
// System.out.println("In BrowseHandler");
// Map<String, Object> variables = ImmutableMap.of("title",
// "Hopper Browse");
// return new ModelAndView(variables, "browse.ftl");
// }
// }
//
// class StorystreamHandler implements TemplateViewRoute {
// @Override
// public ModelAndView handle(Request req, Response res) {
// System.out.println("In StorystreamHandler");
// Map<String, Object> variables = ImmutableMap.of("title",
// "Hopper StorystreamHandlerResponse");
// return new ModelAndView(variables, "storystream.ftl");
// }
// }
//
// /**
// * Handles the printing of exceptions
// */
// static class ExceptionPrinter implements ExceptionHandler
// {
// @Override
// public void handle(Exception e, Request req, Response
// res) {
// res.status(500);
// StringWriter stacktrace = new StringWriter();
// try (PrintWriter pw = new PrintWriter(stacktrace)) {
// pw.println("<pre>");
// e.printStackTrace(pw);
// pw.println("</pre>");
// }
// res.body(stacktrace.toString());
// }
// }
//
// /*
// * Runs the spark server
// */
// private void runSparkServer() {
// Spark.setPort(2222);
// Spark.externalStaticFileLocation("src/main/resources/static");
// Spark.exception(Exception.class, new ExceptionPrinter());
//
// FreeMarkerEngine freeMarker = createEngine();
// Spark.get("/home", new FrontHandler(), freeMarker);
// Spark.get("/login", new LogInHandler(), freeMarker);
// Spark.get("/signup", new SignUpHandler(), freeMarker);
// Spark.get("/forgot", new ForgotHandler(), freeMarker);
// Spark.get("/response", new ForgotResponseHandler(),
// freeMarker);
// Spark.get("/browse", new BrowseHandler(), freeMarker);
// Spark.get("/stream", new StorystreamHandler(),
// freeMarker);
// }
// }
