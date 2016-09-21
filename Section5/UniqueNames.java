/**
 * UniqueNames.java
 * 
 * This program shows...
 */

import acm.program.*;

import java.util.*; // Class: ArrayList

public class UniqueNames extends ConsoleProgram {
	
	public static final String SENTINAL = " ";
	
	public void run() {
		ArrayList<String> nameList = new ArrayList<String>();
		ArrayList<String> uniqueName = new ArrayList<String>();

		
		while(true) {
			String name = readLine("Enter name: ");
			if (name.equals(SENTINAL)) break; // @Todo "\n"; ==
			nameList.add(name);
			
		}
		
		getUniqueName(nameList, uniqueName);
		
		printName(uniqueName);
	}
	
	/**
	 * Copy unique names from `a` to `b`
	 */
	private void getUniqueName(ArrayList<String> a, ArrayList<String> b) { // @ToDo
		boolean unique;
		
		b.add(a.get(0)); // Init first unique name
		
		for(int i=1; i<a.size(); i++) {
			
			unique = true;
			
			for (int j=0; j<b.size(); j++) {
				if (a.get(i).equals(b.get(j))) { // @Todo boolean equals(String)
					unique = false;
					break;
				}
			}
			
			if (unique) b.add(a.get(i));
		}
		
	}
	
	/**
	 * Console out unique names
	 */
	private void printName(ArrayList<String> a) { 
		println("Unique name list contains: ");

		for(int i=0; i<a.size(); i++) 
			println(a.get(i));
	}
}