package gotBrains;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.*;

public class MathGameGame extends JPanel implements ActionListener {
	private ImageIcon iconQuit = new ImageIcon("images/quitButton.png");
	private ImageIcon iconQuitHover = new ImageIcon("images/quitButtonHover.png");
	private ImageIcon iconMenu = new ImageIcon("images/menuButton.png");
	private ImageIcon iconMenuHover = new ImageIcon("images/menuButtonHover.png");
	private JButton btnQuit = new JButton(iconQuit);
	private JButton btnMenu = new JButton(iconMenu);

	private JLabel lblElem1 = new JLabel("", SwingConstants.RIGHT);
	private JLabel lblElem2 = new JLabel("", SwingConstants.LEFT);
	private JLabel lblChar = new JLabel("", SwingConstants.CENTER);

	private Controller controller;
	private Font font = new Font("", Font.BOLD, 24);
	private String difficulty = "";
	private Random random = new Random();

	public MathGameGame(Controller controller) {
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

		add(lblElem1);
//		lblElem1.setOpaque(true);
		lblElem1.setFont(font);
		lblElem1.setBounds(350, 350, 60, 30);
		
		add(lblChar);
//		lblChar.setOpaque(true);
		lblChar.setFont(font);
		lblChar.setBounds(410, 350, 30, 30);
		
		add(lblElem2);
//		lblElem2.setOpaque(true);
		lblElem2.setFont(font);
		lblElem2.setBounds(440, 350, 60, 30);
		
		MathGameEasy mge = new MathGameEasy();
		mge.start();
	}

	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}

	protected void paintComponent(Graphics g) {
		ImageIcon background = new ImageIcon("images/mathGameBackground.png");
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

	private class MathGameEasy extends Thread {
		public void run() {
			lblElem1.setText(Integer.toString(random.nextInt(10) +1));
			lblElem2.setText(Integer.toString(random.nextInt(10) +1));
			int tempChar = random.nextInt(2) + 1;
			if(tempChar == 1) {
				lblChar.setText("+");
			} else if (tempChar == 2) {
				lblChar.setText("-");
			}
		}
	}
}