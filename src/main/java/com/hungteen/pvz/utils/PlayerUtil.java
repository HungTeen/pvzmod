package com.hungteen.pvz.utils;

import com.hungteen.pvz.capabilities.CapabilityHandler;
import com.hungteen.pvz.capabilities.player.IPlayerDataCapability;
import com.hungteen.pvz.capabilities.player.PlayerDataManager;
import com.hungteen.pvz.utils.enums.Almanacs;
import com.hungteen.pvz.utils.enums.Plants;
import com.hungteen.pvz.utils.enums.Resources;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.common.util.LazyOptional;

public class PlayerUtil {

	public static final int MAX_TREE_LVL = 100;
	public static final int MAX_ENERGY_NUM = 10;
	public static final int MAX_SLOT_NUM = 162;
	public static final int[] TREE_LVL_XP = new int[] {10,25,45,75,120,180,250,350,480,600,750,900,1080,1300,1600,2000,2500,3200,4000,5000}; 
	public static int getPlayerMaxSunNum(int lvl){
		int now=lvl/10+1;
		return now*1000;
	}
	
	public static int getPlayerLevelUpXp(int lvl){
		int pos = lvl/5;
		if(lvl>=MAX_TREE_LVL) {
			return 1000000000;
		}
		return TREE_LVL_XP[pos];
	}
	
	
	public static void addPlayerStats(PlayerEntity player ,Resources res,int num){
		player.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent((l)->{
			l.getPlayerData().getPlayerStats().addPlayerStats(res, num);
		});
	}
	
	public static void addPlantLvl(PlayerEntity player, Plants plant, int num) {
		player.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent((l)->{
			l.getPlayerData().getPlantStats().addPlantLevel(plant, num);
		});
	}
	
	public static void addPlantXp(PlayerEntity player, Plants plant, int num) {
		player.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent((l)->{
			l.getPlayerData().getPlantStats().addPlantXp(plant, num);
		});
	}
	
	public static void clonePlayerData(PlayerEntity oldPlayer, PlayerEntity newPlayer) {
		LazyOptional<IPlayerDataCapability> oldCap = oldPlayer.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY);
		LazyOptional<IPlayerDataCapability> newCap = newPlayer.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY);
		if(oldCap.isPresent()&&newCap.isPresent()) {
			newCap.ifPresent((l)->{
				oldCap.ifPresent((r)->{
					l.getPlayerData().cloneFromExistingPlayerData(r.getPlayerData());
				});
			});
		}
	}
	
	public static void unLockAlmanac(PlayerEntity player, Almanacs a) {
		player.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent((l)->{
		    PlayerDataManager.AlmanacStats stats = l.getPlayerData().getAlmanacStats();
		    if(!stats.isAlmanacUnLocked(a)) {
			    stats.setAlmanacUnLocked(a, true);
		    }
	    });
	}

}
