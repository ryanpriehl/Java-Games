
package hunting_game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * This creates creates the panel to show the game and controls
 * drawing and animation.
 * 
 * @author 013507765
 */
public class Panel extends JPanel implements KeyListener, MouseListener, MouseMotionListener, Runnable{
	
	/**
	 * Stores the hunter the player controls
	 */
	private Hunter hunter;
	
	/**
	 * Stores the quarry generator
	 */
	private QuarryGenerator Qgen;
	
	/**
	 * Stores the obstacles on the screen
	 */
	private ArrayList<Obstacle> obstacles;
	
	/**
	 * Stores the quarries currently on screen, CopyOnWriteArrayList used because of multithreading
	 */
	//private ArrayList<Quarry> quarries;
	private CopyOnWriteArrayList<Quarry> quarries;
	
	/**
	 * Used to control the hunter's movement when stuck on objects
	 */
	private boolean checkCollision;
	
	/**
	 * Creates the panel to show the game
	 */
	public Panel(){
		setBounds(100, 100, 800, 800);
		setBackground(Color.BLACK);
		
		hunter = new Hunter(new Point(380, 340), 20, 20, 5, 3);
		Qgen = new QuarryGenerator();
		obstacles = new ArrayList<Obstacle>();
		//quarries = new ArrayList<Quarry>();
		quarries = new CopyOnWriteArrayList<Quarry>();
		
		Random rand = new Random();
		for(int i = 0; i < 10; i++){
			obstacles.add(new Obstacle(new Point(rand.nextInt(725), rand.nextInt(710)), 1));
		}
		
		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
		setFocusable(true);
	}
	
	/**
	 * Checks for collisions between the different entities and controls movement accordingly
	 */
	public void testCollisions(){
		Random rand = new Random();
		if(checkCollision){
			if(hunter.getLocation().x <= 0 || hunter.getLocation().y <= 0 || hunter.getLocation().x + 20 >= this.getWidth() || hunter.getLocation().y + 20 >= this.getHeight()){
				hunter.stopMoving();
				checkCollision = false;
			}
			for(Obstacle o : obstacles){
				if(o.testCollision(hunter)){
					hunter.stopMoving();
					checkCollision = false;
				}
			}
		}
		
		for(Quarry q : quarries){
			if(q.getLocation().x <= 0 || q.getLocation().y <= 0 || q.getLocation().x + q.getWidth() >= this.getWidth() || q.getLocation().y + q.getHeight() >= this.getHeight())
				quarries.remove(quarries.indexOf(q));
			if(hunter.getBullet() != null && hunter.testHit(q)){
				q.takeHit();
			}
			if(q.isDead()){
				obstacles.add(new Obstacle(q));
				quarries.remove(quarries.indexOf(q));
			}
		}
		for(Obstacle o : obstacles){
			if(hunter.getBullet() != null)
				hunter.getBullet().testCollision(o);
			for(Quarry q : quarries){
				if(o.testCollision(q)){
					if(q.getDirection() > 4) q.setDirection(q.getDirection() - 5 + rand.nextInt(3));
					else q.setDirection(q.getDirection() + 3 + rand.nextInt(3));
				}
			}
		}
		
		// creates new quarries if there aren't at least 3 on screen
		if(quarries.size() < 3){
			quarries.add(Qgen.generateQuarry());
		}
	}
	
	/**
	 * Moves and redraws the entities to create the animation
	 */
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		//testCollisions();
		if(hunter.isMoving())
			checkCollision = true;
		
		hunter.update(g);
		for(Quarry q : quarries){
			q.update(g);
		}
		for(Obstacle o : obstacles){
			o.update(g);
		}
	}
	
	/**
	 * Redraws the images about 60 times a second to create the animation
	 */
	@Override
	public void run() {
		while(true){
			testCollisions();
			repaint();
			try{
				Thread.sleep(16);
			} catch (InterruptedException e){}
		}
	}

	/**
	 * Uses the mouse pointer to control the hunter's direction (or tries to)
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
		Point loc = new Point(e.getX(), e.getY());
		Point p = hunter.getLocation();
		if(loc.x == p.x + 10 && loc.y < p.y){
			hunter.setDirection(1);
		}
		else if(loc.x > p.x + 20 && loc.y < p.y && loc.x - p.x == -(loc.y - p.y)){
			hunter.setDirection(2);
		}
		else if(loc.x > p.x && loc.y == p.y + 10){
			hunter.setDirection(3);
		}
		else if(loc.x > p.x + 20 && loc.y > p.y + 20 && loc.x - p.x + 20 == loc.y - p.y + 20){
			hunter.setDirection(4);
		}
		else if(loc.x == p.x + 10 && loc.y > p.y){
			hunter.setDirection(5);
		}
		else if(loc.x < p.x && loc.y > p.y + 20 && loc.x - p.x == -(loc.y - p.y)){
			hunter.setDirection(6);
		}
		else if(loc.x < p.x && loc.y == p.y + 10){
			hunter.setDirection(7);
		}
		else if(loc.x < p.x && loc.y < p.y && loc.x - p.x == loc.y - p.y){
			hunter.setDirection(8);
		}
	}

	/**
	 * Fires a bullet when the mouse is pressed
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		hunter.fireBullet();
	}
	
	/**
	 * Does stuff when various keys are pressed.
	 * WASD control movement, enter starts/stops motion, space fires bullet
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_D)
			hunter.spinCW();
		else if(e.getKeyCode() == KeyEvent.VK_A)
			hunter.spinCCW();
		else if(e.getKeyCode() == KeyEvent.VK_W)
			hunter.incSpeed();
		else if(e.getKeyCode() == KeyEvent.VK_S)
			hunter.decSpeed();
		else if(e.getKeyCode() == KeyEvent.VK_ENTER)
			hunter.toggleMoving();
		else if(e.getKeyCode() == KeyEvent.VK_SPACE)
			hunter.fireBullet();
	}

	// unused listener methods
	@Override
	public void mouseDragged(MouseEvent e) {
		
	}
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
	@Override
	public void keyReleased(KeyEvent e) {
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		
	}
}
