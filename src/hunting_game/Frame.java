
package hunting_game;

import javax.swing.JFrame;

/**
 * Contains the main to play the game and the frame to play it in
 * 
 * @author 013507765
 */
public class Frame extends JFrame{
	
	/**
	 * Stores the panel to show the game
	 */
	private Panel panel;
	
	/**
	 * Creates a frame to play the game
	 */
	public Frame(){
		panel = new Panel();
		Thread t = new Thread(panel);
		setTitle("Hunting Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(panel);
		setVisible(true);
		setBounds(100, 100, 800, 800);
		getContentPane().add(panel);
		t.start();
	}
	
	/**
	 * Creates a frame that does the important stuff
	 * @param args none
	 */
	public static void main(String[] args){
		new Frame();
	}
}
