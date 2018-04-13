package Classes;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;
import javafx.util.Duration;

public class CollisionObjects {

	private ImageView[][] brickHitboxes; 
	private ImageView barHitbox;
	private ImageView ballHitbox;
	private ArrayList<Polygon> perkSprites = new ArrayList<Polygon>();
	final int COLLISION_OFFSET = 1;
	final double BACKGROUND_WIDTH;
	final double BACKGROUND_HEIGHT;
	
	/**
	 * Constructor for a Collision Object
	 * @param barH is an ImageView object representing the player bar's object and hitbox in the game. Used for collision of bar vs ball, perks, and sides of screen
	 * @param ballH is an ImageView object representing the ball's object and hitbox in the game. Used for collision of ball vs blocks, bar, and sides of screen
	 * @param BACKGROUND_WIDTH is a constant for the window width. Used for general values of spawning and collisions
	 * @param BACKGROUND_HEIGHT is a constant for the window height. Used for general values of spawning and collisions
	 */
	public CollisionObjects(ImageView barH, ImageView ballH, double BACKGROUND_WIDTH, double BACKGROUND_HEIGHT)
	{
		this.barHitbox = barH;
		this.ballHitbox = ballH;
		this.BACKGROUND_WIDTH = BACKGROUND_WIDTH;
		this.BACKGROUND_HEIGHT = BACKGROUND_HEIGHT;
	}
	
	/**
	 * A method for returning the windows height which is a constant double
	 * @return the double attribute of the constant for the window background height
	 */
	public double getBackHeight()
	{
		return this.BACKGROUND_HEIGHT;
	}
	
	/**
	 * A method for returning the windows width which is a constant double
	 * @return the double attribute of the constant for the window background width
	 */
	public double getBackWidth()
	{
		return this.BACKGROUND_WIDTH;
	}
	
	/**
	 * A method for checking and appropriately changing the ball's sprite according to it's position on the screen
	 * @param ball is a Ball object for retrieving it's sprite and checking then changing the position flag of ball if it needs to be
	 */
	public void checkBallImageSwitch(Ball ball)
	{
		double diff = this.BACKGROUND_WIDTH/5; //The windows background divided in 5ths to split it into 5 sections evenly
		for(int i = 0; i < 5; i++)
		{
			if(this.ballHitbox.getX() >= (diff * i) && this.ballHitbox.getX() < diff * (i+1) && ball.getPositionFlag() != i)
			{
				this.ballHitbox.setImage(ball.getBallSpritesAtIndex(i)); //Changing the sprite to the correct one, they are ordered to fit with the i index
				ball.setPositionFlag(); //The ball will set to a position flag telling which section of the screen it's currently in
			}
		}
	}
	
	/**
	 * This method is for checking the current image of the player bar and changing it to the correct current sprite if neccessary
	 * @param bar if the Player object passed for it's position flag and and sprites
	 */
	public void checkBarImageSwitch(Player bar)
	{
		
		double diff = this.BACKGROUND_WIDTH/5; //The windows background divided in 5ths to split it into 5 sections evenly
		for(int i = 0; i < 5; i++)
		{
			if(this.barHitbox.getX() >= (diff * i) && this.barHitbox.getX() < diff * (i+1) && bar.getPositionFlag() != i) //Changing the sprite to the correct one, they are ordered to fit with the i index
			{
				this.barHitbox.setImage(bar.getBarSpritesAtIndex(i)); //Changing the sprite to the correct one, they are ordered to fit with the i index
				bar.setPositionFlag(); //The bar will set to a position flag telling which section of the screen it's currently in
			}
		}
	}
	
	/**
	 * This method is for checking collisions between the ball and the borders of the window
	 * @param ball is a Ball object passed for it's collisions, reset, and pauseball methods
	 * @param player is a Player object passed through for it's lives and stopping it's movement
	 * @param mP is a MediaPlayer object which will play a sounds when the ball collides with a block
	 */
	public void checkBallAndBorders(Ball ball, Player player, MediaPlayer impact, MediaPlayer death)
	{
		if(impact.getCurrentCount()==1)
		{
			impact.stop();
		}
		impact.setCycleCount(MediaPlayer.INDEFINITE);
		death.setCycleCount(1);
		if (this.ballHitbox.getX() + this.ballHitbox.getFitWidth() - this.COLLISION_OFFSET >= this.BACKGROUND_WIDTH || this.ballHitbox.getX() <= 0 - this.COLLISION_OFFSET) //Ball colliding with the right or left borders
    	{
    		ball.horzCollision(); //Will change the balls direction to the opposite horizontal direction
    		impact.play();
    	}
		if (this.ballHitbox.getY() <= (0 - this.COLLISION_OFFSET)) //Ball colliding with the top of the window
    	{
    		ball.vertCollision(); //Will change the balls direction to the opposite vertical direction
    		impact.play();
    	}
		if (this.ballHitbox.getY() >= (this.BACKGROUND_HEIGHT - this.ballHitbox.getFitHeight())) //Ball colliding with the bottom of screen, aka lost
    	{
			if(player.getLives() > 0) //Method checking if there's positive lives left, aka player is still alive even though a ball was lost
			{
				ballHitbox.setX(this.barHitbox.getX()+this.barHitbox.getFitWidth()/2); //Reseting the balls position to the middle of the screens width
				ballHitbox.setY(this.barHitbox.getY() - ballHitbox.getFitHeight() - 5); //Reseting the balls position to be relatively above the bar no matter the screen size to avoid clipping into each other
				ball.reset(); //Reseting motion and speed
				player.loseLife(); //Taking one life away
				impact.play();
			}
			else
			{
				death.play();
	    		ball.pauseBall(); //Stopping the balls movement as the player has lost
	    		player.setMoveFlag(false); //Stopping the player's bar movement as the play has lost
			}
    	}
	}
	
	/**
	 * This method is for checking for the collisions between the ball and the brick sides specifically
	 * @param i in an integer representing an index in the first layer in the 2D array
	 * @param j in an integer representing an index in the second nested layer in the 2D array 
	 * @return a boolean representing if such a collision does happen or not
	 */
	public boolean checkBallAndBrickSides(int i, int j)
	{
		ImageView rect = this.brickHitboxes[i][j]; //Using the i and j variables to access a particular blocks hitbox and set it as a variable
		if(this.ballHitbox.getBoundsInParent().intersects(rect.getBoundsInParent().getMinX(), rect.getY() + this.COLLISION_OFFSET/2, 1.0, rect.getFitHeight() - this.COLLISION_OFFSET) || 
				ballHitbox.getBoundsInParent().intersects(rect.getBoundsInParent().getMaxX(), rect.getY() + this.COLLISION_OFFSET/2, 1.0, rect.getFitHeight() - this.COLLISION_OFFSET)) //Ball colliding with left or right sides
		{ //The collision offset is for making the ball travel slightly farther into the block to look better, it moves and shortens the collision trigger lines
			return true;
		}
		return false;
	}
	
	/**
	 * This method is for checking for the collision between the ball and the bottom and top of the bricks
	 * @param i in an integer representing an index in the first layer in the 2D array
	 * @param j in an integer representing an index in the second nested layer in the 2D array 
	 * @return a boolean representing if such a collision does happen or not
	 */
	public boolean checkBallAndBrickTopAndBottom(int i, int j)
	{
		ImageView rect = this.brickHitboxes[i][j]; //Using the i and j variables to access a particular blocks hitbox and set it as a variable
		if(ballHitbox.getBoundsInParent().intersects(rect.getX() + this.COLLISION_OFFSET/2, rect.getY(), rect.getFitWidth() - this.COLLISION_OFFSET, 1.0) || 
				ballHitbox.getBoundsInParent().intersects(rect.getX() + this.COLLISION_OFFSET/2, rect.getY() + rect.getFitHeight(), rect.getFitWidth() - this.COLLISION_OFFSET, 1.0)) //Ball colliding with top or bottom
			{ //The collision offset is for making the ball travel slightly farther into the block to look better, it moves and shortens the collision trigger lines
			 return true;
			}
		return false;
	}
	
	/**
	 * A method for checking if the ball collides with the sides of the player's bar
	 * @return A boolean representing if the collision happened or not
	 */
	public boolean checkBallAndPlayerSides()
	{
		double yMax = this.ballHitbox.getY() + this.ballHitbox.getFitHeight()/2; //Getting the Y-Center of the ball, which is the highest the ball can be for a side collision to happen
		double xMaxLeft = this.ballHitbox.getX() + this.ballHitbox.getFitWidth(); double xMaxRight = this.ballHitbox.getX(); //Getting the X-Coordinates where the ball will intersect with the bar
		if(yMax >= this.barHitbox.getY() && xMaxLeft < this.barHitbox.getX() || yMax >= this.barHitbox.getY() && xMaxRight > this.barHitbox.getX()+this.barHitbox.getFitWidth()) //Checking if the Y coordinate and the appropriate X side are in the right position to collide
		{
			return true;
		}
		return false;
	}
	
	/**
	 * A method for checking if the ball collides with the player top or bottom
	 * @return A boolean representing if the collision happened or not
	 */
	public boolean checkBallAndPlayerTop()
	{
		if(this.ballHitbox.getY()+this.ballHitbox.getFitHeight() >= this.barHitbox.getY() && //Making sure the ball is above the player bar and then between the two ends where corner collisions can happen
				this.ballHitbox.getX()+this.ballHitbox.getFitWidth()/2 > this.barHitbox.getX() && 
				this.ballHitbox.getX()+this.ballHitbox.getFitWidth()/2 < this.barHitbox.getX()+this.barHitbox.getFitWidth())
		{
			this.ballHitbox.setY(this.ballHitbox.getY()-2); //Setting the Y-Coordinate of the ball back 2 pixels to avoid some clipping
			return true;
		}
		return false;
	}
	
	/**
	 * A method for checking if the ball collides with the player bars corner to bounce straight back
	 * @return a boolean representing if the collision happens or not
	 */
	public boolean checkBallAndPlayerCorners(Ball ball)
	{
		double yMax = this.ballHitbox.getY() + this.ballHitbox.getFitHeight()/2; //Getting the Y-Center of the ball, which is the lowest the ball can be for a corner collision to happen
		double deltaXLeft = this.barHitbox.getX() - (this.ballHitbox.getX()+this.ballHitbox.getFitWidth()/2); double deltaY = this.barHitbox.getY() - (this.ballHitbox.getY()+this.ballHitbox.getFitHeight()/2);
		double deltaXRight = (this.barHitbox.getX()+this.barHitbox.getFitWidth()) - (this.ballHitbox.getX()+this.ballHitbox.getFitWidth()/2); //Delta's are the difference between points of the corners and the ball's center
		double distanceLeft = Math.sqrt((deltaXLeft*deltaXLeft)+(deltaY*deltaY)); double distanceRight = Math.sqrt((deltaXRight*deltaXRight)+(deltaY*deltaY)); //Using the delta's to get distances between points
		
		if(this.ballHitbox.getX()+this.ballHitbox.getFitWidth()/2 <= this.barHitbox.getX() && distanceLeft <= 14 && yMax < this.barHitbox.getY()) 
			//Checking the ball if over the edge far enough, the distance is the radius (14), and that the ball bottom is a little lower than the bar top
		{
			this.ballHitbox.setX(this.ballHitbox.getX()-2); //Moving the ball away to prevent clipping
			this.ballHitbox.setY(this.ballHitbox.getY()-2);
			return true;
		}
		else if(this.ballHitbox.getX()+this.ballHitbox.getFitWidth()/2 >= this.barHitbox.getX()+this.barHitbox.getFitWidth() && distanceRight <= 14 && yMax < this.barHitbox.getY())
		{ //Checking the ball if over the edge far enough, the distance is the radius (14), and that the ball bottom is a little lower than the bar top
			this.ballHitbox.setX(this.ballHitbox.getX()+2); //Moving the ball away to prevent clipping
			this.ballHitbox.setY(this.ballHitbox.getY()-2);
			return true;
		}
		return false;
	}
	
	/**
	 * A method for checking if a falling Perk and a Player collide
	 * @param index is an integer for accessing the correct index of the array list of the falling perks
	 * @return a boolean representing if the collision happened or not
	 */
	public boolean checkPerkAndPlayer(int index)
	{
		if(this.perkSprites.get(index).getBoundsInParent().intersects(this.barHitbox.getBoundsInParent()))
		{
			return true;
		}
		return false;
	}
	
	/**
	 * This method is for moving the visual ball in the window based on the attributes of the Ball object
	 * @param ballMovement is the Ball object used for it's horz/vertMovement variables to move
	 */
	public void moveBallInWIndow(Ball ballMovement)
	{
		this.ballHitbox.setX(this.ballHitbox.getX() + ballMovement.getHorzMovement());
    	this.ballHitbox.setY(this.ballHitbox.getY() + ballMovement.getVertMovement());
    	ballMovement.setPosition((int)ballMovement.getPosition().getX() + ballMovement.getHorzMovement(), 
    			(int) ballMovement.getPosition().getY() + ballMovement.getVertMovement());
	}
	
	/**
	 * This method is for moving the visual bar in the window based on the attributes of the Player object
	 * @param barMovement
	 */
	public void movePlayerInWindow(Player barMovement, Ball ball)
	{
		//If the right key is down
		if(barMovement.getRFlag() && this.barHitbox.getX() < (this.BACKGROUND_WIDTH - this.barHitbox.getFitWidth()) && barMovement.getMoveFlag()) //Uses the constant of the screen width to prevent going beyond
    	{
			if(ball.getGameStartState())//If the ball is in the game start state, make it move right with the bar
			{
				ball.setPosition(ball.getPosition().getX() + barMovement.accelerate(), ball.getPosition().getY());
				this.ballHitbox.setX(this.ballHitbox.getX() + barMovement.accelerate());
			}
			this.barHitbox.setX(this.barHitbox.getX() + barMovement.accelerate());
    	}
    	
    	//If the left key is down
		if(barMovement.getLFlag() && this.barHitbox.getX() > 0 && barMovement.getMoveFlag()) //0 for the left side
    	{
			if(ball.getGameStartState())//If the ball is in the game start state, make it move left with the bar
			{
				ball.setPosition(ball.getPosition().getX() - barMovement.accelerate(), ball.getPosition().getY());
				this.ballHitbox.setX(this.ballHitbox.getX() - barMovement.accelerate());
			}
			this.barHitbox.setX(this.barHitbox.getX() - barMovement.accelerate());
    	}
		
	}
	
	/**
	 * This method goes through all active falling Perks (in a list) on screen and makes them move down
	 * @param pD is a PerkDrop object used for the attribute of falling speed
	 */
	public void moveAllPerksInWindow(PerkDrop pD)
	{
		for(int i = 0; i < this.perkSprites.size(); i++)
		{
			this.perkSprites.get(i).setLayoutY(this.perkSprites.get(i).getLayoutY() + pD.getFallSpeed());
		}
	}
	
	/**
	 * This method makes the 2D array of viewable Sprites to collide with by using the built array from Board of a 2D array of Blocks/subBlocks
	 * @param nArray is a 2D Block array used for making another 2D array of the same size but with viewable objects that can collide
	 */
	public void setBrickHitBoxes(Block[][] nArray)
	{
		this.brickHitboxes = new ImageView[nArray.length][nArray[0].length];
		for(int i = 0; i < nArray.length; i++)
		{
			for(int j = 0; j < nArray[i].length; j++)
			{
				if(i <= nArray.length/4 && nArray[i][j] instanceof Normal_Block) //If there's a Normal Block in the left side of the array
				{
					int rand = ThreadLocalRandom.current().nextInt(0, 2);
					if(rand == 0) //Choosing between red and green blocks
					{
						this.brickHitboxes[i][j] = new ImageView(nArray[i][j].getBrickSpritesAtIndex(0)); //Red sprite for left
						
					}
					else
					{
						this.brickHitboxes[i][j] = new ImageView(nArray[i][j].getBrickSpritesAtIndex(3)); //Green sprite for left
					}
					this.brickHitboxes[i][j].setFitHeight(nArray[i][j].getHeight()); this.brickHitboxes[i][j].setFitWidth(nArray[i][j].getWidth()); //Using the attributes of the Block to fill out the ImageView object
					this.brickHitboxes[i][j].setX(nArray[i][j].getPosition().getX()); this.brickHitboxes[i][j].setY(nArray[i][j].getPosition().getY());
				}
				else if(i > nArray.length/4 && i < nArray.length -(nArray.length/4 + 1) && nArray[i][j] instanceof Normal_Block) //Middle of the screen/array
				{
					int rand = ThreadLocalRandom.current().nextInt(0, 2);
					if(rand == 0) //Choosing between red and green blocks
					{
						this.brickHitboxes[i][j] = new ImageView(nArray[i][j].getBrickSpritesAtIndex(1)); //Red sprite for middle
					}
					else
					{
						this.brickHitboxes[i][j] = new ImageView(nArray[i][j].getBrickSpritesAtIndex(4)); //Green sprite for middle
					}
					this.brickHitboxes[i][j].setFitHeight(nArray[i][j].getHeight()); this.brickHitboxes[i][j].setFitWidth(nArray[i][j].getWidth()); //Using the attributes of the Block to fill out the ImageView object
					this.brickHitboxes[i][j].setX(nArray[i][j].getPosition().getX()); this.brickHitboxes[i][j].setY(nArray[i][j].getPosition().getY());
				}
				else if(i >= (nArray.length- (nArray.length/4 + 1)) && nArray[i][j] instanceof Normal_Block) //Right side of the screen/array
				{
					int rand = ThreadLocalRandom.current().nextInt(0, 2);
					if(rand == 0) //Choosing between red and green blocks
					{
						this.brickHitboxes[i][j] = new ImageView(nArray[i][j].getBrickSpritesAtIndex(2)); //Red sprite for right
					}
					else
					{
						this.brickHitboxes[i][j] = new ImageView(nArray[i][j].getBrickSpritesAtIndex(5)); //Green sprite for right
					}
					this.brickHitboxes[i][j].setFitHeight(nArray[i][j].getHeight()); this.brickHitboxes[i][j].setFitWidth(nArray[i][j].getWidth()); //Using the attributes of the Block to fill out the ImageView object
					this.brickHitboxes[i][j].setX(nArray[i][j].getPosition().getX()); this.brickHitboxes[i][j].setY(nArray[i][j].getPosition().getY());
				}
				else //If the Block is a Hard_Block
				{
					this.brickHitboxes[i][j] = new ImageView(nArray[i][j].getBrickSpritesAtIndex(0)); //Only one starting hardblock image
					this.brickHitboxes[i][j].setFitHeight(nArray[i][j].getHeight()); this.brickHitboxes[i][j].setFitWidth(nArray[i][j].getWidth()); //Using the attributes of the Block to fill out the ImageView object
					this.brickHitboxes[i][j].setX(nArray[i][j].getPosition().getX()); this.brickHitboxes[i][j].setY(nArray[i][j].getPosition().getY());
				}
			}
		}
	}
	
	/**
	 * This method iterates through the newly made 2D array of viewable objects and adds them to the root to be displayed on screen
	 * @param root is the Pane object to add the viewable ImageView object to
	 */
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
	
	/**
	 * This method removes a specified graphics object out of the root, aka when the ball collides and destroys a block
	 * @param root is the Pane object to remove the collidable object from
	 * @param i is an integer for going to a specific index of the first level of the 2D array of graphics objects
	 * @param j is an integer for going to a specific index of the second level of the 2D array of graphics objects
	 */
	public void removeBlockFromRoot(Pane root, int i, int j)
	{
		root.getChildren().remove(this.brickHitboxes[i][j]);
	}
	
	/**
	 * This method is for adding Perk Sprites the root once spontaneously made
	 * @param root is the Pane object for adding new viewable objects into the window
	 * @param i is an integer for going to a specific index of the first level of the 2D array of graphics objects
	 * @param j is an integer for going to a specific index of the second level of the 2D array of graphics objects
	 */
	public void addPerkToRoot(Pane root, double i, double j)
	{
		Polygon perk = new Polygon(); //Making a triangle with a Polygon object and filling it's colors
		perk.getPoints().addAll(new Double[] { i,j, i+10, j+10, i-10, j+10 });
		perk.setStroke(Color.BLACK);
		perk.setFill(Color.GREEN);
		root.getChildren().add(perk);
		this.perkSprites.add(perk); //Adding the perk to the list of perkSprites as well as the root to keep track of them and which one is lowest on the screen (will be first one in list)
	}
	
	/**
	 * This is a method for handling the ball and brick collisions
	 * @param root is the Pane object used for removing bricks and adding perks
	 * @param ballMovement is a Ball object used for conditions and changing it's attributes like direction and hitting blocks
	 * @param barMovement is a Bar object used for increasing the score
	 * @param board is the Board object used for accesing the Block methods for conditions, health, and types of blocks
	 */
	public void checkBallBrickCollisionTrigger(Pane root, Ball ballMovement, Player barMovement, Board board, PerkDrop pD, MediaPlayer impact) {
		
		if(impact.getCurrentCount()==1)
		{
			impact.stop();
		}
		impact.setCycleCount(MediaPlayer.INDEFINITE);
		for(int i = 0; i < this.brickHitboxes.length; i++) 
    	{
    		for(int j = 0; j < this.brickHitboxes[i].length; j++)
    		{
    			if((ballMovement.getHitBrick() == false && board.getBlockArrayAtIndex(i,j).getVisibility() && 
    					this.checkBallAndBrickSides(i, j))) //Colliding with the brick sides
    			{
    				if(board.getBlockArrayAtIndex(i, j).decreaseHealth())
    				{
    					if(board.getBlockArrayAtIndex(i, j).getSymbol() == 'N') //Check if Block is a Normal Block
    					{
    						barMovement.increaseScore(1, pD);
    					}
    					if(board.getBlockArrayAtIndex(i, j).getSymbol() == 'H') //Check if Block is a Hard Block
    					{
    						barMovement.increaseScore(2, pD);
    						double x = this.brickHitboxes[i][j].getX() + (this.brickHitboxes[i][j].getFitWidth()/2);
    						double y = this.brickHitboxes[i][j].getY();
    						this.addPerkToRoot(root, x, y); //Getting the x and y positions and then feeding them to spawn Perks
    						pD.choosePerk();
    					}
    					root.getChildren().remove(this.brickHitboxes[i][j]);
    					board.removeBlockAtIndex(root, i, j, this);
    				}
    				else //If Block doesn't die it's a hard block so switch the sprite image
    				{
    					this.brickHitboxes[i][j].setImage(board.getBlockArrayAtIndex(i,j).getBrickSpritesAtIndex(1));
    				}
    				impact.play();
    				ballMovement.horzCollision();
    				ballMovement.setHitBrick(true);
    			}
    			else if((ballMovement.getHitBrick() == false && board.getBlockArrayAtIndex(i,j).getVisibility() && 
    					this.checkBallAndBrickTopAndBottom(i, j))) //Colliding with the blocks top or bottom
    			{
    				if(board.getBlockArrayAtIndex(i, j).decreaseHealth())
    				{
    					root.getChildren().remove(this.brickHitboxes[i][j]);
    					board.removeBlockAtIndex(root, i, j, this);
    					if(board.getBlockArrayAtIndex(i, j).getSymbol() == 'N') //Check if Block is a Normal Block
    					{
    						barMovement.increaseScore(1, pD);
    					}
    					else if(board.getBlockArrayAtIndex(i, j).getSymbol() == 'H') //Check if Block is a Hard Block
    					{
    						barMovement.increaseScore(2, pD);
    						double x = this.brickHitboxes[i][j].getX() + (this.brickHitboxes[i][j].getFitWidth()/2);
    						double y = this.brickHitboxes[i][j].getY();
    						this.addPerkToRoot(root, x, y); //Getting the x and y positions and then feeding them to spawn Perks
    						pD.choosePerk();
    					}
    				}
    				else //If Block doesn't die it's a hard block so switch the sprite image
    				{
    					this.brickHitboxes[i][j].setImage(board.getBlockArrayAtIndex(i,j).getBrickSpritesAtIndex(1));
    				}
    				impact.play();
    				ballMovement.vertCollision();
    				ballMovement.setHitBrick(true);
    			}
    		}
    	}       
		
	}
	
	/**
	 * This is a method for checking if the ball (Circle Object) collides with the player (Rectangle Object)
	 * @param bM is the Ball object for changing movement
	 */
	public void checkBallPlayerCollisionTrigger(Ball bM, MediaPlayer impact)
	{
		if(impact.getCurrentCount()==1)
		{
			impact.stop();
		}
		impact.setCycleCount(MediaPlayer.INDEFINITE);
		if((this.ballHitbox.getBoundsInParent().intersects(this.barHitbox.getBoundsInParent()) && (this.checkBallAndPlayerCorners(bM))))
		{
			impact.play();
			bM.vertCollision();
			bM.horzCollision();
		}
		else if((this.ballHitbox.getBoundsInParent().intersects(this.barHitbox.getBoundsInParent()) && (this.checkBallAndPlayerSides())))
    	{
			impact.play();
    		bM.horzCollision();
    	} 
		else if((this.ballHitbox.getBoundsInParent().intersects(this.barHitbox.getBoundsInParent()) && this.checkBallAndPlayerTop()))
		{
			impact.play();
			bM.vertCollision();
		}
	}
	
	/**
	 * This method if for checking if a Perk collides with the Player or the bottom of the screen
	 * @param root is the Pane object holding the game objects and is used to remove collided Perks
	 * @param pM is the Player object used to pass down to apply a Perk to it
	 * @param pD is the PerkDrop object use for applying and removing Perks
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
			else if(this.perkSprites.get(i).getLayoutY() >= this.BACKGROUND_HEIGHT)
			{
				root.getChildren().remove(this.perkSprites.get(i));
				pD.removeLowestPerk();
				this.perkSprites.remove(i);
			}
		}
	}
	
}
