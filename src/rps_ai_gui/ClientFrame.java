
package rps_ai_gui;

import javax.swing.JFrame;

/**
 * Runs the Client side stuff and creates the Frame
 * and Panel needed for the GUI and to get the game
 * running.
 * 
 * @author Ryan Riehl
 */
public class ClientFrame extends JFrame{

	/**
	 * Stores the panel used to display the game
	 */
	private ClientPanel panel;
	
	/**
	 * Creates the Frame for the GUI, creates a panel
	 * and adds it to the frame, starts the multithreading.
	 */
	public ClientFrame() {
		panel = new ClientPanel();
		Thread t = new Thread(panel);
		setTitle("RPS GUI");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(panel);
		setVisible(true);
		setBounds(100, 100, 1100, 800);
		getContentPane().add(panel);
		t.start();
	}

	/**
	 * Creates the ClientFrame to get everything going
	 * @param args none
	 */
	public static void main(String[] args){
		new ClientFrame();
	}
}