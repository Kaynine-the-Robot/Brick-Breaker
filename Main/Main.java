package Main;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.util.Duration;

import Classes.Board;
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
public class Main extends Application // *** SEEMS TO DO NOTHING *** implements EventHandler<KeyEvent>
	{
	
	
		
		/**
		 * The main method of the game, controls and calls other objects to make the game happen.
		 * @param args is an array of Strings.
		 */
	    public static void main(String[] args)
	        {
	    		launch(args);
	        }
	    
	    //Overriding the inherited start function of Application class in JavaFX.
	    @Override
		public void start(Stage primaryStage) throws Exception {
			
	    	//Classes used for keeping track of the ball and player movement for the GUI
			Ball ballMovement = new Ball(0,0);
			Player barMovement = new Player(0);
			
			//A 2D array for keeping track of the bricks in the GUI and inserting the bricks into it with info
			Rectangle[][] bricks = new Rectangle[10][3];
			
			for(int i = 0; i < bricks.length; i++)
			{
				for(int j = 0; j < bricks[0].length; j++)
				{
					bricks[i][j] = new Rectangle(10 + (40 * i), 70 + (20 * j), 30, 10);
				}
			}
			
			//Just setting up the graphics for the game including the player and ball
			Pane root = new Pane();
			Scene scene = new Scene(root , 400 , 500);
			
			Rectangle bar = new Rectangle(280,460,70,8);
			
			Circle ball = new Circle(205,455,10);
			ball.setStroke(Color.BLACK);
			ball.setFill(Color.RED);
			root.getChildren().add(ball);
			root.getChildren().add(bar);
			
			//Iterating through the array of bricks and adding them to the graphics
			for(int i = 0; i < bricks.length; i++)
			{
				for(int j = 0; j < bricks[0].length; j++)
				{
					root.getChildren().add(bricks[i][j]);
				}	
			}			
			//The animation "loop" that handles all movement in graphics
			Timeline timeline = new Timeline(new KeyFrame(Duration.millis(8), (evt) -> {
				
				ballMovement.setHitBrick(false);
				
				//Here we are moving the ball
            	ball.setLayoutX(ball.getLayoutX() + ballMovement.getHorzMovement());
            	ball.setLayoutY(ball.getLayoutY() + ballMovement.getVertMovement());
            	
            	//If the right key is down
            	if(barMovement.getRFlag() && bar.getLayoutX() < 50)
            	{
            		bar.setLayoutX(bar.getLayoutX() + 1);
            	}
            	
            	//If the left key is down
            	if(barMovement.getLFlag() && bar.getLayoutX() > -280)
            	{
            		bar.setLayoutX(bar.getLayoutX() - 1);
            	}
            	
            	//If the all comes in contact with left or right side of border
            	if (ball.getLayoutX() == (195-ball.getRadius()) || ball.getLayoutX() == (-206+ ball.getRadius()))
            	{
            		ballMovement.horzCollision();
            	}
            	
            	//If ball comes in contact with top side of the border
            	if (ball.getLayoutY() == (-456 + ball.getRadius())) 
            	{
            	ballMovement.vertCollision();
            	}
            	
            	//if ball hits the floor (game ends)
            	if (ball.getLayoutY() == (46 - ball.getRadius())) 
            	{
            		System.exit(0); //For now, to become a losing screen
            	}
            	
            	//Checks each brick for ball contact
            	for(int i = 0; i < bricks.length; i++)
            	{
            		for(int j = 0; j < bricks[0].length; j++)
            		{
            			if((ballMovement.getHitBrick() == false && root.getChildren().contains(bricks[i][j])) && (ball.getBoundsInParent().intersects(bricks[i][j].getBoundsInParent()))) 
            			{
            				root.getChildren().remove(bricks[i][j]);
            				ballMovement.vertCollision();
            				ballMovement.setHitBrick(true);
            			}
            		}
            	}
            	
				//If ball comes into contact with bar
            	if((root.getChildren().contains(bar)) && (ball.getBoundsInParent().intersects(bar.getBoundsInParent())))
            		{
            			ballMovement.vertCollision();
            		}  	
			})); //This is the end of the Timeline animation
			
				//The Key handler for key presses, sets flag on in movement objects
				scene.setOnKeyPressed(
					new EventHandler<KeyEvent>()
					{
						public void handle(KeyEvent e)
						{
							if(e.getCode() == KeyCode.RIGHT && barMovement.getLFlag() == false)
							{
								barMovement.setRFlag(true);
							}
							
							if(e.getCode() == KeyCode.LEFT && barMovement.getRFlag() == false)
							{
								barMovement.setLFlag(true);
							}
						}
					});
				//The Key handler for keys released, sets flags off in movement objects
				scene.setOnKeyReleased(
						new EventHandler<KeyEvent>()
						{
							public void handle(KeyEvent e)
							{
								if(e.getCode() == KeyCode.RIGHT)
								{
									barMovement.setRFlag(false);
								}
								
								if(e.getCode() == KeyCode.LEFT)
								{
									barMovement.setLFlag(false);
								}
							}
						});
			
			//Timeline goes forever unless interrupted and starts timeline
			timeline.setCycleCount(Timeline.INDEFINITE);
	        timeline.play();
			
	        //Setting up the the final for actually showing the graphics
			primaryStage.setScene(scene);
			primaryStage.setTitle("Brick Breaker");
			primaryStage.show();
			 
		}
	}

//This is the old text based game
/**
Board board = new Board(6,5);
Text_GUI draw = new Text_GUI(true);
Block blocks = new Block();
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


**/	