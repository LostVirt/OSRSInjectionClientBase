package client.loader.util;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author LostVirt
 * @created 29/01/2022 - 19:57
 * @project OsrsInjectionBase
 */
public class BaseClassLoader extends ClassLoader
{
	@Getter
	private final Map<String, byte[]> classBytes;
	private final Map<String, Class<?>> classFiles = new HashMap<>();
	private final Map<String, Class<?>> tempClassFiles = new HashMap<>();

	public BaseClassLoader(Map<String, byte[]> map)
	{
		classBytes = map;
	}

	public Class<?> loadClass(String name) throws ClassNotFoundException, NoClassDefFoundError
	{
		if (classFiles.containsKey(name))
		{
			return classFiles.get(name);
		}
		else if (!classBytes.containsKey(name))
		{
			return super.loadClass(name);
		}
		else if (tempClassFiles.containsKey(name))
		{
			return tempClassFiles.get(name);
		}
		else
		{
			byte[] bytes = classBytes.get(name);
			Class<?> clazz = super.defineClass(name, bytes, 0, bytes.length);
			classFiles.put(name, clazz);
			tempClassFiles.put(name, clazz);
			return clazz;
		}
	}
}
