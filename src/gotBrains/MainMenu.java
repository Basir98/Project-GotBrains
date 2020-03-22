package gotBrains;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Panel that holds the components for the mainmenu.
 *
 * @author Isak Hartman, Felix Jönsson
 */
public class MainMenu extends JPanel implements ActionListener {
    private JButton btnQuit = new JButton(new ImageIcon("images/quitButton.png"));
    private JButton btnMinimize = new JButton(new ImageIcon("images/minimizeButton.png"));
    private JButton btnSpellThis = new JButton(new ImageIcon("images/spellThisButton.png"));
    private JButton btnMemorizeThis = new JButton(new ImageIcon("images/memorizeThisButton.png"));
    private JButton btnCalculateThis = new JButton(new ImageIcon("images/calculateThisButton.png"));
    private JButton btnLeaderboard = new JButton(new ImageIcon("images/leaderboardButton.png"));

    private JButton btnToggleMusic = new JButton(new ImageIcon("images/musicIcon.png"));
    private JButton btnToggleSound = new JButton(new ImageIcon("images/soundIcon.png"));
    
    private JButton btnInfo = new JButton(new ImageIcon("images/icon_quq.png"));
    JTextField fieldUsername = new JTextField();
    private JLabel lblVersion = new JLabel("Version 2.0");

    private JSlider musicVolumeSlider = new JSlider(JSlider.VERTICAL, 0, 35, 35);
    private JSlider soundVolumeSlider = new JSlider(JSlider.VERTICAL, 0, 50, 50);

    private boolean mutedMusic = false;
    private boolean mutedSound = false;

    private Font font = new Font("Monospaced", Font.BOLD, 18);
    private Controller controller;

    /**
     * Places components and adds actionlistener to buttons.
     *
     * @param controller
     */
    public MainMenu(Controller controller) {
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

        add(fieldUsername);
        fieldUsername.setOpaque(false);
        fieldUsername.setHorizontalAlignment(JTextField.CENTER);
        fieldUsername.setFont(font);
        fieldUsername.setDocument(new LengthRestrictedDocument(12));
        fieldUsername.setUI(new JTextFieldHintUI("Enter your name.", new Color(130, 130, 130)));
        fieldUsername.setCaretPosition(fieldUsername.getText().length());
        fieldUsername.setSelectionColor(Color.white);
        fieldUsername.setBorder(BorderFactory.createEmptyBorder());
        fieldUsername.setForeground(new Color(80, 80, 80));
        fieldUsername.setBounds(300, 260, 200, 30);

        add(btnCalculateThis);
        btnCalculateThis.setOpaque(false);
        btnCalculateThis.setContentAreaFilled(false);
        btnCalculateThis.setBorderPainted(false);
        btnCalculateThis.setBounds(275, 300, 250, 50);
        btnCalculateThis.addActionListener(this);
        btnCalculateThis.setRolloverIcon(new ImageIcon("images/calculateThisButtonHover.png"));

        add(btnMemorizeThis);
        btnMemorizeThis.setOpaque(false);
        btnMemorizeThis.setContentAreaFilled(false);
        btnMemorizeThis.setBorderPainted(false);
        btnMemorizeThis.setBounds(275, 360, 250, 50);
        btnMemorizeThis.addActionListener(this);
        btnMemorizeThis.setRolloverIcon(new ImageIcon("images/memorizeThisButtonHover.png"));

        add(btnSpellThis);
        btnSpellThis.setOpaque(false);
        btnSpellThis.setContentAreaFilled(false);
        btnSpellThis.setBorderPainted(false);
        btnSpellThis.setBounds(275, 420, 250, 50);
        btnSpellThis.addActionListener(this);
        btnSpellThis.setRolloverIcon(new ImageIcon("images/spellThisButtonHover.png"));

        add(btnLeaderboard);
        btnLeaderboard.setOpaque(false);
        btnLeaderboard.setContentAreaFilled(false);
        btnLeaderboard.setBorderPainted(false);
        btnLeaderboard.setBounds(275, 480, 250, 50);
        btnLeaderboard.addActionListener(this);
        btnLeaderboard.setRolloverIcon(new ImageIcon("images/leaderboardButtonHover.png"));

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
        
        add(btnInfo);
        btnInfo.setContentAreaFilled(false);
        btnInfo.setBorderPainted(false);
        btnInfo.setBounds(75, 560, 32, 32);
        btnInfo.addActionListener(this);
        
        add(lblVersion);
        lblVersion.setBounds(725, 575, 75, 20);
    }

    /**
     * Paints the panel as desired
     */
    protected void paintComponent(Graphics g) {
        ImageIcon background = new ImageIcon("images/openingWindowBackground.png");
        super.paintComponent(g);
        g.drawImage(background.getImage(), 0, 0, null);
    }

    /**
     * Triggered when a button is being pressed.
     */
    public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnQuit) {
			controller.buttonSound();
			System.exit(0);

		} else if (e.getSource() == btnMinimize) {
			controller.buttonSound();
			controller.minimizeApp();

		} else if (e.getSource() == btnLeaderboard) {
			controller.buttonSound();
			controller.showLeaderboard();

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

		}    else if (e.getSource() == btnSpellThis) {
			if(fieldUsername.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Please enter username");
			} else {
				controller.buttonSound();
				controller.addPlayer(fieldUsername.getText());
				controller.setCurrentUsername(fieldUsername.getText());
				controller.showSpellThisMenu();
			}


		}  else if (e.getSource() == btnMemorizeThis) {
			if(fieldUsername.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "Please enter username");
			}else {
				controller.buttonSound();
				controller.addPlayer(fieldUsername.getText());
				controller.setCurrentUsername(fieldUsername.getText());
				controller.showMemorizeThisWindow();
			}    		

		} else if (e.getSource() == btnCalculateThis) {
			if(fieldUsername.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Please enter username");
			} else {
				controller.buttonSound();
				controller.addPlayer(fieldUsername.getText());
				controller.setCurrentUsername(fieldUsername.getText());
				controller.showCalculateThisMenu();

			}

		}
		
		else if(e.getSource() == btnInfo) {
			controller.mainInfoPanel();

		}
	}

    public void setMusicVolumeSlider(int volume) {
        musicVolumeSlider.setValue(volume);
    }

    public void setSoundVolumeSlider(int volume) {
        soundVolumeSlider.setValue(volume);
    }

    // Test methods
    public void clickQuit() {
        btnQuit.doClick();
    }

    public void clickMinimize() {
        btnMinimize.doClick();
    }

    public void clickCalculateThis() {
        btnCalculateThis.doClick();
    }

    public void clickMemorizeThis() {
        btnMemorizeThis.doClick();
    }

    public void clickSpellThis() {
        btnSpellThis.doClick();
    }

    public void clickLeaderboard() {
        btnLeaderboard.doClick();
    }

    public void clickToggleMusic() {
        btnToggleMusic.doClick();
    }

    public void clickToggleSound() {
        btnToggleSound.doClick();
    }

    public void setUsername(String username) {
        fieldUsername.setText(username);
        clickCalculateThis();
    }

    public JButton getBtnSpellThis() {
        return btnSpellThis;
    }

    public JButton getBtnCalculateThis() {
        return btnCalculateThis;
    }

    public JButton getBtnMemorizeThis() {
        return btnMemorizeThis;
    }

}
