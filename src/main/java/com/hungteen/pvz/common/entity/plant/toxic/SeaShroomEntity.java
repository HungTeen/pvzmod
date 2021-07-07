package com.hungteen.pvz.common.entity.plant.toxic;

import com.hungteen.pvz.common.entity.bullet.AbstractBulletEntity;
import com.hungteen.pvz.common.entity.bullet.itembullet.SporeEntity;
import com.hungteen.pvz.common.entity.plant.base.PlantShooterEntity;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.enums.Plants;
import com.hungteen.pvz.utils.enums.ShootTypes;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class SeaShroomEntity extends PlantShooterEntity {

	protected static final double SHOOT_OFFSET = 0.1D;
	
	public SeaShroomEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(2, new SwimGoal(this));
	}
	
	@Override
	public void shootBullet() {
		if(this.isPlantInSuperMode()) {
			this.performShoot(SHOOT_OFFSET, 0, 0, this.getExistTick() % 5 == 0, ShootTypes.FORWARD);
		} else {
			this.performShoot(SHOOT_OFFSET, 0, 0, this.getAttackTime() == 1, ShootTypes.FORWARD);
		}
	}
	
	@Override
	protected AbstractBulletEntity createBullet() {
		return new SporeEntity(this.level, this);
	}
	
	@Override
	protected SoundEvent getShootSound() {
		return SoundRegister.PUFF.get();
	}
	
	@Override
	public float getShootRange() {
		return 10;
	}
	
	@Override
	public void startShootAttack() {
		this.setAttackTime(1);
	}
	
	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return EntitySize.scalable(0.5f, 0.8f);
	}

	@Override
	public Plants getPlantEnumName() {
		return Plants.SEA_SHROOM;
	}

	@Override
	public int getSuperTimeLength() {
		if(this.isPlantInStage(1)) return 100;
		if(this.isPlantInStage(2)) return 120;
		return 140;
	}

}
