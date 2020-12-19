package com.hungteen.pvz.world;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import com.hungteen.pvz.capability.CapabilityHandler;
import com.hungteen.pvz.capability.player.PlayerDataManager;
import com.hungteen.pvz.capability.player.PlayerDataManager.OtherStats;
import com.hungteen.pvz.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.register.EntityRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.ZombieUtil;
import com.hungteen.pvz.utils.enums.Ranks;
import com.hungteen.pvz.utils.enums.Resources;
import com.hungteen.pvz.utils.enums.Zombies;
import com.hungteen.pvz.world.data.WorldEventData;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.Heightmap.Type;

public class WaveManager {

	private static final ITextComponent HUGE_WAVE = new TranslationTextComponent("event.pvz.huge_wave").applyTextStyle(TextFormatting.DARK_RED);
	public static final int MAX_WAVE_NUM = 5;
	private final World world;
	private final int currentWave;
	private final PlayerEntity player;
	private final BlockPos center;
	private final List<Zombies> spawns = new ArrayList<>(); 
	private final int[] minSpawnCounts = new int[] {8, 12, 16, 21, 28};
	private final int[] maxSpawnCounts = new int[] {15, 20, 25, 32, 40};
	public int spawnCnt = 0;
	
	public WaveManager(PlayerEntity player, int waveNum) {
		this.world = player.world;
		this.currentWave = waveNum;
		this.player = player;
		this.center = player.getPosition();
		this.updateSpawns();
	}
	
	/**
	 * check spawn huge wave of zombies for players.
	 */
	public static void tickWave(World world, int dayTime) {
		if(world.getDifficulty() == Difficulty.PEACEFUL) return ;
		world.getPlayers().forEach((player)->{
			if(PlayerUtil.isPlayerSurvival(player)) {
				player.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent((l)->{
			        PlayerDataManager.OtherStats stats = l.getPlayerData().getOtherStats();
			        for(int i = 0 ; i < stats.zombieWaveTime.length; ++ i) {
				        int time = stats.zombieWaveTime[i];
				        if(time != 0 && time >= dayTime) {
					        if(time == dayTime) {
						        WaveManager manager = new WaveManager(player, i);
						        manager.spawnWaveZombies();
						        stats.zombieWaveTime[i] = 0;
					        }
					        break;
				        }
			        }
		        });
			}
		});
	}
	
	public void spawnWaveZombies() {
		if(world.getDimension().getType() != DimensionType.OVERWORLD) return ;
		BlockPos mid = this.findRandomSpawnPos(20);
		if(mid == null) {// no position do not spawn.
			return ;
		}
		PlayerUtil.playClientSound(player, 2);
		PlayerUtil.sendSubTitleToPlayer(player, HUGE_WAVE);
//		System.out.println(mid);
		int cnt = this.getSpawnCount();
		List<Integer> spawnChances = new ArrayList<>();
		int now = 0;
		for(int i = 0; i < this.spawns.size(); ++ i) {
			Zombies zombie = this.spawns.get(i);
			Ranks rank = ZombieUtil.getZombieRank(zombie);
			now += (Ranks.values().length - rank.ordinal()) * (Ranks.values().length - rank.ordinal());
			spawnChances.add(now);
		}
		for(int i = 0; i < cnt; ++ i) {
			int tmp = this.world.rand.nextInt(now);
			for(int j = 0; j < spawnChances.size(); ++ j) {
				if(tmp < spawnChances.get(j)) {
					this.spawnZombie(this.spawns.get(j), mid);
					break;
				}
			}
		}
		PVZZombieEntity flagZombie = EntityRegister.FLAG_ZOMBIE.get().create(world);
		EntityUtil.onMobEntitySpawn(world, flagZombie, mid.add(0, 1, 0));
	}
	
	/**
	 * reset the huge wave time of each player.
	 */
	public static void resetPlayerWaveTime(PlayerEntity player) {
		player.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent((l)->{
			OtherStats stats = l.getPlayerData().getOtherStats();
			int treeLvl = l.getPlayerData().getPlayerStats().getPlayerStats(Resources.TREE_LVL);
			int maxCnt = PlayerUtil.getPlayerWaveCount(treeLvl);
			int minCnt = (maxCnt + 1) / 2;
			int cnt = player.getRNG().nextInt(maxCnt - minCnt + 1) + minCnt;
			int partTime = 24000 / cnt;
			for(int i = 0; i < MAX_WAVE_NUM; ++ i) {
				if(i < cnt) {
					int time = player.getRNG().nextInt(partTime - 500);
					stats.zombieWaveTime[i] = time + i * partTime + 250;
				} else {
					stats.zombieWaveTime[i] = 0;
				}
			}
//			for(int i : stats.zombieWaveTime) {
//				System.out.println(i);
//			}
		});
	}
	
	private void spawnZombie(Zombies zombie, BlockPos pos) {
		int range = 11;
		int x = pos.getX() + this.world.rand.nextInt(range) - range / 2;
		int z = pos.getZ() + this.world.rand.nextInt(range) - range / 2;
		int y = this.world.getHeight(Type.WORLD_SURFACE, x, z);
		PVZZombieEntity zombieEntity = ZombieUtil.getZombieEntity(world, zombie);
		EntityUtil.onMobEntitySpawn(world, zombieEntity, new BlockPos(x, y + 1, z));
	}
	
	private int getSpawnCount() {
		if(this.spawnCnt != 0) return this.spawnCnt;
		int minCnt = this.minSpawnCounts[this.currentWave];
		int maxCnt = this.maxSpawnCounts[this.currentWave];
		return this.world.rand.nextInt(maxCnt - minCnt + 1) + minCnt;
	}
	
	private void updateSpawns() {
		WorldEventData data = WorldEventData.getOverWorldEventData(world);
		for(Zombies zombie : Zombies.values()) {
			if(data.hasZombieSpawnEntry(zombie)) {
				this.spawns.add(zombie);
			}
		}
	}

	@SuppressWarnings("deprecation")
	@Nullable
	private BlockPos findRandomSpawnPos(int chance) {
		int range = 16, distance = 48;
		for (int i = 0; i < chance; ++i) {
			float f = this.world.rand.nextFloat() * ((float) Math.PI * 2F);
			int dx = MathHelper.floor(MathHelper.cos(f) * distance);
			int dz = MathHelper.floor(MathHelper.sin(f) * distance);
			int x = this.center.getX() + (dx > 0 ? dx + range : dx - range);
			int z = this.center.getZ() + (dz > 0 ? dz + range : dz - range);
			int y = this.world.getHeight(Heightmap.Type.WORLD_SURFACE, x, z);
			BlockPos pos = new BlockPos(x, y, z);
			if (this.world.isAreaLoaded(pos.add(-range, -range, -range), pos.add(range, range, range))
					&& this.world.getChunkProvider().isChunkLoaded(new ChunkPos(pos))) {
				return pos;
			}
		}
		return null;
	}

}