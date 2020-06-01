package com.hungteen.pvzmod.client.gui.mainwindow;

import java.util.HashMap;

import com.hungteen.pvzmod.util.enums.Plants;

import net.minecraft.client.gui.GuiScreen;

public class PVZGuiTabPlayerData extends GuiScreen{

	public static int level=0;
	public static int xp=0;
	public static int sunNum=0;
	public static int energyNum=0;
	public static int money=0;
	
	private static HashMap<Plants, Integer> plantCardXp = new HashMap<Plants, Integer>(Plants.values().length);
	private static HashMap<Plants, Integer> plantCardLevel = new HashMap<Plants, Integer>(Plants.values().length);
	private static HashMap<Plants, Boolean> isPlantLocked = new HashMap<Plants, Boolean>(Plants.values().length);
	
	public static void setPlayerData(int lvl,int x,int m,int sun,int energy)
	{
		level=lvl;
		xp=x;
		money=m;
		sunNum=sun;
		energyNum=energy;
	}
	
	public static void setPlayerPlantData(Plants plant,int lvl,int num,boolean is)
	{
		plantCardLevel.put(plant, lvl);
		plantCardXp.put(plant, num);
		isPlantLocked.put(plant, is);
	}
	
	public static int getPlayerPlantCardLvl(Plants plant)
	{
		return plantCardLevel.get(plant);
	}
	
	public static int getPlayerPlantCardXp(Plants plant)
	{
		return plantCardXp.get(plant);
	}
	
	public static int getPlayerSunNum()
	{
		return sunNum;
	}
	
	public static int getPlayerMoney()
	{
		return money;
	}
}
