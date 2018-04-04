package Classes;

import javafx.scene.shape.Rectangle;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
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
	
	public boolean getIntersectsRectangleTopAndBottom(Pane r, Circle b)
	{
		if(b.getBoundsInParent().intersects(rect.getX() + 2.0, rect.getY(), rect.getWidth() - 4.0, 1.0) || 
				b.getBoundsInParent().intersects(rect.getX() + 2.0, rect.getY() + rect.getHeight(), rect.getWidth() - 4.0, 1.0))
			{ //The 2.0 and 4.0 are offsets to avoid clipping in false collisions
			 return true;
			}
		return false;
		//return b.getBoundsInParent().intersects((this.rect).getBoundsInParent());
	}
	
	public boolean getIntersectsRectangleSides(Pane r, Circle b)
	{
		if(b.getBoundsInParent().intersects(rect.getX(), rect.getY() + 2.0, 1.0, rect.getHeight() - 4.0) || 
				b.getBoundsInParent().intersects(rect.getX() + rect.getWidth(), rect.getY() + 2.0, 1.0, rect.getHeight() - 4.0))
		{
			return true;
		}
		return false;
		//return b.getBoundsInParent().intersects((this.rect).getBoundsInParent());
	}
	
	public void setColor(Paint color) {
		rect.setFill(color);
		rect.setStroke(color);
	}
}
