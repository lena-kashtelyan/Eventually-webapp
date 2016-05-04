package edu.brown.cs.finalproject.eventsSorter;

import java.util.Comparator;
import edu.brown.cs.finalproject.eventsSorter.PQNode;

public class PQNodeComparator implements Comparator<PQNode> {

	@Override
	public int compare(PQNode pqnode1, PQNode pqnode2) {
		return Double.compare(pqnode1.getPriority(), pqnode2.getPriority());
	}
}
