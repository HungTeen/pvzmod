package com.hungteen.pvz.common.entity.plant.ice;

import com.hungteen.pvz.common.entity.bullet.MelonEntity.MelonStates;
import com.hungteen.pvz.common.entity.plant.arma.MelonPultEntity;
import com.hungteen.pvz.common.entity.plant.interfaces.IIcePlant;
import com.hungteen.pvz.register.EffectRegister;
import com.hungteen.pvz.utils.enums.Plants;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.potion.EffectInstance;
import net.minecraft.world.World;

public class WinterMelonEntity extends MelonPultEntity implements IIcePlant {

	public WinterMelonEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
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
			return 7 + now;
		}
		return 11;
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
	protected MelonStates getThrowMelonState() {
		return MelonStates.ICE;
	}
	
	@Override
	public Plants getUpgradePlantType() {
		return null;
	}
	
	@Override
	public Plants getPlantEnumName() {
		return Plants.WINTER_MELON;
	}

}
