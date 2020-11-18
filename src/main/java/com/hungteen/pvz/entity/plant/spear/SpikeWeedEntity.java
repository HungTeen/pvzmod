package com.hungteen.pvz.entity.plant.spear;

import java.util.List;
import java.util.Random;

import com.hungteen.pvz.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.entity.zombie.poolday.BobsleTeamEntity;
import com.hungteen.pvz.entity.zombie.poolday.ZomboniEntity;
import com.hungteen.pvz.misc.damage.PVZDamageSource;
import com.hungteen.pvz.register.ParticleRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.enums.Plants;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.util.DamageSource;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;

public class SpikeWeedEntity extends PVZPlantEntity{

	public SpikeWeedEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	protected void normalPlantTick() {
		super.normalPlantTick();
		if(this.getAttackTime() > 0) {
			this.setAttackTime(this.getAttackTime() - 1);
		}
		if(this.getSuperTime() % 10 == 5) {
			for(LivingEntity target : EntityUtil.getEntityTargetableEntity(this, EntityUtil.getEntityAABB(this, 10, 2))) {
				if(!world.isRemote) {
					target.attackEntityFrom(PVZDamageSource.causeSpikeDamage(this, this), this.getAttackDamage() * 2);
				}else {
					for(int i = 0; i < 2; ++ i) {
						Random rand = this.getRNG();
					    this.world.addParticle(ParticleRegister.DIRT_BURST_OUT.get(), this.getPosX() + 0.5d, this.getPosY(), this.getPosZ() + 0.5d, (rand.nextFloat() - 0.5) / 10, 0.05d, (rand.nextFloat() - 0.5) / 10);
					    this.world.addParticle(ParticleRegister.DIRT_BURST_OUT.get(), this.getPosX() + 0.5d, this.getPosY(), this.getPosZ() - 0.5d, (rand.nextFloat() - 0.5) / 10, 0.05d, (rand.nextFloat() - 0.5) / 10);
				        this.world.addParticle(ParticleRegister.DIRT_BURST_OUT.get(), this.getPosX() - 0.5d, this.getPosY(), this.getPosZ() + 0.5d, (rand.nextFloat() - 0.5) / 10, 0.05d, (rand.nextFloat() - 0.5) / 10);
					    this.world.addParticle(ParticleRegister.DIRT_BURST_OUT.get(), this.getPosX() - 0.5d, this.getPosY(), this.getPosZ() - 0.5d, (rand.nextFloat() - 0.5) / 10, 0.05d, (rand.nextFloat() - 0.5) / 10);
					}
				}
			}
		}
	}
	
	@Override
	protected void collideWithNearbyEntities() {
		List<LivingEntity> list = this.world.getEntitiesWithinAABB(LivingEntity.class, this.getBoundingBox());
		if (!list.isEmpty()){
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
            for (int l = 0; l < list.size(); ++l){
                LivingEntity target = list.get(l);
                if(target != this && shouldCollideWithEntity(target)) {//can collide with
                    this.collideWithEntity(target);
                }
            }
            if(!this.world.isRemote && this.getAttackTime() == 0) {
            	for (int l = 0; l < list.size(); ++l) {
            	    LivingEntity target = list.get(l);
            	    if(target != this && EntityUtil.checkCanEntityAttack(this, target)) {
            		    this.spikeNormalAttack(target);
            	    }
                }
            }
        }
	}
	
	protected void spikeNormalAttack(LivingEntity target) {
		if(!this.canPlantNormalUpdate()) {
			return ;
		}
		this.setAttackTime(this.getAttackCD());
		if(target instanceof ZomboniEntity || target instanceof BobsleTeamEntity) {
			target.attackEntityFrom(PVZDamageSource.causeSpikeDamage(this, this), target.getMaxHealth());
			this.remove();
		}
		target.attackEntityFrom(PVZDamageSource.causeSpikeDamage(this, this), getAttackDamage());
	}
	
	@Override
	protected boolean shouldCollideWithEntity(LivingEntity target) {
		if(EntityUtil.checkCanEntityAttack(this, target)) {
			return false;
		}
		return super.shouldCollideWithEntity(target);
	}
	
	@Override
	public EntitySize getSize(Pose poseIn) {
		return new EntitySize(0.9f, 0.4f, false);
	}
	
	public float getAttackDamage(){
		int lvl = this.getPlantLvl();
		if(lvl <= 20) {
			int now = (lvl - 1) / 4;
			return 2 + 0.25f * now;
		}
		return 3;
	}
	
	public int getAttackCD() {
		int lvl = this.getPlantLvl();
		if(lvl <= 19) return 41 - lvl;
		return 20;
	}
	
	@Override
	public int getSuperTimeLength() {
		if(this.isPlantInStage(1)) return 65;
		if(this.isPlantInStage(2)) return 85;
		return 105;
	}
	
    @Override
	public Plants getPlantEnumName() {
		return Plants.SPIKE_WEED;
	}

}
