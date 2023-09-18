package saveOurPlanet;

import java.util.Objects;

/**
 * @author Andrew Grainger. This is the FieldArea class which extends the Area
 *         class. The Field Area class contains the Field which the Area is in,
 *         Area Name, Area/Investment/Minor Development/Major Development Cost
 *         and Owner of the Field.
 *
 */
public class FieldArea extends Area {
	// final variables
	private final int INCREASE_PRICE = 25;

	// instance variables
	private String field;
	private int areaCost;
	private int investCost;
	private Player owner;
	private int devCount;
	private int minorDevCost;
	private int majorDevCost;
	private boolean isMajorDevBuilt;
	private String devName;

	/**
	 * Constructor with args
	 * 
	 * @param areaName
	 * @param field
	 * @param areaCost
	 * @param investCost
	 * @param minorDevCost
	 * @param majorDevCost
	 */
	public FieldArea(String areaName, Field field, int areaCost, int investCost, int minorDevCost, int majorDevCost,
			String devName) {
		super(areaName);
		this.field = field.getField();
		this.areaCost = areaCost;
		this.investCost = investCost;
		this.owner = null;
		this.minorDevCost = minorDevCost;
		this.majorDevCost = majorDevCost;
		this.devName = devName;
		this.isMajorDevBuilt = false;
	}

	/**
	 * @return the field
	 */
	public String getField() {
		return field;
	}

	/**
	 * @param field the field to set
	 */
	public void setField(String field) {
		this.field = field;
	}

	/**
	 * @return the areaCost
	 */
	public int getAreaCost() {
		return areaCost;
	}

	/**
	 * @param areaCost the areaCost to set
	 */
	public void setAreaCost(int areaCost) {
		this.areaCost = areaCost;
	}

	/**
	 * @return the investCost
	 */
	public int getInvestCost() {
		return investCost;
	}

	/**
	 * @param investCost the investCost to set
	 */
	public void setInvestCost(int investCost) {
		this.investCost = investCost;
	}

	/**
	 * @return the owner
	 */
	public Player getOwner() {
		return owner;
	}

	/**
	 * @param owner the owner to set
	 */
	public void setOwner(Player owner) {
		this.owner = owner;
	}

	/**
	 * @return the minorDevelopmentCost
	 */
	public int getMinorDevCost() {
		return minorDevCost;
	}

	/**
	 * @param minorDevelopmentCost the minorDevelopmentCost to set
	 */
	public void setMinorDevCost(int minorDevCost) {
		this.minorDevCost = minorDevCost;
	}

	/**
	 * @return the majorDevelopmentCost
	 */
	public int getMajorDevCost() {
		return majorDevCost;
	}

	/**
	 * @param majorDevelopmentCost the majorDevelopmentCost to set
	 */
	public void setMajorDevelopmentCost(int majorDevCost) {
		this.majorDevCost = majorDevCost;
	}

	/**
	 * @return the total development count
	 */
	public int getDevCount() {
		return devCount;
	}

	/**
	 * @param devCount the devCount to set
	 */
	public void setDevCount(int devCount) {
		this.devCount = devCount;
	}

	/**
	 * @param majorDevBuilt set to true if Major Development is built on FieldArea
	 */
	public void setMajorDevBuilt(boolean isMajorDevBuilt) {
		this.isMajorDevBuilt = isMajorDevBuilt;
	}

	/**
	 * @return the development name
	 */
	public String getDevName() {
		return devName;
	}

	/**
	 * @param devName the development name to set
	 */
	public void setDevName(String devName) {
		this.devName = devName;
	}

	@Override
	public String toString() {
		return String.format(
				"FieldArea [INCREASE_PRICE=%s, areaName=%s, field=%s, areaCost=%s, investCost=%s, owner=%s, devCount=%s, minorDevCost=%s, majorDevCost=%s, isMajorDevBuilt=%s, devName=%s]",
				INCREASE_PRICE, this.getAreaName(), field, areaCost, investCost, owner, devCount, minorDevCost,
				majorDevCost, isMajorDevBuilt, devName);
	}

	@Override
	public void displayAreaInformation() {
		String owner = Objects.equals(this.getOwner(), null) ? "No one!" : this.getOwner().getName();

		System.out.println("This area is in the field : " + this.getField() + ". The owner of this area : " + owner);
		System.out.println();
	}

	/**
	 * Check if the player is the owner of the FieldArea
	 * 
	 * @param player
	 * @return true if player is owner
	 * @return false if player is not owner
	 */
	public boolean isPlayerOwner(Player player) {
		return this.owner != null && this.owner.equals(player);
	}

	/**
	 * Check if a major development is built
	 * 
	 * @return true if major development built
	 * @return false if major development is not built
	 */
	public boolean isMajorDevBuilt() {
		return isMajorDevBuilt;
	}

	/**
	 * Method checks the development count of a FieldArea, if greater than or equal
	 * 3 the player must add a major development.
	 * 
	 * @param player
	 */
	public void addDevelopment(Player player) {
		if (this.getDevCount() >= 3) {
			this.addMajorDevelopment(player);
		} else {
			this.addNormalDevelopment(player);
		}
	}

	/**
	 * Method to add a normal development
	 * 
	 * @param player
	 */
	private void addNormalDevelopment(Player player) {
		if (this.getDevCount() == 0) {
			System.out.println();
			System.out.println("******************************************");
			System.out.println(player.getName() + " invests eco-funds into building the development : "
					+ this.getDevName() + " in the area : " + this.getAreaName() + "!");
		} else {
			System.out.println();
			System.out.println("******************************************");
			System.out.println(player.getName() + " invests eco-funds into upgrading the development : "
					+ this.getDevName() + " in the area : " + this.getAreaName() + "!");
		}
		this.setDevCount(this.getDevCount() + 1);
		if (this.getDevCount() == 3) {
			System.out.println(
					"The third development has been built! The next development for this Area will be a Major Development!");
		}
		player.minusFunds(this.getMinorDevCost());
		System.out.printf("****************************************** %n %n");
		this.increaseAreaPrice();
	}

	/**
	 * Method to add a major development.
	 * 
	 * @param player
	 */
	private void addMajorDevelopment(Player player) {
		System.out.println();
		System.out.println("******************************************");
		System.out.println(player.getName()
				+ " invests eco-funds into adding a MAJOR development and fully upgrading the development of this area!");
		this.setMajorDevBuilt(true);
		System.out.println(this.getDevName() + " HAS BEEN FULLY DEVELOPED!");
		player.minusFunds(this.getMajorDevCost());
		System.out.printf("****************************************** %n %n");
	}

	/**
	 * This method is used to increase the development/investment cost of the
	 * FieldArea after it has been developed
	 */
	public void increaseAreaPrice() {
		this.setMinorDevCost(this.getMinorDevCost() + INCREASE_PRICE);
		this.setInvestCost(this.getInvestCost() + INCREASE_PRICE);
	}

	/**
	 * Method to return the development price depending on the development count.
	 * 
	 * @return
	 */
	public int getDevPrice() {
		return this.isMajorDevBuilt() ? 0 : this.getDevCount() >= 3 ? this.getMajorDevCost() : this.getMinorDevCost();
	}

	@Override
	public void displayStats(int position) {
		String devCost = (this.getDevPrice() == 0) ? "FULLY DEVELOPED" : String.valueOf(this.getDevPrice());
		System.out.printf("%-10s | %-18s | %-20s | %-31s | %-20s | %-32s | %-20s | %-10s %n", position,
				this.getAreaName(), this.getField(), (this.getOwner() != null ? this.getOwner().getName() : "No-one!"),
				this.getInvestCost(), this.getDevName(), devCost, this.isMajorDevBuilt());
	}

}
