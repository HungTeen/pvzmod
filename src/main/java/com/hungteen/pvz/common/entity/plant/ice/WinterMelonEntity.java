package com.hungteen.pvz.common.entity.plant.ice;

import com.hungteen.pvz.api.interfaces.IIceEffect;
import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.entity.bullet.MelonEntity.MelonStates;
import com.hungteen.pvz.common.entity.plant.arma.MelonPultEntity;
import com.hungteen.pvz.common.impl.plant.PVZPlants;
import com.hungteen.pvz.common.potion.EffectRegister;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.CreatureEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

import java.util.Optional;

public class WinterMelonEntity extends MelonPultEntity implements IIceEffect {

	public WinterMelonEntity(EntityType<? extends CreatureEntity> type, Level worldIn) {
		super(type, worldIn);
	}

	public int getColdLvl() {
		return 7;
	}
	
	public int getColdTick() {
		return 80;
	}
	
	@Override
	public Optional<MobEffectInstance> getColdEffect() {
		return Optional.ofNullable(new MobEffectInstance(EffectRegister.COLD_EFFECT.get(), this.getColdTick(), this.getColdLvl(), false, false));
	}
	
	@Override
	public Optional<MobEffectInstance> getFrozenEffect() {
		return Optional.empty();
	}
	
	@Override
	protected MelonStates getThrowMelonState() {
		return MelonStates.ICE;
	}
	
	@Override
	public IPlantType getPlantType() {
		return PVZPlants.WINTER_MELON;
	}

}
