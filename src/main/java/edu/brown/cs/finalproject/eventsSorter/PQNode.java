package edu.brown.cs.finalproject.eventsSorter;

import edu.brown.cs.finalproject.entities.Event;


/**
 * This class represents a node in the priority queue with a Priority object as
 * a point of comparison
 * 
 * @author ipetrov
 *
 */
public class PQNode {

	Priority priorityIndex;
	Event event;

	public PQNode(Priority priorityIndex, Event event) {
		this.priorityIndex = priorityIndex;
		this.event = event;
	}

	/**
	 * Getter for the priority index of the node
	 * 
	 * @return double, the priority index of the node
	 */
	public double getPriority() {
		return priorityIndex.getPriority();
	}

	/**
	 * Setter for the priority index of the node
	 * 
	 * @param newPriority
	 *            , a Priority object containing what the new priority should be
	 * @return the updated Priority object
	 */
	public Priority setPriority(Priority newPriority) {
		priorityIndex = newPriority;
		return priorityIndex;
	}

	/**
	 * Setter for the priority index of the node
	 * 
	 * @param newPriority
	 *            , a double indicating what the updated index should be
	 * @return the updated Priority object
	 */
	public Priority setPriority(double newPriority) {
		priorityIndex.setPriority(newPriority);
		return priorityIndex;
	}

	/**
	 * Getter for the event stored in the priority queue node
	 * 
	 * @return String, the suggestion stored in the priority queue
	 */
	public Event getEvent() {
		return event;
	}

}
