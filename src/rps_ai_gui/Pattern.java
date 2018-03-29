
package rps_ai_gui;

import java.io.Serializable;

/**
 * This class creates a Pattern based on a String. Used with the Computer class
 * to store Patterns of RPS throws.
 * 
 * @author Ryan
 *
 */
public class Pattern implements Serializable {

	/**
	 * Stores the String that represents this Pattern
	 */
	private String pattern;

	/**
	 * Constructs a new Pattern based on the given String
	 * 
	 * @param s
	 *            The String to use for the new Pattern
	 */
	public Pattern(String s) {
		pattern = s;
	}

	/**
	 * Gets the String value that represents this Pattern
	 * 
	 * @return The string for this Pattern
	 */
	public String getPattern() {
		return pattern;
	}

	/**
	 * Checks if two Patterns have the same String representation.
	 * 
	 * @param obj
	 *            The Object to compare the Pattern to.
	 * @return True or false for whether the Patterns are the same.
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof Pattern))
			return false;
		Pattern p = (Pattern) obj;
		return pattern.equals(p.getPattern());
	}

	/**
	 * Gets the hash code for the String representing this Pattern.
	 * 
	 * @return The String's hash value.
	 */
	@Override
	public int hashCode() {
		return pattern.hashCode();
	}
}
