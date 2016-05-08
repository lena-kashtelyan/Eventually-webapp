package edu.brown.cs.finalproject.categorization;

class EventTextHolder {
	
	private String name;
	private String description;
	private String textHolder;
	
	EventTextHolder(String name, String description) {
		this.name = name;
		this.description = description;
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
