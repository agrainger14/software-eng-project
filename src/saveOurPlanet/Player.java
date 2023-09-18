package saveOurPlanet;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Andrew Grainger. This is the Player Class which contains the Players
 *         name, eco-funds, position on the board and a List of FieldAreas that
 *         the player owns..
 *
 */
public class Player {
	// final variables
	private final int STARTING_ECOFUNDS = 300;
	private final int STARTING_POSITION = 0;// start square = 0 index
	private final int MIN_NAME_LENGTH = 3;
	private final int MAX_NAME_LENGTH = 20;

	// instance variables
	private Dice dice;
	private String name;
	private int ecoFunds;
	private int spentEcoFunds;
	private int position;
	private List<FieldArea> ownedAreas;

	/**
	 * Constructor without args
	 */
	public Player() {
		setOwnedAreas(new ArrayList<FieldArea>());
		dice = new Dice();
		this.setEcoFunds(STARTING_ECOFUNDS);
		this.setPosition(STARTING_POSITION);
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) throws IllegalArgumentException {
		if (isNameValid(name)) {
			this.name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
		} else {
			throw new IllegalArgumentException(String.format(
					"Please enter a valid name, the name must be between %d to %d characters long and must contain characters.",
					MIN_NAME_LENGTH, MAX_NAME_LENGTH));
		}
	}

	/**
	 * @return the ecoFunds
	 */
	public int getEcoFunds() {
		return ecoFunds;
	}

	/**
	 * @param ecoFunds the ecoFunds to set
	 */
	public void setEcoFunds(int ecoFunds) {
		if (ecoFunds < 0) {
			throw new IllegalArgumentException("Eco-Funds amount can't be minus!");
		} else {
			this.ecoFunds = ecoFunds;
		}
	}

	/**
	 * @return the position
	 */
	public int getPosition() {
		return position;
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition(int position) {
		this.position = position;
	}

	/**
	 * This method uses the Dice class for the Player to roll the dice.
	 * 
	 * @return the total roll value.
	 */
	public int roll() {
		int result = dice.roll();
		return result;
	}

	/**
	 * 
	 * @return the Player ownedAreas
	 */
	public List<FieldArea> getOwnedAreas() {
		return ownedAreas;
	}

	/**
	 * 
	 * @param ownedAreas the OwnedAreas to set
	 */
	public void setOwnedAreas(List<FieldArea> ownedAreas) {
		this.ownedAreas = ownedAreas;
	};

	/**
	 * Set the Player as Owner of a FieldArea
	 * 
	 * @param area
	 */
	public void addArea(FieldArea area) {
		area.setOwner(this);
		ownedAreas.add(area);
		System.out.println();
		System.out.println("******************************************");
		System.out.println("Alright " + this.getName() + " you have enough Eco-Funds!");
		this.minusFunds(area.getAreaCost());
		System.out.println(this.getName() + " is now in charge of area : " + area.getAreaName());
		System.out.printf("****************************************** %n %n");
	}

	public void investFunds(FieldArea area) {
		System.out.println();
		System.out.println("******************************************");
		this.minusFunds(area.getInvestCost());
		System.out.printf("****************************************** %n %n");
		System.out.println("******************************************");
		System.out.println(area.getOwner().getName() + " your Eco-Funds have increased after receiving "
				+ this.getName() + "'s investment!");
		area.getOwner().addFunds(area.getInvestCost());
		System.out.printf("****************************************** %n %n");
	}

	/**
	 * Check if the player is a FieldArea Owner
	 * 
	 * @param area
	 * @return
	 */
	public boolean isAreaOwner(FieldArea area) {
		return area.isPlayerOwner(this) ? true : false;
	}

	/**
	 * Checks if the player has enough funds
	 * 
	 * @param cost
	 * @return true if the player has enough funds greater than the cost
	 */
	public boolean checkFunds(int cost) {
		return this.getEcoFunds() > cost;
	}

	/**
	 * Checks if the player funds are greater than 0
	 * 
	 * @return true if the player funds are greater than 0
	 */
	public boolean hasFunds() {
		return this.getEcoFunds() > 0;
	}

	/**
	 * Method to minus funds from the players funds.
	 * 
	 * @param cost
	 */
	public void minusFunds(int cost) {
		System.out.println(this.getName() + "'s old balance was : " + this.getEcoFunds() + " Eco-Funds.");
		this.setEcoFunds(this.getEcoFunds() - cost);
		this.setSpentEcoFunds(this.getSpentEcoFunds() + cost);
		System.out.println(this.getName() + "'s new balance is : " + this.getEcoFunds() + " Eco-Funds.");
	}

	/**
	 * Method to add funds to the players funds.
	 * 
	 * @param cost
	 */
	public void addFunds(int cost) {
		System.out.println(this.getName() + "'s old balance was : " + this.getEcoFunds() + " Eco-Funds.");
		this.setEcoFunds(this.getEcoFunds() + cost);
		System.out.println(this.getName() + "'s new balance is : " + this.getEcoFunds() + " Eco-Funds.");
	}

	/**
	 * 
	 * @return the spentEcoFunds
	 */
	public int getSpentEcoFunds() {
		return spentEcoFunds;
	}

	/**
	 * 
	 * @param spentEcoFunds the spentEcoFunds to set
	 */
	public void setSpentEcoFunds(int spentEcoFunds) {
		this.spentEcoFunds = spentEcoFunds;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + MAX_NAME_LENGTH;
		result = prime * result + MIN_NAME_LENGTH;
		result = prime * result + STARTING_ECOFUNDS;
		result = prime * result + STARTING_POSITION;
		result = prime * result + ecoFunds;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + position;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Player other = (Player) obj;
		if (MAX_NAME_LENGTH != other.MAX_NAME_LENGTH) {
			return false;
		}
		if (MIN_NAME_LENGTH != other.MIN_NAME_LENGTH) {
			return false;
		}
		if (STARTING_ECOFUNDS != other.STARTING_ECOFUNDS) {
			return false;
		}
		if (STARTING_POSITION != other.STARTING_POSITION) {
			return false;
		}
		if (ecoFunds != other.ecoFunds) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (position != other.position) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return String.format(
				"Player [STARTING_ECOFUNDS=%s, STARTING_POSITION=%s, MIN_NAME_LENGTH=%s, MAX_NAME_LENGTH=%s, name=%s, ecoFunds=%s, position=%s]",
				STARTING_ECOFUNDS, STARTING_POSITION, MIN_NAME_LENGTH, MAX_NAME_LENGTH, name, ecoFunds, position);
	}

	/**
	 * Method to print the players stats
	 * 
	 * @param rank
	 */
	public void printStats(int rank) {

		System.out.println(rank + ". " + this.getName().toUpperCase() + ": ");
		System.out.println("--------------------");
		System.out.println();
		System.out.println("Areas owned: ");
		System.out.println("-------------------------------------");
		System.out.printf("%-20s | %-25s %n", "Area Name", "Developments Made");
		System.out.println("-------------------------------------");
		if (this.getOwnedAreas().size() == 0) {
			System.out.println("None!");
		} else {
			for (FieldArea area : this.getOwnedAreas()) {
				String devCount = (area.getDevPrice() == 0) ? "FULLY DEVELOPED" : String.valueOf(area.getDevCount());
				System.out.printf("%-20s | %-25s %n", area.getAreaName(), devCount);

			}
		}
		System.out.println();
		System.out.println("-------------------------------------");
		System.out.println("Total Eco-Funds spent to Save-The-Planet! (Area Purchases, Investments and Developments): "
				+ this.getSpentEcoFunds());
		System.out.println("-------------------------------------");
		System.out.println("-------------------------------------");
		System.out.println("Remaining Eco-Funds : " + this.getEcoFunds());
		System.out.println("-------------------------------------");
		System.out.println();
	}

	/**
	 * This method checks if the player name is valid.
	 * 
	 * @param name
	 * @return true if the player name contains characters, is within the name
	 *         length and is not blank.
	 */
	private boolean isNameValid(String name) {
		if (name == null) {
			return false;
		}

		for (char c : name.toCharArray()) {
			if (Character.isDigit(c) || Character.isSpaceChar(c)) {
				return false;
			}
		}

		if (name.trim().length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH || name.isBlank()) {
			return false;
		}

		return true;
	}

}
