package dungeon_crawler_gui;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * This is the Enemy class that extends from the Character class.
 * Has basic getters/setters as well as methods to draw, attack,
 * and get basic info about the enemy as a String.
 * 
 * @author Ryan Riehl
 */
public class Enemy extends Character {

	/**
	 * The image used to draw this enemy to the panel
	 */
	private BufferedImage image;
	
	/**
	 * Stores the location of this enemy in the level array
	 */
	private Point location;
	
	/**
	 * Stores the Item dropped when this enemy is killed
	 */
	private Item item;
	
	/**
	 * Stores the attack value of the enemy
	 */
	private int attack;

	/**
	 * 
	 * @param n The enemy's name
	 * @param l The enemy's level
	 * @param h The enemy's hp
	 * @param g How much gold the enemy has
	 * @param im The image used to draw this enemy
	 * @param p The enemy's location in the level
	 * @param i The enemy's Item
	 * @param a The enemy's attack value
	 */
	public Enemy(String n, int l, int h, int g, BufferedImage im, Point p, Item i, int a) {
		super(n, l, (h * l), g);
		item = i;
		location = p;
		image = im;
		attack = a;
	}

	/**
	 * Attacks the given character for a random amount of damage based on 
	 * this enemy's level and attack
	 * @param c The target character for this enemy to attack
	 */
	@Override
	public void attack(Character c) {
		Random rand = new Random();
		int damage = rand.nextInt(3) + getLevel() + attack;
		c.takeDamage(damage);
	}
	
	/**
	 * Gets the image sued to draw this enemy
	 * @return The BufferedImage for this enemy
	 */
	public BufferedImage getImage(){
		return image;
	}
	
	/**
	 * Gets the location of this enemy in the level
	 * @return A point for the enemy's location
	 */
	public Point getLocation(){
		return location;
	}
	
	/**
	 * Gets the Item this enemy has
	 * @return The Item for this enemy
	 */
	public Item getItem(){
		return item;
	}
	
	/**
	 * Sets the location of this enemy to the given point
	 * @param p The point to set the location to
	 */
	public void setLocation(Point p){
		location = p;
	}
	
	/**
	 * Calculates and sets the gold of this enemy based on its level and attack
	 */
	public void setGold(){
		Random rand = new Random();
		setGold(rand.nextInt(5) + 3*getLevel() + 3*attack);
	}
	
	/**
	 * Gets the attack of this enemy
	 * @return An int for the enemy's attack
	 */
	public int getAttack(){
		return attack;
	}
	
	/**
	 * Prints basic information about this enemy
	 */
	public String toString(){
		return getName() +" (lvl " +getLevel() +") HP: " +getHp() + " Attack: " +(attack+getLevel()) +" - " +(attack + getLevel() +2);
	}
	
	/**
	 * Draws this enemy in the Panel based on its location
	 * @param g Used for drawing
	 * @param p The panel to draw the enemy in
	 */
	public void draw(Graphics g, Panel p){
		g.drawImage(image, 105 + location.y * 105, 105 + location.x * 105, p);
	}
}