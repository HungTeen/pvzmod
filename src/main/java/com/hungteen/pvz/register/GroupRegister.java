package com.hungteen.pvz.register;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class GroupRegister {

	//材料
	public static final ItemGroup PVZ_MISC = new ItemGroup("pvz_misc") {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(ItemRegister.PEA.get());
		}
	};
//	//方块
//	public static final ItemGroup GROUP_BLOCKS = new PVZGroup("pvz_blocks", new ItemStack(ItemRegister.Pea.get()));
//	//工具
//	public static final ItemGroup GROUP_TOOLS = new PVZGroup("pvz_tools", new ItemStack(ItemRegister.Pea.get()));
	//植物卡
	public static final ItemGroup PVZ_CARD = new ItemGroup("pvz_card") {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(ItemRegister.PEA_SHOOTER_CARD.get());
		}
	};
	
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
