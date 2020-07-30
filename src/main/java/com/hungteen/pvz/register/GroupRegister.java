package com.hungteen.pvz.register;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class GroupRegister {

	//材料
	public static final ItemGroup PVZ_GROUP = new ItemGroup("pvz_group") {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(ItemRegister.PEA.get());
		}
	};
//	//方块
//	public static final ItemGroup GROUP_BLOCKS = new PVZGroup("pvz_blocks", new ItemStack(ItemRegister.Pea.get()));
//	//工具
//	public static final ItemGroup GROUP_TOOLS = new PVZGroup("pvz_tools", new ItemStack(ItemRegister.Pea.get()));
//	//植物卡
//	public static final ItemGroup GROUP_CARDS = new PVZGroup("pvz_cards", new ItemStack(ItemRegister.Pea.get()));
	
//	public static class PVZGroup extends ItemGroup
//	{
//	    ItemStack displayStack;
//		public PVZGroup(String label,ItemStack stack) {
//			super(label);
//			this.displayStack=stack;
//		}
//
//		@Override
//		public ItemStack createIcon() {
//			return this.displayStack;
//		}
//	}
}
