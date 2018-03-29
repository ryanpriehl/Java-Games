
package dungeon_crawler;

import java.io.Serializable;

/**
 * This is the superclass for the Hero and Enemy classes
 * 
 * @author Ryan
 *
 */
public abstract class Character implements Serializable{

	/**
	 * Store the Character's name and quip respectively
	 */
	private String name, quip;
	/**
	 * Store the Character's level, hp, and gold respectively
	 */
	private int level, hp, gold;

	/**
	 * Creates a new Character with given characteristics
	 * @param n The Character's name
	 * @param q The Character's quip
	 * @param l The Character's level
	 * @param h The Character's hp
	 * @param g The Character's gold
	 */
	public Character(String n, String q, int l, int h, int g) {
		name = n;
		quip = q;
		level = l;
		hp = h;
		gold = g;
	}

	/**
	 * Attacks the given Character for a random amount of damage
	 * @param c The Character to attack
	 */
	public abstract void attack(Character c);

	/**
	 * Gets the character's name
	 * @return The character's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the character's quip
	 * @return The character's quip
	 */
	public String getQuip() {
		return quip;
	}

	/**
	 * Gets the character's hp
	 * @return The character's hp
	 */
	public int getHp() {
		return hp;
	}

	/**
	 * Gets the character's level
	 * @return The character's level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * Gets the character's gold
	 * @return How much gold the character has
	 */
	public int getGold() {
		return gold;
	}

	/**
	 * Increases the character's level and doubles their hp
	 */
	public void increaseLevel() {
		level++;
		hp = hp * 2;
		if (level > 3)
			level = 3;
	}

	/**
	 * Restores the character to their max hp
	 * 
	 * @param h
	 *            This character's max hp
	 */
	public void heal(int h) {
		hp = h;
	}

	/**
	 * Reduces the character's hp by a given amount
	 * 
	 * @param h
	 *            How much to reduce the character's hp by
	 */
	public void takeDamage(int h) {
		hp = hp - h;
		if (hp < 0)
			hp = 0;
	}

	/**
	 * Increases how much gold the character has
	 * 
	 * @param g
	 *            How much gold to add
	 */
	public void collectGold(int g) {
		gold = gold + g;
	}

	/**
	 * Prints information about the character
	 */
	public void display() {
		System.out.println(getName() + " is a level " + getLevel() + " character with " + getHp() + " hp.");
	}
}
