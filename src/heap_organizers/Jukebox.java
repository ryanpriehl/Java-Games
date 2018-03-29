package heap_organizers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class is used with the Song class to create a playlist of songs read in
 * from a file.
 * 
 * @author Ryan
 */
public class Jukebox {

	/**
	 * Runs the program to use the playlist of songs.
	 * @param args None
	 * @throws FileNotFoundException In case "playlist.txt" doesn't exist.
	 */
	public static void main(String[] args) throws FileNotFoundException {

		// stores the songs
		Heap<Song> heap = new Heap<Song>();
		
		// where the songs are read in from
		File f = new File("playlist.txt");
		Scanner scan = new Scanner(f);
		scan.useDelimiter(",");

		// reads in songs from file
		while (scan.hasNextLine()) {
			String title = scan.next();
			String artist = scan.next();
			String album = scan.next();
			int rating = Integer.valueOf(scan.nextLine().substring(1));
			Song s = new Song(title, artist, album, rating);
			heap.add(s);
		}

		int choice = 0;
		while (choice != 7) {
			// displays menu
			System.out.println("1. Show song list");
			System.out.println("2. Show sorted song list");
			System.out.println("3. Show current song");
			System.out.println("4. Add new song to list");
			System.out.println("5. Play next song");
			System.out.println("6. Re-rate next song");
			System.out.println("7. Quit");
			// gets/checks user's choice
			choice = checkInt(1, 7);

			if (choice == 1) {
				// prints the heap by index order
				heap.printHeap();
			} else if (choice == 2) {
				// prints the heap in play order
				heap.printSorted();
			} else if (choice == 3) {
				// shows the current song
				System.out.println("Now playing " + heap.get(0).toString());
			} else if (choice == 4) {
				// adds a new song to the list
				Scanner in = new Scanner(System.in);
				System.out.println("Enter song title: ");
				String title = in.nextLine();
				System.out.println("Enter artist: ");
				String artist = in.nextLine();
				System.out.println("Enter album: ");
				String album = in.nextLine();
				System.out.println("Enter rating (1 - 5): ");
				int rating = checkInt(1, 5);
				Song s = new Song(title, artist, album, rating);
				System.out.println(s.toString() + " added to playlist.");
				heap.add(s);
			} else if (choice == 5) {
				// removes current song and goes to next
				System.out.println(heap.get(0).toString() + " removed from list.");
				heap.removeMax();
				System.out.println("Now playing " + heap.get(0).toString());
			} else if (choice == 6) {
				// re-rates next song
				Song s1 = heap.removeMax();
				System.out.println("Enter a new rating (1 - 5) for " + heap.get(0).toString());
				int rating = checkInt(1, 5);
				Song s2 = new Song(heap.get(0), rating);
				heap.removeMax();
				heap.add(s1);
				heap.add(s2);
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
