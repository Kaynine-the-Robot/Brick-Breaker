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
public class Main extends Application implements EventHandler<KeyEvent>
	{
		
		/**
		 * The main method of the game, controls and calls other objects to make the game happen.
		 * @param args is an array of Strings.
		 */
	    public static void main(String[] args)
	        {
	    		launch(args);
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
                                
                while(player.getScore() < 3){
                	
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
	        }
	    
	    @Override
		public void start(Stage primaryStage) throws Exception {
			
			Ball ballMovement = new Ball(0,0);
			
			Pane root = new Pane();
			Scene scene = new Scene(root , 400 , 500);
			
			Rectangle bar = new Rectangle(170,460,70,8);
			
			Circle ball = new Circle(205,455,10);
			ball.setStroke(Color.RED);
			ball.setFill(Color.RED);
			root.getChildren().add(ball);
			root.getChildren().add(bar);
			
			scene.setOnKeyPressed(this);
			
			
			//ball.relocate(0, 10); //Might be useful later
			//final Bounds bounds = new Bounds();

			final Bounds bounds = root.getBoundsInLocal(); //Border bounds
			
			//The animation "loop"
			Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10), (evt) -> {
				
				//Here we are moving the ball
            	ball.setLayoutX(ball.getLayoutX() + ballMovement.getHorzMovement());
            	ball.setLayoutY(ball.getLayoutY() + ballMovement.getVertMovement());
            	
            	//If the all comes in contact with left or right side of border
            	if (ball.getLayoutX() == (195-ball.getRadius()) || ball.getLayoutX() == (-206+ ball.getRadius())){
            		ballMovement.horzCollision();
            	}
            	
            	//If ball comes in contact with top or bottom 
            	if (ball.getLayoutY() == (-456 + ball.getRadius())) {
            		ballMovement.vertCollision();
            		
            	//If ball comes in contact with the bar
            	if (ball.getBoundsInParent().intersects(bar.getBoundsInParent()) == true ){
            		System.out.println("Ball intersected the bar!!");
            	}
      
           	}
            	
			}));
			
			timeline.setCycleCount(Timeline.INDEFINITE);
	        timeline.play();
			
			primaryStage.setScene(scene);
			primaryStage.setTitle("Block Breaker");
			primaryStage.show();
			
	
			 
		}
		
		@Override
		public void handle(KeyEvent event) {
			if (event.getCode() == KeyCode.KP_RIGHT) {
				System.out.println("Right key pressed");
			}
			//System.out.println(barX);
		}
	    
		
	    

	}