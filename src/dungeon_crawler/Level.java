
package dungeon_crawler;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Reads a text file to create a 2D-array of characters for the level
 * 
 * @author Ryan
 *
 */
public class Level {

	/**
	 * Array to store the characters representing the rooms in the level
	 */
	private char[][] level;
	
	private String filePath;

	/**
	 * Creates a new 4 x 4 array for the level
	 */
	public Level() {
		level = new char[4][4];
		filePath = new File("").getAbsolutePath();
	}

	/**
	 * Reads in one of the level files to create a map of the level
	 * @param levelNum The level file to use
	 */
	public void generateLevel(int levelNum) {
		File f = null;
		
		if (levelNum == 1)
			f = new File(filePath +"/src/dungeon_crawler/Level1.txt");
		else if (levelNum == 2)
			f = new File(filePath +"/src/dungeon_crawler/Level2.txt");
		else if (levelNum == 3)
			f = new File(filePath +"/src/dungeon_crawler/Level3.txt");
		
		try {
			Scanner read = new Scanner(f);
			while (read.hasNext()) {
				for (int i = 0; i < 4; i++) {
					for (int j = 0; j < 4; j++) {
						level[i][j] = read.next().charAt(0);
					}
				}
			}
			read.close();
		} catch (FileNotFoundException e) {
			System.out.println(levelNum);
			System.out.println("Error: File for level not found");
		}
	}

	/**
	 * Gets the character in the map at the given point
	 * @param p The point on the map to get the character at
	 * @return The character at the given point
	 */
	public char getRoom(Point p) {
		return level[(int) p.x][(int) p.y];
	}

	/**
	 * Prints the map with player's current location
	 * @param p The point the player is currently at
	 */
	public void displayMap(Point p) {
		System.out.println("*************");
		System.out.println("Dungeon map: ");
		System.out.println("*************");
		System.out.println("-----------");
		for (int i = 0; i < 4; i++) {
			System.out.print("| ");
			for (int j = 0; j < 4; j++) {
				if (i == (int) p.x && j == (int) p.y)
					System.out.print("H ");
				else
					System.out.print(level[i][j] + " ");
			}
			System.out.print("|\n");
		}
		System.out.println("-----------");
	}

	/**
	 * Gets the location the player starts the map at
	 * @return The point representing the start location
	 */
	public Point findStartLocation() {
		Point p = new Point(3, 0);
		return p;
	}
	
	public void clearRoom(Point p) {
		level[(int) p.x][(int) p.y] = 'c';
	}
}
