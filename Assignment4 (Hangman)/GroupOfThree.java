/*
* GroupOfThree.java
* 
* This program shows you how to use comma to separate a digit into groups of three. 
*/
import acm.program.*;

import java.lang.*;

public class GroupOfThree extends ConsoleProgram {
	
	public void run() {
		
		while(true) {
			String digits = readLine("Enter a numeric string: ");
			
			if (digits.length() == 0) break;
			println(addCommasToNumericString(digits));
		}
	}
	
	private String addCommasToNumericString(String s) {
		int len = s.length();
		String result = "";
		
		if(len <= 3) return s;
		for (int i=len-1; i>=0; i-=3 ) {
			if (i>2) {
				result ="," + s.substring(i-2, len) + result;		
			} else {
				if (len % 3 == 0) {
					result = s.substring(0, 3) + result;
				} else if (len % 3 == 2) {
					result = s.substring(0, 2) + result;
				} else {
					result = s.substring(0, 1) + result;
				}
			}		
		}
		return result;
	}
	
}