package client.loader.injector.asm;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;

/**
 * @author LostVirt
 * @created 29/01/2022 - 20:29
 * @project OsrsInjectionBase
 */
public class AsmUtil
{
	public static boolean addInterface(ClassNode node, Class<?> interfaceClass)
	{
		return node.interfaces.add(interfaceClass.getCanonicalName().replace('.', '/'));
	}

	public static void setSuperClass(ClassNode node, Class<?> currentSuperClass, Class<?> newSuperClass)
	{
		String currentSuperClassName = currentSuperClass.getCanonicalName().replace('.', '/');
		String newSuperClassName = newSuperClass.getCanonicalName().replace('.', '/');

		for (MethodNode methodNode : node.methods)
		{
			for (AbstractInsnNode abstractInsnNode : methodNode.instructions)
			{
				if (abstractInsnNode.getOpcode() == Opcodes.INVOKESPECIAL && abstractInsnNode instanceof MethodInsnNode methodInsnNode)
				{
					if (methodInsnNode.owner.equals(currentSuperClassName))
					{
						methodInsnNode.owner = newSuperClassName;
						break;
					}
				}
			}
		}

		node.superName = newSuperClassName;
	}
}
