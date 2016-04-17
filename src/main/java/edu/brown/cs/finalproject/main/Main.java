package edu.brown.cs.finalproject.main;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;

import com.google.common.collect.ImmutableMap;

import freemarker.template.Configuration;
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
		System.out.println("helloworld");

//		lines to instantiate tables in the database and create indices; uncomment and change login name if you want to reset the tables
//		Database db = new Database("/home/ipetrov/course/cs032/final_project/32final/database/finalproject.db");
//		new DatabaseFactory(db).createAndIndexTables();	
		
		
		
		runSparkServer();
		
		System.out.println("all done");
	}
	
	private void runSparkServer() {
		Spark.externalStaticFileLocation("src/main/resources/static");
		Spark.exception(Exception.class, new ExceptionPrinter());
		FreeMarkerEngine freeMarker = createEngine();
		Spark.get("/home", new FrontHandler("main.ftl"), freeMarker);

	}
	
	/**
	 * Creates the server freemarker engine
	 * 
	 * @return a configured freemarker engine
	 */
	private static FreeMarkerEngine createEngine() {
		Configuration config = new Configuration();
		File templates = new File(
				"src/main/resources/spark/template/freemarker");
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
	 * Sends the stacktrace to the front-end at an exception
	 * 
	 */
	private static class ExceptionPrinter implements ExceptionHandler {
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
	
	/**
	 * Handles the get request when initially loading the /home webpage
	 * 
	 * @author ipetrov
	 *
	 */
	private class FrontHandler implements TemplateViewRoute {

		private String htmlUrl;

		FrontHandler(String htmlUrl) {
			this.htmlUrl = htmlUrl;
		}

		@Override
		public ModelAndView handle(Request req, Response res) {

			Map<String, String> variables = ImmutableMap.of("title", "CS32Final");
			return new ModelAndView(variables, htmlUrl);
		}
	}

}
