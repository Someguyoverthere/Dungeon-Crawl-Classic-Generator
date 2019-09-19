/*
 * Contains a roll function and other supporting functions
 */
package com.github.someguyoverthere.dccgen;

public class Roller {
	
	/**
	 * Roll.
	 *
	 * @param diceNum the dice num
	 * @param diceSize the dice size
	 * @return the int[]
	 */
	public static int[] rollSet(int diceNum, int diceSize) {
		int[] rolls = new int[diceNum];
		for(int i = 0; i < diceNum; i++) {
			rolls[i] = (int) (Math.random() * (diceSize - 1)) + 1;
		}
			
		
		return rolls;
		
	}
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
