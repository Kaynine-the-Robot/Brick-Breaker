package Tests;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;

import Classes.Player;

public class Player_Test {

	@Test
	public void test_constructor() {
		
		Player p = new Player(3);
		assertEquals("Expected (3, 4)", new Point(3, 4), p.getPosition());
	}
	
	@Test
	public void test_score() {
		
		Player p = new Player(3);
		assertEquals("Expected 0", 0, p.getScore());
		p.increaseScore();
		p.increaseScore();
		assertEquals("Expected 2", 2, p.getScore());
	}
}
