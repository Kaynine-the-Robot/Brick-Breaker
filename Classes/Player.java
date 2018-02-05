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
	private char symbol = 'P';
	
	public Player() 
	{
		this.position = new Point(2,4);
	}
	
	public Player(int startX) 
	{
		this.position = new Point(startX, 4);
	}
	
	public char getSymbol() 
	{
		return this.symbol;
	}
	
	public Point getPosition()
	{		
		return position;
	}
	
	/** moveBar() method asks the user to move bar in direction 'R', 'L' or 'N'(none). and updates the bar position.
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
				}
				
		}	
		else {
		    
			System.out.println("Please enter a valid input - 'R' , 'L' or ' '.");
		}

		}	
		}
		
	}
