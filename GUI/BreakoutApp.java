package GUI;


import java.io.File;

import Classes.*;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

public class BreakoutApp extends Application implements EventHandler<KeyEvent>{
		
	//Overriding the inherited start function of Application class in JavaFX.
    @Override
	public void start(Stage primaryStage) throws Exception {
		
    	//Just setting up the graphics for the game including the player and ball
    	Pane root = new Pane();
    	Scene scene = new Scene(root , 816 , Screen.getPrimary().getVisualBounds().getHeight() - 50);
    	
    	Image img = new Image("file:GUI/purpleSpace.jpg");
    	final double BACKGROUND_WIDTH = 816;
    	final double BACKGROUND_HEIGHT = Screen.getPrimary().getVisualBounds().getHeight() - 50;
    	final double TOP_OFFSET = 10;
    	
		BackgroundSize bS = new BackgroundSize(BACKGROUND_WIDTH,BACKGROUND_HEIGHT,true,true,true,true);
		final BackgroundImage bI = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, 
	            					BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, bS);
	    final Background background = new Background(bI);
	    root.setBackground(background); root.setMinWidth(img.getWidth()); root.setMinHeight(img.getHeight());
    	
    	ImageView spriteBar = new ImageView(new Image("file:Assets/Bar.png"));
    	spriteBar.setX(BACKGROUND_WIDTH/2); spriteBar.setY(BACKGROUND_HEIGHT - 50); 
    	spriteBar.setFitHeight(15); spriteBar.setFitWidth(140);
    	
    	ImageView spriteBall = new ImageView(new Image("file:Assets/Ball.png"));
    	spriteBall.setFitHeight(28); spriteBall.setFitWidth(28);
    	spriteBall.setX(spriteBar.getX()+spriteBar.getFitWidth()/2); spriteBall.setY(spriteBar.getY() - spriteBall.getFitHeight() - 5); 
    	
    	//Classes used for keeping track of the ball and player movement for the GUI
		Ball ballMovement = new Ball(BACKGROUND_WIDTH/2, BACKGROUND_HEIGHT * (9/10), new Image("file:Assets/Ball_Left.png"),// 28, 28, true, false), 
				new Image("file:Assets/Ball_LeftMiddle.png"),// 28, 28, true, false), 
				new Image("file:Assets/Ball.png"),// 28, 28, true, false), 
				new Image("file:Assets/Ball_RightMiddle.png"),// 28, 28, true, false),  
				new Image("file:Assets/Ball_Right.png"));// 28, 28, true, false));
		
		Player barMovement = new Player(BACKGROUND_WIDTH/2, new Image("file:Assets/Bar_Left.png", 140, 15, true, true), 
				new Image("file:Assets/Bar_LeftMiddle.png", 140, 15, true, true), 
				new Image("file:Assets/Bar.png", 140, 15, true, true), 
				new Image("file:Assets/Bar_RightMiddle.png", 140, 15, true, true), 
				new Image("file:Assets/Bar_Right.png", 140, 15, true, true));
		
		CollisionObjects cO = new CollisionObjects(spriteBar, spriteBall, BACKGROUND_WIDTH, BACKGROUND_HEIGHT);
		Board board = new Board(); 
		
		scene.getStylesheets().add("/GUI/BreakoutAppStyle.css"); //The font,size,color of labels
				
		String[] perkList = new String[2]; perkList[0] = "lumpScoreBonus"; perkList[1] = "scoreMultiplier";
		PerkDrop pD = new PerkDrop(1, perkList);
		
		Sound gameSounds = new Sound(new Media(new File("Assets/Impact1.mp3").toURI().toString()), 
				new Media(new File("Assets/ImpactGlass.mp3").toURI().toString()), 
				new Media(new File("Assets/Atmosphere.mp3").toURI().toString()), 
				new Media(new File("Assets/Death.mp3").toURI().toString()));
		
		board.generateBlockArray(cO);
		
		cO.addBlockArrayToRoot(root);
		
		
		//Below is the main menu display
		
		MainMenu.display(ScoresTracker.getHighScoreAsString());
		if (MainMenu.getRandomOrCustom().equals("")) 
		{
			System.out.println("The JavaFX GUI has been closed.");
			System.out.println("If you wish to access text version, \n"
					+ "do so in Main method and set up the level there.");
			System.exit(0);
		}
		
		if (MainMenu.getRandomOrCustom().equals("random")) 
		{
			board.generateRandomLevel(root, cO, MainMenu.getColors());
		}
		
		else if (MainMenu.getRandomOrCustom().equals("custom")) 
		{
			board.addCustomLevel(root,MainMenu.getLevelName(), MainMenu.getColors(), cO);
		}
		
		primaryStage.setScene(scene);
		
		root.getChildren().add(spriteBall);

		root.getChildren().add(spriteBar);
		
		Label endScreen = new Label();
		endScreen.setVisible(false);
		endScreen.setFont(new Font(50));
		
		Label lives = new Label("Lives: 3");
		
		Label score = new Label("Score: 0");
		
		root.getChildren().add(score);
		root.getChildren().add(endScreen);
		root.getChildren().add(lives);
		
		endScreen.setLayoutX(BACKGROUND_WIDTH/2 - 35);
		endScreen.setLayoutY(BACKGROUND_HEIGHT/2 + 30);
		score.setLayoutX(TOP_OFFSET);
		score.setLayoutY(TOP_OFFSET);
		lives.setLayoutX(BACKGROUND_WIDTH - (BACKGROUND_WIDTH/8));
		lives.setLayoutY(TOP_OFFSET);
		
		//We use this to write scores to a file
		ScoresTracker scoreWriter = new ScoresTracker(MainMenu.getLevelName());
		
		//Count for BallSpeed up
		ballMovement.setSpeedTimer();
		gameSounds.getAmbientBackground().setCycleCount(MediaPlayer.INDEFINITE);
		gameSounds.getAmbientBackground().play();
		
		//The animation "loop" that handles all movement in graphics
		Timeline timeline = new Timeline(new KeyFrame(Duration.millis(8), (evt) -> 
		{
			
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
			
			ballMovement.checkCurrentTime();
		
			//Here we are moving the ball
			cO.checkBallImageSwitch(ballMovement);
			cO.moveBallInWIndow(ballMovement);
        	//Moving Player
			cO.checkBarImageSwitch(barMovement);
        	cO.movePlayerInWindow(barMovement, ballMovement);
        	
        	//Moving any Perks
        	cO.moveAllPerksInWindow(pD);
        	
        	//Checking ball and all borders of the window
        	cO.checkBallAndBorders(ballMovement, barMovement, gameSounds.getImpactAtIndex(0), gameSounds.getDeath());
        	//Checking ball and player bar collision
        	cO.checkBallPlayerCollisionTrigger(ballMovement, gameSounds.getImpactAtIndex(0));
        	//If ball collides with brick
        	cO.checkBallBrickCollisionTrigger(root,ballMovement,barMovement, board, pD, gameSounds.getImpactAtIndex(1));
        	//Checking falling perks collisions
        	cO.checkPerkCollisions(root, barMovement, pD);
        	
        	
        	//If the player lost
        	if(barMovement.getMoveFlag() == false)
        	{
        		endScreen.setVisible(true);

        		endScreen.setText("! YOU LOSE - Press Esc !");
        	}
        	
        	//Update the current high score to file if greater then the last score
        	scoreWriter.scoreToFileUpdate(barMovement.getScore());
        	
        	//Check if all blocks have been destroyed
        	
        	if(root.getChildren().size() - 5 == 0)
        	{
        		ballMovement.pauseBall();
        		barMovement.setMoveFlag(false);
        		
        		//Write to file that the level has been completed marked with - "1"
        		scoreWriter.levelWonUpdate(barMovement.getScore());
        		
        		endScreen.setText("! YOU WIN - Press Esc !");
        		endScreen.setVisible(true);
        	
        	
        	
		}})); //This is the end of the Timeline animation
		
		
		//The Key handler for key presses, sets flag on in movement objects
		scene.setOnKeyPressed(
		new EventHandler<KeyEvent>()
		{
				public void handle(KeyEvent e)
				{
					if(e.getCode() == KeyCode.RIGHT && barMovement.getRFlag() == false && barMovement.getMoveFlag())
					{
						barMovement.setRFlag(true);
					}
					
					if(e.getCode() == KeyCode.LEFT && barMovement.getLFlag() == false && barMovement.getMoveFlag())
					{
						barMovement.setLFlag(true);
					}
					
					//For if the player presses start at beginning of a level or when a life is lost, starts the ball moving
					if(e.getCode() == KeyCode.SPACE)
					{
						ballMovement.startBall();
					}
				
				
				
				//Esc is basically a re-set of the game but score is kept
				if (e.getCode() == KeyCode.ESCAPE) 
				{
				
					scoreWriter.scoreToFileUpdate(barMovement.getScore()); //Writing score to file, keeping track
					
					endScreen.setVisible(false);
			    	
					primaryStage.close();
					timeline.pause();
					
					primaryStage.close();

					//Resetting bar and ball positions
					spriteBall.setFitHeight(28); spriteBall.setFitWidth(28);
			    	spriteBall.setX(BACKGROUND_WIDTH/2); spriteBall.setY(spriteBar.getY() - spriteBall.getFitHeight() - 5); 
			    	spriteBar.setX(BACKGROUND_WIDTH/2); spriteBar.setY(BACKGROUND_HEIGHT - 50); 
			    	spriteBar.setFitHeight(15); spriteBar.setFitWidth(140);
			    	ballMovement.reset();
			    	gameSounds.reset();
			    	 
			    	//Reseting block array here	!!! removeblockfrom root
					//for (int i = 0 ;i < root.getChildren().size() - 5 ; i++) 
					//{
					
						
						//root.getChildren().remove(root.getChildren());
					//}
			    	root.getChildren().clear();
			    	
			    	root.getChildren().add(spriteBall);
					root.getChildren().add(spriteBar);
					root.getChildren().add(score);
					root.getChildren().add(endScreen);
					root.getChildren().add(lives);
					
					board.generateBlockArray(cO);
					
					cO.addBlockArrayToRoot(root);
					
					MainMenu.display(ScoresTracker.getHighScoreAsString());
	        		
					if (MainMenu.getRandomOrCustom().equals("random")) 
					{
						board.generateRandomLevel(root, cO, MainMenu.getColors());
					}
					
					else if (MainMenu.getRandomOrCustom().equals("custom")) 
					{
						board.addCustomLevel(root,MainMenu.getLevelName(), MainMenu.getColors(), cO);
					}
	
											
					primaryStage.setScene(scene);
					
					
					barMovement.setLives(3);
					barMovement.setScore(0);
					
					barMovement.setMoveFlag(true);
			   
			    	timeline.playFromStart();
					primaryStage.show(); 

					
				}
				
			}});
		
		
			//The Key handler for keys released, sets flags off in movement objects
		scene.setOnKeyReleased(
			new EventHandler<KeyEvent>()
			{
					public void handle(KeyEvent e)
				{
					if(e.getCode() == KeyCode.RIGHT)
					{
						barMovement.setRFlag(false);
						barMovement.resetAccelerate();
					}
						
					if(e.getCode() == KeyCode.LEFT)
					{
						barMovement.setLFlag(false);
						barMovement.resetAccelerate();
					}
				}
			});

		//Timeline goes forever unless interrupted and starts timeline
		timeline.setCycleCount(Timeline.INDEFINITE);
        //Setting up the the final for actually showing the graphics
		timeline.play();
		primaryStage.setTitle("Brick Breaker");
		
		//This bit ensures the maximization of the window isn't allowed
		primaryStage.maximizedProperty().addListener((observable, oldValue, newValue) -> 
		{
		   if (newValue) primaryStage.setMaximized(false);
		 });
		//
				
		primaryStage.show();
			 
		
    }

	@Override
	public void handle(KeyEvent event) {
		// TODO Auto-generated method stub
		
	}}
 
