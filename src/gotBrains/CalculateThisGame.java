package gotBrains;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * The panel-class that holds the actual gameUI and Game logic.
 *
 * @author Isak Hartman, Felix Jönsson
 */
public class CalculateThisGame extends JPanel implements ActionListener {
    private Controller controller;
    private CalculateThis calculateThis;
    private CountDownTimer timer;
    private Font font = new Font("Calibri", Font.BOLD, 32);
    private Color darkGrey = new Color(80, 80, 80);
    private Color lightGrey = new Color(180, 180, 180);
    private int difficulty;
    private int score = 0;
    private Random random = new Random();
    private Action action;

    private JButton btnHelp = new JButton(new ImageIcon("images/helpIcon.png"));


    private JButton btnQuit = new JButton(new ImageIcon("images/quitButton.png"));
    private JButton btnMinimize = new JButton(new ImageIcon("images/minimizeButton.png"));
    private JButton btnMenu = new JButton(new ImageIcon("images/menuButton.png"));
    private JButton btnRestart = new JButton(new ImageIcon("images/restartButton.png"));
    private JButton btnJumpOver = new JButton(new ImageIcon("images/jumpOver.png"));
    JTextField textField = new JTextField();
    private JTextArea gameLog = new JTextArea();
    private JScrollPane logScroll;

    private JLabel lblNbr1 = new JLabel("", SwingConstants.RIGHT);
    private JLabel lblNbr2 = new JLabel("", SwingConstants.LEFT);
    private JLabel lblOperation = new JLabel("", SwingConstants.CENTER);
    private JLabel lblScore = new JLabel("Score: " + score, SwingConstants.LEFT);
    private JLabel lblTimer = new JLabel("", SwingConstants.CENTER);
    private JLabel lblEnterIcon = new JLabel(new ImageIcon("images/enterIcon.png"));

    /**
     * Places components and also starts the timer
     *
     * @param controller
     */
    public CalculateThisGame(Controller controller) {
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
        btnMenu.setBounds(4, 4, 120, 30);
        btnMenu.addActionListener(this);
        btnMenu.setRolloverIcon(new ImageIcon("images/menuButtonHover.png"));

        add(btnRestart);
        btnRestart.setOpaque(false);
        btnRestart.setContentAreaFilled(false);
        btnRestart.setBorderPainted(false);
        btnRestart.setFocusPainted(false);
        btnRestart.setBounds(576, 325, 220, 40);
        btnRestart.addActionListener(this);
        btnRestart.setRolloverIcon(new ImageIcon("images/restartButtonHover.png"));

        add(btnJumpOver);
        btnJumpOver.setOpaque(false);
        btnJumpOver.setContentAreaFilled(false);
        btnJumpOver.setBorderPainted(false);
        btnJumpOver.setFocusPainted(false);
        btnJumpOver.setBounds(365, 460, 60, 60);
        btnJumpOver.setRolloverIcon(new ImageIcon("images/jumpOverHover.png"));
        btnJumpOver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==btnJumpOver){
                    //next question AND decrease timer with 5 seconds
                    gameLog.append("Skips question!\n-5 seconds\n");
                    timer.decrease5();
                    textField.setText("");
                    calculateThis.newTask();
                }
            }
        });

        add(lblNbr1);
        lblNbr1.setFont(font);
        lblNbr1.setForeground(darkGrey);
        lblNbr1.setBounds(300, 320, 80, 30);

        add(lblOperation);
        lblOperation.setFont(font);
        lblOperation.setForeground(darkGrey);
        lblOperation.setBounds(385, 320, 30, 30);

        add(lblNbr2);
        lblNbr2.setFont(font);
        lblNbr2.setForeground(darkGrey);
        lblNbr2.setBounds(420, 320, 80, 30);

        add(textField);
        textField.setOpaque(false);
        textField.setBorder(BorderFactory.createEmptyBorder());
        textField.setDocument(new LengthRestrictedDocument(6));
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setFont(new Font("Calibri", Font.PLAIN, 28));
        textField.setForeground(darkGrey);
        textField.setBounds(300, 370, 190, 30);
        textField.addActionListener(gameAction());

        add(lblEnterIcon);
        lblEnterIcon.setBounds(490, 372, 24, 24);

        add(lblScore);
        lblScore.setFont(new Font("Calibri", Font.PLAIN, 24));
        lblScore.setForeground(lightGrey);
        lblScore.setBounds(355, 405, 200, 30);

        add(lblTimer);
        lblTimer.setFont(new Font("DejaVu Sans Mono", Font.PLAIN, 18));
        lblTimer.setForeground(lightGrey);
        lblTimer.setBounds(315, 2, 160, 30);

        add(gameLog);
        gameLog.setFont(new Font("Monospaced", Font.BOLD, 12));
        gameLog.setForeground(new Color(80, 80, 80));
        gameLog.setOpaque(false);
        gameLog.setBorder(BorderFactory.createEmptyBorder());
        gameLog.setEditable(false);
        gameLog.setSelectionColor(new Color(0, 0, 0, 0));
        gameLog.setBounds(0, 0, 190, 210);

        logScroll = new JScrollPane(gameLog);
        add(logScroll);
        logScroll.setOpaque(false);
        logScroll.getViewport().setOpaque(false);
        logScroll.setBorder(BorderFactory.createEmptyBorder());
        logScroll.setHorizontalScrollBar(null);
        logScroll.getVerticalScrollBar().setUI(new CustomScrollBarUI());
        logScroll.getVerticalScrollBar().setOpaque(false);
        logScroll.setBounds(590, 367, 206, 229);

        timer = new CountDownTimer(2, 0);
        timer.start();
    }

    /**
     * Sets the current difficulty to param difficulty
     *
     * @param difficulty
     */
    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    /**
     * Starts the game by instantiating the inner class CalculateThis. Let's the
     * user know what level is being played and how many points 1 correct answer
     * is worth.
     */
    public void startLevel() {
        String difficultyStr = "No";
        if (this.difficulty == 5)
            difficultyStr = "Easy";
        if (this.difficulty == 10)
            difficultyStr = "Medium";
        if (this.difficulty == 20)
            difficultyStr = "Hard";
        gameLog.append(difficultyStr + " difficulty chosen.\n");
        gameLog.append("Every correct answer is " + "\nworth " + difficulty + " point(s).\n\n");
        calculateThis = new CalculateThis();
        textField.setEditable(true);
        calculateThis.newTask();
    }

    /**
     * This method is triggered when the user presses "ENTER"-button in the
     * textfield. Figures out in the input was correct or incorrect, gives the
     * user the corresponding sound and info in gameLog and a new question if
     * answer were correct. Also manages the score count.
     *
     * @return
     */
    public Action gameAction() {
        action = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                if (!textField.getText().equals("")) {
                    try {
                        int userAnswer = Integer.parseInt(textField.getText());
                        int correctAnswer;

                        String operation = lblOperation.getText();
                        switch (operation) {
                            case "+":
                                correctAnswer = Integer.parseInt(lblNbr1.getText()) + Integer.parseInt(lblNbr2.getText());
                                if (Integer.toString(userAnswer).equals((Integer.toString(correctAnswer)))) {
                                    score += difficulty;
                                    controller.correctSound(true);
                                    gameLog.append("Correct!\n");
                                    calculateThis.newTask();
                                } else {
                                    controller.correctSound(false);
                                    gameLog.append("Incorrect, try again!\n");
                                }
                                break;
                            case "-":
                                correctAnswer = Integer.parseInt(lblNbr1.getText()) - Integer.parseInt(lblNbr2.getText());
                                if (Integer.toString(userAnswer).equals((Integer.toString(correctAnswer)))) {
                                    score += difficulty;
                                    controller.correctSound(true);
                                    gameLog.append("Correct!\n");
                                    calculateThis.newTask();
                                } else {
                                    controller.correctSound(false);
                                    gameLog.append("Incorrect, try again!\n");
                                }
                                break;
                            case "*":
                                correctAnswer = Integer.parseInt(lblNbr1.getText()) * Integer.parseInt(lblNbr2.getText());
                                if (Integer.toString(userAnswer).equals((Integer.toString(correctAnswer)))) {
                                    score += difficulty;
                                    controller.correctSound(true);
                                    gameLog.append("Correct!\n");
                                    calculateThis.newTask();
                                } else {
                                    controller.correctSound(false);
                                    gameLog.append("Incorrect, try again!\n");
                                }
                                break;
                        }
                        updateScore();
                        textField.setText("");
                    } catch (NumberFormatException nfE) {
                        gameLog.append("Please enter a number.\n");
                        controller.correctSound(false);
                        textField.setText("");
                    }
                }
            }
        };
        return action;
    }

    /**
     * This method is called to update the score label to current score.
     */
    public void updateScore() {
        lblScore.setText("Score: " + score);
    }

    /**
     * This method is called to stop the game, stop the timer and add the score
     * to the HighscoreManager class via controller.
     */
    public void gameOver() {
        textField.setEditable(false);
        textField.setText("");
        btnJumpOver.setEnabled(false);
        gameLog.append("\nGame over, time's up!\n" + "Your result: " + score + " point(s).\n\n");
        controller.newCalculateThisScore(score);
        timer.interrupt();
    }

    /**
     * This method is called to restart the game. Adds the score to
     * HighscoreManager class and resets the game.
     */
    public void restart() {
        controller.newCalculateThisScore(score);
        textField.setText("");
        timer.interrupt();
        this.score = 0;
        updateScore();
        gameLog.append("\n____________________________\n\nRound restarted. \n\n");
        timer = new CountDownTimer(2, 0);
        timer.start();
        calculateThis = new CalculateThis();
        textField.setEditable(true);
        calculateThis.newTask();
        textField.grabFocus();
        btnJumpOver.setEnabled(true);
    }

    /**
     * Paints the panel with figures and a background
     */
    protected void paintComponent(Graphics g) {
        ImageIcon background = new ImageIcon("images/calculateThisBackground.png");
        super.paintComponent(g);
        g.drawImage(background.getImage(), 0, 0, null);
        // Coordinates that are used in painting custom Polygons
        int x1Points[] = {275, 325, 465, 515};
        int y1Points[] = {0, 30, 30, 0};
        int y2Points[] = {400, 435, 435, 400};
        int nPoints = 4;
        g.setColor(darkGrey);
        g.fillPolygon(x1Points, y1Points, nPoints);
        g.fillPolygon(x1Points, y2Points, nPoints);

        // Sets the thickness of the stroke to 2 pixels.
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setStroke(new BasicStroke(3));
        g2.setPaint(darkGrey);
        g2.drawRect(270, 366, 250, 35);
        g2.drawRect(577, 365, 300, 300);

    }

    /**
     * Triggered when user presses a button
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnMenu) {
            controller.buttonSound();
            gameOver();
            controller.showMainMenu();

        } else if (e.getSource() == btnQuit) {
            controller.buttonSound();
            System.exit(0);

        } else if (e.getSource() == btnMinimize) {
            controller.buttonSound();
            controller.minimizeApp();

        } else if (e.getSource() == btnRestart) {
            controller.buttonSound();
            restart();
        }
    }

    /**
     * Inner private class that handles the new tasks for the game.
     *
     * @author Hartman
     */
    private class CalculateThis {
        int questionNbr = 1;

        /**
         * Gives the user a new task depending on the chosen difficulty
         */
        public void newTask() {
            int range;
            switch (difficulty) {
                case 5:
                    int tempChar1 = random.nextInt(2) + 1;
                    if (tempChar1 == 1) {
                        lblOperation.setText("+");
                        range = 9;
                    } else {
                        lblOperation.setText("-");
                        range = 9;
                    }
                    lblNbr1.setText(Integer.toString(random.nextInt(range)+1));
                    lblNbr2.setText(Integer.toString(random.nextInt(range)+1));
                    break;
                case 10:
                    int tempChar5 = random.nextInt(3) + 1;
                    System.out.println(tempChar5);
                    if (tempChar5 == 1) {
                        lblOperation.setText("+");
                        range = 79;
                        lblNbr1.setText(Integer.toString(random.nextInt(range) + 20));
                        lblNbr2.setText(Integer.toString(random.nextInt(range) + 20));
                    } else if (tempChar5 == 2) {
                        lblOperation.setText("-");
                        range = 79;
                        lblNbr1.setText(Integer.toString(random.nextInt(range) + 20));
                        lblNbr2.setText(Integer.toString(random.nextInt(range) + 20));
                    } else {
                        lblOperation.setText("*");
                        range = 5;
                        lblNbr1.setText(Integer.toString(random.nextInt(range)+5));
                        lblNbr2.setText(Integer.toString(random.nextInt(range)+5));
                    }
                    break;
                case 20:
                    int tempChar10 = random.nextInt(3) + 1;
                    if (tempChar10 == 1) {
                        lblOperation.setText("+");
                        range = 100;
                        lblNbr1.setText(Integer.toString(random.nextInt(range) + 100));
                        lblNbr2.setText(Integer.toString(random.nextInt(range) + 100));
                    } else if (tempChar10 == 2) {
                        lblOperation.setText("-");
                        range = 100;
                        lblNbr1.setText(Integer.toString(random.nextInt(range) + 100));
                        lblNbr2.setText(Integer.toString(random.nextInt(range) + 100));
                    } else {
                        lblOperation.setText("*");
                        range = 10;
                        lblNbr1.setText(Integer.toString(random.nextInt(range)+10));
                        lblNbr2.setText(Integer.toString(random.nextInt(range)+10));
                    }
                    break;

            }
            gameLog.append("Question " + questionNbr + ":\n");
            questionNbr++;
        }
    }

    /**
     * A  inner private Timer class that handles the game timer.
     *
     * @author Isak Hartman, Felix Jönsson
     */
    private class CountDownTimer extends Thread {
        private int minutes;
        private int seconds;

        public void decrease5(){
            if(seconds == 0) {
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            seconds -= 4; 
        }

        public CountDownTimer(int minutes, int seconds) {
            this.minutes = minutes;
            this.seconds = seconds;
        }

        public void run() {
            try {
                do {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            lblTimer.setText(timer.toString());
                        }
                    });
                    lblTimer.setText(toString());
                    Thread.sleep(999);

                    if (seconds <= 0) {
                        minutes--;
                        seconds = 59;
                    } else if (seconds > 0) {
                        seconds--;
                    }
                } while (minutes >= 0 && seconds >= 0);
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        controller.alarmSoundSound();
                        gameOver();
                    }
                });
            } catch (InterruptedException e) {
                System.out.println("Timer was interrupted.");
            }
        }

        /**
         * Returns the wanted String syntax.
         */
        public String toString() {
            return minutes + " min" + ", " + seconds + " sec";
        }
    }
}