package com.hungteen.pvz.common.entity.ai.processor;

import com.hungteen.pvz.common.entity.zombie.base.SwimmerZombieEntity;

import net.minecraft.entity.MobEntity;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.pathfinding.WalkAndSwimNodeProcessor;
import net.minecraft.world.Region;

public class ZombieNodeProcessor extends WalkAndSwimNodeProcessor {

	public void prepare(Region p_225578_1_, MobEntity mob) {
		super.prepare(p_225578_1_, mob);
		if(mob instanceof SwimmerZombieEntity) {
			mob.setPathfindingMalus(PathNodeType.WATER, 0.0F);
		    mob.setPathfindingMalus(PathNodeType.WALKABLE, 3F);
		    mob.setPathfindingMalus(PathNodeType.WATER_BORDER, 4.0F);
		} else {
			mob.setPathfindingMalus(PathNodeType.WATER, 2.0F);
		    mob.setPathfindingMalus(PathNodeType.WALKABLE, 0F);
		    mob.setPathfindingMalus(PathNodeType.WATER_BORDER, 4.0F);
		}
	}
	
}
