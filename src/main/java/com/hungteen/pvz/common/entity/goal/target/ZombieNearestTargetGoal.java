package com.hungteen.pvz.common.entity.goal.target;

import com.hungteen.pvz.utils.EntityUtil;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;

public class ZombieNearestTargetGoal extends PVZNearestTargetGoal {

	public ZombieNearestTargetGoal(MobEntity mobIn, boolean checkSight, float w, float h) {
		this(mobIn, checkSight, 5, w, h);
	}

	public ZombieNearestTargetGoal(MobEntity mobIn, boolean checkSight, int chance, float w, float h) {
		this(mobIn, checkSight, chance, w, h, h);
	}

	public ZombieNearestTargetGoal(MobEntity mobIn, boolean checkSight, int chance, float w, float h1, float h2) {
		super(mobIn, checkSight, chance, w, h1, h2);
	}

	@Override
	public boolean canContinueToUse() {
		LivingEntity entity = this.mob.getTarget();
		if (entity == null) {
			entity = this.targetMob;
		}
		if (entity == null || ! entity.isAlive()) {
			return false;
		}
		if (EntityUtil.checkCanEntityTarget(mob, entity) && entity != this.mob) {
			if (this.checkOther(entity)) {
				this.mob.setTarget(entity);
				return true;
			}
		}
		return false;
	}

}
