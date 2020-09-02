package com.hungteen.pvz.entity.plant.base;

import com.hungteen.pvz.entity.ai.PVZNearestTargetGoal;
import com.hungteen.pvz.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.utils.interfaces.ICloser;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
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
		if(this.getAttackTarget()!=null) {
			this.getLookController().setLookPositionWithEntity(getAttackTarget(), 30f, 30f);
			if(this.performAttack()) {
			    this.setHealth(0);//go to on death update
			}
		}
	}
	
	@Override
	protected void onDeathUpdate() {
		this.spawnParticle();
		this.remove();
	}
	
	@Override
	public void spawnParticle() {		
	}
	
	@Override
	public boolean hasSuperMode() {
		return true;
	}

	@Override
	public int getSuperTimeLength() {
		return 2;
	}

	@Override
	public float getCloseHeight() {
		return 1;
	}
}
