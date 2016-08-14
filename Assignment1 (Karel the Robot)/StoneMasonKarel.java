/*
 * File: StoneMasonKarel.java
 * --------------------------
 * The StoneMasonKarel subclass as it appears here does nothing.
 * When you finish writing it, it should solve the "repair the quad"
 * problem from Assignment 1.  In addition to editing the program,
 * you should be sure to edit this comment so that it no longer
 * indicates that the program does nothing.
 */

import stanford.karel.*;

public class StoneMasonKarel extends SuperKarel {

	public void run () {
		putAllBeepers();		
		putLineOfBeepers();
	}
	
	private void backToOrigin () {
		while (frontIsClear()) {
			move();
		}
	}
	
	private void move4Steps () {
		for (int i=0; i<4; i++) {
			move();
		}
	}
	
	private void put1LineBeepers () {
		while (frontIsClear()) {
			if (noBeepersPresent()) {
				putBeeper();
			}
			move();
		}
		if (noBeepersPresent()) {
			putBeeper();
		}
		
	} 
	
	private void putLineOfBeepers () {
		turnLeft();
		put1LineBeepers();
		turnAround();
		backToOrigin();
		turnLeft();
	}
	
	private void putAllBeepers() {
		while(frontIsClear()) {
			putLineOfBeepers();
			move4Steps();
		}
			
	}
	


}
