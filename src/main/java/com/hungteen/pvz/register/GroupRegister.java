package com.hungteen.pvz.register;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class GroupRegister {

	public static final ItemGroup PVZ_MISC = new ItemGroup("pvz_misc") {
		@Override
		public ItemStack makeIcon() {
			return new ItemStack(ItemRegister.PEA.get());
		}
	};
	public static final ItemGroup PVZ_CARD = new ItemGroup("pvz_card") {
		@Override
		public ItemStack makeIcon() {
			return new ItemStack(ItemRegister.PEA_SHOOTER_CARD.get());
		}
	};
	
}
