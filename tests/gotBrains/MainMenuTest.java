package gotBrains;

import org.junit.jupiter.api.TestInstance;

import javax.swing.*;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

@org.junit.jupiter.api.TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MainMenuTest {

    private Controller controller;
    private MainMenu mainMenu;


    @org.junit.jupiter.api.BeforeAll
    public void init() {
        controller = new Controller(new JFrame());
        mainMenu = controller.getMainMenu();
    }

    @org.junit.jupiter.api.Test
    void chooseGames() {
        mainMenu.clickCalculateThis();
        for (Component comp : controller.getPanelContainer().getComponents()) {
            if (comp.isVisible()) {
                assertEquals("CalculateThisMenu", comp.toString().substring(comp.toString().indexOf('.') + 1, comp.toString().indexOf('[')));
            }
        }
        mainMenu.clickMemorizeThis();
        for (Component comp : controller.getPanelContainer().getComponents()) {
            if (comp.isVisible()) {
                assertEquals("MemorizeThisMenu", comp.toString().substring(comp.toString().indexOf('.') + 1, comp.toString().indexOf('[')));
            }
        }
        mainMenu.clickSpellThis();
        for (Component comp : controller.getPanelContainer().getComponents()) {
            if (comp.isVisible()) {
                assertEquals("SpellThisMenu", comp.toString().substring(comp.toString().indexOf('.') + 1, comp.toString().indexOf('[')));
            }
        }
    }

    @org.junit.jupiter.api.Test
    void chooseUsernameSuccess() {
        controller.getMainMenu().setUsername("TestUsername");
        assertEquals("TestUsername", controller.getCurrentUsername());
    }

    @org.junit.jupiter.api.Test
    void minimizeApp() {
        controller.getMainMenu().clickMinimize();
        assertEquals(JFrame.ICONIFIED, controller.getPanelState());
        controller = new Controller(new JFrame());
        mainMenu = controller.getMainMenu();
    }

    @org.junit.jupiter.api.Test
    void toggleMusic() {
        controller.getMainMenu().clickToggleMusic();
        assertEquals(true, controller.musicMuted());
        controller.getMainMenu().clickToggleMusic();
        assertEquals(false, controller.musicMuted());
    }

    @org.junit.jupiter.api.Test
    void toggleSound() {
        controller.getMainMenu().clickToggleSound();
        assertEquals(true, controller.soundMuted());
        controller.getMainMenu().clickToggleSound();
        assertEquals(false, controller.soundMuted());
    }
}