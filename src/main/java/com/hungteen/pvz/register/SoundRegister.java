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
	
	//spawn
	public static final RegistryObject<SoundEvent> COIN_DROP = registerSound("coin_drop");
	public static final RegistryObject<SoundEvent> JEWEL_DROP = registerSound("jewel_drop");
	public static final RegistryObject<SoundEvent> DIRT_RISE = registerSound("dirt_rise");
	public static final RegistryObject<SoundEvent> CAR_SPAWN = registerSound("car_spawn");
	public static final RegistryObject<SoundEvent> BOWLING_SPAWN = registerSound("bowling_spawn");
	//death
	public static final RegistryObject<SoundEvent> SUN_PICK = registerSound("sun_pick");
	public static final RegistryObject<SoundEvent> COIN_PICK = registerSound("coin_pick");
	public static final RegistryObject<SoundEvent> JEWEL_PICK = registerSound("jewel_pick");
	public static final RegistryObject<SoundEvent> CAR_EXPLOSION = registerSound("car_explosion");
	public static final RegistryObject<SoundEvent> DAVE_DIE = registerSound("dave_die");
	public static final RegistryObject<SoundEvent> PAPER_GONE = registerSound("paper_gone");
	public static final RegistryObject<SoundEvent> GARGANTUAR_DEATH = registerSound("gargantuar_death");
	//hit
	public static final RegistryObject<SoundEvent> PLANT_HURT = registerSound("plant_hurt");
	public static final RegistryObject<SoundEvent> PLASTIC_HIT = registerSound("plastic_hit");
	public static final RegistryObject<SoundEvent> METAL_HIT = registerSound("metal_hit");
	public static final RegistryObject<SoundEvent> ZOMBIE_FROZEN = registerSound("zombie_frozen");
	public static final RegistryObject<SoundEvent> ZOMBIE_FIRE = registerSound("zombie_fire");
	public static final RegistryObject<SoundEvent> DAVE_HURT = registerSound("dave_hurt");
	public static final RegistryObject<SoundEvent> PEA_HIT = registerSound("pea_hit");
	public static final RegistryObject<SoundEvent> BOWLING_HIT = registerSound("bowling_hit");
	public static final RegistryObject<SoundEvent> BOWLING_BOMB = registerSound("bowling_bomb");
	public static final RegistryObject<SoundEvent> MELON_HIT = registerSound("melon_hit");
	public static final RegistryObject<SoundEvent> BUTTER_HIT = registerSound("butter_hit");
	//say
	public static final RegistryObject<SoundEvent> ZOMBIE_SAY = registerSound("zombie_say");
	public static final RegistryObject<SoundEvent> SQUASH_HMM = registerSound("squash_hmm");
	public static final RegistryObject<SoundEvent> DAVE_SAY = registerSound("dave_say");
	public static final RegistryObject<SoundEvent> DOLPHIN_SAY = registerSound("dolphin_say");
	public static final RegistryObject<SoundEvent> JACK_SAY = registerSound("jack_say");
	public static final RegistryObject<SoundEvent> JACK_SURPRISE = registerSound("jack_surprise");
	public static final RegistryObject<SoundEvent> BRUH = registerSound("bruh");
	public static final RegistryObject<SoundEvent> PENNY_SAY = registerSound("penny_say");
	public static final RegistryObject<SoundEvent> BUNGEE_SCREAM = registerSound("bungee_scream");
	public static final RegistryObject<SoundEvent> YUCK = registerSound("yuck");
	public static final RegistryObject<SoundEvent> GARGANTUAR_SAY = registerSound("gargantuar_say");
	//attack
	public static final RegistryObject<SoundEvent> CHERRY_BOMB = registerSound("cherry_bomb");
	public static final RegistryObject<SoundEvent> POTATO_MINE = registerSound("potato_mine");
	public static final RegistryObject<SoundEvent> SNOW_SHOOT = registerSound("snow_shoot");
	public static final RegistryObject<SoundEvent> CHOMP = registerSound("chomp");
	public static final RegistryObject<SoundEvent> GROUND_SHAKE = registerSound("ground_shake");
	public static final RegistryObject<SoundEvent> DRAG = registerSound("drag");
	public static final RegistryObject<SoundEvent> JALAPENO = registerSound("jalapeno");
	public static final RegistryObject<SoundEvent> DOLPHIN_JUMP = registerSound("dolphin_jump");
	public static final RegistryObject<SoundEvent> PUFF = registerSound("puff");
	public static final RegistryObject<SoundEvent> FUME = registerSound("fume");
	public static final RegistryObject<SoundEvent> ANGRY = registerSound("angry");
	public static final RegistryObject<SoundEvent> CHARM = registerSound("charm");
	public static final RegistryObject<SoundEvent> DOOM = registerSound("doom");
	public static final RegistryObject<SoundEvent> MAGNET = registerSound("magnet");
	public static final RegistryObject<SoundEvent> LAWN_MOWER = registerSound("lawn_mower");
	public static final RegistryObject<SoundEvent> PLANT_THROW = registerSound("plant_throw");
	public static final RegistryObject<SoundEvent> BASKETBALL = registerSound("basketball");
	public static final RegistryObject<SoundEvent> THROW_IMP = registerSound("throw_imp");
	//misc
	public static final RegistryObject<SoundEvent> PLANT_ON_GROUND= registerSound("plant_on_ground");
	public static final RegistryObject<SoundEvent> PLANT_IN_WATER = registerSound("plant_in_water");
	public static final RegistryObject<SoundEvent> HUGE_WAVE = registerSound("huge_wave");
	public static final RegistryObject<SoundEvent> DAVE_BUY = registerSound("dave_buy");
	public static final RegistryObject<SoundEvent> SLURP = registerSound("slurp");
	public static final RegistryObject<SoundEvent> PLANT_GROW = registerSound("plant_grow");
	public static final RegistryObject<SoundEvent> WAKE_UP = registerSound("wake_up");
	public static final RegistryObject<SoundEvent> WARN = registerSound("warn");
	public static final RegistryObject<SoundEvent> POGO = registerSound("pogo");
	public static final RegistryObject<SoundEvent> PLANTERN = registerSound("plantern");
	public static final RegistryObject<SoundEvent> BALLOON_FULL = registerSound("balloon_full");
	public static final RegistryObject<SoundEvent> BALLOON_POP = registerSound("balloon_pop");
	public static final RegistryObject<SoundEvent> BLOVER = registerSound("blover");
	public static final RegistryObject<SoundEvent> WIN_MUSIC = registerSound("win_music");
	public static final RegistryObject<SoundEvent> LOSE_MUSIC = registerSound("lose_music");
	public static final RegistryObject<SoundEvent> SLOT_MACHINE = registerSound("slot_machine");
	
	private static RegistryObject<SoundEvent> registerSound(String name){
		return SOUNDS.register(name, ()->{
			return new SoundEvent(StringUtil.prefix(name));
		});
	}
}
