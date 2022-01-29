package client.loader;

import client.loader.injector.asm.AsmUtil;
import client.loader.injector.clazz.RSClientInjector;
import client.loader.injector.hooks.HookedClass;
import client.loader.injector.hooks.Hooks;
import client.loader.injector.node.RSCanvasInjector;
import org.objectweb.asm.tree.ClassNode;

import java.util.Map;

/**
 * @author LostVirt
 * @created 29/01/2022 - 20:00
 * @project OsrsInjectionBase
 */
public class Injector
{
	private final Map<String, ClassNode> map;

	public Injector(Map<String, ClassNode> map)
	{
		this.map = map;
	}

	public void inject()
	{
		for (Map.Entry<String, ClassNode> entry : map.entrySet())
		{
			String className = entry.getKey().replace(".class", "");
			ClassNode classNode = entry.getValue();

			for (HookedClass hookedClass : Hooks.getHookedClasses())
			{
				if (hookedClass.getClazz() != null && className.equals(hookedClass.getName()))
				{
					injectInterface(classNode, hookedClass.getClazz());

					switch (hookedClass.getClazz())
					{
						case "Canvas" -> new RSCanvasInjector().transform(classNode);
						case "Client" -> new RSClientInjector().transform(classNode, hookedClass);
					}
				}
			}
		}

	}

	public void injectInterface(ClassNode node, String deObfClassName)
	{
		try
		{
			Class<?> clazz = Class.forName("internal.interfaces.RS" + deObfClassName);
			AsmUtil.addInterface(node, clazz);
		}
		catch (ClassNotFoundException e)
		{
			System.out.println("Couldn't find interface for " + deObfClassName);
		}
	}
}
