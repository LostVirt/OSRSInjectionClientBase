package client.loader.injector.hooks;

import client.loader.injector.hooks.exceptions.HookedFieldNotFound;
import client.loader.injector.hooks.exceptions.HookedMethodNotFound;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import lombok.Getter;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @author LostVirt
 * @created 29/01/2022 - 20:08
 * @project OsrsInjectionBase
 */
public class Hooks
{
	private static Loader loader;

	public static List<HookedClass> getHookedClasses()
	{
		if (loader == null)
		{
			loader = new Loader();
		}

		return loader.getHookedClasses();
	}

	public static HookedField findHookedField(String field)
	{

		for (HookedClass hookedClass : getHookedClasses())
		{
			for (HookedField hookedField : hookedClass.getFields())
			{
				if (hookedField.getName().equals(field))
				{
					return hookedField;
				}
			}
		}

		throw new HookedFieldNotFound("Was not able to find field: " + field);
	}

	public static HookedField findHookedField(String field, String hookedClassName)
	{

		for (HookedClass hookedClass : getHookedClasses())
		{
			if (hookedClass.getClazz() != null && hookedClass.getClazz().equals(hookedClassName))
			{
				for (HookedField hookedField : hookedClass.getFields())
				{
					if (hookedField.getName().equals(field))
					{
						return hookedField;
					}
				}
			}
		}

		throw new HookedFieldNotFound("Was not able to find field: " + field + " in " + hookedClassName);
	}

	public static HookedMethod findHookedMethod(String method)
	{
		for (HookedClass hookedClass : getHookedClasses())
		{
			for (HookedMethod hookedMethod : hookedClass.getMethods())
			{
				if (hookedMethod.getName().equals(method))
				{
					return hookedMethod;
				}
			}
		}

		throw new HookedMethodNotFound("Was not able to find method: " + method);
	}

	public static HookedMethod findHookedMethod(String method, String hookedClassName)
	{
		for (HookedClass hookedClass : getHookedClasses())
		{
			if (hookedClass.getClazz() != null && hookedClass.getClazz().equals(hookedClassName))
			{
				for (HookedMethod hookedMethod : hookedClass.getMethods())
				{
					if (hookedMethod.getName().equals(method))
					{
						return hookedMethod;
					}
				}
			}
		}

		throw new HookedMethodNotFound("Was not able to find method: " + method + " in " + hookedClassName);
	}

	public static void clear()
	{
		loader.hookedClasses.clear();
		loader = null;
	}

	private static class Loader
	{
		@Getter
		private List<HookedClass> hookedClasses = new ArrayList<>();

		private Loader()
		{
			try (InputStream is = this.getClass().getResourceAsStream("/hooks/hooks.json"))
			{
				if (is == null)
				{
					System.out.println("Couldn't find hook file");
					System.exit(0);
				}

				hookedClasses = new GsonBuilder().create().fromJson(new InputStreamReader(is, StandardCharsets.UTF_8), new TypeToken<ArrayList<HookedClass>>()
				{
				}.getType());
				hookedClasses.removeIf(hookedClass -> hookedClass.getClazz() == null);
			}
			catch (Exception e)
			{
				e.printStackTrace();
				System.exit(0);
			}
		}

	}
}
