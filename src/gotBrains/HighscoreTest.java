package gotBrains;

/**
 * Class used in testing the highscore system.
 * @author Isak Hartman
 *
 */
public class HighscoreTest {
    public static void main(String[] args) {
        HighscoreManager hm = new HighscoreManager();
//        hm.clearScores();
        hm.addPlayer("Isak");
        hm.addPlayer("Felix");
        hm.addPlayer("Gulcin");
        hm.addPlayer("Farid");
        hm.addPlayer("Alper");
        
        hm.addMathGameScore("Isak", 10);
        hm.addScrabbleScore("Isak", 12);
        hm.addSimonSaysScore("Isak", 8);
        
        hm.addMathGameScore("Felix", 14);
        hm.addScrabbleScore("Felix", 4);
        hm.addSimonSaysScore("Felix", 6);
        
        hm.addMathGameScore("Gulcin", 10);
        hm.addScrabbleScore("Gulcin", 5);
        hm.addSimonSaysScore("Gulcin", 5);
        
        hm.addMathGameScore("Alper", 2);
        hm.addScrabbleScore("Alper", 5);
        hm.addSimonSaysScore("Alper", 10);
        
        
        hm.addMathGameScore("Unknown", 1);
        hm.addScrabbleScore("Unknown", 1);
        hm.addSimonSaysScore("Unknown", 1);
        
        hm.addPlayer("Unknown");
        
        hm.updateScoreFile();
        hm.loadScoreFile();
        System.out.print(hm.getHighscoreString());
        System.out.println();
        
        hm.addPlayer("Isak");
        hm.addMathGameScore("Isak", 20);
        hm.addScrabbleScore("Isak", 24);
        hm.addSimonSaysScore("Isak", 16);
        
//        hm.addMathGameScore("Unknown", 1);
//        hm.addScrabbleScore("Unknown", 1);
//        hm.addSimonSaysScore("Unknown", 1);
        
        hm.updateScoreFile();
        hm.loadScoreFile();
        System.out.print(hm.getHighscoreString());
        System.out.println();
    }
}