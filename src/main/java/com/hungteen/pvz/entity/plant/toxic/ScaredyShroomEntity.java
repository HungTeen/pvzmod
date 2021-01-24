package com.hungteen.pvz.entity.plant.toxic;

import com.hungteen.pvz.entity.ai.target.PVZNearestTargetGoal;
import com.hungteen.pvz.entity.bullet.itembullet.SporeEntity;
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
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class ScaredyShroomEntity extends PlantShooterEntity {

	protected final double LENTH = 0.2d; //pea position offset
	private static final DataParameter<Integer> SCARE_TIME = EntityDataManager.createKey(ScaredyShroomEntity.class, DataSerializers.VARINT);
	public static final int ANIM_TIME = 15;
	
	public ScaredyShroomEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	protected void registerData() {
		super.registerData();
		this.dataManager.register(SCARE_TIME, 0);
	}
	
	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.targetSelector.addGoal(0, new PVZNearestTargetGoal(this, true, 5, getShootRange(), 2, 0));
	}
	
	@Override
	public void normalPlantTick() {
		super.normalPlantTick();
		this.recalculateSize();
		if(!this.world.isRemote) {
			int time = MathHelper.clamp(this.getScareTime() - 1, 0, ANIM_TIME);
			if(this.getAttackTarget() != null) {//has target
				double dis = getScareDistance();
				if(this.getDistanceSq(this.getAttackTarget()) <= dis * dis) {//close to this
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
	public EntitySize getSize(Pose poseIn) {
		return EntitySize.flexible(0.6f, 1.6f - this.getScareTime() * 1.0f / ANIM_TIME);
	}

	@Override
	public void shootBullet() {
		LivingEntity target = this.getAttackTarget();
		if(target == null || this.isScared()) {
			return ;
		}
		double dx = target.getPosX() - this.getPosX();
        double dz = target.getPosZ() - this.getPosZ();
        double y = this.getPosY() + this.getSize(getPose()).height * 0.7f;
        double dis = MathHelper.sqrt(dx * dx + dz * dz);
        double tmp = this.LENTH / dis;
        double deltaX = tmp * dx;
        double deltaZ = tmp * dz;
        SporeEntity spore = new SporeEntity(EntityRegister.SPORE.get(), this.world, this); 
        spore.setPosition(this.getPosX() + deltaX, y, this.getPosZ() + deltaZ);
        spore.shootPea(dx, target.getPosY() + target.getHeight() - y, dz, this.getBulletSpeed());
        EntityUtil.playSound(this, SoundRegister.PUFF.get());
        this.world.addEntity(spore);
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
    	return this.dataManager.get(SCARE_TIME);
    }
    
    public void setScareTime(int time) {
    	this.dataManager.set(SCARE_TIME, time);
    }
    
    public boolean isScared() {
    	return this.getScareTime() > 0;
    }
    
	@Override
	public Plants getPlantEnumName() {
		return Plants.SCAREDY_SHROOM;
	}
	
	@Override
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		compound.putInt("scare_time", this.getScareTime());
	}
	
	@Override
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		if(compound.contains("scare_time")) {
			this.setScareTime(compound.getInt("scare_time"));
		}
	}

}
