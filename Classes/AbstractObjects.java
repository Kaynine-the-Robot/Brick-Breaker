package Classes;

import java.awt.Point;

public abstract class AbstractObjects {
	
	private Point position = new Point(0,0);
	private char symbol = 'A';
	
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
	public void setPosition(int XCoord,int YCoord)
	{
		if(XCoord > 0 && XCoord < 400)
		{
			this.position.x = XCoord;
		}
		if(YCoord > 0 && YCoord < 500)
		{
			this.position.y = YCoord;
		}
	}
	
	/**
     * This method updates the position of the ball object.
     * @param args unused.
     * @return Nothing.
     * @author Amanda
     */
    public void updatePos(int horzMovement, int vertMovement)
    {	
    	int newX = (int) (this.position.getX() + horzMovement);
    	int newY = (int) (this.position.getY() + vertMovement);
    	
        this.position.move(newX, newY);
    }
    
    /**
     * This method updates the position of the player object.
     * @param args unused.
     * @return Nothing.
     * @author Amanda
     */
    public void updatePos(int horzMovement)
    {	
    	System.out.println(horzMovement);
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
		if(nSymb == 'O' || nSymb == 'B' || nSymb == 'P')
		{
			this.symbol = nSymb;
		}
	}

}
