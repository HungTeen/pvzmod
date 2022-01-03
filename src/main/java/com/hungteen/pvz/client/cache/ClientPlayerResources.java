package com.hungteen.pvz.client.cache;

import com.hungteen.pvz.client.gui.search.SearchOption;
import com.hungteen.pvz.common.container.shop.MysteryShopContainer;
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

	/* summon card inventory */
	public static final List<ItemStack> SUMMON_CARDS = new ArrayList<>();
	public static int emptySlot;
	private static final HashMap<SearchOption, Boolean> unLocked = new HashMap<SearchOption, Boolean>(SearchOption.OPTION.size());
	public static final int[] zombieWaveTime = new int[WaveManager.MAX_WAVE_NUM];
	public static int totalWaveCount;
	public static final int[] mysteryGoods = new int[MysteryShopContainer.MAX_MYSTERY_GOOD];
	public static int updateGoodTick;
	public static int lightLevel = 0;
	
	static { //init to avoid unexpected error !
		/* init card list */
		for(int i = 0; i <= Resources.SLOT_NUM.max; ++ i) {
			SUMMON_CARDS.add(ItemStack.EMPTY);
		}
		for(SearchOption a : SearchOption.OPTION) {
			unLocked.put(a, false);
		}
	}

	public static void setAlmanacUnLocked(int type, boolean data){
		unLocked.put(SearchOption.OPTION.get(type), data);
	}
	
	public static boolean isAlmanacUnLocked(SearchOption option){
		return unLocked.get(option);
	}
	
}
