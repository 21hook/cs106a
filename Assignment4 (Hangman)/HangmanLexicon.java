/*
 * File: HangmanLexicon.java
 * -------------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.*; // Class: ArrayList
import acm.util.*; // Class: ErrorException


public class HangmanLexicon {

	// This is the HangmanLexicon constructor
	public HangmanLexicon() {
		
		BufferedReader rd = null;
		
		rd = openFileReader("HangmanLexicon.txt");
		
		try{
			while(true) {
				if (rd.readLine() == null) break;
				lexiconList.add(rd.readLine());
			}
			rd.close();
		} catch(IOException ex) {
			throw new ErrorException(ex); // @ToDo Throw to upper functions
		}
	}
	
	private BufferedReader openFileReader(String f) {
		BufferedReader rd = null;
		
		try{ // put open operations in try 
			rd = new BufferedReader(new FileReader(f));
		} catch (IOException ex) {
			throw new ErrorException(ex);
		}
		
		return rd;
	}
		
/** Returns the number of words in the lexicon. */
	public int getWordCount() {
		return lexiconList.size();
	}

/** Returns the word at the specified index. */
	public String getWord(int index) {

		if (index < lexiconList.size() && index >= 0) {
			lexiconList.get(index);
			return lexiconList.get(index);
		} else 
			throw new ErrorException("getWord: Illegal index");
		
	};
	
	private ArrayList<String> lexiconList = new ArrayList<String>(); // @ToDo Instance property, Instance state btn constructor methods 

}
