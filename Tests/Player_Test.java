package Tests;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;

import Classes.Player;

public class Player_Test {

	@Test
	public void test_constructor() {
		
		Player p = new Player(3);
		assertEquals("Expected (3, 19)", new Point(3, 19), p.getPosition());
	}
	
	@Test
	public void test_score() {
		
		Player p = new Player(3);
		assertEquals("Expected 0", 0, p.getScore());
		p.increaseScore();
		p.increaseScore();
		assertEquals("Expected 2", 2, p.getScore());
	}
	
	@Test
	public void test_bar_movement()
	{
		
		Player p = new Player(3);
		p.moveBar('L');
		assertEquals("Expected (2, 19)", new Point(2, 19), p.getPosition());
		p.moveBar('L');
		p.moveBar('L');
		p.moveBar('L');
		assertEquals("Expected (0, 19)", new Point(0, 19), p.getPosition());
		p.moveBar('R');
		p.moveBar('W');
		assertEquals("Expected (1, 19)", new Point(1, 19), p.getPosition());
	}
}
