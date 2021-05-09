package com.hungteen.pvz.entity.plant.toxic;

import com.hungteen.pvz.entity.ai.target.PVZNearestTargetGoal;
import com.hungteen.pvz.entity.bullet.itembullet.SporeEntity;
import com.hungteen.pvz.entity.plant.base.PlantShooterEntity;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.enums.Plants;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class ScaredyShroomEntity extends PlantShooterEntity {

	protected final double LENTH = 0.2d; //pea position offset
	private static final DataParameter<Integer> SCARE_TIME = EntityDataManager.defineId(ScaredyShroomEntity.class, DataSerializers.INT);
	public static final int ANIM_TIME = 15;
	
	public ScaredyShroomEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(SCARE_TIME, 0);
	}
	
	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.targetSelector.addGoal(0, new PVZNearestTargetGoal(this, true, 5, getShootRange(), 2, 0));
	}
	
	@Override
	public void normalPlantTick() {
		super.normalPlantTick();
		this.refreshDimensions();
		if(!this.level.isClientSide) {
			int time = MathHelper.clamp(this.getScareTime() - 1, 0, ANIM_TIME);
			if(this.getTarget() != null) {//has target
				double dis = getScareDistance();
				if(this.distanceToSqr(this.getTarget()) <= dis * dis) {//close to this
					time = MathHelper.clamp(this.getScareTime() + 1, 0, ANIM_TIME);
				}
			}
			this.setScareTime(time);
		}
	}
	
	public float getScareDistance() {
		int lvl = this.getPlantLvl();
		if(lvl <= 20) {
			int now = (lvl - 1) / 5;
			return 5 - now;
		}
		return 2;
	}
	
	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return EntitySize.scalable(0.6f, 1.6f - this.getScareTime() * 1.0f / ANIM_TIME);
	}

	@Override
	public void shootBullet() {
		LivingEntity target = this.getTarget();
		if(target == null || this.isScared()) {
			return ;
		}
		double dx = target.getX() - this.getX();
        double dz = target.getZ() - this.getZ();
        double y = this.getY() + this.getDimensions(getPose()).height * 0.7f;
        double dis = MathHelper.sqrt(dx * dx + dz * dz);
        double tmp = this.LENTH / dis;
        double deltaX = tmp * dx;
        double deltaZ = tmp * dz;
        SporeEntity spore = new SporeEntity(this.level, this); 
        spore.setPos(this.getX() + deltaX, y, this.getZ() + deltaZ);
        spore.shootPea(dx, target.getY() + target.getBbHeight() - y, dz, this.getBulletSpeed());
        EntityUtil.playSound(this, SoundRegister.PUFF.get());
        this.level.addFreshEntity(spore);
	}

	@Override
	public void startShootAttack() {
		this.setAttackTime(1);
	}
	
	@Override
	public float getBulletSpeed() {
		return 1.2f;
	}
	
	@Override
	public int getSuperTimeLength() {
		if(this.isPlantInStage(1)) return 100;
		if(this.isPlantInStage(2)) return 120;
		return 140;
	}
    
    public int getScareTime() {
    	return this.entityData.get(SCARE_TIME);
    }
    
    public void setScareTime(int time) {
    	this.entityData.set(SCARE_TIME, time);
    }
    
    public boolean isScared() {
    	return this.getScareTime() > 0;
    }
    
	@Override
	public Plants getPlantEnumName() {
		return Plants.SCAREDY_SHROOM;
	}
	
	@Override
	public void addAdditionalSaveData(CompoundNBT compound) {
		super.addAdditionalSaveData(compound);
		compound.putInt("scare_time", this.getScareTime());
	}
	
	@Override
	public void readAdditionalSaveData(CompoundNBT compound) {
		super.readAdditionalSaveData(compound);
		if(compound.contains("scare_time")) {
			this.setScareTime(compound.getInt("scare_time"));
		}
	}

}
