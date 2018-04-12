package Classes;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Hard_Block extends Block {
		
	/*
	private int width;
	private int height;
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
	
	/*
	public int getWidth()
	{
		return this.width;
	}
	
	public int getHeight()
	{
		return this.height;
	}
	*/
	
}
