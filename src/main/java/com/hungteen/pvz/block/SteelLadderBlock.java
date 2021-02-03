package com.hungteen.pvz.block;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.LadderBlock;
import net.minecraftforge.common.ToolType;

public class SteelLadderBlock extends LadderBlock {

	public SteelLadderBlock() {
		super(Block.Properties.from(Blocks.LADDER).hardnessAndResistance(6F).harvestTool(ToolType.PICKAXE));
	}

}
