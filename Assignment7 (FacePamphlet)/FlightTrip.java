/**
 * This program shows ... 
 * 
 */
import acm.program.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class FlightTrip extends ConsoleProgram {
	
	public void init() {
		storeRoutes();
	}

	public void run() {	
	    
		println("weclome to Flight Planner!");
	    println("Here's a list of all the cities in our database: ");    
	    for(String city : flightRoutes.keySet()) { // @ToDo map.keySet() => Set => s set of keys
	    	println(" " + city);
	    }
	    println("Let's plan a round-trip route!");
	    
	    String startCity = "";  
	    while(true) { // loop & dynamic init
	    	startCity = readLine("Enter the starting city: ");
	    	if( !flightRoutes.containsKey(startCity) ) {
	    		println("You can't get to that city at the current city: ");
	    	} else { 
	    		route.add(startCity);
	    		listCities(startCity);
	    		break;
	    	}
	    }
	  
	    
	    while(true) {
	    	String nextCity = readLine("Where do you want to go from "+ startCity + " : ");
	    	if( isAvailable(startCity, nextCity) ) {
	    		route.add(nextCity);
	    		if(route.get(0).equals(nextCity)) break;
	    		listCities(nextCity);
	    		startCity = nextCity;
	    	} else {
	    		println("You can't get to that city by a direct flight: ");
	    		listCities(startCity);
	    	}	
	    }
	    
	    listRoute();
	    
		
	}
	
	/**
	 * Read all routes from a file, store a source for one key with destinations 
	 * for multiple values   
	 */
	private void storeRoutes() {
	    BufferedReader rd = openFileReader("flights.txt");
	    
	    try{
	    	while(true) {// @ToDO IO only once
		    	String line = rd.readLine();
		    	if(line == null) break; // EOF => null
		    	if(line.equals("")) continue; // @ToDo '\r' '\n' "\r\n" => ""
		    	String src = line.substring(0, line.indexOf(" -> "));
		    	String dest = line.substring(line.indexOf(" -> ") + 4);
		    	if(!flightRoutes.containsKey(src))
		    		flightRoutes.put(src, new ArrayList<String>()); 		
		    	flightRoutes.get(src).add(dest);
	    	}
	    	rd.close();
	    } catch(IOException ex) {
	    	println("An I/O exception has occurred");
            
	    }
	}

	/**
	 * Open a file  
	 * 
	 * @param name a file name
	 * @return an object of type BufferedReader 
	 */
	private BufferedReader openFileReader(String name) {
		BufferedReader rd = null;
		
		try{
			rd = new BufferedReader(new FileReader(name));
		} catch(IOException ex) { // @ToDO
			println("Can't open that file.");
		}
		
		return rd;
	}
	
	/**
	 * Whether a city is available
	 *  
	 * @param city a city that users entered
	 * @return boolean
	 */
	private boolean isAvailable(String start, String end) {
		return flightRoutes.get(start).contains(end);
	}
	
	/*
	 * List available cities form flightRoutes of type map according to a specific name
	 * 
	 * @param city a city with which the user want to start  
	 */
	private void listCities(String city) {
		println("Form " + city + " you can fly directly to: ");
		
		ArrayList<String> destination = flightRoutes.get(city);
		for(int i=0; i<destination.size(); i++) {
			println(" " + destination.get(i));
		}
				
	}
	
	/**
	 * List the route the user have chosen
	 */
	private void listRoute() {
	    println("The route you've chosen is: ");
	    
	    String path = route.get(0);
	    for(int i=1; i<route.size(); i++) {
	    	path += " -> " + route.get(i);
	    }
	    println(path);
	}
	
	private Map<String, ArrayList<String>> flightRoutes = new HashMap<String, ArrayList<String>>(); // @ToDo Map<String, Class>
	private ArrayList<String> route = new ArrayList<String>();
}
