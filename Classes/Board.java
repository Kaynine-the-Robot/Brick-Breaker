package Classes;
import java.awt.Point;
import Classes.*;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
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
	    private Rectangle[][] blockArray;
	    private int blockArrayRow;
	    private int blockArrayCol;
	    
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
	            this.board = new char[this.xLength][this.yLength];
	        }
	   
	   /**
	    * This is a constructor for board that asks for row and col number(For rectangle-like block structure)
	    * @param levelName
	    * @param rowNumber
	    * @param colNumber
	    */
	   public Board(String levelName,int rowNumber, int colNumber) {
		   this.blockArrayRow = rowNumber;
		   this.blockArrayCol = colNumber;
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
	    
	    /**
	     * This is for making multiple rows of blocks
	     * @param blocks is the 2D array of blocks
	     */
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
	    public void makePlayer(Player player)
	    	{
	    		this.playerPos = new Point((int) player.getPosition().getX(), (int) player.getPosition().getY());
	    		this.board[(int) this.playerPos.getX()+1][(int) this.playerPos.getY()] = 'P';
	    		this.board[(int) this.playerPos.getX()][(int) this.playerPos.getY()] = 'P';
	    	}
	    
	    /**
	     * This is a method for setting the ball's positions on the board.
	     * The ball's a 1X1 block so it's coordinate is exact.
	     * @param startX is the specified starting x coordinate of the ball.
	     * @param startY is the specified starting y coordinate of the ball.
	     */
	    public void makeBall(Ball ball) 
	    	{
	    		this.ballPos = new Point( (int) ball.getPosition().getX(), (int) ball.getPosition().getY());
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
				this.playerPos.setLocation((int) newPlayerPos.getX(), (int) newPlayerPos.getY());//Update bar
				this.board[(int) newPlayerPos.getX()][(int) newPlayerPos.getY()] = 'P'; //Place bar
				this.board[(int) newPlayerPos.getX()+1][(int) newPlayerPos.getY()] = 'P'; //Place bar
				
				
				this.checkBrickCollision();
				
			}
		
		/**
		 * This is a simple function for checking if the ball hits one of the blocks at the top and removing it's part beside it
		 */
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
		
		/**
		   * This method generates array of Rectangle objects (Blocks) to store it in Array List
		   */
		   public void generateBlockArray() 
		   {
			   blockArray = new Rectangle[blockArrayRow][blockArrayCol];
				
		 		for(int i = 0; i < blockArray.length; i++)
		 		{
		 			for(int j = 0; j < blockArray[0].length; j++)
		 			{
		 				blockArray[i][j] = new Rectangle(10 + (40 * i), 70 + (20 * j), 30, 10);
		 			}
		 		}
			      
		   }

		/**
		 * This is a getter for number of rows in the block array
		 * @return blockArrayRow - which is a number of rows in block array
		 */
		public int getBlockArrayRow() 
		{
			return this.blockArrayRow;
		}
		
		/**
		  * This is a getter for number of columns in the block array
		 * @return blockArrayCol - which is a number of columns in block array
		 */
		public int getBlockArrayCol() 
		{
			return this.blockArrayCol;
		}
		
		/**
		 * This is a method for getting a specific block by index
		 * @param x
		 * @param y
		 * @return blockArray - At the index requested
		 */
		public Rectangle getBlockArrayAtIndex(int x,int y) 
		{
			return this.blockArray[x][y];
		}
		
		/**
		 * This method removes block by specific index
		 * @param root
		 * @param x
		 * @param y
		 */
		public void removeBlockAtIndex(Pane root,int x, int y) 
		{
				root.getChildren().remove((this.getBlockArrayAtIndex(x,y)));
		}
		
		/**
		 * This is a getter for block array
		 * @return blockArray - which is the array of blocks
		 */
		public Rectangle[][] getBlockArray(){
			return this.blockArray;
		}
		
		/**
		 * This is a method for adding block array to the root
		 * @param root
		 */
		public void addBlockArray(Pane root) {
			//Iterating through the array of bricks and adding them to the graphics
			for(int i = 0; i < blockArray.length; i++)
			{
				for(int j = 0; j < blockArray[0].length; j++)
				{
					root.getChildren().add(blockArray[i][j]);
				}	
			}	
			
		}

		/**
		 * This is a method for checking ball and brick collision
		 * @param root
		 * @param ball
		 * @param ballMovement
		 */
		public void checkBallBrickCollision(Pane root, Circle ball, Ball ballMovement) {
			
			for(int i = 0; i < this.getBlockArrayRow(); i++) 
        	{
        		for(int j = 0; j < this.getBlockArrayCol(); j++)
        		{
        			if((ballMovement.getHitBrick() == false && root.getChildren().contains(this.getBlockArrayAtIndex(i,j))) && (ball.getBoundsInParent().intersects((this.getBlockArrayAtIndex(i,j)).getBoundsInParent()))) 
        			{
        				root.getChildren().remove((this.getBlockArrayAtIndex(i,j)));
        				ballMovement.vertCollision();
        				ballMovement.setHitBrick(true);
        			}
        		}
        	}       
			
		}
		
	}






