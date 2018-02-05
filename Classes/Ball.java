package Classes;
import java.awt.Point;

/**
 * This is the ball class which will have all the methods and attributes to control the ball 
 * and it's interactions.
 * @author
 *
 */
public class Ball 
	{
	    
	    private Point position;
	    private int horzMovement = 1;
	    private int vertMovement = -1;
	    private char symbol = 'O';
	    
	    public Ball(int x, int y)
	    {
	        this.position = new Point(x, y);
	    }
	    
	    /**
	     * This method returns the position of the ball object.
	     * @param args unused.
	     * @return Ball position as a Point object.
	     * 
	     */
	    public Point getPosition()
	    {
	        return position;
	    }
	    
	    /**
	     * This method updates the position of the ball object.
	     * @param args unused.
	     * @return Nothing.
	     * 
	     */
	    public void updatePos()
	    {
	        position.move(horzMovement, vertMovement);
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
	    
	    public char getSymbol()
	    {
	        return symbol;
	    }
	    
	     /** checkLocation() tracks ball location and responds appropriately.
	     * @args None
	     * This just MOVES the ball, but doesnt detect collision with block or bar yet, it sees them as invisible.
	     * @author Amanda
	     */
	    
	    public void checkLocation()
	    {
	    	if (this.position.getX() == 0 || this.position.getX() == 5) { 
	    		
	    		this.vertCollision();
	    	
	    		if (this.position.getY() == 0) {
	    			this.horzCollision();
	    		}
	    				
	    	} 
	    	
	    	else if (this.position.getY() == 0 ) {
	    		
	    		this.horzCollision();
	    	} 
	    	
	    	
	    	else if (this.position.getY() == 4) {
	    		System.out.println("You lose.");
	    		System.exit(0);
	    	}
	    	
	    	
	    }
	
	}