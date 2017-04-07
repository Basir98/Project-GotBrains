package gotBrains;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.*;

public class MathGameGame extends JPanel implements ActionListener {
	private Controller controller;
	private MathGameEasy mge;
	private Font font = new Font("Calibri", Font.BOLD, 32);
	private Color fontColor = new Color(80, 80, 80);
	private String difficulty = "";
	private int score = 0;
	private Random random = new Random();
	private Action action;

	private ImageIcon iconQuit = new ImageIcon("images/quitButton.png");
	private ImageIcon iconQuitHover = new ImageIcon("images/quitButtonHover.png");
	private ImageIcon iconMenu = new ImageIcon("images/menuButton.png");
	private ImageIcon iconMenuHover = new ImageIcon("images/menuButtonHover.png");
	private JButton btnQuit = new JButton(iconQuit);
	private JButton btnMenu = new JButton(iconMenu);
	JTextField textField = new JTextField();

	private JLabel lblNbr1 = new JLabel("", SwingConstants.RIGHT);
	private JLabel lblNbr2 = new JLabel("", SwingConstants.LEFT);
	private JLabel lblOperation = new JLabel("", SwingConstants.CENTER);
	private JLabel lblScore = new JLabel("Score: " + score, SwingConstants.LEFT);
	private JLabel lblTimer = new JLabel("", SwingConstants.LEFT);

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
		btnQuit.setRolloverIcon(iconQuitHover);

		add(btnMenu);
		btnMenu.setOpaque(false);
		btnMenu.setContentAreaFilled(false);
		btnMenu.setBorderPainted(false);
		btnMenu.setBounds(-2, -2, 120, 30);
		btnMenu.addActionListener(this);
		btnMenu.setRolloverIcon(iconMenuHover);

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
		textField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		textField.setFont(new Font("Calibri", Font.PLAIN, 28));
		textField.setForeground(fontColor);
		textField.setBounds(265, 370, 250, 30);
		textField.addActionListener(action = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				
				int correctAnswer1 = Integer.parseInt(lblNbr1.getText()) + Integer.parseInt(lblNbr2.getText());
				int correctAnswer2 = Integer.parseInt(lblNbr1.getText()) - Integer.parseInt(lblNbr2.getText());
				System.out.println(correctAnswer1 + ", " + correctAnswer2);
				System.out.println(textField.getText());
				
				int userAnswer = Integer.parseInt(textField.getText());
				
				if(Integer.toString(userAnswer).equals((Integer.toString(correctAnswer1))) || Integer.toString(userAnswer).equals((Integer.toString(correctAnswer2)))) {
					score++;
					updateScore();
					textField.setText("");
					mge.newTask();
				} else {
					textField.setText("");
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

		mge = new MathGameEasy();
		mge.newTask();

		CountDownTimer timer = new CountDownTimer(0, 10);
		timer.start();

	}	
	
	public void updateScore() {
		lblScore.setText("Score: " + score);
	}
	
	public void gameOver() {
		controller.showMathGameWindow();
	}

	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}

	protected void paintComponent(Graphics g) {
		ImageIcon background = new ImageIcon("images/mathGameGameBackground.png");
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
	
	private class MathGameEasy {
		public void newTask() {
			lblNbr1.setText(Integer.toString(random.nextInt(9) + 1));
			lblNbr2.setText(Integer.toString(random.nextInt(9) + 1));
			int tempChar = random.nextInt(2) + 1;

			if (tempChar == 1) {
				lblOperation.setText("+");
			} else if (tempChar == 2) {
				lblOperation.setText("-");
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
//				System.out.println(toString());
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