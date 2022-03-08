package com.hungteen.pvz.api.interfaces;

import java.util.Optional;

import net.minecraft.world.effect.MobEffectInstance;

public interface IIceEffect {

	Optional<MobEffectInstance> getColdEffect();
	
	Optional<MobEffectInstance> getFrozenEffect();
}
