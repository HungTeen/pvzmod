package com.hungteen.pvz.common.entity.ai.goal;

import java.util.EnumSet;

import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.tags.FluidTags;

public class PVZSwimGoal extends Goal {

	protected final MobEntity mob;

	public PVZSwimGoal(MobEntity entity) {
		this.mob = entity;
		this.setFlags(EnumSet.of(Goal.Flag.JUMP));
		this.mob.getNavigation().setCanFloat(true);
	}

	@Override
	public boolean canUse() {
		return this.mob.isInWater() && this.mob.getFluidHeight(FluidTags.WATER) > this.mob.getFluidJumpThreshold();
	}

	public void tick() {
		if (this.mob.getRandom().nextFloat() < 0.8F) {
			this.mob.getJumpControl().jump();
		}
	}

}
