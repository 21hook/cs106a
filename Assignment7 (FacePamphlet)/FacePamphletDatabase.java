/*
 * File: FacePamphletDatabase.java
 * -------------------------------
 * This class keeps track of the profiles of all users in the
 * FacePamphlet application.  Note that profile names are case
 * sensitive, so that "ALICE" and "alice" are NOT the same name.
 */

import java.util.*;

public class FacePamphletDatabase implements FacePamphletConstants {

	/** 
	 * Constructor
	 * This method takes care of any initialization needed for 
	 * the database.
	 */
	public FacePamphletDatabase() { // @Todo empty constructor
		// You fill this in
	}
	
	
	/** 
	 * This method adds the given profile to the database.  If the 
	 * name associated with the profile is the same as an existing 
	 * name in the database, the existing profile is replaced by 
	 * the new profile passed in.
	 */
	public void addProfile(FacePamphletProfile profile) {
		//profileDb.add(profile);
		
		profileDb0.put(profile.getName(), profile);
	}

	
	/** 
	 * This method returns the profile associated with the given name 
	 * in the database.  If there is no profile in the database with 
	 * the given name, the method returns null.
	 */
	public FacePamphletProfile getProfile(String name) {
		/* Note: type conversion & method with data => data 
		Iterator it = profileDb.iterator();
		
		while(it.hasNext()) {
			if(it.next().equals(name)) return (FacePamphletProfile)it.next();
		}
		*/
		
		/*
		for(int i=0; i<profileDb.size(); i++) {
			if(profileDb.get(i).getName().equals(name)) return profileDb.get(i);
		}
		*/
		
		if(profileDb0.containsKey(name)) return profileDb0.get(name);
		return null;
	}
	
	
	/** 
	 * This method removes the profile associated with the given name
	 * from the database.  It also updates the list of friends of all
	 * other profiles in the database to make sure that this name is
	 * removed from the list of friends of any other profile.
	 * 
	 * If there is no profile in the database with the given name, then
	 * the database is unchanged after calling this method.
	 */
	public void deleteProfile(String name) {
		profileDb0.remove(name);
	}

	
	/** 
	 * This method returns true if there is a profile in the database 
	 * that has the given name.  It returns false otherwise.
	 */
	public boolean containsProfile(String name) {
		
		/*
		for(int i=0; i<profileDb.size(); i++) 
			if(profileDb.get(i).getName().equals(name)) return true;
		
		return false;
		*/
		
		return profileDb0.containsKey(name);
	}
	
	
	private Map<String, FacePamphletProfile> profileDb0 = new HashMap<String, FacePamphletProfile>(); // 
	private ArrayList<FacePamphletProfile> profileDb = new ArrayList<FacePamphletProfile>();

}
