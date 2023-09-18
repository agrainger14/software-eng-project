package saveOurPlanet;

/**
 * 
 * @author Andrew Grainger This is the StartArea class. if the player passes the
 *         StartArea they will receive Eco-Funds, if the player lands on the
 *         StartArea they will receive extra Eco-Funds.
 *
 */
public class StartArea extends Area {
	//private variables
	private final int LAND_FUNDS = 300;
	private final int PASS_FUNDS = 200;

	//instance variables
	private int landFunds;
	private int passFunds;

	/**
	 * Constructor with args
	 * @param areaName
	 */
	public StartArea(String areaName) {
		super(areaName);
		this.setLandFunds(LAND_FUNDS);
		this.setPassFunds(PASS_FUNDS);
	}

	/**
	 * @return passFunds
	 */
	public int getPassFunds() {
		return passFunds;
	}

	/**
	 * 
	 * @param passFunds the passFunds to set
	 */
	public void setPassFunds(int passFunds) {
		this.passFunds = passFunds;
	}

	/**
	 * 
	 * @return landFunds
	 */
	public int getLandFunds() {
		return landFunds;
	}

	/**
	 * 
	 * @param landFunds the landFunds to set
	 */
	public void setLandFunds(int landFunds) {
		this.landFunds = landFunds;
	}

	@Override
	public void displayAreaInformation() {
		System.out.println();
		System.out.println("******************************************");
		System.out.println(
				"Your travels around the board has inspired others to help save the planet! You've gained extra eco-funds!");
		System.out.println("******************************************");
		System.out.println();
	}

	@Override
	public String toString() {
		return String.format("StartArea [getareaName()=%s]", getAreaName());
	}

	@Override
	public void displayStats(int position) {
		System.out.printf("%-10s | %-18s | %-20s | %-31s | %-20s | %-32s | %-20s | %-10s %n", position, this.getAreaName(), " ", " ", " ", " ", " ", " ");
	}

}
