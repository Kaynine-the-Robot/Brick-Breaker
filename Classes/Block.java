package Classes;
import java.awt.Point;
import Classes.Board;
/**
 * This is the block class which will have methods and attributes to control the blocks behavior and appearance
 * @author
 *
 */
public class Block 
	{
		private Point coordinates = new Point(0,0);
		private char color = 'R';
		private int blockLength = 2;
		private char symbol = 'B';
		
		/**
		 * A default constructor for Block
		 */
		public Block() 
			{
			
			}
		
		/**
		 * A Constructor for Block setting all its attributes
		 * @param x is an int used for the Point object's x coordinate
		 * @param y is an int used for the Point object's y coordinate
		 * @param col is a character representing what color the block is
		 * @param len is an in representing the length of the blocks
		 */
		public Block(int x, int y, char col, int len) 
			{
				this.color = col;
				this.blockLength = len;
				this.coordinates = new Point(x,y);
			}
		
		/**
		 * A getter for the symbol representing this is a block
		 * @return a character B for Block
		 */
		public char getSymbol() 
			{
				return this.symbol;
			}
		
		/**
		 * A method for removing the parts of the blocks next to each other
		 * @param board is the Board object used to get the array and change the B char to empty
		 */
		public void removeBlock(Board board) 
			{
				if(this.blockLength == 2) 
					{
						//Makes the Board block and the one next to it disappear
						board.getBoard()[(int) this.coordinates.getX()][(int) this.coordinates.getY()] = ' ';
						board.getBoard()[(int) this.coordinates.getX()+1][(int) this.coordinates.getY()] = ' ';
					}
			}
		
		/**
		 * A method for making custom sized boards
		 * @return is a 2D array of Block objects
		 */
		public Block[][] arrayBlocks()
			{
				//Making a custom board, could be more generalized later
				Block[][] letterBlocks = new Block[6][5];
				for(int i = 0; i < 6; i++) 
					{
						for(int j = 0; j < 5; j++) 
							{
								if(j==0) 
									{
										letterBlocks[i][j] = new Block(i,j,'R',2);  //The array of Block objects
										i += 1; //Incrementing one more for 2 block space
									}
							}
					}
				return letterBlocks;
			}
	}