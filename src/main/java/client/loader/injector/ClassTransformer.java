package client.loader.injector;

import client.loader.injector.hooks.HookedClass;
import org.objectweb.asm.tree.ClassNode;

/**
 * @author LostVirt
 * @created 29/01/2022 - 19:58
 * @project OsrsInjectionBase
 */
public abstract class ClassTransformer
{
	public abstract void transform(ClassNode node, HookedClass clazz);
}
