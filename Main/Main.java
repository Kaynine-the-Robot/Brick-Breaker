package Main;
import Classes.Board;
import GUI.BreakoutApp;
import GUI.Text_GUI;
import javafx.application.Application;
import java.util.Scanner;

import Classes.Ball;
import Classes.Player;
import Classes.Text_Block;

/**
 * This is the project for CPSC 233 Lecture 02 Tutorial section 06 for group 5
 * Group 5 is Amanda, Kaynen, Sebastien.
 * The project is a clone of Brickbreaker, the game where you (the player) move a
 * bat left and right to prevent a bouncing ball from going beyond it to the bottom 
 * of the screen. The ball will bounce around the game area bouncing off the walls and
 * blocks with the collison with the blocks causuing them to break and earn the player
 * score. The level will end when all the blocks are destroyed.
 */

/**
 * This is the main class of the game that will control and call the other classes and functions.
 */
public class Main
	{
		/**
		 * The main method of the game, controls and calls other objects to make the game happen.
		 * @param args is an array of Strings.
		 */
	    public static void main(String[] args)
	        {
	    		/**
	    		 * Javafx game launch is below
	    		 */
	    		Application.launch(BreakoutApp.class, args);
	    		
	    		/**
	    		 * Text based game is below
	    		 */
	    		Board board = new Board(10,20); //columns, rows
	    		Text_GUI draw = new Text_GUI(true);
	    		Text_Block blocks = new Text_Block();
	    		Ball ball = new Ball(4,18); //columns, rows
	    		Player player = new Player(4); //column

	    		board.advancedRowBlocks(blocks.arrayBlocks(10, 15)); //columns,rows

	    		board.makePlayer(player);
	    		board.makeBall(ball);

	    		//board.generateRandomLevel(); //Use to generate random level
	    		board.addCustomLevelText("lines.txt"); //Can also use spikes.txt and heart.txt
	    		draw.printBoard(board, player);
	    		board.makePlayer(player);
	    		board.makeBall(ball);

	    		Scanner input = new Scanner(System.in);
	    		
	    		while(player.getScore() <= board.totalBlocks())
	    		{
	    			
	    			ball.updatePos((int) ball.getHorzMovement(), (int) ball.getVertMovement());  //Ball gets moved
	    			
	    			char direction = ' ';
	    		
	    			while (direction != 'L' && direction != 'R' && direction != 'N') {
	    				
	    				System.out.println("Please enter 'L' , 'R' or 'N'(none) for bar movement :");
		    			
		    			direction = input.next().charAt(0);

	    			}
	    			
	    			player.moveBar(direction);
	    			
	    			
	    			//if ball is on the bar's LEFT or RIGHT side..
	    			if (ball.getPosition().getX() == player.getPosition().getX() && ball.getPosition().getY()+1 == player.getPosition().getY()
	    					|| ball.getPosition().getX() == player.getPosition().getX()+1 && ball.getPosition().getY()+1 == player.getPosition().getY()){ 
	    			 
	    				ball.vertCollision();

	    			}
	    		 
	    			board.checkBrickCollision(ball,player); //Checks if ball collides with brick
	    		 
	    		 		
	    		 	board.updateBoard(ball.getPosition(), player.getPosition(),ball); //Board gets updated
	    		 		
	    		 	draw.printBoard(board, player); //Board gets displayed
	    			ball.checkLocation(); //Checks ball-wall collision

	    			}

	    			System.out.println("You win.");
	    			input.close();
	    			System.exit(0);
	        }
	}




