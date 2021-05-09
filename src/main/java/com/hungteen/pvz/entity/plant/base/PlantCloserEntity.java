package com.hungteen.pvz.entity.plant.base;

import com.hungteen.pvz.entity.ai.target.PVZNearestTargetGoal;
import com.hungteen.pvz.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.misc.damage.PVZDamageSource;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.interfaces.ICloser;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public abstract class PlantCloserEntity extends PVZPlantEntity implements ICloser{
	
	public PlantCloserEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.targetSelector.addGoal(0, new PVZNearestTargetGoal(this, true, this.getCloseWidth(), this.getCloseHeight()));
	}
	
	@Override
	protected void normalPlantTick() {
		super.normalPlantTick();
		if(EntityUtil.isEntityValid(this.getTarget())) {
			this.getLookControl().setLookAt(getTarget(), 30f, 30f);
			this.performAttack(this.getTarget());
		}
	}
	
	@Override
	public boolean isPlantImmuneTo(DamageSource source) {
		if(this.canBeImmuneToEnforce()) {
			return super.isPlantImmuneTo(source) || PVZDamageSource.isEnforceDamage(source);
		}
		return super.isPlantImmuneTo(source);
	}
	
	protected boolean canBeImmuneToEnforce() {
		return true;
	}
	
	@Override
	public int getSuperTimeLength() {
		return 20;
	}
	
	@Override
	public float getCloseWidth() {
		return 1;
	}

	@Override
	public float getCloseHeight() {
		return 1;
	}
	
}
