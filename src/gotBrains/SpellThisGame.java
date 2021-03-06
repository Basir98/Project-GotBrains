package gotBrains;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

/**
 * A class that holds the game UI and the game logic.
 *
 * @author Alper Kilic, Farid Razwan, Isak Hartman
 */
public class SpellThisGame extends JPanel implements ActionListener {
    private String rightAnswer;
    private Controller controller;
    CountDownTimer timer;
    private SpellThis spellThis;
    private Font font = new Font("Calibri", Font.BOLD, 32);
    private Color fontColor = new Color(80, 80, 80);
    private Color darkGrey = new Color(80, 80, 80);
    private Color lightGrey = new Color(180, 180, 180);
    private int difficulty;
    private int score = 0;
    private LinkedList<String> wordsEasy;
    private LinkedList<String> wordsMedium;
    private LinkedList<String> wordsHard;
    private Random rand = new Random();
    private Action action;
    private int hintsLeft = 3;

    private JButton btnQuit = new JButton(new ImageIcon("images/quitButton.png"));
    private JButton btnMinimize = new JButton(new ImageIcon("images/minimizeButton.png"));
    private JButton btnMenu = new JButton(new ImageIcon("images/menuButton.png"));
    private JButton btnRestart = new JButton(new ImageIcon("images/restartButton.png"));
    private JButton btnJumpOver = new JButton(new ImageIcon("images/jumpOver.png"));
    private JButton btnToggleMusic = new JButton(new ImageIcon("images/musicIcon.png"));
    private JButton btnToggleSound = new JButton(new ImageIcon("images/soundIcon.png"));
    JTextField textField = new JTextField();
    private JTextArea gameLog = new JTextArea();
    private JScrollPane logScroll;
    
    private boolean mutedMusic = false;
    private boolean mutedSound = false;

    private JLabel lblText = new JLabel("", SwingConstants.CENTER);
    private JLabel lblScore = new JLabel("Score: " + score, SwingConstants.LEFT);
    private JLabel lblTimer = new JLabel("", SwingConstants.CENTER);
    private JLabel lblEnterIcon = new JLabel(new ImageIcon("images/enterIcon.png"));
    private JLabel lblHintButton = new JLabel("HINT " + hintsLeft + "/" + hintsLeft, SwingConstants.CENTER);
    private JLabel lblHint = new JLabel("", SwingConstants.CENTER);

    private JSlider musicVolumeSlider = new JSlider(JSlider.VERTICAL, 0, 35, 35);
    private JSlider soundVolumeSlider = new JSlider(JSlider.VERTICAL, 0, 50, 50);

    @SuppressWarnings("serial")
    public SpellThisGame(Controller controller) {
        this.controller = controller;
        setLayout(null);
        setPreferredSize(new Dimension(800, 600));

        add(btnQuit);
        controller.exitBtnFilter(btnQuit);
        btnQuit.addActionListener(this);

        add(btnMinimize);
        controller.miniBtnFilter(btnMinimize);
        btnMinimize.addActionListener(this);

        add(btnMenu);
        controller.mainMenuBtnFilter(btnMenu);
        btnMenu.addActionListener(this);
        
        add(btnToggleMusic);
        btnToggleMusic.setContentAreaFilled(false);
        btnToggleMusic.setBorderPainted(false);
        btnToggleMusic.setBounds(5, 560, 32, 32);
        btnToggleMusic.addActionListener(this);
        btnToggleMusic.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                musicVolumeSlider.setVisible(true);
                soundVolumeSlider.setVisible(false);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                musicVolumeSlider.setVisible(false);
            }
        });

        add(btnToggleSound);
        btnToggleSound.setContentAreaFilled(false);
        btnToggleSound.setBorderPainted(false);
        btnToggleSound.setBounds(40, 560, 32, 32);
        btnToggleSound.addActionListener(this);
        btnToggleSound.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                musicVolumeSlider.setVisible(false);
                soundVolumeSlider.setVisible(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                soundVolumeSlider.setVisible(false);
            }
        });

        add(musicVolumeSlider);
        musicVolumeSlider.setBounds(8, 460, 32, 100);
        musicVolumeSlider.setOpaque(false);
        musicVolumeSlider.addChangeListener(changeEvent -> controller.setMusicVolume(musicVolumeSlider.getValue()));
        musicVolumeSlider.setVisible(false);
        musicVolumeSlider.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                musicVolumeSlider.setVisible(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                musicVolumeSlider.setVisible(false);
            }
        });

        add(soundVolumeSlider);
        soundVolumeSlider.setBounds(40, 460, 32, 100);
        soundVolumeSlider.setOpaque(false);
        soundVolumeSlider.addChangeListener(changeEvent -> controller.setSoundVolume(soundVolumeSlider.getValue()));
        soundVolumeSlider.setVisible(false);
        soundVolumeSlider.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                soundVolumeSlider.setVisible(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                soundVolumeSlider.setVisible(false);
            }
        });

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
                    spellThis.newTask();
                }
            }
        });

        add(btnRestart);
        btnRestart.setOpaque(false);
        btnRestart.setContentAreaFilled(false);
        btnRestart.setBorderPainted(false);
        btnRestart.setFocusPainted(false);
        btnRestart.setBounds(576, 325, 220, 40);
        btnRestart.addActionListener(this);
        btnRestart.setRolloverIcon(new ImageIcon("images/restartButtonHover.png"));

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
        lblTimer.setBounds(315, 2, 160, 30);

        add(lblHintButton);
        lblHintButton.setFont(new Font("Calibri", Font.PLAIN, 24));
        lblHintButton.setForeground(Color.BLACK);
        lblHintButton.setBounds(324, 530, 140, 40);
        lblHintButton.setBorder(new RoundedLineBorder(Color.BLACK, 2, 10, true));
        lblHintButton.setHorizontalAlignment(JLabel.CENTER);
        lblHintButton.setVerticalAlignment(JLabel.CENTER);
        lblHintButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                getHint();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                lblHintButton.setForeground(Color.darkGray);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                lblHintButton.setForeground(Color.BLACK);
            }
        });


        add(textField);
        textField.setOpaque(false);
        textField.setBorder(BorderFactory.createEmptyBorder());
        textField.setDocument(new LengthRestrictedDocument(15));
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setFont(new Font("Calibri", Font.PLAIN, 28));
        textField.setForeground(fontColor);
        textField.setBounds(300, 370, 190, 30);
        textField.addActionListener(action());

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

        add(lblHint);
        lblHint.setFont(font);
        lblHint.setForeground(new Color(48, 108, 48));
        lblHint.setBounds(186, 260, 400, 40);

        timer = new CountDownTimer(2, 0);
        timer.start();
    }

    private void getHint() {
        controller.buttonSound();
        String text = "" + rightAnswer.charAt(0);
        for (int i = 1; i < rightAnswer.length(); i++) {
            text += " _";
        }
        lblHint.setText(text);
        hintsLeft -= 1;
        lblHintButton.setText("HINT " + hintsLeft + "/3");
        lblHintButton.setVisible(false);
    }

    public Action action() { //Method that gives points and checks if the User types in the right answer.
        action = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                if (!textField.getText().equals("")) {
                    String correctAnswer = rightAnswer;
                    String userAnswer = textField.getText().toUpperCase();

                    System.out.println("Your answer: " + textField.getText());

                    // String right = rightAnswer;
                    // switch (right) {
                    // case "":
                    if ((userAnswer).equals(correctAnswer)) {
                        score += difficulty;
                        controller.correctSound(true);
                        gameLog.append("Correct!\n");
                        spellThis.newTask();
                        if(hintsLeft > 0) {
                            lblHintButton.setVisible(true);
                        }
                        lblHint.setText("");
                    } else {
                        controller.correctSound(false);
                        gameLog.append("Incorrect, try again!\n");
                    }
                    updateScore();
                    textField.setText("");

                }
            }
        };
        return action;
    }

    public void updateScore() { //Updates the score
        lblScore.setText("Score: " + score);
    }

    public void gameOver() { //Method that stops the game when the time is up.
        textField.setEditable(false);
        textField.setText("");
        gameLog.append("\nGame over, time's up!\n" + "Your result: " + score + " point(s).\n");
        controller.newSpellThisScore(score);
        timer.interrupt();
    }

    public void restart() { //Method that restarts the game when the user presses the button "restart".
        controller.newSpellThisScore(score);
        textField.setText("");
        timer.interrupt();
        this.score = 0;
        updateScore();
        gameLog.append("\n____________________________\n\nRound restarted. \n\n");
        timer = new CountDownTimer(2, 0);
        timer.start();
        spellThis = new SpellThis();
        loadWords();
        spellThis = new SpellThis();
        textField.setEditable(true);
        spellThis.newTask();
        textField.grabFocus();
        lblTimer.setForeground(lightGrey);
        hintsLeft = 3;
        lblHintButton.setVisible(true);
        lblHintButton.setText("HINT " + hintsLeft + "/" + hintsLeft);
        lblHint.setText("");
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public void startLevel() { //Writes out the difficulty in the gamelog and starts the game.
        String difficultyStr = "No";
        if (this.difficulty == 5)
            difficultyStr = "Easy";
        if (this.difficulty == 10)
            difficultyStr = "Medium";
        if (this.difficulty == 20)
            difficultyStr = "Hard";
        gameLog.append(difficultyStr + " difficulty chosen.\n");
        gameLog.append("Every correct answer is " + "\nworth " + difficulty + " point(s).\n\n");
        loadWords();
        spellThis = new SpellThis();
        textField.setEditable(true);
        spellThis.newTask();
        textField.grabFocus();

    }

    public void loadWords() { //Gets the words from the class SpellThisWords.
        SpellThisWords wordsList = new SpellThisWords();
        wordsEasy = wordsList.getEasyWords();
        wordsMedium = wordsList.getMediumWords();
        wordsHard = wordsList.getHardWords();
    }

    protected void paintComponent(Graphics g) { //The gameUI.
        ImageIcon background = new ImageIcon("images/spellThisBackground.png");
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
        } else if (e.getSource() == btnToggleMusic) {
        controller.buttonSound();
        controller.toggleMusic();
        if (!mutedMusic) {
            btnToggleMusic.setIcon(new ImageIcon("images/musicIconMuted.png"));
            mutedMusic = true;
        } else if (mutedMusic) {
            btnToggleMusic.setIcon(new ImageIcon("images/musicIcon.png"));
            mutedMusic = false;
        }
    } else if (e.getSource() == btnToggleSound) {
        controller.buttonSound();
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

    private class SpellThis {
        int questionNbr = 1;

        public void newTask() {

            switch (difficulty) {
                case 5:
                    /*
                     * rightAnswer används istället för att hela tiden hämta
                     * wordsEasy[r] varje gång (optimering).
                     */
                    Collections.shuffle(wordsEasy);

                    // För att visa att orden tas ur listan för att undvika att de
                    // kommer flera gånger
                    rightAnswer = wordsEasy.poll().toUpperCase();
//                    System.out.println(rightAnswer);
//                    System.out.println(wordsEasy.size());

                    // Create a new char array with the size of the random word.
                    char[] chars1 = new char[rightAnswer.length()];

                    // Populate the char array.
                    for (int i = 0; i < rightAnswer.length(); i++) {
                        chars1[i] = rightAnswer.charAt(i);
                    }
                    String scrambledWord1;
                    do {
                        for (int i = 0; i < chars1.length; i++) {
                            int randomPosition = rand.nextInt(chars1.length);
                            char temp = chars1[i];
                            chars1[i] = chars1[randomPosition];
                            chars1[randomPosition] = temp;
                        }
                        // Save the scrambled word in a new string.
                        scrambledWord1 = new String(chars1);
                    } while (rightAnswer.equals(scrambledWord1));
                    lblText.setText(scrambledWord1);
                    break;

                case 10:
                    Collections.shuffle(wordsMedium);

                    rightAnswer = wordsMedium.poll().toUpperCase();
                    System.out.println(rightAnswer);
                    System.out.println(wordsMedium.size());

                    char[] chars2 = new char[rightAnswer.length()];

                    for (int i = 0; i < rightAnswer.length(); i++) {
                        chars2[i] = rightAnswer.charAt(i);
                    }
                    String scrambledWord2;
                    do {
                        for (int i = 0; i < chars2.length; i++) {
                            int randomPosition = rand.nextInt(chars2.length);
                            char temp = chars2[i];
                            chars2[i] = chars2[randomPosition];
                            chars2[randomPosition] = temp;
                        }
                        scrambledWord2 = new String(chars2);
                    } while (rightAnswer.equals(scrambledWord2));
                    lblText.setText(scrambledWord2);
                    break;
                case 20:
                    Collections.shuffle(wordsHard);

                    rightAnswer = wordsHard.poll().toUpperCase();
                    System.out.println(rightAnswer);
                    System.out.println(wordsHard.size());

                    char[] chars3 = new char[rightAnswer.length()];

                    for (int i = 0; i < rightAnswer.length(); i++) {
                        chars3[i] = rightAnswer.charAt(i);
                    }
                    String scrambledWord3;
                    do {
                        for (int i = 0; i < chars3.length; i++) {
                            int randomPosition = rand.nextInt(chars3.length);
                            char temp = chars3[i];
                            chars3[i] = chars3[randomPosition];
                            chars3[randomPosition] = temp;
                        }
                        scrambledWord3 = new String(chars3);
                    } while (rightAnswer.equals(scrambledWord3));
                    lblText.setText(scrambledWord3);
                    break;

            }
            gameLog.append("Question " + questionNbr + ":\n");
            questionNbr++;

        }
    }

    /**
     * A private class for the timer.
     *
     * @author Alper Kilic, Farid Razwan
     */
    private class CountDownTimer extends Thread {
        private int minutes;
        private int seconds;
        private boolean ticking = false;

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

                    if(!ticking && minutes == 0 && seconds < 11) {
                        controller.tickingSound();
                        lblTimer.setForeground(Color.RED);
                        ticking = true;
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

        public String toString() {
            return minutes + " min" + ", " + seconds + " sec";
        }
    }

    public void setMusicVolumeSlider(int volume) {
        musicVolumeSlider.setValue(volume);
    }

    public void setSoundVolumeSlider(int volume) {
        soundVolumeSlider.setValue(volume);
    }

    public class RoundedLineBorder extends AbstractBorder {
        int lineSize, cornerSize;
        Paint fill;
        Stroke stroke;
        private Object aaHint;

        public RoundedLineBorder(Paint fill, int lineSize, int cornerSize) {
            this.fill = fill;
            this.lineSize = lineSize;
            this.cornerSize = cornerSize;
            stroke = new BasicStroke(lineSize);
        }
        public RoundedLineBorder(Paint fill, int lineSize, int cornerSize, boolean antiAlias) {
            this.fill = fill;
            this.lineSize = lineSize;
            this.cornerSize = cornerSize;
            stroke = new BasicStroke(lineSize);
            aaHint = antiAlias? RenderingHints.VALUE_ANTIALIAS_ON: RenderingHints.VALUE_ANTIALIAS_OFF;
        }

        @Override
        public Insets getBorderInsets(Component c, Insets insets) {
            int size = Math.max(lineSize, cornerSize);
            if(insets == null) insets = new Insets(size, size, size, size);
            else insets.left = insets.top = insets.right = insets.bottom = size;
            return insets;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2d = (Graphics2D)g;
            Paint oldPaint = g2d.getPaint();
            Stroke oldStroke = g2d.getStroke();
            Object oldAA = g2d.getRenderingHint(RenderingHints.KEY_ANTIALIASING);
            try {
                g2d.setPaint(fill!=null? fill: c.getForeground());
                g2d.setStroke(stroke);
                if(aaHint != null) g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, aaHint);
                int off = lineSize >> 1;
                g2d.drawRoundRect(x+off, y+off, width-lineSize, height-lineSize, cornerSize, cornerSize);
            }
            finally {
                g2d.setPaint(oldPaint);
                g2d.setStroke(oldStroke);
                if(aaHint != null) g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, oldAA);
            }
        }
    }


    // Test methods

    public void createMockGame(int score) {
        timer.decrease5();
        this.score = score;
        textField.setText("TESTTEXT");
    }

    public int getScore() {
        return score;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public JTextField getTextField() {
        return textField;
    }

    public void clickMenu() {
        btnMenu.doClick();
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public void inputString(String string) {
        textField.setText(string);
        textField.postActionEvent();
    }

    public int getTimerMinutes() {
        return timer.minutes;
    }

    public int getTimerSeconds() {
        return timer.seconds;
    }

    public void setTimerZero() {
        timer.seconds = 0;
        timer.minutes = 0;
    }

    public void clickSkip() {
        btnJumpOver.doClick();
    }

    public JButton getBtnRestart() {
        return btnRestart;
    }

    public JLabel getLblScore() {
        return lblScore;
    }

    public JButton getBtnJumpOver() {
        return btnJumpOver;
    }

    public JTextArea getGameLog() {
        return gameLog;
    }
}
