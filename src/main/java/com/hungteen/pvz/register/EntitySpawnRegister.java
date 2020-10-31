package com.hungteen.pvz.register;

import java.util.ArrayList;
import java.util.List;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.entity.creature.FoodieZombieEntity;
import com.hungteen.pvz.entity.drop.SunEntity;
import com.hungteen.pvz.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.utils.BiomeUtil;
import com.hungteen.pvz.utils.enums.Events;
import com.hungteen.pvz.world.data.WorldEventData;

import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntitySpawnPlacementRegistry.PlacementType;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid=PVZMod.MOD_ID,bus=Mod.EventBusSubscriber.Bus.MOD)
public class EntitySpawnRegister {

	public static final PlacementType IN_SKY = PlacementType.create("pvz_in_sky", (world, pos, type)->{
		return world.canSeeSky(pos)&&world.canBlockSeeSky(pos.add(0, -5, 0));
	});
	
	public static final PlacementType ON_SNOW = PlacementType.create("pvz_on_snow", (world, pos, type)->{
		return world.getBlockState(pos).getBlock()==Blocks.SNOW||world.getBlockState(pos.down()).getBlock()==Blocks.SNOW_BLOCK;
	});
	
	public static final List<SpawnEntry> BUCKET_SPAWN = new ArrayList<>();
	public static final List<SpawnEntry> WATER_SPAWN = new ArrayList<>();
	
	@SubscribeEvent
	public static void registerEntities(RegistryEvent.Register<EntityType<?>> evt) {
		EntitySpawnPlacementRegistry.register(EntityRegister.NORMAL_ZOMBIE.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, PVZZombieEntity::canZombieSpawn);
		EntitySpawnPlacementRegistry.register(EntityRegister.FLAG_ZOMBIE.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, PVZZombieEntity::canZombieSpawn);
		EntitySpawnPlacementRegistry.register(EntityRegister.CONEHEAD_ZOMBIE.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, PVZZombieEntity::canZombieSpawn);
		EntitySpawnPlacementRegistry.register(EntityRegister.POLE_ZOMBIE.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, PVZZombieEntity::canZombieSpawn);
		EntitySpawnPlacementRegistry.register(EntityRegister.BUCKETHEAD_ZOMBIE.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, PVZZombieEntity::canZombieSpawn);
		EntitySpawnPlacementRegistry.register(EntityRegister.SUN.get(), IN_SKY, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, SunEntity::canSunSpawn);
		EntitySpawnPlacementRegistry.register(EntityRegister.SNORKEL_ZOMBIE.get(), PlacementType.NO_RESTRICTIONS, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, PVZZombieEntity::canZombieSpawn);
		EntitySpawnPlacementRegistry.register(EntityRegister.ZOMBONI.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, PVZZombieEntity::canZombieSpawn);
		EntitySpawnPlacementRegistry.register(EntityRegister.BOBSLE_TEAM.get(), ON_SNOW, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, PVZZombieEntity::canZombieSpawn);
		EntitySpawnPlacementRegistry.register(EntityRegister.ZOMBIE_DOLPHIN.get(), PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, FoodieZombieEntity::canSpawn);
		EntitySpawnPlacementRegistry.register(EntityRegister.DOLPHIN_RIDER.get(), PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, PVZZombieEntity::canZombieSpawn);
		EntitySpawnPlacementRegistry.register(EntityRegister.FOODIE_ZOMBIE.get(), PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, FoodieZombieEntity::canSpawn);
		EntitySpawnPlacementRegistry.register(EntityRegister.LAVA_ZOMBIE.get(), PlacementType.NO_RESTRICTIONS, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, PVZZombieEntity::canZombieSpawn);
		EntitySpawnPlacementRegistry.register(EntityRegister.CRAZY_DAVE.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MobEntity::canSpawnOn);
	}
	
	public static void registerEntitySpawn() {
		BUCKET_SPAWN.add(new SpawnEntry(EntityRegister.NORMAL_ZOMBIE.get(), 60, 1, 3, BiomeUtil.OVER_LAND));
		BUCKET_SPAWN.add(new SpawnEntry(EntityRegister.CONEHEAD_ZOMBIE.get(), 20, 1, 2, BiomeUtil.OVER_LAND));
		BUCKET_SPAWN.add(new SpawnEntry(EntityRegister.POLE_ZOMBIE.get(), 20, 1, 1, BiomeUtil.OVER_LAND));
		BUCKET_SPAWN.add(new SpawnEntry(EntityRegister.BUCKETHEAD_ZOMBIE.get(), 4, 1, 1, BiomeUtil.OVER_LAND));
		addFlagZombie(BUCKET_SPAWN);
		WATER_SPAWN.add(new SpawnEntry(EntityRegister.SNORKEL_ZOMBIE.get(), 60, 1, 2, BiomeUtil.OVER_LAND));
		WATER_SPAWN.add(new SpawnEntry(EntityRegister.ZOMBONI.get(), 20, 1, 1, BiomeUtil.OVER_LAND));
		WATER_SPAWN.add(new SpawnEntry(EntityRegister.BOBSLE_TEAM.get(), 10, 1, 1, BiomeUtil.OVER_LAND));
		WATER_SPAWN.add(new SpawnEntry(EntityRegister.DOLPHIN_RIDER.get(), 3, 1, 1, BiomeUtil.OCEAN));
		WATER_SPAWN.add(new SpawnEntry(EntityRegister.LAVA_ZOMBIE.get(), 2, 1, 1, BiomeUtil.OVER_LAND));
		addFlagZombie(WATER_SPAWN);
	}
	
	public static List<SpawnEntry> getEventSpawnList(Events ev){
		switch(ev) {
		case BUCKET:return BUCKET_SPAWN;
		case WATER:return WATER_SPAWN;
		default:{
			PVZMod.LOGGER.debug("No Event Spawn Error!");
			return null;
		}
		}
	}
	
	public static void updateEventSpawns(World world){
		WorldEventData data = WorldEventData.getOverWorldEventData(world);
//		System.out.println("update data");
		for(Events event:Events.values()) {
			if(data.hasEvent(event)) {
//				System.out.println(event);
				addEventSpawns(event);
			}
		}
	}
	
	public static void removeEventSpawns(World world) {
		WorldEventData data = WorldEventData.getOverWorldEventData(world);
		for(Events event:Events.values()) {
			if(data.hasEvent(event)) {
				removeEventSpawns(event);
			}
		}
	}
	
	public static void addEventSpawns(Events event) {
        List<SpawnEntry> spawnList = getEventSpawnList(event);
        if(spawnList==null) {
        	return ;
        }
        for (SpawnEntry entry : spawnList) {
            BiomeUtil.addSpawn(entry.entityType, entry.weight, entry.minGroupSize, entry.maxGroupSize, entry.biomes);
        }
    }

    public static void removeEventSpawns(Events event) {
    	List<SpawnEntry> spawnList = getEventSpawnList(event);
    	if(spawnList==null) {
    		return ;
    	}
        for (SpawnEntry entry : spawnList) {
            BiomeUtil.removeSpawn(entry.entityType, entry.biomes);
        }
    }
    
    public static void addFlagZombie(List<SpawnEntry> list) {
    	list.add(new SpawnEntry(EntityRegister.FLAG_ZOMBIE.get(), 2, 1, 1, BiomeUtil.OVER_LAND));
    }
    
	private static class SpawnEntry {
		private final EntityType<? extends Entity> entityType;
		private final int weight;
		private final int minGroupSize;
		private final int maxGroupSize;
		private final Biome[] biomes;

		private SpawnEntry(EntityType<? extends Entity> type, int weight, int minGroupSize, int maxGroupSize,
				Biome... biomes) {
			this.entityType = type;
			this.weight = weight;
			this.minGroupSize = minGroupSize;
			this.maxGroupSize = maxGroupSize;
			this.biomes = biomes;
		}
	}
	
}
