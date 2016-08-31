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

    public void run() {
    	
    	println("Welocme to Hangman!");
    	
    	while (true) {
    		println("The word now looks like this: --------");
        	println("You hava 8 guesses left.");
        	String ch = readLine("Your guesss: ");
        	while (ch.length() != 1) {
        		println("You must enter a word."); 
        		ch = readLine("Your guesss: ");
        	}
        	
        	if () {
        		println("There si no A's in the word."); 
        	} else {
        		println("Your guess is correct");
        		println("The word now looks like this:" + );
        	}
    	}
    	
	}
    
    private boolean check4Word(char c) {
    	
    }
    
    
    String lexicon = new HangmanLexicon().getWord(1);

}
