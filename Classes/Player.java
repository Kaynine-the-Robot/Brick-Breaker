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
	
	public Player(int x) {
		this.position = new Point(x, 4);
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
	
	public boolean getRFlag()
	{
		return rFlag;
	}
	
	public void setRFlag(boolean state)
	{
		rFlag = state;
	}
	
	public boolean getLFlag()
	{
		return lFlag;
	}
	
	public void setLFlag(boolean state)
	{
		lFlag = state;
	}
	

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
	
	