package dungeon_crawler_gui;

import java.io.Serializable;

/**
 * This is the generic character class from which the Hero and Enemy classes extend
 * and has basic getters/setters and fucntionality for the classes.
 * 
 * @author Ryan Riehl
 */
public abstract class Character implements Serializable{
	
	/**
	 * Stores this character's name
	 */
	private String name;	
	
	/**
	 * Stores this character's level
	 */
	private int level;	
	
	/**
	 * Stores this character's hp
	 */
	private int hp;
	
	/**
	 * Stores this character's gold value
	 */
	private int gold;

	/**
	 * Creates a new character with given parameters
	 * @param n The name of the character
	 * @param l The level of the character
	 * @param h The hp of the character
	 * @param g The gold of the character
	 */
	public Character(String n, int l, int h, int g) {
		name = n;
		level = l;
		hp = h;
		gold = g;
	}

	/**
	 * Attacks the given character for a somewhat random amount of damage
	 * @param c The character to attack
	 */
	public abstract void attack(Character c);

	/**
	 * Gets the name of this character
	 * @return A string for the name of this character
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the hp of this character
	 * @return An int for the hp of this character
	 */
	public int getHp() {
		return hp;
	}

	/**
	 * Gets the level of this character
	 * @return An int for the level of the character
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * Gets the gold amount for this character
	 * @return The amount of gold this character has
	 */
	public int getGold() {
		return gold;
	}
	
	/**
	 * Sets this character's gold amount to the given value
	 * @param g How much to set the gold to
	 */
	public void setGold(int g){
		gold = g;
	}

	/**
	 * Increments the level of the character up to 3
	 */
	public void increaseLevel() {
		if(level < 3){
			level++;
			hp = hp * 2;
		}
	}

	/**
	 * Heals this character setting their hp to the given amount
	 * @param h What to heal this character to
	 */
	public void heal(int h) {
		hp = h;
	}

	/**
	 * Deals damage to this character, reduces their hp by the given amount
	 * @param h How much damage to deal to the character
	 */
	public void takeDamage(int h) {
		hp = hp - h;
		if (hp < 0)
			hp = 0;
	}

	/**
	 * Adds the given amount of gold to this player's current gold
	 * @param g The amount of gold to add
	 */
	public void collectGold(int g) {
		gold = gold + g;
	}

}
