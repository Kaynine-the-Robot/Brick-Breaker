package Classes;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Sound {
	
	private MediaPlayer[] sounds = new MediaPlayer[2];
	private MediaPlayer backgroundSound;
	private MediaPlayer death;
	private Media[] files = new Media[4];
	
	public Sound(Media impact1, Media impact2, Media background, Media death)
	{
		this.sounds[0] = new MediaPlayer(impact1);
		this.sounds[1] = new MediaPlayer(impact2);
		this.backgroundSound = new MediaPlayer(background);
		this.death = new MediaPlayer(death);
		files[0] = impact1; files[1] = impact2; files[2] = background; files[3] = death;
	}
	
	public MediaPlayer getImpactAtIndex(int index)
	{
		return this.sounds[index];
	}
	
	public MediaPlayer getAmbientBackground()
	{
		return this.backgroundSound;
	}
	
	public MediaPlayer getDeath()
	{
		return this.death;
	}
	
	public void reset()
	{
		this.sounds[0] = new MediaPlayer(this.files[0]);
		this.sounds[1] = new MediaPlayer(this.files[1]);
		this.backgroundSound = new MediaPlayer(this.files[2]);
		this.death = new MediaPlayer(this.files[3]);
	}

}
