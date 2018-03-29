package heap_organizers;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * This class is used with the Task class to create a list of tasks read in from
 * a file.
 * 
 * @author Ryan
 */
public class Tasklist {

	/**
	 * Runs the program to use the list of tasks.
	 * @param args None
	 * @throws FileNotFoundException In case "taskList.txt" doesn't exist.
	 */
	public static void main(String[] args) throws FileNotFoundException {

		// stores the tasks
		Heap<Task> heap = new Heap<Task>();
		
		// where the tasks are read in from
		File f = new File("taskList.txt");
		Scanner scan = new Scanner(f);
		scan.useDelimiter(",");

		// reads in tasks from file
		while (scan.hasNextLine()) {
			String taskName = scan.next();
			String date = scan.nextLine().substring(2);
			Task t = new Task(taskName, date);
			heap.add(t);
		}

		int choice = 0;
		while (choice != 7) {
			// displays menu
			System.out.println("1. Show task list");
			System.out.println("2. Show sorted task list");
			System.out.println("3. Show current task");
			System.out.println("4. Add new task to list");
			System.out.println("5. Mark task complete");
			System.out.println("6. Postpone next task");
			System.out.println("7. Quit");
			// gets/checks user's choice
			choice = checkInt(1, 7);

			if (choice == 1) {
				// prints the heap by index order
				heap.printHeap();
			} else if (choice == 2) {
				// prints the heap by date order
				heap.printSorted();
			} else if (choice == 3) {
				// shows the current task
				System.out.println("Current task: " + heap.get(0).toString());
			} else if (choice == 4) {
				// adds a new task to the list
				Scanner in = new Scanner(System.in);
				System.out.println("Enter task name: ");
				String taskName = in.nextLine();

				// gets date using checkDate method
				String date = checkDate();

				// gets date asking for every number individually
				/*
				 * System.out.println("Enter month due: "); String month = new
				 * Integer(checkInt(1, 12)).toString(); System.out.println(
				 * "Enter day due: "); String day = new Integer(checkInt(1,
				 * 31)).toString(); System.out.println("Enter year due: ");
				 * String year = new Integer(checkInt(2016, 9999)).toString();
				 * System.out.println("Enter hour due: "); String hour = new
				 * Integer(checkInt(1, 12)).toString(); System.out.println(
				 * "Enter minute due: "); String minute = new
				 * Integer(checkInt(0, 59)).toString(); String date = month +
				 * "/" + day + "/" + year + " " + hour + ":" + minute;
				 */

				Task t = new Task(taskName, date);
				System.out.println(t.toString() + " added to task list.");
				heap.add(t);
			} else if (choice == 5) {
				// removes current task and goes to next
				System.out.println(heap.get(0).toString() + " completed and removed.");
				heap.removeMax();
				System.out.println("Next task: " + heap.get(0).toString());
			} else if (choice == 6) {
				// postpones next task
				Task t1 = heap.removeMax();
				System.out.println("Enter a new date for " + heap.get(0).toString());
				String date = checkDate();
				Task t2 = new Task(heap.get(0), date);
				heap.removeMax();
				heap.add(t1);
				heap.add(t2);
			}
		}
	}

	/**
	 * Gets a string with the desired date format.
	 * 
	 * @return A string of the desired format.
	 */
	public static String checkDate() {
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy hh:mm");
		System.out.println("Enter date in foramt: MM/dd/yyyy hh:mm");
		Scanner in = new Scanner(System.in);
		Date date;
		while (true) {
			try {
				String input = in.nextLine();
				date = format.parse(input);
				return input;
			} catch (ParseException e) {
				System.out.println("Error. Try again.");
			}
		}
	}

	/**
	 * Gets an int within the desired range.
	 * 
	 * @param low
	 *            The lower bound of the desired range.
	 * @param high
	 *            The upper bound of the desired range.
	 * @return An int in the given range.
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
					System.out.println("Invalid input. Retry: ");
				}
			} else {
				in.next();
				System.out.println("Invalid input. Retry: ");
			}
		}
		return validNum;
	}
}
