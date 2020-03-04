package gotBrains;

import org.junit.jupiter.api.TestInstance;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static org.junit.jupiter.api.Assertions.*;

@org.junit.jupiter.api.TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SpellThisGameTest {
    private Controller controller;

    @org.junit.jupiter.api.BeforeAll
    public void init() {
        controller = new Controller(new JFrame());
    }

    // Testing F-S-1 + F-S-1.1
    @org.junit.jupiter.api.Test
    void gameSpellThisStartAndRestart() {
        controller.startSpellThisGame(1);
        SpellThisGame game = controller.getSpellThisGame();
        game.createMockGame(50);
        assertEquals("TESTTEXT", controller.getSpellThisGame().getTextField().getText());
        assertEquals(true, controller.getSpellThisGame().getTextField().isEditable());
        assertEquals(1, game.getDifficulty());
        assertEquals(50, game.getScore());
        assertEquals(true, game.getBtnRestart().isEnabled());
        game.getBtnRestart().doClick();
        assertEquals(1, game.getDifficulty());
        assertEquals(0, game.getScore());
        game.gameOver();
    }

    // Testing F-S-2
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

    // Testing F-S-ST-1 + F-S-ST-1.1
    @org.junit.jupiter.api.Test
    void gameSpellThisQuestionsEasy() {
        controller.startSpellThisGame(1);
        SpellThisGame game = controller.getSpellThisGame();
        String rightAnswer = "";
        for (int i = 0; i < 100; i++) {
            rightAnswer = game.getRightAnswer();
            assertEquals(true, rightAnswer.length() > 3 && rightAnswer.length() < 6);
            game.inputString(rightAnswer);
        }
        game.gameOver();
    }

    // Testing F-S-ST-1 + F-S-ST-1.2
    @org.junit.jupiter.api.Test
    void gameSpellThisQuestionsMedium() {
        controller.startSpellThisGame(5);
        SpellThisGame game = controller.getSpellThisGame();
        String rightAnswer = "";
        for (int i = 0; i < 100; i++) {
            rightAnswer = game.getRightAnswer();
            assertEquals(true, rightAnswer.length() > 5 && rightAnswer.length() < 8);
            game.inputString(rightAnswer);
        }
        game.gameOver();
    }

    // Testing F-S-ST-1 + F-S-ST-1.3
    @org.junit.jupiter.api.Test
    void gameSpellThisQuestionsHard() {
        controller.startSpellThisGame(10);
        SpellThisGame game = controller.getSpellThisGame();
        String rightAnswer = "";
        for (int i = 0; i < 100; i++) {
            rightAnswer = game.getRightAnswer();
            assertEquals(true, rightAnswer.length() > 7 && rightAnswer.length() < 11);
            game.inputString(rightAnswer);
        }
        game.gameOver();
    }

    // Testing F-S-ST-2
    @org.junit.jupiter.api.Test
    void gameSpellThisInput() {
        controller.startSpellThisGame(1);
        SpellThisGame game = controller.getSpellThisGame();
        assertEquals(true, game.getTextField().isEditable());
        assertEquals(true, game.getTextField().getActionListeners()[0] != null); // Kollar om det finns en listener bunden till Textfield-objektet
        game.gameOver();
        controller.startSpellThisGame(5);
        game = controller.getSpellThisGame();
        assertEquals(true, game.getTextField().isEditable());
        assertEquals(true, game.getTextField().getActionListeners()[0] != null);
        game.gameOver();
        controller.startSpellThisGame(10);
        game = controller.getSpellThisGame();
        assertEquals(true, game.getTextField().isEditable());
        assertEquals(true, game.getTextField().getActionListeners()[0] != null);
        game.gameOver();
    }

    // Testing F-S-ST-2.2 + F-S-ST-3.1 + F-S-5
    @org.junit.jupiter.api.Test
    void gameSpellThisCorrectInputEasy() {
        controller.startSpellThisGame(1);
        SpellThisGame game = controller.getSpellThisGame();
        String rightAnswer = "";
        int score = 0;
        for (int i = 0; i < 10; i++) {
            rightAnswer = game.getRightAnswer();
            game.inputString(rightAnswer);
            assertEquals(score + 5, game.getScore());
            score += game.getDifficulty();
            assertEquals(false, game.getRightAnswer() == rightAnswer);
        }
        assertEquals(String.valueOf(score), game.getLblScore().getText());
        assertEquals(true, game.getLblScore().isVisible());
        game.gameOver();
    }

    // Testing F-S-ST-2.2 + F-S-ST-3.2 + F-S-5
    @org.junit.jupiter.api.Test
    void gameSpellThisCorrectInputMedium() {
        controller.startSpellThisGame(5);
        SpellThisGame game = controller.getSpellThisGame();
        String rightAnswer = "";
        int score = 0;
        for (int i = 0; i < 10; i++) {
            rightAnswer = game.getRightAnswer();
            game.inputString(rightAnswer);
            assertEquals(score + 10, game.getScore());
            score += game.getDifficulty();
            assertEquals(false, game.getRightAnswer() == rightAnswer);
        }
        assertEquals(String.valueOf(score), game.getLblScore().getText());
        assertEquals(true, game.getLblScore().isVisible());
        game.gameOver();
    }

    // Testing F-S-ST-2.2 + F-S-ST-3.3 + F-S-5
    @org.junit.jupiter.api.Test
    void gameSpellThisCorrectInputHard() {
        controller.startSpellThisGame(10);
        SpellThisGame game = controller.getSpellThisGame();
        String rightAnswer = "";
        int score = 0;
        for (int i = 0; i < 10; i++) {
            rightAnswer = game.getRightAnswer();
            game.inputString(rightAnswer);
            assertEquals(score + 20, game.getScore());
            score += game.getDifficulty();
            assertEquals(false, game.getRightAnswer() == rightAnswer);
        }
        assertEquals(String.valueOf(score), game.getLblScore().getText());
        assertEquals(true, game.getLblScore().isVisible());
        game.gameOver();
    }

    // Testing F-S-ST-2.1
    @org.junit.jupiter.api.Test
    void gameSpellThisWrongInput() {
        controller.startSpellThisGame(1);
        SpellThisGame game = controller.getSpellThisGame();
        String rightAnswer = "";
        int score = 0;
        for (int i = 0; i < 10; i++) {
            rightAnswer = game.getRightAnswer();
            game.inputString("ABCDE");
            assertEquals(0, game.getScore());
            assertEquals(true, game.getRightAnswer() == rightAnswer);
        }
        game.gameOver();
    }

    // Testing F-S-ST-4
    @org.junit.jupiter.api.Test
    void gameSpellThisTimer() throws InterruptedException {
        controller.startSpellThisGame(1);
        assertEquals(2, controller.getSpellThisGame().getTimerMinutes());
        assertEquals(0, controller.getSpellThisGame().getTimerSeconds());
        controller.getSpellThisGame().setTimerZero();
        Thread.sleep(2000);
        assertEquals(false, controller.getSpellThisGame().getTextField().isEditable());
    }

    // Testing F-S-ST-1.4
    @org.junit.jupiter.api.Test
    void skipQuestion() {
        controller.startSpellThisGame(1);
        SpellThisGame game = controller.getSpellThisGame();
        assertEquals(true , game.getBtnJumpOver().isEnabled());
        int minutes = game.getTimerMinutes();
        int seconds = game.getTimerSeconds();
        int targetMinutes;
        int targetSeconds;
        int secondDecrease = 5;
        if(seconds < secondDecrease) {
            targetSeconds = 60 - (secondDecrease - seconds);
            targetMinutes = minutes - 1;
        } else {
            targetMinutes = minutes;
            targetSeconds = seconds - secondDecrease;
        }
        String questionAnswer = game.getRightAnswer();
        game.clickSkip();
        assertEquals(targetMinutes, game.getTimerMinutes());
        assertEquals(targetSeconds, game.getTimerSeconds());
        assertEquals(false, questionAnswer == game.getRightAnswer());
        game.gameOver();
        assertEquals(false, game.getBtnJumpOver().isEnabled());
    }

    // Testing F-S-4
    @org.junit.jupiter.api.Test
    void showGameInstructions() {
        // Not implemented
        fail();
    }

    // Testing F-S-7
    @org.junit.jupiter.api.Test
    void gameLogInfo() {
        gameLogTest(1);
        gameLogTest(5);
        gameLogTest(10);
    }

    // Testing F-S-8
    @org.junit.jupiter.api.Test
    void inputVisualFeedback() {
        // Not implemented
        fail();
    }


    // Non-test methods ---

    private void gameLogTest(int difficulty) {
        controller.startSpellThisGame(difficulty);
        SpellThisGame game = controller.getSpellThisGame();
        JTextArea gameLog = game.getGameLog();
        switch (difficulty) {
            case 1:
                assertEquals("Easy difficulty chosen.\nEvery correct answer is \nworth 5 point(s).\n\nQuestion 1:\n", gameLog.getText());
                break;
            case 5:
                assertEquals("Medium difficulty chosen.\nEvery correct answer is \nworth 10 point(s).\n\nQuestion 1:\n", gameLog.getText());
                break;
            case 10:
                assertEquals("Hard difficulty chosen.\nEvery correct answer is \nworth 20 point(s).\n\nQuestion 1:\n", gameLog.getText());
                break;
            default:
                break;
        }
        String rightAnswer = game.getRightAnswer();
        game.inputString(rightAnswer);
        switch (difficulty) {
            case 1:
                assertEquals("Easy difficulty chosen.\nEvery correct answer is \nworth 5 point(s).\n\nQuestion 1:\nCorrect!\nQuestion 2:\n", gameLog.getText());
                break;
            case 5:
                assertEquals("Medium difficulty chosen.\nEvery correct answer is \nworth 10 point(s).\n\nQuestion 1:\nCorrect!\nQuestion 2:\n", gameLog.getText());
                break;
            case 10:
                assertEquals("Hard difficulty chosen.\nEvery correct answer is \nworth 20 point(s).\n\nQuestion 1:\nCorrect!\nQuestion 2:\n", gameLog.getText());
                break;
            default:
                break;
        }
        game.clickSkip();
        switch (difficulty) {
            case 1:
                assertEquals("Easy difficulty chosen.\nEvery correct answer is \nworth 5 point(s).\n\nQuestion 1:\nCorrect!\nQuestion 2:\nSkips Question!\n-5 seconds\nQuestion 3:\n", gameLog.getText());
                break;
            case 5:
                assertEquals("Medium difficulty chosen.\nEvery correct answer is \nworth 10 point(s).\n\nQuestion 1:\nCorrect!\nQuestion 2:\nSkips Question!\n-5 seconds\nQuestion 3:\n", gameLog.getText());
                break;
            case 10:
                assertEquals("Hard difficulty chosen.\nEvery correct answer is \nworth 20 point(s).\n\nQuestion 1:\nCorrect!\nQuestion 2:\nSkips Question!\n-5 seconds\nQuestion 3:\n", gameLog.getText());
                break;
            default:
                break;
        }
        game.gameOver();
        switch (difficulty) {
            case 1:
                assertEquals("Easy difficulty chosen.\nEvery correct answer is \nworth 5 point(s).\n\nQuestion 1:\nCorrect!\nQuestion 2:\nSkips Question!\n-5 seconds\nQuestion 3:\n\nGame over, time's up!\nYour result: 5 point(s).\n", gameLog.getText());
                break;
            case 5:
                assertEquals("Medium difficulty chosen.\nEvery correct answer is \nworth 10 point(s).\n\nQuestion 1:\nCorrect!\nQuestion 2:\nSkips Question!\n-5 seconds\nQuestion 3:\n\nGame over, time's up!\nYour result: 5 point(s).\n", gameLog.getText());
                break;
            case 10:
                assertEquals("Hard difficulty chosen.\nEvery correct answer is \nworth 20 point(s).\n\nQuestion 1:\nCorrect!\nQuestion 2:\nSkips Question!\n-5 seconds\nQuestion 3:\n\nGame over, time's up!\nYour result: 5 point(s).\n", gameLog.getText());
                break;
            default:
                break;
        }
    }
}