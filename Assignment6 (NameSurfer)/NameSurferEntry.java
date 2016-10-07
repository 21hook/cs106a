/*
 * File: NameSurferEntry.java
 * --------------------------
 * This class represents a single entry in the database.  Each
 * NameSurferEntry contains a name and a list giving the popularity
 * of that name for each decade stretching back to 1900.
 */

import acm.util.*;

import java.util.*;

public class NameSurferEntry implements NameSurferConstants {
	
	private static final boolean IN = true;
	private static final boolean OUT = false;

	/* Constructor: NameSurferEntry(line) */
	/**
	 * Creates a new NameSurferEntry from a data line as it appears
	 * in the data file.  Each line begins with the name, which is
	 * followed by integers giving the rank of that name for each
	 * decade.
	 */
	public NameSurferEntry(String line) {
		
		// initialize a rankList of type ArrayList
		// init a name
		name = line.substring(0, line.indexOf(' '));
		// init a rank list
		getRanks(line);    
	}
	
	
	/**
	 * Whether a char is a digit or not
	 * @param c a char
	 */
	private boolean isDigit(char c) {
		return  c >= '0' && c <= '9';
	}
	
	/**
	 * Add all ranks associated with that particular name into a object of type ArrayList  
	 * 
	 * @param line a line of type string
	 */
	private void getRanks(String line) {
		int s, e;
		boolean state = OUT;
		
		s = e = 0;
		for(int i=0; i<line.length(); i++) {
			char c = line.charAt(i);
			
			
			if(isDigit(c) && state == OUT) {
				state = IN;
				s = i;
			} else if(state == IN && c == ' ') {// @ToDO || c == '\n'
				state = OUT;
				e = i;
			}
			
			/*
			if(c == ' ') {// @ToDo "gdgdgd\n"
				state = OUT;
				e = i;
			} else if(state == OUT && isDigit(c)) {
				state = IN;
				s = i;
			}
			*/
			if(e > s) rankList.add(Integer.parseInt(line.substring(s, e)));
		
 		}
		
		rankList.add(Integer.parseInt(line.substring(s))); // off by one
	}
	
	
	/* Method: getName() */
	/**
	 * Returns the name associated with this entry.
	 */
	public String getName() { // @ToDo Must return a string
		return name;
	}

	
	/* Method: getRank(decade) */
	/**
	 * Returns the rank associated with an entry for a particular
	 * decade.  The decade value is an integer indicating how many
	 * decades have passed since the first year in the database,
	 * which is given by the constant START_DECADE.  If a name does
	 * not appear in a decade, the rank value is 0.
	 * 
	 * @param decade how many decades have passed 
	 * since the first year in the database,
	 * which is given by the constant START_DECADE.
	 */
	public int getRank(int decade) { 
		if(decade >=0 && decade <=10) return rankList.get(decade);	
		return 0;
	}

	/* Method: toString() */
	/**
	 * Returns a string that makes it easy to see the value of a
	 * NameSurferEntry.
	 * 
	 * i.e. "Sam [58 69 99 131 168 236 278 380 467 408 466]"
	 */
	public String toString() {
		String numOfRanks = rankList.get(0).toString();
		
		for(int i=1; i<rankList.size(); i++) {
			numOfRanks += " " + rankList.get(i);
		}
		return name + " [" + numOfRanks + "]" ;
	}
	
	private String name;
	private ArrayList<Integer> rankList = new ArrayList<Integer>();
}

