package saveOurPlanet;

/**
 * @author Andrew Grainger The DoNothingArea, if a player lands on this Area
 *         their turn is automatically skipped.
 */
public class DoNothingArea extends Area {

	/**
	 * Constructor with args
	 * @param areaName
	 */
	public DoNothingArea(String areaName) {
		super(areaName);
	}

	@Override
	public void displayAreaInformation() {
		System.out.println();
		System.out.println("******************************************");
		System.out.println(
				"You are exhausted from trying to invest in saving the planet! You decide to take a break and reflect on the hard work done.");
		System.out.println("******************************************");
		System.out.println();
	}

	@Override
	public String toString() {
		return String.format("DoNothingArea [getareaName()=%s]", getAreaName());
	}

	@Override
	public void displayStats(int position) {
		System.out.printf("%-10s | %-18s | %-20s | %-31s | %-20s | %-32s | %-20s | %-10s %n", position, this.getAreaName(), " ", " ", " ", " ", " ", " ");
	}
}
