package com.hungteen.pvz.common.item;

import com.hungteen.pvz.common.enchantment.PVZEnchantmentTypes;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class PVZItemGroups {

	public static final ItemGroup PVZ_MISC = new ItemGroup("pvz_misc") {
		@Override
		public ItemStack makeIcon() {
			return new ItemStack(ItemRegister.PEA.get());
		}

	}.setEnchantmentCategories(PVZEnchantmentTypes.getPVZEnchantmentTypes());
	
	public static final ItemGroup PVZ_PLANT_CARD = new ItemGroup("pvz_plant_card") {
		@Override
		public ItemStack makeIcon() {
			return new ItemStack(ItemRegister.PEA_SHOOTER_CARD.get());
		}
	};
	
	public static final ItemGroup PVZ_USEFUL = new ItemGroup("pvz_useful") {
		@Override
		public ItemStack makeIcon() {
			return new ItemStack(ItemRegister.PEA_GUN.get());
		}
	};
	
	public static final ItemGroup PVZ_ENVELOPE = new ItemGroup("pvz_envelope") {
		@Override
		public ItemStack makeIcon() {
			return new ItemStack(ItemRegister.CHALLENGE_ENVELOPE.get());
		}
	};
	
}
