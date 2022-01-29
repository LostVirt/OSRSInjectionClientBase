package client.gui;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static client.util.Constants.*;

/**
 * @author LostVirt
 * @created 29/01/2022 - 19:38
 * @project OsrsInjectionBase
 */
public class Gui extends JFrame
{

	@Getter
	private final JPanel botPanel = new JPanel();

	public Gui()
	{
		setLayout(new BorderLayout());
		setTitle("Osrs Injection Base");

		setMinimumSize(new Dimension(GAME_FIXED_WIDTH + 24, GAME_FIXED_HEIGHT + 48));
		getRootPane().setBorder(BorderFactory.createEmptyBorder(0, 4, 4, 4));
		getRootPane().setBackground(new Color(26, 26, 26));

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent windowEvent)
			{
				windowEvent.getWindow().dispose();
				System.exit(0);
			}
		});

		// add bot panel to the GUI
		add(botPanel, BorderLayout.CENTER);
		// have to set minimum size otherwise game will crash...
		botPanel.setSize(GAME_FIXED_SIZE);
		botPanel.setMinimumSize(GAME_FIXED_SIZE);
		botPanel.setPreferredSize(GAME_FIXED_SIZE);
	}
}
