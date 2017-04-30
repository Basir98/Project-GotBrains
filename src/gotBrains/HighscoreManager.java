package gotBrains;

import java.util.*;
import java.io.*;

public class HighscoreManager {
	private ArrayList<Player> players;
	private static final String HIGHSCORE_FILE = "files/scores.dat";
	ObjectOutputStream outputStream = null;
	ObjectInputStream inputStream = null;

	public HighscoreManager() {
		players = new ArrayList<Player>();
	}

	public ArrayList<Player> getScores() {
		loadScoreFile();
		sort();
		return players;
	}

	private void sort() {
		HighscoreComparator comparator = new HighscoreComparator();
		Collections.sort(players, comparator);
	}
	
	public void addPlayer(String username) {
		loadScoreFile();
		if (!usernameTaken(players, username)) {
			players.add(new Player(username));
			updateScoreFile();
		} else {
			System.out.println("Username \"" + username + "\" already exists.");
			System.out.println();
		}
	}

	public boolean usernameTaken(ArrayList<Player> players, String username) {
		for (Player object : players) {
			if (object.getUsername().equals(username)) {
				return true;
			}
		}
		return false;
	}

	public void addMathGameScore(String username, int score) {
		loadScoreFile();
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).getUsername().equals(username)) {
				players.get(i).setMathGameScore(score);
			}
		}
		updateScoreFile();
	}

	public void addScrabbleScore(String username, int score) {
		loadScoreFile();
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).getUsername().equals(username)) {
				players.get(i).setScrabbleScore(score);
			}
		}
		updateScoreFile();
	}

	public void addSimonSaysScore(String username, int score) {
		loadScoreFile();
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).getUsername().equals(username)) {
				players.get(i).setSimonSaysScore(score);
			}
		}
		updateScoreFile();
	}

	public void clearScores() {
		loadScoreFile();
		players.clear();
		updateScoreFile();
	}

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

	public String getHighscoreString() {
		String highscoreString = "";
		int max = 10;

		ArrayList<Player> players;
		players = getScores();

		int i = 0;
		int x = players.size();
		if (x > max) {
			x = max;
		}
		while (i < x) {
			highscoreString += (i + 1) + ".\t" + players.get(i).getUsername() + "\t\t" + players.get(i).getTotalScore()
					+ "\n";
			i++;
		}
		return highscoreString;
	}

}
