package com.hungteen.pvz.common.entity.plant.ice;

import com.hungteen.pvz.api.interfaces.IIceEffect;
import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.advancement.trigger.EntityEffectAmountTrigger;
import com.hungteen.pvz.common.entity.plant.base.PlantCloserEntity;
import com.hungteen.pvz.common.impl.plant.OtherPlants;
import com.hungteen.pvz.common.misc.damage.PVZDamageSource;
import com.hungteen.pvz.common.misc.sound.SoundRegister;
import com.hungteen.pvz.common.potion.EffectRegister;
import com.hungteen.pvz.utils.EntityUtil;
import net.minecraft.entity.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.world.World;

import java.util.Optional;

public class IcebergLettuceEntity extends PlantCloserEntity implements IIceEffect {

	private static final int FROZEN_TICK = 200;
	
	public IcebergLettuceEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	public void performAttack(LivingEntity target) {
		for(int i = 0; i < 2; ++ i) {
			EntityUtil.spawnParticle(this, 5);
		}
		EntityUtil.playSound(this, SoundRegister.FROZEN_HIT.get());
		this.dealDamageTo(target);
		this.remove();
	}
	
	@Override
	public void startSuperMode(boolean first) {
		super.startSuperMode(first);
		final float range = this.getSuperRange();
		for(int i = 0; i < 2; ++ i) {
			EntityUtil.spawnParticle(this, 5);
		}
		EntityUtil.playSound(this, SoundRegister.FROZEN_HIT.get());
		int cnt = 0;
		for(Entity target : EntityUtil.getTargetableEntities(this, EntityUtil.getEntityAABB(this, range, range))) {
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
		this.getColdEffect().ifPresent(e -> source.addEffect(e));
		this.getFrozenEffect().ifPresent(e -> source.addEffect(e));
		target.hurt(source, 0.001F);
	}
	
    @Override
	public Optional<EffectInstance> getColdEffect() {
		return Optional.ofNullable(new EffectInstance(EffectRegister.COLD_EFFECT.get(), FROZEN_TICK + this.getColdDuration(), this.getColdLevel(), false, false));
	}
    
    @Override
	public Optional<EffectInstance> getFrozenEffect() {
    	return Optional.ofNullable(new EffectInstance(EffectRegister.FROZEN_EFFECT.get(), FROZEN_TICK, 1, false, false));
	}
    
    public int getColdLevel() {
		return 1;
    }
    
    public int getColdDuration() {
		return 100;
    }
    
    public float getSuperRange() {
		return 20;
    }
    
	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return EntitySize.fixed(0.6F, 0.6F);
	}
	
	@Override
	public IPlantType getPlantType() {
		return OtherPlants.ICEBERG_LETTUCE;
	}

}
