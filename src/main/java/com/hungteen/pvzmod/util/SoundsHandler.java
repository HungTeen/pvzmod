package com.hungteen.pvzmod.util;

import com.hungteen.pvzmod.entities.npcs.EntityTreeMan;
import com.hungteen.pvzmod.entities.zombies.grassday.EntityNormalZombie;
import com.hungteen.pvzmod.entities.zombies.special.EntityDolphin;

import net.minecraft.entity.passive.EntityParrot;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = Reference.MODID)
public class SoundsHandler {

	public static final SoundEvent ZOMBIE_AMBIENT = createEvent("zombie_ambient");
	public static final SoundEvent PLANT_HURT = createEvent("plant_hurt");
	public static final SoundEvent CRAZYDAVE_AMBIENT = createEvent("crazydave_ambient");
	public static final SoundEvent CRAZYDAVE_HURT = createEvent("crazydave_hurt");
	public static final SoundEvent SUN_DIE = createEvent("sun_die");
	public static final SoundEvent POTATOMINE_EXPLODE = createEvent("mine_exp");
	public static final SoundEvent HUGE_WAVE = createEvent("huge_wave");
	public static final SoundEvent METAL_HURT = createEvent("metal_hurt");
	public static final SoundEvent PLASTIC_HURT = createEvent("plastic_hurt");
	public static final SoundEvent FIRE_PEA = createEvent("fire_pea");
	public static final SoundEvent FROZEN_PEA = createEvent("frozen_pea");
	public static final SoundEvent DROP_COIN = createEvent("drop_coin");
	public static final SoundEvent DROP_JEWEL = createEvent("drop_jewel");
	public static final SoundEvent DROP_SPECIAL = createEvent("drop_special");
	public static final SoundEvent GET_COIN = createEvent("get_coin");
	public static final SoundEvent GET_JEWEL = createEvent("get_jewel");
	public static final SoundEvent JALAPENO = createEvent("jalapeno");
	public static final SoundEvent CHERRY_BOMB = createEvent("cherry_bomb");
	public static final SoundEvent SQUASH_HMM = createEvent("squash_hmm");
	public static final SoundEvent GROUND_SHAKE = createEvent("ground_shake");
	public static final SoundEvent PAPER_ANGRY = createEvent("paper_angry");
	public static final SoundEvent PAPER_OUT = createEvent("paper_out");
	public static final SoundEvent START_DANCE = createEvent("start_dance");
	public static final SoundEvent CAR_START = createEvent("car_start");
	public static final SoundEvent CAR_EXPLOSION = createEvent("car_explosion");
	public static final SoundEvent DOLPHIN_AMBIENT = createEvent("dolphin_ambient");
	public static final SoundEvent DOLPHIN_JUMP = createEvent("dolphin_jump");
	public static final SoundEvent GAR_AMBIENT = createEvent("gar_ambient");
	public static final SoundEvent GAR_DIE = createEvent("gar_die");
	public static final SoundEvent IMP = createEvent("imp");
	public static final SoundEvent JACK_AMBIENT = createEvent("jack_ambient");
	public static final SoundEvent JACK_SURPRISE = createEvent("jack_surprise");
	public static final SoundEvent MELON_HIT = createEvent("melon_hit");
	public static final SoundEvent BALL_SHOOT = createEvent("ball_shoot");
	public static final SoundEvent POGO = createEvent("pogo");
	
	private static SoundEvent createEvent(String sound) {
		ResourceLocation name = new ResourceLocation(Reference.MODID, sound);
		return new SoundEvent(name).setRegistryName(name);
	}
	
	@SubscribeEvent
	public static void onSoundEventRegistration(RegistryEvent.Register<SoundEvent> event) {
	    event.getRegistry().registerAll(
	    		ZOMBIE_AMBIENT,
	    		PLANT_HURT,
	    		CRAZYDAVE_AMBIENT,
	    		CRAZYDAVE_HURT,
	    		SUN_DIE,
	    		POTATOMINE_EXPLODE,
	    		HUGE_WAVE,
	    		METAL_HURT,
	    		PLASTIC_HURT,
	    		FIRE_PEA,
	    		FROZEN_PEA,
	    		DROP_COIN,
	    		DROP_JEWEL,
	    		DROP_SPECIAL,
	    		GET_COIN,
	    		GET_JEWEL,
	    		JALAPENO,
	    		CHERRY_BOMB,
	    		SQUASH_HMM,
	    		GROUND_SHAKE,
	    		PAPER_ANGRY,
	    		PAPER_OUT,
	    		START_DANCE,
	    		CAR_START,
	    		CAR_EXPLOSION,
	    		DOLPHIN_AMBIENT,
	    		DOLPHIN_JUMP,
	    		GAR_AMBIENT,
	    		GAR_DIE,
	    		IMP,
	    		JACK_AMBIENT,
	    		JACK_SURPRISE,
	    		MELON_HIT,
	    		BALL_SHOOT,
	    		POGO
	    );
	    registerParrotSounds();//¸øðÐðÄÌí¼ÓÉùÒô
	}
	
	private static void registerParrotSounds() {
//		EntityParrot.registerMimicSound(EntityTreeMan.class, CRAZYDAVE_AMBIENT);
		EntityParrot.registerMimicSound(EntityNormalZombie.class, ZOMBIE_AMBIENT);
		EntityParrot.registerMimicSound(EntityDolphin.class, DOLPHIN_AMBIENT);
	}
}
