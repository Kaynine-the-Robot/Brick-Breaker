package Classes;

import java.awt.TextField;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.converter.IntegerStringConverter;

public class MainMenu {

	private static Paint[] colors ;
	private static String levelName = "";
	private static String randomOrCustom = "";
	private static Scene menu;
	private static String scoreToWrite = "0";
	
	/**
	 * This method is for getting the color array
	 * @return colors - Paint[] array
	 */
	public static Paint[] getColors(){
		return colors;
	}
	
	/**
	 * This method is for returning the level name
	 * @return levelName - String
	 */
	public static String getLevelName() {
		return levelName;
	}
	/**
	 * This method is for returning level selection
	 * @return randomOrCustom - String
	 */
	public static String getRandomOrCustom() {

			return randomOrCustom;
			
	}
	
	/**
	 * This method is for returning the menu scene
	 * @return menu - Scene
	 */
	public static Scene getMenu() {
		return menu;
	}
	
	
	/**
	 * This method is for displaying the actual menu
	 */
	public static void display(String scoreToWrite) {
		
		//Buttons
		Button heartStage = new Button("HeartStage | High Score : " + ScoresTracker.getHeartHighScore("heart.txt"));
		Button spikesStage = new Button("SpikesStage | High Score: " +  ScoresTracker.getHeartHighScore("spikes.txt"));
		Button linesStage = new Button("LinesStage| High Score: " +  ScoresTracker.getHeartHighScore("lines.txt"));
		Button randomStage = new Button("RandomStage | High Score: " + ScoresTracker.getHeartHighScore("random"));
		
		
		String[] FileArray = ScoresTracker.getFileContentsArray();
		//
		if (FileArray[0].equals("1")) {heartStage.setText("HeartStage | High Score : " + ScoresTracker.getHeartHighScore("heart.txt") + " DONE");}
		if (FileArray[2].equals("1")) {spikesStage.setText("SpikesStage | High Score: " + ScoresTracker.getHeartHighScore("spikes.txt") + " DONE!");}
		if (FileArray[4].equals("1")) {linesStage.setText("LinesStage| High Score: " + ScoresTracker.getHeartHighScore("lines.txt") + " DONE!");}
		if (FileArray[6].equals("1")) {randomStage.setText("RandomStage | High Score: " + ScoresTracker.getHeartHighScore("random") + " DONE!");}
		
		Stage window = new Stage();
		window.setTitle("Welcome to the Brick Breaker!");
		window.initModality(Modality.APPLICATION_MODAL);

		//The brick breaker logo
		ImageView logo = new ImageView(new Image("/GUI/logo.png",554,83,true,true));
		logo.setFitWidth(330);
		logo.setFitHeight(75);
		//
		
		//Icon
		Image icon = new Image("/GUI/ball.png",554,83,true,true);
		window.getIcons().add(icon);
		//
		
		//High Score 
		Label scoreText = new Label("CURRENT HIGHSCORE: " + scoreToWrite);
		//
		
		BorderPane menuRoot = new BorderPane();
		VBox vbox1 = new VBox();
		HBox hbox1 = new HBox();
		
		vbox1.getChildren().add(heartStage);
		vbox1.getChildren().add(randomStage);
		vbox1.getChildren().add(linesStage);
		vbox1.getChildren().add(spikesStage);
		vbox1.getChildren().add(scoreText);
		hbox1.getChildren().add(logo);
		
		menuRoot.setTop(hbox1);
		menuRoot.setCenter(vbox1);
		
		menu = new Scene(menuRoot, 330, 285);
		
		menu.getStylesheets().add("/GUI/MainMenuStyle.css");
		
		window.setScene(menu);
		
		vbox1.setPadding(new Insets(8));
		vbox1.setSpacing(10);
		
		//This bit ensures the maximization of the window isn't allowed
			window.maximizedProperty().addListener((observable, oldValue, newValue) -> {
		        if (newValue)
		             window.setMaximized(false);
		        });
		//
			
		
		//Sets up both menu buttons
		randomStage.setOnAction(e -> {

				colors = new Paint[4];
				colors[0] = Color.BLUE; colors[1] = Color.BROWN; colors[2] = Color.GREEN; colors[3] = Color.RED;
				levelName = "random";
				randomOrCustom = "random";
				window.close();
			
		});
		
    	heartStage.setOnAction(e -> {
    		
				colors = new Paint[1];
				colors[0] = Color.DARKRED;
				levelName = "heart.txt";
				randomOrCustom = "custom";
				window.close();
		});
    	
    	spikesStage.setOnAction(e -> {

				colors = new Paint[4];
				colors[0] = Color.BLUE; 
				colors[1] = Color.BROWN; 
				colors[2] = Color.GREEN; 
				colors[3] = Color.RED;
				levelName = "spikes.txt";
				randomOrCustom = "custom";
				window.close();
		});
    	
    	linesStage.setOnAction(e -> {

				colors = new Paint[4];
				colors[0] = Color.BLUE; colors[1] = Color.AQUA; colors[2] = Color.GREEN; colors[3] = Color.RED;
				levelName = "lines.txt";
				randomOrCustom = "custom";
				window.close();
		});
		
    	window.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                System.exit(0);
            }
    	});
    	
		window.showAndWait();

	}
	
	
}
