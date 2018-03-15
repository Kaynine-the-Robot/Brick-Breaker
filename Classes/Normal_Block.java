package Classes;

import javafx.scene.shape.Rectangle;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

public class Normal_Block extends Block {
	
	private Rectangle rect;
	
	public Normal_Block(int x, int y, char col, int len, int width, int height)
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
	
	public void setRectangle(Rectangle nRect)
	{
		this.rect = new Rectangle(nRect.getX(), nRect.getY(), nRect.getWidth(), nRect.getHeight());
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
