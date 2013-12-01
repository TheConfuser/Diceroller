package code.diceroller;

import java.util.Random;


public class Dice {
	
	private int diceNumber;
	private Random randomNumberGenerator;
	
	public Dice() {
		this.randomNumberGenerator = new Random();
		this.diceNumber = randomNumberGenerator.nextInt(6) + 1;
	}
	
	/*
	 * This function is only used for testing purposes. 
	 * 
	 * @param diceValue the value to set the dice 
	 */
	public Dice(int diceValue) {
		this.diceNumber = diceValue;
	}

	public Integer getDiceValue() {
		return diceNumber;
	}
	
	/**
	 * Prints the number of the dice. 
	 */
	public String toString() {
		return Integer.toString(this.getDiceValue());
	}
}
