/*
 RobotFace.java
 
 This program is to draw a robot face in canvas.
*/


import acm.program.*;
import acm.graphics.*;
import java.awt.*;


public class RobotFace extends GraphicsProgram {
	private static final int HEAD_WIDTH = 200;
	private static final int HEAD_HEIGHT = 320;
	private static final int EYE_RADIUS = 15;
	private static final int MOUTH_WIDTH = 100;
	private static final int MOUTH_HEIGHT = 30;
	
	
	public void run () {
		double headX = (getWidth() - HEAD_WIDTH) /2;
		double headY = (getHeight() - HEAD_HEIGHT) /2;
		double eyeXLeft = headX + (HEAD_WIDTH/4 - EYE_RADIUS);
		double eyeYLeft = headY + (HEAD_HEIGHT/4 - EYE_RADIUS);
		double eyeXRight = headX + (HEAD_WIDTH/4*3 - EYE_RADIUS);   
		double eyeYRight = eyeYLeft;
		double eyeDiameter = EYE_RADIUS*2;
		double mouthX = headX + (HEAD_WIDTH - MOUTH_WIDTH) /2;
		double mouthY = headY + HEAD_HEIGHT - ((HEAD_HEIGHT - MOUTH_HEIGHT)/4 + MOUTH_HEIGHT);
	
		GRect head = new GRect(headX, headY, HEAD_WIDTH, HEAD_HEIGHT);
		head.setFilled(true);
		head.setFillColor(Color.GRAY);
		GOval eyeLeft = new GOval(eyeXLeft, eyeYLeft, eyeDiameter, eyeDiameter);
		eyeLeft.setFilled(true);
		eyeLeft.setColor(Color.YELLOW);
		GOval eyeRight = new GOval(eyeXRight, eyeYRight, eyeDiameter, eyeDiameter);
		eyeRight.setFilled(true);
		eyeRight.setColor(Color.YELLOW);
		GRect mouth = new GRect(mouthX, mouthY, MOUTH_WIDTH, MOUTH_HEIGHT);
		mouth.setFilled(true);
		mouth.setColor(Color.WHITE);
		
		
		add(head);
		add(eyeLeft);
		add(eyeRight);
		add(mouth);
	}
}