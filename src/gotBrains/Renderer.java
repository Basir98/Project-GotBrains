package gotBrains;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Renderer extends JPanel
{

	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		if (MemorizeThisGame.memorizeThisGame != null)
		{
			MemorizeThisGame.memorizeThisGame.paint((Graphics2D) g);
		}
	}

}
