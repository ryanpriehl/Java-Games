
package hunting_game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/**
 * This class creates Quarries to be hunted with varying attributes.
 * 
 * @author 013507765
 */
public class Quarry extends Entity{
	
	/**
	 * Stores the name of the Quarry.
	 */
	private String name;
	
	/**
	 * Stores the weight of the Quarry.
	 */
	private int weight;

	/**
	 * Creates a new Quarry with given attributes.
	 * @param p The Point where the Quarry is located.
	 * @param w The width of the Quarry.
	 * @param h The height of the Quarry.
	 * @param hp The hp of the Quarry.
	 * @param sp The speed of the Quarry.
	 * @param n The name of the Quarry.
	 * @param wt The weight of the Quarry.
	 */
	public Quarry(Point p, int w, int h, int hp, int sp, String n, int wt) {
		super(p, w, h, hp, sp, 0);
		name = n;
		weight = wt;
	}
	
	/**
	 * Gets the name of this Quarry.
	 * @return The name of this Quarry.
	 */
	public String getName(){
		return name;
	}

	/**
	 * Gets the weight of this Quarry.
	 * @return The weight of the Quarry.
	 */
	public int getWeight(){
		return weight;
	}

	/**
	 * Draws the Quarries as a light blue circle.
	 */
	@Override
	public void draw(Graphics g, Point p, int w, int h, int dir) {
		g.setColor(Color.CYAN);
		setDirection(dir);
		g.fillOval(p.x, p.y, w, h);
	}
}
