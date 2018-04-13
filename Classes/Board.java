package Classes;
import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.util.converter.IntegerStringConverter;
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
	    private Block[][] blockArray; //Might make the same as objBoard
	    private int blockArrayRow = 10;
	    private int blockArrayCol = 15;//Means 15 Rows
	    
	    /**
	     * This is a default constructor for board
	     */
	    public Board()
	    {
	    	this.blockArrayRow = 10;
	    	this.blockArrayCol = 15; //Means 15 Rows
	    	
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
	    * @param levelName The name of the level
	    * @param rowNumber The amount of rows on the board
	    * @param colNumber The amount of columns on the board
	    */
	   public Board(String levelName,int rowNumber, int colNumber)
	   {
		   
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
	     * This is a method for returning how many blocks are left in text gui
	     * @return blockNum - int
	     */
	    public int totalBlocks() 
	    {
	    	return objBoard.length;
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
	    			if(blocks[i][j] != null)
	    			{
	    				this.board[i][j] = (char) blocks[i][j].getSymbol();

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
		public void updateBoard(Point newBallPos, Point newPlayerPos, Ball ball) 
		{
			this.board[(int) this.ballPos.getX()][(int) this.ballPos.getY()] = ' '; //ball remove
			this.ballPos.setLocation((int) newBallPos.getX(), (int) newBallPos.getY()); //ball update pos
			this.board[(int) newBallPos.getX()][(int) newBallPos.getY()] = 'O';//place ball

			this.board[(int) this.playerPos.getX()][(int) this.playerPos.getY()] =' '; //Remove bar
			this.board[(int) this.playerPos.getX()+1][(int) this.playerPos.getY()] = ' ';//Remove bar
			this.playerPos.setLocation((int) newPlayerPos.getX(), (int) newPlayerPos.getY());//Update bar
			this.board[(int) newPlayerPos.getX()][(int) newPlayerPos.getY()] = 'P'; //Place bar
			this.board[(int) newPlayerPos.getX()+1][(int) newPlayerPos.getY()] = 'P'; //Place bar
		}
		
		/**
		 * This is a function that checks the board brick collision IN TEXT GUI
		 * @param ball The ball object to check for collision
		 * @param player The player object to check for collision
		 */
		public void checkBrickCollision(Ball ball,Player player)
		{
			for (int i = 0 ; i< 10; i++) 
			{
				for (int j = 0 ; j< 15 ; j++) 
				{
					if ((ball.getPosition().getX() == objBoard[i][j].getPosition().getX()
							&& ball.getPosition().getY() == objBoard[i][j].getPosition().getY()) 
							&& objBoard[i][j].getVisibility() == true)
					{
						
						objBoard[i][j].setVisibility(false);
						
						ball.vertCollision();
						
						if (ball.getPosition().getX() != 0 && ball.getPosition().getX() != 9) 
						{
							ball.horzCollision();
						}
						
						player.increaseScore();
					
					
					}
					
					
				}
			}
			
		}
						
		
		/**
		 * This method generates array of Rectangle objects (Blocks) to store it in Array List for javafx gui
		 * @param cO The collision object for generation
		 */
		public void generateBlockArray(CollisionObjects cO)
		{
			blockArray = new Block[blockArrayRow][blockArrayCol];
			Image[] normalBlockSprites = new Image[6];
			normalBlockSprites[0] = new Image("file:Assets/NormalBlock_Red_Left.png"); normalBlockSprites[1] = new Image("file:Assets/NormalBlock_Red.png"); normalBlockSprites[2] = new Image("file:Assets/NormalBlock_Red_Right.png");
			normalBlockSprites[3] = new Image("file:Assets/NormalBlock_Green_Left.png"); normalBlockSprites[4] = new Image("file:Assets/NormalBlock_Green.png"); normalBlockSprites[5] = new Image("file:Assets/NormalBlock_Green_Right.png");
			Image[] hardBlockSprites = new Image[2];
			hardBlockSprites[0] = new Image("file:Assets/HardBlock_Whole.png"); hardBlockSprites[1] = new Image("file:Assets/HardBlock_Broken.png");
			for(int i = 0; i < blockArray.length; i++)
			{
				for(int j = 0; j < blockArray[i].length; j++)
				{
					int rand = ThreadLocalRandom.current().nextInt(0, 4);
					if(rand == 3)
					{
						blockArray[i][j] = new Hard_Block(cO.getBackWidth()/30 + (80 * i), cO.getBackHeight()/10 + (30 * j), 'B', 2, 60, 20, 2);
						blockArray[i][j].setBrickSprites(hardBlockSprites);
					}
					else
					{
						blockArray[i][j] = new Normal_Block(cO.getBackWidth()/30 + (80 * i), cO.getBackHeight()/10 + (30 * j), 'B', 2, 60, 20);
						blockArray[i][j].setBrickSprites(normalBlockSprites);
					}
				}
			}
			cO.setBrickHitBoxes(blockArray);

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
		public Block getBlockArrayAtIndex(int x,int y) 
		{
			return this.blockArray[x][y];
		}
		
		/**
		 * This method removes block by specific index in javafx gui
		 * @param root The root object to remove block from
		 * @param x The x index of the block to remove
		 * @param y The y index of the block to remove
		 */
		public void removeBlockAtIndex(Pane root,int x, int y, CollisionObjects cO) 
		{
			this.getBlockArrayAtIndex(x,y).setVisibility(false);
			cO.removeBlockFromRoot(root, x, y);
		}
		
		/**
		 * This method is for removing block at text gui by index
		 * @param x The x index of the block to remove
		 * @param y The y index of the block to remove
		 */
		
		public void removeBlockAtIndexText(int x, int y)
		{
			objBoard[x][y].setVisibility(false);
			board[x][y] = ' ';

		}
		
		/**
		 * This is a getter for block array
		 * @return blockArray - which is the array of blocks
		 */
		public Block[][] getBlockArray()
		{
			return this.blockArray;
		}
		
		/**
		 * This method generates random block level in javafx gui
		 * @param root The root object to place the nodes in
		 * @param cO The collision object to generate blocks to
		 * @param colors An array or colours for the blocks
		 */
		public void generateRandomLevel(Pane root, CollisionObjects cO, Paint[] colors) 
		{
			int maxBlockThatCanBeRemoved = (blockArrayRow * blockArrayCol); //Max num of blocks that can be removed is all blocks
			int randomNumBlocksToRemove = ThreadLocalRandom.current().nextInt(0, maxBlockThatCanBeRemoved +1);
			
			for (int i=0; i< randomNumBlocksToRemove ;i++) 
			{
				
				int randomRow = ThreadLocalRandom.current().nextInt(0, blockArrayRow );
				int randomCol = ThreadLocalRandom.current().nextInt(0, blockArrayCol );
				this.removeBlockAtIndex(root, randomRow,randomCol, cO);
			}
		}
		
		/**
		 * This method generates random block level in the TEXT gui
		 * 
		 */
		public void generateRandomLevel() {
			int maxBlockThatCanBeRemoved = (blockArrayRow * blockArrayCol); //Max num of blocks that can be removed is all blocks
			int randomNumBlocksToRemove = ThreadLocalRandom.current().nextInt(0, maxBlockThatCanBeRemoved +1);
			for (int i=0; i< randomNumBlocksToRemove ;i++) {
				int randomRow = ThreadLocalRandom.current().nextInt(0, blockArrayRow );
				int randomCol = ThreadLocalRandom.current().nextInt(0, blockArrayCol );
				this.removeBlockAtIndexText(randomRow, randomCol);
			}
		}


		/**
		 * This method is for generating custom levels for javafx gui
		 * @param root The root object for the nodes
		 * @param level The name of the level to generate
		 * @param colors An array of colours for the blocks
		 * @param cO The collision object for block generation
		 */
		public void addCustomLevel(Pane root,String level,Paint[] colors, CollisionObjects cO)
		{
			Scanner scan = null;

			try {
				scan = new Scanner(new File(level));
			}
			catch(FileNotFoundException e) {
				System.out.println(e);
				System.exit(0);
			}

			IntegerStringConverter isc = new IntegerStringConverter();

			String coordinateLine = scan.nextLine();
			String[] coordinates = coordinateLine.split(",");

			int blocksToRemove = coordinates.length;

			for (int i=0; i<blocksToRemove;i+=2) {
				int xvalue = isc.fromString(coordinates[i]);
				int yvalue = isc.fromString(coordinates[i+1]);
				this.removeBlockAtIndex(root,xvalue,yvalue, cO);

			}
		}

		/**
		 * This method is for generating custom levels for text gui
		 * @param root
		 * @param level
		 */
		public void addCustomLevelText(String level)
		{
			Scanner scan = null;
			this.blockArrayRow = 15;
			this.blockArrayRow = 10; //Ensure the standard size

			try {
				scan = new Scanner(new File(level));
			}
			catch(FileNotFoundException e) {
				System.out.println(e);
				System.exit(0);
			}

			String coordinateLine = scan.nextLine();
			String[] coordinates = coordinateLine.split(",");

			IntegerStringConverter isc = new IntegerStringConverter();

			int blocksToRemove = coordinates.length;

			for (int i=0; i<blocksToRemove;i+=2)
			{
				int xvalue = isc.fromString(coordinates[i]);
				int yvalue = isc.fromString(coordinates[i+1]);
				this.removeBlockAtIndexText(xvalue,yvalue);
			}
		}		
	}






