package gotBrains;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class MathGameMenu extends JPanel implements ActionListener {
	private Controller controller;
	
	private Font font = new Font("Courier New", Font.BOLD, 14);
	private Color fontColor = new Color(180, 180, 180);
	
	private JButton btnQuit = new JButton(new ImageIcon("images/quitButton.png"));
	private JButton btnMenu = new JButton(new ImageIcon("images/menuButton.png"));
	private JButton btnStartEasy = new JButton(new ImageIcon("images/easyButton.png"));
	private JButton btnStartMedium = new JButton(new ImageIcon("images/mediumButton.png"));
	private JButton btnStartHard = new JButton(new ImageIcon("images/hardButton.png"));
	private JButton btnLeaderboard = new JButton("LEADERBOARD");
	
	
	public MathGameMenu(Controller controller) {
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
		btnMenu.setRolloverIcon( new ImageIcon("images/menuButtonHover.png"));
		
		add(btnStartEasy);
		btnStartEasy.setOpaque(true);
		btnStartEasy.setContentAreaFilled(false);
		btnStartEasy.setBorderPainted(false);
		btnStartEasy.setRolloverIcon(new ImageIcon("images/easyButtonHover.png"));
		btnStartEasy.setBounds(245, 295, 100, 30);
		btnStartEasy.addActionListener(this);
		
		add(btnStartMedium);
		btnStartMedium.setOpaque(true);
		btnStartMedium.setContentAreaFilled(false);
		btnStartMedium.setBorderPainted(false);
		btnStartMedium.setRolloverIcon(new ImageIcon("images/mediumButtonHover.png"));
		btnStartMedium.setBounds(350, 295, 100, 30);
		btnStartMedium.addActionListener(this);
		
		add(btnStartHard);
		btnStartHard.setOpaque(true);
		btnStartHard.setContentAreaFilled(false);
		btnStartHard.setBorderPainted(false);
		btnStartHard.setRolloverIcon(new ImageIcon("images/hardButtonHover.png"));
		btnStartHard.setBounds(455, 295, 100, 30);
		btnStartHard.addActionListener(this);
		
	}

	protected void paintComponent(Graphics g) {
		ImageIcon background = new ImageIcon("images/calculateThisBackground.png");
		super.paintComponent(g);
		g.drawImage(background.getImage(), 0, 0, null);
		
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnMenu) {
			controller.showMenu();
		} else if (e.getSource() == btnQuit) {
			System.exit(0);
		} else if(e.getSource() == btnStartEasy) {
			controller.startMathGame(1);
		} else if(e.getSource() == btnStartMedium) {
			controller.startMathGame(5);
		} else if(e.getSource() == btnStartHard) {
			controller.startMathGame(10);
		}
	}

}
