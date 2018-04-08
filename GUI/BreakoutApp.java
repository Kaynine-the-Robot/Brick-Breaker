package GUI;

import Classes.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
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
		
		//Menu items
		Button heartStage = new Button("Heart Stage");
		Button spikesStage = new Button("Spikes Stage");
		Button linesStage = new Button("Lines Stage");
		Button randomStage = new Button("Random Stage");
				
		heartStage.setLayoutX(50);
		heartStage.setLayoutY(50);
		spikesStage.setLayoutX(50);
		spikesStage.setLayoutY(100);
		linesStage.setLayoutX(50);
		linesStage.setLayoutY(150);
		randomStage.setLayoutX(50);
		randomStage.setLayoutY(200);
				
		Pane menuRoot = new Pane();
		Scene menu = new Scene(menuRoot, 200, 250, Color.SKYBLUE);
				
		menuRoot.getChildren().add(heartStage);
		menuRoot.getChildren().add(randomStage);
		menuRoot.getChildren().add(linesStage);
		menuRoot.getChildren().add(spikesStage);
		
		
		//Just setting up the graphics for the game including the player and ball
		Pane root = new Pane();
		Board board = new Board(); 
		Scene scene = new Scene(root , 408 , 500, Color.SKYBLUE);
		
		Rectangle bar = new Rectangle(280,460,70,8);		
		Circle ball = new Circle(205,455,7);
		
		CollisionObjects cO = new CollisionObjects(bar, ball);
		
		board.generateBlockArray(cO);
		
		cO.addBlockArrayToRoot(root);
		
		//board.generateRandomLevel(root);
		//board.removeBlockAtIndex(root, 0,4);
		//board.addCustomLevel(root,"lines.txt",Color.CORNFLOWERBLUE);
		
		ball.setStroke(Color.BLACK);
		ball.setFill(Color.CRIMSON);
		root.getChildren().add(ball);
		root.getChildren().add(bar);
		
		
		Label score = new Label("Score: 0");
		root.getChildren().add(score);
		score.setLayoutX(10);
		score.setLayoutY(10);

		//The animation "loop" that handles all movement in graphics
		Timeline timeline = new Timeline(new KeyFrame(Duration.millis(8), (evt) -> {
			
			ballMovement.setHitBrick(false);
			
			score.setText("Score: " + barMovement.getScore());
			
			//Here we are moving the ball
			cO.moveBallInWIndow(ballMovement);
        	//Moving Player
        	cO.movePlayerInWindow(barMovement);
        	
        	//Checking ball and all borders of the window
        	cO.checkBallAndBorders(ballMovement);
        	//Checking ball and player bar collision
        	cO.checkBallPlayerCollisionTrigger(root, ballMovement, barMovement);
        	//If ball collides with brick
        	cO.checkBallBrickCollisionTrigger(root,ballMovement,barMovement, board);
        	
        	if(root.getChildren().size() - 3 == 0)
        	{
        		ballMovement.pauseBall();
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
        //Setting up the the final for actually showing the graphics
		primaryStage.setScene(menu);
		primaryStage.setTitle("Brick Breaker");
		primaryStage.show();
		
		//Sets up both menu buttons
    	heartStage.setOnAction(new EventHandler<ActionEvent>( ) {
			@Override
			public void handle(ActionEvent e) {
				
				Paint[] colors = new Paint[1];
				colors[0] = Color.DARKRED;
				board.addCustomLevel(root,"heart.txt", colors, cO);
				primaryStage.setScene(scene);
				timeline.play();
			}
		});
    	
    	spikesStage.setOnAction(new EventHandler<ActionEvent>( ) {
			@Override
			public void handle(ActionEvent e) {
				
				Paint[] colors = new Paint[4];
				colors[0] = Color.BLUE; colors[1] = Color.BROWN; colors[2] = Color.GREEN; colors[3] = Color.RED;
				board.addCustomLevel(root,"spikes.txt", colors, cO);
				primaryStage.setScene(scene);
				timeline.play();
			}
		});
    	
    	linesStage.setOnAction(new EventHandler<ActionEvent>( ) {
			@Override
			public void handle(ActionEvent e) {
				
				Paint[] colors = new Paint[4];
				colors[0] = Color.BLUE; colors[1] = Color.AQUA; colors[2] = Color.GREEN; colors[3] = Color.RED;
				board.addCustomLevel(root,"lines.txt", colors, cO);
				primaryStage.setScene(scene);
				timeline.play();
			}
		});
		
		randomStage.setOnAction(new EventHandler<ActionEvent>( ) {

			@Override
			public void handle(ActionEvent e) {
				
				Paint[] colors = new Paint[4];
				colors[0] = Color.BLUE; colors[1] = Color.BROWN; colors[2] = Color.GREEN; colors[3] = Color.RED;
				board.generateRandomLevel(root, cO, colors);
				primaryStage.setScene(scene);
				timeline.play();
				
			}
		});
		 
	} 
    
	@Override
	public void handle(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
