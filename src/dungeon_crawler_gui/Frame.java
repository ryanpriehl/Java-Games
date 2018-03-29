package dungeon_crawler_gui;

import javax.swing.JFrame;

/**
 * This class created a frame and panel to run and display the game.
 * 
 * @author Ryan Riehl
 */
public class Frame extends JFrame{
	
	/**
	 * The panel where everything is drawn
	 */
	private Panel panel;
	
	/**
	 * Creates a new panel where most of the stuff happens.
	 * Creates a new thread for multithreading.
	 * Sets basic information for the frame.
	 */
	public Frame(){
		panel = new Panel();
		Thread t = new Thread(panel);
		setTitle("Enter the Fungeon");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(panel);
		setVisible(true);
		setBounds(100, 100, 850, 800);
		getContentPane().add(panel);
		t.start();
	}
	
	/**
	 * Calls the Frame constructor to get things started.
	 * 
	 * @param args None
	 */
	public static void main(String[] args){
		new Frame();
	}
}
