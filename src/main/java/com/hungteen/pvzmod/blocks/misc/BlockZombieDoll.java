package com.hungteen.pvzmod.blocks.misc;

import com.hungteen.pvzmod.util.SoundsHandler;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockZombieDoll extends BlockMiscBase{

	public BlockZombieDoll(String name, Material material) {
		super(name, material);
	}

	protected AxisAlignedBB getAABB()
	{
		return new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 2.0D, 1.0D);
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if(!worldIn.isRemote) {
			worldIn.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundsHandler.ZOMBIE_AMBIENT, SoundCategory.VOICE, 1f, 1f);
		}
		return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
	}
}
