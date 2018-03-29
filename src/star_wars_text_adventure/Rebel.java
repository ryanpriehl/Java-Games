
package star_wars_text_adventure;

import java.util.Random;

/**
 * Main allies. Slightly weaker than stormtroopers.
 * @author Ryan
 *
 */
public class Rebel extends Person implements Healable {

	/**
	 * Creates a new rebel
	 * @param n Name of the rebel
	 * @param q Quip the rebel says
	 */
	public Rebel(String n, String q) {
		super(n, 20, "Blaster", q);
	}

	/**
	 * Attacks target for random amount of damage with chance to miss
	 */
	@Override
	public void attack(Entity e) {
		Random rand = new Random();
		int damage = rand.nextInt(3) + 3;
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

	/**
	 * Calls heal method
	 */
	@Override
	public void heal(int hp) {
		System.out.println(getName() +" is healed for 10 hp.");
		modifyHp(getHp() + hp);
		if (getHp() > 20) {
			modifyHp(20);
		}
	}
}
