package dungeon_crawler_gui;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import javax.imageio.ImageIO;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * Creates a list of items from ItemList.txt and can randomly choose and return
 * one of the possible items.
 * 
 * @author Ryan Riehl
 */
public class ItemGenerator {

	/**
	 * Stores the list of items
	 */
	private ArrayList<Item> itemList = new ArrayList<Item>();

	/**
	 * Reads ItemList.txt to create an ArrayList of the all available items
	 */
	public ItemGenerator() {
		try {
			Scanner read = new Scanner(new File(Panel.FILE_PATH +"/src/dungeon_crawler_gui/ItemList.txt"));
			while (read.hasNextLine()) {
				Item i = null;
				read.useDelimiter(",");
				String n = read.next();
				int v = Integer.valueOf(read.next());
				read.skip(",");
				String source = read.nextLine();
				try {
					i = new Item(n, v, ImageIO.read(new File(Panel.FILE_PATH +"/src/dungeon_crawler_gui/Images/" +source)), new Point(0,0));
				} catch (IOException e) {}
				itemList.add(i);
			}
			read.close();
		} catch (FileNotFoundException e) {}
	}

	/**
	 * Gets a random item from the list of items
	 * @return The randomly chosen item
	 */
	public Item generateItem() {
		Random rand = new Random();
		int random = rand.nextInt(itemList.size());
		Item i = itemList.get(random);
		Item newItem = new Item(i.getName(), i.getValue(), (BufferedImage) i.getImage().getImage(), i.getLocation());
		return newItem;
	}
}
