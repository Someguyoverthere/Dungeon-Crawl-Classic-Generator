/*
 * Contains a roll function and other supporting functions
 */
package com.github.someguyoverthere.dccgen;

public class Roller {
	
	/**
	 * Rolls dice and places the values into an array.
	 *
	 * @param diceNum Number of dice rolled
	 * @param diceSize the maximum roll possible on each die
	 * @return The dice rolls in the form of an array
	 */
	public static int[] rollSet(int diceNum, int diceSize) {
		int[] rolls = new int[diceNum];
		for(int i = 0; i < diceNum; i++) {
			rolls[i] = (int) (Math.random() * (diceSize - 1)) + 1;
		}
			
		
		return rolls;
		
	}
	
	/**
	 * Rolls dice and returns the cumulative value of the rolls.
	 *
	 * @param diceNum Number of dice rolled
	 * @param diceSize the maximum roll possible on each die
	 * @return returns the cumulative value of the rolls
	 */
	public static int rollSingle(int diceNum, int diceSize) {
		int total = 0;
		for(int i = 0; i < diceNum; i++) {
			total += (int) (Math.random() * (diceSize - 1)) + 1;
		}
			
		
		return total;
		
	}
	
	/**
	 * This function takes an array and changes a specific number within the array into another number.
	 *
	 * @param rolls is an array that contains dice rolls
	 * @param oldNum is the number that you want to change
	 * @param newNum is the number oldNum will become
	 * @return the int[] after being modified
	 */
	public static int[] rollConverter(int[] rolls, int oldNum, int newNum) {
		for(int i = 0; i < rolls.length; i++) {
			if(rolls[i] == oldNum) {
				rolls[i] = newNum;
			}
		}
		
		return rolls;
	}
}
