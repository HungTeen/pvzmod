package com.hungteen.pvzmod.registry;

import java.util.ArrayList;

import org.lwjgl.Sys;

import com.hungteen.pvzmod.entities.drops.EntitySun;
import com.hungteen.pvzmod.entities.zombies.grassday.EntityBucketHeadZombie;
import com.hungteen.pvzmod.entities.zombies.grassday.EntityConeHeadZombie;
import com.hungteen.pvzmod.entities.zombies.grassday.EntityFlagZombie;
import com.hungteen.pvzmod.entities.zombies.grassday.EntityNormalZombie;
import com.hungteen.pvzmod.entities.zombies.grassday.EntityPoleZombie;
import com.hungteen.pvzmod.entities.zombies.grassnight.EntityFootballZombie;
import com.hungteen.pvzmod.entities.zombies.grassnight.EntityGigaFootballZombie;
import com.hungteen.pvzmod.entities.zombies.grassnight.EntityOldZombie;
import com.hungteen.pvzmod.entities.zombies.grassnight.EntityPaperZombie;
import com.hungteen.pvzmod.entities.zombies.grassnight.EntityScreenDoorZombie;
import com.hungteen.pvzmod.entities.zombies.grassnight.EntityTombStone;
import com.hungteen.pvzmod.entities.zombies.plantzombies.EntityGatlingPeaZombie;
import com.hungteen.pvzmod.entities.zombies.plantzombies.EntityJalapenoZombie;
import com.hungteen.pvzmod.entities.zombies.plantzombies.EntityNutWallZombie;
import com.hungteen.pvzmod.entities.zombies.plantzombies.EntityPeaShooterZombie;
import com.hungteen.pvzmod.entities.zombies.plantzombies.EntitySquashZombie;
import com.hungteen.pvzmod.entities.zombies.plantzombies.EntityTallNutZombie;
import com.hungteen.pvzmod.entities.zombies.poolday.EntityBobsle;
import com.hungteen.pvzmod.entities.zombies.poolday.EntityLavaZombie;
import com.hungteen.pvzmod.entities.zombies.poolday.EntitySnorkelZombie;
import com.hungteen.pvzmod.entities.zombies.poolday.EntityZomboni;
import com.hungteen.pvzmod.entities.zombies.poolnight.EntityDiggerZombie;
import com.hungteen.pvzmod.entities.zombies.poolnight.EntityJackInBoxZombie;
import com.hungteen.pvzmod.entities.zombies.poolnight.EntityPogoZombie;
import com.hungteen.pvzmod.entities.zombies.poolnight.EntityYetiZombie;
import com.hungteen.pvzmod.entities.zombies.roof.EntityCataPultZombie;
import com.hungteen.pvzmod.entities.zombies.roof.EntityGargantuar;
import com.hungteen.pvzmod.entities.zombies.roof.EntitySadGargantuar;
import com.hungteen.pvzmod.entities.zombies.special.EntityBalloon;
import com.hungteen.pvzmod.entities.zombies.special.EntityDolphin;
import com.hungteen.pvzmod.util.BiomeUtil;
import com.hungteen.pvzmod.util.enums.SpecialEvents;
import com.hungteen.pvzmod.world.data.OverworldData;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class EntitySpawnRegister {

	private static final ArrayList<SpawnEntry> commonSpawns = new ArrayList<SpawnEntry>();
	private static final ArrayList<SpawnEntry> dayTimeSpawns = new ArrayList<SpawnEntry>();
	private static final ArrayList<SpawnEntry> nightTimeSpawns = new ArrayList<SpawnEntry>();
	private static final ArrayList<SpawnEntry> plantZombieEventSpawns = new ArrayList<SpawnEntry>();
	
	
	public static void registerEntitySpawn()
	{
		//ÆÕÍ¨½©Ê¬
		commonSpawns.add(new SpawnEntry(EntityNormalZombie.class, 80, 1, 3, EnumCreatureType.MONSTER, BiomeUtil.overworldLand));
		commonSpawns.add(new SpawnEntry(EntityFlagZombie.class, 5, 1, 3, EnumCreatureType.MONSTER, BiomeUtil.overworldLand));
		commonSpawns.add(new SpawnEntry(EntityConeHeadZombie.class, 30, 1, 2, EnumCreatureType.MONSTER, BiomeUtil.overworldLand));
		commonSpawns.add(new SpawnEntry(EntityBucketHeadZombie.class, 10, 1, 1, EnumCreatureType.MONSTER, BiomeUtil.overworldLand));
		commonSpawns.add(new SpawnEntry(EntitySnorkelZombie.class, 50, 1, 1, EnumCreatureType.MONSTER, BiomeUtil.sea));
		commonSpawns.add(new SpawnEntry(EntityJackInBoxZombie.class, 15, 1, 1, EnumCreatureType.MONSTER, BiomeUtil.overworldLand));

		//°×Ìì½©Ê¬
		dayTimeSpawns.add(new SpawnEntry(EntityPoleZombie.class, 20, 1, 2, EnumCreatureType.MONSTER, BiomeUtil.overworldLand));
		dayTimeSpawns.add(new SpawnEntry(EntityBobsle.class, 10, 1, 1, EnumCreatureType.MONSTER, BiomeUtil.snowLand));
		dayTimeSpawns.add(new SpawnEntry(EntityZomboni.class, 40, 1, 1, EnumCreatureType.MONSTER, BiomeUtil.snowLand));
		dayTimeSpawns.add(new SpawnEntry(EntityCataPultZombie.class, 20, 1, 2, EnumCreatureType.MONSTER, BiomeUtil.overworldLand));
		dayTimeSpawns.add(new SpawnEntry(EntityGargantuar.class, 5, 1, 1, EnumCreatureType.MONSTER, BiomeUtil.desert));
		dayTimeSpawns.add(new SpawnEntry(EntitySadGargantuar.class, 1, 1, 1, EnumCreatureType.MONSTER, BiomeUtil.mesa));
		dayTimeSpawns.add(new SpawnEntry(EntityDolphin.class, 20, 1, 1, EnumCreatureType.MONSTER, BiomeUtil.sea));
		dayTimeSpawns.add(new SpawnEntry(EntitySun.class, 100, 1, 1, EnumCreatureType.CREATURE, BiomeUtil.overworldLand));
		
		//Ò¹Íí½©Ê¬
		nightTimeSpawns.add(new SpawnEntry(EntityPaperZombie.class, 50, 1, 1, EnumCreatureType.MONSTER, BiomeUtil.overworldLand));
		nightTimeSpawns.add(new SpawnEntry(EntityOldZombie.class, 5, 1, 1, EnumCreatureType.MONSTER, BiomeUtil.overworldLand));
		nightTimeSpawns.add(new SpawnEntry(EntityTombStone.class, 5, 1, 2, EnumCreatureType.MONSTER, BiomeUtil.overworldLand));
		nightTimeSpawns.add(new SpawnEntry(EntityScreenDoorZombie.class, 10, 1, 1, EnumCreatureType.MONSTER, BiomeUtil.overworldLand));
		nightTimeSpawns.add(new SpawnEntry(EntityDiggerZombie.class, 15, 1, 1, EnumCreatureType.MONSTER, BiomeUtil.mountain));
		nightTimeSpawns.add(new SpawnEntry(EntityPogoZombie.class, 20, 1, 2, EnumCreatureType.MONSTER, BiomeUtil.mountain));
		nightTimeSpawns.add(new SpawnEntry(EntityBalloon.class, 20, 1, 2, EnumCreatureType.MONSTER, BiomeUtil.sky));
		nightTimeSpawns.add(new SpawnEntry(EntityYetiZombie.class, 1, 1, 1, EnumCreatureType.MONSTER, BiomeUtil.snowLand));
		nightTimeSpawns.add(new SpawnEntry(EntityFootballZombie.class, 8, 1, 1, EnumCreatureType.MONSTER, BiomeUtil.overworldLand));
		nightTimeSpawns.add(new SpawnEntry(EntityGigaFootballZombie.class, 1, 1, 1, EnumCreatureType.MONSTER, BiomeUtil.swampland));
		
		//Ö²Îï½©Ê¬
		plantZombieEventSpawns.add(new SpawnEntry(EntityJalapenoZombie.class, 5, 1, 1, EnumCreatureType.MONSTER, BiomeUtil.overworldLand));
		plantZombieEventSpawns.add(new SpawnEntry(EntityPeaShooterZombie.class, 50, 1, 2, EnumCreatureType.MONSTER, BiomeUtil.overworldLand));
		plantZombieEventSpawns.add(new SpawnEntry(EntityNutWallZombie.class, 20, 1, 1, EnumCreatureType.MONSTER, BiomeUtil.overworldLand));
		plantZombieEventSpawns.add(new SpawnEntry(EntityGatlingPeaZombie.class, 20, 1, 1, EnumCreatureType.MONSTER, BiomeUtil.overworldLand));
		plantZombieEventSpawns.add(new SpawnEntry(EntityTallNutZombie.class, 10, 1, 1, EnumCreatureType.MONSTER, BiomeUtil.overworldLand));
		plantZombieEventSpawns.add(new SpawnEntry(EntitySquashZombie.class, 10, 1, 1, EnumCreatureType.MONSTER, BiomeUtil.overworldLand));
		
		//µØÓü
		EntityRegistry.addSpawn(EntityLavaZombie.class, 40, 1, 1, EnumCreatureType.MONSTER, BiomeUtil.hell);
	}
	
	public static void updateEntitySpawn(World world)
	{
		if(world.isRemote) return ;
		OverworldData data = OverworldData.getGlobalData(world);
		for(SpecialEvents event:SpecialEvents.values()) {
			System.out.println(event);
			if(data.hasEvent(event)) {
				System.out.println("add");
				addEventSpawns(event);
			}
		}
	}
	
	public static ArrayList<SpawnEntry> getSpawnList(SpecialEvents event)
	{
		switch (event) {
        case NORMAL_ZOMBIE:return commonSpawns;
        case DAY_ZOMBIE:return dayTimeSpawns;
        case NIGHT_ZOMBIE:return nightTimeSpawns;
        case PLANT_ZOMBIE:return plantZombieEventSpawns;
		default: return null;
        }
	}
	
	public static void addEventSpawns(SpecialEvents event) {
        ArrayList<SpawnEntry> spawnList = getSpawnList(event);
        if(spawnList==null) return ;
        for (SpawnEntry entry : spawnList) {
            EntityRegistry.addSpawn(entry.entityClass, entry.weight, entry.minGroupSize, entry.maxGroupSize, entry.creatureType, entry.biomes);
        }
    }

    public static void removeEventSpawns(SpecialEvents event) {
    	ArrayList<SpawnEntry> spawnList = getSpawnList(event);
    	if(spawnList==null) return ;
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
