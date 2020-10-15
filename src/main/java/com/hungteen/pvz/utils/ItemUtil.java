package com.hungteen.pvz.utils;

import java.util.HashMap;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.register.ItemRegister;
import com.hungteen.pvz.utils.enums.Almanacs;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class ItemUtil {

	public static final int MELON_AMOUNT = 2;
	public static final int APPLE_AMOUNT = 4;
	public static final int BREAD_AMOUNT = 5;
	public static final int CHICKEN_AMOUNT = 6;
	public static final int PIG_AMOUNT = 8;
	
	public static final float COOKIE_SATURATION = 0.1f;
	public static final float APPLE_SATURATION = 0.3f;
	public static final float BREAD_SATURATION = 0.6f;
	public static final float PIG_SATURATION = 0.8f;
	public static final float GOLDEN_SATURATION = 1.2f;
	
	public static final HashMap<Almanacs, Item> ALMANAC_ITEM = new HashMap<>();
	
	static {
		ALMANAC_ITEM.put(Almanacs.PLAYER, Items.PLAYER_HEAD);
		
		ALMANAC_ITEM.put(Almanacs.PEA_SHOOTER, ItemRegister.PEA_SHOOTER_CARD.get());
		ALMANAC_ITEM.put(Almanacs.SUN_FLOWER, ItemRegister.SUN_FLOWER_CARD.get());
		ALMANAC_ITEM.put(Almanacs.CHERRY_BOMB, ItemRegister.CHERRY_BOMB_CARD.get());
		ALMANAC_ITEM.put(Almanacs.WALL_NUT, ItemRegister.WALL_NUT_CARD.get());
		ALMANAC_ITEM.put(Almanacs.POTATO_MINE, ItemRegister.POTATO_MINE_CARD.get());
		ALMANAC_ITEM.put(Almanacs.SNOW_PEA, ItemRegister.SNOW_PEA_CARD.get());
		ALMANAC_ITEM.put(Almanacs.CHOMPER, ItemRegister.CHOMPER_CARD.get());
		ALMANAC_ITEM.put(Almanacs.REPEATER, ItemRegister.REPEATER_CARD.get());
		ALMANAC_ITEM.put(Almanacs.LILY_PAD, ItemRegister.LILY_PAD_CARD.get());
		ALMANAC_ITEM.put(Almanacs.SQUASH, ItemRegister.SQUASH_CARD.get());
		ALMANAC_ITEM.put(Almanacs.THREE_PEATER, ItemRegister.THREE_PEATER_CARD.get());
		ALMANAC_ITEM.put(Almanacs.TANGLE_KELP, ItemRegister.TANGLE_KELP_CARD.get());
		ALMANAC_ITEM.put(Almanacs.JALAPENO, ItemRegister.JALAPENO_CARD.get());
		ALMANAC_ITEM.put(Almanacs.SPIKE_WEED, ItemRegister.SPIKE_WEED_CARD.get());
		ALMANAC_ITEM.put(Almanacs.TORCH_WOOD, ItemRegister.TORCH_WOOD_CARD.get());
		ALMANAC_ITEM.put(Almanacs.TALL_NUT, ItemRegister.TALL_NUT_CARD.get());
		ALMANAC_ITEM.put(Almanacs.PUFF_SHROOM, ItemRegister.PUFF_SHROOM_CARD.get());
		ALMANAC_ITEM.put(Almanacs.SUN_SHROOM, ItemRegister.SUN_SHROOM_CARD.get());
		ALMANAC_ITEM.put(Almanacs.FUME_SHROOM, ItemRegister.FUME_SHROOM_CARD.get());
		ALMANAC_ITEM.put(Almanacs.GRAVE_BUSTER, ItemRegister.GRAVE_BUSTER_CARD.get());
		ALMANAC_ITEM.put(Almanacs.HYPNO_SHROOM, ItemRegister.HYPNO_SHROOM_CARD.get());
		ALMANAC_ITEM.put(Almanacs.SCAREDY_SHROOM, ItemRegister.SCAREDY_SHROOM_CARD.get());
		ALMANAC_ITEM.put(Almanacs.ICE_SHROOM, ItemRegister.ICE_SHROOM_CARD.get());
		ALMANAC_ITEM.put(Almanacs.DOOM_SHROOM, ItemRegister.DOOM_SHROOM_CARD.get());
	}
	
	public static ItemStack getItemStackByAlmanac(Almanacs a) {
		return new ItemStack(getItemByAlmanac(a));
	}
	
	public static Item getItemByAlmanac(Almanacs a) {
		if(ALMANAC_ITEM.containsKey(a)) {
			return ALMANAC_ITEM.get(a);
		}
		PVZMod.LOGGER.debug("WRONG ALMANAC TYPE");
		return Items.GRASS_BLOCK;
//			switch(a) {
//			case PEA_SHOOTER: return ItemRegister.PEA_SHOOTER_CARD.get();
//			case SUN_FLOWER: return ItemRegister.SUN_FLOWER_CARD.get();
//			case CHERRY_BOMB: return ItemRegister.CHERRY_BOMB_CARD.get();
//			case WALL_NUT: return ItemRegister.WALL_NUT_CARD.get();
//			case POTATO_MINE: return ItemRegister.POTATO_MINE_CARD.get();
//			case SNOW_PEA: return ItemRegister.SNOW_PEA_CARD.get();
//			case CHOMPER: return ItemRegister.CHOMPER_CARD.get();
//			case REPEATER: return ItemRegister.REPEATER_CARD.get();
//			case PUFF_SHROOM: return ItemRegister.PUFF_SHROOM_CARD.get();
//			case SUN_SHROOM: return ItemRegister.SUN_SHROOM_CARD.get();
//			case FUME_SHROOM: return ItemRegister.FUME_SHROOM_CARD.get();
//			case GRAVE_BUSTER: return ItemRegister.GRAVE_BUSTER_CARD.get();
//			case HYPNO_SHROOM: return ItemRegister.HYPNO_SHROOM_CARD.get();
//			case SCAREDY_SHROOM: return ItemRegister.SCAREDY_SHROOM_CARD.get();
//			case ICE_SHROOM: return ItemRegister.ICE_SHROOM_CARD.get();
//			case DOOM_SHROOM: return ItemRegister.DOOM_SHROOM_SPAWN_EGG.get();
//			case LILY_PAD: return ItemRegister.LILY_PAD_CARD.get();
//			case SQUASH: return ItemRegister.SQUASH_CARD.get();
//			case THREE_PEATER: return ItemRegister.THREE_PEATER_CARD.get();
//			case TANGLE_KELP: return ItemRegister.TANGLE_KELP_CARD.get();
//			case JALAPENO: return ItemRegister.JALAPENO_CARD.get();
//			case SPIKE_WEED: return ItemRegister.SPIKE_WEED_CARD.get();
//			case TORCH_WOOD: return ItemRegister.TORCH_WOOD_CARD.get();
//			case TALL_NUT: return ItemRegister.TALL_NUT_CARD.get();
//			case SPLIT_PEA:return ItemRegister.SPLIT_PEA_CARD.get();
//			case PUMPKIN:return ItemRegister.PUMPKIN_CARD.get();
//			case CABBAGE_PULT:return ItemRegister.CABBAGE_PULT_CARD.get();
//			case FLOWER_POT:return ItemRegister.FLOWER_POT_CARD.get();
//			case KERNEL_PULT:return ItemRegister.KERNEL_PULT_CARD.get();
//			case COFFEE_BEAN:return ItemRegister.COFFEE_BEAN_CARD.get();
//			case MARIGOLD:return ItemRegister.MARIGOLD_CARD.get();
//			case MELON_PULT:return ItemRegister.MELON_PULT_CARD.get();
//			case GATLING_PEA:return ItemRegister.GATLING_PEA_CARD.get();
//			case TWIN_SUNFLOWER:return ItemRegister.TWIN_SUNFLOWER_CARD.get();
//			case CAT_TAIL:return ItemRegister.CAT_TAIL_CARD.get();
//			case WINTER_MELON:return ItemRegister.WINTER_MELON_CARD.get();
//			case SPIKE_ROCK:return ItemRegister.SPIKE_ROCK_CARD.get();
//			case ICEBERG_LETTUCE:return ItemRegister.ICEBERG_LETTUCE_CARD.get();
//			case GOLD_LEAF:return ItemRegister.GOLD_LEAF_CARD.get();
//			case LIGHTLING_ROD:return ItemRegister.LIGHTNING_ROD_CARD.get();
//			case STRANGE_CAT:return ItemRegister.STRANGE_CAT_CARD.get();
//			default:{
//				PVZMod.LOGGER.debug("WRONG ALMANAC TYPE");
//				return Items.AIR;
//			}
//			}
	}
	
//	/**
//	 * restore item from stack to backpack
//	 */
//	public static void restoreFromItemStack(ItemStack stack,Inventory backpack){
//		final CompoundNBT tag = stack.getTag();
//		if(tag != null && tag.contains("backpack")) {
//			final ListNBT list=(ListNBT) tag.get("backpack");
//			backpack.clear();
//			for(int i=0;i<list.size();i++) {
//				CompoundNBT stackTag=list.getCompound(i);
//				int id=stackTag.getInt("slot");
//				if(id>=0&&id<backpack.getSizeInventory()) {
//					final ItemStack itemstack=ItemStack.read(stackTag);
//					if(!itemstack.isEmpty()) {
//						backpack.setInventorySlotContents(id, itemstack);
//					}
//				}
//			}
//		}
//	}
//	
//	/**
//	 * 
//	 */
//	public static void convertToItemStack(ItemStack stack,Inventory backpack){
//		ListNBT list = new ListNBT();
//		for(int i = 0;i < backpack.getSizeInventory();i ++) {
//			final ItemStack itemstack = backpack.getStackInSlot(i);
//			if(!itemstack.isEmpty()) {
//				CompoundNBT stackTag = new CompoundNBT();
//				itemstack.write(stackTag);
//				stackTag.putInt("slot", i);
//				list.add(stackTag);
//			}
//		}
//		CompoundNBT old = stack.getOrCreateTag();
//		old.put("backpack", list);
//		stack.write(old);
//	}
}
