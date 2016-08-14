/*
 VoteCountingKarel.java
 
 Remove beepers from the specific & partially enclosed rectangles.
*/

import stanford.karel.*;

public class VoteCountingKarel extends SuperKarel {
	
	public void run () {
		
		while ( frontIsClear() ) {
			moveToBeepers();
		}
		
		if ( noBeepersPresent() ) {
			rm1ColBeepers();
		}
		
	}
	
	
	/*
	 precondition: facing east in the middle of the rectangle 
	 postcondition: facing east in the middle of the another rectangle
	*/
	private void moveToBeepers () {
		
		if ( noBeepersPresent()) {
			rm1ColBeepers();
		}
		move();
		 
	}

	
	/*
	 precondition: facing east in the middle of the rectangle containing beepers 
	 postcondition: facing east, after removing one column beepers of the rectangle  
	*/
	private void rm1ColBeepers () {
		
		turnRight();
		move();
		
		rmRectBeepers();
		
		turnAround();
		move2Steps();
		
		rmRectBeepers();
		
		turnAround();
		move();
		turnLeft();
		
	}
	
	private void move2Steps () {
		move();
		move();
	}

	
	/*
	 precondition: in the bottom/top of the rectangle 
	 postcondition:  in the bottom/top of the rectangle, after removing beepers
	*/
	private void rmRectBeepers () {
		while ( beepersPresent() ) {
			pickBeeper();
		}
	}
	
	


}
