package com.hungteen.pvz.entity.plant.toxic;

import com.hungteen.pvz.entity.ai.target.PVZNearestTargetGoal;
import com.hungteen.pvz.entity.bullet.FumeEntity;
import com.hungteen.pvz.entity.plant.base.PlantShooterEntity;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.enums.Plants;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class FumeShroomEntity extends PlantShooterEntity {

	protected final double LENTH = 0.2d;
	
	public FumeShroomEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.targetSelector.addGoal(0, new PVZNearestTargetGoal(this, true, 5, getShootRange(), 2, 0));
	}
	
	@Override
	public void shootBullet() {
		LivingEntity target = this.getTarget();
		if(target == null) {
			return ;
		}
		double dx = target.getX() - this.getX();
        double dz = target.getZ() - this.getZ();
        double y = this.getY() + this.getDimensions(getPose()).height * 0.7f;
        double dis = MathHelper.sqrt(dx * dx + dz * dz);
        double tmp = this.LENTH / dis;
        double deltaX = tmp * dx;
        double deltaZ = tmp * dz;
        FumeEntity fume = new FumeEntity(this.level, this);
        if(this.isPlantInSuperMode()) {
        	fume.setKnockback(this.getKnockback());
        }
        fume.setPos(this.getX() + deltaX, y, this.getZ() + deltaZ);
        fume.shootPea(dx, target.getY() + target.getBbHeight() - y, dz, this.getBulletSpeed());
        EntityUtil.playSound(this, SoundRegister.FUME.get());
        this.level.addFreshEntity(fume);
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
