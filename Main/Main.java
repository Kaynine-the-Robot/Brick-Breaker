package Main;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.util.Duration;

import Classes.Board;
import GUI.BreakoutApp;
import GUI.Text_GUI;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;

import Classes.Ball;
import Classes.Block;
import Classes.Player;
import Classes.Text_Block;

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
 *@author Everyone
 */
public class Main
	{
		/**
		 * The main method of the game, controls and calls other objects to make the game happen.
		 * @param args is an array of Strings.
		 */
	    public static void main(String[] args)
	        {
	    		Application.launch(BreakoutApp.class, args);
	        }
	    
	}

//This is the old text based game
/**
Board board = new Board(6,5);
Text_GUI draw = new Text_GUI(true);
Block blocks = new Text_Block();
Ball ball = new Ball(2,3);
Player player = new Player(2);
board.makeBoard();

board.advancedRowBlocks(blocks.arrayBlocks());
board.makePlayer(player);
board.makeBall(ball);

draw.printBoard(board, player);
board.makePlayer(player);
board.makeBall(ball);               
                
while(player.getScore() < 3)
{
	
	ball.updatePos();  //Ball gets moved
	
	player.moveBar(); //Bar gets moved by the player
 
	
 //if ball is on the bar's LEFT or RIGHT side..
 if (ball.getPosition().getX() == player.getPosition().getX() && ball.getPosition().getY()+1 == player.getPosition().getY()
		 || ball.getPosition().getX() == player.getPosition().getX()+1 && ball.getPosition().getY()+1 == player.getPosition().getY()){ 
	 
	 ball.vertCollision();

 }
 
 board.checkBrickCollision(); //Checks if ball collides with brick
 
 if((int) ball.getPosition().getY() == 0)//Checks where ball is and switches direction if necessary
 {
	 player.increaseScore();
 }
 
 	board.updateBoard(ball.getPosition(), player.getPosition()); //Board gets updated
 	
 	draw.printBoard(board, player); //Board gets displayed
 
	board.checkBrickCollision(); //Checks if ball collides with brick
	
	ball.checkLocation(); //Checks where ball is and switches direction if necessary
	
	board.updateBoard(ball.getPosition(), player.getPosition()); //Board gets updated
	
	draw.printBoard(board, player); //Board gets displayed

}

System.out.println("You win.");
System.exit(0);

*/
