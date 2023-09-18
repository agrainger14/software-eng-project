package saveOurPlanet;

/**
 * @author Andrew Grainger. The fields on the board are represented as Enums.
 *
 */
public enum Field {
	RENEWABLE_ENERGY("Renewable Energy"), REFORESTATION("Reforestation"), CONSERVATION("Conservation"),
	WASTE_MANAGEMENT("Waste Management");

	// to set fieldName as a String
	private String fieldName;

	/**
	 * Enum Constructor
	 * 
	 * @param fieldName
	 */
	Field(String fieldName) {
		this.fieldName = fieldName;
	}

	/**
	 * @return the enum field as a String.
	 */
	public String getField() {
		return this.fieldName;
	}

}
