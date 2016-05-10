package edu.brown.cs.finalproject.database;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

public class PublicFbEventsWriterThread extends Thread {
	
	private JsonArray currentList;
	
	public PublicFbEventsWriterThread(JsonArray currentList) {
		
		this.currentList = currentList;
		
	}
	
	@Override
	  public void run() {
	    for (JsonElement eventElement : currentList) {
	        PublicFBEventsWriter.processEvent(eventElement);
	      }	
	}

}
