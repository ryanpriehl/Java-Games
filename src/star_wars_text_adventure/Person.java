
package star_wars_text_adventure;

/**
 * Superclass for jedi, sith, rebel, and stormtrooper.
 * @author Ryan
 *
 */
public abstract class Person extends Entity {

	/**
	 * The weapon the Person uses
	 */
	private String weapon;
	/**
	 * The quip the Person says
	 */
	private String quip;

	/**
	 * Creates a new Person
	 * @param n Name of the Person
	 * @param h How much hp the Person has
	 * @param w The weapon the Person uses
	 * @param q The quip the Person says
	 */
	public Person(String n, int h, String w, String q) {
		super(n, h);
		weapon = w;
		quip = q;
	}

	/**
	 * Says the person's quip
	 */
	public void sayCatchPhrase() {
		System.out.println(quip);
	}

	/**
	 * Attacks the enemy dealing damage to them
	 * @param e The target Entity to attack
	 */
	public abstract void attack(Entity e);

	/**
	 * Returns the weapon the person uses
	 * @return The weapon the person uses
	 */
	public String getWeapon() {
		return weapon;
	}
}
