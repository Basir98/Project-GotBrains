package gotBrains;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.*;

public class Leaderboard extends JPanel implements ActionListener {
	
	private JButton btnQuit = new JButton(new ImageIcon("images/quitButton.png"));
	private JButton btnMenu = new JButton(new ImageIcon("images/menuButton.png"));
	private JTextArea highscoreList = new JTextArea();
	
	private Font font = new Font("Calibri", Font.BOLD, 18);
	private Color fontColor = new Color(80, 80, 80);
	
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
		
		add(highscoreList);
		highscoreList.setOpaque(false);
		highscoreList.setBounds(125, 180, 550, 350);
		highscoreList.setForeground(fontColor);
		highscoreList.setFont(font);
		highscoreList.setEditable(false);
		highscoreList.setText(controller.getHighscore());
		highscoreList.setSelectionColor(new Color(0, 0, 0, 0));
		
	}
	
	protected void paintComponent(Graphics g) {
		ImageIcon background = new ImageIcon("images/leaderboardBackground.png");
		super.paintComponent(g);
		g.drawImage(background.getImage(), 0, 0, null);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnQuit) {
			System.exit(0);
		} else if(e.getSource() == btnMenu) {
			controller.showMenu();
		}
	}
}
