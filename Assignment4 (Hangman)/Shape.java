/*
 *  Shape.js
 * 
 *  This program is for practice.
 */


import java.awt.Color;

import acm.graphics.*;
import acm.program.*;

public class Shape extends GraphicsProgram {
	
	public void run() {
		GImage img = new GImage("v.jpg");
		img.scale(2, 2);
		//add(img);
		
		double originX = 40;
		double originY = 40;
		
		GPolygon diamond = new GPolygon();
		diamond.addVertex(-originX/2, 0);
		diamond.addVertex(0, -originY/2);
		diamond.addVertex(originX/2, 0);
		diamond.addVertex(0, originY/2);
		diamond.setFilled(true);
		diamond.setColor(Color.blue);
		
		add(diamond, getWidth()/2, getHeight()/2);
		
		double x = 40;
		double y = 40;
		GPolygon rect = new GPolygon();
		rect.addVertex(-x/2, 0);
		rect.addEdge(x/2, -y/2);
		rect.addEdge(x/2, y/2);
		rect.addEdge(-x/2, y/2);
		//rect.addEdge(-x/2, -y/2);
		rect.setFilled(true);
		rect.setColor(Color.black);
		add(rect, getWidth()/2, getHeight()/2);
		
	}
	
}
