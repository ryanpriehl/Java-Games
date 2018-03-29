
package dungeon_crawler;

import java.io.Serializable;

/**
 * This class creates a new item with a given name and gold value
 * 
 * @author Ryan
 *
 */
public class Item implements Serializable{

	/**
	 * Stores the name of the items
	 */
	private String name;
	/**
	 * Stores the gold value of the items
	 */
	private int goldValue;

	/**
	 * Creates a new item with given name and value
	 * 
	 * @param n
	 *            The name of the new item
	 * @param v
	 *            The value of the new item
	 */
	public Item(String n, int v) {
		name = n;
		goldValue = v;
	}

	/**
	 * Gets the name of the item
	 * 
	 * @return The name of the item
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the gold value of the item
	 * 
	 * @return The gold value of the item
	 */
	public int getValue() {
		return goldValue;
	}

}
