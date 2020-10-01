package com.hungteen.pvz.utils;

import com.hungteen.pvz.gui.DaveShopScreen.Goods;

public class TradeUtil {

	public static final int ALMANAC_COST = 100;
	
	public static int getGoodCost(Goods good) {
		if(good==Goods.ALMANAC) return ALMANAC_COST;
		return 0;
	}
	
	public static int getEnergyCost(int num) {
		if(num == 1) return 50;
		if(num == 2) return 400;
		if(num == 3) return 1000;
		if(num == 4) return 2500;
		if(num == 5) return 5000;
		if(num == 6) return 10000;
		if(num == 7) return 15000;
		if(num == 8) return 20000;
		if(num == 9) return 30000;
		return 999999999;
	}
	
	public static int getSlotCost(int num) {
		num /= 18;
		if(num == 1) return 100;
		if(num == 2) return 500;
		if(num == 3) return 2000;
		if(num == 4) return 500;
		if(num == 5) return 8000;
		if(num == 6) return 15000;
		if(num == 7) return 20000;
		if(num == 8) return 30000;
		if(num == 9) return 50000;
		return 999999999;
	}
}
