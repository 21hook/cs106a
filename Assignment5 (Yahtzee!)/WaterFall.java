/**
 WaterFall.java
	
 This program shows that using 2d array with GImage changes each pixel in an image. 
*/

import acm.graphics.*;
import acm.program.*;


public class WaterFall extends GraphicsProgram {

	
	public void run() {
		GImage myImg = new GImage("milkmaid.jpg");
		add(myImg, 200,200);
		
		add(createGrayImg(myImg), 0, 0);
		
		
		
		
	}
	
	/**
	 *Image(GImage) -> 2d arr pixles(int[][])  -> pixel -> RGB -> pixels(2d pixels) -> Image(GImage)
	 * 
	 */
	private GImage createGrayImg(GImage img) {
		int[][] pixels = img.getPixelArray(); // int[][]; all 2d image data
		int height = pixels.length; // [].length
		int width = pixels[0].length;
		
		for(int i=0; i<height; i++) {
			for (int j=0; j<width; j++) {
				
				int pixel = pixels[i][j]; // get one pixel 
				
				int r = GImage.getRed(pixel); // int - RGB
				int g = GImage.getGreen(pixel); 
				int b = GImage.getBlue(pixel);
				
				long xx = Math.round(0.299*r+0.587*g+0.114*b);
				
				pixels[i][j] = GImage.createRGBPixel((int)xx, (int)xx, (int)xx);
				
				
			}
				
		}
		
		return new GImage(pixels); 
	}
}

