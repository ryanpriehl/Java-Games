
package dungeon_crawler;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.io.*;

/**
 * Reads in ItemList.txt to create the Items and stores them
 * 
 * @author Ryan
 *
 */
public class ItemGenerator {

	/**
	 * Stores the items read in from the text document
	 */
	private static ArrayList<Item> itemList = new ArrayList<Item>();

	/**
	 * Fills the array list with the items read in from ItemList.txt
	 */
	public ItemGenerator() {
		try {
			Scanner read = new Scanner(new File(Main.FILE_PATH +"/src/dungeon_crawler/ItemList.txt"));
			while (read.hasNextLine()) {
				read.useDelimiter(",");
				String n = read.next();
				read.skip(",");
				int v = Integer.valueOf(read.nextLine());
				Item item = new Item(n, v);
				itemList.add(item);
			}
			read.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
		}
	}

	/**
	 * Gets a random item from the array list of items
	 * 
	 * @return The random item
	 */
	public Item generateItem() {
		Random rand = new Random();
		int random = rand.nextInt(itemList.size());
		return itemList.get(random);
	}
}
