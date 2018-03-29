
package star_wars_text_adventure;

import java.util.Random;

/**
 * Player's character inherits from Person
 * @author Ryan
 *
 */
public class Jedi extends Person implements Healable, HasForce {

	/**
	 * Color of the player's lightsaber
	 */
	private String saberColor;

	/**
	 * Creates a new Jedi
	 * @param n Name of the jedi
	 * @param q Quip the jedi says
	 * @param c Color of the jedi's lightsaber
	 */
	public Jedi(String n, String q, String c) {
		super(n, 40, "Lightsaber", q);
		saberColor = c;
	}

	/**
	 * Attacks target enemy for random amount of damage with chance to miss.
	 */
	@Override
	public  void attack(Entity e) {
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
	 * Calls attack method
	 */
	@Override
	public void doTask(Entity e) {
		if(task.equals("attack")) attack(e);
		else if (task.equals("force")) useForce(e);
	}

	/**
	 * Restores player's hp, cannot go over cap of 40
	 */
	@Override
	public void heal(int hp) {
		System.out.println(getName() + " is healed for 10 hp.");
		modifyHp(getHp() + hp);
		if (getHp() > 40) {
			modifyHp(40);
		}
	}

	/**
	 * Another attack but with different damage and accuracy values.
	 */
	@Override
	public void useForce(Entity e) {
		Random rand = new Random();
		int damage = rand.nextInt(2) + 5;
		System.out.println(getName() + " uses the force to push " + e.getName() + " and deals " + damage + " damage.");
		e.modifyHp(e.getHp() - damage);
		if (e.getHp() <= 0)
			sayCatchPhrase();

	}
}
