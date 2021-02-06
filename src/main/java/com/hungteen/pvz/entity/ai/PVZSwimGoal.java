package com.hungteen.pvz.entity.ai;

import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.SwimGoal;

public class PVZSwimGoal extends SwimGoal {

	protected final MobEntity entity;

	public PVZSwimGoal(MobEntity entityIn) {
		super(entityIn);
		this.entity = entityIn;
	}

	@Override
	public boolean shouldExecute() {
		double d0 = (double) this.entity.getEyeHeight() < 0.4D ? 0D : 0.4D;
		return this.entity.getSubmergedHeight() > d0 && (this.entity.isInWater() || this.entity.isInLava());
	}

}
