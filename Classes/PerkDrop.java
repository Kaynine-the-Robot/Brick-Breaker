package Classes;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class PerkDrop extends AbstractObjects {
	
	private double fallSpeed;
	private int scoreMultiplier = 1;
	private String[] perkList;
	private ArrayList<String> perksFalling = new ArrayList<String>();
	
	public PerkDrop(double nFall, String[] nPerkList)
	{
		this.fallSpeed = nFall;
		this.perkList = new String[nPerkList.length];
		for(int i = 0; i < nPerkList.length; i++)
		{
			this.perkList[i] = nPerkList[i].substring(0).trim();
		}
	}
	
	public double getFallSpeed()
	{
		return this.fallSpeed;
	}
	
	public void setFallSpeed(int nSpeed)
	{
		if(nSpeed > 0 && nSpeed < 5)
		{
			this.fallSpeed = nSpeed;
		}
	}
	
	public int getMulti()
	{
		return this.scoreMultiplier;
	}
	
	public void lowerMulti()
	{
		this.scoreMultiplier = this.scoreMultiplier / 2;
	}
	
	public void resetMulti()
	{
		this.scoreMultiplier = 1;
	}
	
	public String choosePerk()
	{
		int rand = ThreadLocalRandom.current().nextInt(0, this.perkList.length);
		perksFalling.add(perkList[rand]);
		return perkList[rand];
	}
	
	public void applyPerk(String perk, Player bar)
	{
		switch(perk)
		{
		case "lumpScoreBonus": bar.increaseScore(10, this); break;
		case "scoreMultiplier": this.scoreMultiplier = this.scoreMultiplier * 2; bar.setMultiFlag(true); bar.setMultiTimer(); break;
		default: break;
		}
	}
	
	public String getLowestPerk()
	{
		return this.perksFalling.get(0); //0 will be the first made perk and lowest on screen
	}
	
	public void removeLowestPerk()
	{
		this.perksFalling.remove(0);
	}
}
