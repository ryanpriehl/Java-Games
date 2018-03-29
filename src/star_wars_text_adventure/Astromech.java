
package star_wars_text_adventure;

/**
 * Astromech droid can hack computers and open doors.
 * 
 * @author Ryan
 */
public class Astromech extends Droid {

	/**
	 * Creates a new astromech droid
	 * 
	 * @param n
	 *            Name of the droid
	 * @param t
	 *            Number of tasks
	 */
	public Astromech(String n, int t) {
		super(n, 10, t);
	}

	/**
	 * Calls the doTask() of input entity (computer or door).
	 */
	@Override
	public void doTask(Entity e) {
		if (e instanceof Door || e instanceof Computer) {
			e.doTask(e);
		} else if (e instanceof Droid) {
			e.modifyHp(e.getHp() + 10);
			System.out.println(e.getName() + " healed for 10 hp.");
			if (e.getHp() > 10)
				e.modifyHp(10);
		}
	}
}
