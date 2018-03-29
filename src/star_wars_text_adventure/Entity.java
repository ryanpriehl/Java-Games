
package star_wars_text_adventure;

/**
 * Superclass for all the characters in the game
 * 
 * @author Ryan
 *
 */
public abstract class Entity {

	/**
	 * The name of the entity
	 */
	private String name;
	/**
	 * How much hp the entity has
	 */
	private int hp;
	/**
	 * Whether the entity is active or not
	 */
	private boolean active;
	/**
	 * What task the entity is performing
	 */
	public String task;

	/**
	 * Creates a new entity
	 * 
	 * @param n
	 *            The name of the entity
	 * @param h
	 *            The entity's hp
	 */
	public Entity(String n, int h) {
		name = n;
		hp = h;
	}

	/**
	 * Does the entity's specific task
	 * 
	 * @param e
	 *            What the task is being done to
	 */
	public abstract void doTask(Entity e);

	String getName() {
		return name;
	}

	/**
	 * Returns how much hp this entity has
	 * 
	 * @return The amount of hp
	 */
	public int getHp() {
		return hp;
	}

	/**
	 * Returns whether the entity is active or not
	 * 
	 * @return Boolean true for active false for not active
	 */
	public boolean getActive() {
		return active;
	}

	/**
	 * Modifies the entity's hp
	 * 
	 * @param newHp
	 *            What the hp is to be changed to
	 */
	public void modifyHp(int newHp) {
		hp = newHp;
		if (hp < 0)
			hp = 0;
	}

	/**
	 * Returns the entity's task
	 * 
	 * @return The entity's task
	 */
	public String getTask() {
		return task;
	}

	/**
	 * Change the entity's task
	 * 
	 * @param newTask
	 *            What the task is to be changed to
	 */
	public void setTask(String newTask) {
		task = newTask;
	}
}
