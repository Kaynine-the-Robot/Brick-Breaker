package Main;

import Classes.*;
import GUI.Text_GUI;

/**
 * This is the project for CPSC 233 Lecture 02 Tutorial section 06 for group 5
 * Group 5 is Amanda, Emir, Kaynen, Sebastien.
 * The project is a clone of Brickbreaker, the game where you (the player) move a
 * bat left and right to prevent a bouncing ball from going beyond it to the bottom 
 * of the screen. The ball will bounce around the game area bouncing off the walls and
 * blocks with the collison with the blocks casuing them to break and earn the player
 * score. The level will end when all the blocks are destroyed.
 */

/**
 * This is the main class of the game that will control and call the other classes and functions.
 *
 *@author
 */
public class Main 
	{
		/**
		 * The main method of the game, controls and calls other objects to make the game happen.
		 * @param args is an array of Strings.
		 */
	    public static void main(String[] args)
	        {
                Board board = new Board(6,5);
                Text_GUI draw = new Text_GUI(true);
                Player player = new Player(2);
                board.makeBoard();
                board.basicRowBlocks(0);
                board.makePlayer(player);
                board.makeBall(2,3);
                draw.printBoard(board);
                
                
                Ball ball = new Ball(2,3);
                Player player = new Player(2,4);
                
                while (true){
                    
                 player.moveBar(); //User asked to move bar, new bar position recorded
                 ball.updatePos();  //Ball moved, new ball position recorded
                    
                    
                 //Detects the ball - bar collision.
                 if (ball.getPosition().getX() == player.getPosition().getX() && ball.getPosition().getY()+1 == player.getPosition().getY()
                    || ball.getPosition().getX() == player.getPosition().getX()+1 && ball.getPosition().getY()+1 == player.getPosition().getY()){ 
                	 
                	 ball.vertCollision();
	 
                 }
                    
                    
                 ball.checkLocation(); //Detects the ball - wall collision.
                 
                 board.updateBoard(ball.getPosition(), player.getPosition()); //Board gets updated
                 draw.printBoard(board); //Board gets displayed
	        }	
	}