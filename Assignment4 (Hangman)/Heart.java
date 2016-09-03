/*
* Heart.java
* 
* This program shows ...
*/

import acm.graphics.*;
import acm.program.*;

import java.lang.*;

public class Heart extends GraphicsProgram {
	private static final double ARC_RADIUS = 32.0;
	
	public void init() {
		double width = getWidth() / 2;
		double height = getHeight() / 2;
		
		GArc _arc = new GArc(ARC_RADIUS*2, ARC_RADIUS*2, 45, 180);
		GArc arc_ = new GArc(ARC_RADIUS*2, ARC_RADIUS*2, -45, 180);
		arc_.setLocation((_arc.getStartPoint().getX() - ARC_RADIUS) *2, 0);
		
		GLine _line = new GLine(_arc.getEndPoint().getX(), _arc.getEndPoint().getY(),
								Math.sqrt(2.0)*ARC_RADIUS + _arc.getEndPoint().getX(), Math.sqrt(2.0)*ARC_RADIUS + _arc.getEndPoint().getY());
		GLine line_ = new GLine(arc_.getStartPoint().getX(), arc_.getStartPoint().getY(),
								Math.sqrt(2.0)*ARC_RADIUS + _arc.getEndPoint().getX(), Math.sqrt(2.0)*ARC_RADIUS + _arc.getEndPoint().getY());
		
		
		add(_arc);
		add(arc_);
		add(_line);
		add(line_);
		
		
		GPoint p = new GPoint(80, 100);
		p.setLocation(20, 20);
	
	}
}