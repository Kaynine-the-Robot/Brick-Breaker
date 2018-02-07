package Classes;
import java.util.Scanner;
import java.awt.Point;

/**
 * This is the player class which contains the methods and attributes of the player's bat.
 * @author
 *
 */
public class Player {
	private Point position;
	
	
	public Player(int x, int y) {
		this.position = new Point(x,y);
		}
	
	public void Move(){
	
		Scanner scan = new Scanner(System.in);
		String direction = scan.next();
		if (direction == "L" || direction == "R") {
			System.out.println("Yes this movement is correct");
		}	
		else {
			System.out.println("No");
		}
	}
	}