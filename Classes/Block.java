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
		private Point coordinates;
		private char color;
		private int blockLength;
		private char symbol = 'B';
		
		public Block() 
			{
				this.color = 'R';
				this.blockLength = 2;
				this.coordinates = new Point(0,0);
			}
		
		public Block(int x, int y, char col, int len) 
			{
				this.color = col;
				this.blockLength = len;
				this.coordinates = new Point(x,y);
			}
		
		public char getSymbol() 
			{
				return this.symbol;
			}
		

		
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