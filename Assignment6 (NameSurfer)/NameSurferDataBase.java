import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import acm.util.ErrorException;

/*
 * File: NameSurferDataBase.java
 * -----------------------------
 * This class keeps track of the complete database of names.
 * The constructor reads in the database from a file, and
 * the only public method makes it possible to look up a
 * name and get back the corresponding NameSurferEntry.
 * Names are matched independent of case, so that "Eric"
 * and "ERIC" are the same names.
 */

public class NameSurferDataBase implements NameSurferConstants {
	
	/* Constructor: NameSurferDataBase(filename) */
	/**
	 * Creates a new NameSurferDataBase and initializes it using the
	 * data in the specified file.  The constructor throws an error
	 * exception if the requested file does not exist or if an error
	 * occurs as the file is being read.
	 */
	public NameSurferDataBase(String filename) {

		// initialize a nameRanks of type HashMap
	    BufferedReader rd = null;
	    
	    rd = openFileReader(filename);
	    try{
	    	while(true) {// @ToDO IO only once
		    	String line = rd.readLine();
		    	if(line == null) break;
		    	NameSurferEntry entry = new NameSurferEntry(line); // @ToDo
		    	nameRanks.put(getName(line), entry);
	    	}
	    	rd.close();
	    } catch(IOException ex) {
	    	throw new ErrorException("An I/O exception has occurred");
            
	    }
	}
	
	
	/**
	 * precondition: a line start with a name followed by a sequence of ranks which is separated by a space
	 * Returns the name associated with a line of type string.	
	 * 
	 * @param line  a line of string 
	 */
	private String getName(String line) { // @ToDo Must return a string
		String name = "";
		
		for(int i=0; i<line.length(); i++) 
			if(line.charAt(i) == ' ')  return name = line.substring(0, i);
				
		return name;
	}
	
	/**
	 * Open a file  
	 * 
	 * @param name a file name
	 * @return an object of type BufferedReader 
	 */
	private BufferedReader openFileReader(String name) {
		BufferedReader rd = null;
		
		try{
			rd = new BufferedReader(new FileReader(name));
		} catch(IOException ex) { // @ToDO
			throw new ErrorException("Can't open that file.");
		}
		
		return rd;
	}
	
	/* Method: findEntry(name) */
	/**
	 * Returns the NameSurferEntry associated with this name, if one
	 * exists.  If the name does not appear in the database, this
	 * method returns null.
	 */
	public NameSurferEntry findEntry(String name) {
		
		for(String s: nameRanks.keySet()) {
			if(s.equals(name)) return nameRanks.get(s);
		}
		return null;
	}
	
	
	private Map<String, NameSurferEntry> nameRanks = new HashMap<String, NameSurferEntry>();
 
}

