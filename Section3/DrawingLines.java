/*
 * DrawingLines.java
 * 
 * This program waits for dots of the click of users on the screen, which is to 
 * connect them as a line. 
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.event.*;

public class DrawingLines extends GraphicsProgram {
	
	public void run() {
		addMouseListeners();
	}
	
	public void mousePressed(MouseEvent e) {
		
		if (bool-- == 1) 
			origin = new GPoint(e.getPoint());
		
		line = new GLine(origin.getX(), origin.getY(), e.getX(), e.getY());
		add(line);
		
	}
	
	public void mouseDragged(MouseEvent e) {
		line.setEndPoint(e.getX(), e.getY());
	}
	
	public void mouseReleased(MouseEvent e) {
		origin = new GPoint(e.getX(), e.getY());
	}

	
	private GPoint origin; // Instance variable
	private GLine line; // Instance variable
	private static int bool = 1; // Class variable 
	
}
