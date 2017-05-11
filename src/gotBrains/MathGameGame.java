package gotBrains;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.*;

/**
 * The panel-class that holds the actual gameUI and Game logic.
 * 
 * @author Isak Hartman, Felix Jönsson
 *
 */
public class MathGameGame extends JPanel implements ActionListener {
	private Controller controller;
	private MathGame mathGame;
	private CountDownTimer timer = new CountDownTimer(0, 30);
	private Font font = new Font("Calibri", Font.BOLD, 32);
	private Color fontColor = new Color(80, 80, 80);
	private int difficulty;
	private int score = 0;
	private Random random = new Random();
	private Action action;

	private JButton btnQuit = new JButton(new ImageIcon("images/quitButton.png"));
	private JButton btnMenu = new JButton(new ImageIcon("images/menuButton.png"));
	JTextField textField = new JTextField();

	private JLabel lblNbr1 = new JLabel("", SwingConstants.RIGHT);
	private JLabel lblNbr2 = new JLabel("", SwingConstants.LEFT);
	private JLabel lblOperation = new JLabel("", SwingConstants.CENTER);
	private JLabel lblScore = new JLabel("Score: " + score, SwingConstants.LEFT);
	private JLabel lblTimer = new JLabel("", SwingConstants.LEFT);

	/**
	 * 
	 * @param Controller
	 *            controller
	 */
	@SuppressWarnings("serial")
	public MathGameGame(Controller controller) {
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

		add(lblNbr1);
		lblNbr1.setFont(font);
		lblNbr1.setForeground(fontColor);
		lblNbr1.setBounds(320, 320, 60, 30);

		add(lblOperation);
		lblOperation.setFont(font);
		lblOperation.setForeground(fontColor);
		lblOperation.setBounds(380, 320, 30, 30);

		add(lblNbr2);
		lblNbr2.setFont(font);
		lblNbr2.setForeground(fontColor);
		lblNbr2.setBounds(410, 320, 60, 30);

		add(textField);
		textField.setOpaque(false);
		textField.setBorder(BorderFactory.createEmptyBorder());
		textField.setFont(new Font("Calibri", Font.PLAIN, 28));
		textField.setForeground(fontColor);
		textField.setBounds(265, 370, 250, 30);
		textField.addActionListener(action = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {

				int correctAnswer;
				int userAnswer = Integer.parseInt(textField.getText());

				System.out.println("Your answer: " + textField.getText());

				String operation = lblOperation.getText();
				switch (operation) {
				case "+":
					correctAnswer = Integer.parseInt(lblNbr1.getText()) + Integer.parseInt(lblNbr2.getText());
					if (Integer.toString(userAnswer).equals((Integer.toString(correctAnswer)))) {
						score++;
						mathGame.newTask();
						controller.correctSound(true);
					} else controller.correctSound(false);
					break;
				case "-":
					correctAnswer = Integer.parseInt(lblNbr1.getText()) - Integer.parseInt(lblNbr2.getText());
					if (Integer.toString(userAnswer).equals((Integer.toString(correctAnswer)))) {
						score++;
						mathGame.newTask();
						controller.correctSound(true);
					} else controller.correctSound(false);
					break;
				case "*":
					correctAnswer = Integer.parseInt(lblNbr1.getText()) * Integer.parseInt(lblNbr2.getText());
					if (Integer.toString(userAnswer).equals((Integer.toString(correctAnswer)))) {
						score++;	
						mathGame.newTask();
						controller.correctSound(true);
					} else controller.correctSound(false);
					break;
				}
				updateScore();
				textField.setText("");
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

		timer.start();
	}

	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}

	public void startLevel() {
		mathGame = new MathGame();
		textField.setEditable(true);
		mathGame.newTask();
	}

	public void updateScore() {
		lblScore.setText("Score: " + score);
	}
	
	public void playSound(boolean correct) {
		if(correct) {
			
		}
	}

	public void gameOver() {
		if (!timer.isInterrupted()) {
			timer.interrupt();
		}
		textField.setEditable(false);

		System.out.println("Your result: " + score * difficulty + " points.");
		controller.newMathGameScore(score * difficulty);
	}

	public void showResult(String result) {

	}

	protected void paintComponent(Graphics g) {
		int xPoints[] = { 280, 330, 470, 520 };
		int yPoints[] = { 0, 30, 30, 0 };
		int nPoints = 4;
		ImageIcon background = new ImageIcon("images/calculateThisBackground.png");
		super.paintComponent(g);
		g.drawImage(background.getImage(), 0, 0, null);
		g.setColor(new Color(80, 80, 80));
		g.fillPolygon(xPoints, yPoints, nPoints);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnMenu) {
			// avbryt aktuellt spel, nollställ timer, nollställ score.
			gameOver();
			controller.showMenu();
		} else if (e.getSource() == btnQuit) {
			System.exit(0);
		}
	}

	private class MathGame {
		public void newTask() {
			int range;
			switch (difficulty) {
			case 1:
				int tempChar1 = random.nextInt(2) + 1;
				if (tempChar1 == 1) {
					lblOperation.setText("+");
					range = 9;
				} else {
					lblOperation.setText("-");
					range = 9;
				}
				lblNbr1.setText(Integer.toString(random.nextInt(range) + 1));
				lblNbr2.setText(Integer.toString(random.nextInt(range) + 1));
				break;
			case 5:
				int tempChar5 = random.nextInt(3) + 1;
				if (tempChar5 == 1) {
					lblOperation.setText("+");
					range = 99;
				} else if (tempChar5 == 2) {
					lblOperation.setText("-");
					range = 99;
				} else {
					lblOperation.setText("*");
					range = 9;
				}
				lblNbr1.setText(Integer.toString(random.nextInt(range) + 1));
				lblNbr2.setText(Integer.toString(random.nextInt(range) + 1));
				break;
			case 10:
				int tempChar10 = random.nextInt(3) + 1;
				if (tempChar10 == 1) {
					lblOperation.setText("+");
					range = 999;
				} else if (tempChar10 == 2) {
					lblOperation.setText("-");
					range = 999;
				} else {
					lblOperation.setText("*");
					range = 99;
				}
				lblNbr1.setText(Integer.toString(random.nextInt(range) + 1));
				lblNbr2.setText(Integer.toString(random.nextInt(range) + 1));
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
				lblTimer.setText(toString());
				try {
					Thread.sleep(999);
				} catch (InterruptedException e) {
					System.out.println("Timer thread was interrupted!");
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