/**
 * Create multiple thread objects by using Thread Class.
 * This will show you the way using code create multiple thread execution.
 * This is what we called CONCURRENCY.  
 * 
 * @author Administrator
 *
 */
import java.awt.event.ActionEvent;

import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;
import acmx.export.javax.swing.JButton;

public class SliderProgram extends GraphicsProgram{

	/**
	 * Initialize a set of classes
	 */
	public void run() {
		add(new JButton("Slide"), SOUTH);
		addActionListeners();
	}
	
	public void actionPerfromed(ActionEvent e) {
		String cmd = e.getActionCommand();
		
		if(cmd.equals("Slide")) {
			Slider slider = new Slider(SIZE, rgen.nextColor());
			add(slider, 0, rgen.nextDouble(0, 300));
		
			Thread sliderThread = new Thread(slider);
			sliderThread.start();
		}
	}
	
	
	private static final int SIZE = 20;
	private RandomGenerator rgen = RandomGenerator.getInstance();
}
