package gotBrains;

import java.util.Comparator;

public class HighscoreComparator implements Comparator<Player> {
	
	public int compare(Player player1, Player player2) {

		int sc1 = player1.getMathGameScore();
		int sc2 = player2.getMathGameScore();
		if (sc1 > sc2) {
			return -1;
		} else if (sc1 < sc2) {
			return +1;
		} else {
			return 0;
		}
	}

}