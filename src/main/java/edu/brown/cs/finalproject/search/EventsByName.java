package edu.brown.cs.finalproject.search;


import com.restfb.*;
import com.restfb.types.Event;

/*
 * Fetches public Facebook events by name (for Machine Learning training)
 */
public class EventsByName {
	
	public EventsByName() {
		
		FacebookClient facebookClient23 = new DefaultFacebookClient("CAACEdEose0cBAFhop73n1499EV6s6930HXx9zIX1jpyjmQJZA1jGjHKCrafGZCjI4hhbFRTIyqkPOzaZBoRiDgKoh4FjT1MDwZBmQQNfaKdQRK9tpjoWncfisiLJetG7i5qXhOknDFsYhvn85ZAxnD2PUEaEjarstOT5v3EGbWNt47FAjBCwdRylzXDWzqNuzIsfdMTEKvwZDZD", Version.LATEST);
		
		Connection<Event> eventList = 
			    facebookClient23.fetchConnection("search", Event.class,
			    Parameter.with("q", "talk"), Parameter.with("type", "event"), Parameter.with("limit", 1000));
		
		int counter = 1;
		for (Event event : eventList.getData()) {
			System.out.println(counter++);
			System.out.println(event);
			System.out.println("----------------");
		}
		System.out.println("Number of events: " + eventList.getData().size());
		
	}

}
