package internal.interfaces;

/**
 * @author LostVirt
 * @created 29/01/2022 - 20:42
 * @project OsrsInjectionBase
 */
public interface RSClient
{
	void doAction(int secondary, int tertiary, int opcode, int primary, String menuOption, String menuTarget, int mouseX, int mouseY);
}
