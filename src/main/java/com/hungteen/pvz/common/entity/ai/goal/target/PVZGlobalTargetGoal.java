package com.hungteen.pvz.common.entity.ai.goal.target;

import com.hungteen.pvz.utils.EntityUtil;

import net.minecraft.entity.Entity;
import net.minecraft.entity.MobEntity;

public class PVZGlobalTargetGoal extends PVZNearestTargetGoal {

	public PVZGlobalTargetGoal(MobEntity mobIn, boolean checkSight, boolean memory, float w, float h) {
		super(mobIn, checkSight, memory, w, h);
	}
	
	@Override
	protected boolean checkSenses(Entity entity) {
		return EntityUtil.canSeeEntity(this.mob, entity);
	}

}
