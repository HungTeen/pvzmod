package com.hungteen.pvz.entity.plant.ice;

import com.hungteen.pvz.entity.plant.base.PlantBomberEntity;
import com.hungteen.pvz.entity.plant.interfaces.IIcePlant;
import com.hungteen.pvz.entity.plant.interfaces.IShroomPlant;
import com.hungteen.pvz.misc.damage.PVZDamageSource;
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
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

public class IceShroomEntity extends PlantBomberEntity implements IShroomPlant, IIcePlant{

	public IceShroomEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	public void startBomb() {
		if(!this.world.isRemote) {
			float len = getAttackRange();
			AxisAlignedBB aabb=EntityUtil.getEntityAABB(this, len, len);
			for(LivingEntity entity : EntityUtil.getEntityTargetableEntity(this, aabb)) {
				 PVZDamageSource source = PVZDamageSource.causeIceDamage(this, this);
				 source.addEffect(getColdEffect());
				 source.addEffect(getFrozenEffect());
				 entity.attackEntityFrom(source, this.getAttackDamage());
			}
			EntityUtil.playSound(this, SoundRegister.ZOMBIE_FROZEN.get());
		} else {
			for(int i = 0;i < 3;i ++) {
		        this.world.addParticle(ParticleTypes.EXPLOSION, this.getPosX(), this.getPosY(), this.getPosZ(), 0, 0, 0);
	 	    }
		    for(int i = 0; i < 15;i ++) {
			    this.world.addParticle(ParticleRegister.SNOW_FLOWER.get(), this.getPosX(), this.getPosY(), this.getPosZ(), (this.getRNG().nextFloat() - 0.5f) / 4, this.getRNG().nextFloat() / 5, (this.getRNG().nextFloat() - 0.5f) / 4);
		    }
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
	public EntitySize getSize(Pose poseIn) {
		return EntitySize.flexible(0.85f, 1.35f);
	}

	@Override
	public EffectInstance getColdEffect() {
		int lvl = this.getPlantLvl();
		int duration = 0, amount = 0;
		if(lvl > 4 && lvl <= 20) {
			duration = (lvl <= 12 ? 100 : 160);
			amount = (lvl <= 8 ? 5 : (lvl <= 16 ? 6 : 7));
		}
		return new EffectInstance(EffectRegister.COLD_EFFECT.get(), duration, amount, false, false);
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
		return this.getColdTick() + time;
	}

	@Override
	public EffectInstance getFrozenEffect() {
		int lvl = this.getPlantLvl();
		int time = 12;
		if(lvl <= 15) {
			int now = (lvl - 1) / 5;
			time = 5 + now * 2;
		}
		return new EffectInstance(EffectRegister.FROZEN_EFFECT.get(), time * 20, 1, false, false);
	}
	
	@Override
	public Plants getPlantEnumName() {
		return Plants.ICE_SHROOM;
	}

}
