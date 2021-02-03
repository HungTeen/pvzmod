package com.hungteen.pvz.data.loot;

import java.util.Arrays;
import java.util.function.BiConsumer;

import com.hungteen.pvz.register.BlockRegister;

import net.minecraft.data.loot.BlockLootTables;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTable.Builder;

public class PVZBlockLootTables extends BlockLootTables {

//	private static final float[] DEFAULT_SAPLING_DROP_RATES = new float[] { 0.05F, 0.0625F, 0.083333336F, 0.1F };
//	private static final float[] RARE_SAPLING_DROP_RATES = new float[] { 0.025F, 0.027777778F, 0.03125F, 0.041666668F,
//			0.1F };

	@Override
	public void accept(BiConsumer<ResourceLocation, Builder> t) {
		Arrays.asList(BlockRegister.AMETHYST_ORE, BlockRegister.STEEL_BLOCK, BlockRegister.AMETHYST_BLOCK,
				BlockRegister.ORIGIN_BLOCK, BlockRegister.BUTTER_BLOCK, BlockRegister.CHOMPER, BlockRegister.LANTERN,
				BlockRegister.SUN_CONVERTER, BlockRegister.FRAGMENT_SPLICE, BlockRegister.SLOT_MACHINE,
				BlockRegister.ESSENCE_ALTAR, BlockRegister.CARD_FUSION_TABLE, BlockRegister.STEEL_LADDER
		).forEach((object) -> {
			    this.registerDropSelfLootTable(object.get());
		});
	}

}
