
package star_wars_text_adventure;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/**
 * A Star Wars themed game. The player controls a character and takes them on
 * one of two missions to fight the Empire.
 * 
 * @author Ryan
 */
public class Main {

	static Scanner scan = new Scanner(System.in);
	static Random rand = new Random();
	/**
	 * Store the player's inputs for their name, lightsaber color, and quip
	 */
	static String name, color, quip;
	// array of entities for good characters
	static Entity[] goodGuys = new Entity[8];

	// array of entities for bad characters
	static Entity[] badGuys = new Entity[6];

	public static void main(String[] args) {

		// takes input for player's character
		System.out.println("Enter a name for your jedi: ");
		name = scan.nextLine();
		System.out.println("Enter the color of your lightsaber: ");
		color = scan.nextLine();
		System.out.println("Enter your battlecry: ");
		quip = scan.nextLine();

		// creating player's character and other good characters
		goodGuys[0] = new Jedi(name, quip, color);
		goodGuys[1] = new Rebel("Graham", "\"Down with the Empire!\"");
		goodGuys[2] = new Rebel("Eric", "\"Down with the Empire!\"");
		goodGuys[3] = new Rebel("Terry", "\"Down with the Empire!\"");
		goodGuys[4] = new Rebel("John", "\"Down with the Empire!\"");
		goodGuys[5] = new Rebel("Michael", "\"Down with the Empire!\"");
		goodGuys[6] = new Medical("2-1B", 5);
		goodGuys[7] = new Astromech("R2-D2", 5);

		// creating bad characters
		badGuys[0] = new SithLord("Darth Vader", "I don't like sand.", "red");
		badGuys[1] = new Stormtrooper("Trooper 1", "\"Rebel scum.\"");
		badGuys[2] = new Stormtrooper("Trooper 2", "\"Rebel scum.\"");
		badGuys[3] = new Stormtrooper("Trooper 3", "\"Rebel scum.\"");
		badGuys[4] = new Stormtrooper("Trooper 4", "\"Rebel scum.\"");
		badGuys[5] = new Stormtrooper("Trooper 5", "\"Rebel scum.\"");

		// allows the player to choose 1 of 2 missions
		System.out.println("Choose a mission: ");
		System.out.println("1. Fight the Sith Lord");
		System.out.println("2. Invade the Imperial Base");
		int missionChoice = checkInt(1, 2);

		// "Fight the Sith Lord" mission
		if (missionChoice == 1) {
			System.out.println("\nA wild Sith Lord appeared!");

			// battle continues while the player and Sith Lord are both alive
			while (!gameOver()) {
				showTeam();

				System.out.println("Good guys' turn: ");
				playerPhase();
				goodAttack();
				System.out.println("Bad guys' turn: ");
				badAttack();
			}
			showTeam();
			if (goodGuys[0].getHp() <= 0)
				System.out.println("You've been defeated... the dark side is victorious.");
			else if (badGuys[0].getHp() <= 0)
				System.out.println("The Sith lord is defeated! You've saved the galaxy!");
		}

		// "Invade the Imperial Base" mission
		else if (missionChoice == 2) {

			// replaces Sith Lord with Trooper Commander
			badGuys[0] = new Stormtrooper("Trooper Commander", "For the Empire!");

			System.out.println("You're sneaking through the Imperial Base when a group of guards appears!");

			// battle begins
			while (!gameOver()) {
				showTeam();
				playerPhase();
				goodAttack();
				badAttack();
			}
			showTeam();

			// battle ends when Trooper Commander or player is killed
			if (badGuys[0].getHp() <= 0)
				System.out.println("The commander is defeated! The rest of the guards flee.");
			if (goodGuys[0].getHp() <= 0) {
				System.out.println("You've been defeated... the galaxy is doomed.");
				return;
			}

			System.out.println("You continue through the base and reach the main control room.");
			Computer c = new Computer("Main Computer", 10);

			// if the astromech droid is alive hacking is guaranteed
			if (goodGuys[7].getHp() > 0) {
				System.out.println(goodGuys[7].getName() + " begins hacking into the system...");
				goodGuys[7].doTask(c);
				System.out.println("You retrieve the secret information and sneak out!");
			}
			// if astromech droid is dead hacking has a chance to fail
			else {
				System.out.println("With no astromech droid you attempt to hack into the system...");
				if (Math.random() > 0.80) {
					System.out.println("Success! You retrieve the secret information and sneak out!");
				} else {
					System.out.println("It didnt' work! The guards are alerted.");
					
					// restores all the stormtroopers to full health
					for(Entity e : badGuys){
						e.modifyHp(20);
					}
					
					// second fight
					while (!gameOver()) {
						showTeam();
						playerPhase();
						goodAttack();
						badAttack();
					}
					showTeam();
					if (badGuys[0].getHp() <= 0)
						System.out.println("The commander is defeated! The rest of the guards flee. You escape the imperial base alive.");
					if (goodGuys[0].getHp() <= 0) {
						System.out.println("You've been defeated... the galaxy is doomed.");
					}
				}
			}
		}
	}

	/**
	 * Displays the names and hp for all characters
	 * 
	 * @param teamGood
	 *            Array of good characters
	 * @param teamBad
	 *            Array of bad characters
	 */
	public static void showTeam() {
		System.out.println("************");
		System.out.println("Good guys: ");
		System.out.println("************");
		for (Entity e : goodGuys) {
			System.out.println(e.getName() + " (" + e.getHp() + ")");
		}
		System.out.println("************");
		System.out.println("Bad guys: ");
		System.out.println("************");
		for (Entity e : badGuys) {
			System.out.println(e.getName() + " (" + e.getHp() + ")");
		}
		System.out.println("************");
	}

	/**
	 * Lets the player select an enemy to attack.
	 * 
	 * @param e
	 *            Array of bad characters.
	 * @return The character the player wants to attack.
	 */
	public static int selectTarget() {
		boolean choosing = true;
		int choice = 0;
		while (choosing) {
			System.out.println("Select a target to attack: ");
			for (int i = 1; i < 7; i++) {
				System.out.println(i + ". " + badGuys[i - 1].getName() + " (" + badGuys[i - 1].getHp() + ")");
			}
			choice = checkInt(1, 6);
			choosing = false;
			if (badGuys[choice - 1].getHp() <= 0) {
				System.out.println("They're already dead. Leave them alone.\n");
				choosing = true;
			}
		}
		return choice - 1;
	}

	/**
	 * Lets the player select an ally to heal.
	 * 
	 * @param e
	 *            The array of good characters.
	 * @return The character the player wants to heal.
	 */
	public static int selectHeal() {

		int choice = 0;

			System.out.println("Select a character to heal: ");
			for (int i = 1; i < 7; i++) {
				System.out.println(i + ". " + goodGuys[i - 1].getName() + " (" + goodGuys[i - 1].getHp() + ")");
			}
			choice = checkInt(1, 6);

		return choice - 1;
	}
	
	public static int selectRepair(){

		int choice = 0;

			System.out.println("Select a droid to repair: ");
			for (int i = 1; i < 3; i++) {
				System.out.println(i + ". " + goodGuys[i + 5].getName() + " (" + goodGuys[i + 5].getHp() + ")");
			}
			choice = checkInt(1, 2);

		return choice + 5;
	}

	/**
	 * Gives the player options of what to do on their turn and executes them.
	 */
	public static void playerPhase() {

		int choice = 0;
		System.out.println("What do you want to do?");
		System.out.println("1. Attack with lightsaber");
		System.out.println("2. Attack with force");
		System.out.println("3. Heal with medical droid");
		System.out.println("4. Repair with astromech droid");
		choice = checkInt(1, 4);

		if (choice == 1) {
			goodGuys[0].setTask("attack");
			goodGuys[0].doTask(badGuys[selectTarget()]);
		} else if (choice == 2) {
			goodGuys[0].setTask("force");
			goodGuys[0].doTask(badGuys[selectTarget()]);
		} else if (choice == 3) {
			if (goodGuys[6].getHp() <= 0){
				System.out.println("Sorry, " +goodGuys[6].getName() +" is dead.");
				return;
			}
			goodGuys[6].setTask("heal");
			goodGuys[6].doTask(goodGuys[selectHeal()]);
		} else if (choice == 4) {
			if (goodGuys[7].getHp() <= 0){
				System.out.println("Sorry, " +goodGuys[7].getName() +" is dead.");
				return;
			}
			goodGuys[7].setTask("repair");
			goodGuys[7].doTask(goodGuys[selectRepair()]);
		}
	}

	/**
	 * Executes the attacks for non-player good characters.
	 */
	public static void goodAttack() {
		for (int i = 1; i < 6; i++) {
			if (!gameOver()) {
				if (goodGuys[i].getHp() > 0) {
					int target = rand.nextInt(badGuys.length);
					if (badGuys[target].getHp() > 0)
						goodGuys[i].doTask(badGuys[target]);
					else {
						i--;
					}
				}
			}
		}
	}

	/**
	 * Executes the attacks for bad characters.
	 */
	public static void badAttack() {
		for (int i = 0; i < 6; i++) {
			if (!gameOver()) {
				if (badGuys[i].getHp() > 0) {
					int target = rand.nextInt(goodGuys.length);
					if (goodGuys[target].getHp() > 0)
						badGuys[i].doTask(goodGuys[target]);
					else {
						i--;
					}
				}
			}
		}
	}

	/**
	 * Checks whether or not the game has ended for the first mission.
	 * @return True if the game has ended, false if not.
	 */
	public static boolean gameOver() {
		if (goodGuys[0].getHp() <= 0) {
			return true;
		} else if (badGuys[0].getHp() <= 0) {
			return true;
		}
		return false;
	}

	/**
	 * Checks that the user's input is an int in the desired range.
	 * @param low The lower bound of the desired range.
	 * @param high The upper bound of the desired range.
	 * @return The user's input in the desired range.
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
					System.out.print("Invalid- Retry: ");
				}
			} else {
				// clear buffer of junk input
				in.next();
				System.out.print("Invalid input- Retry: ");
			}
		}
		return validNum;
	}
}
