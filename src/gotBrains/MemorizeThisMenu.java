package gotBrains;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class for the game menu you see before the game starts.
 *
 * @author Andreas Holm and Matilda Frimodig
 */
public class MemorizeThisMenu extends JPanel implements ActionListener {
    private Controller controller;

    private Font font = new Font("Monospaced", Font.BOLD, 24);
    private Color fontColor = new Color(100, 100, 100);

    private JButton btnQuit = new JButton(new ImageIcon("images/quitButton.png"));
    private JButton btnMenu = new JButton(new ImageIcon("images/menuButton.png"));
    private JButton btnMinimize = new JButton(new ImageIcon("images/minimizeButton.png"));
    private JButton btnStartEasy = new JButton(new ImageIcon("images/easyButton.png"));
    private JButton btnStartMedium = new JButton(new ImageIcon("images/mediumButton.png"));
    private JButton btnStartHard = new JButton(new ImageIcon("images/hardButton.png"));
    // private JButton btnLeaderboard = new JButton("LEADERBOARD");

    public MemorizeThisMenu(Controller controller) {
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

        add(btnMenu);
        btnMenu.setOpaque(false);
        btnMenu.setContentAreaFilled(false);
        btnMenu.setBorderPainted(false);
        btnMenu.setBounds(4, 4, 120, 30);
        btnMenu.addActionListener(this);
        btnMenu.setRolloverIcon(new ImageIcon("images/menuButtonHover.png"));

        add(btnMinimize);
        btnMinimize.setOpaque(false);
        btnMinimize.setContentAreaFilled(false);
        btnMinimize.setBorderPainted(false);
        btnMinimize.setFocusPainted(false);
        btnMinimize.setBounds(716, 2, 40, 35);
        btnMinimize.addActionListener(this);
        btnMinimize.setRolloverIcon(new ImageIcon("images/minimizeButtonHover.png"));

        add(btnStartEasy);
        btnStartEasy.setOpaque(true);
        btnStartEasy.setContentAreaFilled(false);
        btnStartEasy.setBorderPainted(false);
        btnStartEasy.setRolloverIcon(new ImageIcon("images/easyButtonHover.png"));
        btnStartEasy.setBounds(175, 300, 150, 40);
        btnStartEasy.addActionListener(this);

        add(btnStartMedium);
        btnStartMedium.setOpaque(true);
        btnStartMedium.setContentAreaFilled(false);
        btnStartMedium.setBorderPainted(false);
        btnStartMedium.setRolloverIcon(new ImageIcon("images/mediumButtonHover.png"));
        btnStartMedium.setBounds(325, 300, 150, 40);
        btnStartMedium.addActionListener(this);

        add(btnStartHard);
        btnStartHard.setOpaque(true);
        btnStartHard.setContentAreaFilled(false);
        btnStartHard.setBorderPainted(false);
        btnStartHard.setRolloverIcon(new ImageIcon("images/hardButtonHover.png"));
        btnStartHard.setBounds(475, 300, 150, 40);
        btnStartHard.addActionListener(this);

    }

    protected void paintComponent(Graphics g) {
        ImageIcon background = new ImageIcon("images/memorizeThisBackground.png");
        super.paintComponent(g);
        g.drawImage(background.getImage(), 0, 0, null);
        g.setColor(fontColor);
        g.setFont(font);
        g.drawString("Choose difficulty", 280, 280);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnMenu) {
            controller.buttonSound();
            controller.showMainMenu();
        } else if (e.getSource() == btnQuit) {
            controller.buttonSound();
            System.exit(0);
        } else if (e.getSource() == btnStartEasy) {
            controller.buttonSound();
            controller.startMemorizeThisGame("easy");
        } else if (e.getSource() == btnStartMedium) {
            controller.buttonSound();
            controller.startMemorizeThisGame("medium");
        } else if (e.getSource() == btnStartHard) {
            controller.buttonSound();
            controller.startMemorizeThisGame("hard");
        } else if (e.getSource() == btnMinimize) {
            controller.buttonSound();
            controller.minimizeApp();
        }
    }

}
