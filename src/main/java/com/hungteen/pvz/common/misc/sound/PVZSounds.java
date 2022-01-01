package com.hungteen.pvz.common.misc.sound;

import java.util.function.Supplier;

import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;

public enum PVZSounds {

	SUN_COLLECT(() -> SoundRegister.SUN_PICK.get()),
	COIN_COLLECT(() -> SoundRegister.COIN_PICK.get()),
	JEWEL_COLLECT(() -> SoundRegister.JEWEL_PICK.get()),
	ENERGY_COLLECT(() -> SoundRegister.JEWEL_PICK.get()),//no sound source for it.
	PEA_SHOOT(() -> SoundEvents.SNOW_GOLEM_SHOOT),
	HUGE_WAVE(() -> SoundRegister.HUGE_WAVE.get()),
	INVASION_WARN(() -> SoundRegister.WARN.get()),
	WIN_MUSIC(() -> SoundRegister.WIN_MUSIC.get()),
	LOSE_MUSIC(() -> SoundRegister.LOSE_MUSIC.get()),
	SLOT_MACHINE(() -> SoundRegister.SLOT_MACHINE.get()),
	PLANT_GROW(() -> SoundRegister.PLANT_GROW.get()),
	JACK_SAY(() -> SoundRegister.JACK_SAY.get())
	;
	
	private final Supplier<SoundEvent> soundSupplier;
	
	private PVZSounds(Supplier<SoundEvent> soundSupplier) {
		this.soundSupplier = soundSupplier;
	}
	
	public SoundEvent get() {
		return this.soundSupplier.get();
	}
	
}
