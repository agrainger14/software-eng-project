package saveOurPlanet;

import java.util.Comparator;

public class CompareByFunds implements Comparator<Player> {

	@Override
	public int compare(Player o1, Player o2) {
		return o2.getSpentEcoFunds() - o1.getSpentEcoFunds();
	}

}
