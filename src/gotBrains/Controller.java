package gotBrains;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Main class that starts the application by creating all windows and shows the
 * menuWindow so that the user can choose what to do.
 *
 * @author Isak Hartman, Basir Ramazani
 */
public class Controller {

    private JFrame frame;
    private JPanel panelContainer = new JPanel();
    private MainMenu mainMenu;
    private SpellThisMenu spellThisMenu;
    private SpellThisGame spellThisGame;
    private MemorizeThisMenu memorizeThisMenu;
    private CalculateThisMenu calculateThisMenu;
    private CalculateThisGame calculateThisGame;
    private Leaderboard leaderboard;
    private MemorizeThisGame memorizeThisGame;
    private String currentUsername;
    private File backgroundMusic = new File("sounds/backgroundMusic.wav");
    private File correctSound = new File("sounds/correctSound.wav");
    private File incorrectSound = new File("sounds/incorrectSound.wav");
    private File alarmSound = new File("sounds/alarmSound.wav");
    private File buttonSound = new File("sounds/buttonSound.wav");
    private File tickingSound = new File("sounds/tickingSound.wav");
    private Clip music;
    private boolean mutedMusic = false;
    private boolean mutedSound = false;
    private HighscoreManager hm = new HighscoreManager();
    private CardLayout cl = new CardLayout();
    private Font customFont;

    private FloatControl musicGainControl;

    public int soundVolume = 50;
    public int musicVolume = 35;

    public Controller(JFrame frame) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        this.frame = frame;
        panelContainer.setLayout(cl);
        loadApp();
        startMusic();
    }

    /**
     * Instantiates the panels, adds them to the cardlayout-container, sets the
     * frame preferences and shows the menu
     */
    public void loadApp() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        mainMenu = new MainMenu(this);
        spellThisMenu = new SpellThisMenu(this);
        memorizeThisMenu = new MemorizeThisMenu(this);
        calculateThisMenu = new CalculateThisMenu(this);

        panelContainer.add(mainMenu, "mainMenu");
        panelContainer.add(spellThisMenu, "spellThisMenu");
        panelContainer.add(memorizeThisMenu, "memorizeThisWindow");
        panelContainer.add(calculateThisMenu, "calculateThisMenu");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panelContainer);
        frame.setSize(800, 600);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);
        frame.setUndecorated(true);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setIconImage(new ImageIcon("images/logo.png").getImage());  


        cl.show(panelContainer, "mainMenu");
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mainMenu.fieldUsername.grabFocus();

        System.out.println(backgroundMusic);
        AudioInputStream ais = AudioSystem.getAudioInputStream(backgroundMusic);
        music = AudioSystem.getClip();
        music.open(ais);
        musicGainControl = (FloatControl) music.getControl(FloatControl.Type.MASTER_GAIN);
        musicGainControl.setValue(-15.0f);
    }

    /**
     * Returns a custom font using filepath, font style and font size
     *
     * @param filepath
     * @param style
     * @param size
     * @return Font
     */
    public Font getCustomFont(String filepath, int style, int size) {
        try {
            customFont = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(new File(filepath))).deriveFont(style,
                    size);
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

    /**
     * Starts the background music, loops it 100 times (~60 minutes)
     */
    public void startMusic() {
        try {
            music.start();
            music.loop(100);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Toggles the mutedMusic variable between muted and unmuted.
     */
    public void toggleMusic() {
        if (!mutedMusic) {
            music.stop();
            mutedMusic = true;
        } else if (mutedMusic) {
            startMusic();
            mutedMusic = false;
        }
    }

    public void setMusicVolume(int volume) {
        if(volume == 0) {
            musicGainControl.setValue(musicGainControl.getMinimum());
        } else {
            float fVolume = volume;
            musicGainControl.setValue(-50.0f + volume);
        }
        musicVolume = volume;
    }

    public void setSoundVolume(int volume) {
        soundVolume = volume;
    }

    public float generateFloatVolume() {
        if(soundVolume == 0) {
            return 0.0f;
        } else {
            return -50.0f + soundVolume;
        }
    }

    /**
     * Toggles the mutedSound variable between muted and unmuted.
     */
    public void toggleSound() {
        if (mutedSound) {
            mutedSound = false;
        } else
            mutedSound = true;
    }

    /**
     * If sound is not muted the following is executed. If param is true, the
     * correctSound is played. Else if false, the incorrectSound is played.
     *
     * @param correct
     */
    public void correctSound(boolean correct) {
        if (!mutedSound) {
            try {
                if (correct) {
                    AudioInputStream ais = AudioSystem.getAudioInputStream(correctSound);
                    Clip sound = AudioSystem.getClip();
                    sound.open(ais);
                    FloatControl gainControl = (FloatControl) sound.getControl(FloatControl.Type.MASTER_GAIN);
                    gainControl.setValue(generateFloatVolume());
                    sound.start();
                } else if (!correct) {
                    AudioInputStream ais = AudioSystem.getAudioInputStream(incorrectSound);
                    Clip sound = AudioSystem.getClip();
                    sound.open(ais);
                    FloatControl gainControl = (FloatControl) sound.getControl(FloatControl.Type.MASTER_GAIN);
                    gainControl.setValue(generateFloatVolume());
                    sound.start();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    /**
     * Plays an alarmsound if the sound is not muted.
     */
    public void alarmSoundSound() {
        if (!mutedSound) {
            try {
                AudioInputStream ais = AudioSystem.getAudioInputStream(alarmSound);
                Clip sound = AudioSystem.getClip();
                sound.open(ais);
                FloatControl gainControl = (FloatControl) sound.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(generateFloatVolume());
                sound.start();

            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    /**
     * Plays a buttonSound if the sound is not muted.
     */
    public void buttonSound() {
        if (!mutedSound) {
            try {
                AudioInputStream ais = AudioSystem.getAudioInputStream(buttonSound);
                Clip sound = AudioSystem.getClip();
                sound.open(ais);
                FloatControl gainControl = (FloatControl) sound.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(generateFloatVolume());
                sound.start();

            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    /**
     * Plays a tickingSound if the sound is not muted.
     */
    public void tickingSound() {
        if (!mutedSound) {
            try {
                AudioInputStream ais = AudioSystem.getAudioInputStream(tickingSound);
                Clip sound = AudioSystem.getClip();
                sound.open(ais);
                FloatControl gainControl = (FloatControl) sound.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(generateFloatVolume());
                sound.start();

            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    /**
     * Shows the SpellThisMenu
     */
    public void showSpellThisMenu() {
        cl.show(panelContainer, "spellThisMenu");
    }

    /**
     * Shows the MemorizeThisWindow
     */
    public void showMemorizeThisWindow() {
        cl.show(panelContainer, "memorizeThisWindow");
    }

    /**
     * Shows the CalculateThisMenu
     */
    public void showCalculateThisMenu() {
        cl.show(panelContainer, "calculateThisMenu");
    }

    /**
	 * Changed from Simon to MemorizeThis
     * Shows the MemorizeThis Panel
	 * Matilda and Andreas
     */
    public void startMemorizeThisGame(String difficulty) {
        memorizeThisGame = new MemorizeThisGame(this);
        panelContainer.add(memorizeThisGame, "memorizeThisGame");

        memorizeThisGame.setDifficulty(difficulty);
        memorizeThisGame.start();

        cl.show(panelContainer, "memorizeThisGame");

        memorizeThisGame.setSoundVolumeSlider(soundVolume);
        memorizeThisGame.setMusicVolumeSlider(musicVolume);
    }

    /**
     * Shows the SpellThisGame panel, grabs focus on the textField  and instantiates the Game
     *
     * @param difficulty
     */
    public void startSpellThisGame(int difficulty) {
        spellThisGame = new SpellThisGame(this);
        panelContainer.add(spellThisGame, "spellThisGame");

        spellThisGame.setDifficulty(difficulty);
        spellThisGame.startLevel();
        cl.show(panelContainer, "spellThisGame");
        spellThisGame.textField.grabFocus();

        spellThisGame.setSoundVolumeSlider(soundVolume);
        spellThisGame.setMusicVolumeSlider(musicVolume);
    }

    /**
     * Shows the CalculateThisGame panel, grabs focus on the textField  and instantiates the Game
     *
     * @param difficulty
     */
    public void startCalculateThisGame(int difficulty) {
        calculateThisGame = new CalculateThisGame(this);
        panelContainer.add(calculateThisGame, "calculateThisGame");

        calculateThisGame.setDifficulty(difficulty);
        calculateThisGame.startLevel();
        cl.show(panelContainer, "calculateThisGame");
        calculateThisGame.textField.grabFocus();

        calculateThisGame.setSoundVolumeSlider(soundVolume);
        calculateThisGame.setMusicVolumeSlider(musicVolume);
    }

    /**
     * Shows the Leaderboard panel
     */
    public void showLeaderboard() {
        leaderboard = new Leaderboard(this);
        panelContainer.add(leaderboard, "leaderboard");
        cl.show(panelContainer, "leaderboard");
    }

    /**
     * Shows the start screen, the MainMenu
     */
    public void showMainMenu() {
        cl.show(panelContainer, "mainMenu");
        mainMenu.fieldUsername.grabFocus();
        mainMenu.setSoundVolumeSlider(soundVolume);
        mainMenu.setMusicVolumeSlider(musicVolume);
    }

    /**
     * Minimizes the application to the taskbar
     */
    public void minimizeApp() {
        frame.setState(JFrame.ICONIFIED);
    }

    /**
     * Sets the currentUsername to param username
     *
     * @param username
     */
    public void setCurrentUsername(String username) {
        this.currentUsername = username;
    }

    public String getCurrentUsername() {
        return this.currentUsername;
    }

    /**
     * Adds the player to the HighscoreManager class.
     *
     * @param username
     */
    public void addPlayer(String username) {
        hm.addPlayer(username);
    }

    /**
     * Returns the leaderboard placement
     *
     * @return
     */
    public String getLeaderboardPlacement() {
        return hm.getLeaderboardPlacement();
    }

    /**
     * returns the leaderboard scores
     *
     * @return
     */
    public String getLeaderboardScore() {
        return hm.getLeaderboardScore();
    }

    /**
     * Clears the leaderboard by clearing the scores.dat via HighscoreManager
     * class
     */
    public void clearLeaderboard() {
        hm.clearScores();
    }

    /**
     * Adds the players score to the HighscoreManager class
     *
     * @param score
     */
    public void newCalculateThisScore(int score) {
        hm.addCalculateThisScore(this.currentUsername, score);
    }

    /**
     * Adds the players score to the HighscoreManager class
     *
     * @param score
     */
    public void newSpellThisScore(int score) {
        hm.addSpellThisScore(this.currentUsername, score);
    }

    /**
     * Adds the players score to the HighscoreManager class
     *
     * @param score
     */
    public void newMemorizeThisScore(int score) {
        hm.addMemorizeThisScore(this.currentUsername, score);
    }
    
    /**
     * Info panel for every game
     * @param txtArea
     * @param lbl
     */
    public void getInfo(JTextArea txtArea, JLabel lbl) {
    	JFrame frame = new JFrame();
    	
    	JPanel pnl = new JPanel();
    	JPanel pnlLeft = new JPanel();
    	pnlLeft.add(new JLabel());
    	
    	txtArea.setFont(new Font("Calibri", Font.ITALIC, 16));
        txtArea.setLineWrap(true);
        txtArea.setWrapStyleWord(true);
        txtArea.setOpaque(false);
        txtArea.setEditable(false);
    	
		lbl.setFont(new Font("Calibri", Font.PLAIN, 18));
		lbl.setForeground(Color.RED);
    	
    	pnl.setLayout(new BorderLayout());
    	pnl.add(lbl, BorderLayout.NORTH);
    	pnl.add(txtArea, BorderLayout.CENTER);
    	pnl.add(pnlLeft, BorderLayout.WEST);
    	
     	ImageIcon imgFile = new ImageIcon("images/logo.png");
        Image imgfile = imgFile.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        imgFile = new ImageIcon(imgfile);     
//    	frame.setContentPane(new JLabel(imgFile));
//    	frame.add(new JLabel(imgFile), BorderLayout.CENTER);
    	pnl.add(new JLabel(imgFile), BorderLayout.SOUTH);
    	
    	frame.add(pnl);
    	frame.setTitle("Info");
    	frame.setVisible(true);
    	frame.setSize(330, 200);
    	frame.setLocationRelativeTo(null);
    	frame.setResizable(false);
    	frame.setIconImage(new ImageIcon("images/iconf_info.png").getImage());
    }
    
    /**
     * info panel in the main screen
     */
    public void mainInfoPanel() {
    	JFrame frame = new JFrame();
    	JPanel pnl = new JPanel();
    	JPanel pnlCenter = new JPanel();
    
    	pnlCenter.setLayout(new GridLayout(8,1));
    	pnl.setLayout(new BorderLayout());
    	
    	pnl.add(pnlCenter, BorderLayout.CENTER);
    	pnl.add(new JPanel(), BorderLayout.WEST);
    	pnl.add(new JPanel(), BorderLayout.EAST);
    	pnl.add(new JPanel(), BorderLayout.SOUTH);
    	JLabel calLabel = new JLabel(new ImageIcon("images/calculateThisButton.png"));
    	JLabel memoLabel = new JLabel(new ImageIcon("images/memorizeThisButton.png"));
    	JLabel spellLabel = new JLabel(new ImageIcon("images/spellThisButton.png"));
    	JLabel pointsLabel = new JLabel("Points!");
    	pointsLabel.setFont(new Font("Time New Roman", Font.BOLD, 32));

    	calLabel.setHorizontalAlignment(SwingConstants.CENTER);
    	memoLabel.setHorizontalAlignment(SwingConstants.CENTER);
    	spellLabel.setHorizontalAlignment(SwingConstants.CENTER);
    	pointsLabel.setHorizontalAlignment(SwingConstants.CENTER);
       	
    	JTextArea calTextArea = new JTextArea("2 minutes to solve as many equations as possible. "
    			+ "Show your math skills and gain points!", 2, 8);
    	
    	textAreaFilter(calTextArea);
    	
    	JTextArea memoTextArea = new JTextArea("Click the colors in the sequence they've been shown."
                + " After every round, another color is added! Can you remember them all?", 2, 8);
    	textAreaFilter(memoTextArea);
    	
    	JTextArea spellTextArea = new JTextArea("Want to challange you word skills? You have 2 minutes"
    			+ " to spell as many words as possible and gain points.", 2, 8);
    	textAreaFilter(spellTextArea);
    	
    	JTextArea pointsArea = new JTextArea("\tEasy level = 5 points\n\tMedium level = 10 points\n\tHard level = 20 points", 3, 5);
    	textAreaFilter(pointsArea);
    	
    	
    	pnlCenter.add(calLabel);    
    	pnlCenter.add(calTextArea);
    	pnlCenter.add(memoLabel);
    	pnlCenter.add(memoTextArea);
    	pnlCenter.add(spellLabel);
    	pnlCenter.add(spellTextArea);
    	pnlCenter.add(pointsLabel);
    	pnlCenter.add(pointsArea);
    	
    	frame.add(pnl);
    	frame.setTitle("How to play?");
    	frame.setVisible(true);
    	frame.setSize(400, 500);
    	frame.setLocationRelativeTo(null);
    	frame.setResizable(false);
        frame.setIconImage(new ImageIcon("images/icon_que.png").getImage());  
    }
    
    
    public void textAreaFilter(JTextArea text) {
    	text.setFont(new Font("Calibri", Font.ITALIC, 16));
    	text.setLineWrap(true);
    	text.setWrapStyleWord(true);
    	text.setOpaque(false);
    	text.setEditable(false);
    }
    
	/**
	 * Exit button
	 * @param btn
	 */
	public void exitBtnFilter(JButton btn) {
		btn.setOpaque(false);
		btn.setContentAreaFilled(false);
		btn.setBorderPainted(false);
		btn.setFocusPainted(false);
		btn.setBounds(756, 2, 40, 35);
		btn.setRolloverIcon(new ImageIcon("images/quitButtonHover.png"));
	}

	/**
	 * Minimize button 
	 * @param btn
	 */
	public void miniBtnFilter(JButton btn) {
		btn.setOpaque(false);
		btn.setContentAreaFilled(false);
		btn.setBorderPainted(false);
		btn.setFocusPainted(false);
		btn.setBounds(716, 2, 40, 35);
		btn.setRolloverIcon(new ImageIcon("images/minimizeButtonHover.png"));
	}
	/**
	 * Main button 
	 * @param btn
	 */
	public void mainMenuBtnFilter(JButton btn) {
		btn.setOpaque(false);
		btn.setContentAreaFilled(false);
		btn.setBorderPainted(false);
		btn.setBounds(4, 4, 120, 30);
		btn.setRolloverIcon(new ImageIcon("images/menuButtonHover.png"));
	}
	
	/*
	 * Enter Username message
	 */
	public void usernamePanel() {
		String str = "Please enter a Username";
		JOptionPane.showMessageDialog(frame, str, "No Username", JOptionPane.WARNING_MESSAGE);
	}
    

    /**
     * Main method, starts the application by creating a frame and instantiates
     * the controller using the frame as param.
     *
     * @param args
     */
    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        JFrame frame = new JFrame();
        Controller controller = new Controller(frame);
    }

    // Test methods

    public MainMenu getMainMenu() {
        return this.mainMenu;
    }

    public JPanel getPanelContainer() {
        return this.panelContainer;
    }

    public int getPanelState() {
        return frame.getState();
    }

    public boolean musicMuted() {
        return mutedMusic;
    }

    public boolean soundMuted() {
        return mutedSound;
    }

    public SpellThisGame getSpellThisGame() {
        return spellThisGame;
    }

    public CalculateThisGame getCalculateThisGame() {
        return calculateThisGame;
    }

    public MemorizeThisGame getMemorizeThisGame() {
        return memorizeThisGame;
    }

    public SpellThisMenu getSpellThisMenu() {
        return spellThisMenu;
    }

    public CalculateThisMenu getCalculateThisMenu() {
        return calculateThisMenu;
    }
  
}
