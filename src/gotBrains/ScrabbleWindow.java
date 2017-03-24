package gotBrains;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ScrabbleWindow extends JPanel implements ActionListener {
	private ImageIcon iconQuit = new ImageIcon("images/quitButton.png");
	private ImageIcon iconQuitHover = new ImageIcon("images/quitButtonHover.png");
	private ImageIcon iconMenu = new ImageIcon("images/menuButton.png");
	private ImageIcon iconMenuHover = new ImageIcon("images/menuButtonHover.png");
	private JButton btnQuit = new JButton(iconQuit);
	private JButton btnMenu = new JButton(iconMenu);
	private Controller controller;

	public ScrabbleWindow(Controller controller) {
		this.controller = controller;
		setLayout(null);
		setPreferredSize(new Dimension(800, 600));
		
		add(btnQuit);
		btnQuit.setOpaque(false);
		btnQuit.setContentAreaFilled(false);
		btnQuit.setBorderPainted(false);
		btnQuit.setBounds(762, -2, 40, 35);
		btnQuit.addActionListener(this);
		btnQuit.setRolloverIcon(iconQuitHover);
		
		add(btnMenu);
		btnMenu.setOpaque(false);
		btnMenu.setContentAreaFilled(false);
		btnMenu.setBorderPainted(false);
		btnMenu.setBounds(-2, -2, 120, 30);
		btnMenu.addActionListener(this);
		btnMenu.setRolloverIcon(iconMenuHover);
	}

	protected void paintComponent(Graphics g) {
		ImageIcon background = new ImageIcon("images/scrabbleBackground.png");
		super.paintComponent(g);
		g.drawImage(background.getImage(), 0, 0, null);
	}
	
	
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnMenu) {
			controller.showMenu();
		} else if (e.getSource() == btnQuit) {
			System.exit(0);
		}
	}
}
