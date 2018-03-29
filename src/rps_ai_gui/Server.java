
package rps_ai_gui;

import java.net.*;
import java.io.*;

/**
 * Creates the Server with the Computini that the
 * Client classes communicate with for the game.
 * 
 * @author Ryan Riehl
 */
public class Server{
	
	/**
	 * Connects to the Client, creates a new Computer,
	 * makes prediction, sends prediction to Client,
	 * updates HashMap, and saves Computer to file
	 * 
	 * @param args none
	 */
	public static void main(String[] args) {
		try {
			ServerSocket server = new ServerSocket(1025);
			String pattern = "";

			Computer comp = null;
			File f = new File(ClientPanel.FILE_PATH +"/src/rps_ai_gui/Computer.dat");
			if (f.exists()) {
				try {
					ObjectInputStream in = new ObjectInputStream(new FileInputStream(f));
					comp = (Computer) in.readObject();
					System.out.println("computer loaded");
					in.close();
				} catch (ClassNotFoundException e) {
					System.out.println("error loading computer");
				}
			} else {
				System.out.println("new computer made");
				comp = new Computer();
			}

			System.out.println("Waiting...");
			Socket s = server.accept();

			while(true){
				if (pattern.length() > 5)
					pattern = pattern.substring(1);
				
				String compThrow = comp.makePrediction(pattern);
				if(compThrow.equals("Rock")){
					compThrow = "Bulbasaur";
				}
				if(compThrow.equals("Paper")){
					compThrow = "Charmander";
				}
				if(compThrow.equals("Scissors")){
					compThrow = "Squirtle";
				}
				
				BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
				String input = in.readLine();
				PrintStream out = new PrintStream(s.getOutputStream());
				if(input.equals("Bulbasaur")){
					pattern += "R";
					out.println(compThrow);
				}
				else if(input.equals("Charmander")){
					pattern += "P";
					out.println(compThrow);
				}
				else if(input.equals("Squirtle")){
					pattern += "S";
					out.println(compThrow);
				}
				
				if (pattern.length() >= 3)
					comp.storePattern(pattern);
				if (pattern.length() >= 4)
					comp.storePattern(pattern.substring(1));
				if (pattern.length() >= 5)
					comp.storePattern(pattern.substring(2));
				
				try {
					ObjectOutputStream outStream = new ObjectOutputStream(new FileOutputStream(f));
					outStream.writeObject(comp);
					outStream.close();
				} catch (IOException e) {
					System.out.println("error updating computer");
				}
			}
			//server.close();
		} catch (IOException e) {
			System.out.println("error with server");
		}
	}
}