/* 
 * File: FacePamphlet.java
 * -----------------------
 * When it is finished, this program will implement a basic social network
 * management system.
 */

import acm.program.*;
import acm.graphics.*;
import acm.util.*;
import java.awt.event.*;
import java.util.Iterator;

import javax.swing.*;

public class FacePamphlet extends Program // Note: data output, verification functionality  => TDD  
					implements FacePamphletConstants {

	/**
	 * This method has the responsibility for initializing the 
	 * interactors in the application, and taking care of any other 
	 * initialization that needs to be performed.
	 */
	public void init() {
		
		rowInteractors();
		colInteractors();		
		
		// listen for stuff
		addActionListeners(); // listen for all JButtons
		statusField.addActionListener(this); // listen for the JTextField
		pictureField.addActionListener(this);
		friendField.addActionListener(this);
		
		// add a canvas
		canvas = new FacePamphletCanvas();
		add(canvas);
    }
	
	private void rowInteractors() {
		add = new JButton("Add");
		delete = new JButton("Delete");
		lookup = new JButton("Lookup");
		nameField = new JTextField(TEXT_FIELD_SIZE);
		
		// put a row of interactors on the north 
		add(new JLabel("Name"), NORTH);
		add(nameField, NORTH);
		add(add, NORTH);
		add(delete, NORTH);
		add(lookup, NORTH);
	}
	
	private void colInteractors() {
		/* Note: constant coupling for JTextFields => linked values */
		changeStatus = new JButton("Change Status");
		changePicture = new JButton("Change Picture");
		addFriend = new JButton("Add Friend");
		statusField = new JTextField(TEXT_FIELD_SIZE);
		pictureField = new JTextField(TEXT_FIELD_SIZE);
		friendField = new JTextField(TEXT_FIELD_SIZE);
		
		// put a column of interactors on the west	
		add(statusField, WEST);
		add(changeStatus, WEST);
		add(new JLabel(EMPTY_LABEL_TEXT), WEST);
		add(pictureField, WEST);
		add(changePicture, WEST);
		add(new JLabel(EMPTY_LABEL_TEXT), WEST);
		add(friendField, WEST);
		add(addFriend, WEST);
	}
	
	
	private void addProfile(String name) {
		profile = new FacePamphletProfile(name); // @ToDo initialize a constructor
		profileDb.addProfile(profile);
	}
	
	private void removeProfile(String name) {
		
		// two-way data unwrapping
		Iterator<String> it = profileDb.getProfile(name).getFriends();
		
		// remove all relations of that profile to be deleted.
		while(it.hasNext()) {
			profileDb.getProfile(it.next()).removeFriend(name);
		}
		profileDb.deleteProfile(name);	
		
	}
	
	private GImage getImgBuffer(String img) {

		GImage image = null;
		try {
			image = new GImage(img);
		} catch (ErrorException ex) {
			canvas.showMessage("The image does not exist.");
		}
		
		return image;
	}
	
	private void addFriend(String you, String friend) {
		FacePamphletProfile currentProfile = profileDb.getProfile(you);
		FacePamphletProfile friendProfile = profileDb.getProfile(friend);
		
		Iterator<String> it = currentProfile.getFriends();
		boolean found = false;
		
		while(it.hasNext()) { // // get an old existed friend in the friend list 
			if(it.next().equals(friend)) { 
				canvas.showMessage("An Old friend: " + it.next());
				found = true;
				break;
			}
		}
		if(!found) { // add a new friend
			// two-way data binding
			currentProfile.addFriend(friend); 
			friendProfile.addFriend(you);
			
			Iterator<String> i = currentProfile.getFriends(); // Iterator<E> => Iterator<String> it = currentPofile.getFriends();
			while(i.hasNext()) {
				String str= i.next(); // @ToDo it.next(), it.next(),...
				if(str.equals(friend)) {
					canvas.showMessage("A new friend: " + str);
					break;
				}
			}
			
		}
	}
  
    /**
     * This class is responsible for detecting when the buttons are
     * clicked or interactors are used, so you will have to add code
     * to respond to these actions.
     */
    public void actionPerformed(ActionEvent e) {
    	// @ToDo bound checking for JTextFields => valid or not, valid
    	// get a string of nameField 
    	String name = nameField.getText();
    	String status = statusField.getText();
    	String picture = pictureField.getText();
    	String friend = friendField.getText();
    	Object obj = e.getSource();
    	
    	
    	if(obj == add) {
    		if(!name.equals(EMPTY_LABEL_TEXT) || !profileDb.containsProfile(name)) { // constant coupling
    			addProfile(name);
    			
    			canvas.displayProfile(profileDb.getProfile(name));
    			canvas.showMessage("New profile created.");
    		} else {	
    			canvas.showMessage("Get: new profile: " + profileDb.getProfile(name).toString());
    		}
    	} else if(obj == delete) {
    		if(!name.equals(EMPTY_LABEL_TEXT) || profileDb.containsProfile(name)) { 
				
				removeProfile(name);
				
				canvas.removeProfile(profileDb.getProfile(name));
				canvas.showMessage("Profile of " + name + " deleted.");
			} else {
				canvas.showMessage("A profile with the " + name + " does not exist.");
			}
    	} else if(obj == lookup) {
    		if(!name.equals(EMPTY_LABEL_TEXT) || profileDb.containsProfile(name)) {
    			
	    		canvas.displayProfile(profileDb.getProfile(name));
	    		canvas.showMessage("Displaying " + name);
    		} else {
    			canvas.showMessage("A profile with the " + name + " does not exist.");
    		}
    	} else if(obj == changeStatus || obj == statusField) {
	    	if(!status.equals(EMPTY_LABEL_TEXT) || profileDb.containsProfile(name)) {
	    		FacePamphletProfile currentProfile = profileDb.getProfile(name);
	    		
	    		currentProfile.setStatus(status);
	    		
    			canvas.displayProfile(currentProfile);
    			canvas.showMessage("Status updated to " + status);
	    	} else {
	    		canvas.showMessage("Select an existed profile to update status.");
	    	}	 
    	} else if(obj == changePicture || obj == pictureField) {
    		if(!picture.equals(EMPTY_LABEL_TEXT) || profileDb.containsProfile(name)) {
	    		FacePamphletProfile currentProfile = profileDb.getProfile(name);
				
				currentProfile.setImage(getImgBuffer(picture));
				
    			canvas.displayProfile(currentProfile);
				canvas.showMessage("Add new Image");
	    	} else {
	    		canvas.showMessage("Select an existed profile to update status.");
	    	}
    	} else if(obj == addFriend || obj == friendField) {
    		if( (!friend.equals(EMPTY_LABEL_TEXT) && !name.equals(EMPTY_LABEL_TEXT)) && 
    			(profileDb.containsProfile(name) && profileDb.containsProfile(friend)) ) {
    	
    				addFriend(name, friend);
    				
		    		canvas.displayProfile(profileDb.getProfile(name));
		    		canvas.showMessage("Created a new friend " + friend);
	    	} else {
	    		canvas.showMessage("Select an existed profile to update friends, or the profile of the friend does not exist");
	    	}
    	}
     	
    	
	}
    
    
    private JButton add, delete, lookup;
    private JTextField nameField;
    private JButton changeStatus, changePicture, addFriend;
    private JTextField statusField, pictureField, friendField;
    private FacePamphletProfile profile; 
    private FacePamphletCanvas canvas;
    private FacePamphletDatabase profileDb = new FacePamphletDatabase();
    
}
