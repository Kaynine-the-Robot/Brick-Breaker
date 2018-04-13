package Tests;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;

import Classes.Ball;
import Classes.Board;
import Classes.Player;
import Classes.Text_Block;

public class Text_Board_Test {
	
	@Test
	public void test_text_board()
	{
		Board bo = new Board(10, 20);
		Player p = new Player(4);
		Ball ba = new Ball(4, 18);
		Text_Block bs = new Text_Block();
		
		bo.advancedRowBlocks(bs.arrayBlocks(10, 15));
		assertEquals("Expected 10", 10, bo.getBoard().length);
		assertEquals("Expected 20", 20, bo.getBoard()[0].length);
		
		bo.makePlayer(p);
		bo.makeBall(ba);
		assertEquals("Expected P", 'P', bo.getBoard()[4][19]);
		assertEquals("Expected O", 'O', bo.getBoard()[4][18]);
		
		bo.updateBoard(new Point(5, 15), new Point(1, 19), ba);
		assertEquals("Expected P", 'P', bo.getBoard()[1][19]);
		assertEquals("Expected O", 'O', bo.getBoard()[5][15]);
		
		assertEquals("Expected A", 'A', bo.getBoard()[1][1]);
		bo.removeBlockAtIndexText(1, 1);
		assertEquals("Expected null", ' ', bo.getBoard()[1][1]);
	}

}
