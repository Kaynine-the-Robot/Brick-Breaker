package Classes;

import javafx.scene.shape.Rectangle;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class Normal_Block extends Block {
	
	private int width;
	private int height;
	
	public Normal_Block(int x, int y, char col, int len, int nWidth, int nHeight)
	{
		this.setColor(col);
		this.setBlockLength(len);
		this.setPosition(x,y);
		this.setSymbol('B');
		this.width = nWidth;
		this.height = nHeight;
	}
	
	public int getWidth()
	{
		return this.width;
	}
	
	public int getHeight()
	{
		return this.height;
	}

}
