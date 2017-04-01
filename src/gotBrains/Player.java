package gotBrains;

public class Player {
	private String username;
	private int scrabbleScore, mathGameScore, simonSaysScore;

	public Player(String username) {
		this.username = username;
		setScrabbleScore(0);
		setMathGameScore(0);
		setSimonSaysScore(0);	
	}

	private void setSimonSaysScore(int score) {
		this.simonSaysScore = score;
	}

	private void setMathGameScore(int score) {
		this.mathGameScore = score;
	}

	private void setScrabbleScore(int score) {
		this.simonSaysScore = score;
	}

	public String getUsername() {
		return username;
	}

	public int getScrabbleScore() {
		return scrabbleScore;
	}

	public int getMathGameScore() {
		return mathGameScore;
	}

	public int getSimonSaysScore() {
		return simonSaysScore;
	}
}
