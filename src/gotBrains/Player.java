package gotBrains;

import java.io.Serializable;

public class Player implements Serializable {
	private String username;
	private int scrabbleScore = 0, mathGameScore = 0, simonSaysScore = 0, totalScore = 0;

	public Player(String username) {
		this.username = username;
	}

	public void setSimonSaysScore(int score) {
		if (score > this.simonSaysScore) {
			this.simonSaysScore = score;
		}
	}

	public void setMathGameScore(int score) {
		if (score > this.mathGameScore) {
			this.mathGameScore = score;
		}
	}

	public void setScrabbleScore(int score) {
		if(score > this.scrabbleScore) {
			this.scrabbleScore = score;
		}
	}
	
	public void setTotalScore() {
		totalScore = getMathGameScore() + getScrabbleScore() + getSimonSaysScore();
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
	
	public int getTotalScore() {
		setTotalScore();
		return totalScore;
	}
	
	public String toString() {
		return "Result: \n\n" + "Username: " + username + "\nScrabble Score: " + scrabbleScore + "\nSimon Says score: " + simonSaysScore + "\nMath Game score: " + mathGameScore + "\nTotal score: " + getTotalScore();
	}
	
//	public static void main(String[] args) {
//		Player player = new Player("Felix");
//		player.setMathGameScore(2);
//		player.setScrabbleScore(10);
//		player.setSimonSaysScore(6);
//		System.out.println(player.toString());
//		System.out.println("Total score: " + getTotalScore();
//	}
}
