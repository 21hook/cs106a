/**
 * This class shows you the way using standard Java swing & awt libraries listen
 * for mouse events, then interact with the event object.   
 */

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

public class MovingLabel extends JComponent implements MouseListener {
	
	public MovingLabel(String label, int x, int y) {
		text = label;
		this.x = x;
		this.y = y;
		addMouseListener(this);
	}
	
	public void paint(Graphics g) {
		g.drawString(text, x, y);
	}
	
	public void mouseClicked(MouseEvent e) {
		x = e.getX();
		y = e.getY();
		repaint();
	}
	
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	
	private String text;
	private int x;
	private int y;
}
