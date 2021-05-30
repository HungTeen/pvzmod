package com.hungteen.pvz.common.block.special;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

public class GoldTileBlock extends Block {

	public final int lvl;
	
	public GoldTileBlock(int lvl) {
		super(Properties.copy(Blocks.GOLD_BLOCK).strength(6.0F, 6.0F));
		this.lvl = lvl;
	}

}
