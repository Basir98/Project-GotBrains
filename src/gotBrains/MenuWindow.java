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
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class MenuWindow extends JPanel implements ActionListener {
	private JButton btnQuit = new JButton(new ImageIcon("images/quitButton.png"));
	private JButton btnScrabble = new JButton("SCRABBLE");
	private JButton btnSimonSays = new JButton("SIMON SAYS");
	private JButton btnMathGame = new JButton("MATH GAME");
	private JButton btnLeaderboard = new JButton("LEADERBOARD");
	JTextField fieldUsername = new JTextField();
	
	private Font font = new Font("Calibri", Font.BOLD, 18);
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

		add(btnQuit);
		btnQuit.setOpaque(false);
		btnQuit.setContentAreaFilled(false);
		btnQuit.setBorderPainted(false);
		btnQuit.setBounds(758, 2, 40, 35);
		btnQuit.addActionListener(this);
		btnQuit.setRolloverIcon(new ImageIcon("images/quitButtonHover.png"));
		
		add(btnScrabble);
		btnScrabble.setOpaque(false);
		btnScrabble.setContentAreaFilled(false);
		btnScrabble.setBounds(220, 360, 110, 30);
		btnScrabble.addActionListener(this);

		add(btnSimonSays);
		btnSimonSays.setOpaque(false);
		btnSimonSays.setContentAreaFilled(false);
		btnSimonSays.setBounds(340, 360, 110, 30);
		btnSimonSays.addActionListener(this);

		add(btnMathGame);
		btnMathGame.setOpaque(false);
		btnMathGame.setContentAreaFilled(false);
		btnMathGame.setBounds(460, 360, 110, 30);
		btnMathGame.addActionListener(this);
		
		add(fieldUsername);
		fieldUsername.setOpaque(false);
		fieldUsername.setHorizontalAlignment(JTextField.CENTER);
		fieldUsername.setFont(font);
		fieldUsername.setDocument(new LengthRestrictedDocument(12));
		fieldUsername.setText("ANONYMOUS");
		fieldUsername.setCaretPosition(fieldUsername.getText().length());
		fieldUsername.setBorder(BorderFactory.createEmptyBorder());
		fieldUsername.setForeground(new Color(80, 80, 80));
		fieldUsername.setBounds(320, 320, 150, 30);
		
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
			controller.addPlayer(fieldUsername.getText());
			controller.setCurrentUsername(fieldUsername.getText());
			controller.showScrabbleWindow();
		} else if (e.getSource() == btnSimonSays) {
			controller.addPlayer(fieldUsername.getText());
			controller.setCurrentUsername(fieldUsername.getText());
			controller.showSimonSaysWindow();
		} else if (e.getSource() == btnMathGame) {
			controller.addPlayer(fieldUsername.getText());
			controller.setCurrentUsername(fieldUsername.getText());
			controller.showMathGameWindow();
		}
	}
}
