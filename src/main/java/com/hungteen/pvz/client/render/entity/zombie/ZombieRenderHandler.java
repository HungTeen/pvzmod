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
