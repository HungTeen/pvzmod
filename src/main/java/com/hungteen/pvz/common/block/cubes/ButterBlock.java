package com.hungteen.pvz.common.block.cubes;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.BreakableBlock;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ButterBlock extends BreakableBlock {

	public ButterBlock() {
		super(Block.Properties.copy(Blocks.HONEY_BLOCK));
	}

	@Override
	public void fallOn(World worldIn, BlockPos pos, Entity entityIn, float fallDistance) {
		if (entityIn.isSuppressingBounce()) {
			super.fallOn(worldIn, pos, entityIn, fallDistance);
		} else {
			entityIn.causeFallDamage(fallDistance, 0.0F);
		}
	}

	@Override
	public void stepOn(World worldIn, BlockPos pos, Entity entityIn) {
		double d0 = Math.abs(entityIn.getDeltaMovement().y);
		if (d0 < 0.1D && !entityIn.isSteppingCarefully()) {
			double d1 = 0.4D + d0 * 0.2D;
			entityIn.setDeltaMovement(entityIn.getDeltaMovement().multiply(d1, 1.0D, d1));
		}
		super.stepOn(worldIn, pos, entityIn);
	}

}
