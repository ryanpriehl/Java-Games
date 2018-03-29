
package rps_ai_gui;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * This class stores the Patterns of throws and how many times they've appeared
 * in a hash map and uses them to predict the best next throw.
 * 
 * @author Ryan
 *
 */
public class Computer implements Serializable {

	/**
	 * Stores the Patterns of RPS throws (key) and how many times they've
	 * appeared (value).
	 */
	private HashMap<Pattern, Integer> map;

	/**
	 * Creates a new Computer and HashMap to store Patterns/how many times
	 * they've appeared.
	 */
	public Computer() {
		map = new HashMap<Pattern, Integer>();
	}

	/**
	 * Gets the computer's next throw based on the user's past throws compared
	 * to the frequencies of Patterns in the HashMap.
	 * 
	 * @param s
	 *            String representing the user's recent throws.
	 * @return String for what the computer predicts it should throw next.
	 */
	public String makePrediction(String s) {
		// Doesn't make predictions until the user has played at least 5 rounds
		if (s.length() >= 5) {
			
			// Checks longer Patterns first 
			for (int i = 0; i < 3; i++) {
				// Drops oldest throw in Pattern
				s = s.substring(1);
				// The three patterns to look for
				Pattern p1 = new Pattern(s + "R");
				Pattern p2 = new Pattern(s + "P");
				Pattern p3 = new Pattern(s + "S");
				// The frequencies of the three patterns
				int f1 = 0;
				int f2 = 0;
				int f3 = 0;

				// Gets the frequencies of the three patterns from the map
				if (map.get(p1) != null) {
					f1 = map.get(p1);
				}
				if (map.get(p2) != null) {
					f2 = map.get(p2);
				}
				if (map.get(p3) != null) {
					f3 = map.get(p3);
				}

				// Returns a prediction if there is a clear winner (higher than both other options)
				if (f1 > f2 && f1 > f3)
					return "Paper";
				else if (f2 > f1 && f2 > f3)
					return "Scissors";
				else if (f3 > f1 && f3 > f2)
					return "Rock";
				// Returns a prediction if there is a tie for winner
				if (f1 > f2 || f1 > f3)
					return "Paper";
				else if (f2 > f1 || f2 > f3)
					return "Scissors";
				else if (f3 > f1 || f3 > f2)
					return "Rock";
			}
		}
		// Returns a random throw if the user hasn't played 
		// enough rounds or there is no clear winner
		Random rand = new Random();
		int randInt = rand.nextInt(3);
		if (randInt == 0)
			return "Rock";
		else if (randInt == 1)
			return "Paper";
		else
			return "Scissors";
	}

	/**
	 * Increments the value at the Pattern key given by the String input.
	 * 
	 * @param s
	 *            The String for the Pattern to increment the value at in the
	 *            HashMap.
	 */
	public void storePattern(String s) {
		Pattern p = new Pattern(s);
		int value = 0;
		if (map.get(p) != null)
			value = map.get(p);
		map.put(p, value + 1);
	}

	/**
	 * Prints out all the keys in the HashMap and their associated values.
	 */
	public void show() {
		for (Map.Entry<Pattern, Integer> entry : map.entrySet()) {
			System.out.println(entry.getKey().getPattern() + " :: " + entry.getValue());
		}
	}
}
