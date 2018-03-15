package Tests;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;

import Classes.Ball;

public class Ball_Test {
	
	@Test
	public void test_constructor(){
		
		Ball b = new Ball(3, 4);
		assertEquals("Expected position (3, 4)", new Point(3, 4), b.getPosition());
		
	}
	
	@Test
	public void test_movement(){
		
		Ball b = new Ball(1, 1);
		b.vertCollision();
		b.updatePos(b.getHorzMovement(), b.getVertMovement());
		b.updatePos(b.getHorzMovement(), b.getVertMovement());
		assertEquals("Expected position (3, 3)", new Point(3, 3), b.getPosition());
		b.horzCollision();
		b.updatePos(b.getHorzMovement(), b.getVertMovement());
		b.updatePos(b.getHorzMovement(), b.getVertMovement());
		assertEquals("Expected position (1, 5)", new Point(1, 5), b.getPosition());
		
	}
	
	@Test
	public void test_check_location() {
		
		Ball b = new Ball(1, 1);
		b.updatePos(b.getHorzMovement(), b.getVertMovement());
		assertTrue("Expected true", b.checkLocation());
		b.updatePos(b.getHorzMovement(), b.getVertMovement());
		b.updatePos(b.getHorzMovement(), b.getVertMovement());
		assertFalse("Expected false", b.checkLocation());
	}

}
