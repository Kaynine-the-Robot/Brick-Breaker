package Classes;
import java.awt.Point;
import Classes.Block;
//import java.util.Formatter;

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
	    private Block[][] objBoard;
	    private Point ballPos;
	    private Point playerPos;
	    
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
	            this.board = new char[this.xLength][this.yLength];
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
	    
	    /**
	     * This method is for drawing a row of Blocks. (Later to become drawing multiple rows or patterns)
	     * @param row is the constant row in the 2D array to set Blocks on.
	     */
	    public void basicRowBlocks(int row) 
	    	{
	    		for(int i = 0; i < this.xLength; i++) 
	    			{
	    				this.board[i][row] = 'B';
    			}
	    	}
	    
	    public void advancedRowBlocks(Block[][] blocks) 
	    	{
	    		this.objBoard = blocks;
	    		for(int i = 0; i < blocks.length; i++) 
    			{
    				for(int j = 0; j < blocks[i].length; j++) 
    					{
    						System.out.println(blocks[i][j]);
    					}
    			}
	    		for(int i = 0; i < blocks.length; i++) 
	    			{
	    				for(int j = 0; j < blocks[i].length; j++) 
	    					{
	    						if(blocks[i][j] != null)
	    							{
	    								System.out.println(this.board.length);
	    								System.out.println(this.board[i].length);
	    								this.board[i][j] = (char) blocks[i][j].getSymbol();
	    								this.board[i+1][j] = (char) blocks[i][j].getSymbol();
	    							}
	    						}
	    					}
	    			}
	    
	    
	    /**
	     * This is a method for setting the player's start position on the board.
	     * The player's bat/bar is multiple spaces long and the "coordinate" is the leftmost block of the player.
	     * @param startX is the specified starting x coordinate of the player.
	     * @param startY is the specified starting y coordinate of the player.
	     */
	    public void makePlayer(int startX, int startY)
	    	{
	    		this.playerPos = new Point(startX, startY);
	    		this.board[(int) this.playerPos.getX()+1][(int) this.playerPos.getY()] = 'P';
	    		this.board[(int) this.playerPos.getX()][(int) this.playerPos.getY()] = 'P';
	    	}
	    
	    /**
	     * This is a method for setting the ball's positions on the board.
	     * The ball's a 1X1 block so it's coordinate is exact.
	     * @param startX is the specified starting x coordinate of the ball.
	     * @param startY is the specified starting y coordinate of the ball.
	     */
	    public void makeBall(int startX, int startY) 
	    	{
	    		this.ballPos = new Point(startX, startY);
	    		this.board[(int) this.ballPos.getX()][(int) this.ballPos.getY()] = 'O';
	    	}
	    
	    /**
	     * This method is for updating the board positions of the ball and player when moving.
	     * @param newBallPos is the new position of the ball that is going to replace the old position.
	     * @param newPlayerPos is the new position of the player that is going to replace the old position.
	     */
		public void updateBoard(Point newBallPos, Point newPlayerPos) 
			{
				this.board[(int) this.ballPos.getX()][(int) this.ballPos.getY()] = ' '; //ball remove
				this.ballPos.setLocation((int) newBallPos.getX(), (int) newBallPos.getY()); //ball update pos
				this.board[(int) newBallPos.getX()][(int) newBallPos.getY()] = 'O';//place ball
				
				this.board[(int) this.playerPos.getX()][(int) this.playerPos.getY()] =' '; //Remove bar
				this.board[(int) this.playerPos.getX()+1][(int) this.playerPos.getY()] = ' ';//Remove bar
				this.board[(int) newPlayerPos.getX()][(int) newPlayerPos.getY()] = 'P'; //Place bar
				this.board[(int) newPlayerPos.getX()+1][(int) newPlayerPos.getY()] = 'P'; //Place bar
				this.playerPos.setLocation((int) newPlayerPos.getX(), (int) newPlayerPos.getY());//Update bar
				
				this.checkBrickCollision();
				
			}
		
		public void checkBrickCollision() {
			
			if((int) this.ballPos.getY() == 0) 
				{
					if( (((int) this.ballPos.getX()) % 2) == 0) 
						{
							this.board[(int) this.ballPos.getX() + 1][(int) this.ballPos.getY()] = ' ';
						}
					else 
						{
						this.board[(int) this.ballPos.getX() - 1][(int) this.ballPos.getY()] = ' ';
						}
				}
			
		}
	}






