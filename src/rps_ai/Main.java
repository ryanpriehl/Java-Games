
package rps_ai;

import java.io.File;
import java.util.Scanner;
import java.io.*;

/**
 * Runs the RPS game. Takes in user's inputs and saves them in a String to base
 * Computer's predictions on. "Computer.dat" can be updated at the end of the
 * game to save the Patterns.
 * 
 * @author Ryan
 *
 */
public class Main {
	
	private static final String FILE_PATH = new File("").getAbsolutePath();

	public static void main(String[] args) {

		// The Computer the user plays against
		Computer comp = null;
		// Where the Computer object can be read in from
		File f = new File(FILE_PATH +"/src/rps_ai/Computer.dat");
		// Stores the user's inputs
		int input = 0;

		// If "Computer.dat" exists the user can choose whether or not to play it
		if (f.exists()) {
			System.out.println("Would you like to play the beginner computer or the veteran?");
			System.out.println("1. Beginner\n2. Veteran");
			input = checkInt(1, 2);
			// Creates Computer from "Computer.dat"
			if (input == 2) {
				try {
					ObjectInputStream in = new ObjectInputStream(new FileInputStream(f));
					comp = (Computer) in.readObject();
					System.out.println("Veteran Computer loaded.");
					in.close();
				} catch (IOException e) {
					System.out.println("Error processing file.");
				} catch (ClassNotFoundException e) {
					System.out.println("Class not found.");
				}
				// Creates new Computer object (not from "Computer.dat")
			} else {
				System.out.println("New Computer created.");
				comp = new Computer();
			}
			// Automatically creates new Computer if file doesn't exist
		} else {
			System.out.println("\"Computer.dat\" not found. New Computer created.");
			comp = new Computer();
		}

		// Stores player's score
		int playerScore = 0;
		// Stores computer's score
		int computerScore = 0;
		// Stores number of ties
		int numTies = 0;
		// Stores throw history
		String prevThrows = "";

		// Checks if user is done playing
		boolean playing = true;
		// Loops until user chooses to exit
		while (playing) {

			System.out.println("Choose what to throw:");
			System.out.println("1. Rock\n2. Paper\n3. Scissors");
			input = checkInt(1, 3);

			// Gets computer's throw
			String compThrow = comp.getThrow(prevThrows);

			// Gets user's throw and updates throw history
			String playerThrow = "";
			if (input == 1) {
				playerThrow = "Rock";
				prevThrows += "R";
			} else if (input == 2) {
				playerThrow = "Paper";
				prevThrows += "P";
			} else if (input == 3) {
				playerThrow = "Scissors";
				prevThrows += "S";
			}

			// Drops oldest throw if too many stored in throw history
			if (prevThrows.length() > 5)
				prevThrows = prevThrows.substring(1);
			
			// Updates frequencies in HashMap
			if (prevThrows.length() >= 3)
				comp.updateMap(prevThrows);
			if (prevThrows.length() >= 4)
				comp.updateMap(prevThrows.substring(1));
			if (prevThrows.length() >= 5)
				comp.updateMap(prevThrows.substring(2));

			// Prints computer's throw
			System.out.println("The computer chose " + compThrow);

			// Determines who won the round and updates their score
			if (playerThrow.equals(compThrow)) {
				System.out.println("It's a tie!");
				numTies++;
			} else if ((playerThrow.equals("Rock") && compThrow.equals("Scissors"))
					|| (playerThrow.equals("Paper") && compThrow.equals("Rock"))
					|| (playerThrow.equals("Scissors") && compThrow.equals("Paper"))) {
				System.out.println("You win!");
				playerScore++;
			} else {
				System.out.println("Computer wins!");
				computerScore++;
			}

			// Shows the scores
			System.out.println("\nPlayer's score: " + playerScore);
			System.out.println("Computer's score: " + computerScore);
			System.out.println("Number of ties: " + numTies);

			// Checks if the user wants to play again
			System.out.println("\nPlay again?");
			System.out.println("1. Yes\n2. No");
			input = checkInt(1, 2);
			if (input == 2)
				playing = false; // Exits loop, ending game
		}

		// Prints scores at end of game
		System.out.println("Final scores: ");
		System.out.println("Player: " + playerScore + "\nComputer: " + computerScore + "\nTies: " + numTies + "\n");

		// Gives user choice whether to update "Computer.dat" or not
		System.out.println("Update \"Computer.dat\"?\n1. Yes\n2. No");
		input = checkInt(1, 2);
		if (input == 1) {
			try {
				ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(f));
				out.writeObject(comp);
				System.out.println("\"Computer.dat\" updated.");
				out.close();
			} catch (IOException e) {
				System.out.println("Error processing file.");
			}
		}
	}

	/**
	 * Checks that the user's int input is in the desired range (inclusive)
	 * 
	 * @param low
	 *            The lower bound of the desired range
	 * @param high
	 *            The upper bound of the desired range
	 * @return The validated int input
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
					System.out.print("Invalid input, retry: ");
				}
			} else {
				in.next();
				System.out.print("Invalid input, retry: ");
			}
		}
		return validNum;
	}

}
