/*
 * File: Breakout.java
 * -------------------
 * Name:
 * Section Leader:
 * 
 * This file will eventually implement the game of Breakout.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.lang.*;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Breakout extends GraphicsProgram {

/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

/** Dimensions of game board (usually the same) */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;

/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;

/** Separation between bricks */
	private static final int BRICK_SEP = 4;

/** Width of a brick */
	private static final int BRICK_WIDTH =
	  (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;
	
	private static final int BALL_COORDINATE_X = WIDTH/2 - BALL_RADIUS;
	
	private static final int BALL_COORDINATE_Y = HEIGHT/2 - BALL_RADIUS;

/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

/** Number of turns */
	private static final int NTURNS = 3;
		
/** Delay between movement of balls */
	private static final int DELAY = 50;

	
/* Method: run() */
/** Runs the Breakout program. */
	public void run() {
		stepup();
		play();
	}

	/*
	 * Initialize bricks, paddle, and ball
	 * add mouseListeners 
	 */
	private void stepup() {
		createBrick();
		createBall();
		createPaddle();
		label4Score();
		label4Board("Game start");
	}
	
	/*
	 * Move balls at a specific displacement in specific time.
	 *   
	 */
	private void play() {
		vx = rgen.nextDouble(1.0, 3.0);
		vx = rgen.nextBoolean(0.5) ? vx : -vx;
		vy = 3.0;
		
		while (true) {			
			ball.move(vx, vy);
			bounceAgainstWall();
			ballOnPaddle();
			//checkForBrick();
			pause(DELAY);
		}
		
	}
	
	/**
	 * Create all bricks down BRICK_Y_OFFSET offset on top of the game box
	 */
	private void createBrick() {
		
		double x = (WIDTH - BRICK_WIDTH*NBRICK_ROWS - BRICK_SEP*(NBRICK_ROWS -1)) /2; // x location of first brick column
		double y = BRICK_Y_OFFSET; // y location of first brick row 
		int num = 0;
		
		for (int i=0; i<NBRICK_ROWS; i++) {
			for (int j=0; j<NBRICKS_PER_ROW; j++) {
				GRect brick = new GRect(x, y, BRICK_WIDTH, BRICK_HEIGHT);
				
				brick.setFilled(true);
				if (num < 2) {
					brick.setColor(Color.RED);
				} else if (num < 4) {
					brick.setColor(Color.ORANGE);
				} else if (num < 6) {
					brick.setColor(Color.YELLOW);
				} else if (num < 8) {
					brick.setColor(Color.GREEN);
				} else {
					brick.setColor(Color.CYAN);
				}
				add(brick);
				
				x += BRICK_WIDTH + BRICK_SEP;
			}
			 num++;
			 x = (WIDTH - BRICK_WIDTH*NBRICK_ROWS - BRICK_SEP*(NBRICK_ROWS -1)) /2; // !!
			 y += BRICK_HEIGHT + BRICK_SEP;
		}
	}

	/** 
	* Create a ball in the center of the game box
	*/
	private void createBall() {
		
		ball = new GOval(BALL_COORDINATE_X, BALL_COORDINATE_Y, BALL_RADIUS*2, BALL_RADIUS*2 );
		ball.setFilled(true);
		ball.setColor(Color.BLACK);
		ball.sendToBack(); // move the ball at the down most of the GObject 
		
		add(ball);
	}
	
	/**
	 * Create a paddle up PADDLE_Y_OFFSET offset on the bottom of the game box
	 */
	private void createPaddle() {
		
		paddle = new GRect((WIDTH - PADDLE_WIDTH) / 2,  HEIGHT - PADDLE_Y_OFFSET - PADDLE_HEIGHT, PADDLE_WIDTH, PADDLE_HEIGHT);
		paddle.setFilled(true);
		paddle.setColor(Color.BLACK);
		
		add(paddle);
		addMouseListeners();
	}
	
	/**
	 * Add label for updating scores according to count of bricks
	 */
	private void label4Score() {
		
		GLabel score = new GLabel("Score: ");
		score.setFont("Times New Roman-12");
		add(score, 10, 12);
		
		num4Score = new GLabel("0");
		num4Score.setFont("Times New Roman-12");
		add(num4Score, 45, 12 );
	}
	
	/**
	 * Show messages against a board on the center of the game box by passing a string
	 * 
	 * @param s a string to show on the board of a game
	 */
	private void label4Board(String s) {
		
		board = new GRect(100, 45);
		board.setFilled(true);
		board.setColor(Color.GRAY);
		board.setFillColor(Color.LIGHT_GRAY);
		hint = new GLabel(s);
		hint.setFont("Times New Roman-12");
		
		add(board, (WIDTH - board.getWidth()) /2, (HEIGHT- board.getHeight()) /2);
		add(hint, (WIDTH - hint.getWidth()) /2, (HEIGHT + hint.getAscent()) /2);

		println("label4Board");
		addMouseListeners();
		wait4Listening();
	}
	
	/**
	 * Add a listener to wait for user click 
	 */
	private void wait4Listening() {
		
		while (true) {
			if (clickOrNot) {
				break;	
			}
		} 
		clickOrNot = false;
		if (clickOrNot == true) println("true");
		remove(board);
		remove(hint);
	}
	
	/** 
	* Bounce it up towards reflection directions, if the ball is collided with the boundaries in the game box. 
	*/
	private void bounceAgainstWall() {
		double x = ball.getX();
		double y = ball.getY();
		double diff;
		
		
		if (x < 0) { // the right wall
			diff = x;
			
			ball.move(-diff, vy); // bounce up
			vx = rgen.nextDouble(1.0, 3.0); 
			
		} else if (x > (WIDTH - BALL_RADIUS*2)) { // the left wall
			diff = x - (WIDTH - BALL_RADIUS*2);
			
			ball.move(-diff, vy); // bounce up
			vx = -(rgen.nextDouble(1.0, 3.0)); // the opposite direction
			
		} else if (y < 0 ) { // the top wall 
				diff = y;
				
				ball.move(vx, -diff); // bounce up
				vx = rgen.nextDouble(1.0, 3.0);
				vy = -vy;
		} 
	}
	
	/**
	 * If the ball is hit by the paddle
	 * else the ball is missed
	 * 	if continue
	 *  else game over
	 */
	private void ballOnPaddle() {
		double x = ball.getX();
		double y = ball.getY();
		double diff;
		
		
		if(y > HEIGHT - PADDLE_Y_OFFSET) { 	
			turn--;
			
			if (turn > 0) {
				label4Board("round " + turn);
				println("here");
				pause(1000000000);
				//ball.setLocation(BALL_COORDINATE_X, BALL_COORDINATE_Y);
			} else {
				//TODO 
				//turnOver();
				label4Board("Game Over");
			}
			
		} else if (y + BALL_RADIUS*2 > HEIGHT - PADDLE_Y_OFFSET - PADDLE_HEIGHT) { 
			gObj = getElementAt(x + BALL_RADIUS, y + BALL_RADIUS*2 + 1);
			
			if (gObj != null ) { // If the ball is hit by the paddle
				diff = y - (HEIGHT - PADDLE_Y_OFFSET - PADDLE_HEIGHT - BALL_RADIUS*2); 
				ball.move(0, -diff); // bounce up
				bounceClip.play();
				
				vx = -1 * rgen.nextDouble(1.0, 3.0);
				vy = -vy;
			} 
		} 
		
	}
	
	/**  
	* Reverse clickOrNot to continue running the program.
	*/
	public void mouseClicked(MouseEvent e) {

		if (getElementAt(e.getX(), e.getY()) == board) { // click for removing board
			clickOrNot = true;
		}	
		
	}

	/**
	 * Move the paddle to the y-dimension of coordinates 
	 * of the mouse in the x-dimension, 
	 * after the user click the mouse.
	 */
	public void mouseMoved(MouseEvent e) {
		
		if (getElementAt((WIDTH - board.getWidth()) /2, (HEIGHT- board.getHeight()) /2) != board) { // start game if the board doesn't exist
			if (e.getX() <= WIDTH - PADDLE_WIDTH/2 && e.getX() >= PADDLE_WIDTH/2) { // move if the mouse is between the specific ranges
				double paddleX = paddle.getX(); // current paddle location x
				double offset = e.getX() - (paddleX + (PADDLE_WIDTH /2));
	
				paddle.move(offset, 0); // move paddle 
			} 
			
		}
	}
	
	/**
	 *  Instance variables between operations 
	 */
	private int turn = NTURNS;
	private GLabel num4Score;
	private GRect board;
	private GLabel hint;
	private boolean clickOrNot = false;
	private GOval ball;
	private double vx, vy; // x, y velocity of a ball
	private GRect paddle;
	private GObject gObj;
	private AudioClip bounceClip = MediaTools.loadAudioClip("bounce.au");
	private RandomGenerator rgen = RandomGenerator.getInstance();
	
	
}
