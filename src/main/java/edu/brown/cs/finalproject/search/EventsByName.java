package edu.brown.cs.finalproject.search;

import static java.lang.String.format;
import static java.lang.System.currentTimeMillis;
import static java.lang.System.out;

import com.restfb.*;
import com.restfb.json.JsonArray;
import com.restfb.json.JsonObject;
import com.restfb.types.Event;
import com.restfb.types.Page;
import com.restfb.types.Post;
import com.restfb.types.Url;
import com.restfb.types.User;

public class EventsByName {
	
	public EventsByName() {
		
		FacebookClient facebookClient23 = new DefaultFacebookClient("CAACEdEose0cBABKqc6v8oZAI53CdOZA8wUT1kOJIvS40JizzpZCF5wkEXFL7B70Q5q20bw9InZBE5u6GS6FTPwVMlInDEw2tlyZCp79Q1Xag4BZA3gx7topnaW8axjVsomHzVFN2knBS3N8ap4jFSvd81IpKmlF37QOQfWOZC0UKoVRNHaZC7lM2KDZBTaLKZBuWrEHeO1AdHvZAgZDZD", Version.LATEST);
//		FacebookClient facebookClient20 = new DefaultFacebookClient(accessToken, Version.LATEST);
				
		Connection<Event> eventList = 
			    facebookClient23.fetchConnection("search", Event.class,
			    Parameter.with("q", "talk"), Parameter.with("type", "event"), Parameter.with("limit", 1000));
		
		
		System.out.println("Number of events: " + eventList.getData().size());
		    while (eventList.hasNext()) {
		    	eventList = facebookClient23.fetchConnectionPage(eventList.getNextPageUrl(), Event.class);
				System.out.println("Number of events: " + eventList.getData().size());
		    }
		

		
//		int counter = 1;
//		for (Event event : eventList.getData()) {
//			System.out.println(counter++);
//			System.out.println(event);
//			System.out.println("----------------");
//		}
//		System.out.println("Number of events: " + eventList.getData().size());
		
		
	}

}
