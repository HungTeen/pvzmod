package com.hungteen.pvzmod.blocks;

import java.util.Random;

import com.hungteen.pvzmod.items.tools.ToolPickaxe;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;

public class BlockGoldenTile extends BlockBase{

	public BlockGoldenTile(String name, Material material) {
		super(name, material);
		setHarvestLevel("pickaxe", 2);
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Item.getItemFromBlock(Blocks.GOLD_BLOCK);
	}
}
