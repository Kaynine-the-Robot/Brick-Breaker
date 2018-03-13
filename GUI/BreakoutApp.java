package GUI;


import Classes.Ball;
import Classes.Player;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
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

	 @Override
		public void start(Stage primaryStage) throws Exception {
			
			Ball ballMovement = new Ball(0,0);
			Player barMovement = new Player(0);
			
			Rectangle[][] bricks = new Rectangle[10][3];
			
			for(int i = 0; i < bricks.length; i++)
			{
				for(int j = 0; j < bricks[0].length; j++)
				{
					bricks[i][j] = new Rectangle(10 + (40 * i), 70 + (20 * j), 30, 10);
				}
			}
			
			Pane root = new Pane();
			Scene scene = new Scene(root , 400 , 500);
			
			Rectangle bar = new Rectangle(280,460,70,8);
			
			Circle ball = new Circle(205,455,10);
			ball.setStroke(Color.RED);
			ball.setFill(Color.RED);
			root.getChildren().add(ball);
			root.getChildren().add(bar);
			
			for(int i = 0; i < bricks.length; i++)
			{
				for(int j = 0; j < bricks[0].length; j++)
				{
					root.getChildren().add(bricks[i][j]);
				}	
			}
			
			scene.setOnKeyPressed(this);
			
			
			//ball.relocate(0, 10); //Might be useful later
			//final Bounds bounds = new Bounds();

			final Bounds bounds = root.getBoundsInLocal(); //Border bound4
			
			//The animation "loop"
			Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10), (evt) -> {
				
				ballMovement.setHitBrick(false);
				
				//Here we are moving the ball
         	ball.setLayoutX(ball.getLayoutX() + ballMovement.getHorzMovement());
         	ball.setLayoutY(ball.getLayoutY() + ballMovement.getVertMovement());
         	
         	//If the right key is down
         	if(barMovement.getRFlag() && bar.getLayoutX() < 330)
         	{
         		bar.setLayoutX(bar.getLayoutX() + 1);
         	}
         	
         	//If the left key is down
         	if(barMovement.getLFlag() && bar.getLayoutX() > -280)
         	{
         		bar.setLayoutX(bar.getLayoutX() - 1);
         	}
         	
         	//If the all comes in contact with left or right side of border
         	if (ball.getLayoutX() == (195-ball.getRadius()) || ball.getLayoutX() == (-206+ ball.getRadius())){
         		ballMovement.horzCollision();
         	}
         	
         	//If ball comes in contact with top side of the border
         	if (ball.getLayoutY() == (-456 + ball.getRadius())) 
         		{
         		ballMovement.vertCollision();
         		}
         	
         	//if ball hits the floor (game ends)
         	if (ball.getLayoutY() == (46 - ball.getRadius())) {
         		System.exit(0); //For now..
         		
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
			}));
			
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
			
			timeline.setCycleCount(Timeline.INDEFINITE);
	        timeline.play();
			
			primaryStage.setScene(scene);
			primaryStage.setTitle("Brick Breaker");
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
