package com.hungteen.pvz.entity.plant.toxic;

import com.hungteen.pvz.entity.ai.PVZNearestTargetGoal;
import com.hungteen.pvz.entity.bullet.FumeEntity;
import com.hungteen.pvz.entity.plant.base.PlantShooterEntity;
import com.hungteen.pvz.register.EntityRegister;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.enums.Plants;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class GloomShroomEntity extends PlantShooterEntity {

	protected final double LENTH = 0.2d;
	private static final int SHOOT_NUM = 8;
	
	public GloomShroomEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.targetSelector.addGoal(0, new PVZNearestTargetGoal(this, true, 5, getShootRange(), 2, 0));
	}
	
	@Override
	public void shootBullet() {
		LivingEntity target = this.getAttackTarget();
		if(target == null) {
			return ;
		}
		float now = this.rotationYawHead;
		boolean kb = (this.getRNG().nextInt(400) < this.getKBChance());
		for(int i = 0; i < SHOOT_NUM; ++ i) {
			this.shootFume(now, kb);
			now += 360F / SHOOT_NUM;
		}
        EntityUtil.playSound(this, SoundRegister.FUME.get());
	}
	
	private void shootFume(float angle, boolean kb) {
		angle *= 3.14159F / 180F;
		double vx = - MathHelper.sin(angle);
		double vz = MathHelper.cos(angle);
		FumeEntity fume = new FumeEntity(EntityRegister.FUME.get(), this.world, this);
        if(this.isPlantInSuperMode()) {
        	fume.setKnockback(this.getKnockback());
        } else {
        	if(kb) fume.setKnockback(1);
        }
        fume.setPosition(this.getPosX(), this.getPosY() + this.getEyeHeight() - 0.2, this.getPosZ());
        fume.setMotion(new Vec3d(vx, 0, vz));
        this.world.addEntity(fume);
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
    public EntitySize getSize(Pose poseIn) {
    	return EntitySize.flexible(0.9F, 0.8F);
    }
    
	@Override
	public Plants getPlantEnumName() {
		return Plants.GLOOM_SHROOM;
	}

}
