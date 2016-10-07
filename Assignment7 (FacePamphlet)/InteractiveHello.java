/**
 * This program use a graphical object as a listening object, and
 * interact with users.
 */
import javax.swing.JFrame;


public class InteractiveHello {
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Graphical Hello");
		
		frame.add(new MovingLabel("CS106A rocks!", 200, 200));
		frame.setSize(500, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}


}
