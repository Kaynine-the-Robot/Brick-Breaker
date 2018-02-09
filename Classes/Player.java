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
	private int score = 0;
	
	public Player(int y) {
		this.position = new Point(2, y);
		}
	
	public Point getPosition(){
		return position;
	}
	
	public int getScore()
	{
		return this.score;
	}
	
	public void increaseScore()
	{
		this.score++;
	}
	
	/** Move method asks the user to move bar in direction 'R', 'L' or ' '. and updates the bar position.
	 * 
	 * @author Amanda
	 */
	public void moveBar() {
	/** Move method asks the user to move bar in direction 'R', 'L' or 'N'(none), and updates the bar position.
	 * @param None
	 * @return Nothing
	 */

	public void moveBar() 
	{
		
		System.out.println("Please enter 'L' , 'R' or 'N'(none) for bar movement :");
		
		Scanner input = new Scanner(System.in);
		
		boolean continueLoop = true;
	
		while (continueLoop == true) {
			
			char direction = input.next().charAt(0);
			
			if (direction == 'L' || direction == 'R' || direction == 'N') 
			{
				continueLoop = false;
				int xCor = (int) position.getX();
				int yCor = (int) position.getY();
				
				
					if (direction == 'R' && xCor !=4)
					{ 
						position.move(xCor+1,yCor);
					}
					
				
					else if (direction == 'L' && xCor !=0) 
					{
						position.move(xCor-1,yCor);	
					}
				
				
					else if (direction == 'N') 
					{
						continueLoop = false;
					}
				
			}	
				
			else 
			{
				continue;
			}

		}	
	}
	}
	
	