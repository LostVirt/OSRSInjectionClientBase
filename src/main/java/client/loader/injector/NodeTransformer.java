package client.loader.injector;

import org.objectweb.asm.tree.ClassNode;

/**
 * @author LostVirt
 * @created 29/01/2022 - 20:27
 * @project OsrsInjectionBase
 */
public abstract class NodeTransformer
{
	public abstract void transform(ClassNode classNode);
}
