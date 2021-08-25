package com.hungteen.pvz.register;

import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.common.entity.drop.SunEntity;
import com.hungteen.pvz.common.misc.SpawnChecker;
import com.hungteen.pvz.utils.BiomeUtil;
import com.hungteen.pvz.utils.ConfigUtil;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntitySpawnPlacementRegistry.PlacementType;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo.Spawners;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid=PVZMod.MOD_ID, bus=Mod.EventBusSubscriber.Bus.MOD)
public class EntitySpawnRegister {

	public static final PlacementType IN_SKY = PlacementType.create("pvz_in_sky", (world, pos, type) -> {
		return world.canSeeSky(pos) && world.canSeeSky(pos.offset(0, - 5, 0));
	});
	
	public static final PlacementType IN_HIGH_SKY = PlacementType.create("pvz_in_sky", (world, pos, type) -> {
		return world.canSeeSky(pos) && world.canSeeSky(pos.offset(0, - 20, 0));
	});
	
	public static final PlacementType ON_SNOW = PlacementType.create("pvz_on_snow", (world, pos, type) -> {
		return world.getBlockState(pos).getBlock() == Blocks.SNOW || world.getBlockState(pos.below()).getBlock() == Blocks.SNOW_BLOCK;
	});
	
	@SubscribeEvent
	public static void registerEntitySpawns(RegistryEvent.Register<EntityType<?>> evt) {
		EntitySpawnPlacementRegistry.register(EntityRegister.SUN.get(), IN_SKY, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, SunEntity::canSunSpawn);
//		EntitySpawnPlacementRegistry.register(EntityRegister.FOODIE_ZOMBIE.get(), PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, FoodieZombieEntity::canSpawn);
		EntitySpawnPlacementRegistry.register(EntityRegister.CRAZY_DAVE.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MobEntity::checkMobSpawnRules);
		
		EntitySpawnPlacementRegistry.register(EntityRegister.NORMAL_ZOMBIE.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, SpawnChecker::canGroundInvasionZombieSpawn);
		EntitySpawnPlacementRegistry.register(EntityRegister.FLAG_ZOMBIE.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, SpawnChecker::canGroundInvasionZombieSpawn);
		EntitySpawnPlacementRegistry.register(EntityRegister.CONEHEAD_ZOMBIE.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, SpawnChecker::canGroundInvasionZombieSpawn);
		EntitySpawnPlacementRegistry.register(EntityRegister.POLE_ZOMBIE.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, SpawnChecker::canGroundInvasionZombieSpawn);
		EntitySpawnPlacementRegistry.register(EntityRegister.BUCKETHEAD_ZOMBIE.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, SpawnChecker::canGroundInvasionZombieSpawn);
		
		EntitySpawnPlacementRegistry.register(EntityRegister.TOMB_STONE.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, SpawnChecker::canNightZombieSpawn);
		EntitySpawnPlacementRegistry.register(EntityRegister.GIGA_TOMB_STONE.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, SpawnChecker::canNightZombieSpawn);
	    EntitySpawnPlacementRegistry.register(EntityRegister.NEWSPAPER_ZOMBIE.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, SpawnChecker::canGroundInvasionZombieSpawn);
		EntitySpawnPlacementRegistry.register(EntityRegister.SCREENDOOR_ZOMBIE.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, SpawnChecker::canGroundInvasionZombieSpawn);
		EntitySpawnPlacementRegistry.register(EntityRegister.FOOTBALL_ZOMBIE.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, SpawnChecker::canGroundInvasionZombieSpawn);
		EntitySpawnPlacementRegistry.register(EntityRegister.GIGA_FOOTBALL_ZOMBIE.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, SpawnChecker::canGroundInvasionZombieSpawn);
		EntitySpawnPlacementRegistry.register(EntityRegister.DANCING_ZOMBIE.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, SpawnChecker::canGroundInvasionZombieSpawn);
		EntitySpawnPlacementRegistry.register(EntityRegister.BACKUP_DANCER.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, SpawnChecker::canGroundInvasionZombieSpawn);
		EntitySpawnPlacementRegistry.register(EntityRegister.OLD_ZOMBIE.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, SpawnChecker::canGroundInvasionZombieSpawn);
		EntitySpawnPlacementRegistry.register(EntityRegister.SUNDAY_EDITION_ZOMBIE.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, SpawnChecker::canGroundInvasionZombieSpawn);
		
		EntitySpawnPlacementRegistry.register(EntityRegister.SNORKEL_ZOMBIE.get(), PlacementType.NO_RESTRICTIONS, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, SpawnChecker::canWaterInvasionZombieSpawn);
		EntitySpawnPlacementRegistry.register(EntityRegister.ZOMBONI.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, SpawnChecker::canGroundInvasionZombieSpawn);
		EntitySpawnPlacementRegistry.register(EntityRegister.BOBSLE_TEAM.get(), ON_SNOW, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, SpawnChecker::canGroundInvasionZombieSpawn);
//		EntitySpawnPlacementRegistry.register(EntityRegister.ZOMBIE_DOLPHIN.get(), PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, FoodieZombieEntity::canSpawn);
		EntitySpawnPlacementRegistry.register(EntityRegister.DOLPHIN_RIDER.get(), PlacementType.NO_RESTRICTIONS, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, SpawnChecker::canWaterInvasionZombieSpawn);
		EntitySpawnPlacementRegistry.register(EntityRegister.LAVA_ZOMBIE.get(), PlacementType.NO_RESTRICTIONS, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, SpawnChecker::canWaterInvasionZombieSpawn);
		
		EntitySpawnPlacementRegistry.register(EntityRegister.PUMPKIN_ZOMBIE.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, SpawnChecker::canGroundInvasionZombieSpawn);
		EntitySpawnPlacementRegistry.register(EntityRegister.TRICK_ZOMBIE.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, SpawnChecker::canGroundInvasionZombieSpawn);
		
		EntitySpawnPlacementRegistry.register(EntityRegister.JACK_IN_BOX_ZOMBIE.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, SpawnChecker::canGroundInvasionZombieSpawn);
		EntitySpawnPlacementRegistry.register(EntityRegister.BALLOON_ZOMBIE.get(), IN_SKY, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, SpawnChecker::canSkyInvasionZombieSpawn);
		EntitySpawnPlacementRegistry.register(EntityRegister.DIGGER_ZOMBIE.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, SpawnChecker::canGroundInvasionZombieSpawn);
		EntitySpawnPlacementRegistry.register(EntityRegister.POGO_ZOMBIE.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, SpawnChecker::canGroundInvasionZombieSpawn);
		EntitySpawnPlacementRegistry.register(EntityRegister.YETI_ZOMBIE.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, SpawnChecker::canYetiSpawn);
		
		EntitySpawnPlacementRegistry.register(EntityRegister.BUNGEE_ZOMBIE.get(), IN_SKY, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, SpawnChecker::canSkyInvasionZombieSpawn);
		EntitySpawnPlacementRegistry.register(EntityRegister.LADDER_ZOMBIE.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, SpawnChecker::canGroundInvasionZombieSpawn);
		EntitySpawnPlacementRegistry.register(EntityRegister.CATAPULT_ZOMBIE.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, SpawnChecker::canGroundInvasionZombieSpawn);
		EntitySpawnPlacementRegistry.register(EntityRegister.GARGANTUAR.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, SpawnChecker::canGroundInvasionZombieSpawn);
		EntitySpawnPlacementRegistry.register(EntityRegister.GIGA_GARGANTUAR.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, SpawnChecker::canGroundInvasionZombieSpawn);
		
		EntitySpawnPlacementRegistry.register(EntityRegister.PEASHOOTER_ZOMBIE.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, SpawnChecker::canGroundInvasionZombieSpawn);
		EntitySpawnPlacementRegistry.register(EntityRegister.WALLNUT_ZOMBIE.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, SpawnChecker::canGroundInvasionZombieSpawn);
		EntitySpawnPlacementRegistry.register(EntityRegister.GATLINGPEA_ZOMBIE.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, SpawnChecker::canGroundInvasionZombieSpawn);
		EntitySpawnPlacementRegistry.register(EntityRegister.TALLNUT_ZOMBIE.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, SpawnChecker::canGroundInvasionZombieSpawn);
		EntitySpawnPlacementRegistry.register(EntityRegister.SQUASH_ZOMBIE.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, SpawnChecker::canGroundInvasionZombieSpawn);
		EntitySpawnPlacementRegistry.register(EntityRegister.JALAPENO_ZOMBIE.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, SpawnChecker::canGroundInvasionZombieSpawn);
		}
	
	/**
	 * {@link BiomeRegister#biomeModification(BiomeLoadingEvent)}
	 */
	public static void addEntitySpawnToBiome(BiomeLoadingEvent event) {
		Biome biome = ForgeRegistries.BIOMES.getValue(event.getName());
		if(biome == null || biome == BiomeRegister.ZEN_GARDEN.get()) {//prevent crash with other mod.
    		return ;
    	}
		RegistryKey<Biome> biomeKey = BiomeUtil.getKey(biome);
		if(BiomeUtil.isOverworld(biomeKey)) {
			if(BiomeUtil.isLand(biomeKey)) {
				addSpawnToOverworldLand(event);
				event.getSpawns().addSpawn(EntityClassification.AMBIENT, new Spawners(EntityRegister.SUN.get(), 2 * PVZConfig.COMMON_CONFIG.WorldSettings.EntitySpawnSettings.SunSpawnWeight.get(), 1, 1));
				event.getSpawns().addSpawn(EntityClassification.MONSTER, new Spawners(EntityRegister.GIGA_TOMB_STONE.get(), PVZConfig.COMMON_CONFIG.WorldSettings.EntitySpawnSettings.GigaTombStoneSpawnWeight.get(), 1, 1));
			}
			if(BiomeUtil.isDesert(biomeKey)) {
			}
			if(BiomeUtil.isOcean(biomeKey)) {
				addSpawnToOverworldOcean(event);
//				event.getSpawns().addSpawn(EntityClassification.CREATURE, new Spawners(EntityRegister.FOODIE_ZOMBIE.get(), PVZConfig.COMMON_CONFIG.WorldSettings.EntitySpawnSettings.FoodieZombieSpawnWeight.get(), 1, 1));
			}
			if(BiomeUtil.isPlain(biomeKey)) {
			}
			if(BiomeUtil.isConiferous(biomeKey)) {
			}
			addSpawnToOverworld(event);
		}
		if(BiomeUtil.isNether(biomeKey)) {
			event.getSpawns().addSpawn(EntityClassification.MONSTER, new Spawners(EntityRegister.LAVA_ZOMBIE.get(), PVZConfig.COMMON_CONFIG.WorldSettings.EntitySpawnSettings.LavaZombieSpawnWeight.get(), 1, 1));
		}
		if(BiomeUtil.isTheEnd(biomeKey)) {
		}
	}
	
	private static void addSpawnToOverworldLand(BiomeLoadingEvent event) {
		//common zombie spawn
		final int p = ConfigUtil.getSpawnMultiple();
		EntityClassification ee = EntityClassification.MONSTER;
		
		event.getSpawns().addSpawn(ee, new Spawners(EntityRegister.YETI_ZOMBIE.get(), 3 * PVZConfig.COMMON_CONFIG.WorldSettings.EntitySpawnSettings.YetiZombieSpawnWeight.get(), 1, 1));
		
		event.getSpawns().addSpawn(ee, new Spawners(EntityRegister.NORMAL_ZOMBIE.get(), 100 * p, 1, 2));
		event.getSpawns().addSpawn(ee, new Spawners(EntityRegister.CONEHEAD_ZOMBIE.get(), 50 * p, 1, 1));
		event.getSpawns().addSpawn(ee, new Spawners(EntityRegister.POLE_ZOMBIE.get(), 30 * p, 1, 1));
		event.getSpawns().addSpawn(ee, new Spawners(EntityRegister.BUCKETHEAD_ZOMBIE.get(), 15 * p, 1, 1));

		event.getSpawns().addSpawn(ee, new Spawners(EntityRegister.SNORKEL_ZOMBIE.get(), 70 * p, 1, 2));
		event.getSpawns().addSpawn(ee, new Spawners(EntityRegister.ZOMBONI.get(), 30 * p, 1, 1));
		event.getSpawns().addSpawn(ee, new Spawners(EntityRegister.BOBSLE_TEAM.get(), 25 * p, 1, 1));
		event.getSpawns().addSpawn(ee, new Spawners(EntityRegister.LAVA_ZOMBIE.get(), 8 * p, 1, 1));
		
		event.getSpawns().addSpawn(ee, new Spawners(EntityRegister.PUMPKIN_ZOMBIE.get(), 30 * p, 1, 1));
		event.getSpawns().addSpawn(ee, new Spawners(EntityRegister.TRICK_ZOMBIE.get(), 40 * p, 1, 2));

		event.getSpawns().addSpawn(ee, new Spawners(EntityRegister.NEWSPAPER_ZOMBIE.get(), 60 * p, 1, 1));
		event.getSpawns().addSpawn(ee, new Spawners(EntityRegister.OLD_ZOMBIE.get(), 35 * p, 1, 1));
		event.getSpawns().addSpawn(ee, new Spawners(EntityRegister.SUNDAY_EDITION_ZOMBIE.get(), 5 * p, 1, 1));
		
		event.getSpawns().addSpawn(ee, new Spawners(EntityRegister.SCREENDOOR_ZOMBIE.get(), 35 * p, 1, 1));
		event.getSpawns().addSpawn(ee, new Spawners(EntityRegister.FOOTBALL_ZOMBIE.get(), 20 * p, 1, 1));
		event.getSpawns().addSpawn(ee, new Spawners(EntityRegister.DANCING_ZOMBIE.get(), 15 * p, 1, 1));
		event.getSpawns().addSpawn(ee, new Spawners(EntityRegister.GIGA_FOOTBALL_ZOMBIE.get(), 5 * p, 1, 1));
		
		event.getSpawns().addSpawn(ee, new Spawners(EntityRegister.JACK_IN_BOX_ZOMBIE.get(), 25 * p, 1, 1));
		event.getSpawns().addSpawn(ee, new Spawners(EntityRegister.BALLOON_ZOMBIE.get(), 30 * p, 1, 1));
		event.getSpawns().addSpawn(ee, new Spawners(EntityRegister.DIGGER_ZOMBIE.get(), 20 * p, 1, 1));
		event.getSpawns().addSpawn(ee, new Spawners(EntityRegister.POGO_ZOMBIE.get(), 30 * p, 1, 1));
		
		event.getSpawns().addSpawn(ee, new Spawners(EntityRegister.BUNGEE_ZOMBIE.get(), 20 * p, 1, 1));
		event.getSpawns().addSpawn(ee, new Spawners(EntityRegister.LADDER_ZOMBIE.get(), 25 * p, 1, 1));
		event.getSpawns().addSpawn(ee, new Spawners(EntityRegister.CATAPULT_ZOMBIE.get(), 25 * p, 1, 1));
		event.getSpawns().addSpawn(ee, new Spawners(EntityRegister.GARGANTUAR.get(), 18 * p, 1, 1));
		event.getSpawns().addSpawn(ee, new Spawners(EntityRegister.GIGA_GARGANTUAR.get(), 5 * p, 1, 1));
		
		event.getSpawns().addSpawn(ee, new Spawners(EntityRegister.PEASHOOTER_ZOMBIE.get(), 60 * p, 1, 1));
		event.getSpawns().addSpawn(ee, new Spawners(EntityRegister.WALLNUT_ZOMBIE.get(), 30 * p, 1, 1));
		event.getSpawns().addSpawn(ee, new Spawners(EntityRegister.GATLINGPEA_ZOMBIE.get(), 15 * p, 1, 1));
		event.getSpawns().addSpawn(ee, new Spawners(EntityRegister.TALLNUT_ZOMBIE.get(), 20 * p, 1, 1));
		event.getSpawns().addSpawn(ee, new Spawners(EntityRegister.SQUASH_ZOMBIE.get(), 15 * p, 1, 1));
		event.getSpawns().addSpawn(ee, new Spawners(EntityRegister.JALAPENO_ZOMBIE.get(), 10 * p, 1, 1));
	}
	
	private static void addSpawnToOverworld(BiomeLoadingEvent event) {
		//common zombie spawn
		final int p = ConfigUtil.getSpawnMultiple();
		EntityClassification ee = EntityClassification.MONSTER;
				
		event.getSpawns().addSpawn(ee, new Spawners(EntityRegister.SNORKEL_ZOMBIE.get(), 70 * p, 1, 2));
		event.getSpawns().addSpawn(ee, new Spawners(EntityRegister.LAVA_ZOMBIE.get(), 8 * p, 1, 1));
	}
	
	private static void addSpawnToOverworldOcean(BiomeLoadingEvent event) {
		//common zombie spawn
		final int p = ConfigUtil.getSpawnMultiple();
		EntityClassification ee = EntityClassification.MONSTER;
		
		event.getSpawns().addSpawn(ee, new Spawners(EntityRegister.DOLPHIN_RIDER.get(), 15 * p, 1, 1));
	}

}
