package com.hungteen.pvz.client.render.entity.zombie;

import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;

import com.hungteen.pvz.utils.StringUtil;
import com.hungteen.pvz.utils.enums.Zombies;

import net.minecraft.util.ResourceLocation;

public class ZombieRenderHandler {

	public static final Map<Zombies, ResourceLocation> ZOMBIE_TEX = new EnumMap<>(Zombies.class);
	public static final ResourceLocation NORMAL_ZOMBIE_TEX = get(Zombies.NORMAL_ZOMBIE);
	public static final ResourceLocation FLAG_ZOMBIE_TEX = get(Zombies.FLAG_ZOMBIE);
	public static final ResourceLocation CONEHEAD_ZOMBIE_TEX = get(Zombies.CONEHEAD_ZOMBIE);
	public static final ResourceLocation POLE_ZOMBIE_TEX = get(Zombies.POLE_ZOMBIE);
	public static final ResourceLocation BUCKETHEAD_ZOMBIE_TEX = get(Zombies.BUCKETHEAD_ZOMBIE);
//	public static final ResourceLocation NORMAL_ZOMBIE_TEX = get(Zombies.NORMAL_ZOMBIE);
//	public static final ResourceLocation NORMAL_ZOMBIE_TEX = get(Zombies.NORMAL_ZOMBIE);
//	public static final ResourceLocation NORMAL_ZOMBIE_TEX = get(Zombies.NORMAL_ZOMBIE);
//	public static final ResourceLocation NORMAL_ZOMBIE_TEX = get(Zombies.NORMAL_ZOMBIE);
	
	private static ResourceLocation get(Zombies zombie) {
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
		default:
			break;
		}
		ResourceLocation res = StringUtil.prefix("textures/entity/zombie/" + sep + "/" + zombie.toString().toLowerCase() + ".png");
		putTex(zombie, res);
		return res;
	}
	
	public static Optional<ResourceLocation> getZombieTex(Zombies zombie) {
		return Optional.ofNullable(ZOMBIE_TEX.get(zombie));
	}
	
	private static void putTex(Zombies zombie, ResourceLocation res) {
		ZOMBIE_TEX.put(zombie, res);
	}
}
