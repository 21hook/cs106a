/**
 * Histograms.java
 * 
 * This program shows ....
 */

import acm.program.*;

import java.io.*; // Constructor: BufferedReader, FileReader


public class Histograms extends ConsoleProgram{ 
	
	public void init() {
		bucket0 = bucket1 =bucket2 = bucket3 = bucket4 = bucket5 = bucket6 = bucket7 = bucket8 = bucket9 = bucket10 = "";
	}
	
	public void run() {
		BufferedReader rd = null;
		
		
		rd = openFileReader("MidtermScores.txt");
		
		try{ // put read lines in try
			while(true) {
				String line = rd.readLine();
				if(line == null) break;
				whichRange(line);
			}
			rd.close(); // put close operations in try to handle exceptions 
		} catch(IOException ex) {
            println("An I/O exception has occurred");
		}
		
		tallyResult();
		
		
	}
	
	/**
	 *  Open a file with a string of file names 
	 */
	public BufferedReader openFileReader(String f) {
		BufferedReader rd = null;
		
		try{ // put open operations in try 
			rd = new BufferedReader(new FileReader(f));
		} catch (IOException ex) {
			println("Can't open that file.");
		}
		
		return rd;
	}
	
	/**
	 * Count the value of specific range variable according to `line`
	 */
	private void whichRange(String line) {
		
		if (line.length() == 1) bucket0 += "*"; // line <= 9
		
		else if (line.length() == 3) bucket10 += "*"; // line == 100
		
		else {
			switch(line.charAt(0)) { // line <= 99 && line >= 10 
				
				case '1': 
					bucket1 += "*";
					//println(line);
					break;
				case '2':
					bucket2 += "*";
					break;
				case '3': 
					bucket3 += "*";
					break;
				case '4':
					bucket4 += "*";
					break;
				case '5': 
					bucket5 += "*";
					break;
				case '6':
					bucket6 += "*";
					break;
				case '7': 
					bucket7 += "*";
					break;
				case '8':
					bucket8 += "*";
					break;
				case '9': 
					bucket9 += "*";
					break;
			
			}
		}
	}
	
	/**
	 * Console results out
	 */
	private void tallyResult() {
		println("00-09: " + bucket0);
		println("10-19: " + bucket1);
		println("20-29: " + bucket2);
		println("30-39: " + bucket3);
		println("40-49: " + bucket4);
		println("50-59: " + bucket5);
		println("60-69: " + bucket6);
		println("70-79: " + bucket7);
		println("80-89: " + bucket8);
		println("90-99: " + bucket9);
		println("100: " + bucket10);
	}
	
	
	
	private String bucket0, bucket1, bucket2, bucket3, bucket4, bucket5, bucket6, bucket7, bucket8, bucket9, bucket10;
}
