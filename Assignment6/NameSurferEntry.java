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

/* Constructor: NameSurferEntry(line) */
/**
 * Creates a new NameSurferEntry from a data line as it appears
 * in the data file.  Each line begins with the name, which is
 * followed by integers giving the rank of that name for each
 * decade.
 */
	public NameSurferEntry(String line) {
	
		lineEntry=line;
		inventoryLoad();
	}

/* Method: getName() */
/**
 * Returns the name associated with this entry.
 */
	public String getName() {
	
			return name;
	}

/* Method: getRank(decade) */
/**
 * Returns the rank associated with an entry for a particular
 * decade.  The decade value is an integer indicating how many
 * decades have passed since the first year in the database,
 * which is given by the constant START_DECADE.  If a name does
 * not appear in a decade, the rank value is 0.
 */
	public int getRank(int decade ) {
			
			if(decades.get(decade)==null)
				return 0;
			else
				return decades.get(decade);	
		
	}
	/**
	 * metoda prilikom pozivanja konstruktora izdvaja ime i rejting imena po goidnama
	 */

	private void inventoryLoad(){
		int endName=0;
			if(lineEntry.contains(" ")) {
				 endName = lineEntry.indexOf(" ");
				name=lineEntry.substring(0, endName);}
			else
				name=lineEntry;
				
			int startDecades=START_DECADE;
			if(endName>0) {
				
				int startNum =lineEntry.indexOf(" ")+1;	
				int andNum= lineEntry.indexOf(" ",startNum);
		
				while(andNum>0) {
					int numNam=Integer.parseInt(lineEntry.substring(startNum, andNum));
					decades.put(startDecades, numNam);
					startNum =lineEntry.indexOf(" ",startNum+1)+1;
					andNum= lineEntry.indexOf(" ",startNum);
					startDecades+=10;	
				} 	
				int numNam=Integer.parseInt(lineEntry.substring(startNum));
				decades.put(startDecades, numNam);
				
			}else {
					while(startDecades!=2000) {
						decades.put(startDecades, 0);
						startDecades+=10;
					}
				}
	}
		
	
/* Method: toString() */
/**
 * Returns a string that makes it easy to see the value of a
 * NameSurferEntry.
 */
	public String toString() {
		
		return name + " ["+ lineEntry.substring(lineEntry.indexOf(" ")+1)+"]";
	}
	

	private String name;
	private String lineEntry;
	private HashMap<Integer,Integer> decades= new HashMap<Integer,Integer>();

}

