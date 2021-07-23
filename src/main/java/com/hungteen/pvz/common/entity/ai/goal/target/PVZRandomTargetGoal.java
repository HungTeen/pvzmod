package com.hungteen.pvz.common.entity.ai.goal.target;

import java.util.List;
import java.util.stream.Collectors;

import com.hungteen.pvz.utils.EntityUtil;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;

public class PVZRandomTargetGoal extends PVZTargetGoal {

	public PVZRandomTargetGoal(MobEntity mobIn, boolean mustSee, boolean mustReach, float w, float h) {
		super(mobIn, mustSee, mustReach, w, h);
	}

	public PVZRandomTargetGoal(MobEntity mobIn, boolean mustSee, boolean mustReach, float w, float h1, float h2) {
		super(mobIn, mustSee, mustReach, w, h1, h2);
	}

	@Override
	public boolean canUse() {
		if (this.targetChance > 0 && this.mob.getRandom().nextInt(this.targetChance) != 0) {
			return false;
		} 
		List<LivingEntity> list1 = EntityUtil.getTargetableLivings(mob, getAABB()).stream().filter(target -> {
			return (! this.mustSee || this.checkSenses(target)) && this.checkOther(target);
		}).collect(Collectors.toList());
		if (list1.isEmpty()) {
			return false;
		}
		int pos = this.mob.getRandom().nextInt(list1.size());
		this.targetMob = list1.get(pos);
		return true;
	}

}
