/*
 * File: Pyramid.java
 * 
 * This file is the starter file for the Pyramid problem.
 * It includes definitions of the constants that match the
 * sample run in the assignment, but you should make sure
 * that changing these values causes the generated display
 * to change accordingly.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Pyramid extends GraphicsProgram {

/** Width of each brick in pixels */
	private static final int BRICK_WIDTH = 20; // private -> used within the class

/** Width of each brick in pixels */
	private static final int BRICK_HEIGHT = 12; // static -> a class variable -> all instance variable

/** Number of bricks in the base of the pyramid */ 
	private static final int BRICKS_IN_BASE = 14; // final -> constant variables
	
	public void run() {
		
	 	double x = (getWidth() - BRICK_WIDTH*BRICKS_IN_BASE) /2; // coordinate x 
	 	double y = getHeight() - BRICK_HEIGHT; // coordinate y
	
	 	
		for (int i=1; i<=BRICKS_IN_BASE; i++) { // layers start from 1
			for (int j=i; j<=BRICKS_IN_BASE; j++) { // the number of bricks of each layer
			 	GRect rect = new GRect(x, y, BRICK_WIDTH, BRICK_HEIGHT); // rect's type is class types
			 	rect.setFilled(true); // GRect methods
			 	rect.setFillColor(Color.WHITE); // GRect methods
			 	add(rect); // graphics methods in current scopes
			 	
			 	x += BRICK_WIDTH;
			} 
			x = (getWidth() - BRICK_WIDTH*(BRICKS_IN_BASE-i)) /2; 
			y = getHeight()- BRICK_HEIGHT*(i+1);
		}
		
		
	}
}

