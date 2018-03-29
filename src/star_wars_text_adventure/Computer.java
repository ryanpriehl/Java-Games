
package star_wars_text_adventure;

/**
 * Used in "Invade the Imperial Base" mission
 * @author Ryan
 *
 */
public class Computer extends Entity 
{
	/**
	 * Creates a new computer
	 * @param n Name of the computer
	 * @param h Hp of the computer
	 */
	public Computer(String n, int h) 
	{
		super(n, h);
	}

	/**
	 * Prints a message saying the hack was successful, signaling end of mission.
	 */
	@Override
	public void doTask(Entity e) 
	{
		System.out.println("System successfully hacked!");
	}
}
