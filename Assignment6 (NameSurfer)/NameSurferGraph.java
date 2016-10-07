/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes or the window is resized.
 */

import acm.graphics.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class NameSurferGraph extends GCanvas
	implements NameSurferConstants, ComponentListener {

	/**
	* Creates a new NameSurferGraph object that displays the data.
	*/
	public NameSurferGraph() {
		addComponentListener(this);		
	}
	
	/**
	* Clears the list of name surfer entries stored inside this class.
	*/
	public void clear() {
		//remove the list of entries
		// => keep columns, rows and decades
		removeAll();
		rankLi.clear();
		rankPt.clear();
			
		update();	
	}
	
	/* Method: addEntry(entry) */
	/**
	* Adds a new NameSurferEntry to the list of entries on the display.
	* Note that this method does not actually draw the graph, but
	* simply stores the entry; the graph is drawn by calling update.
	*/
	public void addEntry(NameSurferEntry entry) {	
		
		// init a set of values of a name with a rank
		for(int j=0; j<NDECADES; j++) { // get a decade of that line
			String nameRank = entry.getName() + " " + entry.getRank(j);
			GLabel str = new GLabel(nameRank);
			GPoint pt = new GPoint(0, 0); // random init value 
			
			if(entry.getRank(j) == 0) {
				nameRank = entry.getName() + " *"; // explicit rewrite
			}
			
			rankLi.add(str);
			rankPt.add(pt);
		}
					
		
		update();
	}
	
	
	
	/**
	* Updates the display image by deleting all the graphical objects
	* from the canvas and then reassembling the display according to
	* the list of entries. Your application must call update after
	* calling either clear or addEntry; update is also called whenever
	* the size of the canvas changes.
	*/
	public void update() {
		
		removeAll();
		
		// update top & bottom lines
		GLine topLine = new GLine(0, GRAPH_MARGIN_SIZE, getWidth(), GRAPH_MARGIN_SIZE);
		GLine bottomLine = new GLine(0, getHeight() - GRAPH_MARGIN_SIZE, getWidth(), getHeight() - GRAPH_MARGIN_SIZE);
		
		add(topLine);
		add(bottomLine);
		
		
		// update all bucket line & decades
		for(int i=0; i<NDECADES; i++) {
			GLabel decade = new GLabel( String.valueOf(START_DECADE + i * 10) );
			GLine bucket = new GLine(getLocationX(i), 0, getLocationX(i), getHeight());

			decade.setLocation(getLocationX(i), getHeight());
			add(decade);
			add(bucket);
		}
		
		
		// update all coordinates of GLabels
		for(int i=0; i<rankLi.size()/NDECADES; i++) { // get one line of decades
			Color color = getColor();
			for(int j=i * NDECADES; j< (i+1) * NDECADES; j++) { // get a decade of that line
				
				GLabel nameRank = rankLi.get(j);
				GPoint pt = rankPt.get(j);
				int rank = Integer.parseInt(nameRank.getLabel().substring( nameRank.getLabel().indexOf(' ') + 1) );
				int column = j - i*NDECADES;
						
				
				nameRank.setLocation(getLocationX(column), getLocationY(rank)); // set all coordinates of name & rank of an entry
				pt.setLocation(getLocationX(column), getLocationY(rank)); // set all coordinates of point of an entry	
				
				if(rank == 0) {
					rankLi.get(j).setLocation(nameRank.getX(), getHeight() - GRAPH_MARGIN_SIZE);
					rankPt.get(j).setLocation(nameRank.getX(), getHeight() - GRAPH_MARGIN_SIZE);
				}
				
				nameRank.setColor(color);	
				add(nameRank);
			}
			
		}
		
		
		// draw brokenlines of all entries line by line 
		for(int i=0; i<rankLi.size()/NDECADES; i++) { // get one line of decades
			for(int j=i * NDECADES; j< i*NDECADES + (NDECADES-1); j++) { // get a decade of that line
				GLine line;		
				
				line = new GLine(rankPt.get(j).getX(), rankPt.get(j).getY(), rankPt.get(j+1).getX(), rankPt.get(j+1).getY());
				
				line.setColor(rankLi.get(j).getColor());
				add(line);
			}
		} 

				
	}

	
	 
	 /**
	  * get x of current coordinate of the object
	  * 
	  * @param col the column number of the an entry
	  */
	private double getLocationX(int col) {
		return (double)getWidth()/NDECADES *col;
	}
	

	/**
	 * get y of current coordinate of the object
	 * 
	 * @param rank the rank value of an entry
	 */
	private double getLocationY(int rank) {		
		return GRAPH_MARGIN_SIZE + (getHeight() - 2*GRAPH_MARGIN_SIZE)/1000.0 *rank;
	}
	
	
	/**
	 * return  color for names & lines
	 */
	private Color getColor() {
		Color c = Color.white;
		
		switch(counter) {
			case 1:
				c = Color.black;
				break;
			case 2:
				c = Color.red;
				break;
			case 3:
				c = Color.blue;
				break;
			case 4:
				c = Color.magenta;
				counter = 1;
				break;
		}
		
		counter++;
		
		return c;
	}
	
	
	
	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { update(); }
	public void componentShown(ComponentEvent e) { }
	

	//private Map<GLabel, GLine> brokenLines = new HashMap<GLabel, GLine>(); 
	private ArrayList<GLabel> rankLi = new ArrayList<GLabel>();
	private ArrayList<GPoint> rankPt = new ArrayList<GPoint>();
	private int counter = 1;

}
