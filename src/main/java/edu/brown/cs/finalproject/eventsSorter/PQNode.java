package edu.brown.cs.finalproject.eventsSorter;


/**
 * This class represents a node in the priority queue with a Priority object as
 * a point of comparison
 * 
 * @author ipetrov
 *
 */
public class PQNode {

	Priority priorityIndex;
	String suggestion;

	public PQNode(Priority priorityIndex, String suggestion) {
		this.priorityIndex = priorityIndex;
		this.suggestion = suggestion;
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
	 * Getter for the suggestion stored in the priority queue node
	 * 
	 * @return String, the suggestion stored in the priority queue
	 */
	public String getSuggestion() {
		return suggestion;
	}

}
