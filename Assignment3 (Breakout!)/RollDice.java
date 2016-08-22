/*
* File: RollDice.java
* -------------------
* This program simulates rolling some number of dice until
* the maximal value on the all the dice is rolled.
*/
import acm.program.*;
import acm.util.*;


public class RollDice extends ConsoleProgram {
	
	/* Number of sides on each die */
	private static final int NUM_SIDES = 6;
	
	public void run() {
		int numDice = readInt("Number of dice: "); // the number of dice you want to roll
		int maxRoll = numDice * NUM_SIDES; // max total of all numDice 
		int numRolls = 0;
		
		while (true) { // loop and a half 
			int roll = rollDice(numDice); //
			numRolls++; // execute the half at least once
			if (roll == maxRoll) break;
			println("Rolled " + roll);
		}
		
		println("Rolled " + maxRoll + " after " + numRolls + " rolls");
	}
	
	/* Returns the total of rolling numDice dice */
	private int rollDice(int numDice) {
		int total = 0;
		
		for (int i = 0; i < numDice; i++) {
			total += rgen.nextInt(1, NUM_SIDES);
		}
		
		return total;
	}
	
	/* Private instance variables */
	private RandomGenerator rgen = RandomGenerator.getInstance();
}