package com.hungteen.pvzmod.blocks.misc;

import java.util.Random;

import com.hungteen.pvzmod.blocks.BlockBase;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.math.AxisAlignedBB;

public class BlockFlowerPot extends BlockMiscBase{

	public BlockFlowerPot(String name, Material material) {
		super(name, material);
	}

	public IBlockState getState(EntityPlayer player)
	{
//		System.out.println(player.getHorizontalFacing().getOpposite());
		return this.getDefaultState().withProperty(FACING, player.getHorizontalFacing().getOpposite());
	}
	
	@Override
	protected AxisAlignedBB getAABB() {
	    return new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return null;
	}
}
