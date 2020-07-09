package com.hungteen.pvzmod.registry;

import java.util.ArrayList;

import com.hungteen.pvzmod.entities.drops.EntitySun;
import com.hungteen.pvzmod.entities.zombies.grassday.EntityBucketHeadZombie;
import com.hungteen.pvzmod.entities.zombies.grassday.EntityConeHeadZombie;
import com.hungteen.pvzmod.entities.zombies.grassday.EntityNormalZombie;
import com.hungteen.pvzmod.entities.zombies.plantzombies.EntityJalapenoZombie;
import com.hungteen.pvzmod.entities.zombies.plantzombies.EntityNutWallZombie;
import com.hungteen.pvzmod.entities.zombies.plantzombies.EntityPeaShooterZombie;
import com.hungteen.pvzmod.entities.zombies.poolday.EntityBobsle;
import com.hungteen.pvzmod.entities.zombies.poolday.EntityLavaZombie;
import com.hungteen.pvzmod.entities.zombies.poolday.EntitySnorkelZombie;
import com.hungteen.pvzmod.entities.zombies.poolday.EntityZomboni;
import com.hungteen.pvzmod.util.BiomeUtil;
import com.hungteen.pvzmod.util.enums.SpecialEvents;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class EntitySpawnRegister {

	private static final ArrayList<SpawnEntry> plantZombieDaySpawns = new ArrayList<SpawnEntry>();
	
	public static void registerEntitySpawn()
	{
		plantZombieDaySpawns.add(new SpawnEntry(EntityJalapenoZombie.class, 20, 1, 1, EnumCreatureType.MONSTER, BiomeUtil.overworldLand));
		plantZombieDaySpawns.add(new SpawnEntry(EntityPeaShooterZombie.class, 80, 1, 2, EnumCreatureType.MONSTER, BiomeUtil.overworldLand));
		plantZombieDaySpawns.add(new SpawnEntry(EntityNutWallZombie.class, 50, 1, 1, EnumCreatureType.MONSTER, BiomeUtil.overworldLand));
		
		//zombie
		EntityRegistry.addSpawn(EntityNormalZombie.class, 100, 2, 3, EnumCreatureType.MONSTER, BiomeUtil.overworldLand);
		EntityRegistry.addSpawn(EntityConeHeadZombie.class, 30, 1, 2, EnumCreatureType.MONSTER, BiomeUtil.overworldLand);
		EntityRegistry.addSpawn(EntityBucketHeadZombie.class, 10, 1, 1, EnumCreatureType.MONSTER, BiomeUtil.overworldLand);
		
		EntityRegistry.addSpawn(EntityBobsle.class, 20, 1, 1, EnumCreatureType.MONSTER, BiomeUtil.snowLand);
		EntityRegistry.addSpawn(EntityZomboni.class, 40, 1, 2, EnumCreatureType.MONSTER, BiomeUtil.snowLand);
		
		EntityRegistry.addSpawn(EntitySnorkelZombie.class, 50, 1, 1, EnumCreatureType.MONSTER, BiomeUtil.sea);
		
		EntityRegistry.addSpawn(EntityLavaZombie.class, 40, 1, 1, EnumCreatureType.MONSTER, BiomeUtil.hell);
		
		//others
		EntityRegistry.addSpawn(EntitySun.class, 100, 1, 1, EnumCreatureType.CREATURE, BiomeUtil.overworldLand);
	}
	
	public static void addEventSpawns(SpecialEvents event) {
        ArrayList<SpawnEntry> spawnList = null;

        switch (event) {
            case PLANTZOMBIE_DAY:
                spawnList = plantZombieDaySpawns;
                break;
		default:
			break;
        }
        
        for (SpawnEntry entry : spawnList) {
            EntityRegistry.addSpawn(entry.entityClass, entry.weight, entry.minGroupSize, entry.maxGroupSize, entry.creatureType, entry.biomes);
        }
    }

    public static void removeEventSpawns(SpecialEvents event) {
        ArrayList<SpawnEntry> spawnList = null;

        switch (event) {
            case PLANTZOMBIE_DAY:
                spawnList = plantZombieDaySpawns;
                break;
		default:
			break;
        }

        for (SpawnEntry entry : spawnList) {
            EntityRegistry.removeSpawn(entry.entityClass, entry.creatureType, entry.biomes);
        }
    }
    
    private static class SpawnEntry {
    	private final Class<? extends EntityLiving> entityClass;
    	private final int weight;
    	private final int minGroupSize;
    	private final int maxGroupSize;
    	private final EnumCreatureType creatureType;
    	private final Biome[] biomes;

    	private SpawnEntry(Class<? extends EntityLiving> entityClass, int weight, int minGroupSize, int maxGroupSize, EnumCreatureType creatureType, Biome... biomes) {
    	    this.entityClass = entityClass;
    	    this.weight = weight;
    	    this.minGroupSize = minGroupSize;
    	    this.maxGroupSize = maxGroupSize;
    	    this.creatureType = creatureType;
    	    this.biomes = biomes;
        }
	}
}
