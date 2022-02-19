package com.hungteen.pvz.common.misc.sound;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.utils.StringUtil;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class SoundRegister {

	//https://minecraft.gamepedia.com/Sounds.json#Java_Edition_values
	public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, PVZMod.MOD_ID);

	public static final RegistryObject<SoundEvent> ADVANCEMENT = registerSound("advancement");
	public static final RegistryObject<SoundEvent> ZOMBIE_SIREN = registerSound("zombie_siren");
	public static final RegistryObject<SoundEvent> PLANT_SIREN = registerSound("plant_siren");
	public static final RegistryObject<SoundEvent> BALLOON_INFLATE = registerSound("balloon_inflate");
	public static final RegistryObject<SoundEvent> BALLOON_POP = registerSound("balloon_pop");
	public static final RegistryObject<SoundEvent> BASKETBALL = registerSound("basketball");
	public static final RegistryObject<SoundEvent> BIG_CHOMP = registerSound("big_chomp");
	public static final RegistryObject<SoundEvent> BLOVER = registerSound("blover");
	public static final RegistryObject<SoundEvent> POLE = registerSound("pole");
	public static final RegistryObject<SoundEvent> POLE_JUMP = registerSound("pole_jump");
	public static final RegistryObject<SoundEvent> HAMMER_BONK = registerSound("hammer_bonk");
	public static final RegistryObject<SoundEvent> EDGAR_SHOOT = registerSound("edgar_shoot");
	public static final RegistryObject<SoundEvent> EDGAR_EXPLOSION = registerSound("edgar_explosion");
	public static final RegistryObject<SoundEvent> BOWLING = registerSound("bowling");
	public static final RegistryObject<SoundEvent> BOWLING_HIT = registerSound("bowling_hit");
	public static final RegistryObject<SoundEvent> BUG_SPRAY = registerSound("bug_spray");
	public static final RegistryObject<SoundEvent> BUNGEE_SCREAM = registerSound("bungee_scream");
	public static final RegistryObject<SoundEvent> BUTTER = registerSound("butter");
	public static final RegistryObject<SoundEvent> NO = registerSound("no");
	public static final RegistryObject<SoundEvent> CHERRY_BOMB = registerSound("cherry_bomb");
	public static final RegistryObject<SoundEvent> JEWEL_DROP = registerSound("jewel_drop");
	public static final RegistryObject<SoundEvent> CHOMP = registerSound("chomp");
	public static final RegistryObject<SoundEvent> COB_LAUNCH = registerSound("cob_launch");
	public static final RegistryObject<SoundEvent> COIN_PICK = registerSound("coin_pick");
	public static final RegistryObject<SoundEvent> CRAZY_DAVE = registerSound("crazy_dave");
	public static final RegistryObject<SoundEvent> CRAZY_SAY = registerSound("crazy_say");
	public static final RegistryObject<SoundEvent> DAVE_HURT = registerSound("dave_hurt");
	public static final RegistryObject<SoundEvent> DAVE_HAPPY = registerSound("dave_happy");
	public static final RegistryObject<SoundEvent> DANCE_MUSIC = registerSound("dance_music");
	public static final RegistryObject<SoundEvent> JEWEL_PICK = registerSound("jewel_pick");
	public static final RegistryObject<SoundEvent> DIRT_RISE = registerSound("dirt_rise");
	public static final RegistryObject<SoundEvent> DOLPHIN_SAY = registerSound("dolphin_say");
	public static final RegistryObject<SoundEvent> DOLPHIN_JUMP = registerSound("dolphin_jump");
	public static final RegistryObject<SoundEvent> DOOM_SHROOM = registerSound("doom_shroom");
	public static final RegistryObject<SoundEvent> EDGAR_LAUGH = registerSound("edgar_laugh");
	public static final RegistryObject<SoundEvent> COIN_DROP = registerSound("coin_drop");
	public static final RegistryObject<SoundEvent> CAR_EXPLOSION = registerSound("car_explosion");
	public static final RegistryObject<SoundEvent> FERTILIZER = registerSound("fertilizer");
	public static final RegistryObject<SoundEvent> FINAL_WIN = registerSound("final_win");
	public static final RegistryObject<SoundEvent> FINAL_WAVE = registerSound("final_wave");
	public static final RegistryObject<SoundEvent> FLAME_HIT = registerSound("flame_hit");
	public static final RegistryObject<SoundEvent> DRAG = registerSound("drag");
	public static final RegistryObject<SoundEvent> FROZEN = registerSound("frozen");
	public static final RegistryObject<SoundEvent> FUME = registerSound("fume");
	public static final RegistryObject<SoundEvent> GROUND_SHAKE = registerSound("ground_shake");
	public static final RegistryObject<SoundEvent> GARGANTUAR_DEATH = registerSound("gargantuar_death");
	public static final RegistryObject<SoundEvent> ZOMBIE_GROAN = registerSound("zombie_groan");
	public static final RegistryObject<SoundEvent> PLANT_DEATH = registerSound("plant_death");
	public static final RegistryObject<SoundEvent> HUGE_WAVE = registerSound("huge_wave");
	public static final RegistryObject<SoundEvent> ROBOT_MOVE = registerSound("robot_move");
	public static final RegistryObject<SoundEvent> IMP_HAPPY = registerSound("imp_happy");
	public static final RegistryObject<SoundEvent> JACK_SURPRISE = registerSound("jack_surprise");
	public static final RegistryObject<SoundEvent> JACK_MUSIC = registerSound("jack_music");
	public static final RegistryObject<SoundEvent> JALAPENO = registerSound("jalapeno");
	public static final RegistryObject<SoundEvent> STEEL_LADDER = registerSound("steel_ladder");
	public static final RegistryObject<SoundEvent> LAWN_MOWER = registerSound("lawn_mower");
	public static final RegistryObject<SoundEvent> GARGANTUAR_GROAN = registerSound("gargantuar_groan");
	public static final RegistryObject<SoundEvent> LOSE_MUSIC = registerSound("lose_music");
	public static final RegistryObject<SoundEvent> MAGNET = registerSound("magnet");
	public static final RegistryObject<SoundEvent> MELON_HIT = registerSound("melon_hit");
	public static final RegistryObject<SoundEvent> HYPNO = registerSound("hypno");
	public static final RegistryObject<SoundEvent> ZOMBIE_ANGRY = registerSound("zombie_angry");
	public static final RegistryObject<SoundEvent> CHANGE_PAPER = registerSound("change_paper");
	public static final RegistryObject<SoundEvent> PAPER_BROKEN = registerSound("paper_broken");
	public static final RegistryObject<SoundEvent> PHONOGRAPH = registerSound("phonograph");
	public static final RegistryObject<SoundEvent> PLACE_PLANT_GROUND = registerSound("place_plant_ground");
	public static final RegistryObject<SoundEvent> PLACE_PLANT_WATER = registerSound("place_plant_water");
	public static final RegistryObject<SoundEvent> PLASTIC_HIT = registerSound("plastic_hit");
	public static final RegistryObject<SoundEvent> POGO = registerSound("pogo");
	public static final RegistryObject<SoundEvent> SUN_PICK = registerSound("sun_pick");
	public static final RegistryObject<SoundEvent> POOL_CLEANER = registerSound("pool_cleaner");
	public static final RegistryObject<SoundEvent> POTATO_MINE = registerSound("potato_mine");
	public static final RegistryObject<SoundEvent> PRIZE_DROP = registerSound("prize_drop");
	public static final RegistryObject<SoundEvent> PUFF = registerSound("puff");
	public static final RegistryObject<SoundEvent> READY = registerSound("ready");
	public static final RegistryObject<SoundEvent> DAVE_SCREAM = registerSound("dave_scream");
	public static final RegistryObject<SoundEvent> METAL_HIT = registerSound("metal_hit");
	public static final RegistryObject<SoundEvent> SHOVEL = registerSound("shovel");
	public static final RegistryObject<SoundEvent> SLOT_MACHINE = registerSound("slot_machine");
	public static final RegistryObject<SoundEvent> SLURP = registerSound("slurp");
	public static final RegistryObject<SoundEvent> SNOW_SHOOT = registerSound("snow_shoot");
	public static final RegistryObject<SoundEvent> SPLAT = registerSound("splat");
	public static final RegistryObject<SoundEvent> SQUASH_HMM = registerSound("squash_hmm");
	public static final RegistryObject<SoundEvent> SWING = registerSound("swing");
	public static final RegistryObject<SoundEvent> PULT_THROW = registerSound("pult_throw");
	public static final RegistryObject<SoundEvent> VASE_BREAKING = registerSound("vase_breaking");
	public static final RegistryObject<SoundEvent> WAKE_UP = registerSound("wake_up");
	public static final RegistryObject<SoundEvent> WIN_MUSIC = registerSound("win_music");
	public static final RegistryObject<SoundEvent> PLANT_GROW = registerSound("plant_grow");
	public static final RegistryObject<SoundEvent> PLANTERN = registerSound("plantern");
	public static final RegistryObject<SoundEvent> YUCK = registerSound("yuck");
	public static final RegistryObject<SoundEvent> CAR = registerSound("car");
	public static final RegistryObject<SoundEvent> BRUH = registerSound("bruh");
	public static final RegistryObject<SoundEvent> PENNY_SAY = registerSound("penny_say");

	//disc
	public static final RegistryObject<SoundEvent> ZOMBIE_ON_YOUR_LAWN = registerSound("zombie_on_your_lawn");
	public static final RegistryObject<SoundEvent> ZEN_GARDEN = registerSound("zen_garden");

	private static RegistryObject<SoundEvent> registerSound(String name){
		return SOUNDS.register(name, ()->{
			return new SoundEvent(StringUtil.prefix(name));
		});
	}

}
