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
	    private int vertMovement = 1;
<<<<<<< HEAD
	    private char symbol = 'o';
=======
	    private char symbol = 'O';
>>>>>>> 3a3f8d1075597f9c68aacb35ff92d8beb8ee7256
	    
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
	
	}