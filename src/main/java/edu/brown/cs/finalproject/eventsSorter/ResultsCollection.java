package edu.brown.cs.finalproject.eventsSorter;

import java.util.HashSet;
import java.util.NoSuchElementException;
import com.google.common.collect.MinMaxPriorityQueue;

/**
 * A class containing all neccessary information about the accumulated
 * suggestions
 * 
 * @author ipetrov
 *
 */
public class ResultsCollection {

	MinMaxPriorityQueue<PQNode> results;
	HashSet<String> suggestions;
	private static final int TOP_NUMBER = 200;

	public ResultsCollection() {
		try {
			results = MinMaxPriorityQueue.orderedBy(new PQNodeComparator())
					.create();
		} catch (java.lang.IllegalArgumentException e) {
			System.out
					.println("INTERNAL ERROR: Priority queue cannot be instantiated.");
			System.exit(1);
		}
		suggestions = new HashSet<String>();
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
		String suggestion = node.getSuggestion();
		if (!suggestions.contains(suggestion)) {
			results.add(node);
			suggestion = node.getSuggestion();
			suggestions.add(suggestion);
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
		return suggestions.contains(suggestion);
	}

	/**
	 * Returns the top 5 suggestions from the priority queue, or fewer if there
	 * are less than 5 suggestions in the priority queue.
	 * 
	 * @return null if passed null; a meaningful string array otherwise
	 */
	public String[] returnTopSuggestions() {
		if (results == null) {
			return new String[0];
		}
		int pqSize = results.size();
		String[] collection = null;

		if (pqSize < TOP_NUMBER) {
			collection = new String[pqSize];
		} else {
			collection = new String[TOP_NUMBER];
		}

		for (int i = 0; i < collection.length; i++) {
			try {
				collection[i] = results.removeLast().getSuggestion();
			} catch (NoSuchElementException e) {
				System.out
						.println("INTERNAL ERROR: Priority queue has no element.");
				System.exit(1);
			}
		}
		return collection;
	}

}
