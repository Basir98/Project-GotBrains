package gotBrains;

import org.junit.jupiter.api.TestInstance;

import javax.swing.*;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

@org.junit.jupiter.api.TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SpellThisGameTest {
    private Controller controller;

    @org.junit.jupiter.api.BeforeAll
    public void init() {
        controller = new Controller(new JFrame());
    }

    @org.junit.jupiter.api.Test
    void gameSpellThisStartAndRestartEasy() {
        controller.startSpellThisGame(1);
        SpellThisGame game = controller.getSpellThisGame();
        game.createMockGame(50);
        assertEquals(1, game.getDifficulty());
        assertEquals(50, game.getScore());
        game.restart();
        assertEquals(1, game.getDifficulty());
        assertEquals(0, game.getScore());
    }

    @org.junit.jupiter.api.Test
    void gameSpellThisStartAndRestartMedium() {
        controller.startSpellThisGame(5);
        SpellThisGame game = controller.getSpellThisGame();
        game.createMockGame(20);
        assertEquals(5, game.getDifficulty());
        assertEquals(20, game.getScore());
        game.restart();
        assertEquals(5, game.getDifficulty());
        assertEquals(0, game.getScore());
    }

    @org.junit.jupiter.api.Test
    void gameSpellThisStartAndRestartHard() {
        controller.startSpellThisGame(10);
        SpellThisGame game = controller.getSpellThisGame();
        game.createMockGame(80);
        assertEquals(10, game.getDifficulty());
        assertEquals(80, game.getScore());
        game.restart();
        assertEquals(10, game.getDifficulty());
        assertEquals(0, game.getScore());
    }

    @org.junit.jupiter.api.Test
    void gameSpellThisExitToMenu() {
        controller.startSpellThisGame(10);
        SpellThisGame game = controller.getSpellThisGame();
        game.createMockGame(80);
        game.clickMenu();
        for (Component comp : controller.getPanelContainer().getComponents()) {
            if (comp.isVisible()) {
                assertEquals("MainMenu", comp.toString().substring(comp.toString().indexOf('.') + 1, comp.toString().indexOf('[')));
            }
        }
    }

    @org.junit.jupiter.api.Test
    void gameSpellThisQuestionsEasy() {
        controller.startSpellThisGame(1);
        SpellThisGame game = controller.getSpellThisGame();
        String rightAnswer = "";
        int score = 0;
        for (int i = 0; i < 30; i++) {
            assertEquals(false, game.getRightAnswer() == rightAnswer);
            rightAnswer = game.getRightAnswer();
            System.out.println("Checking word: " + rightAnswer);
            assertEquals(true, rightAnswer.length() > 3 && rightAnswer.length() < 6);
            game.inputString(rightAnswer);
            assertEquals(score + game.getDifficulty(), game.getScore());
            score += game.getDifficulty();
        }
        game.inputString("ABCDE");
        game.gameOver();
    }

    @org.junit.jupiter.api.Test
    void gameSpellThisQuestionsMedium() {
        controller.startSpellThisGame(5);
        SpellThisGame game = controller.getSpellThisGame();
        String rightAnswer = "";
        int score = 0;
        for (int i = 0; i < 30; i++) {
            assertEquals(false, game.getRightAnswer() == rightAnswer);
            rightAnswer = game.getRightAnswer();
            System.out.println("Checking word: " + rightAnswer);
            assertEquals(true, rightAnswer.length() > 5 && rightAnswer.length() < 8);
            game.inputString(rightAnswer);
            assertEquals(score + game.getDifficulty(), game.getScore());
            score += game.getDifficulty();
        }
        game.inputString("ABCDE");
        game.gameOver();
    }

    @org.junit.jupiter.api.Test
    void gameSpellThisQuestionsHard() {
        controller.startSpellThisGame(10);
        SpellThisGame game = controller.getSpellThisGame();
        String rightAnswer = "";
        int score = 0;
        for (int i = 0; i < 30; i++) {
            assertEquals(false, game.getRightAnswer() == rightAnswer);
            rightAnswer = game.getRightAnswer();
            System.out.println("Checking word: " + rightAnswer);
            assertEquals(true, rightAnswer.length() > 7 && rightAnswer.length() < 11);
            game.inputString(rightAnswer);
            assertEquals(score + game.getDifficulty(), game.getScore());
            score += game.getDifficulty();
        }
        game.inputString("ABCDE");
        game.gameOver();
    }

    @org.junit.jupiter.api.Test
    void gameSpellThisTimer() {

    }
}