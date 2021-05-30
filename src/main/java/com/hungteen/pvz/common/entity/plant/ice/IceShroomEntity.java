package com.hungteen.pvz.common.entity.plant.ice;

import com.hungteen.pvz.common.advancement.trigger.EntityEffectAmountTrigger;
import com.hungteen.pvz.common.entity.misc.ElementBallEntity;
import com.hungteen.pvz.common.entity.misc.ElementBallEntity.ElementTypes;
import com.hungteen.pvz.common.entity.plant.base.PlantBomberEntity;
import com.hungteen.pvz.common.entity.plant.interfaces.IIcePlant;
import com.hungteen.pvz.common.misc.damage.PVZDamageSource;
import com.hungteen.pvz.register.EffectRegister;
import com.hungteen.pvz.register.ParticleRegister;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;
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

public class IceShroomEntity extends PlantBomberEntity implements IIcePlant{

	public IceShroomEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	public void startBomb() {
		if(! this.level.isClientSide) {
			float len = getAttackRange();
			AxisAlignedBB aabb=EntityUtil.getEntityAABB(this, len, len);
			int cnt = 0;
			for(LivingEntity entity : EntityUtil.getEntityTargetableEntity(this, aabb)) {
				 PVZDamageSource source = PVZDamageSource.causeIceDamage(this, this);
				 source.addEffect(getColdEffect());
				 source.addEffect(getFrozenEffect());
				 entity.hurt(source, this.getAttackDamage());
				 if(EntityUtil.isEntityCold(entity)) {
					 ++ cnt;
				 }
			}
			PlayerEntity player = EntityUtil.getEntityOwner(level, this);
			if(player != null && player instanceof ServerPlayerEntity) {
				EntityEffectAmountTrigger.INSTANCE.trigger((ServerPlayerEntity) player, this, cnt);
			}
			EntityUtil.playSound(this, SoundRegister.ZOMBIE_FROZEN.get());
		} else {
			for(int i = 0;i < 3; ++ i) {
		        this.level.addParticle(ParticleTypes.EXPLOSION, this.getX(), this.getY(), this.getZ(), 0, 0, 0);
	 	    }
		    for(int i = 0; i < 15; ++ i) {
			    this.level.addParticle(ParticleRegister.SNOW_FLOWER.get(), this.getX(), this.getY(), this.getZ(), (this.getRandom().nextFloat() - 0.5f) / 4, this.getRandom().nextFloat() / 5, (this.getRandom().nextFloat() - 0.5f) / 4);
		    }
		}
		this.killFireBall();
	}
	
	/**
	 * kill zomboss fireball
	 */
	private void killFireBall() {
		if(! level.isClientSide) {
			float range = this.getAttackRange() + 10F;
			level.getEntitiesOfClass(ElementBallEntity.class, EntityUtil.getEntityAABB(this, range, range), (target) -> {
				return target.getElementBallType() == ElementTypes.FLAME;
			}).forEach((target) -> {
				target.onKilledByPlants(this);
			});
		}
	}

	@Override
	public int getReadyTime() {
		return 30;
	}
	
	public float getAttackDamage() {
		int lvl = this.getPlantLvl();
		if(lvl <= 20) {
			return 0.5f * lvl;
		}
		return 10f;
	}
	
	public float getAttackRange() {
		if(this.isPlantInStage(1)) return 10;
		if(this.isPlantInStage(2)) return 15;
		return 20;
	}
	
	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return EntitySize.scalable(0.85f, 1.35f);
	}

	@Override
	public EffectInstance getColdEffect() {
		int lvl = this.getPlantLvl();
		int amount = 0;
		if(lvl > 4 && lvl <= 20) {
			amount = (lvl <= 8 ? 5 : (lvl <= 16 ? 6 : 7));
		}
		return new EffectInstance(EffectRegister.COLD_EFFECT.get(), this.getColdTick() + this.getFrozenTick(), amount, false, false);
	}
	
	public int getColdLvl() {
		int lvl = this.getPlantLvl();
		if(lvl <= 20) {
			int now = (lvl - 1) / 4;
			return 3 + now;
		}
		return 7;
	}
	
	public int getColdTick() {
		int lvl = this.getPlantLvl();
		if(lvl <= 20) {
			return 5 * lvl;
		}
		return 100;
	}
	
	public int getFrozenTick() {
		int lvl = this.getPlantLvl();
		int time = 240;
		if(lvl <= 20) {
			time = 93 + 7 * lvl;
		}
		return time;
	}

	@Override
	public EffectInstance getFrozenEffect() {
		return new EffectInstance(EffectRegister.FROZEN_EFFECT.get(), this.getFrozenTick(), 1, false, false);
	}
	
	@Override
	public Plants getPlantEnumName() {
		return Plants.ICE_SHROOM;
	}

}
