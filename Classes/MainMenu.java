package Classes;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainMenu {

	private static Paint[] colors ;
	private static String levelName;
	private static String randomOrCustom;
	private static Scene menu;
	
	public static Paint[] getColors(){
		return colors;
	}
	
	public static String getLevelName() {
		return levelName;
	}
	
	public static String getRandomOrCustom() {
		return randomOrCustom;
	}
	
	public static Scene getMenu() {
		return menu;
	}
	
	public static void display() {
		
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
				
		Stage window = new Stage();
		window.setTitle("Welcome to the Block Breaker!");
		window.initModality(Modality.APPLICATION_MODAL);
		
		Pane menuRoot = new Pane();
		menu = new Scene(menuRoot, 330, 250, Color.SKYBLUE);
		
		window.setScene(menu);
		
		menuRoot.getChildren().add(heartStage);
		menuRoot.getChildren().add(randomStage);
		menuRoot.getChildren().add(linesStage);
		menuRoot.getChildren().add(spikesStage);
		
		
		//Sets up both menu buttons
		randomStage.setOnAction(e -> {

				colors = new Paint[4];
				colors[0] = Color.BLUE; colors[1] = Color.BROWN; colors[2] = Color.GREEN; colors[3] = Color.RED;
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
		
		
		window.showAndWait();

	}
	
	
}
