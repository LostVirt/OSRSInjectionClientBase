package client;

import api.Client;
import client.gui.Gui;
import client.loader.Injector;
import client.loader.applet.ConfigReader;
import client.loader.applet.RSApplet;
import client.loader.injector.hooks.Hooks;
import client.loader.util.BaseClassLoader;
import client.loader.util.JarHelper;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMaterialDarkerContrastIJTheme;
import internal.interfaces.RSClient;
import lombok.Getter;

import javax.swing.*;
import java.applet.Applet;
import java.awt.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;

import static client.util.Constants.BASE_DIR;

/**
 * @author LostVirt
 * @created 29/01/2022 - 19:23
 * @project OsrsInjectionBase
 */
public class Launcher
{
	@Getter
	private static Gui gui;

	public static void main(String[] args) throws IOException
	{
		Files.createDirectories(BASE_DIR.toPath());

		try
		{
			SwingUtilities.invokeAndWait(() ->
			{
				// Look and Feel
				FlatMaterialDarkerContrastIJTheme.install();
				UIManager.put("Component.focusWidth", -1);
				UIManager.put("Component.innerFocusWidth", -1);
				UIManager.put("TabbedPane.showTabSeparators", true);
				UIManager.put("TabbedPane.tabSeparatorsFullHeight", true);
				UIManager.put("Component.arrowType", "chevron");
				UIManager.put("Button.arc", 0);
				UIManager.put("Component.arc", 0);
				UIManager.put("CheckBox.arc", 0);
				UIManager.put("ProgressBar.arc", 0);

				// Some settings to make so canvas don't render on top of dropdowns if you have that
				JPopupMenu.setDefaultLightWeightPopupEnabled(false);
				ToolTipManager.sharedInstance().setLightWeightPopupEnabled(false);

				gui = new Gui();
			});
		}
		catch (InterruptedException | InvocationTargetException e)
		{
			e.printStackTrace();
			System.out.println("Couldn't load gui.");
			System.exit(0);
		}

		ConfigReader config = new ConfigReader();
		JarHelper helper = new JarHelper(config);

		Injector injector = new Injector(helper.getClasses());
		injector.inject();

		helper.saveJar();

		BaseClassLoader classLoader = new BaseClassLoader(helper.getClassBytes());

		RSApplet applet = new RSApplet(classLoader, config);

		try
		{
			Applet client = applet.getApplet();
			Client.setClient((RSClient) client);

			client.init();
			client.start();

			EventQueue.invokeAndWait(() ->
			{
				gui.setVisible(true);
				JPanel botPanel = gui.getBotPanel();
				botPanel.add(client, BorderLayout.CENTER);
				botPanel.revalidate();
			});

			// Clear hooks since they aren't needed anymore
			Hooks.clear();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
