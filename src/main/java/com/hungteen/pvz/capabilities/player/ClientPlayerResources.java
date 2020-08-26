package com.hungteen.pvz.capabilities.player;

import java.util.HashMap;

import com.hungteen.pvz.utils.enums.Plants;
import com.hungteen.pvz.utils.enums.Resources;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ClientPlayerResources{

	private static HashMap<Resources, Integer> resources = new HashMap<>(Resources.values().length);
	private static HashMap<Plants, Integer> plantCardXp = new HashMap<Plants, Integer>(Plants.values().length);
	private static HashMap<Plants, Integer> plantCardLevel = new HashMap<Plants, Integer>(Plants.values().length);
//	private static HashMap<Plants, Boolean> isPlantLocked = new HashMap<Plants, Boolean>(Plants.values().length);
	protected PlayerEntity player;
	
	public static void setPlayerData(int type,int data){
		resources.put(Resources.values()[type], data);
	}
	
	public static void setPlantData(int type,int lvl,int xp){
		plantCardLevel.put(Plants.values()[type], lvl);
		plantCardXp.put(Plants.values()[type], xp);
	}
	
	public static int getPlayerStats(Resources res){
		return resources.get(res);
	}
	
	public static int getPlayerPlantCardLvl(Plants plant){
		return plantCardLevel.get(plant);
	}
	
	public static int getPlayerPlantCardXp(Plants plant){
		return plantCardXp.get(plant);
	}
	
}
