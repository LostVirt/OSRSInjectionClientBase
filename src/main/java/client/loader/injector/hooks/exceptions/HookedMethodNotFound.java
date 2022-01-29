package client.loader.injector.hooks.exceptions;

/**
 * @author LostVirt
 * @created 29/01/2022 - 20:06
 * @project OsrsInjectionBase
 */
public class HookedMethodNotFound extends RuntimeException
{
	public HookedMethodNotFound(String message)
	{
		super(message);
	}
}
