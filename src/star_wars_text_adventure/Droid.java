
package star_wars_text_adventure;

/**
 * Superclass for medical and astromech droids
 * @author Ryan
 *
 */
public abstract class Droid extends Entity
{
	/**
	 * Number of tasks the droid can perform
	 */
	private int numTasks;
	
	/**
	 * Creates a new droid
	 * @param n Name of the droid
	 * @param h Hp of the droid
	 * @param t Number of tasks the droid can perform
	 */
	public Droid(String n, int h, int t)
	{
		super(n, h);
		numTasks = t;
	}
	
	/**
	 * Returns how many tasks are left
	 * @return The number of tasks
	 */
	public int getNumTasks()
	{
		return numTasks;
	}
	
	/**
	 * Decrements number of tasks
	 */
	public void useTask()
	{
		numTasks--;
	}
}
