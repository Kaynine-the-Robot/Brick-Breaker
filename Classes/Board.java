package Classes;
import java.awt.Point;
import java.util.concurrent.ThreadLocalRandom;

import Classes.*;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import Classes.Normal_Block;
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
	    private Normal_Block[][] blockArray;
	    private int blockArrayRow;
	    private int blockArrayCol;
	    
	    /**
	     * This is a default constructor for board
	     */
	    public Board()
	    {
	    	this.blockArrayRow = 10;
	    	this.blockArrayCol = 10;
	    	
	    }
	    
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
	    * This is a constructor for board that creates basic square block shape.
	    * @param levelName
	    * @param rowNumber
	    * @param colNumber
	    */
	   public Board(String levelName,int rowNumber, int colNumber) {
		   
		   if (levelName == "Basic") 
		   {
			   this.blockArrayRow = rowNumber;
			   this.blockArrayCol = colNumber;
			   
		   }
		   
		   else 
		   {
			   System.out.println("Logical Error - please use constructor for board properly." );
		   }
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
			   blockArray = new Normal_Block[blockArrayRow][blockArrayCol];
				
		 		for(int i = 0; i < blockArray.length; i++)
		 		{
		 			for(int j = 0; j < blockArray[0].length; j++)
		 			{
		 				blockArray[i][j] = new Normal_Block(10 + (40 * i), 70 + (20 * j), 'B', 2, 30, 10);
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
		public Normal_Block getBlockArrayAtIndex(int x,int y) 
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
				this.getBlockArrayAtIndex(x,y).removeRectangleFromRoot(root);
		}
		
		/**
		 * This is a getter for block array
		 * @return blockArray - which is the array of blocks
		 */
		public Block[][] getBlockArray(){
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
				for(int j = 0; j < blockArray[i].length; j++)
				{
					blockArray[i][j].addRectangleToRoot(root);;
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
        			if((ballMovement.getHitBrick() == false && this.getBlockArrayAtIndex(i,j).getContainsRectangle(root) && this.getBlockArrayAtIndex(i,j).getIntersectsRectangle(root, ball))) 
        			{
        				this.getBlockArrayAtIndex(i,j).removeRectangleFromRoot(root);;
        				ballMovement.vertCollision();
        				ballMovement.setHitBrick(true);
        			}
        		}
        	}       
			
		}
		
		/**
		 * This method generates random array of blocks
		 * @param root
		 */
		public void generateRandomLevel(Pane root) {
			int maxBlockThatCanBeRemoved = (blockArrayRow * blockArrayCol); //Max num of blocks that can be removed is all blocks
			int randomNumBlocksToRemove = ThreadLocalRandom.current().nextInt(0, maxBlockThatCanBeRemoved +1);
			
			for (int i=0; i< randomNumBlocksToRemove ;i++) {
				
				int randomRow = ThreadLocalRandom.current().nextInt(0, blockArrayRow );
				int randomCol = ThreadLocalRandom.current().nextInt(0, blockArrayCol );
				this.removeBlockAtIndex(root, randomRow,randomCol);
			
			}		
		}
		

		
		  public void addCustomLevel(Pane root,String levelName) {
			  
			  	this.blockArrayRow = 10;
			  	this.blockArrayRow = 10; //Ensure the standard size
			  	
				if (levelName == "smile")
				{
				}
			
				if (levelName == "heart") 
				{
					this.removeBlockAtIndex(root,1,0);
					this.removeBlockAtIndex(root,8,0);
					this.removeBlockAtIndex(root,1,6);
					this.removeBlockAtIndex(root,8,6);
					for (int i=0;i<10;i++) 
					{
						this.removeBlockAtIndex(root,0,i);
						this.removeBlockAtIndex(root,9,i);
					}
					for (int i=1;i<4;i++)
					{
						this.removeBlockAtIndex(root,i,9);
						this.removeBlockAtIndex(root,i,8);
						this.removeBlockAtIndex(root,i+5,9);
						this.removeBlockAtIndex(root,i+5,8);
					}
					for (int i=3;i<7;i++) 
					{
						this.removeBlockAtIndex(root,i,0);
					}	
						
					for (int i=1;i<3;i++)
					{
						this.removeBlockAtIndex(root,i,7);
						this.removeBlockAtIndex(root,i+6,7);
						this.removeBlockAtIndex(root,i+3,1);		
					}	
					
				}
			
				if (levelName == "2 boxes")
				{
					this.removeBlockAtIndex(root, 0, 0);
				}
				
		  }
	 
				
	}






