package com.hungteen.pvz.common.entity.plant.enforce;

import com.hungteen.pvz.common.entity.goal.target.PVZNearestTargetGoal;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.common.misc.damage.PVZDamageSource;
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
		if(!level.isClientSide) {
			if(this.getAttackTime()>0) {
				this.setAttackTime(this.getAttackTime()+1);
				if(this.getPassengers().isEmpty()) {
					this.remove();
					return ;
				}
				this.setDeltaMovement(0, - 0.03f, 0);
				if(this.getAttackTime()%100==0) {
					for(Entity target:this.getPassengers()) {
						target.hurt(PVZDamageSource.causeNormalDamage(this, this), this.getAttackDamage());
					}
				}
				if(this.getAttackTime()>=1000) {
					this.remove();
				}
			}
			if(this.getAttackTime()==0){
				if(this.getTarget()!=null) {
					this.setAttackTime(1);
					if(this.getTarget().getVehicle()!=null) {
						this.getTarget().stopRiding();
					}
					this.getTarget().startRiding(this, true);
					EntityUtil.playSound(this, SoundRegister.DRAG.get());
				}
			}
		}
	}
	
	@Override
	public void startSuperMode(boolean first) {
		super.startSuperMode(first);
		if(!level.isClientSide) {
			int cnt = this.getCount();
			for(Entity target:EntityUtil.getEntityTargetableEntity(this, EntityUtil.getEntityAABB(this, 25, 3))) {
				if(target.isInWater()) {
					-- cnt;
					TangleKelpEntity entity = EntityRegister.TANGLE_KELP.get().create(level);
					entity.setPos(target.getX(), target.getY(), target.getZ());
					PlantUtil.copyPlantData(entity, this);
					level.addFreshEntity(entity);
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
	public double getPassengersRidingOffset() {
		return 0;
	}
	
	private int getCount(){
		if(this.isPlantInStage(1)) return 3;
		if(this.isPlantInStage(2)) return 4;
		return 5;
	}
	
	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return new EntitySize(0.6f, 1f, false);
	}
	
	@Override
	public boolean isPlantImmuneTo(DamageSource source) {
		return super.isPlantImmuneTo(source) || PVZDamageSource.isEnforceDamage(source);
	}
	
	@Override
	public boolean rideableUnderWater() {
		return true;
	}
	
	@Override
	public boolean canBeRiddenInWater(Entity rider) {
		return true;
	}
	
	@Override
	public boolean isNoGravity() {
		return this.isInWater();
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
