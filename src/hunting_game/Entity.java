
package hunting_game;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

/**
 * This class is the abstract class that hunter, bullet, obstacle, 
 * and quarry extend from and has their common methods.
 * 
 * @author 013507765
 */
public abstract class Entity {
	
	/**
	 * Stores the location of the entity
	 */
	private Rectangle location;
	
	/**
	 * Stores the hp of the entity
	 */
	private int hp;
	
	/**
	 * Stores the speed of the entity
	 */
	private int speed;
	
	/**
	 * Stores the direction of the entity
	 */
	private int direction;
	
	/**
	 * Stores whether or not the entity is moving
	 */
	private boolean moving;
	
	/**
	 * Creates an entity with given attributes
	 * @param p The location of this entity
	 * @param w The width of this entity
	 * @param h The height of this entity
	 * @param hpInput The hp of this entity
	 * @param sp The speed of this entity
	 * @param dir The direction of this entity
	 */
	public Entity(Point p, int w, int h, int hpInput, int sp, int dir){
		location = new Rectangle(p.x, p.y, w, h);
		hp = hpInput;
		speed = sp;
		direction = dir;
		moving = true;
	}
	
	/**
	 * Gets the location of this entity
	 * @return The location of the entity
	 */
	public Point getLocation(){
		return location.getLocation();
	}
	
	/**
	 * Sets the location of this entity to the given point
	 * @param p The point to set the location to
	 */
	public void setLocation(Point p){
		location.setLocation(p);
	}
	
	/**
	 * Gets the width of this entity
	 * @return The width of the entity
	 */
	public int getWidth(){
		return (int) location.getWidth();
	}
	
	/**
	 * Gets the height of this entity
	 * @return The height of the entity
	 */
	public int getHeight(){
		return (int) location.getHeight();
	}
	
	/**
	 * Gets the speed of this entity
	 * @return The speed of the entity
	 */
	public int getHp(){
		return hp;
	}
	
	/**
	 * Gets the speed of this entity
	 * @return The speed of the entity
	 */
	public int getSpeed(){
		return speed;
	}
	
	/**
	 * Gets the direction this entity is facing
	 * @return The direction of the entity
	 */
	public int getDirection(){
		return direction;
	}
	
	/**
	 * Checks if this entity is moving
	 * @return true if it's moving, false otherwise
	 */
	public boolean isMoving(){
		return moving;
	}
	
	/**
	 * Checks if this entity is dead (hp <= 0)
	 * @return true if it's dead, false otherwise
	 */
	public boolean isDead(){
		return hp <= 0;
	}
	
	/**
	 * Decrements the hp of this entity
	 */
	public void takeHit(){
		hp--;
	}
	
	/**
	 * Rotates this entity one tick clockwise
	 */
	public void spinCW(){
		if(direction == 8)
			direction = 1;
		else
			direction++;
	}
	
	/**
	 * Rotates this entity one tick counter-clockwise
	 */
	public void spinCCW(){
		if(direction == 1 || direction == 0)
			direction = 8;
		else
			direction--;
	}
	
	/**
	 * Changes the direction of this entity
	 * @param d The direction to change to
	 */
	public void setDirection(int d){
		direction = d;
	}
	
	/**
	 * Increases the speed of this entity
	 */
	public void incSpeed(){
		speed++;
	}
	
	/**
	 * Decreases the speed of this entity
	 */
	public void decSpeed(){
		speed--;
	}
	
	/**
	 * Toggles this entity's movement
	 */
	public void toggleMoving(){
		moving = !moving;
	}
	
	/**
	 * Stops this entity if it is moving
	 */
	public void stopMoving(){
		moving = false;
	}
	
	/**
	 * Moves and redraws this entity
	 * @param g Graphics
	 */
	public void update(Graphics g){
		move();
		draw(g, getLocation(), getWidth(), getHeight(), direction);
	}
	
	/**
	 * Moves this entity in the direction it is moving
	 */
	public void move(){
		if(moving){
		if (direction == 1)
			location = new Rectangle(getLocation().x, getLocation().y - getSpeed(), getWidth(), getHeight());
		else if (direction == 2)
			location = new Rectangle(getLocation().x + getSpeed(), getLocation().y - getSpeed(), getWidth(), getHeight());
		else if (direction == 3)
			location = new Rectangle(getLocation().x + getSpeed(), getLocation().y, getWidth(), getHeight());
		else if (direction == 4)
			location = new Rectangle(getLocation().x + getSpeed(), getLocation().y + getSpeed(), getWidth(), getHeight());
		else if (direction == 5)
			location = new Rectangle(getLocation().x, getLocation().y + getSpeed(), getWidth(), getHeight());
		else if (direction == 6)
			location = new Rectangle(getLocation().x - getSpeed(), getLocation().y + getSpeed(), getWidth(), getHeight());
		else if (direction == 7)
			location = new Rectangle(getLocation().x - getSpeed(), getLocation().y, getWidth(), getHeight());
		else if (direction == 8)
			location = new Rectangle(getLocation().x - getSpeed(), getLocation().y - getSpeed(), getWidth(), getHeight());
		}
	}
	
	/**
	 * Draws this entity
	 * @param g Graphics
	 * @param p The location to draw at
	 * @param w The width of the entity to draw
	 * @param h The height of the entity to draw
	 * @param dir The direction of the entity to draw
	 */
	abstract void draw(Graphics g, Point p, int w, int h, int dir);
}
