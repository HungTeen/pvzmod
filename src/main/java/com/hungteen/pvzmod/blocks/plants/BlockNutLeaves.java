package com.hungteen.pvzmod.blocks.plants;

import java.util.List;

import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks.EnumType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockNutLeaves extends BlockLeaves{

	@Override
	public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
		return null;
	}

	@Override
	public EnumType getWoodType(int meta) {
		return null;
	}

}
