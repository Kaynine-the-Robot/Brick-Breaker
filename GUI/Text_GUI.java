package GUI;

import java.awt.Point;
import Classes.Board;
import Classes.Player;

public class Text_GUI
	{
		private boolean visibility;
		
		/**
		 * This method is a constructor for Text_GUI which sets the visibility of the board (basically if it's drawn or not).
		 * @param bool is a passed true or false for if the board will show. True if it will.
		 */
		public Text_GUI(boolean bool)
		{
			this.visibility = bool;
		}
		
		/**
		 * This is the drawing method for the text-based version of the game.
		 * Draws the letters representing the objects surrounded by lines representing the spaces/blocks/possible spaces of the array
		 * @param board is the object of the board which contains the array and size of the current board.
		 */
		public void printBoard(Board board, Player player)
		{
			if(this.visibility == true)
			{
				Point size = board.getBoardSize();
				int xLength = (int) size.getX();
				int yLength = (int) size.getY();
				for(int l = 0; l < (xLength*4); l++) 
				{
					System.out.print("-");
				}
				System.out.println("");
				for(int j = 0; j < yLength; j++) 
				{
					for(int i = 0; i < xLength; i++) 
					{
						if(board.getBoard()[i][j] == 0)
						{
							System.out.print("|   ");
						}
						else
						{
							System.out.print(String.format("| %c ",board.getBoard()[i][j]));
						}
					}
					System.out.println("|");
					for(int a = 0; a < xLength; a++) 
					{
						System.out.print("----");
					}
					System.out.println("");
				}
				System.out.println("Score: " + player.getScore());

			}
		}
	}