package edu.brown.cs.finalproject.eventsSorter;

/**
 * This class stores information about the priority of a given PQNode The
 * higher the priority, the forward the PQNode is sorted.
 * 
 * @author ipetrov
 *
 */
public class Priority {

	double currentPriority;

	public Priority(double initialPriority) {
		if (initialPriority >= 0) {
			currentPriority = initialPriority;
		} else {
			System.out
					.println("INTERNAL ERROR: The priority of a node cannot be negative.");
			System.exit(1);
		}
	}

	/**
	 * Getter for the priority index of a suggestion
	 * 
	 * @return double, the priority index associated with a suggestion
	 */
	public double getPriority() {
		return currentPriority;
	}

	/**
	 * Setter for the priority index of a suggestion
	 * 
	 * @param newPriority
	 *            , a double representing the new priority index of a suggestion
	 * @return double, the priority index associated with a suggestion
	 */
	public double setPriority(double newPriority) {
		currentPriority = newPriority;
		return currentPriority;
	}
}
