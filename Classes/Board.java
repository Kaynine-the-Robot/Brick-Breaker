package Classes;
import java.awt.Point;

/**
 * This is the Board class for the Brickbreaker game. 
 * It's methods are for creating and modifying the board size.
 * @author Kaynen
 *
 */
public class Board 
	{
	    private int xLength;
	    private int yLength;
	    private char[][] board;
	    
	    /**
	     * The constructor for the board class.
	     * Sets the board dimensions.
	     * @param x is the horizontal length in integer units.
	     * @param y is the vertical length in integer units.
	     */
	   public Board(int x, int y)
	        {
	            this.xLength = x;
	            this.yLength = y;
	        }
	    
	    /**
	     * This is a method for creating and setting a new board/array to the attribute board.
	     */
	    public void makeBoard()
	        {
	            this.board = new char[this.yLength][this.xLength];
	        }
	    
	    /**
	     * This is a getter for retrieving the board size in a Point object coordinate.
	     * @return returns the board size in a Point object.
	     */
	    public Point getBoardSize()
	        {
	            Point size = new Point(this.xLength,this.yLength);
	            return size;
	        }
	    
	    /**
	     * This is a getter for retrieving the board/array and everything in it.
	     * @return returns the board/array
	     */
	    public char[][] getBoard()
	    	{
	    		return this.board;
	    	}
	    
	    /**
	     * This is a setter for setting a predefined 2D array of characters as the new board.
	     * @param newBoard Should be a 2D array of characters.
	     */
	    public void setBoard(char[][] newBoard) 
	    	{
	    		this.board = newBoard;
	    	}
	    
	    /**
	     * This is a setter for the boards dimensions in x and y lengths.
	     * @param x is the horizontal length.
	     * @param y is the vertical length.
	     */
	    public void setBoardSize(int x, int y) 
	    	{
	    		this.xLength = x;
	    		this.yLength = y;
	    	}
	    
	    public void basicRowBlocks(int row) 
	    	{
	    		for(int i = 0; i < this.yLength; i++) 
	    			{
	    				this.board[i][row] = 'B';
    			}
	    	}
	}