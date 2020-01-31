package gotBrains;

import java.io.Serializable;

/**
 * Class that holds player data used in comparing with other players scores.
 *
 * @author Isak Hartman, Felix Jönsson
 */
public class Player implements Serializable {
    private String username;
    private int scrabbleScore = 0, mathGameScore = 0, memorizeThisScore = 0, totalScore = 0;

    /**
     * Constructor gets the username from user input at game start.
     *
     * @param String username
     */
    public Player(String username) {
        this.username = username;
    }

    /**
     * Sets the simonSaysScore to the parameter if parameter value is bigger than existing value.
     *
     * @param int score
     */
    public void setMemorizeThisScore(int score) {
        if (score > this.memorizeThisScore) {
            this.memorizeThisScore = score;
        }
    }

    /**
     * Sets mathGameScore to the parameter if parameter value is bigger than existing value.
     *
     * @param int score
     */
    public void setMathGameScore(int score) {
        if (score > this.mathGameScore) {
            this.mathGameScore = score;
        }
    }

    /**
     * Sets scrabbleScore to the parameter if parameter value is bigger than existing value.
     *
     * @param int score
     */
    public void setScrabbleScore(int score) {
        if (score > this.scrabbleScore) {
            this.scrabbleScore = score;
        }
    }

    /**
     * Sets the total score to the sum of the game scores.
     */
    public void setTotalScore() {
        totalScore = getMathGameScore() + getScrabbleScore() + getMemorizeThis();
    }

    /**
     * Returns this player's username.
     *
     * @return String username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns this player's Scrabble score.
     *
     * @return int scrabbleScore
     */
    public int getScrabbleScore() {
        return scrabbleScore;
    }

    /**
     * Returns this player's Math Game score.
     *
     * @return int mathGameScore
     */
    public int getMathGameScore() {
        return mathGameScore;
    }

    /**
     * Returns this player's Simon Says score.
     *
     * @return int simonSaysScore
     */
    public int getMemorizeThis() {
        return memorizeThisScore;
    }

    /**
     * @return int totalScore
     */
    public int getTotalScore() {
        setTotalScore();
        return totalScore;
    }

    /**
     * Returns a String that displays the Class's data in an understandable way. Mostly used in debugging.
     *
     * @return String toString
     */
    public String toString() {
        return "Result: \n\n" + "Username: " + username + "\nScrabble Score: " + scrabbleScore + "\nMemorizeThis score: " + memorizeThisScore + "\nMath Game score: " + mathGameScore + "\nTotal score: " + getTotalScore();
    }
}
