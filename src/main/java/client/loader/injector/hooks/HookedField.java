package client.loader.injector.hooks;

import client.util.DMath;
import lombok.Value;

/**
 * @author LostVirt
 * @created 29/01/2022 - 20:04
 * @project OsrsInjectionBase
 */
@Value
public class HookedField
{
	String name;
	String owner;
	String obfOwner;
	String field;
	String obfDescriptor;
	String descriptor;
	long multiplier;
	boolean isStatic;

	public long getInverseMultiplier()
	{
		return DMath.isInversable(multiplier) ? DMath.modInverse(multiplier) : 0;
	}
}
