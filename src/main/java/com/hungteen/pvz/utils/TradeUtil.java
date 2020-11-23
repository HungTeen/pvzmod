package com.hungteen.pvz.utils;

import java.util.EnumMap;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.register.ItemRegister;

import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;

public class TradeUtil {

	public static final EnumMap<DaveGoods, Integer> DAVE_GOODS_COST = new EnumMap<>(DaveGoods.class);
	public static final EnumMap<DaveGoods, RegistryObject<? extends Item>> DAVE_GOODS_ITEM = new EnumMap<>(DaveGoods.class);

	static {
		putInfoToDaveGoodsMap(DaveGoods.ALMANAC, 100, ItemRegister.ALMANAC);
		putInfoToDaveGoodsMap(DaveGoods.GATLING_PEA_CARD, 5000, ItemRegister.GATLING_PEA_CARD);
		putInfoToDaveGoodsMap(DaveGoods.TWIN_SUNFLOWER_CARD, 5000, ItemRegister.TWIN_SUNFLOWER_CARD);
	}
	
	public static void putInfoToDaveGoodsMap(DaveGoods good, int cost, RegistryObject<? extends Item> item) {
		DAVE_GOODS_COST.put(good, cost);
		DAVE_GOODS_ITEM.put(good, item);
	}
	
	public static Item getGoodItem(DaveGoods good) {
		if(DAVE_GOODS_ITEM.containsKey(good)) {
			return DAVE_GOODS_ITEM.get(good).get();
		}
		PVZMod.LOGGER.debug("ERROR: Get DaveGoods Item !");
		return null;
	}
	
	public static int getGoodCost(DaveGoods good) {
		if(good == DaveGoods.ENERGY) {
			
		}
		if(DAVE_GOODS_COST.containsKey(good)) {
			return DAVE_GOODS_COST.get(good);
		}
		PVZMod.LOGGER.debug("ERROR: Get DaveGoods Cost !");
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
	
	public enum DaveGoods{
		ENERGY,
		ALMANAC,
		GATLING_PEA_CARD,
		TWIN_SUNFLOWER_CARD
	}
	
}
