/**
 * 
 */
package saveOurPlanet;

import java.util.Random;

/**
 * @author Andrew Grainger This is the Dice class. The player will roll the dice
 *         during their turn and the players position on the board will be
 *         updated from the result of two rolls.
 **/
public class Dice {
	//final variables
	private final int DICE_FACES = 6;
	
	//instance variables
	private int maxDiceValue;

	/**
	 * Constructor with no arguments
	 */
	public Dice() {
		this.setMaxDiceValue(DICE_FACES);
	}

	/**
	 * @return the maxDiceValue
	 */
	public int getMaxDiceValue() {
		return maxDiceValue;
	}

	/**
	 * @param maxDiceValue the maxDiceValue to set
	 */
	public void setMaxDiceValue(int maxDiceValue) {
		this.maxDiceValue = maxDiceValue;
	}

	/**
	 * This method uses the RNG to throw 2 dice. The result will be displayed to the
	 * console.
	 * 
	 * @return the total int value of two rolls
	 */
	public int roll() {
		int resultOne, resultTwo, total;
		Random num = new Random();

		resultOne = num.nextInt(this.getMaxDiceValue()) + 1;
		resultTwo = num.nextInt(this.getMaxDiceValue()) + 1;
		total = resultOne + resultTwo;

		System.out.printf("You've rolled a %d and a %d - that makes %d %n", resultOne, resultTwo, total);
		return total;
	}

}
