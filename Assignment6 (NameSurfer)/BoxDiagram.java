/**
 * BoxDiagram.java
 * 
 * 
 */
import acm.program.*; // for addActionListeners()
import acm.graphics.*;

import java.awt.event.*; // for ActionEvent, MouseEvent
import javax.swing.*; // for JObject
import java.util.*;

public class BoxDiagram extends GraphicsProgram { //  GraphicsProgram for addMouseListeners() 
	


	
	/**
	 * Statically initialize component method
	 */
	public void init() { 
		
		add(new JLabel("Name"), SOUTH);
		
		boxName = new JTextField(10);
		add(boxName, SOUTH);
		boxName.addActionListener(this); 
		
		add = new JButton("Add");
		remove = new JButton("Remove");
		clear = new JButton("Clear");
	
		add(add, SOUTH);
		add(remove, SOUTH);
		add(clear, SOUTH);
		
	
		addMouseListeners();
		addActionListeners();
	}

	
	/**
	 * Add actionPerformed method to listen for keyboard event object & mouse click event object
	 */
	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand() == "Add" || e.getSource() == boxName) {
			GBox diagram = new GBox(boxName.getText()); // import, call
			
			rectList.put(boxName.getText(), diagram); // 
			add(diagram, getWidth()/2 - diagram.getWidth()/2, getHeight()/2 - diagram.getHeight()/2);
		} else if(e.getActionCommand() == "Remove") {
			String name = boxName.getText(); // get JTextField boxName
			
			remove(rectList.get(name));
		} else if(e.getActionCommand().equals("Clear")) {
			removeAll();
		}
		
	}
	
	// Call on mouse press to get the GObject
	public void mousePressed(MouseEvent e) {
		
		// GPoint has X and Y coordinate
		last = new GPoint(e.getPoint());
		gobj = getElementAt(last);
	}
	
	// Called on mouse drag to reposition the object
	public void mouseDragged(MouseEvent e) {
		
		
		if (gobj != null) {
			
			gobj.move(e.getX() - last.getX(), e.getY() - last.getY());
			last = new GPoint(e.getPoint());
		}
	}
	


	private JButton add, remove, clear;
	private JTextField boxName; 
	private HashMap<String, GBox> rectList = new HashMap<String, GBox>();  // @ToDo
	private GPoint last;
	private GObject gobj;
	
}