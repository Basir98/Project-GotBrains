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

    private JButton btnHelp = new JButton(new ImageIcon("images/helpIcon.png"));

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

        add(btnHelp);
        btnHelp.setOpaque(false);
        btnHelp.setContentAreaFilled(false);
        btnHelp.setBorderPainted(false);
        btnHelp.setBounds(366, 510, 60, 60);
        btnHelp.addActionListener(this);

        add(btnQuit);
        controller.exitBtnFilter(btnQuit);
        btnQuit.addActionListener(this);

        add(btnMinimize);
        controller.miniBtnFilter(btnMinimize);
        btnMinimize.addActionListener(this);

        add(btnMenu);
        controller.mainMenuBtnFilter(btnMenu);
        btnMenu.addActionListener(this);

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
            controller.startMemorizeThisGame("Easy");
        } else if (e.getSource() == btnStartMedium) {
            controller.buttonSound();
            controller.startMemorizeThisGame("Medium");
        } else if (e.getSource() == btnStartHard) {
            controller.buttonSound();
            controller.startMemorizeThisGame("Hard");
        } else if (e.getSource() == btnMinimize) {
            controller.buttonSound();
            controller.minimizeApp();
        }else if(e.getSource() == btnHelp ) {
        	controller.buttonSound();
        	
        	JTextArea textArea = new JTextArea("\nYou gain points for every round with right colors.               Try memorize all the colors", 2, 10);
        	JLabel label = new JLabel();
        	label.setText("     Memorize as many color as possible");
        	
        	controller.getInfo(textArea, label);
        }
    }
    


}
