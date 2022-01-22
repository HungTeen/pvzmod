package com.hungteen.pvz.common.entity.ai.goal;

import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.common.entity.zombie.PVZZombieEntity;

import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;

public class PVZLookRandomlyGoal extends LookRandomlyGoal{

	private MobEntity plant;
	
	public PVZLookRandomlyGoal(MobEntity entitylivingIn) {
		super(entitylivingIn);
		this.plant = entitylivingIn;
	}

	@Override
	public boolean canUse() {
		return this.canExecute() && super.canUse();
	}
	
	private boolean canExecute() {
		if(this.plant instanceof PVZPlantEntity && !((PVZPlantEntity) this.plant).canNormalUpdate()) {
			return false;
		}
		if(this.plant instanceof PVZZombieEntity && !((PVZZombieEntity) this.plant).canNormalUpdate()) {
			return false;
		}
		return true;
	}
	
	@Override
	public boolean canContinueToUse() {
		return this.canExecute() && super.canContinueToUse();
	}
}
