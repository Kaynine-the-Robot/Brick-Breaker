package Classes;
import java.util.Scanner;
import java.awt.Point;
import Classes.*;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.Pane;

/**
 * This is the player class which contains the methods and attributes of the player's bat.
 * @author
 *
 */
public class Player extends AbstractObjects {
	private int score = 0;
	private boolean rFlag = false;
	private boolean lFlag = false;
	private boolean multiFlag = false;
	
	/**
	 * A constructor for creating a player object with a horizontal position (should stay in one veticle placement)
	 * @param x is the horizontal position of the player the begin in
	 */
	public Player(int x) 
		{
		this.setPosition(x, 14);
		this.setSymbol('P');
		}
	
	public boolean getMultiFlag()
	{
		return this.multiFlag;
	}
	
	public void setMultiFlag(boolean nFlag)
	{
		this.multiFlag = nFlag;
	}
	
	/**
	 * A getter returning the score of the game for winning
	 * @return is  an int representing the score of the game
	 */
	public int getScore()
	{
		return this.score;
	}
	
	/**
	 * A method for increasing the score by one
	 */
	public void increaseScore(int nInc, PerkDrop pD)
	{
		if(nInc > 0 && this.multiFlag)
		{
			this.score = this.score + (nInc * pD.getMulti());
		}
		else if(nInc > 0)
		{
			this.score = this.score + nInc;
		}
	}
	
	/**
	 * A method for increasing the score by one for TEXT GUI
	 */
	public void increaseScore(int nInc)
	{
			this.score = this.score++;
	}
	
	/**
	 * A getter for telling if the right arrow key is pressed
	 * @return is a boolean flag representing if the right arrow key is pressed
	 */
	public boolean getRFlag()
	{
		return rFlag;
	}
	
	/**
	 * A setter for setting if the right arrow key is pressed
	 * @param state is a boolean describing if the right arrow key is pressed
	 */
	public void setRFlag(boolean state)
	{
		rFlag = state;
	}
	
	/**
	 * A getter for telling if the left arrow key is pressed
	 * @return is a boolean flag representing if the left arrow key is pressed
	 */
	public boolean getLFlag()
	{
		return lFlag;
	}
	
	/**
	 * A setter for setting if the left arrow key is pressed
	 * @param state is a boolean describing if the left arrow key is pressed
	 */
	public void setLFlag(boolean state)
	{
		lFlag = state;
	}

	/** 
	 * A method that asks the user to move bar in direction 'R', 'L' or 'N'(none), and updates the bar position.
	 */
	public void moveBar(char d) 
	{
		if(d == 'L' && this.getPosition().getX() != 0)
		{
			updatePos(-1);
		}
		else if(d == 'R' && this.getPosition().getX() != 8)
		{
			updatePos(1);
		}
	}
	
}
	
	