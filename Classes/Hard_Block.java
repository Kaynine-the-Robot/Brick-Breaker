package Classes;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Hard_Block extends Block {
	
	private int health = 2;
	private Rectangle rect;
	
	public Hard_Block(int x, int y, char col, int len, int width, int height)
	{
		this.setColor(col);
		this.setBlockLength(len);
		this.setPosition(x,y);
		this.setSymbol('B');
		this.rect = new Rectangle(x, y, width, height);
	}
	
	public Rectangle getRectangle()
	{
		return new Rectangle(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
	}
	
	public int getHealth()
	{
		return this.health;
	}
	
	public void setRectangle(Rectangle nRect)
	{
		this.rect = new Rectangle(nRect.getX(), nRect.getY(), nRect.getWidth(), nRect.getHeight());
	}
	
	public void setHealth(int nHlth)
	{
		if(nHlth > 0 && nHlth < 5)
		{
			this.health = nHlth;
		}
	}
	
	public void decreaseHealth(Pane r)
	{
		if(this.health > 1)
		{
			this.health--;
		}
		else 
		{
			this.removeRectangleFromRoot(r);
		}
	}
	
	public boolean getContainsRectangle(Pane r)
	{
		return r.getChildren().contains(this.rect);
	}
	
	public void addRectangleToRoot(Pane r)
	{
		r.getChildren().add(this.rect);
	}
	
	public void removeRectangleFromRoot(Pane r)
	{
		r.getChildren().remove(this.rect);
	}
	
	public boolean getIntersectsRectangle(Pane r, Circle b)
	{
		return b.getBoundsInParent().intersects((this.rect).getBoundsInParent());
	}
	
}
