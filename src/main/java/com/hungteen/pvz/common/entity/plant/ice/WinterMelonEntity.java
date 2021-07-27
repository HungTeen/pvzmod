package com.hungteen.pvz.common.entity.plant.ice;

import java.util.Optional;

import com.hungteen.pvz.api.interfaces.IIceEffect;
import com.hungteen.pvz.common.entity.bullet.MelonEntity.MelonStates;
import com.hungteen.pvz.common.entity.plant.arma.MelonPultEntity;
import com.hungteen.pvz.register.EffectRegister;
import com.hungteen.pvz.utils.enums.Plants;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.potion.EffectInstance;
import net.minecraft.world.World;

public class WinterMelonEntity extends MelonPultEntity implements IIceEffect {

	public WinterMelonEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}

	public int getColdLvl() {
		return this.getAverageProgress(7, 11);
	}
	
	public int getColdTick() {
		return this.getAverageProgress(80, 160);
	}
	
	@Override
	public Optional<EffectInstance> getColdEffect() {
		return Optional.ofNullable(new EffectInstance(EffectRegister.COLD_EFFECT.get(), this.getColdTick(), this.getColdLvl(), false, false));
	}
	
	@Override
	public Optional<EffectInstance> getFrozenEffect() {
		return Optional.empty();
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
