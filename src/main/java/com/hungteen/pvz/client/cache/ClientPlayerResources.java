package com.hungteen.pvz.client.cache;

import com.hungteen.pvz.client.gui.search.SearchOption;
import com.hungteen.pvz.common.container.shop.MysteryShopContainer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.HashMap;

@OnlyIn(Dist.CLIENT)
public class ClientPlayerResources{

	/* summon card inventory */
	private static final HashMap<SearchOption, Boolean> unLocked = new HashMap<SearchOption, Boolean>(SearchOption.OPTION.size());
	public static final int[] mysteryGoods = new int[MysteryShopContainer.MAX_MYSTERY_GOOD];
	public static int updateGoodTick;
	public static int lightLevel = 0;
	
	static { //init to avoid unexpected error !
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
