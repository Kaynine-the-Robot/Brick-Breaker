package GUI;

import Classes.Ball;
import Classes.Board;
import Classes.Player;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class BreakoutApp extends Application implements EventHandler<KeyEvent>{

	//Overriding the inherited start function of Application class in JavaFX.
    @Override
	public void start(Stage primaryStage) throws Exception {
		
    	//Classes used for keeping track of the ball and player movement for the GUI
		Ball ballMovement = new Ball(0,0);
		Player barMovement = new Player(0);
		Board board = new Board("Default",10,3); //10 rows and 2 columns in the block structure
		board.generateBlockArray();
		
		//Just setting up the graphics for the game including the player and ball
		Pane root = new Pane();
		Scene scene = new Scene(root , 400 , 500);
		
		board.addBlockArray(root);

		//This here just messes around with different block arrangements by removing some, so creating a "design"
		//Can save some levels and just load them on here somehow
		board.removeBlockAtIndex(root, 4, 0);
		board.removeBlockAtIndex(root, 5, 0);
		board.removeBlockAtIndex(root, 6, 0);
		board.removeBlockAtIndex(root, 3, 1);
		board.removeBlockAtIndex(root, 4, 1);
		board.removeBlockAtIndex(root, 5, 1);
		board.removeBlockAtIndex(root, 2, 2);
		board.removeBlockAtIndex(root, 3, 2);
		board.removeBlockAtIndex(root, 4, 2);
		
		Rectangle bar = new Rectangle(280,460,70,8);
		
		Circle ball = new Circle(205,455,10);
		ball.setStroke(Color.BLACK);
		ball.setFill(Color.RED);
		root.getChildren().add(ball);
		root.getChildren().add(bar);
		
		
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
        	
			//If ball comes into contact with bar
        	if((root.getChildren().contains(bar)) && (ball.getBoundsInParent().intersects(bar.getBoundsInParent())))
        		{
        			ballMovement.vertCollision();
        		}  	
        	
        	//If ball collides with brick
        	board.checkBallBrickCollision(root,ball,ballMovement);
        	
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

	@Override
	public void handle(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
