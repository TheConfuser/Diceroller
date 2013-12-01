package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import code.diceroller.DiceGroup;

public class DiceLogicTest {
	
	private static DiceGroup testDiceGroup;
	private static DiceLogicMock diceLogicMockObject;
	
	@BeforeClass
	static public void initTests() {
		diceLogicMockObject = new DiceLogicMock();
		
		testDiceGroup = new DiceGroup(1);
	}
	
	/**
	 * Populates the arraylist with dices. 
	 * @param one first dicevalue
	 * @param two second dicevalue
	 * @param three third dicevalue
	 */
	public void populateList(DiceGroup dg, int... number) {
		dg.populate(number);
	}

	@Test
	public void checkPerfectWithAllOnesTest() {
		this.populateList(testDiceGroup, 1, 1, 1);
		
		assertTrue("Total minus one dices should be one to reach perfect throw. ", 
					diceLogicMockObject.checkForPerfectThrow(testDiceGroup));
	}
	
	@Test
	public void checkPerfectWithOnesAndSixesTest() {
		this.populateList(testDiceGroup, 1, 1, 6);
		assertFalse("A six-dice should not enable perfect throws. ", 
				diceLogicMockObject.checkForPerfectThrow(testDiceGroup));
	}
	
	@Test
	public void checkPerfectWithRandomTest() {
		this.populateList(testDiceGroup, 1, 2, 3);
		assertFalse("Random dices should not return perfect throw.", 
				diceLogicMockObject.checkForPerfectThrow(testDiceGroup));
	}
	
	@Test
	public void checkPerfectWithGroupNumber() {
		DiceGroup dg = new DiceGroup(2);
		this.populateList(dg, 1, 1, 2);
		assertFalse("Only dicegroup 1 should return perfect throw. ", 
				diceLogicMockObject.checkForPerfectThrow(dg));
	}
	
	@Test
	public void checkPerfectWithOnlyOneDIce() {
		DiceGroup dg = new DiceGroup(1);
		this.populateList(dg, 1);
		assertTrue("If one dice is thrown and it shows dice one, it should be perfect. ", 
				diceLogicMockObject.checkForPerfectThrow(dg));
		
		this.populateList(dg,  2);
		assertFalse("If throw with one dice is not 1, it should not be perfect. ", 
				diceLogicMockObject.checkForPerfectThrow(dg));
	}
	
	/**
	 * Deprecated, no such check available. 
	 * @author Joakim Rehn
	 */
	@Ignore
	public void checkForSixesTest() {
		this.populateList(1, 1, 6);
		assertFalse("A six-dice should not enable perfect throws. ", 
				diceLogicMockObject.checkForPerfectThrow(testDiceGroup));
	}

}
