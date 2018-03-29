
package star_wars_text_adventure;

/**
 * Used in imperial base mission, door to leave the base.
 * @author Ryan
 *
 */
public class Door extends Entity 
{

	/**
	 * Creates a new door
	 * @param n Name of the door
	 * @param h Hp of the door
	 */
	public Door(String n, int h) 
	{
		super(n, h);
	}

	/**
	 * Prints that the door has been opened.
	 */
	@Override
	public void doTask(Entity e) 
	{
		System.out.println("Door successfully opened!");
	}

}
