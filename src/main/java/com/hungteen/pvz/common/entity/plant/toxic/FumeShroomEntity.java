package com.hungteen.pvz.common.entity.plant.toxic;

import com.hungteen.pvz.common.entity.ai.goal.target.PVZNearestTargetGoal;
import com.hungteen.pvz.common.entity.bullet.AbstractBulletEntity;
import com.hungteen.pvz.common.entity.bullet.FumeEntity;
import com.hungteen.pvz.common.entity.plant.base.PlantShooterEntity;
import com.hungteen.pvz.utils.enums.Plants;
import com.hungteen.pvz.utils.enums.ShootTypes;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.world.World;

public class FumeShroomEntity extends PlantShooterEntity {

	protected static final double SHOOT_OFFSET = 0.2D;
	
	public FumeShroomEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.targetSelector.addGoal(0, new PVZNearestTargetGoal(this, true, false, 5, getShootRange(), 2, 0));
	}
	
	@Override
	public void shootBullet() {
		this.performShoot(SHOOT_OFFSET, 0, 0, this.getAttackTime() == 1, ShootTypes.FORWARD);
	}
	
	@Override
	protected AbstractBulletEntity createBullet() {
		final FumeEntity fume = new FumeEntity(this.level, this);
        if(this.isPlantInSuperMode()) {
        	fume.setKnockback(this.getKnockback());
        }
        return fume;
	}

	@Override
	public void startShootAttack() {
		this.setAttackTime(1);
	}
	
	@Override
	public int getSuperTimeLength() {
		if(this.isPlantInStage(1)) return 40;
		if(this.isPlantInStage(2)) return 50;
		return 60;
	}
	
	/**
	 * get bullet knockback lvl in super mode
	 */
	public int getKnockback() {
		if(this.isPlantInStage(1)) return 1;
		if(this.isPlantInStage(2)) return 2;
		return 3;
	}
    
	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return EntitySize.scalable(0.8f, 1.25f);
	}
	
    @Override
	public float getShootRange() {
		return 20;
	}

    @Override
    public Plants getUpgradePlantType() {
    	return Plants.GLOOM_SHROOM;
    }
    
	@Override
	public Plants getPlantEnumName() {
		return Plants.FUME_SHROOM;
	}
	
}
