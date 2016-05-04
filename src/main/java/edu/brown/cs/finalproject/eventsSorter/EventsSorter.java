package edu.brown.cs.finalproject.eventsSorter;

import java.util.List;

import edu.brown.cs.finalproject.database.DatabaseManager;
import edu.brown.cs.finalproject.entities.Event;

public class EventsSorter {
	
	public EventsSorter() {
	}
	
	/**
	 * Sorts a list of events based on location; uses a priority queue
	 * @param List<Event> 
	 * @return List<Event>
	 */
	public List<Event> sortEventsByLocation(List<Event> unsortedEventsList, double currLatitute, double currLongitude) {
		
		ResultsCollection resultsCollection = new ResultsCollection();
		
		for (Event tempEvent : unsortedEventsList) {
			
			double euclideanDistance = this.findDistance(currLatitute, currLongitude, tempEvent.getLatitude(), tempEvent.getLongitude());
			
			PQNode tempPQNode = new PQNode(new Priority(euclideanDistance), tempEvent);
			resultsCollection.add(tempPQNode);
		}
		
		List<Event> outcome = resultsCollection.returnEventsWithLowestPriority();
		
		for (Event e : outcome) {
			System.out.println(e.getID() + " " + this.findDistance(currLatitute, currLongitude, e.getLatitude(), e.getLongitude()));
		}

		return outcome;
	}
	
	/**
	 * Sorts a list of events based on attending count; uses a priority queue
	 * @param unsortedEventsList
	 * @return
	 */
	public List<Event> sortEventsByAttendingCount(List<Event> unsortedEventsList) {

		ResultsCollection resultsCollection = new ResultsCollection();

		for (Event tempEvent : unsortedEventsList) {
			PQNode tempPQNode = new PQNode(new Priority(tempEvent.getAttendingCount()), tempEvent);
			resultsCollection.add(tempPQNode);
		}

		List<Event> outcome = resultsCollection
				.returnEventsWithHighestPriority();

		for (Event e : outcome) {
			System.out.println(e.getID() + " " + e.getAttendingCount());
		}

		return outcome;

	}
	
	/**
	 * Finds the square of the Euclidean distance between two points; works for a universe of two dimensions
	 * @param firstPointLat
	 * @param firstPointLng
	 * @param secondPointLat
	 * @param secondPointLng
	 * @return double, the square of the Euclidean distance between the two points
	 */
	private double findDistance(double firstPointLat, double firstPointLng, double secondPointLat, double secondPointLng) {
		double sumOfSquares = 0;
		sumOfSquares += Math.pow((firstPointLat - secondPointLat), 2);
		sumOfSquares += Math.pow((firstPointLng - secondPointLng), 2);
		return sumOfSquares;
	}

}
