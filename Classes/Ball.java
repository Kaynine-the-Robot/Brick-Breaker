package Classes;
import java.awt.Point;
import Classes.Block;

/**
 * This is the ball class which will have all the methods and attributes to control the ball 
 * and it's interactions.
 * @author Sebastian, Amanda
 *
 */
public class Ball extends AbstractObjects
	{
	    
	    private int horzMovement = 1;
	    private int vertMovement = -1;
	    private boolean hitBrick = false;
	    
	    /**
	     * A constructor for a ball object
	     * @param x is an int for the x coordinate of a Point object
	     * @param y is an int for the y coordinate of a Point object
	     */
	    public Ball(int x, int y)
	    {
	        this.setPosition(x, y);
	        this.setSymbol('O');
	    }
	    
	    /**
	     * This is a getter for returning a value of current horizontal movement
	     * @param none
	     * @return horzMovement value
	     */
	    public int getHorzMovement() 
	    {
	    	return this.horzMovement;
	    }
	    
	     /**
	      * This is a getter for returning a value of current vertical movement
	      * @return
	      */
	    public int getVertMovement() 
	    {
	    	return this.vertMovement;
	    }
	    
	    /**
	     * This method changes the horizontal speed of the ball object when there has been a collision.
	     * @param args unused.
	     * @return Nothing.
	     * 
	     */
	    public void horzCollision()
	    {
	        horzMovement = horzMovement * -1;
	    }
	    
	    /**
	     * This method changes the vertical speed of the ball object when there has been a collision.
	     * @param args unused.
	     * @return Nothing.
	     * 
	     */
	    public void vertCollision()
	    {
	        vertMovement = vertMovement * -1;
	    }
	    
	    /**
	     * A getter for returning a flag if a collision with a brick is true
	     * @return a boolean telling if the ball has hit a brick
	     */
	    public boolean getHitBrick()
	    {
	    	return hitBrick;
	    }
	    
	    /**
	     * A method for setting if a brick is hit
	     * @param state is a boolean describing if a brick has been hit
	     */
	    public void setHitBrick(boolean state)
	    {
	    	hitBrick = state;
	    }
	    
	    /** 
	     * A method that tracks ball location and responds appropriately
	     * @returns true if the ball collides with a block, otherwise false
	     */
	    public boolean checkLocation()
	    {
	    	//Checking if the ball will hit horizontal boundaries
	    	if (this.getPosition().getX() == 0 || this.getPosition().getX() == 5) 
	    	{ 
	    		this.horzCollision();
	    	} 
	    	//Checks for is ball is at the top of screen
	    	if (this.getPosition().getY() == 0 ) 
	    	{
	    		this.vertCollision();
	    		return true;
	    	} 
	    	//Checks for if ball is at the bottom of screen
	    	else if (this.getPosition().getY() == 4) 
	    	{
	    		System.out.println("You lose.");
	    		System.exit(0);
	    	}
			return false;
	    }
	   
	    	
	    }
	    
	    
	
