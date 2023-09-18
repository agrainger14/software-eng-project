/**
 * 
 */
package saveOurPlanet;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Andrew
 *
 */
class PlayerTest {

	// test variables
	Player testPlayer1;
	Player testPlayer2;
	String playerNameValidLow, playerNameValidHigh;
	String playerNameInvalidLow, playerNameInvalidHigh;
	String emptyString, nameWithSpace;
	FieldArea testArea1, testArea2, testArea3, testArea4, testArea5;
	int fundsValid;
	int fundsInvalid;
	Dice dice;
	List<FieldArea> testOwnedAreas;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		testOwnedAreas = new ArrayList<FieldArea>();
		dice = new Dice();
		testPlayer1 = new Player();
		testPlayer2 = new Player();
		playerNameValidLow = "bob";
		playerNameValidHigh = "abcdefghijklmnopqrst"; // 20 characters
		playerNameInvalidLow = "Bo";
		playerNameInvalidHigh = "abcdefghijklmnopqrstu"; // 21 characters
		testPlayer1.setName(playerNameValidHigh);
		testPlayer2.setName(playerNameValidLow);
		emptyString = "";
		nameWithSpace = "Bob Space";
		testArea1 = new FieldArea("Test Area1", Field.REFORESTATION, 200, 50, 250, 300, "Test Development1");
		testArea2 = new FieldArea("Test Area2", Field.RENEWABLE_ENERGY, 100, 25, 125, 150, "Test Development2");
		testArea3 = new FieldArea("Test Area3", Field.CONSERVATION, 300, 150, 350, 600, "Test Development3");
		testArea4 = new FieldArea("Test Area4", Field.WASTE_MANAGEMENT, 150, 60, 300, 100, "Test Development4");
		testArea5 = new FieldArea("Test Area5", Field.WASTE_MANAGEMENT, 100, 30, 50, 200, "Test Development5");
		testOwnedAreas.add(testArea1);
		testOwnedAreas.add(testArea2);
		testOwnedAreas.add(testArea3);
		testOwnedAreas.add(testArea4);
		fundsValid = 500;
		fundsInvalid = -100;
	}

	@Test
	void testConstructor() {
		Player player = new Player();
		assertNotNull(player);
	}

	/**
	 * Test method for {@link saveOurPlanet.Player#getName()}.
	 */
	@Test
	void testGetSetNameValidLow() {
		// As the method sets the first char in the name to the capital, we need to
		// check ignoring cases.
		testPlayer1.setName(playerNameValidLow);
		assertTrue(playerNameValidLow.equalsIgnoreCase(testPlayer1.getName()));
	}

	/**
	 * Test method for {@link saveOurPlanet.Player#getName()}.
	 */
	@Test
	void testGetSetNameValidHigh() {
		// As the method sets the first char in the name to the capital, we need to
		// check ignoring cases.
		testPlayer1.setName(playerNameValidHigh);
		assertTrue(playerNameValidHigh.equalsIgnoreCase(testPlayer1.getName()));
	}

	/**
	 * Test method for {@link saveOurPlanet.Player#setName(java.lang.String)}.
	 */
	@Test
	void testGetSetNameInvalidLow() {
		assertThrows(IllegalArgumentException.class, () -> {
			testPlayer1.setName(playerNameInvalidLow);
		});
	}

	/**
	 * Test method for {@link saveOurPlanet.Player#setName(java.lang.String)}.
	 */
	@Test
	void testGetSetNameInvalidHigh() {
		assertThrows(IllegalArgumentException.class, () -> {
			testPlayer1.setName(playerNameInvalidHigh);
		});
	}

	/**
	 * Test method for {@link saveOurPlanet.Player#setName(java.lang.String)}.
	 */
	@Test
	void testGetSetNameInvalidEmpty() {
		assertThrows(IllegalArgumentException.class, () -> {
			testPlayer1.setName(emptyString);
		});
	}

	/**
	 * Test method for {@link saveOurPlanet.Player#setName(java.lang.String)}.
	 */
	@Test
	void testGetSetNameInvalidNameSpace() {
		assertThrows(IllegalArgumentException.class, () -> {
			testPlayer1.setName(nameWithSpace);
		});
	}

	/**
	 * Test method for {@link saveOurPlanet.Player#setName(java.lang.String)}.
	 */
	@Test
	void testGetSetNameInvalidNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			testPlayer1.setName(null);
		});
	}

	/**
	 * Test method for {@link saveOurPlanet.Player#getEcoFunds()}.
	 */
	@Test
	void testGetSetEcoFundsValid() {
		testPlayer1.setEcoFunds(500);
		assertEquals(500, testPlayer1.getEcoFunds());
	}

	/**
	 * Test method for {@link saveOurPlanet.Player#setEcoFunds(int)}.
	 */
	@Test
	void testSetEcoFundsInvalid() {
		assertThrows(IllegalArgumentException.class, () -> {
			testPlayer1.setEcoFunds(fundsInvalid);
		});
	}

	/**
	 * Test method for {@link saveOurPlanet.Player#getPosition()}.
	 */
	@Test
	void testGetSetPosition() {
		int roll = dice.roll();
		testPlayer1.setPosition(roll);
		assertEquals(roll, testPlayer1.getPosition());
	}

	/**
	 * Test method for {@link saveOurPlanet.Player#getOwnedAreas()}.
	 */
	@Test
	void testGetSetOwnedAreas() {
		testPlayer1.setOwnedAreas(testOwnedAreas);
		assertEquals(testOwnedAreas, testPlayer1.getOwnedAreas());
	}

	/**
	 * Test method for
	 * {@link saveOurPlanet.Player#addArea(saveOurPlanet.FieldArea)}.
	 */
	@Test
	void testAddArea() {
		testPlayer1.addArea(testArea5);
		assertTrue(testPlayer1.getOwnedAreas().contains(testArea5));
	}

	/**
	 * Test method for
	 * {@link saveOurPlanet.Player#investFunds(saveOurPlanet.FieldArea)}.
	 */
	@Test
	void testInvestFunds() {
		testArea1.setOwner(testPlayer2);

		int fundsToInvest = testArea1.getInvestCost();
		int result = testPlayer1.getEcoFunds() - fundsToInvest;

		testPlayer1.investFunds(testArea1);

		assertEquals(result, testPlayer1.getEcoFunds());
	}

	/**
	 * Test method for
	 * {@link saveOurPlanet.Player#isAreaOwner(saveOurPlanet.FieldArea)}.
	 */
	@Test
	void testIsAreaOwnerValid() {
		testPlayer1.addArea(testArea5);
		assertTrue(testPlayer1.isAreaOwner(testArea5));
	}

	/**
	 * Test method for
	 * {@link saveOurPlanet.Player#isAreaOwner(saveOurPlanet.FieldArea)}.
	 */
	@Test
	void testIsAreaOwnerInvalid() {
		testPlayer1.addArea(testArea5);
		assertFalse(testPlayer1.isAreaOwner(testArea4));
	}

	/**
	 * Test method for {@link saveOurPlanet.Player#checkFunds(int)}.
	 */
	@Test
	void testCheckFundsValid() {
		testPlayer1.setEcoFunds(fundsValid);
		assertTrue(testPlayer1.checkFunds(testArea1.getAreaCost()));
	}

	/**
	 * Test method for {@link saveOurPlanet.Player#checkFunds(int)}.
	 */
	@Test
	void testCheckFundsInvalid() {
		testPlayer1.setEcoFunds(fundsValid);
		assertTrue(testPlayer1.checkFunds(testArea3.getAreaCost()));
	}

	/**
	 * Test method for {@link saveOurPlanet.Player#hasFunds()}.
	 */
	@Test
	void testHasFundsValid() {
		testPlayer1.setEcoFunds(fundsValid);
		assertTrue(testPlayer1.hasFunds());
	}

	/**
	 * Test method for {@link saveOurPlanet.Player#minusFunds(int)}.
	 */
	@Test
	void testMinusFunds() {
		int fundsToMinus = 200;
		int result = testPlayer1.getEcoFunds() - fundsToMinus;

		testPlayer1.minusFunds(fundsToMinus);

		assertEquals(result, testPlayer1.getEcoFunds());
	}

	/**
	 * Test method for {@link saveOurPlanet.Player#addFunds(int)}.
	 */
	@Test
	void testAddFunds() {
		int fundsToAdd = 200;
		int result = testPlayer1.getEcoFunds() + fundsToAdd;

		testPlayer1.addFunds(fundsToAdd);
		assertEquals(result, testPlayer1.getEcoFunds());
	}
}
