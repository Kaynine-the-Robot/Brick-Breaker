package Classes;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Polygon;

public class CollisionObjects {

	private Rectangle[][] brickHitboxes; 
	private Rectangle barHitbox;
	private Circle ballHitbox;
	private ArrayList<Polygon> perkSprites = new ArrayList<Polygon>();
	
	public CollisionObjects(Rectangle barH, Circle ballH)
	{
		this.barHitbox = barH;
		this.ballHitbox = ballH;
	}
	
	public void checkBallAndBorders(Ball ball)
	{
		if (this.ballHitbox.getLayoutX() == (204-this.ballHitbox.getRadius()) || this.ballHitbox.getLayoutX() == (-206+ this.ballHitbox.getRadius()))
    	{
    		ball.horzCollision();
    	}
		if (this.ballHitbox.getLayoutY() == (-456 + this.ballHitbox.getRadius())) 
    	{
    		ball.vertCollision();
    	}
		if (this.ballHitbox.getLayoutY() == (46 - this.ballHitbox.getRadius())) 
    	{
    		ball.pauseBall();
    	}
	}
	
	public boolean checkBallAndBrickSides(int i, int j)
	{
		Rectangle rect = this.brickHitboxes[i][j];
		if(this.ballHitbox.getBoundsInParent().intersects(rect.getX(), rect.getY() + 2.0, 1.0, rect.getHeight() - 4.0) || 
				ballHitbox.getBoundsInParent().intersects(rect.getX() + rect.getWidth(), rect.getY() + 2.0, 1.0, rect.getHeight() - 4.0))
		{
			return true;
		}
		return false;
		//return b.getBoundsInParent().intersects((this.rect).getBoundsInParent());
	}
	
	public boolean checkBallAndBrickTopAndBottom(int i, int j)
	{
		Rectangle rect = this.brickHitboxes[i][j];
		if(ballHitbox.getBoundsInParent().intersects(rect.getX() + 2.0, rect.getY(), rect.getWidth() - 4.0, 1.0) || 
				ballHitbox.getBoundsInParent().intersects(rect.getX() + 2.0, rect.getY() + rect.getHeight(), rect.getWidth() - 4.0, 1.0))
			{ //The 2.0 and 4.0 are offsets to avoid clipping in false collisions
			 return true;
			}
		return false;
		//return b.getBoundsInParent().intersects((this.rect).getBoundsInParent());
	}
	
	public boolean checkPerkAndPlayer(int index)
	{
		if(this.perkSprites.get(index).getBoundsInParent().intersects(this.barHitbox.getBoundsInParent()))
		{
			return true;
		}
		return false;
	}
	
	public boolean checkBallAndPlayerSides()
	{
		if(this.ballHitbox.getBoundsInParent().intersects(this.barHitbox.getX(), this.barHitbox.getY() + 2.0, 1.0, this.barHitbox.getHeight() - 4.0) || 
			this.ballHitbox.getBoundsInParent().intersects(this.barHitbox.getX() + this.barHitbox.getWidth(), this.barHitbox.getY() + 2.0, 1.0, this.barHitbox.getHeight() - 4.0))
		{
			return true;
		}
		return false;
	}
	
	public boolean checkBallAndPlayerTopAndBottom()
	{
		if(this.ballHitbox.getBoundsInParent().intersects(this.barHitbox.getX() + 2.0, this.barHitbox.getY(), this.barHitbox.getWidth() - 4.0, 1.0) || 
				this.ballHitbox.getBoundsInParent().intersects(this.barHitbox.getX() + 2.0, this.barHitbox.getY() + this.barHitbox.getHeight(), this.barHitbox.getWidth() - 4.0, 1.0))
		{
			return true;
		}
		return false;
	}
	
	public boolean checkBallAndPlayerCorners()
	{
		this.ballHitbox.setLayoutY(this.ballHitbox.getLayoutY() + 2);
		this.ballHitbox.setLayoutX(this.ballHitbox.getLayoutX() + 2);
		return true;
	}
	
	public void moveBallInWIndow(Ball ballMovement)
	{
		this.ballHitbox.setLayoutX(this.ballHitbox.getLayoutX() + ballMovement.getHorzMovement());
    	this.ballHitbox.setLayoutY(this.ballHitbox.getLayoutY() + ballMovement.getVertMovement());
    	ballMovement.setPosition((int)ballMovement.getPosition().getX() + ballMovement.getHorzMovement(), 
    			(int) ballMovement.getPosition().getY() + ballMovement.getVertMovement());
	}
	
	public void movePlayerInWindow(Player barMovement)
	{
		//If the right key is down
		if(barMovement.getRFlag() && this.barHitbox.getX() < 340)
    	{
    		this.barHitbox.setX(this.barHitbox.getX() + 1);
    	}
    	
    	//If the left key is down
    	if(barMovement.getLFlag() && this.barHitbox.getX() > 0)
    	{
    		this.barHitbox.setX(this.barHitbox.getX() - 1);
    	}
	}
	
	public void moveAllPerksInWindow(PerkDrop pD)
	{
		for(int i = 0; i < this.perkSprites.size(); i++)
		{
			this.perkSprites.get(i).setLayoutY(this.perkSprites.get(i).getLayoutY() + pD.getFallSpeed());
			//(pD.getFallSpeed());
		}
	}
	
	public void setBrickHitBoxes(Block[][] nArray)
	{
		this.brickHitboxes = new Rectangle[nArray.length][nArray[0].length];
		for(int i = 0; i < nArray.length; i++)
		{
			for(int j = 0; j < nArray[i].length; j++)
			{
				this.brickHitboxes[i][j] = new Rectangle(nArray[i][j].getPosition().getX(), nArray[i][j].getPosition().getY(),
						nArray[i][j].getWidth(), nArray[i][j].getHeight());
			}
		}
	}
	
	public void addBlockArrayToRoot(Pane root) {
		//Iterating through the array of bricks and adding them to the graphics
		for(int i = 0; i < this.brickHitboxes.length; i++)
		{
			for(int j = 0; j < this.brickHitboxes[i].length; j++)
			{
				root.getChildren().add(this.brickHitboxes[i][j]);
			}	
		}	
		
	}
	
	public void removeBlockFromRoot(Pane root, int i, int j)
	{
		root.getChildren().remove(this.brickHitboxes[i][j]);
	}
	
	public void addPerkToRoot(Pane root, double i, double j)
	{
		Polygon perk = new Polygon();
		perk.getPoints().addAll(new Double[] { i,j, i+10, j+10, i-10, j+10 });
		perk.setStroke(Color.BLACK);
		perk.setFill(Color.GREEN);
		root.getChildren().add(perk);
		this.perkSprites.add(perk);
	}
	
	public void colorBrickInArray(int i, int j, Paint[] colors)
	{
			int rand = ThreadLocalRandom.current().nextInt(0, colors.length);
			this.brickHitboxes[i][j].setStroke(Color.BLACK);
			switch(rand)
			{
			case 0: this.brickHitboxes[i][j].setFill(colors[rand]); break;
			case 1: this.brickHitboxes[i][j].setFill(colors[rand]); break;
			case 2: this.brickHitboxes[i][j].setFill(colors[rand]); break;
			case 3: this.brickHitboxes[i][j].setFill(colors[rand]); break;
			default: this.brickHitboxes[i][j].setFill(colors[0]); break;
			}
		//this.brickHitboxes[i][j].setFill(colors[ThreadLocalRandom.current().nextInt(0, colors.length)]);
	}
	
	/**
	 * This is a method for checking ball and brick collision
	 * @param root
	 * @param ballMovement
	 * @param barMovement
	 * @param board
	 */
	public void checkBallBrickCollisionTrigger(Pane root, Ball ballMovement, Player barMovement, Board board, PerkDrop pD) {
		
		for(int i = 0; i < this.brickHitboxes.length; i++) 
    	{
    		for(int j = 0; j < this.brickHitboxes[i].length; j++)
    		{
    			if((ballMovement.getHitBrick() == false && board.getBlockArrayAtIndex(i,j).getVisibility() && 
    					this.checkBallAndBrickSides(i, j)))
    			{
    				if(board.getBlockArrayAtIndex(i, j).decreaseHealth())
    				{
    					if(board.getBlockArrayAtIndex(i, j).getSymbol() == 'N')
    					{
    						barMovement.increaseScore(1, pD);
    					}
    					else if(board.getBlockArrayAtIndex(i, j).getSymbol() == 'H')
    					{
    						barMovement.increaseScore(2, pD);
    						int rand = ThreadLocalRandom.current().nextInt(3, 4);
    						if (rand == 3)
    						{
    							double x = this.brickHitboxes[i][j].getX() + (this.brickHitboxes[i][j].getWidth()/2);
    							double y = this.brickHitboxes[i][j].getY();
    							this.addPerkToRoot(root, x, y);
    							pD.choosePerk();
    							//pD.applyPerk(pD.choosePerk(), barMovement);
    						}
    					}
    					root.getChildren().remove(this.brickHitboxes[i][j]);
    					board.removeBlockAtIndex(root, i, j, this);
    				}
    				ballMovement.horzCollision();
    				ballMovement.setHitBrick(true);
    			}
    			else if((ballMovement.getHitBrick() == false && board.getBlockArrayAtIndex(i,j).getVisibility() && 
    					this.checkBallAndBrickTopAndBottom(i, j)))
    			{
    				if(board.getBlockArrayAtIndex(i, j).decreaseHealth())
    				{
    					root.getChildren().remove(this.brickHitboxes[i][j]);
    					board.removeBlockAtIndex(root, i, j, this);
    					if(board.getBlockArrayAtIndex(i, j).getSymbol() == 'N')
    					{
    						barMovement.increaseScore(1, pD);
    					}
    					else if(board.getBlockArrayAtIndex(i, j).getSymbol() == 'H')
    					{
    						barMovement.increaseScore(2, pD);
    					}
    				}
    				ballMovement.vertCollision();
    				ballMovement.setHitBrick(true);
    			}
    		}
    	}       
		
	}
	
	/**
	 * This is a method for checking if the ball (Circle Object) collides with the player (Rectangle Object)
	 * @param root is the Pane object holding the game objects
	 * @param ball Is the Circle Object for position
	 * @param bM is the Ball object for movement
	 * @param bar is the Rectangle object for movement and position
	 * @param pM is a Player Object for the collides method
	 */
	public void checkBallPlayerCollisionTrigger(Pane root, Ball bM, Player pM)
	{
		if((root.getChildren().contains(this.barHitbox) && (this.checkBallAndPlayerSides())))
    	{
			if(true)
			{
				
			}
			if(pM.getLFlag())
			{
				this.ballHitbox.setLayoutX(this.ballHitbox.getLayoutX() - 2);
				this.ballHitbox.setLayoutY(this.ballHitbox.getLayoutY());
				bM.setPosition((int) bM.getPosition().getX() - 2, (int) bM.getPosition().getY());
			}
			else if(pM.getRFlag())
			{
				this.ballHitbox.setLayoutX(this.ballHitbox.getLayoutX() + 2);
				this.ballHitbox.setLayoutY(this.ballHitbox.getLayoutY());
				bM.setPosition((int) bM.getPosition().getX() + 2, (int) bM.getPosition().getY());
			}
    		bM.horzCollision();
    	} 
		if((root.getChildren().contains(this.barHitbox) && (this.checkBallAndPlayerTopAndBottom())))
		{
			if(pM.getLFlag())
			{
				this.ballHitbox.setLayoutX(this.ballHitbox.getLayoutX() + 2);
				this.ballHitbox.setLayoutY(this.ballHitbox.getLayoutY());
				bM.setPosition((int) bM.getPosition().getX() + 2, (int) bM.getPosition().getY());
			}
			else if(pM.getRFlag())
			{
				this.ballHitbox.setLayoutX(this.ballHitbox.getLayoutX() - 2);
				this.ballHitbox.setLayoutY(this.ballHitbox.getLayoutY());
				bM.setPosition((int) bM.getPosition().getX() - 2, (int) bM.getPosition().getY());
			}
			bM.vertCollision();
		}
	}
	
	/*
	public void PerkAndPlayerCollision(Pane root, Player pM, PerkDrop pD)
	{
		for(int i = 0; i < this.perkSprites.size(); i++)
		{
			if(this.checkPerkAndPlayer(i))
			{
				pD.applyPerk(pD.getLowestPerk(), pM);
			}
		}
		
	}
	
	public void PerkAndBottomCollision(Pane root, PerkDrop pD)
	{
		for(int i = 0; i < this.perkSprites.size(); i++)
		{
			if(this.perkSprites.get(i).getLayoutY() >= 30)
			{
				root.getChildren().remove(this.perkSprites.get(i));
				pD.removeLowestPerk();
			}
		}
	}
	*/
	
	public void checkPerkCollisions(Pane root, Player pM, PerkDrop pD)
	{
		Paint[] colors = new Paint[4];
		colors[0] = Color.BLUE; colors[1] = Color.AQUA; colors[2] = Color.GREEN; colors[3] = Color.RED;
		int rand = ThreadLocalRandom.current().nextInt(0, 4);
		for(int i = 0; i < this.perkSprites.size(); i++)
		{
			this.perkSprites.get(i).setFill(colors[rand]);
			if(this.checkPerkAndPlayer(i))
			{
				pD.applyPerk(pD.getLowestPerk(), pM);
				root.getChildren().remove(this.perkSprites.get(i));
				pD.removeLowestPerk();
				this.perkSprites.remove(i);
			}
			else if(this.perkSprites.get(i).getLayoutY() >= 470)
			{
				root.getChildren().remove(this.perkSprites.get(i));
				pD.removeLowestPerk();
				this.perkSprites.remove(i);
			}
		}
	}
	
}
