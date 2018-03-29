package dungeon_crawler_gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * This class creates the panel used to display the game and has all
 * the gameplay mechanics and objects needed for the game.
 * 
 * @author Ryan Riehl
 */
public class Panel extends JPanel implements KeyListener, MouseListener, MouseMotionListener, Runnable {

	/**
	 * Stores the randomly generated enemies in the level
	 */
	private CopyOnWriteArrayList<Enemy> enemies;
	
	/**
	 * Stores the randomly generated items in the level
	 */
	private CopyOnWriteArrayList<Item> items;
	
	/**
	 * Stores the hero the player plays as
	 */
	private Hero hero;
	
	/**
	 * Stores the level the player is on
	 */
	private Level l;
	
	/**
	 * Checks if enemies should attack the player
	 */
	private boolean attackToggle = true;
	
	/**
	 * Checks if the hero is in a state where they can move
	 */
	private boolean canMove = true;
	
	/**
	 * Used to store and display basic information for playing the game
	 */
	private String text = "";
	
	public static final String FILE_PATH = new File("").getAbsolutePath();

	/**
	 * Creates the panel and all relevant parts needed for the game
	 */
	public Panel() {
		setBounds(100, 100, 850, 800);
		setBackground(Color.BLACK);

		// creates Hero from file if one exists, or a new Hero if not
		File f = new File(FILE_PATH +"/src/dungeon_crawler_gui/hero.dat");
		if (f.exists()) {
			try {
				ObjectInputStream in = new ObjectInputStream(new FileInputStream(f));
				hero = (Hero) in.readObject();
				hero.setLocation(new Point(3, 0));
				in.close();
			} catch (ClassNotFoundException e) {
				System.out.println("error");
			} catch (IOException e) {
				System.out.println("error");
			}
		} else {
			hero = new Hero("Link", new Point(3, 0));
		}

		// creating the level and generating enemies/items
		l = new Level();
		l.generateLevel(hero.getLevel());
		enemies = l.getEnemies(hero.getLevel());
		items = l.getItems(hero.getLevel());

		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
		setFocusable(true);
	}
	
	/**
	 * Updates the panel every 60 seconds with any image changes and
	 * listens for inputs from the player.
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
	 * Draws all the stuff needed for the game.
	 * @param g Used for drawing
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		// stops gameplay when the hero is dead
		if (hero.getHp() <= 0) {
			text = "Game over! Press enter to load last save.";
		} else {
			gameplay(g);
		}

		BufferedImage heroImage = null;
		BufferedImage ladder = null;
		BufferedImage shopkeeper = null;
		try {
			heroImage = ImageIO.read(new File(FILE_PATH +"/src/dungeon_crawler_gui/Images/test.png"));
			ladder = ImageIO.read(new File(FILE_PATH +"/src/dungeon_crawler_gui/Images/ladder.png"));
			shopkeeper = ImageIO.read(new File(FILE_PATH +"/src/dungeon_crawler_gui/Images/shopkeeper.png"));
		} catch (IOException e) {}

		// drawing grid, HUD, and static images
		drawGrid(g);
		g.drawImage(shopkeeper, 105, 420, this);
		g.drawImage(ladder, 420, 105, this);
		drawHUD(g);

		// drawing items and enemies
		for (Enemy e : enemies) {
			e.draw(g, this);
		}
		for (Item i : items) {
			i.draw(g, this);
		}

		// draws game info and character portrait
		g.setColor(Color.GREEN);
		g.setFont(new Font("Arial", Font.PLAIN, 36));
		g.drawString(text, 95, 566);
		g.drawImage(heroImage, 105 + hero.getLocation().y * 105, 105 + hero.getLocation().x * 105, this);
	}

	/**
	 * Draws the bars for the game board
	 * @param g Used for drawing
	 */
	public void drawGrid(Graphics g) {
		g.setColor(Color.GRAY);
		g.fillRect(95, 95, 425, 5);
		g.fillRect(95, 200, 425, 5);
		g.fillRect(95, 305, 425, 5);
		g.fillRect(95, 410, 425, 5);
		g.fillRect(95, 515, 425, 5);
		g.fillRect(95, 95, 5, 425);
		g.fillRect(200, 95, 5, 425);
		g.fillRect(305, 95, 5, 425);
		g.fillRect(410, 95, 5, 425);
		g.fillRect(515, 95, 5, 425);
	}

	/**
	 * Draws the HUD with info about the character
	 * @param g Used for drawing
	 */
	public void drawHUD(Graphics g) {
		g.setColor(Color.GREEN);
		g.setFont(new Font("Arial", Font.PLAIN, 48));
		g.drawString(hero.getName() + " (lvl " + String.valueOf(hero.getLevel()) + ")", 575, 135);
		g.drawString(String.valueOf(hero.getHp()), 620, 185);
		g.drawString(String.valueOf(hero.getGold()), 620, 241);
		g.drawString("Inventory", 575, 299);

		// showing the player's inventory
		ArrayList<Item> inventory = hero.getItems();
		g.setFont(new Font("Arial", Font.PLAIN, 36));
		for (int i = 0; i < 5; i++) {
			g.drawString((i + 1) + ". ", 575, 340 + 41 * i);
			if (inventory.size() > i && inventory.get(i) != null) {
				if (l.getRoom(hero.getLocation()) == 's') {
					g.drawString(inventory.get(i).getName() + " (" + inventory.get(i).getValue() + "g)", 605,
							340 + 41 * i);
				} else {
					g.drawString(inventory.get(i).getName(), 605, 340 + 41 * i);
				}
			}
		}

		// drawing pictures in the HUD
		BufferedImage heart = null;
		BufferedImage rupee = null;
		try {
			heart = ImageIO.read(new File(FILE_PATH +"/src/dungeon_crawler_gui/Images/heart.png"));
			rupee = ImageIO.read(new File(FILE_PATH +"/src/dungeon_crawler_gui/Images/rupee.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		g.drawImage(heart, 575, 140, this);
		g.drawImage(rupee, 582, 193, this);
	}

	/**
	 * Basic gameplay mechanics. Does different stuff based on what room the character is in.
	 * i = item room
	 * m = monster room
	 * s = shop
	 * f = finish
	 * @param g Used for drawing
	 */
	public void gameplay(Graphics g) {
		
		// item room
		if (l.getRoom(hero.getLocation()) == 'i') {
			canMove = true;
			for (Item i : items) {
				if (i.getLocation().equals(hero.getLocation())) {
					if (hero.getItems().size() < 5) {
						text = i.getName() + " added to inventory.";
						hero.pickUpItem(i);
						l.clearRoom(hero.getLocation());
						items.remove(i);
					} else {
						text = "Inventory full. " + i.getName() + " sold for " + i.getValue() + " gold.";
						hero.collectGold(i.getValue());
						l.clearRoom(hero.getLocation());
						items.remove(i);
					}
				}
			}
		}

		// monster room
		else if (l.getRoom(hero.getLocation()) == 'm') {
			text = "";
			canMove = false;
			g.setColor(Color.GREEN);
			g.setFont(new Font("Arial", Font.PLAIN, 36));
			Enemy here = null;
			for (Enemy e : enemies) {
				if (e.getLocation().equals(hero.getLocation())) {
					here = e;
				}
			}
			g.drawString(here.toString(), 95, 566);

			// drawing buttons
			g.setColor(Color.GRAY);
			g.fillRect(95, 576, 200, 100);
			g.fillRect(305, 576, 200, 100);
			if (hero.hasPotion())
				g.fillRect(515, 576, 200, 100);

			g.setColor(Color.BLACK);
			g.drawString("Attack", 145, 637);
			g.drawString("Flee", 370, 637);
			if (hero.hasPotion())
				g.drawString("Use potion", 530, 637);

			if (attackToggle) {
				here.attack(hero);
				attackToggle = false;
			} else {

			}

		}

		// shop
		else if (l.getRoom(hero.getLocation()) == 's') {
			canMove = true;
			g.setColor(Color.GREEN);
			g.setFont(new Font("Arial", Font.PLAIN, 36));
			text = "You entered the shop! Use 1-5 to sell items.";
		}

		// level finish
		else if (l.getRoom(hero.getLocation()) == 'f') {
			canMove = true;
			hero.heal((int) (20 * Math.pow(2, hero.getLevel() - 1)));
			hero.increaseLevel();
			try {
				ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(new File(FILE_PATH +"/src/dungeon_crawler_gui/hero.dat")));
				out.writeObject(hero);
				out.close();
			} catch (IOException e) {
				System.out.println("error");
			}

			text = "Floor cleared! Your hero gained a level.";
			l.generateLevel(hero.getLevel());
			enemies = l.getEnemies(hero.getLevel());
			items.clear();
			items = l.getItems(hero.getLevel());
			hero.setLocation(new Point(3, 0));
		}
	}

	/**
	 * Used for combat. Allows the user to click on buttons for attacking, fleeing, and using potions
	 * when they are in combat.
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		// the location of the mouse click
		Point loc = e.getPoint();
		// the locations of the buttons
		Rectangle attack = new Rectangle(95, 576, 200, 100);
		Rectangle flee = new Rectangle(305, 576, 200, 100);
		Rectangle potion = new Rectangle(515, 576, 200, 100);
		
		// attacking
		if (attack.contains(loc)) {
			for (Enemy en : enemies) {
				if (en.getLocation().equals(hero.getLocation())) {
					hero.attack(en);
					if (en.getHp() <= 0) {
						if (hero.getItems().size() < 5) {
							hero.pickUpItem(en.getItem());
							text = "Enemy defeated. " + en.getItem().getName() + " added to inventory.";
						} else {
							text = "Enemy defeated. Inventory full. " + en.getItem().getName() + " sold for "
									+ en.getItem().getValue() + " gold.";
							hero.collectGold(en.getItem().getValue());
						}
						hero.collectGold(en.getGold());
						enemies.remove(en);
						l.clearRoom(hero.getLocation());
						canMove = true;

					}
					attackToggle = true;
				}
			}
			
			// fleeing
		} else if (flee.contains(loc) && l.getRoom(hero.getLocation()) == 'm') {
			canMove = true;
			Random rand = new Random();
			boolean moved = false;
			while (!moved) {
				int dir = rand.nextInt(4);
				if (dir == 0 && hero.canGoNorth()) {
					hero.goNorth(l);
					attackToggle = true;
					moved = true;
				} else if (dir == 1 && hero.canGoEast()) {
					hero.goEast(l);
					attackToggle = true;
					moved = true;
				} else if (dir == 2 && hero.canGoSouth()) {
					hero.goSouth(l);
					attackToggle = true;
					moved = true;
				} else if (dir == 3 && hero.canGoWest()) {
					hero.goWest(l);
					attackToggle = true;
					moved = true;
				}
			}
			
			// using potion
		} else if (potion.contains(loc)) {
			for (int i = 0; i < hero.getItems().size(); i++) {
				if (hero.getItems().get(i).getName().equals("Potion")) {
					hero.heal((int) (20 * Math.pow(2, hero.getLevel() - 1)));
					hero.removeItem(i);
					i = hero.getItems().size() + 1;
				}
			}
		}
	}

	/**
	 * Does different actions based on various key presses
	 * WASD : hero movement
	 * 1 - 5 : selling items
	 * Enter : restart game when defeated
	 * @param e The 
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		
		// hero movement stuff
		if (e.getKeyCode() == KeyEvent.VK_W && canMove) {
			if (hero.canGoNorth()) {
				hero.goNorth(l);
				attackToggle = true;
				text = "";
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_D && canMove) {
			if (hero.canGoEast()) {
				hero.goEast(l);
				attackToggle = true;
				text = "";
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_S && canMove) {
			if (hero.canGoSouth()) {
				hero.goSouth(l);
				attackToggle = true;
				text = "";
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_A && canMove) {
			if (hero.canGoWest()) {
				hero.goWest(l);
				attackToggle = true;
				text = "";
			}
		}

		// restarting on game over
		if (e.getKeyCode() == KeyEvent.VK_ENTER && hero.getHp() <= 0) {
			File f = new File(FILE_PATH +"/src/dungeon_crawler_gui/hero.dat");
			if (f.exists()) {
				try {
					ObjectInputStream in = new ObjectInputStream(new FileInputStream(f));
					hero = (Hero) in.readObject();
					hero.setLocation(new Point(3, 0));
					in.close();
				} catch (ClassNotFoundException error) {
					System.out.println("error");
				} catch (IOException error) {
					System.out.println("error");
				}
			} else {
				hero = new Hero("Link", new Point(3, 0));
			}

			l.generateLevel(hero.getLevel());
			enemies = l.getEnemies(hero.getLevel());
			items = l.getItems(hero.getLevel());
		}

		// item selling stuff
		if (l.getRoom(hero.getLocation()) == 's') {
			if (e.getKeyCode() == KeyEvent.VK_1) {
				if (hero.getItems().size() >= 1) {
					hero.collectGold(hero.getItems().get(0).getValue());
					hero.removeItem(0);
				}
			}
			if (e.getKeyCode() == KeyEvent.VK_2) {
				if (hero.getItems().size() >= 2) {
					hero.collectGold(hero.getItems().get(1).getValue());
					hero.removeItem(1);
				}
			}
			if (e.getKeyCode() == KeyEvent.VK_3) {
				if (hero.getItems().size() >= 3) {
					hero.collectGold(hero.getItems().get(2).getValue());
					hero.removeItem(2);
				}
			}
			if (e.getKeyCode() == KeyEvent.VK_4) {
				if (hero.getItems().size() >= 4) {
					hero.collectGold(hero.getItems().get(3).getValue());
					hero.removeItem(3);
				}
			}
			if (e.getKeyCode() == KeyEvent.VK_5) {
				if (hero.getItems().size() >= 5) {
					hero.collectGold(hero.getItems().get(4).getValue());
					hero.removeItem(4);
				}
			}
		}
	}

	// unused listener methods
	@Override
	public void mouseDragged(MouseEvent e) {

	}

	@Override
	public void mouseMoved(MouseEvent e) {

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
