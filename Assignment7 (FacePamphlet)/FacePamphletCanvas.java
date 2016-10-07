/*
 * File: FacePamphletCanvas.java
 * -----------------------------
 * This class represents the canvas on which the profiles in the social
 * network are displayed.  NOTE: This class does NOT need to update the
 * display when the window is resized.
 */


import acm.graphics.*;
import java.awt.*;
import java.util.*;

public class FacePamphletCanvas extends GCanvas 
					implements FacePamphletConstants {
	
	/** 
	 * Constructor
	 * This method takes care of any initialization needed for 
	 * the display
	 * 
	 * Note: initialize & update tied up for instance variable => an empty constructor(random values, or just declare)  
	 */
	public FacePamphletCanvas() {
	}
	
	
	/** 
	 * This method displays a message string near the bottom of the 
	 * canvas.  Every time this method is called, the previously 
	 * displayed message (if any) is replaced by the new message text 
	 * passed in.
	 */
	public void showMessage(String msg) {
		// init application messages
		appMsg.setFont(MESSAGE_FONT);
		add(appMsg, getWidth()/2.0 - appMsg.getWidth()/2.0, getHeight() - BOTTOM_MESSAGE_MARGIN); 
		appMsg.setLabel(msg);
		appMsg.setLocation(getWidth()/2.0 - appMsg.getWidth()/2.0, getHeight() - BOTTOM_MESSAGE_MARGIN);
	}
	
	
	/** 
	 * This method displays the given profile on the canvas.  The 
	 * canvas is first cleared of all existing items (including 
	 * messages displayed near the bottom of the screen) and then the 
	 * given profile is displayed.  The profile display includes the 
	 * name of the user from the profile, the corresponding image 
	 * (or an indication that an image does not exist), the status of
	 * the user, and a list of the user's friends in the social network.
	 */
	public void displayProfile(FacePamphletProfile profile) {
		/* Note: decoupling for each graphic */
		
		// init a name
		name.setLabel(profile.getName());
		name.setColor(Color.blue);
		name.setFont(PROFILE_NAME_FONT);
		add(name, LEFT_MARGIN, TOP_MARGIN + name.getAscent());
		
		// init a img 	
		remove(rect);
		remove(attr);
		remove(img);
		if(profile.getImage() == null) {	
			attr.setFont(PROFILE_IMAGE_FONT);
			
			add(rect, LEFT_MARGIN, TOP_MARGIN + name.getAscent() + IMAGE_MARGIN);
			add(attr, rect.getX() + rect.getWidth()/2 - attr.getWidth()/2, 
					rect.getY() + rect.getHeight()/2 + attr.getAscent()/2);
		} else {
			img = profile.getImage();
			img.setSize(IMAGE_WIDTH, IMAGE_HEIGHT);
			
			add(img, LEFT_MARGIN, TOP_MARGIN + name.getAscent() + IMAGE_MARGIN);
		}
		
		//init status
		if(!profile.getStatus().equals("")) {
			status.setLabel(profile.getStatus());
		}
		status.setFont(PROFILE_STATUS_FONT);
		add(status, LEFT_MARGIN, TOP_MARGIN + name.getAscent() + IMAGE_MARGIN +
		            IMAGE_HEIGHT + STATUS_MARGIN + status.getAscent());
		
		//Note: coupling for relevant GLabels
		// init a string of list of friends
		GLabel title = new GLabel("Friends:"); // title for friends
		title.setFont(PROFILE_FRIEND_LABEL_FONT);
		add(title, getWidth()/2.0, TOP_MARGIN + name.getAscent() + IMAGE_MARGIN);
		Iterator<String> it = profile.getFriends();
		String friendsStrLi = "";
		while(it.hasNext()) {
			friendsStrLi += (String)it.next() + "\n";
		}
		friends.setLabel(friendsStrLi);
		friends.setFont(PROFILE_FRIEND_FONT);
		add(friends, getWidth()/2.0, title.getY() + friends.getHeight());

	}

	/*
	 * Remove all graphics of that profile
	 */
	public void removeProfile(FacePamphletProfile profile) {
		removeAll();
	}
	
	private GLabel name = new GLabel("");
	private GImage img = new GImage("", IMAGE_WIDTH, IMAGE_HEIGHT);
	private GLabel status = new GLabel("No current status");
	private GLabel friends = new GLabel("");
	private GLabel appMsg = new GLabel("");
	private GRect rect = new GRect(IMAGE_WIDTH, IMAGE_HEIGHT); // a rectangle for img
	private GLabel attr = new GLabel("No Image"); // an attributive description for img
}
