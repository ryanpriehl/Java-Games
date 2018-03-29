
package star_wars_text_adventure;

/**
 * Used with the medical droid to heal allied characters.
 * @author Ryan
 */
public interface Healable {
	
	/**
	 * Heals target jedi or rebel
	 * @param hp How much to heal them by
	 */
	public void heal(int hp);
}
