package Classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import javafx.animation.Timeline;
import javafx.util.converter.IntegerStringConverter;

public class ScoresTracker {

	private static String heartHighScore = "0";
	private static String spikesHighScore = "0";
	private static String linesHighScore = "0";
	private static String randomHighScore = "0";
	
	String levelSelection = "";
	int HighScore;
	
	/**
	 * This is the constructor for the ScoresWriter
	 * @param randomOrCustom - the current level selection
	 */
	public ScoresTracker(String randomOrCustom) {
		levelSelection = randomOrCustom;
	}
	/**
	 * This is the getter for individual level high score
	 * @param levelName - A String
	 * @return heartHighScore - a String
	 */
	
	public static String getHeartHighScore(String levelName) {
		
		String highScore = "0";
		
		Scanner read = null;
		try 
		{
			read = new Scanner(new File("LevelCompletionTracker.txt"));
			String fileContents = read.nextLine();
			read.close();
			String[] fileContentsInArray = fileContents.split(" ");
			if(levelName.equals("heart.txt")) {highScore = fileContentsInArray[10];} 
			if(levelName.equals("spikes.txt")){highScore = fileContentsInArray[12];}
			if(levelName.equals("lines.txt")){highScore = fileContentsInArray[14];}
			if(levelName.equals("random")){highScore = fileContentsInArray[16];}
			
		}
		catch(FileNotFoundException e) 
		{
			System.out.println(e);
		}
		
		
			
		return highScore;
	}
	
	
	

	/**
	 * This method is from retrieving high score of the whole game
	 * @return
	 */
	public static String getHighScoreAsString() 
	{
		Scanner read = null;
		try 
		{
			read = new Scanner(new File("LevelCompletionTracker.txt"));
			String fileContents = read.nextLine();
			read.close();
			String[] fileContentsInArray = fileContents.split(" ");
			
			
			return fileContentsInArray[8];
		}
		
		catch(FileNotFoundException e) 
		{
			System.out.println(e);
		}
		
		return "0";	
		
	}
	/**
	 * This method returns an array of file contents - each entity is a String
	 * @return String[] - file contents as string
	 */
	public static String[] getFileContentsArray() 
	{
		Scanner read = null;
		
		try 
		{
			read = new Scanner(new File("LevelCompletionTracker.txt"));
		
			String fileContents = read.nextLine();
			read.close();
			String[] fileContentsInArray = fileContents.split(" ");
			return fileContentsInArray;
		}
		
		catch(FileNotFoundException e)
		{
			System.out.println(e);
		}
		
		return new String[]{};
	}
	/**
	 * This method marks the current level as complete and writes this info to file
	 * Also saves the current score - high score
	 * @param score - current score
	 */
	public void levelWonUpdate(int score) {
		PrintWriter write = null;
		Scanner read = null;
	
		try 
		{
			read = new Scanner(new File("LevelCompletionTracker.txt"));
			
			String fileContents = read.nextLine();
			read.close();
			String[] fileContentsInArray = fileContents.split(" ");
		
			//Specific high scores + if level was completed
			if(MainMenu.getLevelName().equals("heart.txt")) fileContentsInArray[0] = "1" ;
			if(MainMenu.getLevelName().equals("heart.txt") && (Integer.parseInt(fileContentsInArray[10]) < (score)) )
			{ fileContentsInArray[10] = Integer.toString(score);
				heartHighScore = Integer.toString(score);}
			
			if(MainMenu.getLevelName().equals("spikes.txt")) fileContentsInArray[2] = "1";
			if(MainMenu.getLevelName().equals("spikes.txt") && (Integer.parseInt(fileContentsInArray[12]) < (score)) )
			{ fileContentsInArray[12] = Integer.toString(score);
				spikesHighScore = Integer.toString(score);}
			
			if(MainMenu.getLevelName().equals("lines.txt")) fileContentsInArray[4] = "1";
			if(MainMenu.getLevelName().equals("lines.txt") && (Integer.parseInt(fileContentsInArray[14]) < (score)) )
			{ fileContentsInArray[14] = Integer.toString(score);
				linesHighScore = Integer.toString(score);}
			
			if(MainMenu.getLevelName().equals("random")) fileContentsInArray[6] = "1";
			if(MainMenu.getLevelName().equals("random") && (Integer.parseInt(fileContentsInArray[16]) < (score)) )
			{ fileContentsInArray[16] = Integer.toString(score);
				randomHighScore = Integer.toString(score);}
			
			
			//General high score
			if (Integer.parseInt(fileContentsInArray[8]) < score) {
				fileContentsInArray[8] = Integer.toString(score);}
			
			//Level high score 10 12 14 16
			if (Integer.parseInt(fileContentsInArray[10]) < score) {
				fileContentsInArray[8] = Integer.toString(score);}
					
			fileContents = String.join(" ",fileContentsInArray);

			write = new PrintWriter("LevelCompletionTracker.txt");

			write.println(fileContents);
			
			write.close();
		}	
	
		catch(FileNotFoundException e)
		{
			System.out.println(e);
		}
	
		

	}


	public void scoreToFileUpdate(int score) 
	{
		PrintWriter write = null;
		Scanner read = null;
		
		try 
		{
			read = new Scanner(new File("LevelCompletionTracker.txt"));
			String fileContents = read.nextLine();
			read.close();
			String[] fileContentsInArray = fileContents.split(" ");
			
			IntegerStringConverter isc = new IntegerStringConverter();
			
			//If the old score is lower then the current score
			if (isc.fromString(fileContentsInArray[8]) < score)
			{
				fileContentsInArray[8] = Integer.toString(score);
			}
			
			if(MainMenu.getLevelName().equals("heart.txt") && (Integer.parseInt(fileContentsInArray[10]) < (score)) )
			{ fileContentsInArray[10] = Integer.toString(score);
				heartHighScore = Integer.toString(score);}
			
			if(MainMenu.getLevelName().equals("spikes.txt") && (Integer.parseInt(fileContentsInArray[12]) < (score)) )
			{ fileContentsInArray[12] = Integer.toString(score);
				spikesHighScore = Integer.toString(score);}
			
			if(MainMenu.getLevelName().equals("lines.txt") && (Integer.parseInt(fileContentsInArray[14]) < (score)) )
			{ fileContentsInArray[14] = Integer.toString(score);
				linesHighScore = Integer.toString(score);}
			
			if(MainMenu.getLevelName().equals("random") && (Integer.parseInt(fileContentsInArray[16]) < (score)) )
			{ fileContentsInArray[16] = Integer.toString(score);
				randomHighScore = Integer.toString(score);}
			
			
			fileContents = String.join(" ",fileContentsInArray);

			write = new PrintWriter("LevelCompletionTracker.txt");

			write.println(fileContents);
			
			write.close();
		}
		
		catch(FileNotFoundException e) 
		{
			System.out.println(e);
		}
	}
}
