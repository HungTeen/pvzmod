package com.hungteen.pvz.common.entity.plant.toxic;

import com.hungteen.pvz.common.entity.bullet.AbstractBulletEntity;
import com.hungteen.pvz.common.entity.bullet.FumeEntity;
import com.hungteen.pvz.common.entity.plant.base.PlantShooterEntity;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.enums.Plants;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class GloomShroomEntity extends PlantShooterEntity {

	protected static final double SHOOT_OFFSET = 0.2D;
	private static final int SHOOT_NUM = 8;
	
	public GloomShroomEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	public void shootBullet() {
		LivingEntity target = this.getTarget();
		if(target == null) {
			return ;
		}
		float now = this.yHeadRot;
		for(int i = 0; i < SHOOT_NUM; ++ i) {
			this.shootFume(now);
			now += 360F / SHOOT_NUM;
		}
        EntityUtil.playSound(this, SoundRegister.FUME.get());
	}
	
	private void shootFume(float angle) {
		angle *= 3.14159F / 180F;
		double vx = - MathHelper.sin(angle);
		double vz = MathHelper.cos(angle);
		AbstractBulletEntity fume = this.createBullet();
        fume.setPos(this.getX(), this.getY() + this.getEyeHeight() - 0.2, this.getZ());
        fume.setDeltaMovement(new Vector3d(vx, 0, vz));
        this.level.addFreshEntity(fume);
	}
	
	@Override
	protected AbstractBulletEntity createBullet() {
		final FumeEntity fume = new FumeEntity(this.level, this);
        if(this.isPlantInSuperMode()) {
        	fume.setKnockback(this.getKnockback());
        }
        return fume;
	}
	
	/**
	 * get bullet knockback lvl in super mode
	 */
	public int getKnockback() {
		if(this.isPlantInStage(1)) return 1;
		if(this.isPlantInStage(2)) return 2;
		return 3;
	}
	
	public int getKBChance() {
		return this.getPlantLvl();
	}
	
	@Override
	public void startShootAttack() {
		this.setAttackTime(1);
	}
	
    @Override
	public int getSuperTimeLength() {
		return 100;
	}
    
    @Override
    public int getNormalAttackCD() {
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
	public Plants getPlantEnumName() {
		return Plants.GLOOM_SHROOM;
	}

}
