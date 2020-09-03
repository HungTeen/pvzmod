package com.hungteen.pvz.entity.plant.base;

import com.hungteen.pvz.entity.ai.PVZNearestTargetGoal;
import com.hungteen.pvz.entity.ai.ShooterAttackGoal;
import com.hungteen.pvz.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.utils.interfaces.IShooter;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.world.World;

public abstract class PlantShooterEntity extends PVZPlantEntity implements IShooter{

	public PlantShooterEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	protected void registerAttributes() {
		super.registerAttributes();
		this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(40.0D);
	}
	
	@Override
	protected void registerGoals() {
//		this.goalSelector.addGoal(8, new LookAtGoal(this, PlayerEntity.class, 8.0F));
	    this.goalSelector.addGoal(0, new ShooterAttackGoal(this));
	    this.targetSelector.addGoal(0, new PVZNearestTargetGoal(this, true, 40, 2));
	}

	@Override
	public void normalPlantTick() {
		super.normalPlantTick();
		if(!this.world.isRemote) {
		    if(this.getAttackTime()>0) {
			    this.shootBullet();
			    this.setAttackTime(this.getAttackTime()-1);
			}
		}
	}
	
	public static boolean checkY(double dy)
	{
		return dy>=-1&&dy<=1.5;
	}
}
