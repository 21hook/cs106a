/*
 * RandomCircles.java
 * 
 * This program draws a set of ten circles with different sizes, positions, and colors.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

public class RandomCircles extends GraphicsProgram {

	private static final int CIRCLE_NUM = 10;
	
	public void run() {
	 
		for (int i=0; i<CIRCLE_NUM; i++) { // local variables are not important to objects state
			int radius = rgen.nextInt(5, 50); // local variables are important to method operations 
			double x = rgen.nextDouble(0, getWidth() - radius*2);
			double y = rgen.nextDouble(0, getHeight() - radius*2);
			
			GOval circle = new GOval(x, y, radius, radius);
			circle.setFilled(true);
			circle.setColor(rgen.nextColor()); // return value of that type -> receive value of that type
			
			add(circle);
		} 
	}
	
	private RandomGenerator rgen = RandomGenerator.getInstance();
}