package gotBrains;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The panel that holds the leaderboardUI
 *
 * @author Isak Hartman
 */
public class Leaderboard extends JPanel implements ActionListener {

    private JButton btnQuit = new JButton(new ImageIcon("images/quitButton.png"));
    private JButton btnMinimize = new JButton(new ImageIcon("images/minimizeButton.png"));
    private JButton btnMenu = new JButton(new ImageIcon("images/menuButton.png"));
    private JButton btnClearLeaderboard = new JButton("CLEAR LEADERBOARD");
    private JTextArea leaderboardPlacement = new JTextArea();
    private JTextArea leaderboardScore = new JTextArea();

    private Font font = new Font("Monospaced", Font.BOLD, 22);
    private Color fontColor = new Color(80, 80, 80);

    private Controller controller;

    /**
     * Places components and adds actionlisteners.
     *
     * @param controller
     */
    public Leaderboard(Controller controller) {
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

        add(btnClearLeaderboard);
        btnClearLeaderboard.setOpaque(false);
        btnClearLeaderboard.setContentAreaFilled(false);
        btnClearLeaderboard.setBounds(300, 500, 200, 30);
        btnClearLeaderboard.addActionListener(this);

        add(leaderboardPlacement);
        leaderboardPlacement.setOpaque(false);
        leaderboardPlacement.setBounds(125, 180, 375, 340);
        leaderboardPlacement.setForeground(fontColor);
        leaderboardPlacement.setFont(font);
        leaderboardPlacement.setEditable(false);
        leaderboardPlacement.setText(controller.getLeaderboardPlacement());
        leaderboardPlacement.setSelectionColor(new Color(0, 0, 0, 0));

        add(leaderboardScore);
        leaderboardScore.setOpaque(false);
        leaderboardScore.setBounds(530, 180, 80, 340);
        leaderboardScore.setForeground(fontColor);
        leaderboardScore.setFont(font);
        leaderboardScore.setEditable(false);
        leaderboardScore.setText(controller.getLeaderboardScore());
        leaderboardScore.setSelectionColor(new Color(0, 0, 0, 0));
    }

    /**
     * Paints the panel as desired
     */
    protected void paintComponent(Graphics g) {
        ImageIcon background = new ImageIcon("images/leaderboardBackground.png");
        super.paintComponent(g);
        g.drawImage(background.getImage(), 0, 0, null);
    }

    /**
     * Triggered when the user presses a button
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnQuit) {
            controller.buttonSound();
            System.exit(0);

        } else if (e.getSource() == btnMenu) {
            controller.buttonSound();
            controller.showMainMenu();

        } else if (e.getSource() == btnClearLeaderboard) {
            controller.buttonSound();
            if (JOptionPane.showInputDialog("Sure? \ny = yes, clear\nn = no, don't clear").equals("y")) {
                controller.clearLeaderboard();
                leaderboardPlacement.setText(controller.getLeaderboardPlacement());
                leaderboardScore.setText(controller.getLeaderboardScore());
            }

        } else if (e.getSource() == btnMinimize) {
            controller.buttonSound();
            controller.minimizeApp();
        }
    }
}
