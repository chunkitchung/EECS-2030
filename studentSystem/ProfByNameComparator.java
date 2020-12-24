package studentSystem;

import java.util.Comparator;

public class ProfByNameComparator implements Comparator<Prof> {

	public ProfByNameComparator() {
		// TODO Auto-generated constructor stub
	}
	public int compare(Prof a, Prof b) {
		return a.getName().compareTo(b.getName());
	}
	
		 	/**
	 	   * Compares two Profs by their year, in ascending order.
	 	   * 
	 	   *  
	 	   */
}  