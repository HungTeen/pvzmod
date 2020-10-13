package com.hungteen.pvz.entity.ai;

import com.hungteen.pvz.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.entity.zombie.PVZZombieEntity;

import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;

public class PVZLookRandomlyGoal extends LookRandomlyGoal{

	private MobEntity plant;
	
	public PVZLookRandomlyGoal(MobEntity entitylivingIn) {
		super(entitylivingIn);
		this.plant = entitylivingIn;
	}

	@Override
	public boolean shouldExecute() {
		return this.canExecute() && super.shouldExecute();
	}
	
	private boolean canExecute() {
		if(this.plant instanceof PVZPlantEntity && !((PVZPlantEntity) this.plant).canPlantNormalUpdate()) {
			return false;
		}
		if(this.plant instanceof PVZZombieEntity && !((PVZZombieEntity) this.plant).canZombieNormalUpdate()) {
			return false;
		}
		return true;
	}
	
	@Override
	public boolean shouldContinueExecuting() {
		return this.canExecute() && super.shouldContinueExecuting();
	}
}
