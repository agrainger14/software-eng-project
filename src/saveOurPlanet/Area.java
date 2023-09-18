package saveOurPlanet;

/**
 * @author Andrew Grainger This is the Area Class (Abstract) which contains the
 *         areaName. This class will be extended to create the relevant Area
 *         (StartingArea, FieldArea or DoNothingArea)
 */
public abstract class Area {
	private String areaName;

	/**
	 * Constructor with areaName to be passed.
	 * 
	 * @param areaName
	 */
	public Area(String areaName) {
		this.areaName = areaName;
	}

	/**
	 * @return the name
	 */
	public String getAreaName() {
		return areaName;
	}

	/**
	 * @param name the name to set
	 */
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	/**
	 * Method to display information relating to the area.
	 */
	public abstract void displayAreaInformation();

	/**
	 * Method to display the area stats.
	 * @param position
	 */
	public abstract void displayStats(int position);
}
