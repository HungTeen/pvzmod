package com.hungteen.pvz.common.entity.goal.target;

import com.hungteen.pvz.utils.EntityUtil;

import net.minecraft.entity.Entity;
import net.minecraft.entity.MobEntity;

public class PVZGlobalTargetGoal extends PVZNearestTargetGoal {

	public PVZGlobalTargetGoal(MobEntity mobIn, boolean checkSight, float w, float h) {
		super(mobIn, checkSight, w, h);
	}
	
	@Override
	protected boolean checkSenses(Entity entity) {
		return EntityUtil.checkCanSeeEntity(this.mob, entity);
	}

}
