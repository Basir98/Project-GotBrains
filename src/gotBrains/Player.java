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
		if (score > this.simonSaysScore) {
			this.simonSaysScore = score;
		}
	}

	private void setMathGameScore(int score) {
		if (score > this.mathGameScore) {
			this.mathGameScore = score;
		}
	}

	private void setScrabbleScore(int score) {
		if(score > this.scrabbleScore) {
			this.scrabbleScore = score;
		}
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

	public String toString() {
		return "Result: \n\n" + "Username: " + username + "\nScrabble Score: " + scrabbleScore + "\nSimon Says score: " + simonSaysScore + "\nMath Game score: " + mathGameScore;
	}
	
	public static void main(String[] args) {
		Player player = new Player("Felix");
		player.setMathGameScore(2);
		player.setScrabbleScore(10);
		player.setSimonSaysScore(6);
		System.out.println(player.toString());
	}
}
