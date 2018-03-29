package dungeon_crawler_gui;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 * This class creates the hero used by the player.
 * 
 * @author Ryan Riehl
 */
public class Hero extends Character implements Serializable {

	/**
	 * Stores the items in the hero's inventory
	 */
	private ArrayList<Item> items;
	
	/**
	 * Stores the hero's location in the level
	 */
	private Point location;

	/**
	 * Creates a new Hero with the given name at the given location
	 * @param n The name of the hero
	 * @param start The start location of the hero
	 */
	public Hero(String n, Point start) {
		super(n, 1, 20, 0);
		items = new ArrayList<Item>();
		location = start;
	}

	/**
	 * Attacks the given character for an amount of damage based on level and randomness
	 * @param c The character to attack
	 */
	@Override
	public void attack(Character c) {
		Random rand = new Random();
		int damage = rand.nextInt(3) + 3 + getLevel();
		c.takeDamage(damage);
	}

	/**
	 * Gets the arraylist of items in the hero's inventory
	 * @return The arraylist of the hero's items
	 */
	public ArrayList<Item> getItems() {
		return items;
	}

	/**
	 * Adds the given item to the player's inventory if there is room
	 * @param i The item to add
	 */
	public void pickUpItem(Item i) {
		if(items.size() < 5)
			items.add(i);
	}

	/**
	 * Removes one of the given item from the hero's inventory
	 * @param i The item to remove
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
	 * Removes the item at the given index from the hero's inventory
	 * @param index The index to remove the item at
	 */
	public void removeItem(int index) {
		items.remove(index);
	}

	/**
	 * Gets the location of the hero in the level
	 * @return A point for the hero's location
	 */
	public Point getLocation() {
		return location;
	}

	/**
	 * Sets the location of the hero to the given point
	 * @param p The point to set the ehro's location to
	 */
	public void setLocation(Point p) {
		location = p;
	}
	
	/**
	 * Checks if the hero has a potion in their inventory
	 * @return True if they have a potion, false otherwise
	 */
	public boolean hasPotion(){
		for(Item i : items){
			if(i != null && i.getName().equals("Potion"))
				return true;
		}
		return false;
	}

	/**
	 * Moves the north west in the given level, returns what's in the room at the new location
	 * @param l The level the hero is in
	 * @return A char for what's in the room at the new location
	 */
	public char goNorth(Level l) {
		Point loc = new Point(getLocation().x - 1, getLocation().y);
		setLocation(loc);
		return l.getRoom(loc);
	}

	/**
	 * Moves the hero south in the given level, returns what's in the room at the new location
	 * @param l The level the hero is in
	 * @return A char for what's in the room at the new location
	 */
	public char goSouth(Level l) {
		Point loc = new Point(getLocation().x + 1, getLocation().y);
		setLocation(loc);
		return l.getRoom(loc);
	}

	/**
	 * Moves the hero east in the given level, returns what's in the room at the new location
	 * @param l The level the hero is in
	 * @return A char for what's in the room at the new location
	 */
	public char goEast(Level l) {
		Point loc = new Point(getLocation().x, getLocation().y + 1);
		setLocation(loc);
		return l.getRoom(loc);
	}

	/**
	 * Moves the hero west in the given level, returns what's in the room at the new location
	 * @param l The level the hero is in
	 * @return A char for what's in the room at the new location
	 */
	public char goWest(Level l) {
		Point loc = new Point(getLocation().x, getLocation().y - 1);
		setLocation(loc);
		return l.getRoom(loc);
	}

	/**
	 * Checks if the hero can go north based on the location in the level
	 * @return True if they can move north, false otherwise
	 */
	public boolean canGoNorth() {
		if (getLocation().x != 0)
			return true;
		return false;
	}

	/**
	 * Checks if the hero can go south based on the location in the level
	 * @return True if they can move south, false otherwise
	 */
	public boolean canGoSouth() {
		if (getLocation().x != 3)
			return true;
		return false;
	}

	/**
	 * Checks if the hero can go east based on the location in the level
	 * @return True if they can move east, false otherwise
	 */
	public boolean canGoEast() {
		if (getLocation().y != 3)
			return true;
		return false;
	}

	/**
	 * Checks if the hero can go west based on the location in the level
	 * @return True if they can move west, false otherwise
	 */
	public boolean canGoWest() {
		if (getLocation().y != 0)
			return true;
		return false;
	}
}
