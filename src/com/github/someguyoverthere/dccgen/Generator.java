package com.github.someguyoverthere.dccgen;

import java.util.Scanner;

public class Generator {

	public static void main(String[] args) {
		int numofded;
		char selector;
		
		Scanner input = new Scanner(System.in);
		
		
		System.out.println("How many characters would you like to generate?");
		numofded = input.nextInt();
		input.nextLine();//clear buffer
		
		System.out.println("How would you like to generate ability scores?");
		System.out.println("A. 3d6 (As Crom intended!");
		System.out.println("B. 4d6 (Power Characters)");
		System.out.println("C. 2d10 (Jack's Deformed/Standard Mobs");
		System.out.println("D. Tetterdamalion's Heros (3d7, 7=6");
		System.out.println("E. Tatterdamalion's Gongfarmers (3d7, 7=1");
		System.out.println("F. 4d7 (Supers: Sezrekan Method)");
		System.out.println("G. 1d16+2 (Mad O'Murrish)");
		System.out.println("H. 6d6/2 (Average Joe)");
		
		selector = input.nextLine().charAt(0); // grabs the input character
		input.nextLine();// clear buffer
		
		//Ability score generation
		for(int i = 0; i < numofded ; i++) {
			switch(selector) {
			case 'a' | 'A':
				break;
			case 'b' | 'B':
				break;
			case 'c' | 'C':
				break;
			case 'd' | 'D':
				break;
			case 'e' | 'E':
				break;
			case 'f' | 'F':
				break;
			case 'g' | 'G':
				break;
			case 'h' | 'H':
				break;
			
			}
		}
		
		//Hit Point generation
		for(int i = 0; i < numofded ; i++) {
			switch(selector) {
			case 'a' | 'A':
				break;
			case 'b' | 'B':
				break;
			case 'c' | 'C':
				break;
			case 'd' | 'D':
				break;
			case 'e' | 'E':
				break;
			case 'f' | 'F':
				break;
			case 'g' | 'G':
				break;
			case 'h' | 'H':
				break;
			
			}
		}
		
		
		input.close();

	}
	
	

}
