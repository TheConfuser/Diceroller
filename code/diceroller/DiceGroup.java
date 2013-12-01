package code.diceroller;

import java.util.ArrayList;

/**
 * A dicegroup creates a number of dices and stores them in the group. 
 * 
 * Each dicethrow contains one or more dicegroups, depending on the number of sixes being thrown. 
 * 
 * @author Confuse
 *
 */
public class DiceGroup {

	// Stores each individual dice
	private ArrayList<Dice> dices;
	private int groupNumber;
	
	/**
	 * Constructor for the dicegroup-class. 
	 * Initiates the arraylist of dices and constructs the dices.
	 * 
	 * @param diceGroupNumber the number of the dice group
	 * @param numberOfDices the number of dices for the group to create
	 */
	public DiceGroup(int diceGroupNumber, int numberOfDices) {
		groupNumber = diceGroupNumber;
		dices = new ArrayList<Dice>();
		for(int i = 0; i < numberOfDices; i++) {
			Dice d = new Dice();
			dices.add(d);
		}
	}
	
	/**
	 * Empty constructor for testing purposes. 
	 */
	public DiceGroup() {
		dices = new ArrayList<Dice>();
	}
	
	/**
	 * Constructor for testnig purposes. 
	 * @param diceGroupNumber the number for this dice group
	 */
	public DiceGroup(int diceGroupNumber) {
		groupNumber = diceGroupNumber;
		dices = new ArrayList<Dice>();
	}
	
	/*
	 * Only for testing purposes, this function is never used in the actual code. 
	 */
	public void populate(int[] diceNumbers) {
		dices.clear();
		for (int i : diceNumbers) 
			dices.add(new Dice(i));
	}
	
	/**
	 * Return the arraylist of dices that is stored for this dicegroup. 
	 */
	public ArrayList<Dice> getDiceList() {
		return dices;
	}
	
	/**
	 * Returns a string containing the result of the dices in this group. 
	 */
	public String toString() {
		String returnString = "";
		for(Dice d : dices) {
			if (d.getDiceValue() == 6)
				returnString += "(" + d + ") ";
			else {
				returnString += d + " ";
			}
		}
		return returnString;
	}
	
	/**
	 * Returns the total value for the dices in this dicegroup. 
	 * @return the value of all dices in the group
	 */
	public Integer getTotalValue() {
		int totalValueOfDiceGroup = 0;
		for(Dice d : dices) {
			if(d.getDiceValue() < 6) {
				totalValueOfDiceGroup += d.getDiceValue();
			}
			} // for
		return totalValueOfDiceGroup;
	} // getTotalValue
	
	public int getGroupNumber() {
		return groupNumber;
	}
	
}
