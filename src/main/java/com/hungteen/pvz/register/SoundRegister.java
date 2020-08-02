package com.hungteen.pvz.register;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class SoundRegister {

	//https://minecraft.gamepedia.com/Sounds.json#Java_Edition_values
	public static final DeferredRegister<SoundEvent> SOUNDS = new DeferredRegister<>(ForgeRegistries.SOUND_EVENTS, PVZMod.MOD_ID);
	
	public static final RegistryObject<SoundEvent> SUN_PICK = registerSound("sun_pick");
	public static final RegistryObject<SoundEvent> COIN_DROP = registerSound("coin_drop");
	public static final RegistryObject<SoundEvent> COIN_PICK = registerSound("coin_pick");
	public static final RegistryObject<SoundEvent> JEWEL_DROP = registerSound("jewel_drop");
	public static final RegistryObject<SoundEvent> JEWEL_PICK = registerSound("jewel_pick");
	
	private static RegistryObject<SoundEvent> registerSound(String name)
	{
		return SOUNDS.register(name, ()->{
			return new SoundEvent(StringUtil.prefix(name));
		});
	}
}
