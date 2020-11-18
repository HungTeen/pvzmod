package com.hungteen.pvz.entity.plant.ice;

import com.hungteen.pvz.entity.bullet.PeaEntity.State;
import com.hungteen.pvz.entity.plant.appease.PeaShooterEntity;
import com.hungteen.pvz.entity.plant.interfaces.IIcePlant;
import com.hungteen.pvz.register.EffectRegister;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.enums.Plants;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class SnowPeaEntity extends PeaShooterEntity implements IIcePlant{

	public SnowPeaEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	public EffectInstance getColdEffect() {
		return new EffectInstance(EffectRegister.COLD_EFFECT.get(), this.getColdTick(), this.getColdLvl(), false, false);
	}
	
	public int getColdLvl() {
		int lvl = this.getPlantLvl();
		if(lvl <= 20) {
			int now = (lvl - 1) / 4;
			return 5 + now;
		}
		return 9;
	}
	
	public int getColdTick() {
		int lvl = this.getPlantLvl();
		if(lvl <= 19) {
			return 76 + 4 * lvl;
		}
		return 160;
	}
	
	@Override
	public EffectInstance getFrozenEffect() {
		return new EffectInstance(EffectRegister.FROZEN_EFFECT.get(), 0, 0, false, false);
	}
	
	@Override
	protected SoundEvent getShootSound() {
		return SoundRegister.SNOW_SHOOT.get();
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
