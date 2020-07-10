package com.hungteen.pvzmod.registry;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CreativeTabRegister {

	public static final CreativeTabs MATERIAL_TAB = new CreativeTabs("Materials") {
		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(ItemRegister.STEEL_INGOT,1,0);
		}
	};
	
	public static final CreativeTabs TOOL_TAB = new CreativeTabs("Tools") {
		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(ItemRegister.STEEL_PICKAXE,1,0);
		}
	};
	
	public static final CreativeTabs WEAPON_TAB = new CreativeTabs("Weapons") {
		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(ItemRegister.STEEL_SWORD,1,0);
		}
	};
	
	public static final CreativeTabs BLOCK_TAB = new CreativeTabs("Blocks") {
		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(Item.getItemFromBlock(BlockRegister.STEEL_BLOCK),1,0);
		}
	};
	public static final CreativeTabs ARMOR_TAB = new CreativeTabs("Armors") {
		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(ItemRegister.STEEL_HELMET);
		}
	};
	public static final CreativeTabs CROP_TAB = new CreativeTabs("Crops") {
		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(Item.getItemFromBlock(BlockRegister.NUT_SAPLING));
		}
	};
	public static final CreativeTabs CARD_TAB = new CreativeTabs("Cards") {
		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(ItemRegister.BLUE_CARD);
		}
	};
	
}
