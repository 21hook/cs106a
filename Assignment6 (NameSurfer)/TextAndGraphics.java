import acm.graphics.*;
import acm.program.*; // for mouseClicked, actionPerformed

import java.awt.*;
import java.awt.event.*; // for ActionEvent, MouseEvent
import javax.swing.*; // for JObject

public class TextAndGraphics extends ConsoleProgram {
	 
	
	public void init() {
		setLayout(new GridLayout(1, 3)); // set float grid layout
		
		leftCanvas = new GCanvas(); // add the left canvas next to the console program
		add(leftCanvas);
		
		rightCanvas = new GCanvas(); // add the right canvas next to the left canvas
		add(rightCanvas);
		
		
		textField = new JTextField(10); 
		add(new JLabel("Some text"), SOUTH);
		add(textField, SOUTH);
		textField.addActionListener(this); // add a listener for the current element  @ToDo
		
		add(new JButton("Draw left"), SOUTH); // add it to the border layout by using SOUTH 
		add(new JButton("Draw right"), SOUTH); 
		
		
		addActionListeners(); // listeners for each button @ToDo
	
	}
	
	public void actionPerformed(ActionEvent e) { // listen for keyboard type && mouse click 
		if(e.getSource() == textField) // for enter key of keyboard
			println("You typed: " + textField.getText());
		
		if(e.getActionCommand().equals("Draw left")) {
			leftCanvas.add(createFilledRect(), 20, leftY);
			leftY += SPACER;
			
		} else if(e.getActionCommand().equals("Draw right")) {
			rightCanvas.add(createFilledRect(), 20, rightY);
			rightY += SPACER;
		}
	}
	
	
	private GRect createFilledRect() {
		GRect rect = new GRect(50, 20);
		rect.setFilled(true);
		rect.setColor(Color.DARK_GRAY);
		
		return rect;
	}
	
	public static final double SPACER = 30;
	
	private GCanvas leftCanvas, rightCanvas;
	private JTextField textField;
	private double leftY = 20; 
	private double rightY = 10;


}
