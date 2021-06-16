package com.hungteen.pvz.common.entity.ai.navigator;

import net.minecraft.entity.MobEntity;
import net.minecraft.pathfinding.GroundPathNavigator;
import net.minecraft.pathfinding.PathFinder;
import net.minecraft.pathfinding.WalkAndSwimNodeProcessor;
import net.minecraft.world.World;

public class ZombiePathNavigator extends GroundPathNavigator {

	public ZombiePathNavigator(MobEntity p_i45875_1_, World p_i45875_2_) {
		super(p_i45875_1_, p_i45875_2_);
	}

	protected PathFinder createPathFinder(int p_179679_1_) {
		this.nodeEvaluator = new WalkAndSwimNodeProcessor();
		this.nodeEvaluator.setCanPassDoors(true);
		return new PathFinder(this.nodeEvaluator, p_179679_1_);
	}

}
