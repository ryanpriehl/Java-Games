
package hunting_game;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * This class is for creating randomly creating new Quarries.
 * 
 * @author 013507765
 */
public class QuarryGenerator {
	
	/**
	 * Stores the types of Quarry that can be created and their attributes
	 */
	private ArrayList<Quarry> quarry;
	
	private static final String FILE_NAME = new File("").getAbsolutePath();
	
	/**
	 * Creates a new QuarryGenerator with Quarry types read in from QuarryList.txt
	 */
	public QuarryGenerator(){
		quarry = new ArrayList<Quarry>();
		try{
			Scanner read = new Scanner(new File(FILE_NAME +"/src/hunting_game/QuarryList.txt"));
			read.useDelimiter(",");
			while(read.hasNextLine()){
				String name = read.next();
				int weight = read.nextInt();
				int hp = read.nextInt();
				int speed = Integer.valueOf(read.nextLine().substring(1));
				quarry.add(new Quarry(new Point(0,0), 15 * hp, 15 * hp, hp, speed/2, name, weight));
			}
		} catch (FileNotFoundException e){
			System.out.println("FNF");
		}
	}
	
	/**
	 * Creates a new Quarry of random type at a random point at one of the edges of the screen
	 * @return The new Quarry
	 */
	public Quarry generateQuarry(){
		Random rand = new Random();
		Quarry q = quarry.get(rand.nextInt(quarry.size()));
		Quarry newQ;
		int randSide = rand.nextInt(4);
		if(randSide == 0){
			newQ = new Quarry(new Point(0, rand.nextInt(760 - q.getHeight())), q.getWidth(), q.getHeight(), q.getHp(), q.getSpeed(), q.getName(), q.getWeight());
			newQ.setDirection(rand.nextInt(3) + 2);
		}
		else if(randSide == 1){
			newQ = new Quarry(new Point(rand.nextInt(780 - q.getWidth()), 0), q.getWidth(), q.getHeight(), q.getHp(), q.getSpeed(), q.getName(), q.getWeight());
			newQ.setDirection(rand.nextInt(3) + 4);
		}
		else if(randSide == 2){
			newQ = new Quarry(new Point(780 - q.getWidth(), rand.nextInt(760- q.getHeight())), q.getWidth(), q.getHeight(), q.getHp(), q.getSpeed(), q.getName(), q.getWeight());
			newQ.setDirection(rand.nextInt(3) + 6);
		}
		else{
			newQ = new Quarry(new Point(rand.nextInt(780 - q.getHeight()), 760 - q.getHeight()), q.getWidth(), q.getHeight(), q.getHp(), q.getSpeed(), q.getName(), q.getWeight());
			newQ.setDirection(rand.nextInt(3));
			if(newQ.getDirection() == 0) newQ.setDirection(8);
		}
		return newQ;
	}
}
