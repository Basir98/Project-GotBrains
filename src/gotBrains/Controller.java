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
	private ScrabbleWindow scrabbleWindow;
	private SimonSaysWindow simonSaysWindow;
	private MathGameWindow mathGameWindow;
	CardLayout cl = new CardLayout();
	
	public Controller(JFrame frame) {
		this.frame = frame;
		panelContainer.setLayout(cl);
		loadApp();
	}
	
	public void loadApp() {
		menuWindow = new MenuWindow(this);
		scrabbleWindow = new ScrabbleWindow(this);
		simonSaysWindow = new SimonSaysWindow(this);
		mathGameWindow = new MathGameWindow(this);
		
		panelContainer.add(menuWindow, "menuWindow");
		panelContainer.add(scrabbleWindow, "scrabbleWindow");
		panelContainer.add(simonSaysWindow, "simonSaysWindow");
		panelContainer.add(mathGameWindow, "mathGameWindow");
		cl.show(panelContainer, "menuWindow");
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panelContainer);
		frame.setSize(800, 600);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);
		frame.setUndecorated(true);
		frame.pack();
		frame.setVisible(true);
		frame.setResizable(false);
		
	}
	
	public void startScrabble() {
		cl.show(panelContainer, "scrabbleWindow");

	}
	
	public void startSimonSays() {
		cl.show(panelContainer, "simonSaysWindow");

	}
	
	public void startMathGame() {
		cl.show(panelContainer, "mathGameWindow");

	}
	
	public void showMenu() {
		cl.show(panelContainer, "menuWindow");
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		Controller controller = new Controller(frame);
		
		
		
		
	}

}
