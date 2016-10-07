/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.program.*;

import java.awt.event.*;
import javax.swing.*;

public class NameSurfer extends Program implements NameSurferConstants {

/* Method: init() */
/**
 * This method has the responsibility for reading in the data base
 * and initializing the interactors at the bottom of the window.
 */
	public void init() {
		
		// initialize interactors
	    nameField = new JTextField(10); // JTextField(int col)
		graphBtn = new JButton("Graph");
	    clearBtn = new JButton("Clear");
	    
	    add(new JLabel("Name "), SOUTH);
	    add(nameField, SOUTH);
	    add(graphBtn, SOUTH);
	    add(clearBtn, SOUTH);
	    
	    // listen for stuffs
	    addActionListeners(); // add a listener for all JButtons
	    nameField.addActionListener(this); // add a listener for the textField of name 
	   
	    // add canvas
	    graph = new NameSurferGraph();
	    add(graph);
	}
	
	

	/* Method: actionPerformed(e) */
	/**
	 * This class is responsible for detecting when the buttons are
	 * clicked, so you will have to define a method to respond to
	 * button actions.
	 */
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource(); // interactor selection
		
		if(obj == graphBtn || obj == nameField) {// for mouse click event object
		    graph.addEntry( entry.findEntry(nameField.getText()) );
		} else if(obj == clearBtn) {
			graph.clear();
		}
	}
	

	private JTextField nameField;
	private JButton graphBtn, clearBtn;
	private NameSurferGraph graph;
	private NameSurferDataBase entry = new NameSurferDataBase(NAMES_DATA_FILE);
	
}
