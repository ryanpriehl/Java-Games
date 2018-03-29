
package dungeon_crawler;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 * This class is for the Hero the player plays as.
 * 
 * @author Ryan
 *
 */
public class Hero extends Character implements Serializable {

	/**
	 * Stores the items in the Hero's inventory
	 */
	private ArrayList<Item> items = new ArrayList<Item>();
	/**
	 * Stores the Hero's location i the map
	 */
	private Point location;

	/**
	 * Creates a new Hero with given characteristics
	 * 
	 * @param n
	 *            The name of the new Hero
	 * @param q
	 *            The quip of the new Hero
	 * @param start
	 *            The location the Hero starts at
	 */
	public Hero(String n, String q, Point start) {
		super(n, q, 1, 10, 0);
		location = start;
	}

	@Override
	/**
	 * Attacks the given character for a random amount of damage
	 */
	public void attack(Character c) {
		Random rand = new Random();
		int damage = rand.nextInt(3) + 1 + getLevel();
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).getName().equals("Sword")
					|| items.get(i).getName().equals("Mace")) {
				damage++;
				break;
			}
		}
		System.out.println(getName() + " attacks " + c.getName() + " for "
				+ damage + " damage.");
		c.takeDamage(damage);
	}

	/**
	 * Gets the ArrayList that stores the player's items
	 * 
	 * @return The ArrayList that stores the player's items
	 */
	public ArrayList<Item> getItems() {
		return items;
	}

	/**
	 * Adds an item to the player's inventory if there is room
	 * 
	 * @param i
	 *            The item to add
	 * @return True if the item was successfully added, false if not
	 */
	public boolean pickUpItem(Item i) {
		if (items.size() >= 5) {
			System.out.println("Can't carry any more items.");
			System.out
					.println("Would you like to sell this item?\n1. yes\n2. no");
			int choice = Main.checkInt(1, 2);
			if (choice == 1) {
				System.out.println(i.getName() + " sold for " + i.getValue()
						+ " gold.");
				collectGold(i.getValue());
			} else if (choice == 2) {
				System.out.println(i.getName() + " was discarded.");
			}
			return false;
		} else {
			System.out.println(i.getName() + " added to inventory.");
			items.add(i);
			return true;
		}
	}

	/**
	 * Removes the item of the given name from the player's inventory
	 * 
	 * @param i
	 *            The name of the item to remove
	 */
	public void removeItem(Item i) {
		for (int index = 0; index < items.size(); index++) {
			if (i.getName().equals(items.get(index).getName())) {
				items.remove(index);
				return;
			}
		}
	}

	/**
	 * Removes the item at the given index from the player's inventory
	 * 
	 * @param index
	 *            The index of the item to remove
	 */
	public void removeItem(int index) {
		items.remove(index);
	}

	/**
	 * Prints the Hero's hp, gold, and items
	 */
	public void showInventory() {
		System.out.println("**********");
		System.out.println(getName() + "'s Inventory");
		System.out.println("**********");
		System.out.println("Hp: " + getHp());
		System.out.println("Gold: " + getGold());
		if (items.size() > 0) {
			for (int i = 0; i < items.size(); i++) {
				System.out.println((i + 1) + ". " + items.get(i).getName());
			}
		} else
			System.out.println("Inventory empty.");
		System.out.println();
	}

	/**
	 * Gets the location of the Hero
	 * 
	 * @return The point where the Hero is located
	 */
	public Point getLocation() {
		return location;
	}

	/**
	 * Sets the Hero's location to a given point
	 * 
	 * @param p
	 *            The point to set the Hero's location to
	 */
	public void setLocation(Point p) {
		location = p;
	}

	/**
	 * Moves the Hero one room north
	 * 
	 * @param l
	 *            The level to move the player in
	 * @return The Hero of the room the player is now in
	 */
	public char goNorth(Level l) {
		Point loc = new Point(getLocation().x - 1, getLocation().y);
		setLocation(loc);
		return l.getRoom(loc);
	}

	/**
	 * Moves the Hero one room south
	 * 
	 * @param l
	 *            The level to move the player in
	 * @return The Hero of the room the player is now in
	 */
	public char goSouth(Level l) {
		Point loc = new Point(getLocation().x + 1, getLocation().y);
		setLocation(loc);
		return l.getRoom(loc);
	}

	/**
	 * Moves the Hero one room east
	 * 
	 * @param l
	 *            The level to move the player in
	 * @return The Hero of the room the player is now in
	 */
	public char goEast(Level l) {
		Point loc = new Point(getLocation().x, getLocation().y + 1);
		setLocation(loc);
		return l.getRoom(loc);
	}

	/**
	 * Moves the Hero one room west
	 * 
	 * @param l
	 *            The level to move the player in
	 * @return The Hero of the room the player is now in
	 */
	public char goWest(Level l) {
		Point loc = new Point(getLocation().x, getLocation().y - 1);
		setLocation(loc);
		return l.getRoom(loc);
	}

	/**
	 * Checks if the player can move north
	 * 
	 * @return True if the move is viable
	 */
	public boolean canGoNorth() {
		if (getLocation().x != 0)
			return true;
		return false;
	}

	/**
	 * Checks if the player can move south
	 * 
	 * @return True if the move is viable
	 */
	public boolean canGoSouth() {
		if (getLocation().x != 3)
			return true;
		return false;
	}

	/**
	 * Checks if the player can move east
	 * 
	 * @return True if the move is viable
	 */
	public boolean canGoEast() {
		if (getLocation().y != 3)
			return true;
		return false;
	}

	/**
	 * Checks if the player can move west
	 * 
	 * @return True if the move is viable
	 */
	public boolean canGoWest() {
		if (getLocation().y != 0)
			return true;
		return false;
	}
}
