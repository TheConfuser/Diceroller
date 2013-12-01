package code.diceroller;


public abstract class DiceLogic {

	/**
	 * Calculates the existance of perfect throw or not, 
	 * @param dg the dicegroup to check
	 * @return a boolean
	 */
	public boolean checkForPerfectThrow(DiceGroup dg) {
		boolean perfectResult = false;
		
		int sixes = 0;
		int ones = 0;
		int numberOfDices = dg.getDiceList().size();
		
		for (Dice d : dg.getDiceList()) {
			if(d.getDiceValue() == 1)
				ones += 1;
			else if (d.getDiceValue() == 6)
				sixes += 1;
		}
		
		if (ones >= numberOfDices - 1 && ones > 0) {
			if(sixes > 0 || dg.getGroupNumber() > 1) 
				perfectResult = false;
			else if (sixes == 0 && dg.getGroupNumber() == 1)
				perfectResult = true;
		}
		
		return perfectResult;
	}
	
	/**
	 * Calculates the number of dice six there is in the dicegroup. 
	 * @param dg a dicegroup to be calculated
	 * @return the number of sixes
	 */
	public Integer numberOfDiceSix(DiceGroup dg) {
		int numberOfNewDices = 0;
		for(Dice dice : dg.getDiceList()) {
			if(dice.getDiceValue() == 6)
				numberOfNewDices += 2;
		}
		return numberOfNewDices;
	}
	
}
