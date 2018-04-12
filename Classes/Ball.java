package Classes;
import java.awt.Point;
import Classes.*;
import javafx.scene.image.Image;
import javafx.scene.shape.Circle;

/**
 * This is the ball class which will have all the methods and attributes to control the ball 
 * and it's interactions.
 * @author Sebastian, Amanda
 *
 */
public class Ball extends AbstractObjects
	{
	    
	    private double horzMovement = 1;
	    private double vertMovement = -1;
	    private boolean hitBrick = false;
	    private Image[] ballSprites = new Image[5];
	    private int ballPositionFlag;
	    private long speedTimer;
	    private long startTime;
	    
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
	    
	    public Ball(double x, double y, Image ballLeft, Image ballMiddleLeft, Image ball, Image ballMiddleRight, Image ballRight)
	    {
	        this.setPosition(x, y);
	        this.setSymbol('O');
	        ballSprites[0] = ballLeft; ballSprites[1] = ballMiddleLeft; ballSprites[2] = ball; ballSprites[3] = ballMiddleRight; ballSprites[4] = ballRight;
	    }
 
	    public Image getBallSpritesAtIndex(int index)
	    {
	    	return ballSprites[index];
	    }
	    
	    public int getPositionFlag()
	    {
	    	return this.ballPositionFlag;
	    }
	    
	    public void setPositionFlag()
	    {
	    	switch(this.ballPositionFlag)
	    	{
	    	case 0: this.ballPositionFlag = 1; break;
	    	case 1: if(this.horzMovement==1) {this.ballPositionFlag = 2;} 
	    			else if(this.horzMovement == -1) {this.ballPositionFlag = 0;}; break;
	    	case 2: if(this.horzMovement==1) {this.ballPositionFlag = 3;} 
					else if(this.horzMovement == -1) {this.ballPositionFlag = 1;}; break;
	    	case 3: if(this.horzMovement==1) {this.ballPositionFlag = 4;} 
					else if(this.horzMovement == -1) {this.ballPositionFlag = 2;}; break;
	    	case 4: this.ballPositionFlag = 3; break;
	    	default: this.ballPositionFlag = 1; break;
	    	}
	    }
	    
	    public void reset()
	    {
	    	horzMovement = 1;
		    vertMovement = -1;
	    }
	    
	    /**
	     * This is a getter for returning a value of current horizontal movement
	     * @param none
	     * @return horzMovement value
	     */
	    public double getHorzMovement() 
	    {
	    	return this.horzMovement;
	    }
	    
	     /**
	      * This is a getter for returning a value of current vertical movement
	      * @return
	      */
	    public double getVertMovement() 
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
	    
	    public void increaseSpeed()
	    {
	    	if(this.horzMovement > 0 && this.horzMovement < 4)
	    	{
	    		this.horzMovement = this.horzMovement + 0.1;
	    	}
	    	else if(this.horzMovement < 0 && this.horzMovement > -4)
	    	{
	    		this.horzMovement = this.horzMovement - 0.1;
	    	}
	    	if(this.vertMovement > 0 && this.vertMovement < 4)
	    	{
	    		this.vertMovement = this.vertMovement + 0.1;
	    	}
	    	else if (this.vertMovement < 0 && this.vertMovement > -4)
	    	{
	    		this.vertMovement = this.vertMovement - 0.1;
	    	}
	    	
	    }
	    
	    public void checkCurrentTime()
		{
			if(this.speedTimer != 0)
			{
				this.speedTimer = (30 - ((System.currentTimeMillis() / 1000) - this.startTime));
			}
			else if (this.speedTimer == 0)
			{
				this.setSpeedTimer();
				this.increaseSpeed();
			}
			
		}
	    
	    public void setSpeedTimer()
		{
			this.startTime = System.currentTimeMillis() / 1000;
			this.speedTimer = (30 - ((System.currentTimeMillis() / 1000) - this.startTime));
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
	     * Method that pauses the balls movement
	     * 
	     */
	    public void pauseBall()
	    {
	    	vertMovement = 0;
	    	horzMovement = 0;
	    }
	    
	    /** 
	     * A method that tracks ball location and responds appropriately
	     * @returns true if the ball collides with a block, otherwise false
	     */
	    public boolean checkLocation()
	    {
	    	//Checking if the ball will hit horizontal boundaries
	    	if (this.getPosition().getX() == 0 || this.getPosition().getX() == 9) 
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
	    	else if (this.getPosition().getY() == 19) 
	    	{
	    		System.out.println("You lose.");
	    		System.exit(0);
	    	}
			return false;
	    }
	   
	    	
	    }
	    
	    
	
