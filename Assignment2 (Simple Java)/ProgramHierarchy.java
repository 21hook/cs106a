/*
 * File: ProgramHierarchy.java
 * Name: 
 * Section Leader: 
 * ---------------------------
 * This file is the starter file for the ProgramHierarchy problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;


public class ProgramHierarchy extends GraphicsProgram {	
	
	private static final int BOX_WIDTH = 120;
	private static final int BOX_HEIGHT = 40;
	private static final int GUTTER = 50; // // GUTTER for boxes
	
	public void run() {
		
		double boxXLeft = (getWidth() - (3*BOX_WIDTH+2*GUTTER)) /2; // coordinate x for the leftmost box
		double boxXMiddle = boxXLeft+BOX_WIDTH+GUTTER;
		double boxXRight =  boxXLeft+2*(BOX_WIDTH+GUTTER);
		double boxY = (getHeight() - (2*BOX_HEIGHT+GUTTER)) / 2 + BOX_HEIGHT; // coordinate x for the leftmost box
		double boxXUpper = boxXLeft+BOX_WIDTH+GUTTER;
		double boxYUpper = boxY-BOX_HEIGHT-GUTTER;
		
	
		boxWithLabel(boxXLeft, boxY, "GraphicProgram"); //the leftmost box
		boxWithLabel(boxXMiddle, boxY, "COnsoleProgram"); //the middle box
		boxWithLabel(boxXRight, boxY, "DialogProgram"); //the rightmost box
		boxWithLabel(boxXUpper, boxYUpper, "Program");//the uppermost box
		
		
		double lineXLeft = boxXLeft + BOX_WIDTH /2;
		double lineXMiddle = boxXMiddle + BOX_WIDTH /2;
		double lineXRight = boxXRight + BOX_WIDTH /2;
		lineWithBox(lineXLeft, boxY);
		lineWithBox(lineXMiddle, boxY);
		lineWithBox(lineXRight, boxY);
	}
	
	private void boxWithLabel(double x, double y, String str) 
	{
		GRect rect = new GRect(x, y, BOX_WIDTH, BOX_HEIGHT);
		GLabel label = new GLabel(str);
		

		double labelX = x + (BOX_WIDTH - label.getWidth()) /2; 
		double labelY = y + (BOX_HEIGHT + label.getAscent()) /2;
		label.setLocation(labelX, labelY); // method from GObject

		add(rect);
		add(label);

	}

	private void lineWithBox(double x, double y) {
		double x0 = getWidth() / 2;
		double y0 = y - GUTTER;
		
		
		GLine line = new GLine(x, y, x0, y0);
		
		add(line);
	}
}

