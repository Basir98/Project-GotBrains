package gotBrains;

import org.junit.jupiter.api.TestInstance;

import javax.swing.*;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

@org.junit.jupiter.api.TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CalculateThisGameTest {
    private Controller controller;

    @org.junit.jupiter.api.BeforeAll
    public void init() {
        controller = new Controller(new JFrame());
    }

    // Testing F-S-1 + F-S-1.1
    @org.junit.jupiter.api.Test
    void gameCalculateThisStartAndRestart() {
        controller.startCalculateThisGame(5);
        CalculateThisGame game = controller.getCalculateThisGame();
        game.createMockGame(50);
        assertEquals("99999", controller.getCalculateThisGame().getTextField().getText());
        assertEquals(true, controller.getCalculateThisGame().getTextField().isEditable());
        assertEquals(5, game.getDifficulty());
        assertEquals(50, game.getScore());
        assertEquals(true, game.getBtnRestart().isEnabled());
        game.getBtnRestart().doClick();
        assertEquals(5, game.getDifficulty());
        assertEquals(0, game.getScore());
        game.gameOver();
    }

    // Testing F-S-2
    @org.junit.jupiter.api.Test
    void gameCalculateThisExitToMenu() {
        controller.startCalculateThisGame(10);
        CalculateThisGame game = controller.getCalculateThisGame();
        game.createMockGame(80);
        game.clickMenu();
        for (Component comp : controller.getPanelContainer().getComponents()) {
            if (comp.isVisible()) {
                assertEquals("MainMenu", comp.toString().substring(comp.toString().indexOf('.') + 1, comp.toString().indexOf('[')));
            }
        }
    }

    // Testing F-S-6
    @org.junit.jupiter.api.Test
    void chooseDifficultyEasy() {
        MainMenu menu = controller.getMainMenu();
        menu.clickCalculateThis();
        CalculateThisMenu gameMenu = controller.getCalculateThisMenu();
        assertTrue(gameMenu.getBtnStartEasy().isEnabled());
        gameMenu.getBtnStartEasy().doClick();
        assertEquals(5, controller.getCalculateThisGame().getDifficulty());
    }

    // Testing F-S-6
    @org.junit.jupiter.api.Test
    void chooseDifficultyMedium() {
        MainMenu menu = controller.getMainMenu();
        menu.clickCalculateThis();
        CalculateThisMenu gameMenu = controller.getCalculateThisMenu();
        assertTrue(gameMenu.getBtnStartMedium().isEnabled());
        gameMenu.getBtnStartMedium().doClick();
        assertEquals(10, controller.getCalculateThisGame().getDifficulty());
    }

    // Testing F-S-6
    @org.junit.jupiter.api.Test
    void chooseDifficultyHard() {
        MainMenu menu = controller.getMainMenu();
        menu.clickCalculateThis();
        CalculateThisMenu gameMenu = controller.getCalculateThisMenu();
        assertTrue(gameMenu.getBtnStartHard().isEnabled());
        gameMenu.getBtnStartHard().doClick();
        assertEquals(20, controller.getCalculateThisGame().getDifficulty());
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
        gameLogTest(5);
        gameLogTest(10);
        gameLogTest(20);
    }

    // Testing F-S-8
    @org.junit.jupiter.api.Test
    void inputVisualFeedback() {
        // Not implemented
        fail();
    }

    // Testing F-S-CT-1 + F-S-CT-1.1
    @org.junit.jupiter.api.Test
    void gameCalculateThisQuestionsEasy() {
        controller.startCalculateThisGame(5);
        CalculateThisGame game = controller.getCalculateThisGame();
        String rightAnswer = "";
        String operator = game.getOperation();
        int nbr1 = Integer.parseInt(game.getNbr1());
        int nbr2 = Integer.parseInt(game.getNbr2());
        for (int i = 0; i < 100; i++) {
            rightAnswer = String.valueOf(game.getRightAnswer());
            assertTrue(operator == "+" || operator == "-");
            assertTrue(nbr1 > 0 && nbr1 < 10);
            assertTrue(nbr2 > 0 && nbr2 < 10);
            game.inputString(rightAnswer);
        }
        game.gameOver();
    }

    // Testing F-S-CT-1 + F-S-CT-1.2
    @org.junit.jupiter.api.Test
    void gameCalculateThisQuestionsMedium() {
        controller.startCalculateThisGame(10);
        CalculateThisGame game = controller.getCalculateThisGame();
        String rightAnswer = "";
        String operator = game.getOperation();
        int nbr1 = Integer.parseInt(game.getNbr1());
        int nbr2 = Integer.parseInt(game.getNbr2());
        for (int i = 0; i < 100; i++) {
            rightAnswer = String.valueOf(game.getRightAnswer());
            assertTrue(operator == "+" || operator == "-" || operator == "*");
            if(operator == "+" || operator == "-") {
                assertTrue(nbr1 > 19 && nbr2 < 100);
                assertTrue(nbr2 > 19 && nbr2 < 100);
            } else {
                assertTrue(nbr1 > 4 && nbr2 < 10);
                assertTrue(nbr2 > 4 && nbr2 < 10);
            }
            game.inputString(rightAnswer);
        }
        game.gameOver();
    }

    // Testing F-S-CT-1 + F-S-CT-1.3
    @org.junit.jupiter.api.Test
    void gameCalculateThisQuestionsHard() {
        controller.startCalculateThisGame(20);
        CalculateThisGame game = controller.getCalculateThisGame();
        String rightAnswer = "";
        String operator = game.getOperation();
        int nbr1 = Integer.parseInt(game.getNbr1());
        int nbr2 = Integer.parseInt(game.getNbr2());
        for (int i = 0; i < 100; i++) {
            rightAnswer = String.valueOf(game.getRightAnswer());
            assertTrue(operator == "+" || operator == "-" || operator == "*");
            if(operator == "+" || operator == "-") {
                assertTrue(nbr1 > 99 && nbr2 < 201);
                assertTrue(nbr2 > 99 && nbr2 < 201);
            } else {
                assertTrue(nbr1 > 9 && nbr2 < 21);
                assertTrue(nbr2 > 9 && nbr2 < 21);
            }
            game.inputString(rightAnswer);
        }
        game.gameOver();
    }

    // Testing F-S-CT-1.4
    @org.junit.jupiter.api.Test
    void skipQuestion() {
        controller.startCalculateThisGame(5);
        CalculateThisGame game = controller.getCalculateThisGame();
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
        String questionAnswer = String.valueOf(game.getRightAnswer());
        game.clickSkip();
        assertEquals(targetMinutes, game.getTimerMinutes());
        assertEquals(targetSeconds, game.getTimerSeconds());
        assertEquals(false, questionAnswer.equals(String.valueOf(game.getRightAnswer())));
        game.gameOver();
        assertEquals(false, game.getBtnJumpOver().isEnabled());
    }

    // Testing F-S-CT-2
    @org.junit.jupiter.api.Test
    void gameCalculateThisInput() {
        controller.startCalculateThisGame(5);
        CalculateThisGame game = controller.getCalculateThisGame();
        assertEquals(true, game.getTextField().isEditable());
        assertEquals(true, game.getTextField().getActionListeners()[0] != null); // Kollar om det finns en listener bunden till Textfield-objektet
        game.gameOver();
        controller.startCalculateThisGame(10);
        game = controller.getCalculateThisGame();
        assertEquals(true, game.getTextField().isEditable());
        assertEquals(true, game.getTextField().getActionListeners()[0] != null);
        game.gameOver();
        controller.startCalculateThisGame(20);
        game = controller.getCalculateThisGame();
        assertEquals(true, game.getTextField().isEditable());
        assertEquals(true, game.getTextField().getActionListeners()[0] != null);
        game.gameOver();
    }

    // Testing F-S-CT-2.2 + F-S-CT-3.1 + F-S-5
    @org.junit.jupiter.api.Test
    void gameCalculateThisCorrectInputEasy() {
        controller.startCalculateThisGame(5);
        CalculateThisGame game = controller.getCalculateThisGame();
        String rightAnswer = "";
        int score = 0;
        for (int i = 0; i < 10; i++) {
            rightAnswer = String.valueOf(game.getRightAnswer());
            game.inputString(rightAnswer);
            assertEquals(score + 5, game.getScore());
            score += game.getDifficulty();
            assertEquals(false, String.valueOf(game.getRightAnswer()).equals(rightAnswer));
        }
        assertEquals("Score: " + score, game.getLblScore().getText());
        assertEquals(true, game.getLblScore().isVisible());
        game.gameOver();
    }

    // Testing F-S-CT-2.2 + F-S-CT-3.2 + F-S-5
    @org.junit.jupiter.api.Test
    void gameCalculateThisCorrectInputMedium() {
        controller.startCalculateThisGame(10);
        CalculateThisGame game = controller.getCalculateThisGame();
        String rightAnswer = "";
        int score = 0;
        for (int i = 0; i < 10; i++) {
            rightAnswer = String.valueOf(game.getRightAnswer());
            game.inputString(rightAnswer);
            assertEquals(score + 10, game.getScore());
            score += game.getDifficulty();
            assertEquals(false, String.valueOf(game.getRightAnswer()).equals(rightAnswer));
        }
        assertEquals("Score: " + score, game.getLblScore().getText());
        assertEquals(true, game.getLblScore().isVisible());
        game.gameOver();
    }

    // Testing F-S-CT-2.2 + F-S-CT-3.3 + F-S-5
    @org.junit.jupiter.api.Test
    void gameCalculateThisCorrectInputHard() {
        controller.startCalculateThisGame(20);
        CalculateThisGame game = controller.getCalculateThisGame();
        String rightAnswer = "";
        int score = 0;
        for (int i = 0; i < 10; i++) {
            rightAnswer = String.valueOf(game.getRightAnswer());
            game.inputString(rightAnswer);
            assertEquals(score + 20, game.getScore());
            score += game.getDifficulty();
            assertEquals(false, String.valueOf(game.getRightAnswer()).equals(rightAnswer));
        }
        assertEquals("Score: " + score, game.getLblScore().getText());
        assertEquals(true, game.getLblScore().isVisible());
        game.gameOver();
    }

    // Testing F-S-CT-2.1
    @org.junit.jupiter.api.Test
    void gameCalculateThisWrongInput() {
        controller.startCalculateThisGame(5);
        CalculateThisGame game = controller.getCalculateThisGame();
        String rightAnswer = "";
        int score = 0;
        for (int i = 0; i < 10; i++) {
            rightAnswer = String.valueOf(game.getRightAnswer());
            game.inputString("99999");
            assertEquals(0, game.getScore());
            assertEquals(true, String.valueOf(game.getRightAnswer()).equals(rightAnswer));
        }
        game.gameOver();
    }

    // Testing F-S-CT-2.3
    @org.junit.jupiter.api.Test
    void incorrectFormatInput() {
        // TODO
        fail();
    }

    // Testing F-S-CT-2.4
    @org.junit.jupiter.api.Test
    void markerPositionInput() {
        // TODO
        fail();
    }

    // Testing F-S-CT-4
    @org.junit.jupiter.api.Test
    void gameCalculateThisTimer() throws InterruptedException {
        controller.startCalculateThisGame(5);
        assertEquals(2, controller.getCalculateThisGame().getTimerMinutes());
        assertEquals(0, controller.getCalculateThisGame().getTimerSeconds());
        controller.getCalculateThisGame().setTimerZero();
        Thread.sleep(2000);
        assertEquals(false, controller.getCalculateThisGame().getTextField().isEditable());
    }

    // Non-test methods ---

    private void gameLogTest(int difficulty) {
        controller.startCalculateThisGame(difficulty);
        CalculateThisGame game = controller.getCalculateThisGame();
        JTextArea gameLog = game.getGameLog();
        switch (difficulty) {
            case 5:
                assertEquals("Easy difficulty chosen.\nEvery correct answer is \nworth 5 point(s).\n\nQuestion 1:\n", gameLog.getText());
                break;
            case 10:
                assertEquals("Medium difficulty chosen.\nEvery correct answer is \nworth 10 point(s).\n\nQuestion 1:\n", gameLog.getText());
                break;
            case 20:
                assertEquals("Hard difficulty chosen.\nEvery correct answer is \nworth 20 point(s).\n\nQuestion 1:\n", gameLog.getText());
                break;
            default:
                break;
        }
        int rightAnswer = game.getRightAnswer();
        game.inputString(String.valueOf(rightAnswer));
        switch (difficulty) {
            case 5:
                assertEquals("Easy difficulty chosen.\nEvery correct answer is \nworth 5 point(s).\n\nQuestion 1:\nCorrect!\nQuestion 2:\n", gameLog.getText());
                break;
            case 10:
                assertEquals("Medium difficulty chosen.\nEvery correct answer is \nworth 10 point(s).\n\nQuestion 1:\nCorrect!\nQuestion 2:\n", gameLog.getText());
                break;
            case 20:
                assertEquals("Hard difficulty chosen.\nEvery correct answer is \nworth 20 point(s).\n\nQuestion 1:\nCorrect!\nQuestion 2:\n", gameLog.getText());
                break;
            default:
                break;
        }
        game.clickSkip();
        switch (difficulty) {
            case 5:
                assertEquals("Easy difficulty chosen.\nEvery correct answer is \nworth 5 point(s).\n\nQuestion 1:\nCorrect!\nQuestion 2:\nSkips question!\n-5 seconds\nQuestion 3:\n", gameLog.getText());
                break;
            case 10:
                assertEquals("Medium difficulty chosen.\nEvery correct answer is \nworth 10 point(s).\n\nQuestion 1:\nCorrect!\nQuestion 2:\nSkips question!\n-5 seconds\nQuestion 3:\n", gameLog.getText());
                break;
            case 20:
                assertEquals("Hard difficulty chosen.\nEvery correct answer is \nworth 20 point(s).\n\nQuestion 1:\nCorrect!\nQuestion 2:\nSkips question!\n-5 seconds\nQuestion 3:\n", gameLog.getText());
                break;
            default:
                break;
        }
        game.gameOver();
        switch (difficulty) {
            case 5:
                assertEquals("Easy difficulty chosen.\nEvery correct answer is \nworth 5 point(s).\n\nQuestion 1:\nCorrect!\nQuestion 2:\nSkips question!\n-5 seconds\nQuestion 3:\n\nGame over, time's up!\nYour result: 5 point(s).\n", gameLog.getText());
                break;
            case 10:
                assertEquals("Medium difficulty chosen.\nEvery correct answer is \nworth 10 point(s).\n\nQuestion 1:\nCorrect!\nQuestion 2:\nSkips question!\n-5 seconds\nQuestion 3:\n\nGame over, time's up!\nYour result: 5 point(s).\n", gameLog.getText());
                break;
            case 20:
                assertEquals("Hard difficulty chosen.\nEvery correct answer is \nworth 20 point(s).\n\nQuestion 1:\nCorrect!\nQuestion 2:\nSkips question!\n-5 seconds\nQuestion 3:\n\nGame over, time's up!\nYour result: 5 point(s).\n", gameLog.getText());
                break;
            default:
                break;
        }
    }
}