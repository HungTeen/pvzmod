package com.hungteen.pvz.block;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.BreakableBlock;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ButterBlock extends BreakableBlock {

	public ButterBlock() {
		super(Block.Properties.from(Blocks.HONEY_BLOCK));
	}

	@Override
	public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance) {
		if (entityIn.isSuppressingBounce()) {
			super.onFallenUpon(worldIn, pos, entityIn, fallDistance);
		} else {
			entityIn.onLivingFall(fallDistance, 0.0F);
		}
	}

	@Override
	public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
		double d0 = Math.abs(entityIn.getMotion().y);
		if (d0 < 0.1D && !entityIn.isSteppingCarefully()) {
			double d1 = 0.4D + d0 * 0.2D;
			entityIn.setMotion(entityIn.getMotion().mul(d1, 1.0D, d1));
		}
		super.onEntityWalk(worldIn, pos, entityIn);
	}

}
