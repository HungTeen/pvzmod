package com.hungteen.pvz.block;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

public class EssenceOreBlock extends Block{

	public EssenceOreBlock() {
		super(Block.Properties.from(Blocks.DIAMOND_ORE).hardnessAndResistance(9, 9).lightValue(10).harvestLevel(3));
	}

}
