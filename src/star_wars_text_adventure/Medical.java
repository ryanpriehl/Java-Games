
package star_wars_text_adventure;

/**
 * Used with Healable interface to restore hp to characters
 * @author Ryan
 *
 */
public class Medical extends Droid {
	
	/**
	 * Creates a new medical droid
	 * @param n Name of the new medical droid
	 * @param t Number of tasks the medical droid can perform
	 */
	public Medical(String n, int t) {
		super(n, 10, t);
	}

	/**
	 * Heals target character
	 */
	@Override
	public void doTask(Entity e) {
		if(e instanceof Healable)
		{
			Healable h = (Healable) e;
			h.heal(10);
		}
		else
			System.out.println("That entity is not healable.");
	}
}
