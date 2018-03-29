
package hunting_game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/**
 * This class creates hunter's to represent the player's character
 * 
 * @author 013507765
 */
public class Hunter extends Entity{
	
	/**
	 * Stores the bullet the hunter shoots
	 */
	private Bullet bullet;
	
	/**
	 * Creates a new hunter with given attributes
	 * @param p The hunter's location
	 * @param w The hunter's width
	 * @param h The hunter's height
	 * @param hp The hunter's hp
	 * @param sp The hunter's speed
	 */
	public Hunter(Point p, int w, int h, int hp, int sp){
		super(p, w, h, hp, sp, 0);
	}
	
	/**
	 * Gets the hunter's instance of Bullet
	 * @return The hunter's bullet
	 */
	public Bullet getBullet(){
		return bullet;
	}
	
	/**
	 * Shoots a bullet in the hunter's current direction
	 */
	public void fireBullet(){
		bullet = new Bullet(new Point(getLocation().x + 8, getLocation().y + 8), 5, 5, 0, 10, getDirection());
	}
	
	/**
	 * Checks if the hunter's bullet has hit the given entity
	 * @param e The entity to check for collision
	 * @return true if they collided, false otherwise
	 */
	public boolean testHit(Entity e){
		return bullet.testCollision(e);
	}

	/**
	 * Draws the hunter and their gun as a red circle and line pointing in the direction
	 * the hunter is facing.
	 */
	@Override
	public void draw(Graphics g, Point p, int w, int h, int dir) {
		if(bullet != null){
			bullet.update(g);
		}
		setDirection(dir);
		g.setColor(Color.RED);
		g.fillOval(p.x, p.y, w, h);
		if(dir == 1){
			g.drawLine(p.x + 10, p.y + 10, p.x + 10, p.y - 10);
		}
		else if(dir == 2){
			g.drawLine(p.x + 10, p.y + 10, p.x + 25, p.y - 5);
		}
		else if(dir == 3){
			g.drawLine(p.x + 10, p.y + 10, p.x + 30, p.y + 10);
		}
		else if(dir == 4){
			g.drawLine(p.x + 10, p.y + 10, p.x + 25, p.y + 25);
		}
		else if(dir == 5){
			g.drawLine(p.x + 10, p.y + 10, p.x + 10, p.y + 30);
		}
		else if(dir == 6){
			g.drawLine(p.x + 10, p.y + 10, p.x - 5, p.y + 25);
		}
		else if(dir == 7){
			g.drawLine(p.x + 10, p.y + 10, p.x - 10, p.y + 10);
		}
		else if(dir == 8){
			g.drawLine(p.x + 10, p.y + 10, p.x - 5, p.y - 5);
		}
	}
}
