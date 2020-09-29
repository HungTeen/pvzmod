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
import net.minecraft.entity.player.PlayerEntity;
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
		if(this.getAttackTime()>0) {
			this.setAttackTime(this.getAttackTime()-1);
		}
		if(this.getSuperTime()%10==5) {
			for(LivingEntity target:EntityUtil.getEntityAttackableTarget(this, EntityUtil.getEntityAABB(this, 10, 2))) {
				if(!world.isRemote) {
					target.attackEntityFrom(PVZDamageSource.causeSpikeDamage(this, this), this.getAttackDamage() * 2);
				}else {
					for(int i=0;i<5;i++) {
						Random rand=this.getRNG();
						this.world.addParticle(ParticleRegister.DIRT_BURST_OUT.get(), target.getPosX()+0.5d, target.getPosY(), target.getPosZ()+0.5d, (rand.nextFloat()-0.5)/10,0.05d,(rand.nextFloat()-0.5)/10);
						this.world.addParticle(ParticleRegister.DIRT_BURST_OUT.get(), target.getPosX()+0.5d, target.getPosY(), target.getPosZ()-0.5d, (rand.nextFloat()-0.5)/10,0.05d,(rand.nextFloat()-0.5)/10);
						this.world.addParticle(ParticleRegister.DIRT_BURST_OUT.get(), target.getPosX()-0.5d, target.getPosY(), target.getPosZ()+0.5d, (rand.nextFloat()-0.5)/10,0.05d,(rand.nextFloat()-0.5)/10);
						this.world.addParticle(ParticleRegister.DIRT_BURST_OUT.get(), target.getPosX()-0.5d, target.getPosY(), target.getPosZ()-0.5d, (rand.nextFloat()-0.5)/10,0.05d,(rand.nextFloat()-0.5)/10);
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
                if(shouldCollideWithEntity(target)) {//can collide with
                    this.collideWithEntity(target);
                }else if(target != this){
                	if(!world.isRemote&&this.getAttackTime()==0) {
                		if(EntityUtil.checkCanEntityAttack(this, target)) {
                		    spikeNormalAttack(target);
                		}
                	}
                }
            }
        }
	}
	
	protected void spikeNormalAttack(LivingEntity target) {
		this.setAttackTime(this.getAttackCD());
		if(target instanceof ZomboniEntity || target instanceof BobsleTeamEntity) {
			target.attackEntityFrom(PVZDamageSource.causeSpikeDamage(this, this), target.getMaxHealth());
			this.remove();
		}
		target.attackEntityFrom(PVZDamageSource.causeSpikeDamage(this, this), getAttackDamage());
	}
	
	@Override
	protected boolean shouldCollideWithEntity(LivingEntity target) {
		return !(target instanceof PlayerEntity)&&!EntityUtil.checkCanEntityAttack(this, target);
	}
	
	@Override
	public EntitySize getSize(Pose poseIn) {
		return new EntitySize(0.8f, 0.4f, false);
	}
	
	public static float getAttackDamage(int lvl){
		if(lvl<=20) {
			int now = (lvl - 1) / 4;
			return 2 + 0.25f*now;
		}
		return 2;
	}
	
	public static int getAttackCD(int lvl) {
		if(lvl<=20) {
			int now = (lvl - 1) / 4;
			return 20 - now;
		}
		return 20;
	}
	
	@Override
	public int getSuperTimeLength() {
		int lvl=this.getPlantLvl();
		if(lvl<=6) return 65;
		if(lvl<=13) return 85;
		if(lvl<=20) return 105;
		return 65;
	}
	
    @Override
	public Plants getPlantEnumName() {
		return Plants.SPIKE_WEED;
	}

}
