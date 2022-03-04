package com.hungteen.pvz.common;

import com.hungteen.pvz.common.block.BlockRegister;
import com.hungteen.pvz.common.item.ItemRegister;
import net.minecraft.block.ComposterBlock;
import net.minecraft.util.IItemProvider;

public class CommonRegister {

	public static void registerCompostable() {
		registerCompostable(0.3F, BlockRegister.NUT_LEAVES.get());
		registerCompostable(0.3F, BlockRegister.NUT_SAPLING.get());
		registerCompostable(0.3F, ItemRegister.PEA.get());
		registerCompostable(0.3F, ItemRegister.CABBAGE_SEEDS.get());
		registerCompostable(0.3F, ItemRegister.CORN_SEEDS.get());
		registerCompostable(0.4F, ItemRegister.NUT.get());
		registerCompostable(0.5F, ItemRegister.CABBAGE.get());
		registerCompostable(0.5F, ItemRegister.PEPPER.get());
		registerCompostable(0.5F, ItemRegister.CORN.get());
	}

	private static void registerCompostable(float chance, IItemProvider itemIn) {
		ComposterBlock.COMPOSTABLES.put(itemIn.asItem(), chance);
	}
	
}
