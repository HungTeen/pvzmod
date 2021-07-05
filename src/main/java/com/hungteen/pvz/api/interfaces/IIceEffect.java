package com.hungteen.pvz.api.interfaces;

import java.util.Optional;

import net.minecraft.potion.EffectInstance;

public interface IIceEffect {

	Optional<EffectInstance> getColdEffect();
	
	Optional<EffectInstance> getFrozenEffect();
}
