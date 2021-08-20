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
import com.hungteen.pvz.common.event.handler.PlayerEventHandler;
import com.hungteen.pvz.common.network.OtherStatsPacket;
import com.hungteen.pvz.common.network.PVZPacketHandler;
import com.hungteen.pvz.common.world.data.PVZFlagData;
import com.hungteen.pvz.common.world.data.PVZInvasionData;
import com.hungteen.pvz.register.EntityRegister;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.remove.InvasionEvents;
import com.hungteen.pvz.remove.Zombies;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.MathUtil;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.WorldUtil;
import com.hungteen.pvz.utils.ZombieUtil;
import com.hungteen.pvz.utils.enums.Resources;
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
	private static final int[] SPAWN_COUNT_EACH_WAVE = new int[] {20, 30, 40, 50, 60};
	public int spawnCnt = 0;
	protected boolean spawned = false;
	
	public WaveManager(PlayerEntity player, int waveNum) {
		this.world = player.level;
		this.currentWave = MathHelper.clamp(waveNum, 1, 5);
		this.player = player;
		this.center = player.blockPosition();
		this.updateSpawns();
	}
	
	/**
	 * check spawn huge wave of zombies for each player.
	 * @param dayTime means current world time.
	 * {@link OverworldInvasion#tick(net.minecraftforge.event.TickEvent.WorldTickEvent)}
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
						    -- stats.totalWaveCount;
						    stats.zombieWaveTime[i] += FINISH_OFFSET;
						    syncWaveTime(player);
					        break;
				        }
			        }
		        });
			}
		});
	}
	
	/**
	 * sync wave time to client side.
	 * {@link #tickWave(World, int)}
	 * {@link PlayerEventHandler#onPlayerLogin(PlayerEntity)}
	 */
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
	
	/**
	 * spawn wave zombies.
	 * {@link #tickWave(World, int)}
	 */
	public void spawnWaveZombies() {
		//can only spawn in overworld.
		if(! world.dimension().equals(World.OVERWORLD)) {
			return ;
		}
		if(this.spawnList.getTotal() == 0) {
			PVZMod.LOGGER.warn("WaveManager : Why cause zombie spawn list empty ?");
			return ;
		}
		int cnt = this.getSpawnCount();
		int groupNum = 0;
		while(cnt >= 10) {//split whole zombie to serveral zombie teams.
			int teamCnt = (cnt < 15 ? cnt : 10);
			this.spawned |= this.spawnZombieTeam(++ groupNum, teamCnt);
			cnt -= teamCnt;
		}
		if(cnt > 0) {
			this.spawned |= this.spawnZombieTeam(++ groupNum, cnt);
		}
		if(this.spawned) {
			PlayerUtil.playClientSound(player, 2);
		    PlayerUtil.sendSubTitleToPlayer(player, HUGE_WAVE);
		    PVZFlagData data = PVZFlagData.getGlobalFlagData(world);
		    if(data.isZombossDefeated()) {
		        this.activateTombStone();
		        this.checkAndSummonBungee();
		    }
		}
	}
	
	/**
	 * summon tomb stone and activate them to summon zombies.
	 * {@link #spawnWaveZombies()}
	 */
	public void activateTombStone() {
		final int len = 50;
		if(this.currentWave > 2 && world.isNight()) {
			int cnt = this.getTombSpawnCount();
			for(int i = 0; i < cnt; ++ i) {
				this.spawnTombStone(player.blockPosition(), 20, 40);
			}
		}
		world.getEntitiesOfClass(TombStoneEntity.class, EntityUtil.getEntityAABB(player, len, len), (tombstone) -> {
			return true;
		}).forEach(tomb -> {
			tomb.activateByWave();
		});
	}
	
	/**
	 * summon bungee to send zombies down.
	 * {@link #spawnWaveZombies()}
	 */
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
			BungeeZombieEntity bungee = EntityRegister.BUNGEE_ZOMBIE.get().create(world);
			bungee.setBungeeType(BungeeTypes.SUMMON);
			EntityUtil.onEntitySpawn(world, bungee, new BlockPos(player.blockPosition().getX() + posX, height + 20, player.blockPosition().getZ() + posZ));
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
	 * {@link #spawnWaveZombies()}
	 */
	private boolean spawnZombieTeam(int groupNum, int cnt) {
		BlockPos mid = this.findRandomSpawnPos(20);
		if(mid == null) {//find spawn position failed.
			return false; 
		}
		for(int i = 0; i < cnt; ++ i) {//spawn zombies.
			this.spawnList.getRandomItem(world.random).ifPresent(zombie -> {
				this.spawnZombie(zombie, mid, 6);
			});
		}
		//spawn yeti zombie at the first team when it's Yeti Invasion.
		if(! this.spawned) {
			PVZInvasionData data = PVZInvasionData.getOverWorldInvasionData(world);
			if(data.hasEvent(InvasionEvents.YETI) && world.random.nextInt(4) == 0) {
				PVZZombieEntity yeti = EntityRegister.YETI_ZOMBIE.get().create(world);
			    EntityUtil.onEntitySpawn(world, yeti, mid.offset(0, 1, 0));
			}
		}
		//spawn team leader -- flag zombie.
		PVZZombieEntity flagZombie = EntityRegister.FLAG_ZOMBIE.get().create(world);
		EntityUtil.onEntitySpawn(world, flagZombie, mid.offset(0, 1, 0));
		return true;
	}
	
	/**
	 * spawn zombie surround by center blockpos.
	 * @param zombie the type of spawned zombie.
	 * @param pos position of the spawn center.
	 */
	private void spawnZombie(Zombies zombie, BlockPos pos, int radius) {
		final BlockPos blockPos = WorldUtil.getSuitableHeightRandomPos(world, pos, radius);
		PVZZombieEntity zombieEntity = ZombieUtil.getZombieEntity(world, zombie);
		EntityUtil.onEntitySpawn(world, zombieEntity, blockPos.above());
	}
	
	private void spawnTombStone(BlockPos pos, int min, int max) {
		final BlockPos blockPos = WorldUtil.getSuitableHeightRandomPos(world, pos, min, max);
		TombStoneEntity.spawnTombStone(world, blockPos);
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
	 * calculate how many tombstone will spawn each wave.
	 */
	private int getTombSpawnCount() {
		final int max = this.currentWave * 3;
		final int min = max / 2;
		return MathUtil.getRandomMinMax(world.random, min, max);
	}
	
	/**
	 * update zombie spawn list.
	 * {@link #WaveManager(PlayerEntity, int)}
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
