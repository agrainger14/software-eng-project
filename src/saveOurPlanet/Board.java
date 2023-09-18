package saveOurPlanet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Andrew Grainger This is the board class. The board class contains an
 *         ArrayList which generates the board with areas using the FileReader
 *         and a passed CSV file. Additional variables are contained to set the
 *         number of board spaces, the index of the starting space and the index
 *         of the do nothing space within the List.
 */
public class Board {
	// final values relating to the csv file name, total board spaces and the index
	// of the starting and do nothing space.
	private final String CSV_NAME = "SaveOurPlanetArea.csv";
	private final int BOARD_SPACES = 12; // 12 spaces in total.
	private final int STARTING_SPACE = 0; // 0 index, 0 = square 1.
	private final int DO_NOTHING_SPACE = 6; // 6 = do nothing.

	// instance variables for the Board object.
	private List<Area> boardAreas;
	private List<FieldArea> fieldAreas;
	private int boardSpaces;
	private int startingSpace;

	/**
	 * Constructor with no arguments.
	 */
	public Board() {
		boardAreas = new ArrayList<Area>();
		this.setBoardSpaces(BOARD_SPACES);
		this.setStartingSpace(STARTING_SPACE);
		this.generateBoard();
	}

	/**
	 * @return the StartingSpace
	 */
	public int getStartingSpace() {
		return startingSpace;
	}

	/**
	 * @param startingSpace the startingSpace to set
	 */
	public void setStartingSpace(int startingSpace) {
		this.startingSpace = startingSpace;
	}

	/**
	 * @return the boardSpaces
	 */
	public int getBoardSpaces() {
		return boardSpaces;
	}

	/**
	 * @param boardSpaces the boardSpaces to set
	 */
	public void setBoardSpaces(int boardSpaces) {
		this.boardSpaces = boardSpaces;
	}

	/**
	 * @return the boardAreas
	 */
	public List<Area> getBoardAreas() {
		return boardAreas;
	}

	/**
	 * This method uses the file reader to generate the board. Areas are generated
	 * based on the input from the CSV file and then added to the boardAreas
	 * ArrayList.
	 */
	private void generateBoard() {
		File file = new File(CSV_NAME);
		int counter = this.getStartingSpace();

		try (FileReader fr = new FileReader(file); BufferedReader reader = new BufferedReader(fr)) {

			String line = reader.readLine(); // discard header
			line = reader.readLine(); // read first line

			while (line != null && counter < this.getBoardSpaces()) {
				String[] parts = line.split(",");

				Area boardArea;

				if (counter == STARTING_SPACE) {
					boardArea = new StartArea(parts[0]);
				} else if (counter == DO_NOTHING_SPACE) {
					boardArea = new DoNothingArea(parts[0]);
				} else {
					boardArea = new FieldArea(parts[0], Field.valueOf(parts[1]), Integer.parseInt(parts[2]),
							Integer.parseInt(parts[3]), Integer.parseInt(parts[4]), Integer.parseInt(parts[5]),
							parts[6]);
				}

				boardAreas.add(boardArea);
				counter++;
				line = reader.readLine();
			}

		} catch (FileNotFoundException e) {
			System.out.println("File not found error");
		} catch (IOException e) {
			System.out.println("IO Exception");
		} catch (Exception e) {
			System.out.println("Exception occured - Check CSV file details are correct.");
			System.out.println(e);
			System.exit(1);
		}
	}

	@Override
	public String toString() {
		return String.format(
				"Board [csvName=%s, BOARD_SPACES=%s, STARTING_SPACE=%s, DO_NOTHING_SPACE=%s, getClass()=%s, hashCode()=%s, toString()=%s]",
				CSV_NAME, BOARD_SPACES, STARTING_SPACE, DO_NOTHING_SPACE, getClass(), hashCode(), super.toString());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + BOARD_SPACES;
		result = prime * result + DO_NOTHING_SPACE;
		result = prime * result + STARTING_SPACE;
		result = prime * result + ((boardAreas == null) ? 0 : boardAreas.hashCode());
		result = prime * result + boardSpaces;
		result = prime * result + ((CSV_NAME == null) ? 0 : CSV_NAME.hashCode());
		result = prime * result + startingSpace;
		return result;
	}

	/**
	 * This method will pass in the player object and enum String field in order to
	 * determine if the player owns the relevant areas within that field.
	 * 
	 * @param player
	 * @param field
	 * @return false if the player owns no areas within the passed field.
	 * @return true if the player owns all areas within the passed field.
	 */
	public boolean checkFieldOwner(Player player, String field) {
		setFieldAreas(field);

		for (FieldArea fieldArea : fieldAreas) {
			if (player != null && !fieldArea.isPlayerOwner(player)) {
				return false;
			}
		}

		for (FieldArea fieldArea : fieldAreas) {
			if (!fieldArea.isMajorDevBuilt()) {
				System.out.println(player.getName() + " you own this field : " + field + "!");
				return true;
			}
		}

		System.out.println("All areas within the field : " + field + " have been fully developed!");
		return false;

	}

	/**
	 * This method will pass in the player object and enum String field in order to
	 * determine if another player owns a relevant area within that field.
	 * 
	 * @param player
	 * @param field
	 * @return false if another player owns no areas within the passed field.
	 * @return true if another player owns an area within the passed field.
	 */
	public boolean checkAreaOwnerInField(Player player, String field) {
		for (Area area : boardAreas) {
			if (area instanceof FieldArea && ((FieldArea) area).getField().equals(field)) {
				FieldArea fieldArea = (FieldArea) area;
				if (fieldArea.getOwner() != null && !fieldArea.getOwner().equals(player)) {
					System.out.println(fieldArea.getOwner().getName() + " owns the " + fieldArea.getAreaName()
							+ " area on this field! if you purchase it you will be unable to Save the Planet!");
					return true;
				}
			}
		}
		return false;

	}

	/**
	 * This method will pass in the enum String field to populate the fieldAreas
	 * ArrayList with relevant Field Areas. This method will be used to help check
	 * if the player is a field owner.
	 * 
	 * @param field
	 * @return the setFieldAreas
	 */
	public void setFieldAreas(String field) {
		fieldAreas = new ArrayList<FieldArea>();

		for (Area area : boardAreas) {
			if (area instanceof FieldArea) {
				if (((FieldArea) area).getField().equals(field)) {
					fieldAreas.add((FieldArea) area);
				}
			}
		}

	}

	/**
	 * This method will return the Field Areas owned by a Player.
	 * 
	 * @return fieldAreas
	 */
	public List<FieldArea> getFieldAreas() {
		return fieldAreas;
	}

	public void printStats() {
		System.out.printf("%-10s | %-18s | %-20s | %-31s | %-20s | %-32s | %-20s | %-10s %n", "Square No.", "Area Name",
				"Field Name", "Area Owner", "Invest Cost", "Development Name", "Development Cost",
				"Major Development Built?");
		System.out.println(
				"----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

		for (int i = 0; i < boardAreas.size(); i++) {
			boardAreas.get(i).displayStats(i + 1);
		}

		System.out.println();
	}

	/**
	 * Method to check if all areas have been fully developed
	 * 
	 * @return true if all areas have been fully developed
	 * @return false if all areas have not been fully developed
	 */
	public boolean allAreasDeveloped() {
		for (Area area : boardAreas) {
			if (area instanceof FieldArea && !((FieldArea) area).isMajorDevBuilt()) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Check if 2 players own an area within the field
	 * 
	 * @param field
	 * @return true if 2 players own an area within the field
	 * @return false if 2 players do not own an area within the field
	 */
	public boolean checkAreas(String field) {
		Set<Player> playerSet = new HashSet<>();

		for (Area area : boardAreas) {
			if (area instanceof FieldArea && ((FieldArea) area).getField().equals(field)) {
				FieldArea fieldArea = (FieldArea) area;
				if (fieldArea.getOwner() != null) {
					playerSet.add(fieldArea.getOwner());
				}
			}
		}

		if (playerSet.size() == 2) {
			return true;
		}
		return false;
	}

}
