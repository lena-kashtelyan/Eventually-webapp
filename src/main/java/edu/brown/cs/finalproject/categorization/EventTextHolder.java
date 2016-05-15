package edu.brown.cs.finalproject.categorization;

/**
 * Helper class to unite a name and a description of an event in one.
 *
 */
class EventTextHolder {

	private String textHolder;
	
	EventTextHolder(String name, String description) {
		this.textHolder = name + " " + description;
		textHolder = textHolder.toLowerCase();
	}
	
	String getText() {
		return textHolder;
	}
	
	boolean contains(String token) {
		return textHolder.contains(token);
	}

}
