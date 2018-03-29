package dungeon_crawler_gui;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;

public class Level {

	private char[][] level;
	private CopyOnWriteArrayList<Item> items;

	public Level() {
		level = new char[4][4];
		items = new CopyOnWriteArrayList<Item>();
	}

	public void generateLevel(int levelNum) {
		File f = null;
		
		if (levelNum == 1)
			f = new File(Panel.FILE_PATH +"/src/dungeon_crawler_gui/Level1.txt");
		else if (levelNum == 2)
			f = new File(Panel.FILE_PATH +"/src/dungeon_crawler_gui/Level2.txt");
		else if (levelNum == 3)
			f = new File(Panel.FILE_PATH +"/src/dungeon_crawler_gui/Level3.txt");
		
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
			System.out.println("File not found");
		}
	}

	public char getRoom(Point p) {
		return level[(int) p.x][(int) p.y];
	}
	
	public CopyOnWriteArrayList<Enemy> getEnemies(int levelNum){
		EnemyGenerator eg = new EnemyGenerator();
		CopyOnWriteArrayList<Enemy> enemies = new CopyOnWriteArrayList<Enemy>();
		for(int i = 0; i < 4; i++){
			for(int j = 0; j < 4; j++){
				if(level[i][j] == 'm'){
					Enemy e = eg.generateEnemy(levelNum);
					e.setLocation(new Point(i, j));
					enemies.add(e);
				}
			}
		}
		return enemies;
	}
	
	/**
	 * 
	 * @param levelNum
	 * @return
	 */
	public CopyOnWriteArrayList<Item> getItems(int levelNum){
		ItemGenerator ig = new ItemGenerator();
		Item item = null;
		for(int i = 0; i < 4; i++){
			for(int j = 0; j < 4; j++){
				if(level[i][j] == 'i'){
					item = ig.generateItem();
					item.setLocation(new Point(i, j));
					items.add(item);
				}
			}
		}
		return items;
	}
	
	/**
	 * Gets the item at a specific room in the level
	 * @param p The location to get the item at
	 * @return The item at the specified location
	 */
	public Item getItemAtLoc(Point p){
		Item item = new Item("error", 420, null, new Point(0,0));
		for(Item i : items){
			if(i.getLocation().equals(p))
				item = i;
		}
		return item;
	}

	/**
	 * Loops through the level array to find the s to know the hero's start location
	 * @return A point for the start location
	 */
	public Point findStartLocation() {
		Point p = null;
		for(int i = 0; i < 4; i++){
			for(int j = 0; j < 4; j++){
				if(level[i][j] == 's'){
					p = new Point(i, j);
				}
			}
		}
		return p;
	}
	
	/**
	 * Sets the room at the given location to c indicating it's been cleared
	 * @param p The location of the room to clear
	 */
	public void clearRoom(Point p) {
		level[(int) p.x][(int) p.y] = 'c';
	}
}
