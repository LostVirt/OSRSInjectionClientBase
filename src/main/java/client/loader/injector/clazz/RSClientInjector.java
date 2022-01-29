package client.loader.injector.clazz;

import client.loader.injector.ClassTransformer;
import client.loader.injector.hooks.HookedClass;
import client.loader.injector.hooks.HookedMethod;
import client.loader.injector.hooks.Hooks;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

/**
 * @author LostVirt
 * @created 29/01/2022 - 20:25
 * @project OsrsInjectionBase
 */
public class RSClientInjector extends ClassTransformer
{
	private ClassNode node;
	private HookedClass clazz;

	@Override
	public void transform(ClassNode node, HookedClass clazz)
	{
		this.node = node;
		this.clazz = clazz;

		injectDoAction();
	}

	private void injectDoAction()
	{
		HookedMethod hookedMethod = Hooks.findHookedMethod("menuAction");


		MethodNode doActionMethodNode = new MethodNode(Opcodes.ACC_PUBLIC, "doAction", hookedMethod.getDescriptor(), null, null);
		InsnList il = new InsnList();

		il.add(new VarInsnNode(Opcodes.ILOAD, 1));
		il.add(new VarInsnNode(Opcodes.ILOAD, 2));
		il.add(new VarInsnNode(Opcodes.ILOAD, 3));
		il.add(new VarInsnNode(Opcodes.ILOAD, 4));
		il.add(new VarInsnNode(Opcodes.ALOAD, 5));
		il.add(new VarInsnNode(Opcodes.ALOAD, 6));
		il.add(new VarInsnNode(Opcodes.ILOAD, 7));
		il.add(new VarInsnNode(Opcodes.ILOAD, 8));
		il.add(new LdcInsnNode(Integer.parseInt(hookedMethod.getDecoder())));
		il.add(new MethodInsnNode(Opcodes.INVOKESTATIC, hookedMethod.getObfOwner(), hookedMethod.getMethod(), hookedMethod.getObfDescriptor(), false));
		il.add(new InsnNode(Opcodes.RETURN));


		doActionMethodNode.instructions.add(il);
		node.methods.add(doActionMethodNode);
	}
}
