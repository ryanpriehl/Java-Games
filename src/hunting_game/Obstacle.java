
package hunting_game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

/**
 * This class creates obstacles and can check if anything has collided with them
 * 
 * @author 013507765
 */
public class Obstacle extends Entity{
	
	/**
	 * Stores the type of the obstacle. 1 = tree, 2 = fallen quarry
	 */
	private int type;
	
	/**
	 * Creates a new obstacle with given attributes
	 * @param p Location of this obstacle
	 * @param t Type of this obstacle
	 */
	public Obstacle(Point p, int t){
		super(p, 25, 25, 0, 0, 0);
		type = t;
	}
	
	/**
	 * Creates a new obstacle from another entity
	 * @param e The entity to create the obstacle from
	 */
	public Obstacle(Entity e){
		super(e.getLocation(), e.getWidth(), e.getHeight(), 0, 0, 0);
		type = 2;
	}
	
	/**
	 * Checks if the given entity intersects this obstacle
	 * @param e The entity to check
	 * @return true if the entity and obstacle intersect, false otherwise
	 */
	public boolean testCollision(Entity e){
		Rectangle obstRect = new Rectangle(getLocation().x, getLocation().y, getWidth(), getHeight());
		Rectangle entRect = new Rectangle(e.getLocation().x, e.getLocation().y, e.getWidth(), e.getHeight());
		if(obstRect.intersects(entRect))
			return true;
		else
			return false;
	}

	/**
	 * Draws obstacles. Default "tree" obstacles are green squares, fallen Quarry obstacles are gray circles
	 */
	@Override
	public void draw(Graphics g, Point p, int w, int h, int dir) {
		if(type == 1){
			g.setColor(Color.GREEN);
			g.fillRect(p.x, p.y, w, h);
		}
		if(type == 2){
			g.setColor(Color.GRAY);
			g.fillOval(p.x, p.y, w, h);
		}
	}
}
