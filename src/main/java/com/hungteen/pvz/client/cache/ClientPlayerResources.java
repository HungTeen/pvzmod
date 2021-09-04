package com.hungteen.pvz.client.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.hungteen.pvz.client.gui.search.SearchOption;
import com.hungteen.pvz.common.container.shop.MysteryShopContainer;
import com.hungteen.pvz.common.core.PlantType;
import com.hungteen.pvz.common.world.invasion.WaveManager;
import com.hungteen.pvz.utils.enums.Resources;

import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ClientPlayerResources{

	private static HashMap<Resources, Integer> resources = new HashMap<>(Resources.values().length);
	/* summon card inventory */
	public static final List<ItemStack> SUMMON_CARDS = new ArrayList<>();
	public static int emptySlot;
	private static HashMap<PlantType, Integer> plantCardXp = new HashMap<>();
	private static HashMap<PlantType, Integer> plantCardLevel = new HashMap<>();
	private static HashMap<SearchOption, Boolean> unLocked = new HashMap<SearchOption, Boolean>(SearchOption.OPTION.size());
	public static final int[] zombieWaveTime = new int[WaveManager.MAX_WAVE_NUM];
	public static int totalWaveCount;
	public static final int[] mysteryGoods = new int[MysteryShopContainer.MAX_MYSTERY_GOOD];
	public static int updateGoodTick;
	public static int lightLevel = 0;
	
	static { //init to avoid unexpected error !
		/* init resources */
		for(Resources res : Resources.values()) {
			resources.put(res, 0);
		}
		/* init card list */
		for(int i = 0; i <= Resources.SLOT_NUM.max; ++ i) {
			SUMMON_CARDS.add(ItemStack.EMPTY);
		}
		for(PlantType p : PlantType.getPlants()) {
			plantCardLevel.put(p, 1);
			plantCardXp.put(p, 0);
		}
		for(SearchOption a : SearchOption.OPTION) {
			unLocked.put(a, false);
		}
	}
	
	public static void setPlayerData(int type,int data){
		resources.put(Resources.values()[type], data);
	}
	
	public static void setPlantData(int type, int lvl, int xp){
		plantCardLevel.put(PlantType.getPlants().get(type), lvl);
		plantCardXp.put(PlantType.getPlants().get(type), xp);
	}
	
	public static void setAlmanacUnLocked(int type, boolean data){
		unLocked.put(SearchOption.OPTION.get(type), data);
	}
	
	public static int getPlayerStats(Resources res){
		return resources.get(res);
	}
	
	public static int getPlayerPlantCardLvl(PlantType plant){
		return plantCardLevel.get(plant);
	}
	
	public static int getPlayerPlantCardXp(PlantType plant){
		return plantCardXp.get(plant);
	}
	
	public static boolean isAlmanacUnLocked(SearchOption option){
		return unLocked.get(option);
	}
	
}
