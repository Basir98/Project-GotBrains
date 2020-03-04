package gotBrains;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
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
 * @author Isak Hartman
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
    private Clip music;
    private boolean mutedMusic = false;
    private boolean mutedSound = false;

    private HighscoreManager hm = new HighscoreManager();
    private CardLayout cl = new CardLayout();
    private Font customFont;

    public Controller(JFrame frame) {
        this.frame = frame;
        panelContainer.setLayout(cl);
        loadApp();
        startMusic();
    }

    /**
     * Instantiates the panels, adds them to the cardlayout-container, sets the
     * frame preferences and shows the menu
     */
    public void loadApp() {
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
            System.out.println(backgroundMusic);
            AudioInputStream ais = AudioSystem.getAudioInputStream(backgroundMusic);
            music = AudioSystem.getClip();
            music.open(ais);
            FloatControl gainControl = (FloatControl) music.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-15.0f);
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
                    sound.start();
                } else if (!correct) {
                    AudioInputStream ais = AudioSystem.getAudioInputStream(incorrectSound);
                    Clip sound = AudioSystem.getClip();
                    sound.open(ais);
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
     * Main method, starts the application by creating a frame and instantiates
     * the controller using the frame as param.
     *
     * @param args
     */
    public static void main(String[] args) {
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
