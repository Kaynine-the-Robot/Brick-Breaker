package Classes;
import java.awt.Point;
import Classes.Board;
import javafx.scene.layout.Pane;
/**
 * This is the block class which will have methods and attributes to control the blocks behavior and appearance
 * @author
 *
 */
public class Block extends AbstractObjects
	{
		Boolean visibility = true;
		private char color = 'R';
		private int blockLength = 2;
		private int health;
		private int width;// = 30;
		private int height;// = 10;
		
		/**
		 * A default constructor for Block
		 */
		public Block() 
			{
			
			}
		
		/**
		 * A Constructor for Block setting all its attributes, this is for the Text version
		 * @param x is an int used for the Point object's x coordinate
		 * @param y is an int used for the Point object's y coordinate
		 * @param col is a character representing what color the block is
		 * @param len is an in representing the length of the blocks
		 */
		public Block(int x, int y, char col, int len) 
			{
				this.color = col;
				this.blockLength = len;
				this.setPosition(x,y);
				//this.setSymbol('B');
				this.health = 1;
			}
		
		public Block(int x, int y, char col, int len, int nWidth, int nHeight) 
		{
			this.color = col;
			this.blockLength = len;
			this.setPosition(x,y);
			//this.setSymbol('B');
			this.width = nWidth;
			this.height = nHeight;
			this.health = 1;
		}

		/**
		 * A getter for the color attribute of a block
		 * @return the char attribute color representing the color
		 */
		public char getColor()
		{
			return this.color;
		}
		
		/**
		 * A getter for the block length of a block for drawing
		 * @return the integer representing block length
		 */
		public int getBlockLength()
		{
			return this.blockLength;
		}
		
		/**
		 * A setter for changing the char representing the color of a block
		 * Checks if the char is one of the three approved color characters
		 * @param nCol is the new char letter that the color will be changed to
		 */
		public void setColor(char nCol)
		{
			if(nCol == 'R' || nCol == 'B' || nCol == 'G')
			{
				this.color = nCol;
			}
		} 
		
		/**
		 * A setter for changing the block length
		 * Checks if the length is positive and from 1-4 units
		 * @param nLength is the new integer length of block
		 */
		public void setBlockLength(int nLength)
		{
			if(nLength > 0 && nLength < 5)
			{
				this.blockLength = nLength;
			}
		}
		 
		/**
		 * This is a method for setting visibility of the block for text gui
		 * @param vis - true or false
		 */
		public void setVisibility(boolean vis) {
			this.visibility = vis;
		}
		
		/**
		 * This is a method for getting visibility of the block for text gui
		 * @return visibility - boolean value
		 */
		
		public boolean getVisibility() {
			return this.visibility;
		}
		
		public boolean decreaseHealth()
		{
			if(this.health > 1)
			{
				this.health--;
				return false;
			}
			else 
			{
				return true;
			}
		}
		
		public int getHealth()
		{
			return this.health;
		}
		
		public void setHealth(int nHealth)
		{
			if(nHealth > 0 && nHealth < 5)
			{
				this.health = nHealth;
			}
		}
		
		public int getWidth()
		{
			return this.width;
		}
		
		public int getHeight()
		{
			return this.height;
		}
		
		public void setWidth(int nWidth)
		{
			if(nWidth > 0 && nWidth < 100)
			{
				this.width = nWidth;
			}
		}
		
		public void setHeight(int nHeight)
		{
			if(nHeight > 0 && nHeight < 100)
			{
				this.height = nHeight;
			}
		}
		
	}