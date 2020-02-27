/*
 * 
 */
package com.github.someguyoverthere.dccgen.character;

import java.util.ArrayList;

public class PCharacter {
	// Primary Attributes
	private int STR;
	private int AGI;
	private int STM;
	private int INT;
	private int PER;
	private int LUK;

	// Derived stats
	private int HP;
	private int AC;

	// Saves
	private int Fort;
	private int Reflex;
	private int Will;

	private ArrayList<String> inventory = new ArrayList<>(); // this contains the character's initial items
	private int copper; // money
	private int number;
	private String occupation;
	private String birthSign;
	private int Initiative;

	public PCharacter() {// default constructor

	}

	public PCharacter(int num) {
		number = num;
	}
	
	public void setOccupation(String occ) {
		occupation = occ;
	}
	
	public String getBirthsign() {
		return birthSign;
	}
	
	public void setBirthSign(String bSign) {
		birthSign = bSign;
	}
	
	public String getOccupation() {
		return occupation;
	}

	public void setNumber(int num) {
		number = num;
	}

	public int getNum() {
		return number;
	}

	public void setCopper(int cpr) {
		copper = cpr;
	}

	public int getCopper() {
		return copper;
	}
	
	public void addItem(String item) {
		inventory.add(item);
	}

	public void setInventory(ArrayList<String> stuff) {
		inventory = stuff;
	}

	public ArrayList<String> getInventory() {
		return inventory;
	}
	

	public int getSTR() {
		return STR;
	}

	public void setSTR(int sTR) {
		STR = sTR;
	}

	public int getAbilityMod(int ability) {
		int mod = 0;
		switch (ability) {
		case 3:
			mod = -3;
			break;
		case 4:
		case 5:
			mod = -2;
			break;
		case 6:
		case 7:
		case 8:
			mod = -1;
			break;
		case 9:
		case 10:
		case 11:
		case 12:
			mod = 0;
			break;

		case 13:
		case 14:
		case 15:
			mod = 1;
			break;
		case 16:
		case 17:
			mod = 2;
			break;
		case 18:
			mod = 3;
			break;

		}
		return mod;
	}

	public int getAGI() {
		return AGI;
	}

	public void setAGI(int aGI) {
		AGI = aGI;
	}

	public int getSTM() {
		return STM;
	}

	public void setSTM(int sTM) {
		STM = sTM;
	}

	public int getINT() {
		return INT;
	}

	public void setINT(int iNT) {
		INT = iNT;
	}

	public int getPER() {
		return PER;
	}

	public void setPER(int pER) {
		PER = pER;
	}

	public int getLUK() {
		return LUK;
	}

	public void setLUK(int lUK) {
		LUK = lUK;
	}

	public int getHP() {
		return HP;
	}

	public void setHP(int hP) {
		HP = hP;
	}

	public int getAC() {
		return AC;
	}

	public void setAC(int aC) {
		AC = aC;
	}
	
	public void calculateAC() {
		AC = 10 + getAbilityMod(getAGI());
	}

	public int getFort() {
		return Fort;
	}

	public void setFort(int fort) {
		Fort = fort;
	}
	
	public void calculateFort() {
		Fort = getAbilityMod(getSTM());
	}
	
	public void calculateInit() {
		Initiative = getAbilityMod(getAGI());
	}

	public int getReflex() {
		return Reflex;
	}


	public void setReflex(int reflex) {
		Reflex = reflex;
	}
	
	public void calculateReflex() {
		Reflex = getAbilityMod(getAGI());
	}

	public int getWill() {
		return Will;
	}

	public void setWill(int will) {
		Will = will;
	}
	
	public void calculateWill() {
		Initiative = getAbilityMod(getPER());
	}

}
