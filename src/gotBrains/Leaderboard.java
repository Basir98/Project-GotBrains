package gotBrains;

import java.util.HashMap;

import javax.swing.JPanel;

public class Leaderboard extends JPanel {
	HashMap<String, Integer> leaderboard = new HashMap<String, Integer>();
	
	public Leaderboard() {
		
	}
	
	public void put(String username, int score) {
		leaderboard.put(username, score);
	}
	
	public String get(String key) {
		return key + "-" + leaderboard.get(key);
		
	}
	
	public static void main(String[] args) {
		Leaderboard leaderboard = new Leaderboard();
		leaderboard.put("Felix", 500);
		leaderboard.put("Isak", 501);
		leaderboard.put("Alfons", 200);
		leaderboard.put("Fredrik", 2);
		System.out.println(leaderboard.get("Felix"));
		System.out.println(leaderboard.get("Isak"));
		System.out.println(leaderboard.get("Fredrik"));
		
	}
}
