package client.util;

import java.awt.*;
import java.io.File;

/**
 * @author LostVirt
 * @created 29/01/2022 - 19:29
 * @project OsrsInjectionBase
 */
public class Constants
{
	public static final File BASE_DIR = new File(System.getProperty("user.home"), "OsrsInjectionBase");
	public static final File GAMEPACK = new File(BASE_DIR, "gamepack.jar");
	public static final File INJECTED_GAMEPACK = new File(BASE_DIR, "injected.jar");
	public static final int GAME_FIXED_WIDTH = 765;
	public static final int GAME_FIXED_HEIGHT = 503;
	public static final Dimension GAME_FIXED_SIZE = new Dimension(GAME_FIXED_WIDTH, GAME_FIXED_HEIGHT);
}
