package Classes;

import java.awt.Point;

import javafx.stage.Screen;

public abstract class AbstractObjects {
	
	private Point position = new Point(0,0);
	private char symbol = 'A';
	final double screenWidth = Screen.getPrimary().getVisualBounds().getHeight() - 50;
	
	/**
     * This method returns the position of the game object.
     * @return Position as a Point object.
     * 
     */
	public Point getPosition()
	{
		return new Point(this.position);
	}
	
	/**
	 * A setter for changing the position of an object
	 * @param XCoord is an integer of the x coordinate to be changed to
	 * @param YCoord is an integer of the y coordinate to be changed to
	 */
	public void setPosition(double XCoord,double YCoord)
	{
		if(XCoord < 816)
		{
			this.position.x = (int) XCoord;
		}
		if(YCoord < screenWidth)
		{
			this.position.y = (int) YCoord;
		}
	}
	
	/**
     * This method updates the position of the ball object.
     */
    public void updatePos(double horzMovement, double vertMovement)
    {	
    	int newX = (int) (this.position.getX() + horzMovement);
    	int newY = (int) (this.position.getY() + vertMovement);
    	
        this.position.move(newX, newY);
    }
    
    /**
     * This method updates the position of the player object.
     */
    public void updatePos(double horzMovement)
    {	
    	int newX = (int) (this.position.getX() + horzMovement);
    	int newY = (int) (this.position.getY());
    	
        this.position.move(newX, newY);
    }
	
	/**
     * This method returns the text representation of the game object.
     * @return symbol
     */
	public char getSymbol()
	{
		return this.symbol; //char type is primitive
	}
	
	/**
	 * A setter for changing the symbol representing the kind of object
	 * @param nSymb is the symbol to be changed to
	 */
	public void setSymbol(char nSymb)
	{
		if(nSymb == 'O' || nSymb == 'B' || nSymb == 'P' || nSymb == 'N' || nSymb == 'H') //Symbols for ball, block, player, normal block, and hard block respectively
		{
			this.symbol = nSymb;
		}
	}

}
