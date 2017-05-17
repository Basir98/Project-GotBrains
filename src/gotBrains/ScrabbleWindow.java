package gotBrains;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Scanner;

import javax.swing.*;

public class ScrabbleWindow extends JPanel implements ActionListener {
	private String rightAnswer;
	private Controller controller;
	private ScrabbleGame scrabbleGame;
	private Font font = new Font("Calibri", Font.BOLD, 32);
	private Color fontColor = new Color(80, 80, 80);
	private Color darkGrey = new Color(80, 80, 80);
	private Color lightGrey = new Color(180, 180, 180);
	private int difficulty;
	private int score = 0;
	private Random random = new Random();
	private Action action;

	private JButton btnQuit = new JButton(new ImageIcon("images/quitButton.png"));
	private JButton btnMinimize = new JButton(new ImageIcon("images/minimizeButton.png"));
	private JButton btnMenu = new JButton(new ImageIcon("images/menuButton.png"));
	JTextField textField = new JTextField();

	private JLabel lblText = new JLabel("", SwingConstants.CENTER);
	private JLabel lblScore = new JLabel("Score: " + score, SwingConstants.LEFT);
	private JLabel lblTimer = new JLabel("", SwingConstants.LEFT);
	private JLabel lblEnterIcon = new JLabel(new ImageIcon("images/enterIcon.png"));

	@SuppressWarnings("serial")
	public ScrabbleWindow(Controller controller) {
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

		add(btnMenu);
		btnMenu.setOpaque(false);
		btnMenu.setContentAreaFilled(false);
		btnMenu.setBorderPainted(false);
		btnMenu.setBounds(-2, -2, 120, 30);
		btnMenu.addActionListener(this);
		btnMenu.setRolloverIcon(new ImageIcon("images/menuButtonHover.png"));

		add(lblText);
		lblText.setFont(new Font("Rockwell", Font.BOLD, 36));
		lblText.setForeground(fontColor);
		lblText.setBounds(190, 180, 400, 300);
		
		add(lblEnterIcon);
		lblEnterIcon.setBounds(490, 372, 24, 24);

		add(lblScore);
		lblScore.setFont(new Font("Calibri", Font.PLAIN, 24));
		lblScore.setForeground(lightGrey);
		lblScore.setBounds(355, 405, 200, 30);

		add(lblTimer);
		lblTimer.setFont(new Font("DejaVu Sans Mono", Font.PLAIN, 18));
		lblTimer.setForeground(lightGrey);
		lblTimer.setHorizontalAlignment(JLabel.CENTER);
		lblTimer.setBounds(315, 2, 160, 30);

		add(textField);
		textField.setOpaque(false);
		textField.setBorder(BorderFactory.createEmptyBorder());
		textField.setFont(new Font("Calibri", Font.PLAIN, 28));
		textField.setForeground(fontColor);
		textField.setBounds(290, 370, 250, 30);
		textField.addActionListener(action = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				String correctAnswer = rightAnswer;
				String userAnswer = textField.getText();

				System.out.println("Your answer: " + textField.getText());

				// String right = rightAnswer;
				// switch (right) {
				// case "":
				if ((userAnswer).equals(correctAnswer)) {
					score++;
					updateScore();
					textField.setText("");
					scrabbleGame.newTask();
				} else {
					textField.setText("");
				}

			}
		});
		// });

		add(lblScore);
		lblScore.setFont(new Font("Calibri", Font.PLAIN, 24));
		lblScore.setForeground(lightGrey);
		lblScore.setBounds(355, 405, 200, 30);

		add(lblTimer);
		lblTimer.setFont(new Font("DejaVu Sans Mono", Font.PLAIN, 18));
		lblTimer.setForeground(new Color(180, 180, 180));
		lblTimer.setBounds(330, 2, 160, 30);

		CountDownTimer timer = new CountDownTimer(1, 0);
		timer.start();
	}

	public void updateScore() {
		lblScore.setText("Score: " + score*difficulty);
	}

	public void gameOver() {
		textField.setEditable(false);
		System.out.println("Your result: " + score * difficulty + " points.");
		controller.newScrabbleScore(score * difficulty);
	}

	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}

	public void startLevel() {
		scrabbleGame = new ScrabbleGame();
		scrabbleGame.newTask();
	}

	protected void paintComponent(Graphics g) {
		ImageIcon background = new ImageIcon("images/spellThisBackground.png");
		super.paintComponent(g);
		g.drawImage(background.getImage(), 0, 0, null);
		// Coordinates that are used in painting custom Polygons
				int x1Points[] = { 275, 325, 465, 515 };
				int y1Points[] = { 0, 30, 30, 0 };
				int y2Points[] = { 400, 435, 435, 400 };
				int nPoints = 4;
				g.drawImage(background.getImage(), 0, 0, null);
				g.setColor(darkGrey);
				g.fillPolygon(x1Points, y1Points, nPoints);
				g.fillPolygon(x1Points, y2Points, nPoints);

				// Sets the thickness of the stroke to 2 pixels.
				Graphics2D g2 = (Graphics2D) g.create();
				g2.setStroke(new BasicStroke(3));
				g2.setPaint(darkGrey);
				g2.drawRect(270, 366, 250, 35);
				g2.drawRect(580, 365, 300, 300);

			}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnMenu) {
			// avbryt aktuellt spel, nollställ timer, nollställ score.
			controller.showMenu();
		} else if (e.getSource() == btnQuit) {
			System.exit(0);
		}
		else if (e.getSource() == btnMinimize) {
			controller.minimizeApp();
		}
	}

	private class ScrabbleGame {
		public void newTask() {

			switch (difficulty) {
			case 1:
				String[] wordsEasy = { "Sun", "Son", "Life", "Love", "Fond", "Ring", "Bag", "Cold", "Fish", "Hell",
						"Five", "Wolf", "Star", "King", "Time", "Tree", "City", "Sing", "Lion", "Foot", "Cool", "Body",
						"Golf", "Moon", "Work", "Lady", "Cake", "Blue", "High", "Rock", "Face", "Good", "Hate", "Pink",
						"Oven", "Bear", "Snow", "Taco", "Zero", "Town", "Book", "Card", "Bomb", "Game", "Year", "Worm",
						"Rage", "Quit", "Ugly", "Rice" };
				Random rand = new Random();
				int r = rand.nextInt(50);
				rightAnswer = wordsEasy[r].toLowerCase();

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
				lblText.setText(scrambledWord.toUpperCase());
				break;

			case 5:
				String[] wordsMedium = { "Hello", "Purple", "Twelve", "Samsung", "Heaven", "Banana", "Africa", "Office",
						"Snitch", "Pumpkin", "Perfect", "Freedom", "Nothing", "History", "Amazing", "Welcome", "Secret",
						"Dolphin", "Justice", "Animal", "Mother", "Father", "Pirate", "Winter", "Summer", "Friend",
						"Memory", "Bottle", "Couple", "Simple", "Guitar", "Police", "Bullet", "Soccer", "Hungry",
						"Murder", "Travel", "Killer", "Finger", "Second", "First", "Shadow", "Peanut", "Zombie",
						"Puzzle", "Vision", "Target", "Option", "Cheese", "Rocket" };
				Random rand1 = new Random();
				int r1 = rand1.nextInt(50);
				rightAnswer = wordsMedium[r1].toLowerCase();
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
				lblText.setText(scrambledWord1.toUpperCase());
				break;
			case 10:
				String[] wordsHard = { "Valkyire", "Football", "Strength", "February", "Building", "Relationship",
						"Calendar", "November", "Everything", "Basketball", "Technology", "Watermelon", "Champion",
						"Hospital", "Television", "Friendship", "Medicine", "University", "Blackboard", "Whiteboard",
						"Jupiter", "Mountain", "Umbrella", "Computer", "Electric", "Doughnut", "Paradise", "Keyboard",
						"Dinosaur", "Aquarium", "Aardvark", "Predator", "Smoothie", "Military", "Festival", "Campfire",
						"Training", "Lemonade", "Popsicle", "Broccoli", "Fraction", "Addition", "Anaconda", "Blackout",
						"Audience", "Creative", "Division", "Goldfish", "Colorful", "Geometry" };
				Random rand2 = new Random();
				int r2 = rand2.nextInt(50);
				rightAnswer = wordsHard[r2].toLowerCase();

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
				lblText.setText(scrambledWord2.toUpperCase());
				break;

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