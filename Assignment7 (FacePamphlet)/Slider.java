/**
 * Construct a slider ...
 * 
 * @author Administrator
 *
 */
import java.awt.Color;

import acm.graphics.*;

public class Slider extends GRect implements Runnable {

	/**
	 * Construct a slider using a super-class GRect 
	 */
	public Slider(int size, Color color) {
		super(size, size); // -> new GRect(size, size);
		setFilled(true);
		setColor(color);
	} 
	
	/**
	 * Run thread objects
	 */
	public void run() {
		for(int i=0; i< 1000/STEP; i++) {
			pause(40);
			move(STEP, 0);
		}
	}
	
	
	private static final int STEP = 5;
}
