package internal;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import static client.util.Constants.GAME_FIXED_HEIGHT;
import static client.util.Constants.GAME_FIXED_WIDTH;

/**
 * @author LostVirt
 * @created 29/01/2022 - 19:22
 * @project OsrsInjectionBase
 */
public class RSCanvas extends Canvas implements KeyListener
{
	public static BufferedImage botBuffer = new BufferedImage(GAME_FIXED_WIDTH, GAME_FIXED_HEIGHT, 1);
	public static BufferedImage gameBuffer = new BufferedImage(GAME_FIXED_WIDTH, GAME_FIXED_HEIGHT, 1);

	public RSCanvas()
	{
		super.setFocusable(true);
		this.addKeyListener(this);
	}

	@Override
	public Graphics getGraphics()
	{
		Graphics botGraphics = botBuffer.getGraphics();
		botGraphics.drawImage(gameBuffer, 0, 0, null);
		botGraphics.dispose();
		Graphics rend = super.getGraphics();
		Graphics2D gg = (Graphics2D) botBuffer.getGraphics();

		painter(gg);

		if (rend != null)
		{
			rend.drawImage(botBuffer, 0, 0, null);
		}

		return gameBuffer.getGraphics();
	}

	public void painter(Graphics2D gg)
	{
		gg.drawString("Painting on Canvas", 25, 25);
	}

	@Override
	public void keyTyped(KeyEvent e)
	{

	}

	@Override
	public void keyPressed(KeyEvent e)
	{

	}

	@Override
	public void keyReleased(KeyEvent e)
	{

	}
}
