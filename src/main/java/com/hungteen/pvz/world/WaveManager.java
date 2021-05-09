package com.hungteen.pvz.world;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import com.hungteen.pvz.capability.CapabilityHandler;
import com.hungteen.pvz.capability.player.PlayerDataManager;
import com.hungteen.pvz.capability.player.PlayerDataManager.OtherStats;
import com.hungteen.pvz.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.entity.zombie.grassnight.TombStoneEntity;
import com.hungteen.pvz.entity.zombie.roof.BungeeZombieEntity;
import com.hungteen.pvz.entity.zombie.roof.BungeeZombieEntity.BungeeTypes;
import com.hungteen.pvz.network.OtherStatsPacket;
import com.hungteen.pvz.network.PVZPacketHandler;
import com.hungteen.pvz.register.EntityRegister;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.ZombieUtil;
import com.hungteen.pvz.utils.enums.Events;
import com.hungteen.pvz.utils.enums.Resources;
import com.hungteen.pvz.utils.enums.Zombies;
import com.hungteen.pvz.world.data.WorldEventData;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.Difficulty;
import net.minecraft.world.LightType;
import net.minecraft.world.World;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.Heightmap.Type;
import net.minecraftforge.fml.network.PacketDistributor;

public class WaveManager {

	private static final ITextComponent HUGE_WAVE = new TranslationTextComponent("event.pvz.huge_wave").withStyle(TextFormatting.DARK_RED);
	public static final int MAX_WAVE_NUM = 5;
	public static final int FINISH_OFFSET = 30000;
	private final World world;
	private final int currentWave;
	private final PlayerEntity player;
	private final BlockPos center;
	private final List<Zombies> spawns = new ArrayList<>(); 
	private final List<Integer> spawnWeights = new ArrayList<>();
	private final int[] minSpawnCounts = new int[] {8, 12, 16, 21, 28};
	private final int[] maxSpawnCounts = new int[] {15, 20, 25, 32, 40};
	public int spawnCnt = 0;
	
	public WaveManager(PlayerEntity player, int waveNum) {
		this.world = player.level;
		this.currentWave = waveNum;
		this.player = player;
		this.center = player.blockPosition();
		this.updateSpawns();
	}
	
	/**
	 * check spawn huge wave of zombies for players.
	 */
	public static void tickWave(World world, int dayTime) {
		if(world.getDifficulty() == Difficulty.PEACEFUL) return ;
		world.players().forEach((player) -> {
			if(PlayerUtil.isPlayerSurvival(player)) {
				player.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent((l) -> {
			        PlayerDataManager.OtherStats stats = l.getPlayerData().getOtherStats();
			        for(int i = 0 ; i < stats.zombieWaveTime.length; ++ i) {
				        int time = stats.zombieWaveTime[i];
				        if(time != 0 && time == dayTime) {
						    WaveManager manager = new WaveManager(player, i);
						    manager.spawnWaveZombies();
						    stats.zombieWaveTime[i] += FINISH_OFFSET;
						    syncWaveTime(player);
					        break;
				        }
			        }
		        });
			}
		});
	}
	
	public static void syncWaveTime(PlayerEntity player) {
		player.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent((l) -> {
			PlayerDataManager.OtherStats stats = l.getPlayerData().getOtherStats();
		    for(int i = 0; i < MAX_WAVE_NUM; ++ i) {
			    PVZPacketHandler.CHANNEL.send(PacketDistributor.PLAYER.with(() -> {
				    return (ServerPlayerEntity) player;
			    }), new OtherStatsPacket(1, i, stats.zombieWaveTime[i]));
		    }
		    PVZPacketHandler.CHANNEL.send(PacketDistributor.PLAYER.with(() -> {
			    return (ServerPlayerEntity) player;
		    }), new OtherStatsPacket(1, - 1, stats.totalWaveCount));
		});
	}
	
	public void activateTombStone() {
		if(! world.dimension().equals(World.OVERWORLD)) return ;
		int len = 30;
		world.getEntitiesOfClass(TombStoneEntity.class, EntityUtil.getEntityAABB(player, len, len), (tombstone) -> {
			return true;
		}).forEach((tomb) -> {
			tomb.summonZombie();
		});
	}
	
	public void spawnWaveZombies() {
		if(! world.dimension().equals(World.OVERWORLD)) return ;
		int now = 0;
		for(int i = 0; i < this.spawns.size(); ++ i) {
			Zombies zombie = this.spawns.get(i);
			now += zombie.spawnWeight;
			this.spawnWeights.add(now);
		}
		if(now == 0) return;
		int cnt = this.getSpawnCount();
		boolean spawned = false;
		int groupNum = 0;
		while(cnt >= 10) {
			int teamCnt = (cnt < 15 ? cnt : 10);
			spawned |= this.spawnZombieTeam(++groupNum, now, teamCnt);
			cnt -= teamCnt;
		}
		if(cnt > 0) {
			spawned |=this.spawnZombieTeam(++ groupNum, now, cnt);
		}
		if(spawned) {
			PlayerUtil.playClientSound(player, 2);
		    PlayerUtil.sendSubTitleToPlayer(player, HUGE_WAVE);
		    this.activateTombStone();
		    this.checkAndSummonBungee();
		}
	}
	
	protected void checkAndSummonBungee() {
		WorldEventData data = WorldEventData.getOverWorldEventData(world);
		if(! data.hasZombieSpawnEntry(Zombies.BUNGEE_ZOMBIE)) return ;
		int minCnt = 5 + this.currentWave;
		int maxCnt = 5 + 3 * this.currentWave;
		int cnt = world.random.nextInt(maxCnt - minCnt + 1) + minCnt;
		int height = world.getHeight(Type.WORLD_SURFACE, player.blockPosition().getX(), player.blockPosition().getZ());
		for(int i = 0; i < cnt; ++ i) {
			int posX = world.random.nextInt(71) - 35;
			int posZ = world.random.nextInt(71) - 35;
//			System.out.println(posX + " " + (height + 20) + " " + posZ);
			BungeeZombieEntity bungee = EntityRegister.BUNGEE_ZOMBIE.get().create(world);
			bungee.setBungeeType(BungeeTypes.SUMMON);
			EntityUtil.onMobEntitySpawn(world, bungee, new BlockPos(player.blockPosition().getX() + posX, height + 20, player.blockPosition().getZ() + posZ));
			EntityUtil.playSound(player, SoundRegister.BUNGEE_SCREAM.get());
		}
	}
	
	/**
	 * reset the huge wave time of each player.
	 */
	public static void resetPlayerWaveTime(PlayerEntity player) {
		player.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent((l) -> {
			OtherStats stats = l.getPlayerData().getOtherStats();
			int treeLvl = l.getPlayerData().getPlayerStats().getPlayerStats(Resources.TREE_LVL);
			int maxCnt = PlayerUtil.getPlayerWaveCount(treeLvl);
			int minCnt = (maxCnt + 1) / 2;
			int cnt = player.getRandom().nextInt(maxCnt - minCnt + 1) + minCnt;
			int partTime = 24000 / cnt;
			for(int i = 0; i < MAX_WAVE_NUM; ++ i) {
				if(i < cnt) {
					int time = player.getRandom().nextInt(partTime - 2000);
					stats.zombieWaveTime[i] = time + i * partTime + 1000;
				} else {
					stats.zombieWaveTime[i] = 0;
				}
			}
			stats.totalWaveCount = cnt;
			syncWaveTime(player);
//			for(int i : stats.zombieWaveTime) {
//				System.out.println(i);
//			}
		});
	}
	
	public static void giveInvasionBonusToPlayer(World world, PlayerEntity player) {
		if(! PlayerUtil.isPlayerSurvival(player)) return ;
		player.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent((l) -> {
			int cnt = l.getPlayerData().getPlayerStats().getPlayerStats(Resources.KILL_COUNT);
			if(cnt >= 20) {
				PlayerUtil.playClientSound(player, 6);
				PlayerUtil.addPlayerStats(player, Resources.MONEY, 50);
				PlayerUtil.addPlayerStats(player, Resources.LOTTERY_CHANCE, 2);
				player.addItem(getRandomItemForPlayer(world));
			}
			l.getPlayerData().getPlayerStats().setPlayerStats(Resources.KILL_COUNT, 0);
		});
	}
	
	private static ItemStack getRandomItemForPlayer(World world) {
		WorldEventData data = WorldEventData.getOverWorldEventData(world);
		for(Events ev : Events.values()) {
			if(data.hasEvent(ev) && ev.bundle.isPresent()) {
				return ev.bundle.get().getRandomBundle();
			}
		}
		return ItemStack.EMPTY;
	}
	
	private boolean spawnZombieTeam(int groupNum, int sum, int cnt) {
		BlockPos mid = this.findRandomSpawnPos(20);
		if(mid == null) return false; // no position do not spawn.
		for(int i = 0; i < cnt; ++ i) {
			int tmp = this.world.random.nextInt(sum);
			for(int j = 0; j < this.spawnWeights.size(); ++ j) {
				if(tmp < this.spawnWeights.get(j)) {
					this.spawnZombie(this.spawns.get(j), mid);
					break;
				}
			}
		}
		if(groupNum == 1) {
			WorldEventData data = WorldEventData.getOverWorldEventData(world);
			if(data.hasEvent(Events.YETI) && world.random.nextInt(3) == 0) {
				PVZZombieEntity yeti = EntityRegister.YETI_ZOMBIE.get().create(world);
			    EntityUtil.onMobEntitySpawn(world, yeti, mid.offset(0, 1, 0));
			}
		}
		PVZZombieEntity flagZombie = EntityRegister.FLAG_ZOMBIE.get().create(world);
		EntityUtil.onMobEntitySpawn(world, flagZombie, mid.offset(0, 1, 0));
		return true;
	}
	
	private void spawnZombie(Zombies zombie, BlockPos pos) {
		int range = 11;
		int x = pos.getX() + this.world.random.nextInt(range) - range / 2;
		int z = pos.getZ() + this.world.random.nextInt(range) - range / 2;
		int y = this.world.getHeight(Type.MOTION_BLOCKING_NO_LEAVES, x, z);
		PVZZombieEntity zombieEntity = ZombieUtil.getZombieEntity(world, zombie);
		EntityUtil.onMobEntitySpawn(world, zombieEntity, new BlockPos(x, y + 1, z));
	}
	
	private int getSpawnCount() {
		if(this.spawnCnt != 0) return this.spawnCnt;
		int minCnt = this.minSpawnCounts[this.currentWave];
		int maxCnt = this.maxSpawnCounts[this.currentWave];
		return this.world.random.nextInt(maxCnt - minCnt + 1) + minCnt;
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
			float f = this.world.random.nextFloat() * ((float) Math.PI * 2F);
			int dx = MathHelper.floor(MathHelper.cos(f) * distance);
			int dz = MathHelper.floor(MathHelper.sin(f) * distance);
			int x = this.center.getX() + (dx > 0 ? dx + range : dx - range);
			int z = this.center.getZ() + (dz > 0 ? dz + range : dz - range);
			int y = this.world.getHeight(Heightmap.Type.WORLD_SURFACE, x, z);
			BlockPos pos = new BlockPos(x, y, z);
			if (this.world.hasChunksAt(pos.offset(-range, -range, -range), pos.offset(range, range, range))
					&& this.world.getChunkSource().isEntityTickingChunk(new ChunkPos(pos))) {
//				System.out.println(world.getBlockState(pos.down()));
				if(world.getBlockState(pos.below()).getFluidState().isEmpty() && world.getBrightness(LightType.BLOCK, pos) < 7) {
					return pos;
				}
			}
		}
		return null;
	}

}
