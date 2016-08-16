/*
 RobotFace.java
 
 This program is to draw a robot face in canvas.
*/


import java.awt.Color;

import acm.graphics.GOval;
import acm.graphics.GRect;
import acm.program.*;


public class Face extends GraphicsProgram {
	 
	private static final int HEAD_WIDTH = 200; // Class constant
	private static final int HEAD_HEIGHT = 320; // Class constant 
	private static final int EYE_RADIUS = 15; // Class constant
	private static final int MOUTH_WIDTH = 100;
	private static final int MOUTH_HEIGHT = 30;
	
	
	public void run() {
		addFace(getWidth()/2, getHeight()/2 ); //a positioning point
	}
	
	private void addFace(double x, double y) {
		addHead(x, y); 
		addEye(x-HEAD_WIDTH/4, y-HEAD_HEIGHT/4); // Class constant with decomposition
		addEye(x+HEAD_WIDTH/4, y-HEAD_HEIGHT/4); // Class constant with relevant operations
		addMouth(x, y+HEAD_HEIGHT/4);
	}
	
	private void addHead(double x, double y) {
		GRect head = new GRect(x-HEAD_WIDTH/2,  y-HEAD_HEIGHT/2, HEAD_WIDTH, HEAD_HEIGHT);
		head.setFilled(true);
		head.setFillColor(Color.GRAY);
		add(head);
	} 
	
	private void addEye(double x, double y) {
		GOval eye = new GOval(x-EYE_RADIUS, y-EYE_RADIUS, EYE_RADIUS*2, EYE_RADIUS*2); // Class instant with relevant operations
		eye.setFilled(true);
		eye.setColor(Color.YELLOW);
		add(eye);
	}
	
	private void addMouth(double x, double y) {
		GRect mouth = new GRect(x-MOUTH_WIDTH/2, y-MOUTH_HEIGHT, MOUTH_WIDTH, MOUTH_HEIGHT); // Class instant with relevant operations
		mouth.setFilled(true);
		mouth.setFillColor(Color.WHITE);
		add(mouth);
	}
	
}