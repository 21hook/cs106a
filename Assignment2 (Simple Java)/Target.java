/*
 * File: Target.java
 * 
 * This file is the starter file for the Target problem.
 */

import acm.graphics.*;  
import acm.program.*;
import java.awt.*;

public class Target extends GraphicsProgram {	
	public void run() {	
		
		double radiusInner = 0.3*72, radiusMiddle = 0.65*72, radiusOuter = 1*72;
		double x0 = (getWidth()-radiusInner) / 2, y0 = (getHeight()-radiusInner) / 2; // coordinate x, y
		double x1 = (getWidth()-radiusMiddle) / 2, y1 = (getHeight()-radiusMiddle) / 2;
		double x2 = (getWidth()-radiusOuter) / 2, y2 = (getHeight()-radiusOuter) / 2;
		
		GOval circleInner = drawCircle(x0, y0, radiusInner, Color.RED);
		GOval circleMiddle = drawCircle(x1, y1, radiusMiddle, Color.WHITE);
		GOval circleOuter = drawCircle(x2, y2, radiusOuter, Color.RED);

		
		add(circleOuter); // methods of GObject
		add(circleMiddle);
		add(circleInner); 
	}
	
	private GOval drawCircle(double x, double y, double diameter, Color color) {
		GOval circle = new GOval(x, y, diameter, diameter);
		circle.setFilled(true); // methods of GOval 
		circle.setColor(color); // methods of GObject
		
		return circle;	
	}
}
