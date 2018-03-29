
package dungeon_crawler;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Reads in a list of enemies from EnemyList.txt and stores them in an array list
 * @author Ryan
 *
 */
public class EnemyGenerator {
	
	/**
	 * Stores the list of enemies read in from EnemyList.txt
	 */
	private static ArrayList<Enemy> enemyList = new ArrayList<Enemy>();

	/**
	 * Fills the array list with the enemies read in from EnemyList.txt
	 */
	public EnemyGenerator() {
		try{
			Scanner read = new Scanner(new File(Main.FILE_PATH +"/src/dungeon_crawler/EnemyList.txt"));
			while(read.hasNextLine()){
				read.useDelimiter(",");
				String n = read.next();
				String q = read.next();
				read.skip(",");
				int h = Integer.valueOf(read.nextLine());
				ItemGenerator ig = new ItemGenerator();
				Enemy enemy = new Enemy(n, q, 1, h, 10, ig.generateItem());
				enemyList.add(enemy);
			}
			read.close();
		} catch(FileNotFoundException e){
			System.out.println("File not found.");
		}
	}

	/**
	 * Returns a random enemy from the array list of enemies
	 * @param level The level of the enemy to return
	 * @return The random enemy of desired level
	 */
	public Enemy generateEnemy(int level){
		Random rand = new Random();
		int random = rand.nextInt(enemyList.size());
		Enemy e = enemyList.get(random);
		if (level == 2){
			e.increaseLevel();
		}
		else if (level == 3){
			e.increaseLevel();
			e.increaseLevel();
		}
		return e;
	}
}
