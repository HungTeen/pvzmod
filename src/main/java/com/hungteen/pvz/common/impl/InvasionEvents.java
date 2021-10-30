package com.hungteen.pvz.common.impl;

import java.util.ArrayList;
import java.util.List;

import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.api.types.IInvasionType;
import com.hungteen.pvz.api.types.IZombieType;
import com.hungteen.pvz.common.cache.FlagCache;
import com.hungteen.pvz.common.impl.misc.InvasionType;
import com.hungteen.pvz.common.impl.zombie.CustomZombies;
import com.hungteen.pvz.common.impl.zombie.GrassZombies;
import com.hungteen.pvz.common.impl.zombie.PoolZombies;
import com.hungteen.pvz.common.impl.zombie.RoofZombies;
import com.hungteen.pvz.common.impl.zombie.Zombotanies;
import com.hungteen.pvz.utils.MathUtil;
import com.hungteen.pvz.utils.others.WeightList;

import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class InvasionEvents extends InvasionType {
  
	private static final List<IInvasionType> LIST = new ArrayList<>();
	
	public static final InvasionType RANDOM = new InvasionEvents("random", new InvasionFeatures()
			.chance(PVZConfig.COMMON_CONFIG.InvasionSettings.RandomInvasionChance.get())
			.bundle(Bundles.RANDOM_ALL).color(TextFormatting.GREEN)
			.addZombie(GrassZombies.NORMAL_ZOMBIE)
			.available(world -> FlagCache.isEdgar090505Defeated())
	) {
		    @Override
			public List<IZombieType> getSpawnZombies(World world) {
		    	WeightList<IZombieType> list = new WeightList<>();
		    	ZombieType.getZombies().stream().filter(type -> type.getRandomInvasionWeight() > 0).forEach(type -> {
		    		list.addItem(type, type.getRandomInvasionWeight());
		    	});
		    	List<IZombieType> zombies = new ArrayList<>();
		    	final int spawnCnt = MathUtil.getRandomMinMax(world.getRandom(), 1, 5);
		    	for(int i = 0; i < spawnCnt; ++ i) {
		    		final IZombieType type = list.getRandomItem(world.getRandom()).get();
		    		if(! zombies.contains(type)) {
		    			zombies.add(type);
		    		}
		    	}
				return zombies;
			}
	};
	
	/* Spawn Invasion Events */
	
	public static final InvasionType BUCKET = new InvasionEvents("bucket", new InvasionFeatures()
		    .chance(PVZConfig.COMMON_CONFIG.InvasionSettings.BucketInvasionChance.get())
		    .bundle(Bundles.GRASS_DAY_PLANT).color(TextFormatting.GRAY)
		    .addZombie(GrassZombies.NORMAL_ZOMBIE).addZombie(GrassZombies.CONEHEAD_ZOMBIE)
		    .addZombie(GrassZombies.POLE_ZOMBIE).addZombie(GrassZombies.BUCKETHEAD_ZOMBIE)
		    .available(world -> true)
	);
	
	public static final InvasionType NEWSPAPER = new InvasionEvents("newspaper", new InvasionFeatures()
			.chance(PVZConfig.COMMON_CONFIG.InvasionSettings.NewspaperInvasionChance.get())
			.bundle(Bundles.GRASS_NIGHT_PLANT).color(TextFormatting.DARK_GRAY)
			.addZombie(GrassZombies.NORMAL_ZOMBIE).addZombie(GrassZombies.NEWSPAPER_ZOMBIE)
			.addZombie(GrassZombies.OLD_ZOMBIE).addZombie(GrassZombies.SUNDAY_EDITION_ZOMBIE)
			.available(world -> true)
	);
	
	public static final InvasionType FOOTBALL = new InvasionEvents("football", new InvasionFeatures()
			.chance(PVZConfig.COMMON_CONFIG.InvasionSettings.FootballInvasionChance.get())
			.bundle(Bundles.GRASS_NIGHT_PLANT).color(TextFormatting.BLACK)
			.addZombie(GrassZombies.NORMAL_ZOMBIE).addZombie(GrassZombies.SCREENDOOR_ZOMBIE)
			.addZombie(GrassZombies.DANCING_ZOMBIE).addZombie(GrassZombies.FOOTBALL_ZOMBIE)
			.addZombie(GrassZombies.GIGA_FOOTBALL_ZOMBIE)
			.available(world -> true)
	);
	
	public static final InvasionType WATER = new InvasionEvents("water", new InvasionFeatures()
			.chance(PVZConfig.COMMON_CONFIG.InvasionSettings.WaterInvasionChance.get())
			.bundle(Bundles.POOL_DAY_PLANT).color(TextFormatting.DARK_BLUE)
			.addZombie(GrassZombies.NORMAL_ZOMBIE).addZombie(PoolZombies.SNORKEL_ZOMBIE)
			.addZombie(PoolZombies.ZOMBONI).addZombie(PoolZombies.BOBSLE_TEAM)
			.addZombie(PoolZombies.DOLPHIN_RIDER).addZombie(CustomZombies.LAVA_ZOMBIE)
			.available(world -> true)
	);
	
	public static final InvasionType YETI = new InvasionEvents("yeti", new InvasionFeatures()
			.chance(PVZConfig.COMMON_CONFIG.InvasionSettings.YetiInvasionChance.get())
			.bundle(Bundles.POOL_NIGHT_PLANT).color(TextFormatting.AQUA)
			.addZombie(GrassZombies.NORMAL_ZOMBIE).addZombie(PoolZombies.JACK_IN_BOX_ZOMBIE)
			.addZombie(PoolZombies.BALLOON_ZOMBIE).addZombie(PoolZombies.DIGGER_ZOMBIE)
			.addZombie(PoolZombies.POGO_ZOMBIE)
			.available(world -> true)
	);
	
	public static final InvasionType ROOF = new InvasionEvents("roof", new InvasionFeatures()
			.chance(PVZConfig.COMMON_CONFIG.InvasionSettings.RoofInvasionChance.get())
			.bundle(Bundles.ROOF_PLANT).color(TextFormatting.GOLD)
			.addZombie(GrassZombies.NORMAL_ZOMBIE).addZombie(RoofZombies.BUNGEE_ZOMBIE)
			.addZombie(RoofZombies.LADDER_ZOMBIE).addZombie(RoofZombies.CATAPULT_ZOMBIE)
			.addZombie(RoofZombies.GARGANTUAR)
			.available(world -> true)
	);
	
	public static final InvasionType HALLOWEEN = new InvasionEvents("halloween", new InvasionFeatures()
			.chance(PVZConfig.COMMON_CONFIG.InvasionSettings.HalloweenInvasionChance.get())
			.bundle(Bundles.RANDOM_ALL).color(TextFormatting.GOLD)
			.addZombie(GrassZombies.NORMAL_ZOMBIE).addZombie(CustomZombies.TRICK_ZOMBIE)
			.addZombie(Zombotanies.PUMPKIN_ZOMBIE)
			.available(world -> true)
	);
	
	/* need defeat Edgar-090505 */
	
	public static final InvasionType BUNGEE = new InvasionEvents("bungee", new InvasionFeatures()
			.chance(PVZConfig.COMMON_CONFIG.InvasionSettings.BungeeInvasionChance.get())
			.bundle(Bundles.ROOF_PLANT).color(TextFormatting.RED)
			.addZombie(RoofZombies.BUNGEE_ZOMBIE)
			.available(world -> FlagCache.isEdgar090505Defeated())
	);
	
	public static final InvasionType METAL = new InvasionEvents("metal", new InvasionFeatures()
			.chance(PVZConfig.COMMON_CONFIG.InvasionSettings.MetalInvasionChance.get())
			.bundle(Bundles.RANDOM_ALL).color(TextFormatting.BLACK)
			.addZombie(GrassZombies.BUCKETHEAD_ZOMBIE).addZombie(GrassZombies.SCREENDOOR_ZOMBIE)
			.addZombie(GrassZombies.FOOTBALL_ZOMBIE).addZombie(GrassZombies.GIGA_FOOTBALL_ZOMBIE)
			.addZombie(PoolZombies.JACK_IN_BOX_ZOMBIE).addZombie(PoolZombies.DIGGER_ZOMBIE)
			.addZombie(PoolZombies.POGO_ZOMBIE).addZombie(RoofZombies.LADDER_ZOMBIE)
			.available(world -> FlagCache.isEdgar090505Defeated())
	);
	
	public static final InvasionType GIANT = new InvasionEvents("giant", new InvasionFeatures()
			.chance(PVZConfig.COMMON_CONFIG.InvasionSettings.GiantInvasionChance.get())
			.bundle(Bundles.UPGRADE_PLANT).color(TextFormatting.DARK_RED)
			.addZombie(RoofZombies.GARGANTUAR).addZombie(RoofZombies.GIGA_GARGANTUAR)
			.available(world -> FlagCache.isEdgar090505Defeated())
	);
	
	public static final InvasionType ZOMBOTANY1 = new InvasionEvents("zombotany1", new InvasionFeatures()
			.chance(PVZConfig.COMMON_CONFIG.InvasionSettings.Zombotany1InvasionChance.get())
			.bundle(Bundles.RANDOM_ALL).color(TextFormatting.DARK_GREEN)
			.addZombie(Zombotanies.PEASHOOTER_ZOMBIE).addZombie(Zombotanies.WALLNUT_ZOMBIE)
			.available(world -> FlagCache.isEdgar090505Defeated())
	);
	
	public static final InvasionType ZOMBOTANY2 = new InvasionEvents("zombotany2", new InvasionFeatures()
			.chance(PVZConfig.COMMON_CONFIG.InvasionSettings.Zombotany2InvasionChance.get())
			.bundle(Bundles.RANDOM_ALL).color(TextFormatting.DARK_GREEN)
			.addZombie(Zombotanies.PEASHOOTER_ZOMBIE).addZombie(Zombotanies.WALLNUT_ZOMBIE)
			.addZombie(Zombotanies.GATLINGPEA_ZOMBIE).addZombie(Zombotanies.TALLNUT_ZOMBIE)
			.addZombie(Zombotanies.SQUASH_ZOMBIE).addZombie(Zombotanies.JALAPENO_ZOMBIE)
			.available(world -> FlagCache.isEdgar090505Defeated())
	);
	
	/* Assist Invasion Events */
	
	public static final InvasionType FOG = new InvasionEvents("fog", new InvasionFeatures()
			.chance(PVZConfig.COMMON_CONFIG.InvasionSettings.FogInvasionChance.get()).color(TextFormatting.GRAY)
			.available(world -> true)
	);
	
	public static final InvasionType MINI = new InvasionEvents("mini", new InvasionFeatures()
			.chance(PVZConfig.COMMON_CONFIG.InvasionSettings.MiniInvasionChance.get()).color(TextFormatting.DARK_AQUA)
			.available(world -> FlagCache.isEdgar090505Defeated())
	);
	
	public static final InvasionType INVIS = new InvasionEvents("invis", new InvasionFeatures()
			.chance(PVZConfig.COMMON_CONFIG.InvasionSettings.InvisInvasionChance.get()).color(TextFormatting.DARK_GRAY)
			.available(world -> FlagCache.isEdgar090505Defeated())
	);
	
	public static void register() {
		registerInvasions(LIST);
	}
	
	public InvasionEvents(String name, InvasionFeatures features) {
		super(name, features);
		LIST.add(this);
	}
	
	@Override
	public String getModID() {
		return PVZMod.MOD_ID;
	}

}
