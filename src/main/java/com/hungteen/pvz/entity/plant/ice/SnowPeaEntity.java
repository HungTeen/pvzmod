package com.hungteen.pvz.entity.plant.ice;

import com.hungteen.pvz.entity.bullet.PeaEntity.State;
import com.hungteen.pvz.entity.plant.appease.PeaShooterEntity;
import com.hungteen.pvz.entity.plant.interfaces.IIcePlant;
import com.hungteen.pvz.register.EffectRegister;
import com.hungteen.pvz.utils.enums.Plants;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.potion.EffectInstance;
import net.minecraft.world.World;

public class SnowPeaEntity extends PeaShooterEntity implements IIcePlant{

	public SnowPeaEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	public EffectInstance getColdEffect() {
		int lvl=this.getPlantLvl();
		int duration=80,amount=5;
		if(lvl<=20) {
			int now=(lvl-1)/4;
			duration+=now*20;
			amount+=now;
		}
		return new EffectInstance(EffectRegister.COLD_EFFECT.get(), duration, amount,false,false);
	}

	@Override
	public EffectInstance getFrozenEffect() {
		return new EffectInstance(EffectRegister.FROZEN_EFFECT.get(), 0, 0, false, false);
	}
	
	@Override
	protected State getShootState() {
		return State.ICE;
	}
	
	@Override
	public Plants getPlantEnumName() {
		return Plants.SNOW_PEA;
	}
	
}
