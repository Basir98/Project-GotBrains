package gotBrains;

import javax.swing.*;



import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

public class MemorizeThisGame extends JPanel implements ActionListener, MouseListener {

    public static MemorizeThisGame memorizeThisGame;
    private JButton b1 = new JButton();
    public Renderer renderer;
    public static final int WIDTH = 300, HEIGHT = 300;
    public int flashed = 0, dark, ticks, indexPattern;
    public boolean creatingPattern = true;
    public ArrayList<Integer> pattern;
    public Random random;
    private boolean gameOver;
    private int totalScore;
    private int difficulty;
    private int pointPerRound; // Easy: 10, Medium: 15, Hard: 20
    private int round;
    
    private JScrollPane logScroll;
    private JTextArea gameLog = new JTextArea();
    
    private JLabel lblScore = new JLabel("Score: " + totalScore, SwingConstants.LEFT);
    private Color lightGrey = new Color(180, 180, 180);
    private Color darkGrey = new Color(80, 80, 80);

    private Controller controller;

    private JButton btnQuit = new JButton(new ImageIcon("images/quitButton.png"));
    private JButton btnMenu = new JButton(new ImageIcon("images/menuButton.png"));
    private JButton btnMinimize = new JButton(new ImageIcon("images/minimizeButton.png"));
    private JButton btnRestart = new JButton(new ImageIcon("images/restartButton.png"));
    
    public MemorizeThisGame(Controller controller) {
        this.controller = controller;
        memorizeThisGame = this;
        setLayout(null);
        setPreferredSize(new Dimension(800, 600));

        add(lblScore);
        lblScore.setFont(new Font("Calibri", Font.PLAIN, 24));
        lblScore.setForeground(lightGrey);
        lblScore.setBounds(355, 2, 200, 30);


        add(btnQuit);
        btnQuit.setOpaque(false);
        btnQuit.setContentAreaFilled(false);
        btnQuit.setBorderPainted(false);
        btnQuit.setBounds(758, 2, 40, 35);
        btnQuit.addActionListener((e) -> {
            if (e.getSource() == btnQuit) {
                System.exit(0);
            }
        });
        btnQuit.setRolloverIcon(new ImageIcon("images/quitButtonHover.png"));

        add(btnMinimize);
        btnMinimize.setOpaque(false);
        btnMinimize.setContentAreaFilled(false);
        btnMinimize.setBorderPainted(false);
        btnMinimize.setFocusPainted(false);
        btnMinimize.setBounds(716, 2, 40, 35);
        btnMinimize.addActionListener((e) -> {
            controller.buttonSound();
            controller.minimizeApp();
        });
        btnMinimize.setRolloverIcon(new ImageIcon("images/minimizeButtonHover.png"));

        add(btnMenu);
        btnMenu.setOpaque(false);
        btnMenu.setContentAreaFilled(false);
        btnMenu.setBorderPainted(false);
        btnMenu.setBounds(-2, -2, 120, 30);
        btnMenu.addActionListener((e) -> {
            if (e.getSource() == btnMenu) {
            	controller.newMemorizeThisScore(totalScore);
                controller.showMainMenu();
            }
        });
        btnMenu.setRolloverIcon(new ImageIcon("images/menuButtonHover.png"));

        add(btnRestart);
        btnRestart.setOpaque(false);
        btnRestart.setContentAreaFilled(false);
        btnRestart.setBorderPainted(false);
        btnRestart.setFocusPainted(false);
        btnRestart.setBounds(576, 325, 220, 40);
        btnRestart.addActionListener(this);
        btnRestart.setRolloverIcon(new ImageIcon("images/restartButtonHover.png"));
        
        Timer timer = new Timer(20, this);
        renderer = new Renderer();

        add(renderer);
        renderer.setOpaque(false);
        renderer.setSize(WIDTH + 8, HEIGHT + 30);
        renderer.setBounds(250, 200, WIDTH, HEIGHT);
        renderer.addMouseListener(this);
        renderer.setVisible(true);

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

        start();
        timer.start();
    }
    
    protected void paintComponent(Graphics g) { 	
        ImageIcon background = new ImageIcon("images/memorizeThisBackground.png");
        super.paintComponent(g);
        g.drawImage(background.getImage(), 0, 0, null);

        // Window for game info
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setStroke(new BasicStroke(3));
        g2.setPaint(darkGrey);
        g2.drawRect(577, 365, 300, 300);
        
        // Coordinates that are used in painting custom Polygons
        int x1Points[] = {275, 325, 465, 515};
        int y1Points[] = {0, 30, 30, 0};
        int nPoints = 4;
        g.setColor(darkGrey);
        g.fillPolygon(x1Points, y1Points, nPoints);
    }

    public void start() {
        lblScore.setText("Score: "+totalScore);
        random = new Random();
        pattern = new ArrayList<Integer>();
        indexPattern = 0;
        dark = 2;
        flashed = 0;
        ticks = 0;
    }

    public void setDifficulty(String difficulty) {
        if(difficulty.equals("Easy")){
            this.difficulty = 20; // Speed of the colorchanges
            this.pointPerRound = 10;

        }else if(difficulty.equals("Medium")){
            this.difficulty = 15;
            this.pointPerRound = 15;

        }else if(difficulty.equals("Hard")){
            this.difficulty = 10;
            this.pointPerRound = 20;
        }
        gameLog.append(difficulty + " difficulty chosen.\n");
        gameLog.append("Every correct answer is " + "\nworth " + pointPerRound + " point(s).\n\n");
    }

    public void restart() {
    	gameOver = false;
    	round = 0;
    	gameLog.append("\n____________________________\n\nRound restarted. \n\n");
    	controller.newMemorizeThisScore(totalScore);
    	this.totalScore = 0;
        lblScore.setText("Score: " + totalScore);
        start();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        ticks++;

     if (e.getSource() == btnRestart) {
        controller.buttonSound();
        System.out.println("Startar om");
        restart();
    }
        
        if (ticks % this.difficulty == 0) {
            flashed = 0;

            if (dark >= 0) {
                dark--;
            }
        }

        if (creatingPattern) {
            if (dark <= 0) {
                if (indexPattern >= pattern.size()) {
                    flashed = random.nextInt(40) % 4 + 1;
                    pattern.add(flashed);
                    indexPattern = 0;
                    creatingPattern = false;
                } else {
                    flashed = pattern.get(indexPattern);
                    indexPattern++;
                }

                dark = 2;
            }

        } else if (indexPattern == pattern.size()) {
            creatingPattern = true;
            indexPattern = 0;
            dark = 2;
            totalScore += pointPerRound;
            lblScore.setText("Score: "+totalScore);
            round++;
			gameLog.append("\nRound " + round  + " Completed");
        }

        renderer.repaint();
    }

    public void paint(Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (flashed == 1) {
            g.setColor(Color.GREEN);
        } else {
            g.setColor(Color.GREEN.darker());
        }

        g.fillRect(0, 0, WIDTH / 2, HEIGHT / 2);

        if (flashed == 2) {
            g.setColor(Color.RED);
        } else {
            g.setColor(Color.RED.darker());
        }

        g.fillRect(WIDTH / 2, 0, WIDTH / 2, HEIGHT / 2);

        if (flashed == 3) {
            g.setColor(Color.ORANGE);
        } else {
            g.setColor(Color.ORANGE.darker());
        }

        g.fillRect(0, HEIGHT / 2, WIDTH / 2, HEIGHT / 2);

        if (flashed == 4) {
            g.setColor(Color.BLUE);
        } else {
            g.setColor(Color.BLUE.darker());
        }

        g.fillRect(WIDTH / 2, HEIGHT / 2, WIDTH / 2, HEIGHT / 2);

        g.setColor(Color.BLACK);
        g.fillRoundRect(220, 220, 350, 350, 300, 300);
        g.fillRect(WIDTH / 2 - WIDTH / 12, 0, WIDTH / 7, HEIGHT);
        g.fillRect(0, WIDTH / 2 - WIDTH / 12, WIDTH, HEIGHT / 7);

        g.setColor(Color.GRAY);
        g.setStroke(new BasicStroke(200));
        g.drawOval(-100, -100, WIDTH + 200, HEIGHT + 200);

        g.setColor(Color.BLACK);
        g.setStroke(new BasicStroke(5));
        g.drawOval(0, 0, WIDTH, HEIGHT);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", 1, 25));

        if (gameOver) {
            g.drawString("Game-Over", WIDTH / 2 - 60, HEIGHT / 2 + 5);
            controller.newMemorizeThisScore(totalScore); // SK3 Highscores - "must" En användare ska få poäng utifrån sin prestation och svårighetsgrad.
        } else {
            g.drawString(indexPattern + "/" + pattern.size(), WIDTH / 2 - 20, HEIGHT / 2 + 10);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

        int x = e.getX(), y = e.getY();

        if (!creatingPattern && !gameOver) {
            if (x > 0 && x < WIDTH / 2 && y > 0 && y < HEIGHT / 2) {
                flashed = 1;
                ticks = 1;
            } else if (x > WIDTH / 2 && x < WIDTH && y > 0 && y < HEIGHT / 2) {
                flashed = 2;
                ticks = 1;
            } else if (x > 0 && x < WIDTH / 2 && y > HEIGHT / 2 && y < HEIGHT) {
                flashed = 3;
                ticks = 1;
            } else if (x > WIDTH / 2 && x < WIDTH && y > HEIGHT / 2 && y < HEIGHT) {
                flashed = 4;
                ticks = 1;
            }

            if (flashed != 0) {
                if (pattern.get(indexPattern) == flashed) {
                    indexPattern++;
                    controller.correctSound(true);
                } else {
                    gameOver = true;
                    controller.correctSound(false);
                }
            }

        } else if (gameOver) {
            totalScore = 0;
            start();
            gameOver = false;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}