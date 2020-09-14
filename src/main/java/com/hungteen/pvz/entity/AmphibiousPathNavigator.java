package com.hungteen.pvz.entity;

import net.minecraft.entity.MobEntity;
import net.minecraft.pathfinding.PathFinder;
import net.minecraft.pathfinding.SwimmerPathNavigator;
import net.minecraft.pathfinding.WalkAndSwimNodeProcessor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class AmphibiousPathNavigator extends SwimmerPathNavigator {

	public AmphibiousPathNavigator(MobEntity entitylivingIn, World worldIn) {
		super(entitylivingIn, worldIn);
	}

	@Override
	protected boolean canNavigate() {
		return true;
	}

	@Override
	protected PathFinder getPathFinder(int p_179679_1_) {
		this.nodeProcessor = new WalkAndSwimNodeProcessor();
		return new PathFinder(this.nodeProcessor, p_179679_1_);
	}

	@Override
	public boolean canEntityStandOnPos(BlockPos pos) {
		return true;
	}
}
