
package rps_ai_gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * Creates the panel part of the Client's GUI and sets up everything
 * to connect to and communicate with the Server
 * 
 * @author Ryan Riehl
 */
public class ClientPanel extends JPanel implements MouseListener, Runnable {

	/**
	 * The socket to connect with the Server
	 */
	private Socket s;
	
	/**
	 * Used to send data to the Server
	 */
	private PrintStream out;
	
	/**
	 * Used to get data from the Server
	 */
	private BufferedReader in;
	
	/**
	 * Stores the player's choice
	 */
	private String playerThrow = "";
	
	/**
	 * Stores the computer's choice
	 */
	private String compThrow = "";
	
	/**
	 * Stores the player's score
	 */
	private int playerScore = 0;
	
	/**
	 * Stores the computer's score
	 */
	private int computerScore = 0;
	
	/**
	 * Checks if the user has made a choice yet
	 */
	private boolean madeChoice = false;
	
	/**
	 * Path to this project for finding files
	 */
	public static final String FILE_PATH = new File("").getAbsolutePath();;

	/**
	 * Creates the panel, connects to the Server, and creates 
	 * PrintStream and BufferedReader to communicate with Server.
	 */
	public ClientPanel() {

		setBounds(100, 100, 1100, 800);
		setBackground(Color.BLACK);
		setFocusable(true);
		addMouseListener(this);

		try {
			s = new Socket("localhost", 1025);
			out = new PrintStream(s.getOutputStream());
			in = new BufferedReader(new InputStreamReader(s.getInputStream()));
		} catch (IOException e) {
			System.out.println("error with client");
		}
	}

	/**
	 * Creates the BufferedImages for the GUI, displays them and
	 * all the text to the panel
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		BufferedImage red = null, blue = null;
		BufferedImage bulbasaur = null, charmander = null, squirtle = null;

		try {
			red = ImageIO.read(new File(FILE_PATH +"/src/rps_ai_gui/Images/Red.png"));
			blue = ImageIO.read(new File(FILE_PATH +"/src/rps_ai_gui/Images/Blue.png"));
			bulbasaur = ImageIO.read(new File(FILE_PATH +"/src/rps_ai_gui/Images/Bulbasaur.png"));
			charmander = ImageIO.read(new File(FILE_PATH +"/src/rps_ai_gui/Images/Charmander.png"));
			squirtle = ImageIO.read(new File(FILE_PATH +"/src/rps_ai_gui/Images/Squirtle.png"));
		} catch (IOException e) {
			System.out.println("error with images");
		}

		g.drawImage(red, 100, 130, 175, 377, this);
		g.drawImage(blue, 825, 100, this);
		g.drawImage(bulbasaur, 300, 357, 150, 150, this);
		g.drawImage(charmander, 475, 357, 150, 150, this);
		g.drawImage(squirtle, 650, 357, 150, 150, this);

		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.PLAIN, 36));
		g.drawString("Choose your starter: ", 375, 307);
		if(madeChoice){
			g.drawString("You chose " +playerThrow +"!", 350, 575);
			g.drawString("Your rival chose " +compThrow +"!", 350, 625);
		}
		g.drawString("Score: " + playerScore, 100, 575);
		g.drawString("Score: " + computerScore, 825, 575);
	}

	/**
	 * Constantly repaints the GUI
	 */
	@Override
	public void run() {
		while (true) {
			repaint();
			try {
				Thread.sleep(16);
			} catch (InterruptedException e) {
			}
		}
	}

	/**
	 * Gets the user's mouse press and determines their choice based
	 * on it's location. Sends that choice to the Server and then
	 * gets the Computer's throw back.
	 * 
	 * @param e The mouse press
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		boolean valid = false;
		Point loc = new Point(e.getX(), e.getY());
		Rectangle bulbRect = new Rectangle(300, 357, 150, 150);
		Rectangle charRect = new Rectangle(475, 357, 150, 150);
		Rectangle squirRect = new Rectangle(650, 357, 150, 150);

		if (bulbRect.contains(loc)) {
			playerThrow = "Bulbasaur";
			out.println(playerThrow);
			valid = true;
			madeChoice = true;
		} else if (charRect.contains(loc)) {
			playerThrow = "Charmander";
			out.println(playerThrow);
			valid = true;
			madeChoice = true;
		} else if (squirRect.contains(loc)) {
			playerThrow = "Squirtle";
			out.println(playerThrow);
			valid = true;
			madeChoice = true;
		}

		if (valid) {
			try {
				compThrow = in.readLine();
			} catch (IOException exception) {
				System.out.println("error reading computer input");
			}
			
			if (playerThrow.equals(compThrow)) {
				
			} else if ((playerThrow.equals("Bulbasaur") && compThrow.equals("Squirtle"))
					|| (playerThrow.equals("Charmander") && compThrow.equals("Bulbasaur"))
					|| (playerThrow.equals("Squirtle") && compThrow.equals("Charmander"))) {
				playerScore++;
			} else {
				computerScore++;
			}
		}
	}

	
	// unused listeners
	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}
}