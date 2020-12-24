package studentSystem;

import java.util.Comparator;

public class ProfByYearComparator implements Comparator<Prof> {

	public ProfByYearComparator() {
		// TODO Auto-generated constructor stub
	}
	public int compare(Prof a, Prof b) {
		return a.getYear() - b.getYear();
	}
	
		 	/**
	 	   * Compares two Profs by their year, in ascending order.
	 	   * 
	 	   *  
	 	   */
	 	   
	
	

}  