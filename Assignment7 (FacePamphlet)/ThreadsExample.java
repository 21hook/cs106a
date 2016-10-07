import java.awt.event.ActionEvent;

import acm.graphics.GLine;
import acm.program.GraphicsProgram;
import acmx.export.javax.swing.JButton;

public class ThreadsExample extends GraphicsProgram {

	public void run() {
		racers = new RacingSquare[NUM_RACERS];
		finished = new boolean[NUM_RACERS];
		threads = new Thread[NUM_RACERS];
		
		add(new GLine(510, 0, 510, 400));
		
		add(new JButton("Start"), SOUTH);
		addActionListeners();
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Start")) {
			for(int i=0; i<NUM_RACERS; i++) {
				if(racers[i] != null) remove(racers[i]);	
			
				racers[i] = new RacingSquare(i, finished);
				add(racers[i], 10, 10 + (40 * i));
				
				
				threads[i] = new Thread(racers[i]);
				threads[i].start();
			}
		}
	}
	
	
	private static final int NUM_RACERS = 2;
	
	private RacingSquare[] racers; // class[] name = new class[num]
	private boolean[] finished;
	private Thread[] threads; 
	
}
