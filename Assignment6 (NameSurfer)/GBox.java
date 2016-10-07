/** 
 * GBox.java
 * 
 * This class is a constructor, which returns back to you a diagram box of GCompound
 * created by GRect & GLabel
 */
import acm.graphics.*; // for GCompound

public class GBox extends GCompound { 
	
	// for a diagram box's width & height
	private static final double BOX_WIDTH = 120;
	private static final double BOX_HEIGHT = 50;
	
	/**
	 * Constructor:
	 * 
	 * 1 Automatically return 
	 * 2 this. -> receiving object; this() -> constructor
	 */
	public GBox(String str) { 
		box = new GRect(BOX_WIDTH, BOX_HEIGHT);
		text = new GLabel(str);

		
		// Conventions: Set a GCompound to the origin 
		add(box, 0, 0);
		add(text, box.getWidth()/2 - text.getWidth()/2, box.getHeight()/2 - text.getAscent()/2);
	}
	
	
	private GRect box;
	private GLabel text;
}
