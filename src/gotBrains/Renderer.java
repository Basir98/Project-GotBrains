package gotBrains;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class Renderer extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (MemorizeThisGame.memorizeThisGame != null) {
            MemorizeThisGame.memorizeThisGame.paint((Graphics2D) g);
        }
    }

}
