package client.loader.injector.hooks;

import lombok.Value;

/**
 * @author LostVirt
 * @created 29/01/2022 - 20:04
 * @project OsrsInjectionBase
 */
@Value
public class HookedMethod
{
	String name;
	String owner;
	String obfOwner;
	String method;
	String obfDescriptor;
	String descriptor;
	String decoder;
	boolean isStatic;
}
