package client.loader.util;

import client.loader.applet.ConfigReader;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;

import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;

import static client.util.Constants.GAMEPACK;
import static client.util.Constants.INJECTED_GAMEPACK;

/**
 * @author LostVirt
 * @created 29/01/2022 - 19:46
 * @project OsrsInjectionBase
 */
public class JarHelper
{

	private Manifest manifest;

	private Map<String, ClassNode> classes;

	private JarFile jarFile;

	public JarHelper(ConfigReader config) throws IOException
	{
		if (GAMEPACK.exists())
		{
			jarFile = new JarFile(GAMEPACK);
		}
		else
		{
			getJarFile(config.getJarUrl());
		}
	}

	public Map<String, ClassNode> getClasses()
	{
		if (classes != null) return classes;

		assert jarFile != null : "The jar file must not be null!";

		classes = new HashMap<>();
		try
		{
			Enumeration<JarEntry> entries = jarFile.entries();
			this.manifest = jarFile.getManifest();
			do
			{
				JarEntry entry = entries.nextElement();

				if (!entry.getName().contains(".class")) continue;

				ClassReader classReader = new ClassReader(jarFile.getInputStream(entry));
				ClassNode classNode = new ClassNode();
				classReader.accept(classNode, ClassReader.SKIP_DEBUG);
				this.classes.put(entry.getName(), classNode);
			}
			while (entries.hasMoreElements());
		}
		catch (IOException e)
		{
			e.printStackTrace();
			System.out.println("Failed to load jarfile.");
			System.exit(0);
		}
		return classes;
	}


	public void saveJar()
	{
		try (JarOutputStream jos = new JarOutputStream(new FileOutputStream(INJECTED_GAMEPACK), this.manifest))
		{
			Collection<ClassNode> classNodes = this.classes.values();

			for (ClassNode node : classNodes)
			{

				JarEntry newEntry = new JarEntry(node.name + ".class");
				jos.putNextEntry(newEntry);

				ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS);
				node.accept(writer);
				jos.write(writer.toByteArray());
				jos.closeEntry();

			}

		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public HashMap<String, byte[]> getClassBytes()
	{
		HashMap<String, byte[]> map = new HashMap<>();
		for (String clazzName : classes.keySet())
		{
			ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS);
			ClassNode classNode = classes.get(clazzName);
			classNode.accept(classWriter);
			map.put(clazzName.replace(".class", "").replace("/", "."), classWriter.toByteArray());
		}
		return map;
	}

	private void getJarFile(String jarUrl)
	{
		URL url;
		URLConnection con;
		DataInputStream dis;
		FileOutputStream fos;
		byte[] fileData;
		try
		{

			System.out.println("Downloading Gamepack...");
			url = new URL(jarUrl); //File Location goes here
			con = url.openConnection(); // open the url connection.
			dis = new DataInputStream(con.getInputStream());
			fileData = new byte[con.getContentLength()];
			for (int q = 0; q < fileData.length; q++)
			{
				fileData[q] = dis.readByte();
			}
			dis.close(); // close the data input stream
			fos = new FileOutputStream(GAMEPACK); //FILE Save Location goes here
			fos.write(fileData);  // write out the file we want to save.
			fos.close(); // close the output stream writer

			jarFile = new JarFile(GAMEPACK);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
