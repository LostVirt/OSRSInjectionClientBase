package client.loader.injector.node;

import client.loader.injector.NodeTransformer;
import client.loader.injector.asm.AsmUtil;
import internal.RSCanvas;
import org.objectweb.asm.tree.ClassNode;

import java.awt.*;

/**
 * @author LostVirt
 * @created 29/01/2022 - 20:27
 * @project OsrsInjectionBase
 */
public class RSCanvasInjector extends NodeTransformer
{
	@Override
	public void transform(ClassNode node)
	{
		AsmUtil.setSuperClass(node, Canvas.class, RSCanvas.class);
	}
}
