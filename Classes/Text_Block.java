package Classes;

public class Text_Block extends Block {
	
	/**
	 * A method for removing the parts of the blocks next to each other
	 * @param board is the Board object used to get the array and change the B char to empty
	 */
	public void removeBlock(Board board) 
		{
			if(this.getBlockLength() == 2) 
				{
					//Makes the Board block and the one next to it disappear
					board.getBoard()[(int) this.getPosition().getX()][(int) this.getPosition().getY()] = ' ';
					board.getBoard()[(int) this.getPosition().getX()+1][(int) this.getPosition().getY()] = ' ';
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