/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;
import java.util.*; // Class: ArrayList<type>
import javax.xml.stream.events.Characters;

public class Hangman extends ConsoleProgram {

	private static final int REMAINING_CHANCES = 8;
	
	
    // Initialize a hangman at the canvas 
    public void init() {
    	 canvas = new HangmanCanvas();
    	 add(canvas);
	}

    // Run operations for the program
    public void run() {
    	int len = lexicon.length();
    	String ch;
    	String currentGuessWords = "";
    	ArrayList<String> lexiconList = new ArrayList<String>();
    	
 
    	for(int i=0; i<len; i++) {
    		lexiconList.add(lexicon.charAt(i) + "");
    		currentGuessWords += "-";
    	}
    	
    	
    	println("Welocme to Hangman!");
    	canvas.reset(); // @ToDo
    	
    	while (currentGuessWords.indexOf("-") >= 0 && count > 0) { // Still being successful && unsuccessful 
    		println("The word now looks like this: " + currentGuessWords);
        	println("You have " + count + " guesses left.");
	
        	while(true) {
            	ch = readLine("Your guesss: ");
            	if (ch.length() == 1) break;  
        	}
        	
        	currentGuessWords = processChar(ch.toUpperCase(), currentGuessWords, lexiconList); 
    	}
    	  	
    	finalResult(currentGuessWords);

    	
	}
    
    /**
     * If users guess correctly, Update `str` string.
     */
    private String processChar(String ch, String str, ArrayList<String> strLi) {
    	
    	int pos = strLi.indexOf(ch);
    	
    	if (pos == -1) {
    		println("There si no " + ch + "'s in the word.");
    		count--;
    		
    		canvas.noteIncorrectGuess(ch.charAt(0)); // update body part & wrong words
    	} else {
    		str = updateChar(pos, str); // @ToDo Passing by copy
    		strLi.set(pos, ch.toLowerCase()); // replace a new char at the `pos` position
    		println("Your guess is correct");
    		
    		canvas.displayWord(str); // Re-draw label for correct words
    	}
    	println(str + " " + strLi + " " + ch);
    	
    	return str;
    }
    
    
    /**
     *  Update what users have guessed
     */
    private String updateChar(int index, String str) {
    	return str.substring(0, index) + lexicon.charAt(index) 
		+ str.substring(index+1);
    }
    
    
	/**
	 * Print out win or lose message
	 */
    private void finalResult(String str) {
    	if (str.indexOf('-') >= 0) {
    		println("The word was: " + lexicon.toUpperCase()); 
    		println("You lose.");
    	}  else {
    		println("You guessed the word: " + lexicon.toUpperCase());
    		println("You win.");
    	}
    }

    
    // Instance variables for object states
    //private String currentGuessWord = ""; //
    private int count = REMAINING_CHANCES;
    private HangmanCanvas canvas;
    private RandomGenerator rgen = RandomGenerator.getInstance();
    private String lexicon = new HangmanLexicon().getWord(rgen.nextInt(0, 9));

}
