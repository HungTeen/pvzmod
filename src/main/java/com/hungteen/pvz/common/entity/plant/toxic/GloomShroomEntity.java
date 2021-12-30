package com.hungteen.pvz.common.entity.plant.toxic;

import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.entity.bullet.AbstractBulletEntity;
import com.hungteen.pvz.common.entity.bullet.FumeEntity;
import com.hungteen.pvz.common.entity.plant.base.PlantShooterEntity;
import com.hungteen.pvz.common.impl.plant.PVZPlants;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.world.World;

public class GloomShroomEntity extends PlantShooterEntity {

	private static final float SHOOT_HEIGHT = 0.2F;
	private static final int SHOOT_NUM = 8;
	
	public GloomShroomEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	public void shootBullet() {
		float now = this.yHeadRot;
		for(int i = 0; i < SHOOT_NUM; ++ i) {
			this.shootByAngle(now, SHOOT_HEIGHT);
			now += 360F / SHOOT_NUM;
		}
        EntityUtil.playSound(this, SoundRegister.FUME.get());
	}
	
	@Override
	protected AbstractBulletEntity createBullet() {
		final FumeEntity fume = new FumeEntity(this.level, this);
        if(this.isPlantInSuperMode()) {
        	fume.setKnockback(1);
        }
        return fume;
	}
	
	@Override
	public void startShootAttack() {
		this.setAttackTime(1);
	}
	
    @Override
	public int getSuperTimeLength() {
		return 60;
//		return this.getThreeStage(60, 80, 100);
	}
    
    @Override
    public int getShootCD() {
    	return 8;
    }
    
    @Override
	public float getShootRange() {
		return 4;
	}
    
    @Override
    public EntitySize getDimensions(Pose poseIn) {
    	return EntitySize.scalable(0.9F, 0.8F);
    }
    
    @Override
	public IPlantType getPlantType() {
		return PVZPlants.GLOOM_SHROOM;
	}

}
