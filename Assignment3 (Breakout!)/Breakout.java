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
import java.awt.*;
import java.awt.event.*;

public class Breakout extends GraphicsProgram {

/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400; // supply to user class
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

/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

/** Number of turns */
	private static final int NTURNS = 3;

/* Method: run() */
/** Runs the Breakout program. */
	public void run() {
		stepup();
		play();
	}
	
	private void stepup() {
		createBrick();
		createPaddle();
		createBall();
	}
	
	private void play() {
		vx = rgen.nextDouble(1.0, 3.0);
		vy = 3.0;
		
		if (rgen.nextBoolean(0.5)) vx = -vx;
		while (true) {			
			ball.move(vx, vy);
			bounceUp();
			checkForCollision();
			pause(50);
		}
	}
	

	/*
	 * 
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
	
	/*
	 * 
	 */
	private void createPaddle() {
		
		paddle = new GRect((WIDTH - PADDLE_WIDTH) / 2,  HEIGHT - PADDLE_Y_OFFSET - PADDLE_HEIGHT, PADDLE_WIDTH, PADDLE_HEIGHT);
		paddle.setFilled(true);
		paddle.setColor(Color.BLACK);
		
		add(paddle);
		addMouseListeners();
	}
	
	public void mouseClicked(MouseEvent e) {
		double paddleX = paddle.getX(); // current paddle location x

		paddle.move(e.getX() - paddleX, 0);
		if (paddle.getX() > WIDTH - PADDLE_WIDTH) {
			double diff = paddle.getX() - (WIDTH - PADDLE_WIDTH);
			paddle.move(-1.1*diff, 0);
		}

		pause(50);
	}
	
	
	private void createBall() {
		ball = new GOval(WIDTH/2 - BALL_RADIUS, HEIGHT/2 - BALL_RADIUS, BALL_RADIUS*2, BALL_RADIUS*2 );
		ball.setFilled(true);
		ball.setColor(Color.BLACK);
		ball.sendToBack(); // move the ball at the downmost of the GObject 
		
		add(ball);
	}
 	

	private void bounceUp() {
		double x = ball.getX();
		double y = ball.getY();
		double diff;
		

		if (x < 0) {
			diff = x;
			
			ball.move(-diff, vy); // bounce up
			vx = rgen.nextDouble(1.0, 3.0); 
		} else if (x > (WIDTH - BALL_RADIUS*2)) {
			diff = x - (WIDTH - BALL_RADIUS*2);
			
			ball.move(-diff, vy); // bounce up
			vx = rgen.nextDouble(1.0, 3.0); // the random speed after bounce up
			vx = -vx; // the opposite direction
		} else if (y < 0 ) {
				diff = y;
				
				ball.move(vx, -diff); // bounce up
				vx = rgen.nextDouble(1.0, 3.0);
				vy = -vy;
		} else if (y + BALL_RADIUS*2 > HEIGHT - PADDLE_Y_OFFSET - PADDLE_HEIGHT) {
			gObj = getElementAt(x + BALL_RADIUS, y + BALL_RADIUS*2 + 1);
			
			if (gObj != null) {
				diff = y - (HEIGHT - PADDLE_Y_OFFSET - PADDLE_HEIGHT - BALL_RADIUS*2); // 
				ball.move(0, -diff); // bounce up
				
				vx = -1 * rgen.nextDouble(1.0, 3.0);
				vy = -vy;
			} else {
				
				//String msg =  --turn != 0 ? "Next Turn" : "Game Over";
				
				turn--;
				if (turn > 0) {
					remove(ball);
					createBall();	
				} else {
					println("Game Over");
					return;
				}
				
			}
		} 
	}
	
	
	/*
	 * 
	 */
	private void checkForCollision() {
		gObj = getElementAt(ball.getX() + BALL_RADIUS, ball.getY() - 1);
		
		if (gObj != null) {
			remove(gObj);
			vx = rgen.nextDouble(1.0, 3.0);
			vy = -vy;
		} 
			
		if (ball.getY() <= BRICK_HEIGHT * NBRICK_ROWS + BRICK_SEP * (NBRICK_ROWS-1) + BRICK_Y_OFFSET) {
			
			gObj = getElementAt(ball.getX() + BALL_RADIUS, ball.getY() + BALL_RADIUS*2 + 1);
			if (gObj != null) {
			remove(gObj);
			vx = rgen.nextDouble(1.0, 3.0);
			vy = -vy;
			}
		} 
		

	}
	/** supply user class with methods of instance variable */
	private GRect paddle;
	private GOval ball;
	private int turn = 3; 
	private GObject gObj;
	private double vx, vy;
	private RandomGenerator rgen = RandomGenerator.getInstance();
	
}
