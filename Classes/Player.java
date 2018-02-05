package Classes;

import java.awt.Point;

/**
 * This is the player class which contains the methods and attributes of the player's bat.
 * @author
 *
 */
public class Player 
	{
		private int lives = 3;
		private Point position;
		private char symbol = 'P';
				
		public Player() 
			{
				this.position = new Point(2,4);
			}
		
		public Player(int startX) 
			{
				this.position = new Point(startX, 4);
			}
		
		public Point getPosition() 
			{
				return this.position;
			}
		
		public char getSymbol() 
			{
				return this.symbol;
			}
	}