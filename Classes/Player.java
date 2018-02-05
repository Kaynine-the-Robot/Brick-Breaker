package Classes;
import java.util.Scanner;
import java.awt.Point;

/**
 * This is the player class which contains the methods and attributes of the player's bar.
 * @author Amanda
 *
 */
public class Player {
    
	private Point position;
	
	public Player(int x, int y) 
	{
		this.position = new Point(x,y);
	}
	
	public Point getPosition()
	{
		return position;
	}
	
	/** moveBar() method asks the user to move bar in direction 'R', 'L' or ' '. and updates the bar position.
	 * 
	 * @author Amanda
	 */

	public void moveBar() {
	    
	    Scanner input = new Scanner(System.in);
	    
		System.out.println("Please enter 'L' , 'R' or ' ' for bar movement :");
		
		
		boolean continueLoop = true;
		while (continueLoop == true) {
			
			char direction = input.next().charAt(0);
			
			if (direction == 'L' || direction == 'R') {
				
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
				else if (direction == ' ') {
					System.out.println("no bar movement");
				}
				
				
		}
		
		else {
			System.out.println("Please enter a valid input - 'R' , 'L' or ' '.");
		}

		}	
		}
		
	}
	
	