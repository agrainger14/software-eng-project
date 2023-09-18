package saveOurPlanet;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SaveOurPlanetGameTest {

	// test variables
	SaveOurPlanetGame testGame;
	int playerAmountValidLow, playerAmountValidHigh;
	int playerAmountInvalidLow, playerAmountInvalidHigh;
	Player testPlayer1;
	Player testPlayer2;
	Player testPlayer3;
	Player testPlayer4;

	@BeforeEach
	void setUp() throws Exception {
		testPlayer1 = new Player();
		testPlayer2 = new Player();
		testPlayer3 = new Player();
		testPlayer4 = new Player();
		testGame = new SaveOurPlanetGame();
		playerAmountValidLow = 2;
		playerAmountValidHigh = 4;
		playerAmountInvalidLow = 1;
		playerAmountInvalidHigh = 5;
	}

	@Test
	void testConstructor() {
		SaveOurPlanetGame testGame1 = new SaveOurPlanetGame();
		assertNotNull(testGame1);
	}

	@Test
	void testGetSetMinPlayersValid() {
		testGame.setMaxPlayers(playerAmountValidLow);
		assertEquals(playerAmountValidLow, testGame.getMaxPlayers());
	}

	@Test
	void testGetSetMinPlayersInvalid() {
		assertThrows(IllegalArgumentException.class, () -> {
			testGame.setMinPlayers(playerAmountInvalidLow);
		});
	}

	@Test
	void testGetSetMaxPlayersValid() {
		testGame.setMaxPlayers(playerAmountValidHigh);
		assertEquals(playerAmountValidHigh, testGame.getMaxPlayers());
	}

	@Test
	void testGetSetMaxPlayersInvalid() {
		assertThrows(IllegalArgumentException.class, () -> {
			testGame.setMaxPlayers(playerAmountInvalidHigh);
		});
	}

	@Test
	void testPlayerList() {
		testGame.setMaxPlayers(playerAmountValidLow);
		assertEquals(playerAmountValidLow, testGame.getMaxPlayers());
	}

	@Test
	void testPlayerAmount() {
		testGame.setMaxPlayers(playerAmountValidLow);
		assertEquals(playerAmountValidLow, testGame.getMaxPlayers());
	}

}
