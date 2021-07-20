package com.hungteen.pvz.common.entity.plant.spear;

import com.hungteen.pvz.api.interfaces.IHasWheel;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.common.misc.damage.PVZDamageSource;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.PlantUtil;
import com.hungteen.pvz.utils.enums.Plants;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class SpikeWeedEntity extends PVZPlantEntity {

	private static final DataParameter<Integer> SPIKE_NUM = EntityDataManager.defineId(SpikeWeedEntity.class, DataSerializers.INT);
	
	public SpikeWeedEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
		this.setSpikeNum(this.getSpikesCount());
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(SPIKE_NUM, 1);
	}
	
	@Override
	protected void normalPlantTick() {
		super.normalPlantTick();
		if(! level.isClientSide) {
			if(this.getSpikeNum() < 0) {
				this.remove();
			}
			if(this.getAttackTime() > 0) {
				this.setAttackTime(this.getAttackTime() - 1);
			}
			if(this.isPlantInSuperMode() && this.getSuperTime() % 10 == 0) {
				for(LivingEntity target : EntityUtil.getTargetableLivings(this, EntityUtil.getEntityAABB(this, 10, 2))) {
					target.hurt(PVZDamageSource.causeThornDamage(this), this.getAttackDamage() * 2);
					for(int i = 0; i < 4; ++ i) {
						EntityUtil.spawnParticle(target, 6);
					}
				}
			}
		}
	}
	
	@Override
	protected void doPush(Entity target) {
		super.doPush(target);
		if(! this.level.isClientSide && this.canPlantNormalUpdate() && this.getAttackTime() == 0) {
			if(this.getSpikeNum() > 0 && EntityUtil.canTargetEntity(this, target)) {
                this.spikeNormalAttack(target);
			}
		}
	}
	
	/**
	 * {@link #doPush(Entity)}
	 */
	public void spikeNormalAttack(Entity target) {
		this.setAttackTime(this.getAttackCD());
		if(target instanceof IHasWheel) {
			((IHasWheel) target).spikeWheelBy(this);
			this.setSpikeNum(this.getSpikeNum() - 1);
			if(this.getSpikeNum() <= 0) {
				this.remove();
			}
		} else {
			target.hurt(PVZDamageSource.causeThornDamage(this), this.getAttackDamage());
		}
	}
	
	@Override
	public boolean hurt(DamageSource source, float amount) {
		if(source instanceof PVZDamageSource) {
			if(((PVZDamageSource) source).isCrushDamage()) {
				if(this.getSpikeNum() >= 0) {
					this.setSpikeNum(this.getSpikeNum() - 1);
					return false;
				}
			}
		}
		return super.hurt(source, amount);
	}
	
	@Override
	protected boolean shouldCollideWithEntity(LivingEntity target) {
		if(EntityUtil.canTargetEntity(this, target)) {
			return false;
		}
		return super.shouldCollideWithEntity(target);
	}
	
	@Override
	public boolean canBeTargetBy(LivingEntity living) {
		return false;
	}
	
	public float getAttackDamage(){
		return PlantUtil.getPlantAverageProgress(this, 2F, 6F);
	}
	
	public int getAttackCD() {
		return 40;
	}
	
	public int getSpikesCount() {
		return 1;
	}
	
	@Override
	public int getSuperTimeLength() {
		if(this.isPlantInStage(1)) return 65;
		if(this.isPlantInStage(2)) return 85;
		return 105;
	}
	
	@Override
	public void readAdditionalSaveData(CompoundNBT compound) {
		super.readAdditionalSaveData(compound);
		if(compound.contains("spike_num")) {
			this.setSpikeNum(compound.getInt("spike_num"));
		}
	}
	
	@Override
	public void addAdditionalSaveData(CompoundNBT compound) {
		super.addAdditionalSaveData(compound);
		compound.putInt("spike_num", this.getSpikeNum());
	}
	
	public int getSpikeNum() {
		return this.entityData.get(SPIKE_NUM);
	}
	
	public void setSpikeNum(int num) {
		this.entityData.set(SPIKE_NUM, num);
	}
	
	@Override
	public Plants getUpgradePlantType() {
		return Plants.SPIKE_ROCK;
	}
	
    @Override
	public Plants getPlantEnumName() {
		return Plants.SPIKE_WEED;
	}

}
