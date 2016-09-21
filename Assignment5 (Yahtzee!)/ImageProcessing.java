/**
 WaterFall.java
	
 This program shows something.
 ....
*/

import acm.graphics.*;
import acm.program.*;


public class ImageProcessing extends GraphicsProgram {
	
	public void run() {
		GImage beforeImg = new GImage("milkmaid.jpg");
		GImage afterImg;
		
		
		afterImg = flipHorizontal(beforeImg);
		
		add(beforeImg, 400, 0);
		add(afterImg, 0, 0);
		
	}
	
	private GImage flipHorizontal(GImage img) {
		int[][] pixelsBefore = img.getPixelArray(); // return int[][]
		int height = pixelsBefore.length; // [].length
		int width = pixelsBefore[0].length;
		int pixlesAfter[][] = new int[height][width]; // new int[][]
	
		
		for(int i=0; i<height; i++) 
			for(int j=0; j<width; j++) 
				pixlesAfter[i][width-1-j] = pixelsBefore[i][j];		
			
		return new GImage(pixlesAfter);
		
	}
}
