package com.hungteen.pvz.client.cache;

import com.hungteen.pvz.common.container.shop.MysteryShopContainer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ClientPlayerResources{

	/* summon card inventory */
	public static final int[] mysteryGoods = new int[MysteryShopContainer.MAX_MYSTERY_GOOD];
	public static int updateGoodTick;
	public static int lightLevel = 0;
	
}
