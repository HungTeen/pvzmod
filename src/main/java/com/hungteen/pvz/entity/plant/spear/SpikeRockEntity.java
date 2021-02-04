package com.hungteen.pvz.entity.plant.spear;

import java.util.List;

import com.hungteen.pvz.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.entity.zombie.poolday.BobsleTeamEntity;
import com.hungteen.pvz.entity.zombie.poolday.ZomboniEntity;
import com.hungteen.pvz.entity.zombie.roof.CatapultZombieEntity;
import com.hungteen.pvz.misc.damage.PVZDamageSource;
import com.hungteen.pvz.misc.damage.PVZDamageType;
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
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class SpikeRockEntity extends PVZPlantEntity {

	private static final DataParameter<Integer> SPIKE_NUM = EntityDataManager.createKey(SpikeRockEntity.class, DataSerializers.VARINT);
	
	public SpikeRockEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason,
			ILivingEntityData spawnDataIn, CompoundNBT dataTag) {
		if(! world.isRemote) {
			this.setSpikeNum(this.getSpikesCount());
		}
		return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
	}
	
	@Override
	protected void registerData() {
		super.registerData();
		this.dataManager.register(SPIKE_NUM, 1);
	}
	
	@Override
	protected void normalPlantTick() {
		super.normalPlantTick();
		if(! world.isRemote) {
			if(this.getSpikeNum() < 0) {
				this.remove();
			}
			if(this.getAttackTime() > 0) {
				this.setAttackTime(this.getAttackTime() - 1);
			}
			if(this.isPlantInSuperMode() && this.getSuperTime() % 10 == 0) {
				for(LivingEntity target : EntityUtil.getEntityTargetableEntity(this, EntityUtil.getEntityAABB(this, 10, 2))) {
					target.attackEntityFrom(PVZDamageSource.causeSpikeDamage(this, this), this.getAttackDamage() * 2);
					for(int i = 0; i < 4; ++ i) {
						EntityUtil.spawnParticle(target, 6);
					}
				}
			}
		}
	}
	
	@Override
	protected void collideWithNearbyEntities() {
		List<LivingEntity> list = this.world.getEntitiesWithinAABB(LivingEntity.class, this.getBoundingBox());
		if (!list.isEmpty()) {
            int i = this.world.getGameRules().getInt(GameRules.MAX_ENTITY_CRAMMING);
            if (i > 0 && list.size() > i - 1 && this.rand.nextInt(4) == 0){
                int j = 0;
                for (int k = 0; k < list.size(); ++k){
                    if (!((Entity)list.get(k)).isPassenger()){
                        ++j;
                    }
                }
                if (j > i - 1){
                    this.attackEntityFrom(DamageSource.CRAMMING, 6.0F);
                }
            }
            for (int l = 0; l < list.size(); ++l) {
                LivingEntity target = list.get(l);
                if(target != this && shouldCollideWithEntity(target)) {//can collide with
                    this.collideWithEntity(target);
                }
            }
            if(! this.world.isRemote && this.getAttackTime() == 0) {
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
			target.attackEntityFrom(PVZDamageSource.causeSpikeDamage(this, this), target.getMaxHealth());
			this.setSpikeNum(this.getSpikeNum() - 1);
		} else {
			target.attackEntityFrom(PVZDamageSource.causeSpikeDamage(this, this), getAttackDamage());
		}
	}
	
	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if(source instanceof PVZDamageSource) {
			if(((PVZDamageSource) source).getPVZDamageType() == PVZDamageType.CRUSH) {
				if(this.getSpikeNum() >= 0) {
					this.setSpikeNum(this.getSpikeNum() - 1);
					return false;
				}
			}
		}
		return super.attackEntityFrom(source, amount);
	}
	
	public int getAttackCD() {
		return 20;
	}
	
	public float getAttackDamage() {
		return 3;
	}
	
	public int getSpikesCount() {
		return 6;
	}

	@Override
	public EntitySize getSize(Pose poseIn) {
		return new EntitySize(0.95f, 0.4f, false);
	}
	
	@Override
	public Plants getPlantEnumName() {
		return Plants.SPIKE_ROCK;
	}

	@Override
	public int getSuperTimeLength() {
		return 0;
	}
	
	@Override
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		if(compound.contains("spike_num")) {
			this.setSpikeNum(compound.getInt("spike_num"));
		}
	}
	
	@Override
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		compound.putInt("spike_num", this.getSpikeNum());
	}
	
	public int getSpikeNum() {
		return this.dataManager.get(SPIKE_NUM);
	}
	
	public void setSpikeNum(int num) {
		this.dataManager.set(SPIKE_NUM, num);
	}

}
