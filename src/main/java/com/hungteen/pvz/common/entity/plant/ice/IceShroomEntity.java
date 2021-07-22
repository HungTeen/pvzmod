package com.hungteen.pvz.common.entity.plant.ice;

import java.util.Optional;

import com.hungteen.pvz.api.interfaces.IIceEffect;
import com.hungteen.pvz.common.advancement.trigger.EntityEffectAmountTrigger;
import com.hungteen.pvz.common.entity.misc.ElementBallEntity;
import com.hungteen.pvz.common.entity.misc.ElementBallEntity.ElementTypes;
import com.hungteen.pvz.common.entity.plant.base.PlantBomberEntity;
import com.hungteen.pvz.common.misc.damage.PVZDamageSource;
import com.hungteen.pvz.register.EffectRegister;
import com.hungteen.pvz.register.ParticleRegister;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.MathUtil;
import com.hungteen.pvz.utils.PlantUtil;
import com.hungteen.pvz.utils.enums.Plants;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

public class IceShroomEntity extends PlantBomberEntity implements IIceEffect{

	public IceShroomEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	public void startBomb(boolean server) {
		if(server) {
			//frozen enemies.
			final float len = 20;
			final AxisAlignedBB aabb = EntityUtil.getEntityAABB(this, len, len);
			int cnt = 0;
			for(LivingEntity entity : EntityUtil.getTargetableLivings(this, aabb)) {
				 PVZDamageSource source = PVZDamageSource.causeIceDamage(this, this);
				 this.getColdEffect().ifPresent(e -> source.addEffect(e));
				 this.getFrozenEffect().ifPresent(e -> source.addEffect(e));
				 entity.hurt(source, this.getAttackDamage());
				 if(EntityUtil.isEntityCold(entity)) {
					 ++ cnt;
				 }
			}
			EntityUtil.playSound(this, SoundRegister.FROZEN_HIT.get());
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
	public int getReadyTime() {
		return 30;
	}
	
	public float getAttackDamage() {
		final int lvl = this.getPlantLvl();
		return lvl <= 20 ? 0.1F * lvl : 10F;
	}
	
	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return EntitySize.scalable(0.85f, 1.35f);
	}
	
	public int getColdLvl() {
		return MathUtil.getProgressByDif(4, 1, this.getPlantLvl(), PlantUtil.MAX_PLANT_LEVEL, 3, 7);
	}
	
	public int getColdTick() {
		return PlantUtil.getPlantAverageProgress(this, 0, 200);
	}
	
	public int getFrozenTick() {
		return PlantUtil.getPlantAverageProgress(this, 100, 240);
	}
	
	@Override
	public Optional<EffectInstance> getColdEffect() {
		return Optional.ofNullable(new EffectInstance(EffectRegister.COLD_EFFECT.get(), this.getColdTick() + this.getFrozenTick(), this.getColdLvl(), false, false));
	}

	@Override
	public Optional<EffectInstance> getFrozenEffect() {
		return Optional.ofNullable(new EffectInstance(EffectRegister.FROZEN_EFFECT.get(), this.getFrozenTick(), 1, false, false));
	}
	
	@Override
	public Plants getPlantEnumName() {
		return Plants.ICE_SHROOM;
	}

}
