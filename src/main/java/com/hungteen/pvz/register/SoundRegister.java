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
	public static final RegistryObject<SoundEvent> PLANT_HURT = registerSound("plant_hurt");
	public static final RegistryObject<SoundEvent> ZOMBIE_SAY = registerSound("zombie_say");
	public static final RegistryObject<SoundEvent> CHERRY_BOMB = registerSound("cherry_bomb");
	public static final RegistryObject<SoundEvent> PLASTIC_HIT = registerSound("plastic_hit");
	public static final RegistryObject<SoundEvent> POTATO_MINE = registerSound("potato_mine");
	public static final RegistryObject<SoundEvent> ZOMBIE_FROZEN = registerSound("zombie_frozen");
	public static final RegistryObject<SoundEvent> METAL_HIT = registerSound("metal_hit");
	public static final RegistryObject<SoundEvent> HUGE_WAVE = registerSound("huge_wave");
	public static final RegistryObject<SoundEvent> CHOMP = registerSound("chomp");
	public static final RegistryObject<SoundEvent> SNOW_SHOOT = registerSound("snow_shoot");
	public static final RegistryObject<SoundEvent> DIRT_RISE = registerSound("dirt_rise");
	public static final RegistryObject<SoundEvent> SQUASH_HMM = registerSound("squash_hmm");
	public static final RegistryObject<SoundEvent> GROUND_SHAKE = registerSound("ground_shake");
	public static final RegistryObject<SoundEvent> DRAG = registerSound("drag");
	public static final RegistryObject<SoundEvent> JALAPENO = registerSound("jalapeno");
	public static final RegistryObject<SoundEvent> CAR_SPAWN = registerSound("car_spawn");
	public static final RegistryObject<SoundEvent> CAR_EXPLOSION = registerSound("car_explosion");
	public static final RegistryObject<SoundEvent> ZOMBIE_FIRE = registerSound("zombie_fire");
	public static final RegistryObject<SoundEvent> DAVE_SAY = registerSound("dave_say");
	public static final RegistryObject<SoundEvent> DAVE_HURT = registerSound("dave_hurt");
	public static final RegistryObject<SoundEvent> DAVE_BUY = registerSound("dave_buy");
	public static final RegistryObject<SoundEvent> DAVE_DIE = registerSound("dave_die");
	public static final RegistryObject<SoundEvent> DOLPHIN_SAY = registerSound("dolphin_say");
	public static final RegistryObject<SoundEvent> DOLPHIN_JUMP = registerSound("dolphin_jump");
	
	private static RegistryObject<SoundEvent> registerSound(String name){
		return SOUNDS.register(name, ()->{
			return new SoundEvent(StringUtil.prefix(name));
		});
	}
}
