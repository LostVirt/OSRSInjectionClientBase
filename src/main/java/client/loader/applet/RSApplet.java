package client.loader.applet;

import lombok.Getter;

import java.applet.Applet;
import java.applet.AppletContext;
import java.applet.AppletStub;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;

import static client.util.Constants.GAME_FIXED_SIZE;

/**
 * @author LostVirt
 * @created 29/01/2022 - 19:32
 * @project OsrsInjectionBase
 */
public class RSApplet
{
	@Getter
	private Applet applet;

	public RSApplet(ClassLoader loader, ConfigReader config)
	{
		try
		{
			applet = (Applet) loader.loadClass("client").newInstance();
			applet.setBackground(Color.BLACK);
			applet.setSize(GAME_FIXED_SIZE);
			applet.setLayout(null);

			applet.setStub(getAppletStub(applet, config));
			applet.setVisible(true);

		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("Failed To Load Jar.");
			System.exit(0);
		}
	}

	public AppletStub getAppletStub(Applet applet, ConfigReader config)
	{
		return new AppletStub()
		{
			public URL getCodeBase()
			{
				try
				{
					return new URL(config.getConfigMap().get("codebase"));
				}
				catch (MalformedURLException var2)
				{
					return null;
				}
			}

			public boolean isActive()
			{
				return true;
			}

			public void appletResize(int width, int height)
			{
				applet.setSize(new Dimension(width, height));
			}

			public void appletResize(Dimension dimension)
			{
				applet.setSize(dimension);
			}

			public String getParameter(String param)
			{
				return config.getConfigMap().get(param);
			}

			public URL getDocumentBase()
			{
				try
				{
					return new URL(config.getConfigMap().get("codebase"));
				}
				catch (MalformedURLException var2)
				{
					return null;
				}
			}

			public AppletContext getAppletContext()
			{
				return null;
			}
		};
	}
}
