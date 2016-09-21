/*
* DeleteOccurence.java
* 
* This program shows you how to remove chars from a specific string. 
*/
import acm.program.*;

import java.lang.*;

public class DeleteOccurence extends ConsoleProgram {
	
	public void run() {
		
		println(removeAllOccurrences("This is a test", 't'));
		println(removeAllOccurrences("Summer is here!", 'e'));
		println(removeAllOccurrences("---0---", '-'));
		
		println(removeAllSubstrings("Mississippi", "si"));
	}

	/* 3n+4  n= N
	public void removeAllOccurrences(String str, char ch) {
		String result = "";
		
		for(int i=0; i<str.length(); i++) {
			if(str.charAt(i) != ch) result += str.charAt(i); 
		}
		println(result);
	}
	*/
	/*
	 * Rewrite + Characters concatenation 
	 */
	private String removeAllOccurrences(String str, char ch) {
		while(true) {
			int pos = str.indexOf(ch); 
			if (pos >= 0) {
				str = str.substring(0, pos) + str.substring(pos+1);
			} else break;
		}
		return str;
	}

	
	/*
	 * Remove strings
	 */
	private String removeAllSubstrings(String str, String s) {
		while(true) {
			int pos = str.indexOf(s);
			if (pos >= 0) {
				str = str.substring(0, pos) + str.substring(pos+s.length());
			} else break;
		}
		
		return str;
	}

}
