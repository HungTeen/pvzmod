package com.hungteen.pvz.common.entity.ai.goal.target;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.hungteen.pvz.utils.AlgorithmUtil;
import com.hungteen.pvz.utils.EntityUtil;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;

public class PVZNearestTargetGoal extends PVZTargetGoal {

	protected final AlgorithmUtil.EntitySorter sorter;

	public PVZNearestTargetGoal(MobEntity mobIn, boolean mustSee, boolean mustReach, float w, float h) {
		this(mobIn, mustSee, mustReach, w, h, h);
	}

	public PVZNearestTargetGoal(MobEntity mobIn, boolean mustSee, boolean mustReach, float w, float h1, float h2) {
		super(mobIn, mustSee, mustReach, w, h1, h2);
		this.sorter = new AlgorithmUtil.EntitySorter(mob);
	}

	@Override
	public boolean canUse() {
		if (this.targetChance > 0 && this.mob.getRandom().nextInt(this.targetChance) != 0) {
			return false;
		}
		List<LivingEntity> list1 = EntityUtil.getTargetableLivings(this.mob, getAABB()).stream().filter(target -> {
			return (! this.mustSee || this.checkSenses(target)) && this.checkOther(target);
		}).collect(Collectors.toList());
		if (list1.isEmpty()) {
			return false;
		}
		Collections.sort(list1, this.sorter);
		this.targetMob = list1.get(0);
		return true;
	}

}
