package com.hungteen.pvz.client.render.entity.zombie;

import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;

import com.hungteen.pvz.utils.StringUtil;
import com.hungteen.pvz.utils.enums.Zombies;

import net.minecraft.util.ResourceLocation;

public class ZombieRenderHandler {

	public static final Map<Zombies, ResourceLocation> ZOMBIE_TEX = new EnumMap<>(Zombies.class);
	public static final Map<Zombies, Float> ZOMBIE_SCALE = new EnumMap<>(Zombies.class);
	public static final ResourceLocation NORMAL_ZOMBIE_TEX = get(Zombies.NORMAL_ZOMBIE);
	public static final ResourceLocation FLAG_ZOMBIE_TEX = get(Zombies.FLAG_ZOMBIE);
	public static final ResourceLocation CONEHEAD_ZOMBIE_TEX = get(Zombies.CONEHEAD_ZOMBIE);
	public static final ResourceLocation POLE_ZOMBIE_TEX = get(Zombies.POLE_ZOMBIE);
	public static final ResourceLocation BUCKETHEAD_ZOMBIE_TEX = get(Zombies.BUCKETHEAD_ZOMBIE);
	public static final ResourceLocation TOMB_STONE_TEX = get(Zombies.TOMB_STONE);
	public static final ResourceLocation NEWSPAPER_ZOMBIE_TEX = get(Zombies.NEWSPAPER_ZOMBIE);
	public static final ResourceLocation SCREENDOOR_ZOMBIE_TEX = get(Zombies.SCREENDOOR_ZOMBIE);
	public static final ResourceLocation FOOTBALL_ZOMBIE_TEX = get(Zombies.FOOTBALL_ZOMBIE);
	public static final ResourceLocation DACING_ZOMBIE_TEX = get(Zombies.DANCING_ZOMBIE);
	public static final ResourceLocation BACKUP_DANCER_TEX = get(Zombies.BACKUP_DANCER);
	public static final ResourceLocation GIGA_FOOTBALL_ZOMBIE_TEX = get(Zombies.GIGA_FOOTBALL_ZOMBIE);
	public static final ResourceLocation OLD_ZOMBIE_TEX = get(Zombies.OLD_ZOMBIE);
	public static final ResourceLocation SUNDAY_EDITION_ZOMBIE_TEX = get(Zombies.SUNDAY_EDITION_ZOMBIE);
	public static final ResourceLocation SNORKEL_ZOMBIE_TEX = get(Zombies.SNORKEL_ZOMBIE);
	public static final ResourceLocation ZOMBONI_TEX = get(Zombies.ZOMBONI);
	public static final ResourceLocation BOBSLE_TEAM_TEX = get(Zombies.BOBSLE_TEAM);
	public static final ResourceLocation BOBSLE_ZOMBIE_TEX = get(Zombies.BOBSLE_ZOMBIE);
	public static final ResourceLocation DOLPHIN_RIDER_TEX = get(Zombies.DOLPHIN_RIDER);
	public static final ResourceLocation DOLPHIN_RIDER_ZOMBIE_TEX = get(Zombies.DOLPHIN_RIDER_ZOMBIE);
	public static final ResourceLocation ZOMBIE_DOLPHIN_TEX = get(Zombies.ZOMBIE_DOLPHIN, 1);
	public static final ResourceLocation LAVA_ZOMBIE_TEX = get(Zombies.LAVA_ZOMBIE);
	public static final ResourceLocation JACK_IN_BOX_ZOMBIE_TEX = get(Zombies.JACK_IN_BOX_ZOMBIE);
	public static final ResourceLocation BALLOON_ZOMBIE_TEX = get(Zombies.BALLOON_ZOMBIE);
	public static final ResourceLocation DIGGER_ZOMBIE_TEX = get(Zombies.DIGGER_ZOMBIE);
	public static final ResourceLocation POGO_ZOMBIE_TEX = get(Zombies.POGO_ZOMBIE);
	public static final ResourceLocation YETI_ZOMBIE_TEX = get(Zombies.YETI_ZOMBIE);
	public static final ResourceLocation BUNGEE_ZOMBIE_TEX = get(Zombies.BUNGEE_ZOMBIE);
	public static final ResourceLocation LADDER_ZOMBIE_TEX = get(Zombies.LADDER_ZOMBIE);
	public static final ResourceLocation CATAPULT_ZOMBIE_TEX = get(Zombies.CATAPULT_ZOMBIE);
	public static final ResourceLocation GARGANTUAR_TEX = get(Zombies.GARGANTUAR, 0.75F);
	public static final ResourceLocation SAD_GARGANTUAR_TEX = get(Zombies.SAD_GARGANTUAR, 0.8F);
	public static final ResourceLocation IMP_TEX = get(Zombies.IMP);
	public static final ResourceLocation ZOMBOSS_TEX = get(Zombies.ZOMBOSS, 2F);
	public static final ResourceLocation PEASHOOTER_ZOMBIE_TEX = get(Zombies.PEASHOOTER_ZOMBIE);
	public static final ResourceLocation WALLNUT_ZOMBIE_TEX = get(Zombies.WALLNUT_ZOMBIE);
	public static final ResourceLocation GATLINGPEA_ZOMBIE_TEX = get(Zombies.GATLINGPEA_ZOMBIE);
	public static final ResourceLocation TALLNUT_ZOMBIE_TEX = get(Zombies.TALLNUT_ZOMBIE);
	public static final ResourceLocation JALAPENO_ZOMBIE_TEX = get(Zombies.JALAPENO_ZOMBIE);
	public static final ResourceLocation SQUASH_ZOMBIE_TEX = get(Zombies.SQUASH_ZOMBIE);
	public static final ResourceLocation PUMPKIN_ZOMBIE_TEX = get(Zombies.PUMPKIN_ZOMBIE);
	public static final ResourceLocation COFFIN_TEX = get(Zombies.COFFIN);
	public static final ResourceLocation MOURNER_ZOMBIE_TEX = get(Zombies.MOURNER_ZOMBIE);
	public static final ResourceLocation NOBLE_ZOMBIE_TEX = get(Zombies.NOBLE_ZOMBIE);
	public static final ResourceLocation RA_ZOMBIE_TEX = get(Zombies.RA_ZOMBIE);
	public static final ResourceLocation TRICK_ZOMBIE_TEX = get(Zombies.TRICK_ZOMBIE);
	public static final ResourceLocation GIGA_TOMB_STONE_TEX = get(Zombies.GIGA_TOMB_STONE);
	
	private static ResourceLocation get(Zombies zombie) {
		return get(zombie, 0.5F);
	}
	
	private static ResourceLocation get(Zombies zombie, float scale) {
		String sep = "";
		switch(zombie) {
		case NORMAL_ZOMBIE:
		case FLAG_ZOMBIE:
		case CONEHEAD_ZOMBIE:
		case POLE_ZOMBIE:
		case BUCKETHEAD_ZOMBIE:{
			sep = "grassday";
			break;
		}
		case TOMB_STONE:
		case GIGA_TOMB_STONE:
		case NEWSPAPER_ZOMBIE:
		case SCREENDOOR_ZOMBIE:
		case FOOTBALL_ZOMBIE:
		case DANCING_ZOMBIE:
		case BACKUP_DANCER:
		case GIGA_FOOTBALL_ZOMBIE:
		case OLD_ZOMBIE:
		case SUNDAY_EDITION_ZOMBIE:{
			sep = "grassnight";
			break;
		}
		case SNORKEL_ZOMBIE:
		case ZOMBONI:
		case BOBSLE_TEAM:
		case BOBSLE_ZOMBIE:
		case DOLPHIN_RIDER:
		case DOLPHIN_RIDER_ZOMBIE:
		case ZOMBIE_DOLPHIN:
		case LAVA_ZOMBIE:{
			sep = "poolday";
			break;
		}
		case JACK_IN_BOX_ZOMBIE:
		case BALLOON_ZOMBIE:
		case DIGGER_ZOMBIE:
		case POGO_ZOMBIE:
		case YETI_ZOMBIE:{
			sep = "poolnight";
			break;
		}
		case BUNGEE_ZOMBIE:
		case LADDER_ZOMBIE:
		case CATAPULT_ZOMBIE:
		case GARGANTUAR:
		case SAD_GARGANTUAR:
		case IMP:
		case ZOMBOSS:{
			sep = "roof";
			break;
		}
		case PEASHOOTER_ZOMBIE:
		case WALLNUT_ZOMBIE:
		case GATLINGPEA_ZOMBIE:
		case TALLNUT_ZOMBIE:
		case JALAPENO_ZOMBIE:
		case SQUASH_ZOMBIE:
		case PUMPKIN_ZOMBIE:{
			sep = "zombotany";
			break;
		}
		case COFFIN:
		case MOURNER_ZOMBIE:
		case NOBLE_ZOMBIE:
		case RA_ZOMBIE:
		case TRICK_ZOMBIE:{
			sep = "other";
			break;
		}
		default:
			break;
		}
		ResourceLocation res = StringUtil.prefix("textures/entity/zombie/" + sep + "/" + zombie.toString().toLowerCase() + ".png");
		put(zombie, res, scale);
		return res;
	}
	
	public static Optional<ResourceLocation> getZombieTex(Zombies zombie) {
		return Optional.ofNullable(ZOMBIE_TEX.get(zombie));
	}
	
	/**
	 * get zombie initial scale.
	 */
	public static float getZombieScale(Zombies zombie) {
		return ZOMBIE_SCALE.getOrDefault(zombie, 0.5F);
	}
	
	private static void put(Zombies zombie, ResourceLocation res, float scale) {
		ZOMBIE_TEX.put(zombie, res);
		ZOMBIE_SCALE.put(zombie, scale);
	}
}
