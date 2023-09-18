package saveOurPlanet;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;

/**
 * @author Andrew Grainger This it the main game class which will play and
 *         control SaveOurPlanet the Game.
 *
 */
public class SaveOurPlanetGame {
	// scanner
	private Scanner scanner;

	// final variables
	private final int MIN_PLAYERS = 2;
	private final int MAX_PLAYERS = 4;
	private final double TEMPERATURE_INCREASE = 0.1;
	private final double TEMPERATURE_LOSS_CONDITION = 2.0;

	// instance variables
	private int minPlayers;
	private int maxPlayers;
	private boolean gameOver;
	private ArrayList<Player> playerList;
	Set<Player> playerSet;
	private Board board;
	private double globalAverageTemperature;

	/*
	 * Constructor without args
	 */
	public SaveOurPlanetGame() {
		scanner = new Scanner(System.in);
		playerList = new ArrayList<Player>();
		playerSet = new HashSet<Player>();
		this.setGameOver(false);
		this.setMinPlayers(MIN_PLAYERS);
		this.setMaxPlayers(MAX_PLAYERS);
		this.setGlobalAverageTemperature(0.0);

	}

	/**
	 * @return the gameOver
	 */
	public boolean isGameOver() {
		return gameOver;
	}

	/**
	 * @param gameOver the gameOver to set
	 */
	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	/**
	 * @return the minPlayers
	 */
	public int getMinPlayers() {
		return minPlayers;
	}

	/**
	 * @param minPlayers the minPlayers to set
	 */
	public void setMinPlayers(int minPlayers) throws IllegalArgumentException {
		if (isPlayerAmountCorrect(minPlayers)) {
			this.minPlayers = minPlayers;
		} else {
			throw new IllegalArgumentException("Invalid Min Players");
		}
	}

	/**
	 * @return the maxPlayers
	 */
	public int getMaxPlayers() {
		return maxPlayers;
	}

	/**
	 * @param maxPlayers the maxPlayers to set
	 */
	public void setMaxPlayers(int maxPlayers) throws IllegalArgumentException {
		if (isPlayerAmountCorrect(maxPlayers)) {
			this.maxPlayers = maxPlayers;
		} else {
			throw new IllegalArgumentException("Invalid Max Players");
		}
	}

	/**
	 * 
	 * @return the globalAverageTempature
	 */
	public double getGlobalAverageTemperature() {
		DecimalFormat df = new DecimalFormat("#.#");
		return Double.valueOf(df.format(globalAverageTemperature));
	}

	/**
	 * 
	 * @param globalAverageTemperature the globalAverageTemperature to set
	 */
	public void setGlobalAverageTemperature(double globalAverageTemperature) {
		this.globalAverageTemperature = globalAverageTemperature;

		if (this.globalAverageTemperature < 0.0) {
			this.globalAverageTemperature = 0.0;
		}

		if (this.globalAverageTemperature > TEMPERATURE_LOSS_CONDITION) {
			this.globalAverageTemperature = TEMPERATURE_LOSS_CONDITION;
		}
	}

	/**
	 * This method will start the Game. It will first set the player amount and the
	 * generate the players. The players will then take turns around the board until
	 * the game is over.
	 */
	public void playGame() {
		int playerAmount = setupGame();
		registerPlayers(playerAmount);
		board = new Board();
		takeTurn();
	}

	/**
	 * Start the game, display information to console and check the registered
	 * player count.
	 * 
	 * @return playerAmount
	 */
	public int setupGame() {
		System.out.println("Welcome to Save Our Planet the game!");
		System.out.println();
		System.out.println(
				"Without major action to reduce greenhouse gas emissions, researchers predict that the global average temperature is on track to rise by 2.5-4.5\u00B0C by the year 2100.");
		System.out.println(
				"Once the 1.5\u00B0C threshold is reached there will be increasing heat waves, longer warm seasons and shorter cold seasons.");
		System.out.println(
				"Once the 2\u00B0C threshold is crossed, critical tolerance levels for agriculture and health will be reached.");
		System.out.println();
		System.out.println("The objective of the game:");
		System.out.println("The game hass a Global Average Temperature which will increase by " + TEMPERATURE_INCREASE
				+ "\u00B0C once all players have passed the starting area.");
		System.out.println(
				"Once the Global Average Temperature has reached " + TEMPERATURE_LOSS_CONDITION + "\u00B0C its game over.");
		System.out.println(
				"To reduce the Global Average Temperature, a player can implement and improve sustainable developments once they own all areas within a field.");
		System.out.println();
		System.out.println(
				"Work together with your fellow players to buy and develop areas which will make the world a more healthy and sustainable place!");
		System.out.printf("****************************************** %n %n");
		System.out.println("How many players will be playing the game? (Player amount must be " + MIN_PLAYERS + " - "
				+ MAX_PLAYERS + " inclusive).");

		int playerAmount;

		while (true) {
			try {
				playerAmount = scanner.nextInt();

				while (!isPlayerAmountCorrect(playerAmount)) {
					playerAmount = scanner.nextInt();
				}
				;

				scanner.nextLine();
				break;

			} catch (InputMismatchException e) {
				System.out.println("Please enter a valid player amount between " + MIN_PLAYERS + " - " + MAX_PLAYERS
						+ " (inclusive).");
				scanner.nextLine();
			}
		}
		return playerAmount;
	};

	/**
	 * This method is used to register the players after the player amount has been
	 * set.
	 * 
	 * @param playerAmount
	 */
	public void registerPlayers(int playerAmount) {
		System.out.printf("OK! This game will be played with %d players! %n", playerAmount);

		for (int i = 0; i < playerAmount; i++) {
			Player player = new Player();

			while (true) {

				try {
					System.out.printf("%nPlayer " + (i + 1) + ". What is your name? %n");
					String playerName = scanner.nextLine();
					player.setName(playerName);

					if (playerList.contains(player)) {
						System.out.printf("Duplicate name detected! %s has already been registered. %n",
								player.getName());
					} else {
						playerList.add(player);
						break;
					}

				} catch (IllegalArgumentException e) {
					System.out.println(e.getMessage());
				}

			}
		}
	}

	/**
	 * This method allows players to take their turn until the game is over.
	 * 
	 */
	private void takeTurn() {
		System.out.println();
		System.out.printf("The players are ready and the board has been generated! Its time to save our planet! %n %n");
		System.out.println("All players have started with " + playerList.get(0).getEcoFunds() + " Eco-Funds!");
		System.out.println("-------------------------------------------------");
		System.out.println();
		int playerIndex = 0;

		while (!this.isGameOver()) {
			if (!playerList.get(playerIndex).hasFunds()) {
				this.setGameOver(true);
			} else {
				String q = playerList.get(playerIndex).getName() + ", would you like to roll the dice? (Y/N)";

				if (isResponseValid(q)) {
					int roll = playerList.get(playerIndex).roll();
					updatePlayerPosition(playerList.get(playerIndex), roll);
					System.out.println("End of " + playerList.get(playerIndex).getName() + "s turn");
					System.out.println();
					System.out.println("Current status of the Board: ");
					board.printStats();
					System.out.println();
					playerIndex++;
				} else {
					quitGame(playerList.get(playerIndex).getName());
				}

				if (globalAverageTemperatureTimer()) {
					System.out.println("The Global Average Temperature Timer is currently "
							+ this.getGlobalAverageTemperature() + "\u00B0C");
					System.out.println();
				}

				checkGameStatus();
				if (playerIndex == playerList.size()) {
					playerIndex = 0;
				}
			}
		}

		endGame();
		scanner.close();
	}

	/**
	 * This method will take the result of the roll and update the players position
	 * on the Board.
	 * 
	 * @param player
	 * @param roll
	 */
	private void updatePlayerPosition(Player player, int roll) {
		int playerMove = roll + player.getPosition();

		if (playerMove >= board.getBoardSpaces()) {
			player.setPosition(playerMove - board.getBoardSpaces());
			increasePlayerFunds(player);
			playerSet.add(player);
		} else {
			player.setPosition(playerMove);
		}

		Area landedArea = board.getBoardAreas().get(player.getPosition());

		System.out.println();
		System.out.println(player.getName() + ", you've landed on area : " + landedArea.getAreaName());

		if (landedArea instanceof FieldArea) {
			landedArea.displayAreaInformation();
			playerChoice(player, (FieldArea) landedArea);
			checkFieldOwner(player);
		} else if (landedArea instanceof StartArea) {
			landedArea.displayAreaInformation();
			checkFieldOwner(player);
		} else {
			landedArea.displayAreaInformation();
		}
	}

	/**
	 * This method allows the player to make a choice in relation to the area they
	 * have landed on after the player position is updated after rolling the dice.
	 * 
	 * @param player
	 * @param area
	 */
	private void playerChoice(Player player, FieldArea area) {
		if (Objects.equals(area.getOwner(), null)) {
			if (player.getOwnedAreas().size() > 0) {
				String text = player.getOwnedAreas().size() == 1 ? "area" : "areas";
				System.out.println(
						player.getName() + " choose wisely! here is a reminder of the " + text + " you already own!");
				System.out.println();
				System.out.printf("%-18s | %-20s %n", "AREA", "FIELD");
				System.out.println("-------------------------------");
				for (FieldArea areaOwned : player.getOwnedAreas()) {
					System.out.printf("%-18s | %-20s %n", areaOwned.getAreaName(), areaOwned.getField());
				}
				System.out.println("-------------------------------");
				System.out.println();
			}

			String q = "Do you want to buy and take charge of this area? (Y/N) the price of this area is : "
					+ area.getAreaCost() + " Eco-Funds!";
			System.out.println("You currently have : " + player.getEcoFunds() + " Eco-Funds to spend!");

			board.checkAreaOwnerInField(player, area.getField());

			if (isResponseValid(q)) {
				buyArea(player, area);
			}
		} else if (area.getOwner().equals(player)) {
			System.out.println("You own this area!");
			System.out.println();
		} else {
			System.out.println("Investment cost of this area: " + area.getInvestCost() + " Eco-Funds!");
			investArea(player, area);
		}
	}

	/**
	 * If the player lands or passes by the StartArea, increase Eco-Funds!
	 * 
	 * @param player
	 */
	private void increasePlayerFunds(Player player) {
		StartArea startArea = ((StartArea) board.getBoardAreas().get(board.getStartingSpace()));

		if (player.getPosition() == board.getStartingSpace()) {
			System.out.println("");
			System.out.println("You landed on the starting square! " + startArea.getPassFunds()
					+ " Eco-Funds have been added to your balance!");
			player.addFunds(startArea.getLandFunds());
		} else {
			System.out.println("");
			System.out.println("You passed the starting square! " + startArea.getPassFunds()
					+ " Eco-Funds have been added to your balance!");
			player.addFunds(startArea.getPassFunds());
		}
	}

	/**
	 * This method allows the player to buy a FieldArea if they have enough
	 * eco-funds.
	 * 
	 * @param player
	 * @param area
	 */
	private void buyArea(Player player, FieldArea area) {
		if (player.checkFunds(area.getAreaCost())) {
			player.addArea(area);
		} else {
			System.out.println();
			System.out.println("Sorry, you don't have enough Eco-Funds to buy this Area!");
			System.out.println();
		}
	}

	/**
	 * This method ensures that if the player lands on a FieldArea owned by another
	 * player, they must pay an investment cost relating to the investment cost of
	 * the FieldArea.
	 * 
	 * @param player
	 * @param area
	 */
	private void investArea(Player player, FieldArea area) {
		if (player.checkFunds(area.getInvestCost())) {
			System.out
					.println("You invest your Eco-Funds to " + area.getOwner().getName() + " to help save the planet!");
			player.investFunds(area);
		} else {
			System.out.println("");
			System.out.println("You dont have enough Eco-Funds! You contribute your remaining Eco-Funds.");
			player.minusFunds(player.getEcoFunds());
			this.setGameOver(true);
		}
	}

	/**
	 * This method loops through the Field enum to check if the player owns a Field
	 * on the Board.
	 * 
	 * @param player
	 */
	private void checkFieldOwner(Player player) {
		for (Field field : Field.values()) {
			if (board.checkFieldOwner(player, field.getField())) {
				if (developAreaCheck(player, board.getFieldAreas())) {
					break;
				}
			}
		}
	}

	/**
	 * If the player owns all Areas within a Field, They are asked if they would
	 * like to upgrade an Area within the Field.
	 * 
	 * @param player
	 * @param fieldAreasOwned
	 * @return true if the player can develop an area
	 * @return false if the player cannot develop an area
	 */
	private boolean developAreaCheck(Player player, List<FieldArea> fieldAreasOwned) {
		String q = "Would you like to develop any of the areas in the field : " + fieldAreasOwned.get(0).getField()
				+ "? (Y/N)";

		if (isResponseValid(q)) {
			int num;

			System.out.printf("Your current Eco-Fund balance : %s %n %n", player.getEcoFunds());

			while (true) {
				System.out.printf("%-7s | %-18s | %-31s | %-18s | %-20s | %-10s %n", "Value", "Area Name",
						"Development Name", "Development Cost", "Development Counter", "Major Development Built?");
				System.out.printf(
						"--------------------------------------------------------------------------------------------------------------------------------------%n");
				for (int i = 0; i < fieldAreasOwned.size(); i++) {
					String devCost = (fieldAreasOwned.get(i).getDevPrice() == 0) ? "FULLY DEVELOPED"
							: String.valueOf(fieldAreasOwned.get(i).getDevPrice());

					System.out.printf("%-7s | %-18s | %-31s | %-18s | %-20s | %-10s %n", (i + 1) + ".",
							fieldAreasOwned.get(i).getAreaName(), fieldAreasOwned.get(i).getDevName(), devCost,
							fieldAreasOwned.get(i).getDevCount(), fieldAreasOwned.get(i).isMajorDevBuilt());
				}
				System.out.printf(
						"--------------------------------------------------------------------------------------------------------------------------------------%n%n");

				System.out.println("Please select an areas number value to be developed.");
				System.out.println("Enter 0 to cancel a development in this field.");

				try {
					num = scanner.nextInt();
					if (!intCheck(num, fieldAreasOwned.size())) {
						System.out.printf("Invalid Choice! Try again! %n %n");
					} else {
						if (num == 0) {
							break;
						} else {
							if (board.getBoardAreas().contains(fieldAreasOwned.get(num - 1))
									&& !fieldAreasOwned.get(num - 1).isMajorDevBuilt()) {
								if (developArea(player, (FieldArea) fieldAreasOwned.get(num - 1))) {
									scanner.nextLine();
									return true;
								}
							} else {
								System.out.println("This area has already been fully developed!");
								System.out.println();
							}
						}
					}
				} catch (InputMismatchException e) {
					System.out.printf("Invalid Choice! Try again! %n %n");
					scanner.nextLine();
				}

			}
			scanner.nextLine();
		}
		;
		return false;
	}

	/**
	 * Check if the int value player enters from console is within range of the
	 * FieldArea size
	 * 
	 * @param num
	 * @param size
	 * @return true if the int value is valid
	 * @return false if the int value is invalid
	 */
	private boolean intCheck(int num, int size) {
		if (num == 0) {
			return true;
		}

		for (int i = 0; i < size; i++) {
			if (num == (i + 1)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * This method develops a FieldArea on the board chosen by the Player who owns
	 * the Field.
	 * 
	 * @param player
	 * @param area
	 * @return true if the player has enough funds and develops an area
	 * @return false if the player does not have enough funds
	 */
	private boolean developArea(Player player, FieldArea area) {
		if (player.checkFunds(area.getMinorDevCost())) {
			area.addDevelopment(player);
			double reduction = area.isMajorDevBuilt() ? 0.3 : 0.1;
			reduceGlobalAverageTemperature(reduction);
			return true;
		} else {
			System.out.println("Sorry, you don't have enough Eco-Funds to develop this area!");
			return false;
		}
	}

	/**
	 * Check if the entered player amount from scanner is correct
	 * 
	 * @param x = playerAmount
	 * @return true if correct player amount
	 */
	private boolean isPlayerAmountCorrect(int x) {
		if (x < MIN_PLAYERS || x > MAX_PLAYERS) {
			System.out.println("Please enter a valid player amount between " + MIN_PLAYERS + " - " + MAX_PLAYERS
					+ " (inclusive).");
			return false;
		}
		return true;
	}

	/**
	 * Method to check if the response from the scanner is valid.
	 * 
	 * @param message
	 * @return true if valid response.
	 */
	private boolean isResponseValid(String message) {
		while (true) {
			System.out.println(message);
			String response = scanner.nextLine();
			response = response.trim().toLowerCase();
			switch (response) {
			case "yes":
			case "y":
				return true;
			case "no":
			case "n":
				return false;
			default:
				System.out.println("Invalid choice! try again");
				continue;
			}
		}
	}

	/**
	 * Method to display if the player chooses to quit the game.
	 */
	private void quitGame(String playerName) {
		String q = "Would you like to quit the game?! The planet needs you! (Y/N)";

		if (isResponseValid(q)) {
			this.setGameOver(true);
			System.out.println("");
			System.out.println(playerName + " has decided to quit the game!");
		}

	}

	/**
	 * Check if a game ending condition has executed.
	 */
	private void checkGameStatus() {
		if (this.getGlobalAverageTemperature() >= TEMPERATURE_LOSS_CONDITION) {
			System.out.println(
					"The Global Average Temperature has reached its limit! You have failed to Save Our Planet!!!");
			this.setGameOver(true);
		}

		if (board.allAreasDeveloped()) {
			System.out.println("CONGRATULATIONS PLAYERS!");
			System.out.println(
					"ALL AREAS HAVE BEEN FULLY DEVELOPED!! THE PLANET IS NOW HEALTHIER AND MORE SUSTAINABLE THAN EVER!!!");
			this.setGameOver(true);
		}

		for (Field field : Field.values()) {
			if (board.checkAreas(field.getField())) {
				System.out.println("Two players own areas within the same field! The game cannot be won!");
				this.setGameOver(true);
			}
		}
	}

	/**
	 * Method to display once the game is over.
	 */
	private void endGame() {
		System.out.println();

		conclusionMessage();

		System.out.printf("Displaying final breakdown of the board! %n %n");
		board.printStats();

		System.out.println("Displaying individual player stats! (Ranked by Total Eco-Funds spent!)");
		System.out.println();
		Collections.sort(playerList, new CompareByFunds());

		for (int i = 0; i < playerList.size(); i++) {
			playerList.get(i).printStats(i + 1);
		}
	}

	/**
	 * The game over message
	 */
	private void conclusionMessage() {
		try {
			System.out.println();
			System.err.print("SAVE ");
			Thread.sleep(500);
			System.err.print("OUR ");
			Thread.sleep(500);
			System.err.print("PLANET ");
			Thread.sleep(500);
			System.err.print("THE ");
			Thread.sleep(500);
			System.err.print("GAME ");
			Thread.sleep(500);
			System.err.print("HAS ");
			Thread.sleep(500);
			System.err.println("CONCLUDED!!");
			System.out.println();
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Players are added to a set once they have passed the starting area (made a
	 * lap around the board) Once the playerSet is the same size as the playerList
	 * then all players have passed the starting area. As all players have passed
	 * the starting area, the global average temperature will increase and the
	 * playerSet will clear allowing the method to be used all once players have
	 * again passed the starting square.
	 * 
	 * @return true if all players have passed the board
	 * @return false if all players have not passed the board
	 */
	private boolean globalAverageTemperatureTimer() {
		if (playerSet.size() == playerList.size()) {
			this.setGlobalAverageTemperature(this.getGlobalAverageTemperature() + TEMPERATURE_INCREASE);
			System.out.println(
					"Time goes on and the players have made significant travels... The Global Average Temperature has increased by "
							+ TEMPERATURE_INCREASE + "\u00B0C");
			if (this.getGlobalAverageTemperature() >= TEMPERATURE_LOSS_CONDITION - 0.2) {
				System.out.println(
						"The limit has nearly been reached the players need to start developing areas to avoid the global average temperature reaching "
								+ TEMPERATURE_LOSS_CONDITION + "\u00B0C");
			}
			playerSet.clear();
			return true;
		}

		return false;
	}

	/**
	 * Method to reduce the global average temperature once a development has been
	 * made
	 * 
	 * @param reduction
	 */
	private void reduceGlobalAverageTemperature(double reduction) {
		this.setGlobalAverageTemperature(this.getGlobalAverageTemperature() - reduction);
		System.out.println(
				"Thanks to your development, the Global Average Temperature has reduced by " + reduction + "\u00B0C");
		System.out.println(
				"The Global Average Temperature Timer is currently " + this.getGlobalAverageTemperature() + "\u00B0C");
		System.out.println();
	}
}
