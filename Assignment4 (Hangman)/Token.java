/* 
 * Token.java
 * 
 * This program shows you how to create a token by using strings 
 * 
 */
import java.util.*;
import java.lang.*;

import acm.program.*;


public class Token extends ConsoleProgram {
	
	private String tokenizer(String s) {
		StringTokenizer token = new StringTokenizer(s);
		String result = "";
		for(int i=0; token.hasMoreTokens(); i++) {
			result += token.nextToken();
		}
		
		return result;
	}
	
	public void run() {
		String line = readLine("Enter a string: ");
	
		   
		while(true) {
			if(line.length() != 0) line = readLine("Enter a string: ");
			println(tokenizer(line));
		}
	} 
	
}