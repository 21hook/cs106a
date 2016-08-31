/*
* DeleteOccurence.java
* 
* This program shows you how to remove chars from a specific string. 
*/
import acm.program.*;

import java.lang.*;

public class DeleteOccurence extends ConsoleProgram {
	
	public void run() {
		
		removeAllOccurrences("This is a test", 't');
		removeAllOccurrences("Summer is here!", 'e');
		removeAllOccurrences("---0---", '-');
	}

	public void removeAllOccurrences(String str, char ch) {
		String result = "";
		
		for(int i=0; i<str.length(); i++) {
			if(str.charAt(i) != ch) result += str.charAt(i); 
		}
		println(result);
	}

}
