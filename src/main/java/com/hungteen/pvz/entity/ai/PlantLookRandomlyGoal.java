package com.hungteen.pvz.entity.ai;

import com.hungteen.pvz.entity.plant.PVZPlantEntity;

import net.minecraft.entity.ai.goal.LookRandomlyGoal;

public class PlantLookRandomlyGoal extends LookRandomlyGoal{

	private PVZPlantEntity plant;
	
	public PlantLookRandomlyGoal(PVZPlantEntity entitylivingIn) {
		super(entitylivingIn);
		this.plant = entitylivingIn;
	}

	@Override
	public boolean shouldExecute() {
		return !this.plant.isPlantSleeping() && super.shouldExecute();
	}
	
	@Override
	public boolean shouldContinueExecuting() {
		return !this.plant.isSleeping() && super.shouldContinueExecuting();
	}
}
