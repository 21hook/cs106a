/*
 * File: FindRange.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the FindRange problem.
 */

import acm.program.*;

public class FindRange extends ConsoleProgram {
	
	private static final int SENTINAL = 0;
	
	public void run() {
		int largestNum, smallestNum;
		int x, y;
		
		smallestNum = largestNum = 0;
		println("This program finds the largest and smallest number.");
		x = readInt("? ");
		if (x == SENTINAL) {
			println("There is no the largest and smallest number.");
		}
		else if ( (y = readInt("? ")) == SENTINAL) {
			println("smallest: " + x + "\n");
			println("largest: " + x);
		} else {
			if (x>y) {
				largestNum = x;
				smallestNum = y;
			} else { 
				largestNum = y;
				smallestNum = x;
			}
			
			while((y = readInt("? ")) != SENTINAL) {	
				if (y > largestNum) 
					largestNum = y;
				if (y < smallestNum)
					smallestNum = y;

			}
			println("smallest: " + smallestNum);
			println("largest: " + largestNum);
		}
		
	}
}

