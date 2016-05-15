package edu.brown.cs.finalproject.categorization;

import edu.brown.cs.finalproject.entities.Event;

public class EventCategorizer {
	
	public EventCategorizer() {
		
	} 
	
	public String categorize(String name, String description) {
		
		EventTextHolder textHolder = new EventTextHolder(name, description);
		String category = "other";
		
		if (textHolder.contains("sports") || textHolder.contains("game")) {
			category = "sports";
		}
		
		if (textHolder.contains("club") || textHolder.contains("party")) {
			category = "nightlife";
		}
		
		if (textHolder.contains("lecture") || textHolder.contains("speak")) {
			category = "public lecture";
		}
		
		if (textHolder.contains("workshop")) {
			category = "workshop";
		}
		
		if (textHolder.contains("food") || textHolder.contains(" eat")) {
			category = "food fest";
		}
		
		if (textHolder.contains("movie") || textHolder.contains("film") || textHolder.contains(" art")) {
			category = "movies and art";
		}
		
		if (textHolder.contains("theater") || textHolder.contains("performance")) {
			category = "theater and performance";
		}
		
		if (textHolder.contains("religio") || textHolder.contains("cultur")) {
			category = "religious and cultural celebration";
		}
		
		return category;
	}

}
