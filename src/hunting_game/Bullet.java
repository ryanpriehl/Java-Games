
package hunting_game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

/**
 * This class creates bullets to be used with the Hunter class and can
 * check if the bullets have hit anything.
 * 
 * @author 013507765
 */
public class Bullet extends Entity {

	/**
	 * Creates a bullet with given attributes
	 * @param p The location of the bullet
	 * @param w The width of the bullet
	 * @param h The height of the bullet
	 * @param hp The hp of the bullet
	 * @param sp The speed of the bullet
	 * @param dir The direction of the bullet
	 */
	public Bullet(Point p, int w, int h, int hp, int sp, int dir) {
		super(p, w, h, hp, sp, dir);
	}

	/**
	 * Checks if the given entity intersects this bullet
	 * @param e The entity to check
	 * @return true if they intersect, false otherwise
	 */
	public boolean testCollision(Entity e) {
		Rectangle bullRect = new Rectangle(getLocation().x, getLocation().y, getWidth(), getHeight());
		Rectangle entRect = new Rectangle(e.getLocation().x, e.getLocation().y, e.getWidth(), e.getHeight());
		if(bullRect.intersects(entRect)){
			stopMoving();
			setLocation(new Point(-100, -100));
			return true;
		}	
		else
			return false;
	}

	/**
	 * Draws the bullet as a yellow circle, but only if it is moving.
	 */
	@Override
	public void draw(Graphics g, Point p, int w, int h, int dir) {
		if(isMoving()){
			setDirection(dir);
			g.setColor(Color.YELLOW);
			g.fillOval(p.x, p.y, w, h);
		}
	}
}
