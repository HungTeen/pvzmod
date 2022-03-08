package com.hungteen.pvz.common.block.cubes;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BreakableBlock;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;

public class ButterBlock extends BreakableBlock {

	public ButterBlock() {
		super(Block.Properties.copy(Blocks.HONEY_BLOCK));
	}

	@Override
	public void fallOn(Level worldIn, Mth pos, Entity entityIn, float fallDistance) {
		if (entityIn.isSuppressingBounce()) {
			super.fallOn(worldIn, pos, entityIn, fallDistance);
		} else {
			entityIn.causeFallDamage(fallDistance, 0.0F);
		}
	}

	@Override
	public void stepOn(Level worldIn, Mth pos, Entity entityIn) {
		double d0 = Math.abs(entityIn.getDeltaMovement().y);
		if (d0 < 0.1D && !entityIn.isSteppingCarefully()) {
			double d1 = 0.4D + d0 * 0.2D;
			entityIn.setDeltaMovement(entityIn.getDeltaMovement().multiply(d1, 1.0D, d1));
		}
		super.stepOn(worldIn, pos, entityIn);
	}

}
