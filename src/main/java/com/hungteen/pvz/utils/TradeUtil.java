package com.hungteen.pvz.utils;

import java.util.EnumMap;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.gui.GuiHandler;
import com.hungteen.pvz.potion.PotionRecipeHandler;
import com.hungteen.pvz.register.BlockRegister;
import com.hungteen.pvz.register.EnchantmentRegister;
import com.hungteen.pvz.register.ItemRegister;

import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.item.EnchantedBookItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class TradeUtil {

	public static final EnumMap<DaveGoods, Integer> DAVE_GOODS_COST = new EnumMap<>(DaveGoods.class);
	public static final EnumMap<DaveGoods, ItemStack> DAVE_GOODS_ITEM = new EnumMap<>(DaveGoods.class);
	private static final ItemStack SUN_SHOVEL_BOOK = new ItemStack(Items.ENCHANTED_BOOK);
	private static final ItemStack SUN_MENDING_BOOK = new ItemStack(Items.ENCHANTED_BOOK);
	
	public static void initTrades() {
		EnchantedBookItem.addEnchantment(SUN_SHOVEL_BOOK, new EnchantmentData(EnchantmentRegister.SUN_SHOVEL.get(), 1));
		EnchantedBookItem.addEnchantment(SUN_MENDING_BOOK, new EnchantmentData(EnchantmentRegister.SUN_MENDING.get(), 1));
		putInfoToDaveGoodsMap(DaveGoods.ALMANAC, 100, new ItemStack(ItemRegister.ALMANAC.get()));
		putInfoToDaveGoodsMap(DaveGoods.GATLING_PEA_CARD, 5000, new ItemStack(ItemRegister.GATLING_PEA_CARD.get()));
		putInfoToDaveGoodsMap(DaveGoods.TWIN_SUNFLOWER_CARD, 5000, new ItemStack(ItemRegister.TWIN_SUNFLOWER_CARD.get()));
		putInfoToDaveGoodsMap(DaveGoods.GLOOM_SHROOM_CARD, 7500, new ItemStack(ItemRegister.GLOOM_SHROOM_CARD.get()));
		putInfoToDaveGoodsMap(DaveGoods.CAT_TAIL_CARD, 12000, new ItemStack(ItemRegister.CAT_TAIL_CARD.get()));
		putInfoToDaveGoodsMap(DaveGoods.GOLD_MAGNET_CARD, 3000, new ItemStack(ItemRegister.GOLD_MAGNET_CARD.get()));
		putInfoToDaveGoodsMap(DaveGoods.CAR_KEY, 500, new ItemStack(ItemRegister.CAR_KEY.get()));
		putInfoToDaveGoodsMap(DaveGoods.LIGHT_EYE_POTION_1, 100, PotionRecipeHandler.LIGHT_EYE_POTION_1);
		putInfoToDaveGoodsMap(DaveGoods.LIGHT_EYE_POTION_2, 800, PotionRecipeHandler.LIGHT_EYE_POTION_2);
		putInfoToDaveGoodsMap(DaveGoods.LANTERN, 500, new ItemStack(BlockRegister.LANTERN.get(), 8));
		putInfoToDaveGoodsMap(DaveGoods.SLOT_MACHINE, 2000, new ItemStack(BlockRegister.SLOT_MACHINE.get()));
		putInfoToDaveGoodsMap(DaveGoods.SUN_SHOVEL, 3500, SUN_SHOVEL_BOOK);
		putInfoToDaveGoodsMap(DaveGoods.SUN_MENDING, 5000, SUN_MENDING_BOOK);
		putInfoToDaveGoodsMap(DaveGoods.GOLD_LEAF_CARD, 6000, new ItemStack(ItemRegister.GOLD_LEAF_CARD.get()));
		putInfoToDaveGoodsMap(DaveGoods.LAWN_MOWER, 2, new ItemStack(ItemRegister.LAWN_MOWER.get()));
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
		if(good == DaveGoods.MONEY) {
			return 1;
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
		GLOOM_SHROOM_CARD(GuiHandler.DAVE_SHOP),
		CAT_TAIL_CARD(GuiHandler.DAVE_SHOP),
		GOLD_MAGNET_CARD(GuiHandler.DAVE_SHOP),
		CAR_KEY(GuiHandler.DAVE_SHOP),
		LIGHT_EYE_POTION_1(GuiHandler.SUN_SHOP),
		LIGHT_EYE_POTION_2(GuiHandler.SUN_SHOP),
		LANTERN(GuiHandler.SUN_SHOP),
		SLOT_MACHINE(GuiHandler.SUN_SHOP),
		SUN_SHOVEL(GuiHandler.SUN_SHOP),
		SUN_MENDING(GuiHandler.SUN_SHOP),
		GOLD_LEAF_CARD(GuiHandler.SUN_SHOP),
		MONEY(GuiHandler.PENNY_SHOP),
		LAWN_MOWER(GuiHandler.PENNY_SHOP);
		
		public final int shopId;//0 means dave shop, 1 means sun shop.
		
		private DaveGoods(int id) {
			this.shopId = id;
		}
		
	}
	
}
