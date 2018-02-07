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
	
	public Point getPosition(){
		return position;
	}
	
	/** Move method asks the user to move bar in direction 'R', 'L' or ' '. and updates the bar position.
	 * 
	 * @author Amanda
	 */

	public void moveBar() {
		System.out.println("Please enter 'L' , 'R' or 'N'(none) for bar movement :");
		Scanner input = new Scanner(System.in);
		
		boolean continueLoop = true;
	
		while (continueLoop == true) {
			
			char direction = input.next().charAt(0);
			
			if (direction == 'L' || direction == 'R' || direction == 'N') {
				
				continueLoop = false;
				int xCor = (int) position.getX();
				int yCor = (int) position.getY();
				
				if (direction == 'R'){ 
					
					position.move(xCor+1,yCor);
					
				}
				
				else if (direction == 'L') {
			
					position.move(xCor-1,yCor);
					System.out.println("I moved the bar to:"+this.getPosition());
				}
				else if (direction == 'N') {
					continueLoop = false;
					
					System.out.println("No bar movement");
				}
				
		}	
		else {
			System.out.println("Please enter a valid input - 'R' , 'L' or ' '.");
		}

		}	
		}
	}
	
	