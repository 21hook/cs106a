/**
 * PhoneBook.java 
 * 
 * This program 
 *
 */

import acm.program.*;
import java.util.*;

public class PhoneBook extends GraphicsProgram {

	public void run() {
		
		while(true) { // dynamic init & loop and a half & collection
			String name = readLine("Name(enter to end): ");
			if(name.equals("")) break;
			int number = readInt("Number: ");
			phoneBook.put(name, number); // automatically box
		}
		
		while(true) {
			String name = readLine("Enter a name to look up(enter to end): ");
			lookupName1(name);
		}
		
		
	}
	
	
	private void lookupName1(String name) {
		Iterator it = phoneBook.keySet().iterator(); // @ToDo map -> list -> iterator; arrList.iterator()
		
		
		while(it.hasNext()) {
			if(it.next().equals(name)) 
				println("Name: " + name + ", Number: "+ phoneBook.get(name));
			else 
				println("The name doesn't exist.");
		}
	} 
	
	private void lookupName2(String name) {
		
		for(String s: phoneBook.keySet()) {
			if(s.equals(name)) 
				println("Name: " + name + ", Number: "+ phoneBook.get(name));
			else 
				println("The name doesn't exist.");
		}
	}
	
	
	
	private Map<String, Integer>  phoneBook = new HashMap<String, Integer>();
	
}
