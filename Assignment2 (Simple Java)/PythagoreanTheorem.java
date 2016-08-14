/*
 * File: PythagoreanTheorem.java
 * Name: 
 * Section Leader: 
 * -----------------------------
 * This file is the starter file for the PythagoreanTheorem problem.
 */

import acm.program.*;

public class PythagoreanTheorem extends ConsoleProgram {
	public void run() {
		int a, b;
		double c;
		
		println("Enter values to compute Pythagorean theorem");
		a = readInt("a: ");
		b = readInt("b: ");
		c = Math.sqrt(a*a + b*b);
		println("c = " + c);
		
	}
}
