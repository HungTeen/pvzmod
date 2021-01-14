package com.hungteen.pvz.block.special;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

public class GoldTileBlock extends Block {

	public final int lvl;
	
	public GoldTileBlock(int lvl) {
		super(Properties.from(Blocks.GOLD_BLOCK).hardnessAndResistance(6.0F, 6.0F));
		this.lvl = lvl;
	}

}
