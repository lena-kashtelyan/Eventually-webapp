package edu.brown.cs.finalproject.eventsSorter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;

import com.google.common.collect.MinMaxPriorityQueue;

import edu.brown.cs.finalproject.entities.Event;

/**
 * A class containing all neccessary information about the accumulated
 * suggestions
 * 
 * @author ipetrov
 *
 */
public class ResultsCollection {

	MinMaxPriorityQueue<PQNode> results;
	HashSet<Event> events;
	private static final int TOP_NUMBER = 100;	// defines the top number of results that can be returned

	public ResultsCollection() {
		try {
			results = MinMaxPriorityQueue.orderedBy(new PQNodeComparator())
					.create();
		} catch (java.lang.IllegalArgumentException e) {
			System.out
					.println("INTERNAL ERROR: Priority queue cannot be instantiated.");
			System.exit(1);
		}
		events = new HashSet<Event>();
	}

	/**
	 * Getter for the priority queue that stores and orders the generated
	 * suggestions
	 * 
	 * @return MinMaxPriorityQueue, the priority queue that stores and
	 *         orders the generated suggestions
	 */
	public MinMaxPriorityQueue<PQNode> getPQ() {
		return results;
	}

	/**
	 * Adds a PQNode only if the node contains a suggestion that has not been
	 * added.
	 * 
	 * @param node
	 *            , a node with a new suggestion to be inserted in the priority
	 *            queue
	 */
	public void add(PQNode node) {
		Event event = node.getEvent();
		if (!events.contains(event)) {
			results.add(node);
			event = node.getEvent();
			events.add(event);
		}
	}

	/**
	 * Returns true if this ResultsCollection contains the specified element.
	 * More formally, returns true if and only if this set contains an element e
	 * such that (o==null ? e==null : o.equals(e)).
	 * 
	 * @param suggestion
	 *            , a string that is checked if it is contained in the priority
	 *            queue
	 * @return true if this ResultsCollection contains the specified element
	 */
	public boolean contains(String suggestion) {
		return events.contains(suggestion);
	}

	/**
	 * Returns the 200 events from the priority queue with highest priority, or fewer if there
	 * are less than 200 events in the priority queue.
	 * 
	 * @return empty List<Event> (ArrayList) if passed null; a meaningful List<Event> (ArrayList) otherwise
	 */
	public List<Event> returnEventsWithHighestPriority() {
		if (results == null) {
			List<Event> emptyList = new ArrayList<Event>();
			return emptyList;
		}
		int pqSize = results.size();
		List<Event> collection = new ArrayList<Event>();

		if (pqSize > TOP_NUMBER) {
			pqSize = TOP_NUMBER;
		}

		for (int i = 0; i < pqSize; i++) {
			try {
				collection.add(results.removeLast().getEvent());
			} catch (NoSuchElementException e) {
				System.out
						.println("INTERNAL ERROR: Priority queue has no element.");
				System.exit(1);
			}
		}
		return collection;
	}
	
	/**
	 * Returns the 200 events from the priority queue with lowest priority, or fewer if there
	 * are less than 200 events in the priority queue.
	 * 
	 * @return empty List<Event> (ArrayList) if passed null; a meaningful List<Event> (ArrayList) otherwise
	 */
	public List<Event> returnEventsWithLowestPriority() {
		if (results == null) {
			List<Event> emptyList = new ArrayList<Event>();
			return emptyList;
		}
		int pqSize = results.size();
		List<Event> collection = new ArrayList<Event>();

		if (pqSize > TOP_NUMBER) {
			pqSize = TOP_NUMBER;
		}

		for (int i = 0; i < pqSize; i++) {
			try {
				collection.add(results.removeFirst().getEvent());
			} catch (NoSuchElementException e) {
				System.out
						.println("INTERNAL ERROR: Priority queue has no element.");
				System.exit(1);
			}
		}
		return collection;
	}

}
