package com.hungteen.pvz.common.entity.plant.ice;

import com.hungteen.pvz.api.interfaces.IAlmanacEntry;
import com.hungteen.pvz.api.interfaces.IIceEffect;
import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.advancement.trigger.EntityEffectAmountTrigger;
import com.hungteen.pvz.common.entity.misc.ElementBallEntity;
import com.hungteen.pvz.common.entity.misc.ElementBallEntity.ElementTypes;
import com.hungteen.pvz.common.entity.plant.base.PlantBomberEntity;
import com.hungteen.pvz.common.impl.plant.PVZPlants;
import com.hungteen.pvz.common.misc.PVZEntityDamageSource;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.common.potion.EffectRegister;
import com.hungteen.pvz.register.ParticleRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.enums.PAZAlmanacs;
import com.mojang.datafixers.util.Pair;
import net.minecraft.entity.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class IceShroomEntity extends PlantBomberEntity implements IIceEffect{

	public IceShroomEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	public void startBomb(boolean server) {
		if(server) {
			//frozen enemies.
			final float len = this.getExplodeRange();
			final AxisAlignedBB aabb = EntityUtil.getEntityAABB(this, len, len);
			int cnt = 0;
			for(LivingEntity entity : EntityUtil.getTargetableLivings(this, aabb)) {
				 PVZEntityDamageSource source = PVZEntityDamageSource.causeIceDamage(this, this);
				 this.getColdEffect().ifPresent(e -> source.addEffect(e));
				 this.getFrozenEffect().ifPresent(e -> source.addEffect(e));
				 entity.hurt(source, this.getExplodeDamage());
				 if(EntityUtil.isEntityCold(entity)) {
					 ++ cnt;
				 }
			}
			EntityUtil.playSound(this, SoundRegister.FROZEN.get());
			//trigger advancement.
			final PlayerEntity player = EntityUtil.getEntityOwner(level, this);
			if(player != null && player instanceof ServerPlayerEntity) {
				EntityEffectAmountTrigger.INSTANCE.trigger((ServerPlayerEntity) player, this, cnt);
			}
			//kill flame ball.
			ElementBallEntity.killElementBalls(this, 40, ElementTypes.FLAME);
		} else {
			for(int i = 0;i < 3; ++ i) {
		        this.level.addParticle(ParticleTypes.EXPLOSION, this.getX(), this.getY(), this.getZ(), 0, 0, 0);
	 	    }
		    for(int i = 0; i < 15; ++ i) {
			    this.level.addParticle(ParticleRegister.SNOW_FLOWER.get(), this.getX(), this.getY(), this.getZ(), (this.getRandom().nextFloat() - 0.5f) / 4, this.getRandom().nextFloat() / 5, (this.getRandom().nextFloat() - 0.5f) / 4);
		    }
		}
	}

	@Override
	public void addAlmanacEntries(List<Pair<IAlmanacEntry, Number>> list) {
		super.addAlmanacEntries(list);
		list.addAll(Arrays.asList(
				Pair.of(PAZAlmanacs.COLD_LEVEL, this.getColdLvl()),
				Pair.of(PAZAlmanacs.COLD_TIME, this.getColdTick()),
				Pair.of(PAZAlmanacs.FROZEN_LEVEL, this.getFrozenLvl()),
				Pair.of(PAZAlmanacs.FROZEN_TIME, this.getFrozenTick())
		));
	}

	@Override
	public int getReadyTime() {
		return 20;
	}

	@Override
	public float getExplodeRange(){
		return 20;
	}
	
	public float getExplodeDamage() {
		return 0.1F;
	}
	
	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return EntitySize.scalable(0.85f, 1.35f);
	}
	
	public int getColdLvl() {
		return 1;
	}
	
	public int getColdTick() {
		return 0;
	}

	public int getFrozenLvl() {
		return 0;
	}
	
	public int getFrozenTick() {
		return 100;
	}
	
	@Override
	public Optional<EffectInstance> getColdEffect() {
		return Optional.ofNullable(new EffectInstance(EffectRegister.COLD_EFFECT.get(), this.getColdTick() + this.getFrozenTick(), this.getColdLvl(), false, false));
	}

	@Override
	public Optional<EffectInstance> getFrozenEffect() {
		return Optional.ofNullable(new EffectInstance(EffectRegister.FROZEN_EFFECT.get(), this.getFrozenTick(), this.getFrozenLvl(), false, false));
	}
	
	@Override
	public IPlantType getPlantType() {
		return PVZPlants.ICE_SHROOM;
	}

}
