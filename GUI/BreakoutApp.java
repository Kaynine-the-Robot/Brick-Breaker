package GUI;

import Classes.Ball;
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

	@Override
	public void handle(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
