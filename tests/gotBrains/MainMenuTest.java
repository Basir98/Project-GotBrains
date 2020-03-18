package gotBrains;

import org.junit.jupiter.api.TestInstance;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

import java.awt.*;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@org.junit.jupiter.api.TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MainMenuTest {

    private Controller controller;
    private MainMenu mainMenu;


    @org.junit.jupiter.api.BeforeAll
    public void init() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        controller = new Controller(new JFrame());
        mainMenu = controller.getMainMenu();
    }

    // Testing F-UI-4
    @org.junit.jupiter.api.Test
    void chooseGames() {
        assertTrue(mainMenu.getBtnCalculateThis().isEnabled());
        mainMenu.clickCalculateThis();
        for (Component comp : controller.getPanelContainer().getComponents()) {
            if (comp.isVisible()) {
                assertEquals("CalculateThisMenu", comp.toString().substring(comp.toString().indexOf('.') + 1, comp.toString().indexOf('[')));
            }
        }
        assertTrue(mainMenu.getBtnMemorizeThis().isEnabled());
        mainMenu.clickMemorizeThis();
        for (Component comp : controller.getPanelContainer().getComponents()) {
            if (comp.isVisible()) {
                assertEquals("MemorizeThisMenu", comp.toString().substring(comp.toString().indexOf('.') + 1, comp.toString().indexOf('[')));
            }
        }
        assertTrue(mainMenu.getBtnSpellThis().isEnabled());
        mainMenu.clickSpellThis();
        for (Component comp : controller.getPanelContainer().getComponents()) {
            if (comp.isVisible()) {
                assertEquals("SpellThisMenu", comp.toString().substring(comp.toString().indexOf('.') + 1, comp.toString().indexOf('[')));
            }
        }
    }

    // Testing F-UI-5
    @org.junit.jupiter.api.Test
    void chooseUsernameSuccess() {
        controller.getMainMenu().setUsername("TestUsername");
        assertEquals("TestUsername", controller.getCurrentUsername());
    }

    // Testing F-UI-2
    @org.junit.jupiter.api.Test
    void minimizeApp() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        controller.getMainMenu().clickMinimize();
        assertEquals(JFrame.ICONIFIED, controller.getPanelState());
        controller = new Controller(new JFrame());
        mainMenu = controller.getMainMenu();
    }

    // "Stänga av musik" är inget krav, men borde vara det.
    @org.junit.jupiter.api.Test
    void toggleMusic() {
        controller.getMainMenu().clickToggleMusic();
        assertEquals(true, controller.musicMuted());
        controller.getMainMenu().clickToggleMusic();
        assertEquals(false, controller.musicMuted());
    }

    // Testing F-A-2
    @org.junit.jupiter.api.Test
    void toggleSound() {
        controller.getMainMenu().clickToggleSound();
        assertEquals(true, controller.soundMuted());
        controller.getMainMenu().clickToggleSound();
        assertEquals(false, controller.soundMuted());
    }

    // Testing F-A-3
    @org.junit.jupiter.api.Test
    void changeMusicVolume() {
        // Not implemented
        fail();
    }

    // Testing F-A-3
    @org.junit.jupiter.api.Test
    void changeSoundVolume() {
        // Not implemented
        fail();
    }

    // Testing F-A-1
    @org.junit.jupiter.api.Test
    void changeMusic() {
        // Not implemented
        fail();
    }

    // Testing F-S-3
    @org.junit.jupiter.api.Test
    void showGameInfo() {
        // TODO
        fail();
    }
}