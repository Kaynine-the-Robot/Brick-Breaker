package Classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import javafx.animation.Timeline;
import javafx.util.converter.IntegerStringConverter;

public class ScoresTracker {

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
	
	public void levelWonUpdate(int score) {
		PrintWriter write = null;
		Scanner read = null;
	
		try 
		{
			read = new Scanner(new File("LevelCompletionTracker.txt"));
			
			String fileContents = read.nextLine();
			read.close();
			String[] fileContentsInArray = fileContents.split(" ");
		
			if(MainMenu.getLevelName().equals("heart.txt")) fileContentsInArray[0] = "1";
			if(MainMenu.getLevelName().equals("spikes.txt")) fileContentsInArray[2] = "1";
			if(MainMenu.getLevelName().equals("lines.txt")) fileContentsInArray[4] = "1";
			if(MainMenu.getLevelName().equals("random")) fileContentsInArray[6] = "1";
			
			//General high score
			fileContentsInArray[8] = Integer.toString(score);
					
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
