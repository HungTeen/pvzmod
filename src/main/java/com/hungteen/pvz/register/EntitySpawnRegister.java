package com.hungteen.pvz.register;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Optional;

import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.entity.creature.FoodieZombieEntity;
import com.hungteen.pvz.entity.drop.SunEntity;
import com.hungteen.pvz.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.entity.zombie.grassnight.TombStoneEntity;
import com.hungteen.pvz.entity.zombie.poolnight.BalloonZombieEntity;
import com.hungteen.pvz.entity.zombie.poolnight.YetiZombieEntity;
import com.hungteen.pvz.utils.BiomeUtil;
import com.hungteen.pvz.utils.ZombieUtil;
import com.hungteen.pvz.utils.enums.Events;
import com.hungteen.pvz.utils.enums.Zombies;
import com.hungteen.pvz.world.data.WorldEventData;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntitySpawnPlacementRegistry.PlacementType;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid=PVZMod.MOD_ID,bus=Mod.EventBusSubscriber.Bus.MOD)
public class EntitySpawnRegister {

	public static final PlacementType IN_SKY = PlacementType.create("pvz_in_sky", (world, pos, type) -> {
		return world.canSeeSky(pos) && world.canBlockSeeSky(pos.add(0, -5, 0));
	});
	
	public static final PlacementType ON_SNOW = PlacementType.create("pvz_on_snow", (world, pos, type) -> {
		return world.getBlockState(pos).getBlock() == Blocks.SNOW || world.getBlockState(pos.down()).getBlock() == Blocks.SNOW_BLOCK;
	});
	
	public static final EnumMap<Zombies, SpawnData> ZOMBIE_SPAWNS = new EnumMap<>(Zombies.class);
	public static final EnumMap<Events, Integer> EVENT_CHANCE = new EnumMap<>(Events.class);
	public static final List<Zombies> ZOMBIE_SPAWN_LIST = new ArrayList<>();
	
	@SubscribeEvent
	public static void registerEntities(RegistryEvent.Register<EntityType<?>> evt) {
		EntitySpawnPlacementRegistry.register(EntityRegister.NORMAL_ZOMBIE.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, PVZZombieEntity::canZombieSpawn);
		EntitySpawnPlacementRegistry.register(EntityRegister.FLAG_ZOMBIE.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, PVZZombieEntity::canZombieSpawn);
		EntitySpawnPlacementRegistry.register(EntityRegister.CONEHEAD_ZOMBIE.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, PVZZombieEntity::canZombieSpawn);
		EntitySpawnPlacementRegistry.register(EntityRegister.POLE_ZOMBIE.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, PVZZombieEntity::canZombieSpawn);
		EntitySpawnPlacementRegistry.register(EntityRegister.BUCKETHEAD_ZOMBIE.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, PVZZombieEntity::canZombieSpawn);
		EntitySpawnPlacementRegistry.register(EntityRegister.SUN.get(), IN_SKY, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, SunEntity::canSunSpawn);
		EntitySpawnPlacementRegistry.register(EntityRegister.NEWSPAPER_ZOMBIE.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, PVZZombieEntity::canZombieSpawn);
		EntitySpawnPlacementRegistry.register(EntityRegister.SCREENDOOR_ZOMBIE.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, PVZZombieEntity::canZombieSpawn);
		EntitySpawnPlacementRegistry.register(EntityRegister.FOOTBALL_ZOMBIE.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, PVZZombieEntity::canZombieSpawn);
		EntitySpawnPlacementRegistry.register(EntityRegister.GIGA_FOOTBALL_ZOMBIE.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, PVZZombieEntity::canZombieSpawn);
		EntitySpawnPlacementRegistry.register(EntityRegister.DANCING_ZOMBIE.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, PVZZombieEntity::canZombieSpawn);
		EntitySpawnPlacementRegistry.register(EntityRegister.BACKUP_DANCER.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, PVZZombieEntity::canZombieSpawn);
		EntitySpawnPlacementRegistry.register(EntityRegister.OLD_ZOMBIE.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, PVZZombieEntity::canZombieSpawn);
		EntitySpawnPlacementRegistry.register(EntityRegister.SUNDAY_EDITION_ZOMBIE.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, PVZZombieEntity::canZombieSpawn);
		EntitySpawnPlacementRegistry.register(EntityRegister.SNORKEL_ZOMBIE.get(), PlacementType.NO_RESTRICTIONS, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, PVZZombieEntity::canZombieSpawn);
		EntitySpawnPlacementRegistry.register(EntityRegister.ZOMBONI.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, PVZZombieEntity::canZombieSpawn);
		EntitySpawnPlacementRegistry.register(EntityRegister.BOBSLE_TEAM.get(), ON_SNOW, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, PVZZombieEntity::canZombieSpawn);
		EntitySpawnPlacementRegistry.register(EntityRegister.ZOMBIE_DOLPHIN.get(), PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, FoodieZombieEntity::canSpawn);
		EntitySpawnPlacementRegistry.register(EntityRegister.DOLPHIN_RIDER.get(), PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, PVZZombieEntity::canZombieSpawn);
		EntitySpawnPlacementRegistry.register(EntityRegister.FOODIE_ZOMBIE.get(), PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, FoodieZombieEntity::canSpawn);
		EntitySpawnPlacementRegistry.register(EntityRegister.LAVA_ZOMBIE.get(), PlacementType.NO_RESTRICTIONS, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, PVZZombieEntity::canZombieSpawn);
		EntitySpawnPlacementRegistry.register(EntityRegister.CRAZY_DAVE.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MobEntity::canSpawnOn);
		EntitySpawnPlacementRegistry.register(EntityRegister.PUMPKIN_ZOMBIE.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, PVZZombieEntity::canZombieSpawn);
		EntitySpawnPlacementRegistry.register(EntityRegister.TRICK_ZOMBIE.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, PVZZombieEntity::canZombieSpawn);
		EntitySpawnPlacementRegistry.register(EntityRegister.JACK_IN_BOX_ZOMBIE.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, PVZZombieEntity::canZombieSpawn);
		EntitySpawnPlacementRegistry.register(EntityRegister.BALLOON_ZOMBIE.get(), IN_SKY, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, BalloonZombieEntity::canBalloonSpawn);
		EntitySpawnPlacementRegistry.register(EntityRegister.DIGGER_ZOMBIE.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, PVZZombieEntity::canZombieSpawn);
		EntitySpawnPlacementRegistry.register(EntityRegister.POGO_ZOMBIE.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, PVZZombieEntity::canZombieSpawn);
		EntitySpawnPlacementRegistry.register(EntityRegister.YETI_ZOMBIE.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, YetiZombieEntity::canYetiSpawn);
		EntitySpawnPlacementRegistry.register(EntityRegister.TOMB_STONE.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, TombStoneEntity::canTombSpawn);
		
	}
	
	public static void registerEntitySpawn() {
		EVENT_CHANCE.put(Events.BUCKET, PVZConfig.COMMON_CONFIG.WorldSettings.WorldEventSettings.EventChanceSettings.BucketAttackChance.get());
		EVENT_CHANCE.put(Events.WATER, PVZConfig.COMMON_CONFIG.WorldSettings.WorldEventSettings.EventChanceSettings.WaterAttackChance.get());
		EVENT_CHANCE.put(Events.HALLOWEEN, PVZConfig.COMMON_CONFIG.WorldSettings.WorldEventSettings.EventChanceSettings.HalloweenAttackChance.get());
		EVENT_CHANCE.put(Events.NEWSPAPER, PVZConfig.COMMON_CONFIG.WorldSettings.WorldEventSettings.EventChanceSettings.NewspaperAttackChance.get());
		EVENT_CHANCE.put(Events.FOOTBALL, PVZConfig.COMMON_CONFIG.WorldSettings.WorldEventSettings.EventChanceSettings.FootballAttackChance.get());
		EVENT_CHANCE.put(Events.RANDOM, PVZConfig.COMMON_CONFIG.WorldSettings.WorldEventSettings.EventChanceSettings.RandomAttackChance.get());
		
		putSpawnData(Zombies.NORMAL_ZOMBIE, 35, 1, 2, BiomeUtil.OVER_LAND);
		putSpawnData(Zombies.CONEHEAD_ZOMBIE, 15, 1, 1, BiomeUtil.OVER_LAND);
		putSpawnData(Zombies.POLE_ZOMBIE, 10, 1, 1, BiomeUtil.OVER_LAND);
		putSpawnData(Zombies.BUCKETHEAD_ZOMBIE, 2, 1, 1, BiomeUtil.OVER_LAND);

		putSpawnData(Zombies.SNORKEL_ZOMBIE, 30, 1, 2, BiomeUtil.OVER_LAND);
		putSpawnData(Zombies.ZOMBONI, 10, 1, 1, BiomeUtil.OVER_LAND);
		putSpawnData(Zombies.BOBSLE_TEAM, 20, 1, 1, BiomeUtil.OVER_LAND);
		putSpawnData(Zombies.LAVA_ZOMBIE, 2, 1, 1, BiomeUtil.OVER_LAND);
		
		putSpawnData(Zombies.PUMPKIN_ZOMBIE, 20, 1, 1, BiomeUtil.OVER_LAND);
		putSpawnData(Zombies.TRICK_ZOMBIE, 30, 1, 2, BiomeUtil.OVER_LAND);

		putSpawnData(Zombies.NEWSPAPER_ZOMBIE, 30, 1, 1, BiomeUtil.OVER_LAND);
		putSpawnData(Zombies.OLD_ZOMBIE, 15, 1, 1, BiomeUtil.OVER_LAND);
		putSpawnData(Zombies.SUNDAY_EDITION_ZOMBIE, 2, 1, 1, BiomeUtil.OVER_LAND);
		
		putSpawnData(Zombies.SCREENDOOR_ZOMBIE, 30, 1, 1, BiomeUtil.OVER_LAND);
		putSpawnData(Zombies.FOOTBALL_ZOMBIE, 12, 1, 1, BiomeUtil.OVER_LAND);
		putSpawnData(Zombies.DANCING_ZOMBIE, 2, 1, 1, BiomeUtil.OVER_LAND);
		putSpawnData(Zombies.GIGA_FOOTBALL_ZOMBIE, 2, 1, 1, BiomeUtil.OVER_LAND);
		
		putSpawnData(Zombies.JACK_IN_BOX_ZOMBIE, 15, 1, 1, BiomeUtil.OVER_LAND);
		putSpawnData(Zombies.BALLOON_ZOMBIE, 15, 1, 1, BiomeUtil.OVER_LAND);
		putSpawnData(Zombies.DIGGER_ZOMBIE, 6, 1, 1, BiomeUtil.OVER_LAND);
		putSpawnData(Zombies.POGO_ZOMBIE, 8, 1, 1, BiomeUtil.OVER_LAND);
	}
	
	public static List<ZombieSpawnEntry> getEventSpawnList(World world, Events ev){
		List<ZombieSpawnEntry> list = new ArrayList<>();
		if(ev.isZombieAttackEvent) {
			if(ev == Events.RANDOM) {
				for(Zombies zombie : getRandomEventSpawnList(world)) {
//					System.out.println(zombie);
					getZombieSpawnEnrty(world, zombie).ifPresent((entry)->{
			    		list.add(entry);
			    	});
				}
			} else {
			    for(Zombies zombie: ev.zombies) {
			    	getZombieSpawnEnrty(world, zombie).ifPresent((entry)->{
			    		list.add(entry);
			    	});
			    }
			}
		}
		return list;
	}
	
	public static List<Zombies> getRandomEventSpawnList(World world) {
		List<Zombies> zombies = new ArrayList<>();
		List<Integer> list = new ArrayList<>();
		int sum = 0;
		for(Zombies zombie : ZOMBIE_SPAWN_LIST) {
			sum += zombie.chooseWeight;
			list.add(sum);
		}
		for(int i = 0; i < 10; ++ i) {
			if(zombies.size() >= 4) break;
			int now = world.rand.nextInt(sum);
			Zombies current = null;
			for(int j = 0; j < list.size(); ++ j) {
				if(now < list.get(j)) {
					current = ZOMBIE_SPAWN_LIST.get(j);
					break;
				}
			}
			if(current != null && zombies.indexOf(current) == -1){
				zombies.add(current);
			}
		}
		return zombies;
	}
	
	public static Optional<ZombieSpawnEntry> getZombieSpawnEnrty(World world, Zombies zombie) {
		if(! ZOMBIE_SPAWNS.containsKey(zombie)) {
			System.out.println("No Zombie Spawn Data");
			return Optional.empty();
		}
		int safeDuration = PVZConfig.COMMON_CONFIG.WorldSettings.WorldEventSettings.SafeDayLength.get();
		int multiple = PVZConfig.COMMON_CONFIG.WorldSettings.WorldEventSettings.MaxSpawnWeightMultiple.get();
		int length = PVZConfig.COMMON_CONFIG.WorldSettings.WorldEventSettings.SpawnWeightIncDuration.get();
		long day = Math.max(0L, (world.getGameTime() - safeDuration) / 24000);//the number of current attack day.
		SpawnData now = ZOMBIE_SPAWNS.get(zombie);
		int min = now.weight;
		double percent = MathHelper.clamp(1d * day / length, 0, 1) * (multiple - 1) + 1;
		SpawnData newData = SpawnData.setNewWeight(now, MathHelper.floor(percent * min));
		return Optional.of(new ZombieSpawnEntry(zombie, newData));
	}
	
	public static Events getCurrentEventByRandom(World world) {
		int sum = 0;
		List<Integer> list = new ArrayList<>(Events.ATTACK_EVENTS.size());
		for(Events ev : Events.ATTACK_EVENTS) {//get all attack events
			if(EVENT_CHANCE.containsKey(ev)) {
				sum += EVENT_CHANCE.get(ev);
			}
			list.add(sum);
		}
		int current = world.rand.nextInt(sum);
		Events res = null;
		for(int i = 0; i < list.size(); ++ i) {
			if(current < list.get(i)) {
				res = Events.ATTACK_EVENTS.get(i);
				break;
			}
		}
		return res;
	}
	
	/**
	 * update world event spawn when restart the world.
	 */
	public static void addGameEventSpawns(World world){
		WorldEventData data = WorldEventData.getOverWorldEventData(world);
		for(Zombies zombie : Zombies.values()) {
			if(data.hasZombieSpawnEntry(zombie)) {
				getZombieSpawnEnrty(world, zombie).ifPresent((entry)->{
					entry.addWorldZombieSpawn(world);
				});
			}
		}
	}
	
	/**
	 * update world event spawn when restart the world.
	 */
	public static void removeGameEventSpawns(World world){
		WorldEventData data = WorldEventData.getOverWorldEventData(world);
		for(Zombies zombie : Zombies.values()) {
			if(data.hasZombieSpawnEntry(zombie)) {
				getZombieSpawnEnrty(world, zombie).ifPresent((entry)->{
					entry.removeWorldZombieSpawn(world);
				});
			}
		}
	}
	
	/**
	 * add spawn of zombies in event spawn list.
	 */
	public static void addEventSpawns(World world, Events event) {
		WorldEventData data = WorldEventData.getOverWorldEventData(world);
        for (ZombieSpawnEntry entry : getEventSpawnList(world, event)) {
        	Zombies zombie = entry.zombie;
        	if(! data.hasZombieSpawnEntry(zombie)) {
//        		System.out.println(zombie);
        		data.addZombieSpawnEntry(zombie);
        		entry.addWorldZombieSpawn(world);
        	}
        }
    }

	/**
	 * remove all spawn of zombies in world data.
	 */
    public static void removeEventSpawns(World world) {
    	WorldEventData data = WorldEventData.getOverWorldEventData(world);
    	for(Zombies zombie : Zombies.values()) {
    		if(data.hasZombieSpawnEntry(zombie)) {
    			data.removeZombieSpawnEntry(zombie);
    			getZombieSpawnEnrty(world, zombie).ifPresent((entry)->{
    				entry.removeWorldZombieSpawn(world);
    			});
    		}
        }
    }
    
	private static void putSpawnData(Zombies zombie, int weight, int minGroupSize, int maxGroupSize, Biome... biomes) {
		ZOMBIE_SPAWNS.put(zombie, new SpawnData(weight, minGroupSize, maxGroupSize, biomes));
		ZOMBIE_SPAWN_LIST.add(zombie);
	}
	
	public static class ZombieSpawnEntry {
		private final Zombies zombie;
		private final int weight;
		private final int minGroupSize;
		private final int maxGroupSize;
		private final Biome[] biomes;

		private ZombieSpawnEntry(Zombies zombie, SpawnData data) {
			this.zombie = zombie;
			this.weight = data.weight;
			this.minGroupSize = data.minGroupSize;
			this.maxGroupSize = data.maxGroupSize;
			this.biomes = data.biomes;
		}
		
		/**
		 * add world spawn to the biomes
		 */
		public void addWorldZombieSpawn(World world) {
			this.getZombieType().ifPresent((type) ->{
				EntityClassification classification = type.getClassification();
				for (Biome biome : biomes) {
				    List<SpawnListEntry> spawns = biome.getSpawns(classification);
				    if(! spawns.stream().anyMatch((entry) -> {
					    return entry.entityType == type;
				    })){
					    spawns.add(new SpawnListEntry(type, weight, this.minGroupSize, this.maxGroupSize));
				    }
			    }
			});
		}
		
		public void removeWorldZombieSpawn(World world) {
			this.getZombieType().ifPresent((type)->{
				EntityClassification classification = type.getClassification();
			    for (Biome biome : biomes) {
				    biome.getSpawns(classification).removeIf((entry) -> {
					    return entry.entityType == type;
				    });
			    }
			});
		}
		
		private Optional<EntityType<? extends PVZZombieEntity>> getZombieType(){
			return Optional.ofNullable(ZombieUtil.getZombieEntityType(zombie));
		}
	}
	
	public static class SpawnData {
		public final int weight;
		public final int minGroupSize;
		public final int maxGroupSize;
		public final Biome[] biomes;

		private SpawnData(int weight, int minGroupSize, int maxGroupSize, Biome... biomes) {
			this.weight = weight;
			this.minGroupSize = minGroupSize;
			this.maxGroupSize = maxGroupSize;
			this.biomes = biomes;
		}
		
		public static SpawnData setNewWeight(SpawnData now, int w) {
			return new SpawnData(w, now.minGroupSize, now.maxGroupSize, now.biomes);
		}
	}
	
}
