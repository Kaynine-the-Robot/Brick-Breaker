package Classes;

/**
 * This is the hard block class which contains all the method and attributes of a hard block
 * It is a subslass of the Block class
 * @author
 *
 */
public class Hard_Block extends Block {
	
	/**
	 * A constructor for the Hard_Block class
	 * @param x The x coordinate of the block
	 * @param y The y coordinate of the block
	 * @param col The character representation of the colour of the block
	 * @param len The length of the block
	 * @param nWidth The width of the block
	 * @param nHeight The height of the block
	 * @param nHealth The health of the block
	 */
	public Hard_Block(double x, double y, char col, int len, int nWidth, int nHeight, int nHealth)
	{		
		this.setColor(col);
		this.setBlockLength(len);
		this.setPosition(x,y);
		super.setSymbol('H');
		super.setWidth(nWidth);
		super.setHeight(nHeight);
		this.setHealth(nHealth);
	}
}
