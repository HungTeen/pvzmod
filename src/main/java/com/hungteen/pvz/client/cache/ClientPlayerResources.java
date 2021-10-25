package com.hungteen.pvz.client.cache;

import com.hungteen.pvz.api.PVZAPI;
import com.hungteen.pvz.api.types.IPAZType;
import com.hungteen.pvz.client.gui.search.SearchOption;
import com.hungteen.pvz.common.container.shop.MysteryShopContainer;
import com.hungteen.pvz.common.impl.PlantType;
import com.hungteen.pvz.common.world.invasion.WaveManager;
import com.hungteen.pvz.utils.enums.Resources;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@OnlyIn(Dist.CLIENT)
public class ClientPlayerResources{

	private static final HashMap<Resources, Integer> RESOURCES = new HashMap<>(Resources.values().length);
	/* summon card inventory */
	public static final List<ItemStack> SUMMON_CARDS = new ArrayList<>();
	public static int emptySlot;
	private static final HashMap<IPAZType, Integer> PAZ_XP = new HashMap<>();
	private static final HashMap<IPAZType, Integer> PAZ_LEVEL = new HashMap<>();
	private static final HashMap<SearchOption, Boolean> unLocked = new HashMap<SearchOption, Boolean>(SearchOption.OPTION.size());
	public static final int[] zombieWaveTime = new int[WaveManager.MAX_WAVE_NUM];
	public static int totalWaveCount;
	public static final int[] mysteryGoods = new int[MysteryShopContainer.MAX_MYSTERY_GOOD];
	public static int updateGoodTick;
	public static int lightLevel = 0;
	
	static { //init to avoid unexpected error !
		/* init resources */
		for(Resources res : Resources.values()) {
			RESOURCES.put(res, 0);
		}
		/* init card list */
		for(int i = 0; i <= Resources.SLOT_NUM.max; ++ i) {
			SUMMON_CARDS.add(ItemStack.EMPTY);
		}
		for(IPAZType p : PVZAPI.get().getPAZs()) {
			PAZ_LEVEL.put(p, 1);
			PAZ_XP.put(p, 0);
		}
		for(SearchOption a : SearchOption.OPTION) {
			unLocked.put(a, false);
		}
	}
	
	public static void setPlayerData(int type,int data){
		RESOURCES.put(Resources.values()[type], data);
	}
	
	public static void setPlantData(IPAZType type, int lvl, int xp){
		PAZ_LEVEL.put(type, lvl);
		PAZ_XP.put(type, xp);
	}
	
	public static void setAlmanacUnLocked(int type, boolean data){
		unLocked.put(SearchOption.OPTION.get(type), data);
	}
	
	public static int getPlayerStats(Resources res){
		return RESOURCES.get(res);
	}
	
	public static int getPlayerPlantCardLvl(PlantType plant){
		return PAZ_LEVEL.get(plant);
	}
	
	public static int getPlayerPlantCardXp(PlantType plant){
		return PAZ_XP.get(plant);
	}
	
	public static boolean isAlmanacUnLocked(SearchOption option){
		return unLocked.get(option);
	}
	
}
