package com.hungteen.pvz.common.world.invasion;

import javax.annotation.Nullable;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.common.capability.CapabilityHandler;
import com.hungteen.pvz.common.capability.player.PlayerDataManager;
import com.hungteen.pvz.common.capability.player.PlayerDataManager.OtherStats;
import com.hungteen.pvz.common.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.common.entity.zombie.grassnight.TombStoneEntity;
import com.hungteen.pvz.common.entity.zombie.roof.BungeeZombieEntity;
import com.hungteen.pvz.common.entity.zombie.roof.BungeeZombieEntity.BungeeTypes;
import com.hungteen.pvz.common.network.OtherStatsPacket;
import com.hungteen.pvz.common.network.PVZPacketHandler;
import com.hungteen.pvz.common.world.data.PVZInvasionData;
import com.hungteen.pvz.register.EntityRegister;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.ZombieUtil;
import com.hungteen.pvz.utils.enums.InvasionEvents;
import com.hungteen.pvz.utils.enums.Resources;
import com.hungteen.pvz.utils.enums.Zombies;
import com.hungteen.pvz.utils.others.WeightList;

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
	private final WeightList<Zombies> spawnList = new WeightList<>();
//	private final List<Zombies> spawns = new ArrayList<>(); 
//	private final List<Integer> spawnWeights = new ArrayList<>();
	private static final int[] SPAWN_COUNT_EACH_WAVE = new int[] {20, 30, 40, 50, 60};
	public int spawnCnt = 0;
	
	public WaveManager(PlayerEntity player, int waveNum) {
		this.world = player.level;
		this.currentWave = waveNum;
		this.player = player;
		this.center = player.blockPosition();
		this.updateSpawns();
	}
	
	/**
	 * check spawn huge wave of zombies for each player.
	 * dayTime means current time.
	 */
	public static void tickWave(World world, int dayTime) {
		if(! canWaveRun(world)) return ;
		PlayerUtil.getServerPlayers(world).forEach(player -> {
			if(PlayerUtil.isPlayerSurvival(player)) {
				player.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent(l -> {
			        PlayerDataManager.OtherStats stats = l.getPlayerData().getOtherStats();
			        for(int i = 0 ; i < stats.zombieWaveTime.length; ++i) {
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
	
	/**
	 * spawn wave zombies.
	 */
	public void spawnWaveZombies() {
		//can only spawn in overworld.
		if(! world.dimension().equals(World.OVERWORLD)) return ;
		if(this.spawnList.getTotal() == 0) {
			PVZMod.LOGGER.warn("Warn : Why cause no wave zombie spawned ?");
			return ;
		}
		int cnt = this.getSpawnCount();
		boolean spawned = false;
		int groupNum = 0;
		while(cnt >= 10) {
			int teamCnt = (cnt < 15 ? cnt : 10);
			spawned |= this.spawnZombieTeam(++groupNum, teamCnt);
			cnt -= teamCnt;
		}
		if(cnt > 0) {
			spawned |=this.spawnZombieTeam(++ groupNum, cnt);
		}
		if(spawned) {
			PlayerUtil.playClientSound(player, 2);
		    PlayerUtil.sendSubTitleToPlayer(player, HUGE_WAVE);
		    this.activateTombStone();
		    this.checkAndSummonBungee();
		}
	}
	
	protected void checkAndSummonBungee() {
		PVZInvasionData data = PVZInvasionData.getOverWorldInvasionData(world);
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
		player.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent(l -> {
			OtherStats stats = l.getPlayerData().getOtherStats();
			int treeLvl = l.getPlayerData().getPlayerStats().getPlayerStats(Resources.TREE_LVL);
			int maxCnt = PlayerUtil.getPlayerWaveCount(treeLvl);
			int minCnt = (maxCnt + 1) / 2;
			int cnt = player.getRandom().nextInt(maxCnt - minCnt + 1) + minCnt;
			int partTime = 21000 / cnt;//not happen at the first 2500 ticks and the last 500 ticks of the day.
			for(int i = 0; i < MAX_WAVE_NUM; ++ i) {
				if(i < cnt) {
					int time = player.getRandom().nextInt(partTime - 2000);
					stats.zombieWaveTime[i] = 2500 + (time + i * partTime + 1000);
				} else {
					stats.zombieWaveTime[i] = 0;
				}
			}
			stats.totalWaveCount = cnt;
			syncWaveTime(player);
		});
	}
	
	public static void giveInvasionBonusToPlayer(World world, PlayerEntity player) {
		if(! PlayerUtil.isPlayerSurvival(player)) return ;//do not effect to creative players.
		player.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent(l -> {
			int cnt = l.getPlayerData().getPlayerStats().getPlayerStats(Resources.KILL_COUNT);
			if(cnt >= 50) {//give reward if kill count reach 50
				PlayerUtil.playClientSound(player, 6);
			    PlayerUtil.addPlayerStats(player, Resources.MONEY, (cnt >= 200 ? 200 : 50));
			    PlayerUtil.addPlayerStats(player, Resources.LOTTERY_CHANCE, (cnt >= 200 ? 5 : 3));
			    for(int i = 0; i < (cnt >= 200 ? 2 : 1); ++ i) {
		    	    player.addItem(getRandomItemForPlayer(world));
			    }
			    PlayerDataManager.OtherStats stats = l.getPlayerData().getOtherStats();
			    //sync zombie wave time
			    for(int i = 0; i < stats.zombieWaveTime.length; ++i) {
			    	stats.zombieWaveTime[i] = 0;
			    }
			    stats.totalWaveCount = 0;
			    syncWaveTime(player);
			}
			//reset kill count.
			l.getPlayerData().getPlayerStats().setPlayerStats(Resources.KILL_COUNT, 0);
		});
	}
	
	/**
	 * get random enjoy card for bonus.
	 */
	private static ItemStack getRandomItemForPlayer(World world) {
		PVZInvasionData data = PVZInvasionData.getOverWorldInvasionData(world);
		for(InvasionEvents ev : InvasionEvents.values()) {
			if(data.hasEvent(ev) && ev.bundle.isPresent()) {
				return ev.bundle.get().getRandomBundle();
			}
		}
		return ItemStack.EMPTY;
	}
	
	/**
	 * spawn a zombie invade team.
	 */
	private boolean spawnZombieTeam(int groupNum, int cnt) {
		BlockPos mid = this.findRandomSpawnPos(20);
		if(mid == null) return false; // no position do not spawn.
		for(int i = 0; i < cnt; ++ i) {
			this.spawnList.getRandomItem(world.random).ifPresent(zombie -> {
				this.spawnZombie(zombie, mid);
			});
		}
		//spawn yeti zombie at team 1 when it's Yeti Invasion.
		if(groupNum == 1) {
			PVZInvasionData data = PVZInvasionData.getOverWorldInvasionData(world);
			if(data.hasEvent(InvasionEvents.YETI) && world.random.nextInt(3) == 0) {
				PVZZombieEntity yeti = EntityRegister.YETI_ZOMBIE.get().create(world);
			    EntityUtil.onMobEntitySpawn(world, yeti, mid.offset(0, 1, 0));
			}
		}
		//spawn team leader -- flag zombie.
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
	
	/**
	 * calculate how many zombies will spawn each wave.
	 */
	private int getSpawnCount() {
		if(this.spawnCnt != 0) return this.spawnCnt;
		int maxCnt = SPAWN_COUNT_EACH_WAVE[this.currentWave];
		int minCnt = maxCnt / 2;
		return this.world.random.nextInt(maxCnt - minCnt + 1) + minCnt;
	}
	
	/**
	 * called at constructor.
	 */
	private void updateSpawns() {
		PVZInvasionData data = PVZInvasionData.getOverWorldInvasionData(world);
		int sum = 0;
		for(Zombies zombie : Zombies.values()) {
			if(data.hasZombieSpawnEntry(zombie)) {
				this.spawnList.addItem(zombie, zombie.spawnWeight);
				sum += zombie.spawnWeight;
			}
		}
		this.spawnList.setTotal(sum);
	}

	@SuppressWarnings("deprecation")
	@Nullable
	private BlockPos findRandomSpawnPos(int chance) {
		int range = 16, distance = 32;
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
	
	public static boolean canWaveRun(World world) {
		return world.getDifficulty() != Difficulty.PEACEFUL;
	}

}
