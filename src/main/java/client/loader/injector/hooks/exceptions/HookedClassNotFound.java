package client.loader.injector.hooks.exceptions;

/**
 * @author LostVirt
 * @created 29/01/2022 - 20:07
 * @project OsrsInjectionBase
 */
public class HookedClassNotFound extends RuntimeException
{
	public HookedClassNotFound(String message)
	{
		super(message);
	}
}
