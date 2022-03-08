package com.hungteen.pvz.common.entity.ai.navigator;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.entity.MobEntity;
import net.minecraft.pathfinding.GroundPathNavigator;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;

public class LavaZombiePathNavigator extends GroundPathNavigator {

	public LavaZombiePathNavigator(MobEntity p_i45875_1_, Level p_i45875_2_) {
		super(p_i45875_1_, p_i45875_2_);
	}

	@Override
	protected boolean hasValidPathType(PathNodeType p_230287_1_) {
		return p_230287_1_ != PathNodeType.LAVA && p_230287_1_ != PathNodeType.DAMAGE_FIRE
				&& p_230287_1_ != PathNodeType.DANGER_FIRE ? super.hasValidPathType(p_230287_1_) : true;
	}

	@Override
	public boolean isStableDestination(Mth p_188555_1_) {
		return this.level.getBlockState(p_188555_1_).is(Blocks.LAVA) || super.isStableDestination(p_188555_1_);
	}

}
