package edu.brown.cs.finalproject.database;

import java.util.HashMap;
import java.util.List;
import edu.brown.cs.finalproject.entities.Event;

/**
 * Helper class to package a list of events and two hashmaps containing the save & attend status of each event for the given user
 *
 */
public class BrowseResultsHolder {
	
	private List<Event> events;
	private HashMap<String, Boolean> userSavedEvents;
	private HashMap<String, Boolean> userAttendingEvents;
	
	public BrowseResultsHolder(List<Event> events, HashMap<String, Boolean> userSavedEvents, HashMap<String, Boolean> userAttendingEvents) {
		this.events = events;
		this.userSavedEvents = userSavedEvents;
		this.userAttendingEvents = userAttendingEvents;
	}
	
	public List<Event> getEvents() {
		return events;
	}
	
	public HashMap<String, Boolean> getUserSavedEvents() {
		return userSavedEvents;
	}
	
	public HashMap<String, Boolean> getUserAttendingEvents() {
		return userAttendingEvents;
	}
}
