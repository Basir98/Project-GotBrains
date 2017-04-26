package gotBrains;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Main class that starts the application by opening a window to let the user select which game to play
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
	private Leaderboard leaderboardMathGame;
	CardLayout cl = new CardLayout();
	
	public Controller(JFrame frame) {
		this.frame = frame;
		panelContainer.setLayout(cl);
		loadApp();
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
	
	public void showMathGameLeaderboard() {
		leaderboardMathGame = new Leaderboard(this);
		panelContainer.add(leaderboardMathGame, "leaderboardMathGame");
		cl.show(panelContainer, "leaderboardMathGame");
	}
	
	public void showMenu() {
		cl.show(panelContainer, "menuWindow");
		menuWindow.fieldUsername.grabFocus();
	}
	
	public JFrame getFrame() {
		return frame;
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		Controller controller = new Controller(frame);	
		
	}
}
