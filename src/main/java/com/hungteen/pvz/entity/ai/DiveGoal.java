package com.hungteen.pvz.entity.ai;

import java.util.EnumSet;

import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.math.Vec3d;

public class DiveGoal extends Goal {

	private static final float UP_SPEED = 0.05f;
	private final MobEntity entity;

	public DiveGoal(MobEntity entity) {
		this.setMutexFlags(EnumSet.of(Goal.Flag.JUMP));
		this.entity = entity;
	}

	@Override
	public boolean shouldExecute() {
		return this.entity.isInWater() && this.entity.getSubmergedHeight() > this.entity.getEyeHeight();
	}

	@Override
	public void tick() {
		Vec3d v = this.entity.getMotion();
		this.entity.setMotion(v.getX(), UP_SPEED, v.getZ());
	}

}
