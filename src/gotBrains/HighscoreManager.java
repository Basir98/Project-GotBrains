package gotBrains;

import java.util.*;
import java.io.*;

/**
 * The class that handles the logic of taking game scores and writing them in the "scores"-dat file.
 * 
 * @author Isak Hartman, Felix Jönsson
 *
 */
public class HighscoreManager {
	private ArrayList<Player> players;
	private static final String HIGHSCORE_FILE = "files/scores.dat";
	ObjectOutputStream outputStream = null;
	ObjectInputStream inputStream = null;

	public HighscoreManager() {
		players = new ArrayList<Player>();
	}

	/*
	 * A method that collects Player-objects and sorts them.
	 */
	public ArrayList<Player> getScores() {
		loadScoreFile();
		sort();
		return players;
	}

	/*
	 * Sorts the Player-objects.
	 */
	private void sort() {
		HighscoreComparator comparator = new HighscoreComparator();
		Collections.sort(players, comparator);
	}

	/*
	 * Adds a new Player-object to the list if the username used is new.
	 */
	public void addPlayer(String username) {
		loadScoreFile();
		if (!usernameTaken(players, username)) {
			players.add(new Player(username));
			updateScoreFile();
		}
	}

	/*
	 * If the username used has been used before this method returns the boolean "true".
	 */
	public boolean usernameTaken(ArrayList<Player> players, String username) {
		for (Player object : players) {
			if (object.getUsername().equals(username)) {
				return true;
			}
		}
		return false;
	}

	/*
	 * Compares the username used with the usernames in the current list. 
	 * If the username used is a previously used one the score is compared in a method in another class and then set to be updated here.
	 */
	public void addCalculateThisScore(String username, int score) {
		loadScoreFile();
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).getUsername().equals(username)) {
				players.get(i).setMathGameScore(score);
			}
		}
		updateScoreFile();
	}

	/*
	 * Compares the username used with the usernames in the current list. 
	 * If the username used is a previously used one the score is compared in a method in another class and then set to be updated here.
	 */
	public void addSpellThisScore(String username, int score) {
		loadScoreFile();
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).getUsername().equals(username)) {
				players.get(i).setScrabbleScore(score);
			}
		}
		updateScoreFile();
	}

	/*
	 * Compares the username used with the usernames in the current list. 
	 * If the username used is a previously used one the score is compared in a method in another class and then set to be updated here.
	 */
	public void addMemorizeThisScore(String username, int score) {
		loadScoreFile();
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).getUsername().equals(username)) {
				players.get(i).setSimonSaysScore(score);
			}
		}
		updateScoreFile();
	}

	/*
	 * A method that clears the "scores"-dat file of it's current usernames.
	 */
	public void clearScores() {
		loadScoreFile();
		players.clear();
		updateScoreFile();
	}

	/*
	 * Loads the Player-objects from the file and stores them in the players-list.
	 */
	public void loadScoreFile() {
		try {
			inputStream = new ObjectInputStream(new FileInputStream(HIGHSCORE_FILE));
			players = (ArrayList<Player>) inputStream.readObject();
		} catch (FileNotFoundException e) {
			System.out.println("FNF Error: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("IO Error: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println("CNF Error: " + e.getMessage());
		} finally {
			try {
				if (outputStream != null) {
					outputStream.flush();
					outputStream.close();
				}
			} catch (IOException e) {
				System.out.println("IO Error: " + e.getMessage());
			}
		}
	}

	/* 
	 * Writes the current player-list to the dat-file.
	 */
	public void updateScoreFile() {
		try {
			outputStream = new ObjectOutputStream(new FileOutputStream(HIGHSCORE_FILE));
			outputStream.writeObject(players);
		} catch (FileNotFoundException e) {
			System.out.println("FNF Error: " + e.getMessage() + ", the program will try and make a new file.");
		} catch (IOException e) {
			System.out.println("IO Error: " + e.getMessage());
		} finally {
			try {
				if (outputStream != null) {
					outputStream.flush();
					outputStream.close();
				}
			} catch (IOException e) {
				System.out.println("IO Error: " + e.getMessage());
			}
		}
	}

	/*
	 * A method that sorts the Player-objects based on their total scores and gives them a placement on the leaderboard.
	 * Only gives a placement to a maximum of 10 different players.
	 */
	public String getLeaderboardPlacement() {
		String leaderboardPlacement = "";
		int max = 10;

		ArrayList<Player> players;
		players = getScores();

		int i = 0;
		int x = players.size();
		if (x > max) {
			x = max;
		}
		while (i < x) {
			leaderboardPlacement += String.format("%4d.      %-14s\n", (i + 1), players.get(i).getUsername());
			i++;
		}
		return leaderboardPlacement;
	}

	/*
	 * A method that sorts the scores and puts them in the order of the highest to lowest.
	 */
	public String getLeaderboardScore() {
		String leaderboardScore = "";
		int max = 10;

		ArrayList<Player> players;
		players = getScores();

		int i = 0;
		int x = players.size();
		if (x > max) {
			x = max;
		}
		while (i < x) {
			leaderboardScore += String.format("%-4d \n", players.get(i).getTotalScore());
			i++;
		}
		return leaderboardScore;
	}

	/**
	 * An inner class that compares the scores of players in the player-list.
	 * @author Isak Hartman, Felix Jönsson
	 *
	 */
	public class HighscoreComparator implements Comparator<Player> {

		/**
		 * The method that compares players in the player-list.
		 */
		public int compare(Player player1, Player player2) {
			int sc1 = player1.getTotalScore();
			int sc2 = player2.getTotalScore();
			if (sc1 > sc2) {
				return -1;
			} else if (sc1 < sc2) {
				return +1;
			} else {
				return 0;
			}
		}
	}
}
