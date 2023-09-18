package saveOurPlanet;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DiceTest {
	// test variables
	Dice dice;

	@BeforeEach
	void setUp() throws Exception {
		dice = new Dice();
	}

	@Test
	void testDice() {
		assertNotNull(dice);
	}

	@Test
	void testRoll() {
		// roll results must be between 2 - 12 inclusive.
		for (int i = 0; i < 1000; i++) {
			assertTrue(dice.roll() >= 2 && dice.roll() <= 12);
		}
	}

}
