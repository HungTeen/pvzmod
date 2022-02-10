package com.hungteen.pvz.common.entity.ai.navigator;

import net.minecraft.block.Blocks;
import net.minecraft.entity.MobEntity;
import net.minecraft.pathfinding.GroundPathNavigator;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class LavaZombiePathNavigator extends GroundPathNavigator {

	public LavaZombiePathNavigator(MobEntity p_i45875_1_, World p_i45875_2_) {
		super(p_i45875_1_, p_i45875_2_);
	}

	@Override
	protected boolean hasValidPathType(PathNodeType p_230287_1_) {
		return p_230287_1_ != PathNodeType.LAVA && p_230287_1_ != PathNodeType.DAMAGE_FIRE
				&& p_230287_1_ != PathNodeType.DANGER_FIRE ? super.hasValidPathType(p_230287_1_) : true;
	}

	@Override
	public boolean isStableDestination(BlockPos p_188555_1_) {
		return this.level.getBlockState(p_188555_1_).is(Blocks.LAVA) || super.isStableDestination(p_188555_1_);
	}

}
