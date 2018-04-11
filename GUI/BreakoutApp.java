package GUI;

import Classes.*;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
import javafx.scene.layout.HBox;
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
		Ball ballMovement = new Ball(205, 430, new Image("file:Assets/Ball_Left.png"),// 28, 28, true, false), 
				new Image("file:Assets/Ball_LeftMiddle.png"),// 28, 28, true, false), 
				new Image("file:Assets/Ball.png"),// 28, 28, true, false), 
				new Image("file:Assets/Ball_RightMiddle.png"),// 28, 28, true, false),  
				new Image("file:Assets/Ball_Right.png"));// 28, 28, true, false));
		
		Player barMovement = new Player(190, new Image("file:Assets/Bar_Left.png", 100, 15, true, true), 
				new Image("file:Assets/Bar_LeftMiddle.png", 100, 15, true, true), 
				new Image("file:Assets/Bar.png", 100, 15, true, true), 
				new Image("file:Assets/Bar_RightMiddle.png", 100, 15, true, true), 
				new Image("file:Assets/Bar_Right.png", 100, 15, true, true));
		
		
		//Just setting up the graphics for the game including the player and ball
		Pane root = new Pane();
		Scene scene = new Scene(root , 408 , 500);
		
		//Rectangle bar = new Rectangle(280,460,70,8);
		//Rectangle bar = new Rectangle(280,460,70,15);
		//Circle ball = new Circle(205,455,7);
		ImageView spriteBall = new ImageView(new Image("file:Assets/Ball.png"));
		spriteBall.setX(205); spriteBall.setY(430); 
		spriteBall.setFitHeight(28); spriteBall.setFitWidth(28);
		
		ImageView spriteBar = new ImageView(new Image("file:Assets/Bar.png"));
		spriteBar.setX(190); spriteBar.setY(460); 
		spriteBar.setFitHeight(15); spriteBar.setFitWidth(100);
		
		CollisionObjects cO = new CollisionObjects(spriteBar, spriteBall);
		Board board = new Board(); 
		
		
		//style for the game
				scene.getStylesheets().add("/GUI/BreakoutAppStyle.css");
				//
		
				
		//Circle ball = new Circle(205,455,7);
		
		//Icon + ball img
		//Image icon = new Image("/GUI/ball.png",554,83,true,true);
		//primaryStage.getIcons().add(icon);
		//ball.setFill(new ImagePattern(icon));
		//
				
		//bar img
		//Image barImg = new Image("/GUI/barImg.png");
		//bar.setFill(new ImagePattern(barImg));
		//
				
		String[] perkList = new String[2]; perkList[0] = "lumpScoreBonus"; perkList[1] = "scoreMultiplier";
		PerkDrop pD = new PerkDrop(0.5, perkList);
		
		//Image imageBall = new Image("file:Assets/Ball.jpg");
		
		//spriteBall.setImage(imageBall);
		//HBox box = new HBox();
		//box.getChildren().add(spriteBall);
		
		
		
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
		
		
		//ball.setStroke(Color.BLACK);
		//ball.setFill(Color.CRIMSON);
		//root.getChildren().add(ball);
		root.getChildren().add(spriteBall);
		//root.getChildren().add(ball);
		root.getChildren().add(spriteBar);
		
		Label endScreen = new Label();
		endScreen.setVisible(false);
		endScreen.setFont(new Font(30));
		
		Label lives = new Label("Lives: 3");
		
		Label score = new Label("Score: 0");
		
		root.getChildren().add(score);
		root.getChildren().add(endScreen);
		root.getChildren().add(lives);
		
		endScreen.setLayoutX(140);
		endScreen.setLayoutY(270);
		score.setLayoutX(10);
		score.setLayoutY(10);
		lives.setLayoutX(300);
		lives.setLayoutY(10);
		
		//The animation "loop" that handles all movement in graphics
		Timeline timeline = new Timeline(new KeyFrame(Duration.millis(8), (evt) -> {
			
			ballMovement.setHitBrick(false);
			lives.setText("Lives: " + barMovement.getLives());
			
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
			cO.checkBallImageSwitch(ballMovement);
			cO.moveBallInWIndow(ballMovement);
        	//Moving Player
			cO.checkBarImageSwitch(barMovement);
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
        	
        	System.out.println(root.getChildren().size());
        	if(root.getChildren().size() - 5 == 0)
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
						if(e.getCode() == KeyCode.RIGHT && barMovement.getRFlag() == false && barMovement.getMoveFlag())
						//if(e.getCode() == KeyCode.RIGHT && barMovement.getLFlag() == false && barMovement.getMoveFlag())
						{
							barMovement.setRFlag(true);
						}
						
						if(e.getCode() == KeyCode.LEFT && barMovement.getLFlag() == false && barMovement.getMoveFlag())
						//if(e.getCode() == KeyCode.LEFT && barMovement.getRFlag() == false && barMovement.getMoveFlag())
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
