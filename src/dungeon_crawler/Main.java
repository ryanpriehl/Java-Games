
package dungeon_crawler;

import java.awt.Point;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Random;
import java.util.Scanner;

public class Main {
	
	public static final String FILE_PATH = new File("").getAbsolutePath();;

	public static void main(String[] args) {

		Hero player = null;
		File f = new File(Main.FILE_PATH +"/src/dungeon_crawler/hero.dat");
		if (f.exists()) {
			try {
				ObjectInputStream in = new ObjectInputStream(new FileInputStream(f));
				player = (Hero) in.readObject();
				player.setLocation(new Point(3, 0));
				in.close();
			} catch (ClassNotFoundException e) {
				System.out.println("Class not found.");
			} catch (IOException e) {
				System.out.println("Error processing file.");
			}
		} else {
			Scanner in = new Scanner(System.in);
			System.out.println("What is your name?");
			String name = in.nextLine();
			System.out.println("What is your battlecry?");
			String quip = in.nextLine();
			Point p = new Point(3, 0);
			player = new Hero(name, quip, p);
		}

		player.display();
		int maxHP = player.getHp();

		Level l = new Level();
		l.generateLevel(player.getLevel());

		boolean playing = true;
		int choice = 0;
		while (playing && player.getHp() > 0) {
			l.displayMap(player.getLocation());
			if(choice == 0)
				choice = playerPhase();

			char encounter = '*';
			if (choice == 1 && player.canGoNorth())
				encounter = player.goNorth(l);
			else if (choice == 2 && player.canGoEast())
				encounter = player.goEast(l);
			else if (choice == 3 && player.canGoSouth())
				encounter = player.goSouth(l);
			else if (choice == 4 && player.canGoWest())
				encounter = player.goWest(l);
			else if (choice == 5){
				player.showInventory();
				for(Item i : player.getItems()){
					if(i.getName().equals("Health Potion")){
						System.out.println("Use health potion?\n1. yes\n2. no");
						choice = checkInt(1,2);
						if(choice == 1){
							System.out.println("Health restored!");
							player.heal(maxHP);
							player.getItems().remove(i);
						}
						break;
					}
				}
			}
			
			choice = 0;
			
			if (encounter == 'f') {
				System.out.println("You've found the exit!\nProgress saved.");
				player.heal(maxHP);
				player.increaseLevel();
				try {
					ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(f));
					out.writeObject(player);
					out.close();
				} catch (IOException e) {
					System.out.println("Error processing file.");
				}
				playing = false;
			} else if (encounter == 'i') {
				ItemGenerator ig = new ItemGenerator();
				Item found = ig.generateItem();
				System.out.println("You've found a " + found.getName() + "!");
				if (player.pickUpItem(found))
					l.clearRoom(player.getLocation());
			} else if (encounter == 's') {
				shopPhase(player);
			} else if (encounter == 'm') {
				choice = combatPhase(player, l);
			} else if (encounter == 'c') {
				System.out.println("This room is clear.");
			}
		}

		if (player.getHp() <= 0) {
			System.out.println("Game over. Restart to continue from current level.");
		}

	}

	public static int playerPhase() {
		System.out.println("What would you like to do?");
		System.out.println("1. Move North");
		System.out.println("2. Move East");
		System.out.println("3. Move South");
		System.out.println("4. Move West");
		System.out.println("5. Check inventory/Use potion");
		return checkInt(1, 5);
	}

	public static int combatPhase(Hero player, Level l) {
		l.displayMap(player.getLocation());
		EnemyGenerator eg = new EnemyGenerator();
		Enemy e = eg.generateEnemy(player.getLevel());
		int maxHP = e.getHp();
		System.out.println(player.getName() + " has encountered a " + e.getName() + "!");
		while (player.getHp() > 0 && e.getHp() > 0) {
			System.out.println(player.getName() + " has " + player.getHp() + " hp.");
			System.out.println("The " + e.getName() + " has " + e.getHp() + " hp.");
			System.out.println("What do you do?");
			System.out.println("1. Fight");
			System.out.println("2. Run away");
			int choice = checkInt(1, 2);

			if (choice == 1) {
				player.attack(e);
				if (e.getHp() > 0){
					e.attack(player);
				}
				else{
					System.out.println("The " + e.getName() + " is defeated!");
					l.clearRoom(player.getLocation());
					System.out.println("You found " + e.getGold() +" gold and a " +e.getItem().getName() +" on the corpse!");
					player.collectGold(e.getGold());
					player.pickUpItem(e.getItem());
					e.heal(maxHP);
					return 0;
				}
					
			} else if (choice == 2) {
				e.attack(player);
				if(player.getHp() < 0){
					System.out.println(player.getName() + " flees the battle.");
				}
				while (true) {
					Random rand = new Random();
					int direction = rand.nextInt(4);
					if (direction == 0 && player.canGoNorth()) {
						return 1;
					} else if (direction == 1 && player.canGoEast()) {
						return 2;
					} else if (direction == 2 && player.canGoSouth()) {
						return 3;
					} else if (direction == 3 && player.canGoWest()) {
						return 4;
					}
				}
			}
		}
		return 0;
	}

	public static void shopPhase(Hero h) {
		System.out.println("You've found the shop!");
		while (true) {
			System.out.println("What would you like to do?");
			System.out.println("1. Sell an item");
			System.out.println("2. Buy a health potion (50 gold)");
			System.out.println("3. Exit shop");
			int choice = checkInt(1, 3);
			if (choice == 1 && h.getItems().size() > 0) {
				h.showInventory();
				choice = checkInt(1, h.getItems().size());
				System.out.println(h.getItems().get(choice-1).getName() + " sold. " +h.getItems().get(choice-1).getValue() +" gold added.");
				h.collectGold(h.getItems().get(choice-1).getValue());
				h.removeItem(choice - 1);
			} else if (choice == 2) {
				if (h.getGold() >= 50) {
					if (h.pickUpItem(new Item("Health Potion", 25))) {
						h.collectGold(-50);
					}
				} else
					System.out.println("Not enough gold.");
			} else if (choice == 3) {
				return;
			}
		}

	}

	/**
	 * Checks that the user's input is in the specified range
	 * 
	 * @param low
	 *            The lower bound of the acceptable range
	 * @param high
	 *            The upper bound of the acceptable range
	 * @return The user's valid input
	 */
	public static int checkInt(int low, int high) {
		Scanner in = new Scanner(System.in);
		boolean valid = false;
		int validNum = 0;
		while (!valid) {
			if (in.hasNextInt()) {
				validNum = in.nextInt();
				if (validNum >= low && validNum <= high) {
					valid = true;
				} else {
					System.out.print("Invalid input. Retry: ");
				}
			} else {
				in.next();
				System.out.print("Invalid input. Retry: ");
			}
		}
		return validNum;
	}
}
