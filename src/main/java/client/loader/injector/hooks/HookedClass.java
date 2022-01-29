package client.loader.injector.hooks;

import client.loader.injector.hooks.exceptions.HookedFieldNotFound;
import client.loader.injector.hooks.exceptions.HookedMethodNotFound;
import lombok.Value;

/**
 * @author LostVirt
 * @created 29/01/2022 - 20:04
 * @project OsrsInjectionBase
 */

@Value
public class HookedClass
{
	String clazz;
	String name;
	String superClass;
	String obfSuperClass;
	int access;
	String[] interfaces;
	HookedField[] fields;
	HookedMethod[] methods;

	public HookedField getHookedField(String name)
	{
		for (HookedField field : fields)
		{
			if (field.getName().equals(name))
				return field;
		}

		throw new HookedFieldNotFound("Was not able to find field: " + name + " -> " + this);
	}

	public HookedMethod getHookedMethod(String name)
	{
		for (HookedMethod method : methods)
		{
			if (method.getName().equals(name))
				return method;
		}

		throw new HookedMethodNotFound("Was not able to find method: " + name + " -> " + this);
	}
}
