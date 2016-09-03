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

import javax.xml.stream.events.Characters;

public class Hangman extends ConsoleProgram {

	private static final int REMAINING_CHANCES = 8;
	
	
    // Initialize a hangman at the canvas 
    public void init() {
    	 currentGuessWord = initCurrentGuessWord(currentGuessWord);
    	 canvas = new HangmanCanvas();
    	 add(canvas);
    
    	 //canvas.reset();
	}

    // Run operations for the program
    public void run() { 	
    	/* Welcome to Hangman */ 
    	println("Welocme to Hangman!");
    	
    	  	
    	while (currentGuessWord.indexOf('-') >= 0 && count > 0) {
    		ch = prompt4Hint();
    		process4Char();
    	}
    	  	
    	guessOrNot(currentGuessWord);

    	
	}

    /**
     * Prompt user hints for a new word	
     * 
     */
    private char prompt4Hint() {
		println("The word now looks like this: " + currentGuessWord);
    	println("You have " + count + " guesses left.");
    	String ch = readLine("Your guesss: ");
    	
    	
    	while (ch.length() != 1) {
    		println("You must enter a word."); 
    		ch = readLine("Your guesss: ");
    	}
    	
    	
    	return ch.toUpperCase().charAt(0);
    } 
    
    /**
     * Process what kind of char users has entered
     */
    private void process4Char() {
    	
    	int pos = lexicon.indexOf(ch);
    	
    	if (pos == -1) {
    		println("There si no " + ch + "'s in the word.");
    		count--;
    		canvas.noteIncorrectGuess(ch); // update body part & wrong words
    	} else {
    		currentGuessWord = updateChar(currentGuessWord, pos, ch); // update guessed char 
    		lexicon = removeOccuerence(pos); // replace a new char at the `pos` position
    		println("Your guess is correct");
    		canvas.displayWord(currentGuessWord); // Re-draw label for correct words
    	}
    }
    
    private String initCurrentGuessWord(String str) {
    	int len = lexicon.length();
    	
    	while(len-- > 0) {
    		str += '-';
    	}
    	
    	return str;
    }
    
    private String updateChar(String str, int index, char ch) {
    	str = str.substring(0, index) + ch + str.substring(index+1);
    	return str;
    }

    private String removeOccuerence(int index) {
    	return lexicon.substring(0, index) + Character.toLowerCase(lexicon.charAt(index)) 
    			+ lexicon.substring(index+1);
    }
     
    
	/**
	 * Print out win or lose message according to `currentGuessWord`
	 */
    private void guessOrNot(String str) {
    	if (str.indexOf('-') >= 0) {
    		println("The word was: " + lexicon.toUpperCase());
    		println("You lose.");
    	}  else {
    		println("You guessed the word: " + lexicon.toUpperCase());
    		println("You win.");
    	}
    }

    
    // Instance variables for object states
    private String currentGuessWord = "";
    private int count = REMAINING_CHANCES;
    private char ch; // a new char users entered
    private HangmanCanvas canvas;
    private RandomGenerator rgen = RandomGenerator.getInstance();
    private String lexicon = new HangmanLexicon().getWord(rgen.nextInt(0, 9));

}
