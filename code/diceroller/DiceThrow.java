package code.diceroller;

import java.util.ArrayList;


/**
 * A class for each throw of dices. 
 * 
 * Creates one or more dicegroups depending on the number of sixes being thrown. 
 * @author Confuse
 *
 */
public class DiceThrow extends DiceLogic {
	
	// AN arraylist to store the created dicegroups. 
	private ArrayList<DiceGroup> diceGroupList;
	private int diceGroupNumber;
	public boolean perfect = false;
	
	/**
	 * Constructor for the dicethrow-class, initiates the arraylist and continues to loop until no dicegroup contains a dice six. 
	 * @param numberOfDicesToThrow
	 */
	public DiceThrow(int numberOfDicesToThrow) {
		diceGroupList = new ArrayList<DiceGroup>();
		while(numberOfDicesToThrow > 0) {
			diceGroupNumber += 1;
			DiceGroup dg = new DiceGroup(diceGroupNumber, numberOfDicesToThrow);
			diceGroupList.add(dg);
			numberOfDicesToThrow = numberOfDiceSix(dg);
			if(checkForPerfectThrow(dg)) {
				perfect = true;
			}
		} // while
	}
	
	
	/**
	 * Returns the result of the dicegroups that has been created for this dicethrow. 
	 * @return a string with dicegroup-results
	 */
	public String getTotalValueOfThrow() {
		String diceThrowResult = "";
		int totalValue = 0;
		for(DiceGroup dg : diceGroupList) {
			diceThrowResult += "Group: " + dg.getGroupNumber() + "     " + 
								dg.toString() + "     " + 
								dg.getTotalValue() + "\n";
			totalValue += dg.getTotalValue();
		}
		return diceThrowResult + "Total: " + totalValue + "\n\n";
	}
	
	public String toString() {
		return getTotalValueOfThrow();
	}
}
