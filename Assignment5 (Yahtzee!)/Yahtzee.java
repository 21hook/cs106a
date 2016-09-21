/*
 * File: Yahtzee.java
 * ------------------
 * This program will eventually play the Yahtzee game.
 */

import acm.io.*;
import acm.program.*;
import acm.util.*;

import java.io.*;
import java.util.*;


public class Yahtzee extends GraphicsProgram implements YahtzeeConstants {
	
	public static void main(String[] args) {
		new Yahtzee().start(args);
	}
	
	public void run() {
		IODialog dialog = getDialog();
		nPlayers = dialog.readInt("Enter number of players");
		playerNames = new String[nPlayers];
		for (int i = 1; i <= nPlayers; i++) {
			playerNames[i - 1] = dialog.readLine("Enter name for player " + i);
		}
		display = new YahtzeeDisplay(getGCanvas(), playerNames);
		playGame();
	}

	private void playGame() {
		int[]  dice  = new int[N_DICE]; // keep a sequence of dices with a random number
		int[][] scorecard = new int[nPlayers][TOTAL];
		int turns = N_SCORING_CATEGORIES;
		int category, score; // score for each player's turn
		
		
		for(int m=0; m<nPlayers; m++) 
			for(int n=0; n<TOTAL; n++)
				scorecard[m][n] = -1;

		while(turns-- != 0) { // the game is over
			for(int i=1; i<=nPlayers; i++) {//the index of players from 1 to length
				display.waitForPlayerToClickRoll(i);// init games on each turn's of a player; return when the button is pressed
				display.printMessage(playerNames[i-1] + "'s turn! Click \"Roll Dice\" button to roll the dice.");
				initRollDice(dice);
				display.displayDice(dice);
				
				
				for(int j=0; j<2; j++) { // each turn for each player
					display.waitForPlayerToSelectDice();// return  after the player has made a selection and clicks the "Roll Again" button
					display.printMessage("Select the dice you wish to re-roll and click \"Roll Again\".");
					if(!diceSelected(dice)) break; // if none of dices is selected
					rerollDice(dice);
					display.displayDice(dice);
				}
				
				
				category = display.waitForPlayerToSelectCategory(); 
				// error checking for category
				while (scorecard[i-1][category] != -1) { 
					category = display.waitForPlayerToSelectCategory();
					display.printMessage("The category is already selected. Select another category for this roll");
				} 
				score = totalScore(dice, category);
				scorecard[i-1][category] = score;
				display.updateScorecard(category, i, score);
				display.printMessage("Select a category for this roll");
				
			}
		}
		
		
		gameOver(scorecard);
	}
	
	/** Initial random roll 
	  generate a sequence of dices with a random number between 1 t0 6 
	 */
	private void initRollDice(int[] dice) {

		for(int i=0; i<dice.length; i++) 
				dice[i] = rgen.nextInt(1, 6);
		
	}
	
	/**
	 * Random reroll dices
	 * update specific dices with a random number as dices is selected 
	 * 
	 * @param dice dice dice list of each player's turn
	 */
	private void rerollDice(int[] dice) {
		for(int i=0; i<dice.length; i++) 
			if(display.isDieSelected(i)) 
				dice[i] = rgen.nextInt(1, 6);
		
	}
	
	/**
	 * Return true if none of dices is selected 
	 * 
	 * @param dice dice list of each player's turn
	 */
	private boolean diceSelected(int[] dice) {
		boolean bool = false;
		
		for(int i=0; i<dice.length; i++) {
			if(display.isDieSelected(i)) {
				bool = true;
				break;
			}
		}
		
		return bool;
	}
	
	/**
	 * Return total scores of each player's turn according to `category` which players chosen
	 *  
	 * @param dice dice list of each player's turn
	 */
	private int totalScore(int[] dice, int category) {
		int score = 0;
		
		/*
		 * Determining the validity for Ones, Twos, Threes, Fours, Fives, Sixes, 
		 * and Chance
		 */
		if(category >= ONES && category <= SIXES) 
			score = nums(dice, category);	
		else if(category == CHANCE) 
			score = numOfAKind(dice);
		/*
		 * Determining the validity for
		 * Three of a Kind, Four of a Kind, Yahtzee, and Full House;
	 	 * Straight and Large Straight
		 */
		else { 
			switch(category) {
	
				case THREE_OF_A_KIND: 
					if (checkCategory(dice, THREE_OF_A_KIND)) 
						score = numOfAKind(dice);
					break;
				case FULL_HOUSE: 
					if (checkCategory(dice, FULL_HOUSE))
						score = 25; 
					break;
				case FOUR_OF_A_KIND: 
					if (checkCategory(dice, FOUR_OF_A_KIND)) 
						score = numOfAKind(dice);
					break;	
				case YAHTZEE: 
					if (checkCategory(dice, YAHTZEE))
						score = 50; 
					break;
				case SMALL_STRAIGHT:
					if (checkCategory(dice, SMALL_STRAIGHT))
						score = 30;
					break;
				case LARGE_STRAIGHT:
					if (checkCategory(dice, LARGE_STRAIGHT))
						score = 40;
					break;		
			}
		} 
		
		return score;
	}
	
	/**
	 * Return scores for Ones, Twos, Threes, Fours, Fives, and Sixes.
	 * 
	 * @param dice dice list of each player's turn
	 * @return total scores
	 */
	private int nums(int[] dice, int category) {
		int total = 0;
		
		for(int i=0; i<dice.length; i++) 
			if (dice[i] == category) total += category; 
	
		return total;
	}
	
	/**
	 * Calculate total scores of Three of a Kind and Four of a Kind
	 *
	 * @param dice dice list of each player's turn
	 * @return total scores 
	 */
	private int numOfAKind(int[] dice) {
		int total = 0;

		for(int i=0; i<dice.length; i++)
			total += dice[i];
				
		return total;
	}
	
	/**
	 * This method determines Whether the category meets the validity or not
	 * The category contains:
	 * 	Three of a Kind, Four of a Kind, Yahtzee, and Full House;
	 * 	Straight and Large Straight
	 *     
	 * @param dice
	 * @param category
	 * @return boolean
	 */
	private boolean checkCategory(int[] dice, int category) {
		boolean bool = false;
		int state = 0; // full house state
		int[] sameNum = new int[6];  // record the same number of all dices on a new array, from 1 to 6
		
		for(int i=0; i<dice.length; i++) 
			sameNum[dice[i]-1] += 1; // 6-5, 5-4, 4-3,...1-0
		
		/* Determining the validity for Three of a
		Kind, Four of a Kind, Yahtzee, and Full House*/
		for(int j=0; j<sameNum.length; j++) {
			if(category == THREE_OF_A_KIND && sameNum[j] >= 3) {
				bool = true;
				break;
			} else if(category == FOUR_OF_A_KIND && sameNum[j] >= 4) {
				bool = true;
				break;
			} else if(category == YAHTZEE && sameNum[j] == 5) {
				bool = true;
				break;
 			} else if(category == FULL_HOUSE && (sameNum[j] == 2 || sameNum[j] == 3)) {
 				if (state == 0) state = sameNum[j]; // execute only once
 				
 				if (state == 2) {
 					if(sameNum[j] == 3) {
 						bool = true;
 						break;
 					}
 				} else if(state == 3) {
 					if(sameNum[j] == 2) {
 						bool = true;
 						break;
 					}
 				} 	 		
 			}
		}
		
		/* Determining the validity for Small
		Straight and Large Straight */ 
		if(category == SMALL_STRAIGHT || category == LARGE_STRAIGHT) {
			bool = true;
			
			for(int i=0; i<dice.length-1; i++) {
				if(dice[i] + 1 != dice[i+1]) {
					bool = false;
					break;
				} 
			}
			
		}

		
		return bool;
	}
	
	/**
	 * Calculate upper score, upper bonus if meet the requirement, lower score, total score
	 * 
	 * @param scorecard scorecard for all users
	 */
	private void gameOver(int[][] scorecard) {
		int upperScore = 0;
		int lowerScore = 0;
		int[] total = new int[nPlayers]; // total scores for all players
		
		
		for(int i=0; i<scorecard.length; i++) {
			for(int j=0; j<N_SCORING_CATEGORIES; j++) {
				
				if(j <= 5) { // upper score
					upperScore += scorecard[i][j];
					display.updateScorecard(UPPER_SCORE, i, upperScore);
					
					if(upperScore >= 63) { // upper bonus
						upperScore += 35; 
						display.updateScorecard(UPPER_BONUS, i, upperScore);
					}
				} else { // lower score
					lowerScore += scorecard[i][j]; 
					display.updateScorecard(LOWER_SCORE, i, lowerScore);	

				}				
			}
			total[i] = upperScore + lowerScore;
			scorecard[i][N_SCORING_CATEGORIES] = total[i]; // total score
			display.updateScorecard(TOTAL, i, total[i]);	
		}
		
		
		int max = 0; 
		int index = 0;
		for(int i=0; i<total.length; i++) { // nplayer = [1, nplayer] 
			if(max < total[i]) {
				max = total[i];
				index = i;
			}
		}
		
		ArrayList<Integer> scoreRecords = new ArrayList<Integer>(); // 10 highest scores
		
		for(int i=0; i<MAX_NUM_RECORD; i++) 
			scoreRecords.add(new Integer(0));
		
		readScoreFile(scoreRecords);
		updateScores(scoreRecords, total);
		
		
		display.printMessage("Congrdulations, " + playerNames[index+1] + ", you're the winner "
				+ "with a total score of " + max + ". Hall of Fame:"
				+ scoreRecords);

		saveScoreFile(scoreRecords);
	}
	
	//ToDo
	private void saveScoreFile(ArrayList scoreRecords) {
		BufferedReader rd = null;
		
	}
	

	private void updateScores(ArrayList scoreRecords, int[] total) {
		
		
			// sort total
			for(int i=1; i<total.length; i++) {
				for(int j=0; j<i; j++) {
					if(total[i] > total[j]) {
						int tmp;
						tmp = total[i];
						total[i] = total[j];
						total[j] = tmp;
					}
				}
			}
			 
			// insert total to scoreRecords, <=10
			for(int i=0; i<total.length; i++) {
				for(int j=MAX_NUM_RECORD; j>=0; j--) {
					if(total[i] > (Integer)scoreRecords.get(j)) {
						scoreRecords.set(new Integer(j), new Integer(total[i]));
					}
				}
			}
	
		 
	}
	
	private void readScoreFile(ArrayList scoreRecords) {
		
		BufferedReader rd = null;
		
		try{ // put open operations in try 
			rd = new BufferedReader(new FileReader("hallOfFame.txt"));
		} catch (IOException ex) {
			println("Can't open that file.");
		}
			
		try{
			for(int i=0; i<MAX_NUM_RECORD; i++) {
				String line = rd.readLine(); 
				if(line == null) break;
				scoreRecords.set(i, Integer.parseInt(line));
			}
			rd.close();
		} catch(IOException ex) {
			println("An I/O exception has occurred");
		}
		
		
	}
	
		
/* Private instance variables */
	private static final int MAX_NUM_RECORD = 10;
	private int nPlayers;
	private String[] playerNames;
	private YahtzeeDisplay display;
	private RandomGenerator rgen = new RandomGenerator();

}
