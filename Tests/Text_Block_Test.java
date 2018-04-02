package Tests;


import static org.junit.Assert.*;

import org.junit.Test;

import Classes.Block;

public class Text_Block_Test {

	@Test
	public void test_length() {
		
		Block b = new Block();
		b.setBlockLength(4);
		assertEquals("Expected 4", 4, b.getBlockLength());
		b.setBlockLength(6);
		assertEquals("Expected 4", 4, b.getBlockLength());
		b.setBlockLength(-1);
		assertEquals("Expected 4", 4, b.getBlockLength());
		
	}

}
