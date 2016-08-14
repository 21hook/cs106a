/*
 * File: Hailstone.java
 * 
 * This file is the starter file for the Hailstone problem.
 */

import acm.program.*;

public class Hailstone extends ConsoleProgram {
	private static final int SENTINAL = 1; // This sentinal must be 1.
	
	public void run() {
		int n;
		int count = 0;
	
		
		while ((n = readInt("Enter a number: ")) <= 1) {
			println("Please enter a positive number, and must not be 1.");
		}
		
		while (n != SENTINAL ) {
			if (n%2 == 0) 
				println(n + " is even, so I take half: " + (n = n/2));
			else 
				println(n + " is odd, so I take 3n+1: " + (n = 3*n+1));

			count++;
		}
		println("The process took " + count + " to reach 1");
	
		
	}
}

