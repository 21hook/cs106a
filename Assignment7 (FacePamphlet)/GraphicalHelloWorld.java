import javax.swing.*;

/**
 * This program shows you how to create a graphical object in standard Java.
 * 
 * @author Administrator
 *
 */
public class GraphicalHelloWorld {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Graphical Hello");
		JLabel label = new JLabel("Hello world");
		
		frame.add(label);
		frame.setSize(500, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
