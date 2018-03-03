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
	private boolean rFlag = false;
	private boolean lFlag = false;
	
	/**
	 * A constructor for creating a player object with a horizontal position (should stay in one veticle placement)
	 * @param x is the horizontal position of the player the begin in
	 */
	public Player(int x) 
		{
		this.position = new Point(x, 4);
		}
	
	/**
	 * A getter for returning the point object of the players position
	 * @return a point object representing the player position
	 */
	public Point getPosition(){
		return new Point(position);
	}
	
	/**
	 * A getter returning the score of the game for winning
	 * @return is  an int representing the score of the game
	 */
	public int getScore()
	{
		return this.score;
	}
	
	/**
	 * A method for increasing the score by one
	 */
	public void increaseScore()
	{
		this.score++;
	}
	
	/**
	 * A getter for telling if the right arrow key is pressed
	 * @return is a boolean flag representing if the right arrow key is pressed
	 */
	public boolean getRFlag()
	{
		return rFlag;
	}
	
	/**
	 * A setter for setting if the right arrow key is pressed
	 * @param state is a boolean describing if the right arrow key is pressed
	 */
	public void setRFlag(boolean state)
	{
		rFlag = state;
	}
	
	/**
	 * A getter for telling if the left arrow key is pressed
	 * @return is a boolean flag representing if the left arrow key is pressed
	 */
	public boolean getLFlag()
	{
		return lFlag;
	}
	
	/**
	 * A setter for setting if the left arrow key is pressed
	 * @param state is a boolean describing if the left arrow key is pressed
	 */
	public void setLFlag(boolean state)
	{
		lFlag = state;
	}

	/** 
	 * A method that asks the user to move bar in direction 'R', 'L' or 'N'(none), and updates the bar position.
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
	
	