package Classes;
import java.util.Scanner;
import java.awt.Point;
import Classes.*;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

/**
 * This is the player class which contains the methods and attributes of the player's bat.
 * @author
 *
 */
public class Player extends AbstractObjects {
	private int score = 0;
	private int lives = 3;
	private boolean rFlag = false;
	private boolean lFlag = false;
	private boolean multiFlag = false;
	private boolean moveFlag = true;
	private long multiTimer;
	private long startMultiTime;
	private double accelAmount = 1.0;
	private Image[] barSprites = new Image[5];
    private int barPositionFlag;
	
	/**
	 * A constructor for creating a player object with a horizontal position (should stay in one veticle placement)
	 * @param x is the horizontal position of the player the begin in
	 */
	public Player(int x) 
		{
		this.setPosition(x, 14);
		this.setSymbol('P');
		}
	
	public Player(double x, Image barLeft, Image barMiddleLeft, Image bar, Image barMiddleRight, Image barRight)
	{
		this.setPosition(x, 950);
		this.setSymbol('P');
		barSprites[0] = barLeft; barSprites[1] = barMiddleLeft; barSprites[2] = bar; barSprites[3] = barMiddleRight; barSprites[4] = barRight;
	}
	    
	public double getAccelAmount()
	{
		return this.accelAmount;
	}
	
	public double accelerate()
	{
		return this.accelAmount = this.accelAmount + 0.01;
	}
	
	public void resetAccelerate()
	{
		this.accelAmount = 1;
	}
	
	public Image getBarSpritesAtIndex(int index)
    {
    	return barSprites[index];
    }
    
    public int getPositionFlag()
    {
    	return this.barPositionFlag;
    }
    
    public void setPositionFlag()
    {
    	switch(this.barPositionFlag)
    	{
    	case 0: this.barPositionFlag = 1; break;
    	case 1: if(this.rFlag) {this.barPositionFlag = 2;} 
    			else if(this.lFlag) {this.barPositionFlag = 0;}; break;
    	case 2: if(this.rFlag) {this.barPositionFlag = 3;} 
				else if(this.lFlag) {this.barPositionFlag = 1;}; break;
    	case 3: if(this.rFlag) {this.barPositionFlag = 4;} 
				else if(this.lFlag) {this.barPositionFlag = 2;}; break;
    	case 4: this.barPositionFlag = 3; break;
    	default: this.barPositionFlag = 1; break;
    	}
    }
	
	public boolean getMultiFlag()
	{
		return this.multiFlag;
	}
	
	public void setMultiFlag(boolean nFlag)
	{
		this.multiFlag = nFlag;
	}
	
	public boolean getMoveFlag()
	{
		return this.moveFlag;
	}
	
	public void setMoveFlag(boolean nFlag)
	{
		this.moveFlag = nFlag;
	}
	
	public long getMultiTimer()
	{
		return this.multiTimer;
	}
	
	public void setMultiTimer()
	{
		this.startMultiTime = System.currentTimeMillis() / 1000;
		long current = 60 - ((System.currentTimeMillis() / 1000) - this.startMultiTime);
		this.multiTimer = (int) current;
	}
	
	public void updateCurrentTime(PerkDrop pD)
	{
		if(this.multiTimer != 0)
		{
			this.multiTimer = (int) (60 - ((System.currentTimeMillis() / 1000) - this.startMultiTime));
		}
		else if (this.multiTimer == 0)
		{
			if(pD.getMulti() <=2)
			{
				this.multiFlag = false;
				this.startMultiTime = 0;
				this.multiTimer = 0;
			}
			else
			{
				pD.lowerMulti();
				this.setMultiTimer();
			}
			
		}
	}
	
	/**
	 * A getter returning the score of the game for winning
	 * @return is  an int representing the score of the game
	 */
	public int getScore()
	{
		return this.score;
	}
	
	public int getLives()
	{
		return this.lives;
	}
	
	public void loseLife()
	{
		lives--;
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
	
	