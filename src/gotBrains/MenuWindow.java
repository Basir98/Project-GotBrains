package gotBrains;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.*;

public class MenuWindow extends JPanel implements ActionListener {
	private JButton btnQuit = new JButton("QUIT");
	private JButton btnScrabble = new JButton("SCRABBLE");
	private JButton btnSimonSays = new JButton("SIMON SAYS");
	private JButton btnMathGame = new JButton("MATH GAME");
	private Controller controller;

	public MenuWindow(Controller controller) {
		this.controller = controller;
		setLayout(null);
		setPreferredSize(new Dimension(800, 600));
//		try {
//			File backgroundMusic = new File("sounds/backgroundMusic.wav");
//			AudioInputStream ais = AudioSystem.getAudioInputStream(backgroundMusic);
//			Clip clip = AudioSystem.getClip();
//			clip.open(ais);
//			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
//			gainControl.setValue(-20.0f);
//			clip.start();
//			clip.loop(10);
//		} catch (Exception e) {
//			System.out.println(e);
//		}

		add(btnScrabble);
		btnScrabble.setOpaque(false);
		btnScrabble.setContentAreaFilled(false);
		btnScrabble.setBounds(340, 300, 110, 30);
		btnScrabble.addActionListener(this);

		add(btnSimonSays);
		btnSimonSays.setOpaque(false);
		btnSimonSays.setContentAreaFilled(false);
		btnSimonSays.setBounds(340, 340, 110, 30);
		btnSimonSays.addActionListener(this);

		add(btnMathGame);
		btnMathGame.setOpaque(false);
		btnMathGame.setContentAreaFilled(false);
		btnMathGame.setBounds(340, 380, 110, 30);
		btnMathGame.addActionListener(this);

		add(btnQuit);
		btnQuit.setOpaque(false);
		btnQuit.setContentAreaFilled(false);
		btnQuit.setBounds(360, 420, 70, 30);
		btnQuit.addActionListener(this);
	}

	protected void paintComponent(Graphics g) {
		ImageIcon background = new ImageIcon("images/openingWindowBackground.png");
		super.paintComponent(g);
		g.drawImage(background.getImage(), 0, 0, null);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnQuit) {
			System.exit(0);
		} else if (e.getSource() == btnScrabble) {
			controller.startScrabble();
		} else if (e.getSource() == btnSimonSays) {
			controller.startSimonSays();
		} else if (e.getSource() == btnMathGame) {
			controller.startMathGame();
		}
	}
}
