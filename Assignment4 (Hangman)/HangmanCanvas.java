/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import acm.graphics.*;

public class HangmanCanvas extends GCanvas {

/** Resets the display so that only the scaffold appears */
	public void reset() {
		double width = getWidth() / 2;
		double height = getHeight() / 2;
		
		GLine scaffold = new GLine(width-BEAM_LENGTH, height - 0.6*SCAFFOLD_HEIGHT ,width-BEAM_LENGTH, height + 0.4*SCAFFOLD_HEIGHT);
		GLine beam = new GLine(width - BEAM_LENGTH, height - 0.6*SCAFFOLD_HEIGHT, width, height - 0.6*SCAFFOLD_HEIGHT);
		GLine rope = new GLine(width, height - 0.6*SCAFFOLD_HEIGHT, width, height - 0.6*SCAFFOLD_HEIGHT + ROPE_LENGTH);
		
		add(scaffold);
		add(beam);
		add(rope);

	}

/**
 * Updates the word on the screen to correspond to the current
 * state of the game.  The argument string shows what letters have
 * been guessed so far; unguessed letters are indicated by hyphens.
 */
	public void displayWord(String word) {
		if (label4GuessWord != null) remove(label4GuessWord);

		label4GuessWord = new GLabel(word);
		//label4GuessWord.setFont("");
		add(label4GuessWord, 50, 400);
	}

/**
 * Updates the display to correspond to an incorrect guess by the
 * user.  Calling this method causes the next body part to appear
 * on the scaffold and adds the letter to the list of incorrect
 * guesses that appears at the bottom of the window.
 */
	public void noteIncorrectGuess(char letter) {
		wrongGuess++;
		updateBody();
		updateChar(letter);
	}

	/**
	 * Adds a letter to the list of incorrect
	 * guesses that appears at the bottom of the window.
	 */
	private void updateChar(char ch) {
		if (label4WrongWord != null) remove(label4WrongWord); // @ToDo Can't remove objects that do not exist before
		
		label4WrongWord = new GLabel(wrongWord += ch);
		add(label4WrongWord, 50, 420);
	}
	
	/**
	 * Update body part against `wrongGuess`
	 */
	private void updateBody() {
		double width = getWidth() / 2;
		double height = getHeight() / 2;
		
		GOval head = new GOval(width - HEAD_RADIUS, height - 0.6*SCAFFOLD_HEIGHT + ROPE_LENGTH, HEAD_RADIUS * 2, HEAD_RADIUS * 2);
		GLine body = new GLine(width, height - 0.6*SCAFFOLD_HEIGHT + ROPE_LENGTH + HEAD_RADIUS * 2, width, BODY_LENGTH + height - 0.6*SCAFFOLD_HEIGHT + ROPE_LENGTH + HEAD_RADIUS * 2);		
		
		// left arm 
		GLine _armUpper = new GLine(width, height - 0.6*SCAFFOLD_HEIGHT + ROPE_LENGTH  + HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD,
									width - UPPER_ARM_LENGTH, height - 0.6*SCAFFOLD_HEIGHT + ROPE_LENGTH  + HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD);
		
		GLine _armLower = new GLine(_armUpper.getEndPoint().getX(), _armUpper.getEndPoint().getY(), 0, 0);
		_armLower.setEndPoint(_armUpper.getEndPoint().getX(), _armUpper.getEndPoint().getY() + LOWER_ARM_LENGTH);
		
		// right arm
		GLine armUpper_ = new GLine(width, height - 0.6*SCAFFOLD_HEIGHT + ROPE_LENGTH + HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD, 
									width + UPPER_ARM_LENGTH, height - 0.6*SCAFFOLD_HEIGHT + ROPE_LENGTH  + HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD);

		GLine armLower_ = new GLine(armUpper_.getEndPoint().getX(), armUpper_.getEndPoint().getY(), 0, 0);
		armLower_.setEndPoint(armUpper_.getEndPoint().getX(), armUpper_.getEndPoint().getY() + LOWER_ARM_LENGTH);

		// left leg
		GLine _hip = new GLine(width, body.getEndPoint().getY(), width - HIP_WIDTH, body.getEndPoint().getY());		
		GLine _leg = new GLine(width - HIP_WIDTH, body.getEndPoint().getY(), 
								width - HIP_WIDTH, body.getEndPoint().getY() + LEG_LENGTH);
		
		// right leg
		GLine hip_ = new GLine(width, body.getEndPoint().getY(), width + HIP_WIDTH, body.getEndPoint().getY());
		GLine leg_ = new GLine(width + HIP_WIDTH, body.getEndPoint().getY(),
				width + HIP_WIDTH, body.getEndPoint().getY() + LEG_LENGTH);
		
		// left foot
		GLine _foot = new GLine(_leg.getEndPoint().getX(), _leg.getEndPoint().getY(), _leg.getEndPoint().getX() - FOOT_LENGTH, _leg.getEndPoint().getY());
		// right foot
		GLine foot_ = new GLine(leg_.getEndPoint().getX(), leg_.getEndPoint().getY(),  leg_.getEndPoint().getX() + FOOT_LENGTH, leg_.getEndPoint().getY());
		
		
		switch (wrongGuess) {
			case 1:	add(head);
					break;
			case 2: add(body);
					break;
			case 3: add(_armUpper);
					add(_armLower);
					break;
			case 4: add(armUpper_);
					add(armLower_);
					break;
			case 5: add(_hip);
					add(_leg);
					break;
			case 6: add(hip_);
					add(leg_);
					break;
			case 7: add(_foot);
					break;
			case 8: add(foot_);
					break;
		}
 
	}

	
/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;
	
	// the number of wrong guesses users entered
	private int wrongGuess = 0;
	private String wrongWord = "";
	private GLabel label4WrongWord = null;
	private GLabel label4GuessWord = null;

}
