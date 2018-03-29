package dungeon_crawler_gui;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import javax.swing.ImageIcon;

/**
 * This class is for creating items that are used for the game
 * 
 * @author Ryan Riehl
 */
public class Item implements Serializable{

	/**
	 * Stores the name of this item
	 */
	private String name;
	
	/**
	 * Stores the int value this item sells for
	 */
	private int goldValue;
	
	/**
	 * Stores the image used to show this item in the panel
	 */
	private ImageIcon image;
	
	/**
	 * Stores the item's location in the level array
	 */
	private Point location;

	/**
	 * Creates a new item
	 * @param n The item's name
	 * @param v The item's gold value
	 * @param i The image for this item
	 * @param p A point for the item's location
	 */
	public Item(String n, int v, BufferedImage i, Point p) {
		name = n;
		goldValue = v;
		image = new ImageIcon(i);
		location = p;
	}

	/**
	 * Gets the name of this item
	 * @return A String for the name of the item
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the gold value of this item
	 * @return An int for this item's value
	 */
	public int getValue() {
		return goldValue;
	}
	
	/**
	 * Gets the image for this item
	 * @return This item's ImageIcon
	 */
	public ImageIcon getImage(){
		return image;
	}
	
	/**
	 * Gets the location of this item
	 * @return A point for the location of the item
	 */
	public Point getLocation(){
		return location;
	}
	
	/**
	 * Sets the location of this item
	 * @param p A point to set the location to
	 */
	public void setLocation(Point p){
		location = p;
	}
	
	/**
	 * Draws this item in the given panel based on its location
	 * @param g Used to draw the item
	 * @param p The panel to draw in
	 */
	public void draw(Graphics g, Panel p){
		image.paintIcon(p, g, 105 + location.y * 105, 105 + location.x * 105);
	}
}
