package com.hungteen.pvz.entity.plant.enforce;

import com.hungteen.pvz.entity.ai.PVZNearestTargetGoal;
import com.hungteen.pvz.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.misc.damage.PVZDamageSource;
import com.hungteen.pvz.register.EntityRegister;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.PlantUtil;
import com.hungteen.pvz.utils.enums.Plants;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class TangleKelpEntity extends PVZPlantEntity{

	public TangleKelpEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.targetSelector.addGoal(0, new PVZNearestTargetGoal(this, true, 1f, 2f));
	}
	
	@Override
	protected void normalPlantTick() {
		super.normalPlantTick();
		if(!world.isRemote) {
			if(this.getAttackTime()>0) {
				this.setAttackTime(this.getAttackTime()+1);
				if(this.getPassengers().isEmpty()) {
					this.remove();
					return ;
				}
				this.setMotion(0, - 0.03f, 0);
				if(this.getAttackTime()%100==0) {
					for(Entity target:this.getPassengers()) {
						target.attackEntityFrom(PVZDamageSource.causeNormalDamage(this, this), this.getAttackDamage());
					}
				}
				if(this.getAttackTime()>=1000) {
					this.remove();
				}
			}
			if(this.getAttackTime()==0){
				if(this.getAttackTarget()!=null) {
					this.setAttackTime(1);
					if(this.getAttackTarget().getRidingEntity()!=null) {
						this.getAttackTarget().stopRiding();
					}
					this.getAttackTarget().startRiding(this, true);
					EntityUtil.playSound(this, SoundRegister.DRAG.get());
				}
			}
		}
	}
	
	@Override
	public void startSuperMode(boolean first) {
		super.startSuperMode(first);
		if(!world.isRemote) {
			int cnt = this.getCount();
			for(Entity target:EntityUtil.getEntityTargetableEntity(this, EntityUtil.getEntityAABB(this, 25, 3))) {
				if(target.isInWater()) {
					-- cnt;
					TangleKelpEntity entity = EntityRegister.TANGLE_KELP.get().create(world);
					entity.setPosition(target.getPosX(), target.getPosY(), target.getPosZ());
					PlantUtil.copyPlantData(entity, this);
					world.addEntity(entity);
				}
				if(cnt<=0) {
					break;
				}
			}
		}
	}
	
	public float getAttackDamage(){
		int lvl = this.getPlantLvl();
		if(lvl <= 19) {
			return 13.75f + 0.25f * lvl;
		}
		return 19;
	}
	
	@Override
	public double getMountedYOffset() {
		return 0;
	}
	
	private int getCount(){
		if(this.isPlantInStage(1)) return 3;
		if(this.isPlantInStage(2)) return 4;
		return 5;
	}
	
	@Override
	public EntitySize getSize(Pose poseIn) {
		return new EntitySize(0.6f, 1f, false);
	}
	
	@Override
	protected boolean checkNormalPlantWeak() {//check if it leave water
		if(this.isImmuneToWeak) return false;
        return !this.isInWater();
	}
	
	@Override
	public boolean isPlantImmuneTo(DamageSource source) {
		return super.isPlantImmuneTo(source) || PVZDamageSource.isEnforceDamage(source);
	}
	
	@Override
	public boolean canBeRiddenInWater() {
		return true;
	}
	
	@Override
	public boolean canBeRiddenInWater(Entity rider) {
		return true;
	}
	
	@Override
	public boolean hasNoGravity() {
		return this.isInWater();
	}
	
	@Override
	public boolean canBreatheUnderwater() {
		return true;
	}
	
	@Override
	public Plants getPlantEnumName() {
		return Plants.TANGLE_KELP;
	}

	@Override
	public int getSuperTimeLength() {
		return 20;
	}

}
