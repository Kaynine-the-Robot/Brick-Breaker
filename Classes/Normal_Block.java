package Classes;

/**
 * A class for the normal block, which contains all the attributes and methods of a normal block
 * This is used for the application GUI of the game, and is a subclass of the Block class
 * @author 
 */
public class Normal_Block extends Block {
	
	/**
	 * The constructor for the normal block class
	 * @param x The block's x coordinate
	 * @param y The block's y coordinate
	 * @param col The character representation of the block's colour
	 * @param len The length of the block
	 * @param nWidth The width of the block
	 * @param nHeight The height of the block
	 */
	public Normal_Block(double x, double y, char col, int len, int nWidth, int nHeight)
	{
		this.setColor(col);
		this.setBlockLength(len);
		this.setPosition(x,y);
		super.setSymbol('N');
		super.setWidth(nWidth);
		super.setHeight(nHeight);
		this.setHealth(1); //Normal Block can only have 1 health
	}
}
