package dungeon_crawler_gui;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.imageio.ImageIO;

/**
 * This class reads in and creates an ArrayList of all possible enemy types and
 * can randomly create enemies based off the list.
 * 
 * @author Ryan Riehl
 */
public class EnemyGenerator {
	
	/**
	 * Stores the list of possible enemy types and their information
	 */
	private static CopyOnWriteArrayList<Enemy> enemyList = new CopyOnWriteArrayList<Enemy>();

	/**
	 * Creates and ArrayList of the different types of enemies that can be created
	 * read in from "EnemyList.txt"
	 */
	public EnemyGenerator() {
		ItemGenerator ig = new ItemGenerator();
		try{
			Scanner read = new Scanner(new File(Panel.FILE_PATH +"/src/dungeon_crawler_gui/EnemyList.txt"));
			while(read.hasNextLine()){
				Enemy enemy = null;
				read.useDelimiter(",");
				String n = read.next();
				int h = Integer.valueOf(read.next());
				int a = Integer.valueOf(read.next());
				read.skip(",");
				String source = read.nextLine();
				try {
					enemy = new Enemy(n, 1, h, 10, ImageIO.read(new File(Panel.FILE_PATH +"/src/dungeon_crawler_gui/Images/" +source)), new Point(0,0), ig.generateItem(), a);
				} catch (IOException e) {}
				enemyList.add(enemy);
			}
			read.close();
		} catch(FileNotFoundException e){}
	}

	/**
	 * Randomly gets an enemy from the ArrayList of enemies
	 * @param level The level of the enemy to generate
	 * @return An randomly chosen enemy of the given level
	 */
	public Enemy generateEnemy(int level){
		Random rand = new Random();
		ItemGenerator ig = new ItemGenerator();
		int random = rand.nextInt(enemyList.size());
		Enemy e = enemyList.get(random);
		Enemy betterEnemy = new Enemy(e.getName(), level, e.getHp()*level, 0, e.getImage(), e.getLocation(), ig.generateItem(), e.getAttack());
		betterEnemy.setGold();
		return betterEnemy;
	}
}
