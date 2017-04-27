package gotBrains;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Scanner;

import javax.swing.*;

public class ScrabbleWindow extends JPanel implements ActionListener {
	private Controller controller;
	private ScrabbleGame scrabbleGame;
	private Font font = new Font("Calibri", Font.BOLD, 32);
	private Color fontColor = new Color(80, 80, 80);
	private int difficulty;
	private int score = 0;
	private Random random = new Random();
	private Action action;

	private JButton btnQuit = new JButton(new ImageIcon("images/quitButton.png"));
	private JButton btnMenu = new JButton(new ImageIcon("images/menuButton.png"));
	JTextField textField = new JTextField();

	private JLabel lblText = new JLabel("", SwingConstants.CENTER);
	private JLabel lblScore = new JLabel("Score: " + score, SwingConstants.LEFT);
	private JLabel lblTimer = new JLabel("", SwingConstants.LEFT);

	@SuppressWarnings("serial")
	public ScrabbleWindow(Controller controller) {
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

		add(lblText);
		lblText.setFont(font);
		lblText.setForeground(fontColor);
		lblText.setBounds(320, 320, 200, 200);

		add(textField);
		textField.setOpaque(false);
		textField.setBorder(BorderFactory.createEmptyBorder());
		textField.setFont(new Font("Calibri", Font.PLAIN, 28));
		textField.setForeground(fontColor);
		textField.setBounds(265, 370, 250, 30);
		textField.addActionListener(action = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				String correctAnswer = lblText.getText();
				String userAnswer = textField.getText();
				
				System.out.println("Your answer: " + textField.getText());

				String lbltext = lblText.getText();
				switch (lbltext) {
				case " ":
					if ((userAnswer).equals(correctAnswer)) {
						score++;
						updateScore();
						textField.setText("");
						scrabbleGame.newTask();
					} else {
						textField.setText("");
					}
			
				}
			}
		});

		add(lblScore);
		lblScore.setFont(new Font("Calibri", Font.PLAIN, 28));
		lblScore.setForeground(fontColor);
		lblScore.setBounds(340, 445, 200, 30);

		add(lblTimer);
		lblTimer.setFont(new Font("DejaVu Sans Mono", Font.PLAIN, 18));
		lblTimer.setForeground(new Color(180, 180, 180));
		lblTimer.setBounds(330, 2, 160, 30);

		CountDownTimer timer = new CountDownTimer(0, 30);
		timer.start();
	}

	public void updateScore() {
		lblScore.setText("Score: " + score);
	}

	public void gameOver() {
		System.out.println("Your result: " + score + " points.");
		controller.showScrabbleWindow();
	}

	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}

	public void startLevel() {
		scrabbleGame = new ScrabbleGame();
		scrabbleGame.newTask();
	}

	protected void paintComponent(Graphics g) {
		ImageIcon background = new ImageIcon("images/scrabbleGameBackground.png");
		super.paintComponent(g);
		g.drawImage(background.getImage(), 0, 0, null);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnMenu) {
			// avbryt aktuellt spel, nollställ timer, nollställ score.
			controller.showMenu();
		} else if (e.getSource() == btnQuit) {
			System.exit(0);
		}
	}

	private class ScrabbleGame {
		public void newTask() {

			switch (difficulty) {
			case 1:
				String[] wordsEasy = { "Baguette", "Fanta", "Samsung", "Lenovo", "Bond", "Väska", "Dricka","Kallt", "Varmt",};
				Random rand = new Random();
				int r = rand.nextInt(9);

				 System.out.println(wordsEasy[r].toLowerCase());

				// Create a new char array with the size of the random word.
				char[] chars = new char[wordsEasy[r].length()];

				// Populate the char array.
				for (int i = 0; i < wordsEasy[r].length(); i++) {
					chars[i] = wordsEasy[r].toLowerCase().charAt(i);
				}

				for (int i = 0; i < chars.length; i++) {
					int randomPosition = rand.nextInt(chars.length);
					char temp = chars[i];
					chars[i] = chars[randomPosition];
					chars[randomPosition] = temp;
				}
			// Save the scrambled word in a new string.
				String scrambledWord = new String(chars);

				Scanner userAnswer = new Scanner(System.in);
				return;
				
			case 5:
				String[] wordsMedium={ "Ljummet", "Centrerad", "Batteri", "Högtalare", "Kapsyl", "Tangentbord", "Muspekare", "Layout", "Flygplan" };
				Random rand1 = new Random();
				int r1 = rand1.nextInt(9);

				System.out.println(wordsMedium[r1].toLowerCase());

				// Create a new char array with the size of the random word.
				char[] chars1 = new char[wordsMedium[r1].length()];

				// Populate the char array.
				for (int i = 0; i < wordsMedium[r1].length(); i++) {
					chars1[i] = wordsMedium[r1].toLowerCase().charAt(i);
				}

				for (int i = 0; i < chars1.length; i++) {
					int randomPosition = rand1.nextInt(chars1.length);
					char temp = chars1[i];
					chars1[i] = chars1[randomPosition];
					chars1[randomPosition] = temp;
				}
			// Save the scrambled word in a new string.
				String scrambledWord1 = new String(chars1);

				Scanner userAnswer1 = new Scanner(System.in);
				return;
			case 10:
				String[] wordsHard={ "Taklampa", "Aggressivitet", "Synonymer", "Överlappa", "Vattenpöl", "Multiplicitet", "Decryptering", "Inkorporation", "Arrangemang" };
				Random rand2 = new Random();
				int r2 = rand2.nextInt(9);

				 System.out.println(wordsHard[r2].toLowerCase());

				// Create a new char array with the size of the random word.
				char[] chars2 = new char[wordsHard[r2].length()];

				// Populate the char array.
				for (int i = 0; i < wordsHard[r2].length(); i++) {
					chars2[i] = wordsHard[r2].toLowerCase().charAt(i);
				}

				for (int i = 0; i < chars2.length; i++) {
					int randomPosition = rand2.nextInt(chars2.length);
					char temp = chars2[i];
					chars2[i] = chars2[randomPosition];
					chars2[randomPosition] = temp;
				}
			// Save the scrambled word in a new string.
				String scrambledWord2 = new String(chars2);

				Scanner userAnswer2 = new Scanner(System.in);
				
				return;
				
			}
			

		}
	}

	private class CountDownTimer extends Thread {
		private int minutes;
		private int seconds;

		public CountDownTimer(int minutes, int seconds) {
			this.minutes = minutes;
			this.seconds = seconds;
		}

		public void run() {
			do {
				// System.out.println(toString());
				lblTimer.setText(toString());
				try {
					Thread.sleep(999);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (seconds == 0) {
					minutes--;
					seconds = 59;
				} else if (seconds != 0) {
					seconds--;
				}
			} while (minutes >= 0 && seconds >= 0);
			gameOver();
		}

		public String toString() {
			return minutes + " min" + ", " + seconds + " sec";
		}
	}
}