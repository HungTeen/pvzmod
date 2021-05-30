package com.hungteen.pvz.common.entity.plant.spear;

import java.util.List;

import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.common.entity.zombie.poolday.BobsleTeamEntity;
import com.hungteen.pvz.common.entity.zombie.poolday.ZomboniEntity;
import com.hungteen.pvz.common.entity.zombie.roof.CatapultZombieEntity;
import com.hungteen.pvz.common.misc.damage.PVZDamageSource;
import com.hungteen.pvz.common.misc.damage.PVZDamageType;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.enums.Plants;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SpawnReason;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.GameRules;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;

public class SpikeRockEntity extends PVZPlantEntity {

	private static final DataParameter<Integer> SPIKE_NUM = EntityDataManager.defineId(SpikeRockEntity.class, DataSerializers.INT);
	
	public SpikeRockEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason,
			ILivingEntityData spawnDataIn, CompoundNBT dataTag) {
		if(! level.isClientSide) {
			this.setSpikeNum(this.getSpikesCount());
		}
		return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
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
				for(LivingEntity target : EntityUtil.getEntityTargetableEntity(this, EntityUtil.getEntityAABB(this, 10, 2))) {
					target.hurt(PVZDamageSource.causeSpikeDamage(this, this), this.getAttackDamage() * 2);
					for(int i = 0; i < 4; ++ i) {
						EntityUtil.spawnParticle(target, 6);
					}
				}
			}
		}
	}
	
	@Override
	protected void pushEntities() {
		List<LivingEntity> list = this.level.getEntitiesOfClass(LivingEntity.class, this.getBoundingBox());
		if (!list.isEmpty()) {
            int i = this.level.getGameRules().getInt(GameRules.RULE_MAX_ENTITY_CRAMMING);
            if (i > 0 && list.size() > i - 1 && this.random.nextInt(4) == 0){
                int j = 0;
                for (int k = 0; k < list.size(); ++k){
                    if (!((Entity)list.get(k)).isPassenger()){
                        ++j;
                    }
                }
                if (j > i - 1){
                    this.hurt(DamageSource.CRAMMING, 6.0F);
                }
            }
            for (int l = 0; l < list.size(); ++l) {
                LivingEntity target = list.get(l);
                if(target != this && shouldCollideWithEntity(target)) {//can collide with
                    this.doPush(target);
                }
            }
            if(! this.level.isClientSide && this.getAttackTime() == 0) {
            	for (int l = 0; l < list.size(); ++l) {
            	    LivingEntity target = list.get(l);
            	    if(target != this && this.getSpikeNum() >= 0 && EntityUtil.checkCanEntityAttack(this, target)) {
            		    this.spikeNormalAttack(target);
            	    }
                }
            }
        }
	}
	
	@Override
	protected boolean shouldCollideWithEntity(LivingEntity target) {
		if(EntityUtil.checkCanEntityAttack(this, target)) {
			return false;
		}
		return super.shouldCollideWithEntity(target);
	}
	
	protected void spikeNormalAttack(LivingEntity target) {
		if(! this.canPlantNormalUpdate()) return ;
		this.setAttackTime(this.getAttackCD());
		if(target instanceof ZomboniEntity || target instanceof BobsleTeamEntity || target instanceof CatapultZombieEntity) {
			target.hurt(PVZDamageSource.causeSpikeDamage(this, this), target.getMaxHealth());
			this.setSpikeNum(this.getSpikeNum() - 1);
		} else {
			target.hurt(PVZDamageSource.causeSpikeDamage(this, this), getAttackDamage());
		}
	}
	
	@Override
	public boolean hurt(DamageSource source, float amount) {
		if(source instanceof PVZDamageSource) {
			if(((PVZDamageSource) source).getPVZDamageType() == PVZDamageType.CRUSH) {
				if(this.getSpikeNum() >= 0) {
					this.setSpikeNum(this.getSpikeNum() - 1);
					return false;
				}
			}
		}
		return super.hurt(source, amount);
	}
	
	public int getAttackCD() {
		int lvl = this.getPlantLvl();
		if(lvl <= 19) return 41 - lvl;
		return 20;
	}
	
	public float getAttackDamage() {
		int lvl = this.getPlantLvl();
		if(lvl <= 16) {
			int now = (lvl - 1) / 2;
			return 3.75F + 0.25F * now;
		}
		return 6;
	}
	
	public int getSpikesCount() {
		int lvl = this.getPlantLvl();
		if(lvl <= 20) {
			int now = (lvl - 1) / 5;
			return 3 * now + 6;
		}
		return 15;
	}

	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return new EntitySize(0.95f, 0.4f, false);
	}
	
	@Override
	public Plants getPlantEnumName() {
		return Plants.SPIKE_ROCK;
	}

	@Override
	public int getSuperTimeLength() {
		if(this.isPlantInStage(1)) return 100;
		if(this.isPlantInStage(2)) return 150;
		return 200;
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

}
