/**
 CountName.java
 
 This program shows something.
 ....
 
*/

import acm.program.*;
import java.util.*; // for ArrayList Class

public class CountName extends ConsoleProgram {
	
	public void run() {
		
		HashMap<String, Integer> nameMap = new HashMap<String, Integer>();
		
		while(true) {
			String name = readLine("Enter name: ");
			if (name.equals(" ")) break; // predicative
			if (nameMap.containsKey(name)) 
				nameMap.put(name, nameMap.get(name)+1); // <T>; parameterized type
			else 
				nameMap.put(name, 1); // init
		}
		
		// get key; get name
		Iterator<String> it = nameMap.keySet().iterator(); // @ToDO
		while (it.hasNext()) {
			String key = it.next();
			int count = nameMap.get(key);
			println("Entry [" + key + "] has count " + count);
		}

	}
	

}