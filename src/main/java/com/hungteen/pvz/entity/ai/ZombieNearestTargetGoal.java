package com.hungteen.pvz.entity.ai;

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

//	@Override
//	protected boolean checkOther(LivingEntity entity) {
//		return super.checkOther(entity) && canEasilyReach(entity);
//	}
//
//	protected boolean canEasilyReach(LivingEntity target) {
//		Path path = this.goalOwner.getNavigator().getPathToEntity(target, 0);
//		if (path == null) {
//			return false;
//		} else {
//			PathPoint pathpoint = path.getFinalPathPoint();
//			if (pathpoint == null) {
//				return false;
//			} else {
//				double i = pathpoint.x - target.getPosX();
//				double j = pathpoint.z - target.getPosZ();
//				double k = pathpoint.y - target.getPosY();
//				return (double) (i * i + j * j + k * k) <= 2.25D;
//			}
//		}
//	}

}
