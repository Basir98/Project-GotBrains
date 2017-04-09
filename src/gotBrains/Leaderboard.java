package gotBrains;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Leaderboard extends JPanel implements ActionListener {
	HashMap<String, Integer> leaderboard = new HashMap<String, Integer>();
	
	private JButton btnQuit = new JButton(new ImageIcon("images/quitButton.png"));
	private JButton btnMenu = new JButton(new ImageIcon("images/menuButton.png"));

	private Controller controller;
	
	public Leaderboard(Controller controller) {
		this.controller = controller;
		setLayout(null);
		setPreferredSize(new Dimension(800, 600));
		
		add(btnQuit);
		btnQuit.setOpaque(false);
		btnQuit.setContentAreaFilled(false);
		btnQuit.setBorderPainted(false);
		btnQuit.setBounds(758, 2, 40, 35);
		btnQuit.addActionListener(this);
		btnQuit.setRolloverIcon(new ImageIcon("images/quitButtonHover.png"));

		add(btnMenu);
		btnMenu.setOpaque(false);
		btnMenu.setContentAreaFilled(false);
		btnMenu.setBorderPainted(false);
		btnMenu.setBounds(-2, -2, 120, 30);
		btnMenu.addActionListener(this);
		btnMenu.setRolloverIcon(new ImageIcon("images/menuButtonHover.png"));
	}
	
	protected void paintComponent(Graphics g) {
		ImageIcon background = new ImageIcon("images/leaderboardMathGame.png");
		super.paintComponent(g);
		g.drawImage(background.getImage(), 0, 0, null);
	}
	
	public void put(String username, int score) {
		leaderboard.put(username, score);
	}
	
	public String get(String key) {
		return key + "-" + leaderboard.get(key);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnQuit) {
			System.exit(0);
		} else if(e.getSource() == btnMenu) {
			controller.showMenu();
		}
		
	}
	
//	public static void main(String[] args) {
//		Leaderboard leaderboard = new Leaderboard();
//		leaderboard.put("Felix", 500);
//		leaderboard.put("Isak", 501);
//		leaderboard.put("Alfons", 200);
//		leaderboard.put("Fredrik", 2);
//		System.out.println(leaderboard.get("Felix"));
//		System.out.println(leaderboard.get("Isak"));
//		System.out.println(leaderboard.get("Fredrik"));
//		
//	}
}
