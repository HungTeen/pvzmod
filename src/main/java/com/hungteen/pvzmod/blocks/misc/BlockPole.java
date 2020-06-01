package com.hungteen.pvzmod.blocks.misc;

import com.hungteen.pvzmod.blocks.BlockBase;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockPole extends BlockMiscBase{

	public BlockPole(String name, Material material) {
		super(name, material);
	}
	
	protected AxisAlignedBB getAABB()
	{
		return new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 2.0D, 1.0D);
	}
}
