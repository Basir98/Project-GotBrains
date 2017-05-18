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

public class MainMenu extends JPanel implements ActionListener {
	private JButton btnQuit = new JButton(new ImageIcon("images/quitButton.png"));
	private JButton btnMinimize = new JButton(new ImageIcon("images/minimizeButton.png"));
	private JButton btnSpellThis = new JButton(new ImageIcon("images/spellThisButton.png"));
	private JButton btnMemorizeThis = new JButton(new ImageIcon("images/memorizeThisButton.png"));
	private JButton btnCalculateThis = new JButton(new ImageIcon("images/calculateThisButton.png"));
	private JButton btnLeaderboard = new JButton(new ImageIcon("images/leaderboardButton.png"));

	private JButton btnToggleMusic = new JButton(new ImageIcon("images/musicIcon.png"));
	private JButton btnToggleSound = new JButton(new ImageIcon("images/soundIcon.png"));
	JTextField fieldUsername = new JTextField();

	private boolean mutedMusic = false;
	private boolean mutedSound = false;

	private Font font = new Font("Monospaced", Font.BOLD, 18);
	private Controller controller;

	public MainMenu(Controller controller) {
		this.controller = controller;
		setLayout(null);
		setPreferredSize(new Dimension(800, 600));

		add(btnQuit);
		btnQuit.setOpaque(false);
		btnQuit.setContentAreaFilled(false);
		btnQuit.setBorderPainted(false);
		btnQuit.setFocusPainted(false);
		btnQuit.setBounds(756, 2, 40, 35);
		btnQuit.addActionListener(this);
		btnQuit.setRolloverIcon(new ImageIcon("images/quitButtonHover.png"));

		add(btnMinimize);
		btnMinimize.setOpaque(false);
		btnMinimize.setContentAreaFilled(false);
		btnMinimize.setBorderPainted(false);
		btnMinimize.setFocusPainted(false);
		btnMinimize.setBounds(716, 2, 40, 35);
		btnMinimize.addActionListener(this);
		btnMinimize.setRolloverIcon(new ImageIcon("images/minimizeButtonHover.png"));
		
		add(fieldUsername);
		fieldUsername.setOpaque(false);
		fieldUsername.setHorizontalAlignment(JTextField.CENTER);
		fieldUsername.setFont(font);
		fieldUsername.setDocument(new LengthRestrictedDocument(12));
		fieldUsername.setText("ANONYMOUS");
		fieldUsername.setCaretPosition(fieldUsername.getText().length());
		fieldUsername.setSelectionColor(Color.white);
		fieldUsername.setBorder(BorderFactory.createEmptyBorder());
		fieldUsername.setForeground(new Color(80, 80, 80));
		fieldUsername.setBounds(325, 260, 150, 30);
		
		add(btnCalculateThis);
		btnCalculateThis.setOpaque(false);
		btnCalculateThis.setContentAreaFilled(false);
		btnCalculateThis.setBorderPainted(false);
		btnCalculateThis.setBounds(275, 300, 250, 50);
		btnCalculateThis.addActionListener(this);
		btnCalculateThis.setRolloverIcon(new ImageIcon("images/calculateThisButtonHover.png"));
		
		add(btnMemorizeThis);
		btnMemorizeThis.setOpaque(false);
		btnMemorizeThis.setContentAreaFilled(false);
		btnMemorizeThis.setBorderPainted(false);
		btnMemorizeThis.setBounds(275, 360, 250, 50);
		btnMemorizeThis.addActionListener(this);
		btnMemorizeThis.setRolloverIcon(new ImageIcon("images/memorizeThisButtonHover.png"));
		
		add(btnSpellThis);
		btnSpellThis.setOpaque(false);
		btnSpellThis.setContentAreaFilled(false);
		btnSpellThis.setBorderPainted(false);
		btnSpellThis.setBounds(275, 420, 250, 50);
		btnSpellThis.addActionListener(this);
		btnSpellThis.setRolloverIcon(new ImageIcon("images/spellThisButtonHover.png"));

		add(btnLeaderboard);
		btnLeaderboard.setOpaque(false);
		btnLeaderboard.setContentAreaFilled(false);
		btnLeaderboard.setBorderPainted(false);
		btnLeaderboard.setBounds(275, 480, 250, 50);
		btnLeaderboard.addActionListener(this);
		btnLeaderboard.setRolloverIcon(new ImageIcon("images/leaderboardButtonHover.png"));

		add(btnToggleMusic);
		btnToggleMusic.setContentAreaFilled(false);
		btnToggleMusic.setBorderPainted(false);
		btnToggleMusic.setBounds(5, 560, 32, 32);
		btnToggleMusic.addActionListener(this);

		add(btnToggleSound);
		btnToggleSound.setContentAreaFilled(false);
		btnToggleSound.setBorderPainted(false);
		btnToggleSound.setBounds(40, 560, 32, 32);
		btnToggleSound.addActionListener(this);

	}

	protected void paintComponent(Graphics g) {
		ImageIcon background = new ImageIcon("images/openingWindowBackground.png");
		super.paintComponent(g);
		g.drawImage(background.getImage(), 0, 0, null);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnQuit) {
			System.exit(0);

		} else if (e.getSource() == btnMinimize) {
			controller.minimizeApp();
			
		} else if (e.getSource() == btnSpellThis) {
			controller.addPlayer(fieldUsername.getText());
			controller.setCurrentUsername(fieldUsername.getText());
			controller.showSpellThisMenu();

		} else if (e.getSource() == btnMemorizeThis) {
			controller.addPlayer(fieldUsername.getText());
			controller.setCurrentUsername(fieldUsername.getText());
			controller.showMemorizeThisWindow();

		} else if (e.getSource() == btnCalculateThis) {
			controller.addPlayer(fieldUsername.getText());
			controller.setCurrentUsername(fieldUsername.getText());
			controller.showCalculateThisMenu();

		} else if (e.getSource() == btnLeaderboard) {
			controller.showLeaderboard();

		} else if (e.getSource() == btnToggleMusic) {
			controller.toggleMusic();
			if (!mutedMusic) {
				btnToggleMusic.setIcon(new ImageIcon("images/musicIconMuted.png"));
				mutedMusic = true;
			} else if (mutedMusic) {
				btnToggleMusic.setIcon(new ImageIcon("images/musicIcon.png"));
				mutedMusic = false;
			}
		} else if (e.getSource() == btnToggleSound) {
			controller.toggleSound();
			if (!mutedSound) {
				btnToggleSound.setIcon(new ImageIcon("images/soundIconMuted.png"));
				mutedSound = true;
			} else if (mutedSound) {
				btnToggleSound.setIcon(new ImageIcon("images/soundIcon.png"));
				mutedSound = false;
			}
		}
	}
}
