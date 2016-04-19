package edu.brown.cs.finalproject.main;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;

import freemarker.template.Configuration;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import spark.ExceptionHandler;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Spark;
import spark.TemplateViewRoute;
import spark.template.freemarker.FreeMarkerEngine;

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
//          lines to instantiate tables in the database and create indices; uncomment and change login name if you want to reset the tables
//          Database db = new Database("/home/ipetrov/course/cs032/final_project/32final/database/finalproject.db");
//          new DatabaseFactory(db).createAndIndexTables(); 
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
            System.out.printf("ERROR: Unable use %s for template loading.\n", templates);
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
            Map<String, Object> variables = ImmutableMap.of(
                    "title", "HopperMap");
            return new ModelAndView(variables, "home.ftl");
        }
    }	
    
    class LogInHandler implements TemplateViewRoute {
        @Override
        public ModelAndView handle(Request req, Response res) {
            System.out.println("In LoginHandler");
            Map<String, Object> variables = ImmutableMap.of(
                    "title", "HopperLogIn");
            return new ModelAndView(variables, "login.ftl");
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
    void runSparkServer() {
        Spark.setPort(2222);
        Spark.externalStaticFileLocation("src/main/resources/static");
        Spark.exception(Exception.class, new ExceptionPrinter());

        FreeMarkerEngine freeMarker = createEngine();
        Spark.get("/home", new FrontHandler(), freeMarker);
        Spark.get("/login", new LogInHandler(), freeMarker);
    }
}
