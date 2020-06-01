package com.hungteen.pvzmod.util;

import javax.annotation.Nonnull;

import com.hungteen.pvzmod.capability.data.PlayerDataManager;
import com.hungteen.pvzmod.capability.handler.PVZPlayerCapability;
import com.hungteen.pvzmod.capability.provider.PVZPlayerProvider;
import com.hungteen.pvzmod.util.enums.Plants;

import net.minecraft.entity.player.EntityPlayer;

public class PlayerUtil {

	@Nonnull
	public static PlayerDataManager getPVZPlayer(@Nonnull EntityPlayer player) {
		PVZPlayerCapability cap = (PVZPlayerCapability) player.getCapability(PVZPlayerProvider.PVZ_PLAYER, null);

		// System.out.println(player.getCapability(PVZPlayerProvider.PVZ_PLAYER, null));
		if (cap == null) {
			cap = new PVZPlayerCapability();

			cap.init(player);
			System.out.println("Player: " + player.getName()
					+ " doesn't have an attached PVZPlayer capability. Something is very wrong here.");
		}

		if (cap.getPlayerData() == null)
			cap.init(player);

		return cap.getPlayerData();
	}

	public static int getPlayerMaxSunNum(int lvl)
	{
		if(lvl<=10) return 100*lvl;   //100 200 ...1000
		if(lvl<=20) return 150*(lvl-10)+1000; //1150 1300 ...2500
		else if(lvl<=40) return 125*(lvl-20)+2500; //2625 2750 ...5000
		else if(lvl<=60) return 250*(lvl-40)+5000; //5250 5500 ...10000
		else if(lvl<=80) return 2000*(lvl-60)+10000; //14000 18000 ... 50000
		else if(lvl<=99) return 2500*(lvl-80)+50000;
		return 99999;
	}
	
	public static int getPlayerMaxEnergyNum(int lvl)
	{
		return 1+lvl/20;
	}
	
	public static int getPlayerLevelUpXp(int lvl)
	{
		if(lvl<=10) return 5*lvl;   //5 10 15 ...50
		else if(lvl<=20) return 50+(lvl-10)*10; //60 70 .... 150
		else if(lvl<=30) return 150+(lvl-20)*30; //180 210 240...450
		else if(lvl<=40) return 450+(lvl-30)*75; //525 600 675...1200
		else if(lvl<=50) return 1200+(lvl-40)*150; //1350 1500 1650...2700
		else if(lvl<=60) return 2700+(lvl-50)*300; //3000 3300 3600...5700
		else if(lvl<=70) return 5700+(lvl-60)*500; //6200 6700 7200...10700
		else if(lvl<=80) return 10700+(lvl-70)*1000; //11700 12700 ...20700
		else if(lvl<=90) return 20700+(lvl-80)*2000; //22700 24700 ...40700
		else if(lvl<=100) return 40700+(lvl-90)*4000; //44700 48700 ...80700
		else return 100000;
	}
	
	public static int getPlantLvl(EntityPlayer player ,Plants plant){
		return getPVZPlayer(player).getPlantStats().getPlantLevel(plant);
	}
	
	public static int getPlayerSunNum(EntityPlayer player) {
		return getPVZPlayer(player).getPlayerStats().getPlayerSunNum();
	}
	
	public static int getPlayerEnergyNum(EntityPlayer player) {
		return getPVZPlayer(player).getPlayerStats().getPlayerEnergyNum();
	}
	
	public static void addPlantCardNum(EntityPlayer player, Plants plant, int xp) {
		int lvl=getPlantLvl(player, plant);
		getPVZPlayer(player).getPlantStats().addPlantXp(plant, xp);
	}

	public static void addPlayerXp(EntityPlayer player, int xp) {
		getPVZPlayer(player).getPlayerStats().addPlayerXp(xp);
	}

	public static void addPlayerSunNum(EntityPlayer player, int num) {
		getPVZPlayer(player).getPlayerStats().addPlayerSunNum(num);
	}

	public static void addPlayerMoney(EntityPlayer player, int num) {
		getPVZPlayer(player).getPlayerStats().addPlayerMoney(num);
	}

	public static void addPlayerEnergyNum(EntityPlayer player, int num) {
		getPVZPlayer(player).getPlayerStats().addPlayerEnergyNum(num);
	}

	public static void clonePlayerData(EntityPlayer sourcePlayer, EntityPlayer targetPlayer) {
		getPVZPlayer(targetPlayer).cloneFromExistingPlayerData(getPVZPlayer(sourcePlayer));
	}

}
