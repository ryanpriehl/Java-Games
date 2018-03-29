
package star_wars_text_adventure;

import java.util.Random;

/**
 * Main enemies, slightly stronger than rebels
 * @author Ryan
 */
public class Stormtrooper extends Person {

	/**
	 * Creates a new stormtrooper
	 * @param n Name of the stormtrooper
	 * @param q Quip the stormtrooper says
	 */
	public Stormtrooper(String n, String q) {
		super(n, 20, "Blaster", q);
	}

	/**
	 * Attacks target for random amount of damage with chance to miss
	 */
	@Override
	public void attack(Entity e) {
		Random rand = new Random();
		int damage = rand.nextInt(3) + 4;
		double accuracy = rand.nextDouble();
		if (accuracy > 0.2) {
			System.out.println(getName() + " shoots at " + e.getName() + " and deals " + damage + " damage.");
			e.modifyHp(e.getHp() - damage);
			if(e.getHp() <= 0) sayCatchPhrase();
		} else {
			System.out.println(getName() + " shoots at " + e.getName() + " but misses.");
		}
	}

	/**
	 * Calls attack method
	 */
	@Override
	public void doTask(Entity e) {
		attack(e);
	}
}
