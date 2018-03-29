
package star_wars_text_adventure;

import java.util.Random;

/**
 * Sith lord class for the main enemy, can attack and use the force
 * @author Ryan
 *
 */
public class SithLord extends Person implements HasForce {

	/**
	 * Color of the sith's lightsaber
	 */
	private String saberColor;

	/**
	 * Creates a new Sith Lord
	 * @param n Name of the Sith Lord
	 * @param q Quip the Sith Lord says
	 * @param c Color of the Sith Lord's lightsaber
	 */
	public SithLord(String n, String q, String c) {
		super(n, 40, "Lightsaber", q);
		saberColor = c;
	}

	/**
	 * attacks for a random amount of damage, has a chance to miss
	 */
	@Override
	public void attack(Entity e) {
		Random rand = new Random();
		int damage = rand.nextInt(3) + 5;
		double accuracy = rand.nextDouble();
		if (accuracy > 0.1) {
			System.out.println(getName() + " swings their " + saberColor + " lightsaber at " + e.getName()
					+ " and deals " + damage + " damage.");
			e.modifyHp(e.getHp() - damage);
			if (e.getHp() <= 0)
				sayCatchPhrase();
		} else {
			System.out.println(
					getName() + " swings their " + saberColor + " lightsaber at " + e.getName() + " but misses.");
		}
	}

	/**
	 * Calls attack or useForce method randomly
	 */
	@Override
	public void doTask(Entity e) {
		if(Math.random() < 0.2)
			useForce(e);
		else
			attack(e);
	}

	/**
	 * Uses force lightning as alternate attack to lightsaber
	 */
	@Override
	public void useForce(Entity e) {
		Random rand = new Random();
		int damage = rand.nextInt(3) + 7;
		double accuracy = rand.nextDouble();
		if (accuracy > 0.1) {
			System.out.println(getName() + " shoots lightning at " + e.getName() + " and deals " + damage + " damage.");
			e.modifyHp(e.getHp() - damage);
			if (e.getHp() <= 0)
				sayCatchPhrase();
		} else {
			System.out.println(getName() + " shoots lightning at " + e.getName() + " but misses.");
		}

	}
}
