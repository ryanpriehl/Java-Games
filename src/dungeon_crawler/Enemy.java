
package dungeon_crawler;

import java.util.Random;

/**
 * This class is used to create and use the enemies encountered in the game. The
 * list of enemies is read in from EnemyList.txt through EnemyGenerator.
 * 
 * @author Ryan
 *
 */
public class Enemy extends Character {

	/**
	 * Stores the Item the enemy is carrying and will drop upon death
	 */
	private Item item;

	/**
	 * Creates a new enemy with given characteristics
	 * 
	 * @param n
	 *            The enemy's name
	 * @param q
	 *            The enemy's quip
	 * @param l
	 *            The enemy's level
	 * @param h
	 *            The enemy's hp
	 * @param g
	 *            Ho much gold the enemy has/will drop
	 * @param i
	 *            The Item the enemy has/will drop
	 */
	public Enemy(String n, String q, int l, int h, int g, Item i) {
		super(n, q, l, (h * l), g);
		item = i;
	}

	/**
	 * Attacks the target character for a random amount of damage
	 */
	@Override
	public void attack(Character c) {
		Random rand = new Random();
		int damage = rand.nextInt(3) + getLevel();
		System.out.println(getName() + " attacks " + c.getName() + " for " + damage + " damage.");
		c.takeDamage(damage);
	}

	/**
	 * Gets the item found on this enemy
	 * 
	 * @return The item found
	 */
	public Item getItem() {
		return item;
	}
}