package Tests;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;

import Classes.CollisionObjects;
import Classes.Player;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Interface_Test {
	
	@Test
	public void test_bar_movement()
	{
		Player p = new Player(500);
		CollisionObjects cO = new CollisionObjects(new ImageView(), new ImageView(), 800, 1000);
		
		p.setRFlag(true);
		//cO.checkBarImageSwitch(p);
    	cO.movePlayerInWindow(p);
    	assertEquals("Expected 501", new Point(501, 950), p.getPosition());
    	
	}

}
