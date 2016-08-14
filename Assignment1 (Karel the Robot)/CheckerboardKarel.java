/*
 * File: CheckerboardKarel.java
 * ----------------------------
 * When you finish writing it, the CheckerboardKarel class should draw
 * a checkerboard using beepers, as described in Assignment 1.  You
 * should make sure that your program works for all of the sample
 * worlds supplied in the starter folder.
 */

import stanford.karel.*;

public class CheckerboardKarel extends SuperKarel {

	public void run () {
		put1RowBeepers();
	
		while (leftIsClear()) {
			repositionForWest();
			put1RowBeepers();
			
			if (rightIsClear()) {
				repositionForEast();
				put1RowBeepers();
			} else {
				turnAround();
			}
		}
		
		turnLeft();
	}
	
	private void repositionForWest () {
		turnLeft();
		move();
		turnLeft();
	}
	
	private void repositionForEast () {
		turnRight();
		move();
		turnRight();
	}
	 	
	private void put1RowBeepers () {
		
		putBeeper();
		while (frontIsClear()) {
			move();
			if(frontIsClear()){
				move();
				putBeeper();
			}

		}
		
		if (leftIsClear()) {
			if (beepersPresent()) {
				turnAround();
				move();
				turnAround();
			}
		}
	}

	

		
	
}

