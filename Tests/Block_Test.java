package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Classes.Block;

public class Block_Test{
	
	@Test
	public void block_test()
	{
		Block b = new Block(10, 10, 'B', 2, 10, 20);
		
		assertEquals("Expected 10", 10, b.getWidth());
		assertEquals("Expected 20", 20, b.getHeight());
		
		b.setWidth(-700);
		
		assertEquals("Expected 10", 10, b.getWidth());
		
		b.setHeight(-700);
		
		assertEquals("Expected 20", 20, b.getHeight());
		
		b.setColor('W');
		
		assertEquals("Expected B", 'B', b.getColor());
		
		b.decreaseHealth();
		
		assertEquals("Expected 1", 1, b.getHealth());
	}

}
