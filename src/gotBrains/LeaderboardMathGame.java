package gotBrains;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class LeaderboardMathGame extends Leaderboard {

	public LeaderboardMathGame(Controller controller) {
		super(controller);
		
	}

	protected void paintComponent(Graphics g) {
		ImageIcon background = new ImageIcon("images/leaderboardMathGame.png");
		super.paintComponent(g);
		g.drawImage(background.getImage(), 0, 0, null);
	}
	
	public static void main(String[] args) {
		LeaderboardMathGame leaderboard = new LeaderboardMathGame(new Controller(new JFrame()));
		leaderboard.put("Felix", 500);
		leaderboard.put("Isak", 501);
		leaderboard.put("Alfons", 200);
		leaderboard.put("Fredrik", 2);
		System.out.println(leaderboard.get("Felix"));
		System.out.println(leaderboard.get("Isak"));
		System.out.println(leaderboard.get("Fredrik"));

	}
}
