package com.hungteen.pvz.capability.player;

import java.util.HashMap;

import com.hungteen.pvz.gui.search.SearchOption;
import com.hungteen.pvz.utils.enums.Plants;
import com.hungteen.pvz.utils.enums.Resources;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ClientPlayerResources{

	private static HashMap<Resources, Integer> resources = new HashMap<>(Resources.values().length);
	private static HashMap<Plants, Integer> plantCardXp = new HashMap<Plants, Integer>(Plants.values().length);
	private static HashMap<Plants, Integer> plantCardLevel = new HashMap<Plants, Integer>(Plants.values().length);
	private static HashMap<SearchOption, Boolean> unLocked = new HashMap<SearchOption, Boolean>(SearchOption.OPTION.size());
	public static int playSoundTick = 0;
	
	static { //init to avoid unexpected error !
		for(Resources res : Resources.values()) {
			resources.put(res, 0);
		}
		for(Plants p : Plants.values()) {
			plantCardLevel.put(p, 0);
			plantCardXp.put(p, 0);
		}
		for(SearchOption a : SearchOption.OPTION) {
			unLocked.put(a, false);
		}
	}
	
	public static void setPlayerData(int type,int data){
		resources.put(Resources.values()[type], data);
	}
	
	public static void setPlantData(int type,int lvl,int xp){
		plantCardLevel.put(Plants.values()[type], lvl);
		plantCardXp.put(Plants.values()[type], xp);
	}
	
	public static void setAlmanacUnLocked(int type, boolean data){
		unLocked.put(SearchOption.OPTION.get(type), data);
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
	
	public static boolean isAlmanacUnLocked(SearchOption option){
		return unLocked.get(option);
	}
	
}
