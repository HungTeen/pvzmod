package com.hungteen.pvz.utils;

import java.util.EnumMap;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.gui.GuiHandler;
import com.hungteen.pvz.potion.PotionRecipeHandler;
import com.hungteen.pvz.register.BlockRegister;
import com.hungteen.pvz.register.ItemRegister;

import net.minecraft.item.ItemStack;

public class TradeUtil {

	public static final EnumMap<DaveGoods, Integer> DAVE_GOODS_COST = new EnumMap<>(DaveGoods.class);
	public static final EnumMap<DaveGoods, ItemStack> DAVE_GOODS_ITEM = new EnumMap<>(DaveGoods.class);
	
	public static void initTrades() {
		putInfoToDaveGoodsMap(DaveGoods.ALMANAC, 100, new ItemStack(ItemRegister.ALMANAC.get()));
		putInfoToDaveGoodsMap(DaveGoods.GATLING_PEA_CARD, 5000, new ItemStack(ItemRegister.GATLING_PEA_CARD.get()));
		putInfoToDaveGoodsMap(DaveGoods.TWIN_SUNFLOWER_CARD, 5000, new ItemStack(ItemRegister.TWIN_SUNFLOWER_CARD.get()));
		putInfoToDaveGoodsMap(DaveGoods.CAT_TAIL_CARD, 12000, new ItemStack(ItemRegister.CAT_TAIL_CARD.get()));
		putInfoToDaveGoodsMap(DaveGoods.LIGHT_EYE_POTION_1, 100, PotionRecipeHandler.LIGHT_EYE_POTION_1);
		putInfoToDaveGoodsMap(DaveGoods.LIGHT_EYE_POTION_2, 800, PotionRecipeHandler.LIGHT_EYE_POTION_2);
		putInfoToDaveGoodsMap(DaveGoods.LANTERN, 500, new ItemStack(BlockRegister.LANTERN.get()));
	}
	
	public static void putInfoToDaveGoodsMap(DaveGoods good, int cost, ItemStack item) {
		DAVE_GOODS_COST.put(good, cost);
		DAVE_GOODS_ITEM.put(good, item);
	}
	
	public static ItemStack getGoodItemStack(DaveGoods good) {
		if(DAVE_GOODS_ITEM.containsKey(good)) {
			ItemStack stack = DAVE_GOODS_ITEM.get(good).copy();
			return stack;
		}
		PVZMod.LOGGER.debug("ERROR: Get DaveGoods ItemStack !");
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
	
	public enum DaveGoods {
		
		ENERGY(GuiHandler.DAVE_SHOP),
		ALMANAC(GuiHandler.DAVE_SHOP),
		GATLING_PEA_CARD(GuiHandler.DAVE_SHOP),
		TWIN_SUNFLOWER_CARD(GuiHandler.DAVE_SHOP),
		CAT_TAIL_CARD(GuiHandler.DAVE_SHOP),
		LIGHT_EYE_POTION_1(GuiHandler.SUN_SHOP),
		LIGHT_EYE_POTION_2(GuiHandler.SUN_SHOP),
		LANTERN(GuiHandler.SUN_SHOP);
		
		public final int shopId;//0 means dave shop, 1 means sun shop.
		
		private DaveGoods(int id) {
			this.shopId = id;
		}
		
	}
	
}
