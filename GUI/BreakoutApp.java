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
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class BreakoutApp extends Application implements EventHandler<KeyEvent>{
		
	//Overriding the inherited start function of Application class in JavaFX.
    @Override
	public void start(Stage primaryStage) throws Exception {
		
    	//Classes used for keeping track of the ball and player movement for the GUI
		Ball ballMovement = new Ball(0,0);
		Player barMovement = new Player(0);
		
		
		//Just setting up the graphics for the game including the player and ball
		Pane root = new Pane();
		Board board = new Board(); 
		Scene scene = new Scene(root , 408 , 500);
		
		//style for the game
				scene.getStylesheets().add("/GUI/BreakoutAppStyle.css");
				//
		
		Rectangle bar = new Rectangle(280,460,70,15);		
		Circle ball = new Circle(205,455,7);
		
		//Icon + ball img
		Image icon = new Image("/GUI/ball.png",554,83,true,true);
		primaryStage.getIcons().add(icon);
		ball.setFill(new ImagePattern(icon));
		//
				
		//bar img
		Image barImg = new Image("/GUI/barImg.png");
		bar.setFill(new ImagePattern(barImg));
		//
		
		CollisionObjects cO = new CollisionObjects(bar, ball);
		
		String[] perkList = new String[2]; perkList[0] = "lumpScoreBonus"; perkList[1] = "scoreMultiplier";
		PerkDrop pD = new PerkDrop(0.5, perkList);
		
		board.generateBlockArray(cO);
		
		cO.addBlockArrayToRoot(root);
		
		//
		MainMenu.display();
		if (MainMenu.getRandomOrCustom().equals("")) {
			System.out.println("The JavaFX GUI has been closed.");
			System.out.println("If you wish to access text version, \n"
					+ "do so in Main method and set up the level there.");
			System.exit(0);
		}
		
		if (MainMenu.getRandomOrCustom().equals("random")) {
			board.generateRandomLevel(root, cO, MainMenu.getColors());
		}
		
		else if (MainMenu.getRandomOrCustom().equals("custom")) {
			board.addCustomLevel(root,MainMenu.getLevelName(), MainMenu.getColors(), cO);
		}
		
		
			
		primaryStage.setScene(scene);
		
		
		root.getChildren().add(ball);
		root.getChildren().add(bar);
		
		Label endScreen = new Label();
		endScreen.setVisible(false);
		endScreen.setFont(new Font(30));
		Label score = new Label("Score: 0");
		root.getChildren().add(score);
		root.getChildren().add(endScreen);
		endScreen.setLayoutX(140);
		endScreen.setLayoutY(270);
		score.setLayoutX(10);
		score.setLayoutY(10);
		
		//The animation "loop" that handles all movement in graphics
		Timeline timeline = new Timeline(new KeyFrame(Duration.millis(8), (evt) -> {
			
			ballMovement.setHitBrick(false);
			
			
			
			if(barMovement.getMultiFlag())
			{
				barMovement.updateCurrentTime(pD);
				score.setText("Score: " + barMovement.getScore() + " MULTIPLIER X" + pD.getMulti() + " " + barMovement.getMultiTimer());
			}
			else
			{
				score.setText("Score: " + barMovement.getScore());
			}
			
			//Here we are moving the ball
			cO.moveBallInWIndow(ballMovement);
        	//Moving Player
        	cO.movePlayerInWindow(barMovement);
        	//Moving any Perks
        	cO.moveAllPerksInWindow(pD);
        	
        	//Checking ball and all borders of the window
        	cO.checkBallAndBorders(ballMovement, barMovement);
        	//Checking ball and player bar collision
        	cO.checkBallPlayerCollisionTrigger(root, ballMovement, barMovement);
        	//If ball collides with brick
        	cO.checkBallBrickCollisionTrigger(root,ballMovement,barMovement, board, pD);
        	//Checking falling perks collisions
        	cO.checkPerkCollisions(root, barMovement, pD);
        	
        	if(barMovement.getMoveFlag() == false)
        	{
        		endScreen.setVisible(true);
        		endScreen.setText("YOU LOSE");
        	}
        	
        	if(root.getChildren().size() - 3 == 0)
        	{
        		ballMovement.pauseBall();
        		barMovement.setMoveFlag(false);
        		
        		endScreen.setVisible(true);
        		endScreen.setText("YOU WIN");
        	}
        	
        	
        	
		})); //This is the end of the Timeline animation
		
		
			//The Key handler for key presses, sets flag on in movement objects
			scene.setOnKeyPressed(
				new EventHandler<KeyEvent>()
				{
					public void handle(KeyEvent e)
					{
						if(e.getCode() == KeyCode.RIGHT && barMovement.getLFlag() == false && barMovement.getMoveFlag())
						{
							barMovement.setRFlag(true);
						}
						
						if(e.getCode() == KeyCode.LEFT && barMovement.getRFlag() == false && barMovement.getMoveFlag())
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
		timeline.play();
		primaryStage.setTitle("Brick Breaker");
		
		//This bit ensures the maximization of the window isn't allowed
				primaryStage.maximizedProperty().addListener((observable, oldValue, newValue) -> {
		            if (newValue)
		                primaryStage.setMaximized(false);
		        });
				//
				
		primaryStage.show();
		
		
		 
	} 
    
	@Override
	public void handle(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
