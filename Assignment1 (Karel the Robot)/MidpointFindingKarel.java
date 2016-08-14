/*
 * File: MidpointFindingKarel.java
 * -------------------------------
 * When you finish writing it, the MidpointFindingKarel class should
 * leave a beeper on the corner closest to the center of 1st Street
 * (or either of the two central corners if 1st Street has an even
 * number of corners).  Karel can put down additional beepers as it
 * looks for the midpoint, but must pick them up again before it
 * stops.  The world may be of any size, but you are allowed to
 * assume that it is at least as tall as it is wide.
 */

import stanford.karel.*;

public class MidpointFindingKarel extends SuperKarel {

	// You fill in this part
	public void run () {
		put1RowBeepers();
		moveAllBeepersToEnd();
		move1StepWith2Beepers();
		clearAllBeepers();
		
	}
	
	private void turnAround1Step () {
		turnAround();
		move();
	} 
	
	/*
	 * Precondition: facing east at starting point 
	 * Postcondition: facing east in front of the wall
	 */
	private void put1RowBeepers () {
		while(frontIsClear()) {
			putBeeper();
			move();
		}
		putBeeper();
	}
	
	/*
	 * precondition: facing east i
	 * postcondition: facing east
	 */
	private void moveToNextBeeper () {
		while(noBeepersPresent()) {
			move();
		}
	}
	
	private void moveToNextCorner () {
		while (beepersPresent()) {
			if (frontIsClear()) {
				move();
			}
		}
	}
	/*
	 * precondition: facing east in front of no walls 
	 * postcondition: facing east in front of the wall
	 */
	private void moveToEnd () {
		while (frontIsClear()) {
			move();
		}
	}
	/*
	 * Precondition: facing west on beepers 
	 * PostCondition: facing west on no beepers, after moving
	 */
	private void move1BeeperToEnd () {
		turnAround();
		moveToEnd();
		putBeeper();
		turnAround();
		move();
		moveToNextBeeper();
	}
	
	/*
	 * Precondition: facing east in front of the wall
	 * Postcondition: facing east in front of the wall, after moving 
	 */
	private void moveAllBeepersToEnd () {
		turnAround();
		move();
		while (frontIsClear()) { 
			pickBeeper();
			move1BeeperToEnd(); 
		}
		pickBeeper();
		turnAround();
		moveToNextBeeper();
		putBeeper();
	}

	/*
	 * Precondition: facing east in front of the wall
	 * Postcondition: facing east in front of the wall, after moving
	 */
	private void move1StepWith2Beepers () {
		pickBeeper(); 
		pickBeeper();
		putBeeper();
		/*
		 * Precondition: stay on beepers in front of the wall, facing east
		 * Postcondition: stay on no beepers in front of the wall, facing east
		 */
		while(beepersPresent()) { 
			pickBeeper(); 
			if (beepersPresent()) {
				pickBeeper();
			}
			turnAround1Step();
			moveToNextCorner();
			putBeeper();
			turnAround1Step();
			moveToEnd();
		}
	}
	
	/*
	 * precondition: facing east in front of the wall 
	 * postcondition: facing east in middle of the street
	 */
	private void clearAllBeepers () {
		turnAround1Step();
		while (beepersPresent()) {
			pickBeeper();
			move();
		}
		turnAround1Step();
		putBeeper();
		
		
	}
}
