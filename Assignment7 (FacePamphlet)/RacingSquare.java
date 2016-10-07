
import java.awt.Color;

import acm.graphics.*;
import acm.util.RandomGenerator;

public class RacingSquare extends GRect implements Runnable { //include that inheritance & interface

	public RacingSquare(int index, boolean[] finished) { // refer to a method of that inheritance explicitly
		super(SIZE, SIZE);
		setFilled(true);
		myIndex = index;
		finishers = finished;
	}
	
	/* Note: put conditions into a method */
	public void run() { //implement the method of that interface 
		finishers[myIndex] = false; // @ToDo put conditions into a method
		
		for(int i=0; i<100; i++) {
			pause(rgen.nextInt(50, 150));
			move(STEP, 0);
		}
		
		// pause a little milliseconds after finished the line
		pause(50);		
		// check all other tracers before determining the champion 
		int counter = 0;
		for(int i=0; i<finishers.length; i++) {
			if(finishers[i]) counter++;
		}
		
		finishers[myIndex] = true;
		
		// if none is head of myself, I'm the champion
		if(counter == 0) setColor(Color.red);
		
		
	} 
	
	
	private static final int STEP = 5;
	private static final int SIZE = 50;
	private static boolean[] arr; // class variables
	
	private int myIndex;
	private boolean[] finishers; // shared instance variables => class variable => multiple thread communication  
	private RandomGenerator rgen = RandomGenerator.getInstance();

}
