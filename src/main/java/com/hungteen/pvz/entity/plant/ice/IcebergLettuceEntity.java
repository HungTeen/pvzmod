package com.hungteen.pvz.entity.plant.ice;

import com.hungteen.pvz.advancement.trigger.EntityEffectAmountTrigger;
import com.hungteen.pvz.entity.plant.base.PlantCloserEntity;
import com.hungteen.pvz.entity.plant.interfaces.IIcePlant;
import com.hungteen.pvz.misc.damage.PVZDamageSource;
import com.hungteen.pvz.register.EffectRegister;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.enums.Plants;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.world.World;

public class IcebergLettuceEntity extends PlantCloserEntity implements IIcePlant {

	public IcebergLettuceEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	public void performAttack(LivingEntity target) {
		for(int i = 0; i < 2; ++ i) {
			EntityUtil.spawnParticle(this, 5);
		}
		EntityUtil.playSound(this, SoundRegister.ZOMBIE_FROZEN.get());
		this.dealDamageTo(target);
		this.remove();
	}
	
	@Override
	public void startSuperMode(boolean first) {
		super.startSuperMode(first);
		float range = this.getSuperRange();
		for(int i = 0; i < 2; ++ i) {
			EntityUtil.spawnParticle(this, 5);
		}
		EntityUtil.playSound(this, SoundRegister.ZOMBIE_FROZEN.get());
		int cnt = 0;
		for(Entity target : EntityUtil.getAttackEntities(this, EntityUtil.getEntityAABB(this, range, range))) {
			this.dealDamageTo(target);
			if(target instanceof LivingEntity && EntityUtil.isEntityCold((LivingEntity) target)) {
				++ cnt;
			}
		};
		PlayerEntity player = EntityUtil.getEntityOwner(level, this);
		if(player != null && player instanceof ServerPlayerEntity) {
			EntityEffectAmountTrigger.INSTANCE.trigger((ServerPlayerEntity) player, this, cnt);
		}
	}
	
	private void dealDamageTo(Entity target) {
		PVZDamageSource source = PVZDamageSource.causeIceDamage(this, this);
		source.addEffect(this.getFrozenEffect());
		source.addEffect(this.getColdEffect());
		target.hurt(source, 0.01F);
	}
	
    @Override
	public EffectInstance getColdEffect() {
		return new EffectInstance(EffectRegister.COLD_EFFECT.get(), this.getFrozenTime() * 2, this.getColdLevel(), false, false);
	}
    
    @Override
	public EffectInstance getFrozenEffect() {
    	return new EffectInstance(EffectRegister.FROZEN_EFFECT.get(), this.getFrozenTime(), 1, false, false);
	}
    
    public int getColdLevel() {
    	int lvl = this.getPlantLvl();
    	if(lvl <= 20) {
    		int now = (lvl - 1) / 5;
    		return 4 + now;
    	}
    	return 7;
    }
    
    public int getFrozenTime() {
    	int lvl = this.getPlantLvl();
    	if(lvl <= 20) {
    		return 95 + 5 * lvl;
    	}
    	return 200;
    }
    
    public float getSuperRange() {
    	if(this.isPlantInStage(1)) return 10;
    	if(this.isPlantInStage(2)) return 15;
    	return 20;
    }
    
	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return EntitySize.fixed(0.6F, 0.6F);
	}
	
	@Override
	public Plants getPlantEnumName() {
		return Plants.ICEBERG_LETTUCE;
	}

}
