
package star_wars_text_adventure;

/**
 * Used with the Jedi and Sith Lord as one of their attacks.
 * @author Ryan
 *
 */
public interface HasForce {
	
	/**
	 * Uses the force to attack
	 * @param e Entity to use the force on
	 */
	public void useForce(Entity e);
}
