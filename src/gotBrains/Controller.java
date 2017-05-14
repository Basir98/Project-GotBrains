package gotBrains;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.sound.sampled.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Main class that starts the application by creating all windows and shows the
 * menuWindow so that the user can choose what to do.
 * 
 * @author Isak Hartman
 *
 */
public class Controller {
	
	private JFrame frame;
	private JPanel panelContainer = new JPanel();
	private MenuWindow menuWindow;
	private ScrabbleMenu scrabbleMenu;
	private ScrabbleWindow scrabbleGame;
	private SimonSaysWindow simonSaysWindow;
	private MathGameMenu mathGameMenu;
	private MathGameGame mathGameGame;
	private Leaderboard leaderboardWindow;
	private String currentUsername;
	private File backgroundMusic = new File("sounds/backgroundMusic.wav");
	private File correctSound = new File("sounds/correctSound.wav");
	private File incorrectSound = new File("sounds/incorrectSound.wav");
	private Clip music;
	private boolean mutedMusic = false;
	private boolean mutedSound = false;

	private HighscoreManager hm = new HighscoreManager();
	private CardLayout cl = new CardLayout();
	private Font customFont;

	public Controller(JFrame frame) {
		this.frame = frame;
		panelContainer.setLayout(cl);
		loadApp();
		startMusic();
	}

	public void loadApp() {
		menuWindow = new MenuWindow(this);
		scrabbleMenu = new ScrabbleMenu(this);
		simonSaysWindow = new SimonSaysWindow(this);
		mathGameMenu = new MathGameMenu(this);

		panelContainer.add(menuWindow, "menuWindow");
		panelContainer.add(scrabbleMenu, "scrabbleWindow");
		panelContainer.add(simonSaysWindow, "simonSaysWindow");
		panelContainer.add(mathGameMenu, "mathGameWindow");

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panelContainer);
		frame.setSize(800, 600);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);
		frame.setUndecorated(true);
		frame.pack();
		frame.setVisible(true);
		frame.setResizable(false);

		cl.show(panelContainer, "menuWindow");
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		menuWindow.fieldUsername.grabFocus();
	}
	
	public Font getCustomFont(String filepath, int style, int size) {
		try {
			customFont = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(new File(filepath))).deriveFont(style, size);
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: " + e);
		} catch (FontFormatException e) {
			System.out.println("ERROR: " + e);
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("ERROR: " + e);
			e.printStackTrace();
		}
		return customFont;
	}
	
	public void startMusic() {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(backgroundMusic);
			music = AudioSystem.getClip();
			music.open(ais);
			FloatControl gainControl = (FloatControl) music.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(-20.0f);
			music.start();
			music.loop(100);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void toggleMusic() {
		if (!mutedMusic) {
			music.stop();
			mutedMusic = true;
		}
		else if (mutedMusic) {
			startMusic();
			mutedMusic = false;
		}
	}

	public void toggleSound() {
		if (mutedSound) {
			mutedSound = false;
		} else mutedSound = true;
	}

	public void correctSound(boolean correct) {
		if (!mutedSound) {
			try {
				if (correct) {
					AudioInputStream ais = AudioSystem.getAudioInputStream(correctSound);
					Clip sound = AudioSystem.getClip();
					sound.open(ais);
					sound.start();
				} else if (!correct) {
					AudioInputStream ais = AudioSystem.getAudioInputStream(incorrectSound);
					Clip sound = AudioSystem.getClip();
					sound.open(ais);
					sound.start();
				}
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	public void showScrabbleWindow() {
		cl.show(panelContainer, "scrabbleWindow");
	}

	public void showSimonSaysWindow() {
		cl.show(panelContainer, "simonSaysWindow");
	}

	public void showMathGameWindow() {
		cl.show(panelContainer, "mathGameWindow");
	}

	public void startScrabbleWindow(int difficulty) {
		scrabbleGame = new ScrabbleWindow(this);
		panelContainer.add(scrabbleGame, "scrabbleGame");

		scrabbleGame.setDifficulty(difficulty);
		scrabbleGame.startLevel();
		cl.show(panelContainer, "scrabbleGame");
		scrabbleGame.textField.grabFocus();
	}

	public void startMathGame(int difficulty) {
		mathGameGame = new MathGameGame(this);
		panelContainer.add(mathGameGame, "mathGameGame");

		mathGameGame.setDifficulty(difficulty);
		mathGameGame.startLevel();
		cl.show(panelContainer, "mathGameGame");
		mathGameGame.textField.grabFocus();
	}

	public void showLeaderboard() {
		leaderboardWindow = new Leaderboard(this);
		panelContainer.add(leaderboardWindow, "leaderboardWindow");
		cl.show(panelContainer, "leaderboardWindow");
	}

	public void showMenu() {
		cl.show(panelContainer, "menuWindow");
		menuWindow.fieldUsername.grabFocus();
	}
	
	public void minimizeApp() {
		frame.setState(JFrame.ICONIFIED);
	}

	public void setCurrentUsername(String username) {
		this.currentUsername = username;
	}

	public void addPlayer(String username) {
		hm.addPlayer(username);
	}

	public String getLeaderboardPlacement() {
		return hm.getLeaderboardPlacement();
	}

	public String getLeaderboardScore() {
		return hm.getLeaderboardScore();
	}

	public void clearLeaderboard() {
		hm.clearScores();
	}

	public void newMathGameScore(int score) {
		hm.addMathGameScore(this.currentUsername, score);
	}

	public void newScrabbleScore(int score) {
		hm.addScrabbleScore(this.currentUsername, score);
	}

	public void newSimonSaysScore(int score) {
		hm.addSimonSaysScore(this.currentUsername, score);
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		Controller controller = new Controller(frame);
	}

}
