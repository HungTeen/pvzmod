package com.hungteen.pvz.common.world.invasion;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.common.misc.sound.SoundRegister;
import com.hungteen.pvz.register.EntityRegister;
import com.hungteen.pvz.utils.*;
import com.hungteen.pvz.utils.enums.Resources;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;

import java.util.Random;

import static com.hungteen.pvz.common.world.invasion.InvasionManager.suitableInvasionPos;

public class WaveManager {

	private static final ITextComponent HUGE_WAVE = new TranslationTextComponent("invasion.pvz.huge_wave").withStyle(TextFormatting.DARK_RED);
	public static final int MAX_WAVE_NUM = 10;
//	public static final int FINISH_OFFSET = 30000;
	private final World world;
	private final int currentWave;
	private final PlayerEntity player;
	private final BlockPos center;
	private static final int[] SPAWN_COUNT_EACH_WAVE = new int[] {25, 30, 35, 40, 45, 50, 55, 60, 65, 70};
	public int spawnCnt = 0;
	
	public WaveManager(PlayerEntity player, int waveNum) {
		this.world = player.level;
		this.currentWave = MathHelper.clamp(waveNum, 1, MAX_WAVE_NUM);
		this.player = player;
		this.center = player.blockPosition();
	}

	/**
	 * check spawn huge wave of zombies for each player.
	 * @param dayTime means current world time.
	 */
	public static void tickWave(World world, int dayTime) {
		PlayerUtil.getServerPlayers(world).stream().forEach(player -> {
			PlayerUtil.getOptManager(player).ifPresent(l -> {
				for(int i = 0; i < l.getTotalWaveCount(); ++ i){
					if(dayTime == l.getWaveTime(i)){
						l.setWaveTriggered(i, new WaveManager(player, i).spawnWaveZombies());
					} else if(dayTime < l.getWaveTime(i)){
						break;
					}
				}
			});
		});
	}

	/**
	 * spawn wave zombies.
	 * {@link #tickWave(World, int)}
	 */
	public boolean spawnWaveZombies() {
		//can only spawn in overworld, and peaceful, and wave enable.
		if(! PlayerUtil.isPlayerSurvival(player) || ! world.dimension().equals(World.OVERWORLD) || world.getDifficulty() == Difficulty.PEACEFUL || ! ConfigUtil.enableHugeWave()) {
			return false;
		}
		if(InvasionManager.spawnList.isEmpty()) {
			PVZMod.LOGGER.warn("WaveManager : Why cause spawn list empty ?");
			return false;
		}
		int cnt = this.getSpawnCount();
		boolean spawned = false;
		while(cnt >= 15) {//split whole zombie to serveral zombie teams.
			final int teamCnt = (cnt < 20 ? cnt : 10);
			spawned |= this.spawnZombieTeam(teamCnt);
			cnt -= teamCnt;
		}
		if(cnt > 0) {
			spawned |= this.spawnZombieTeam(cnt);
		}
		if(spawned) {
			PlayerUtil.playClientSound(player, SoundRegister.HUGE_WAVE.get());
		    PlayerUtil.sendSubTitleToPlayer(player, HUGE_WAVE);
			// TODO 一大波额外生成

//		    PVZFlagData data = PVZFlagData.getGlobalFlagData(world);
//		    if(data.isZombossDefeated()) {
//		        this.activateTombStone();
//		        this.checkAndSummonBungee();
//		    }
		}
		return spawned;
	}

//	/**
//	 * summon tomb stone and activate them to summon zombies.
//	 * {@link #spawnWaveZombies()}
//	 */
//	public void activateTombStone() {
//		final int len = 50;
//		if(this.currentWave > 2 && world.isNight()) {
//			int cnt = this.getTombSpawnCount();
//			for(int i = 0; i < cnt; ++ i) {
//				this.spawnTombStone(player.blockPosition(), 20, 40);
//			}
//		}
//		world.getEntitiesOfClass(TombStoneEntity.class, EntityUtil.getEntityAABB(player, len, len), (tombstone) -> {
//			return true;
//		}).forEach(tomb -> {
//			tomb.activateByWave();
//		});
//	}
//
//	/**
//	 * summon bungee to send zombies down.
//	 * {@link #spawnWaveZombies()}
//	 */
//	protected void checkAndSummonBungee() {
//		PVZInvasionData data = PVZInvasionData.getOverWorldInvasionData(world);
//		if(! data.hasZombieSpawnEntry(RoofZombies.BUNGEE_ZOMBIE)) {
//			return ;
//		}
//		int minCnt = 5 + this.currentWave;
//		int maxCnt = 5 + 3 * this.currentWave;
//		int cnt = world.random.nextInt(maxCnt - minCnt + 1) + minCnt;
//		int height = world.getHeight(Type.WORLD_SURFACE, player.blockPosition().getX(), player.blockPosition().getZ());
//		for(int i = 0; i < cnt; ++ i) {
//			int posX = world.random.nextInt(71) - 35;
//			int posZ = world.random.nextInt(71) - 35;
//			BungeeZombieEntity bungee = EntityRegister.BUNGEE_ZOMBIE.get().create(world);
//			bungee.setBungeeType(BungeeTypes.SUMMON);
//			EntityUtil.onEntitySpawn(world, bungee, new BlockPos(player.blockPosition().getX() + posX, height + 20, player.blockPosition().getZ() + posZ));
//			EntityUtil.playSound(player, SoundRegister.BUNGEE_SCREAM.get());
//		}
//	}
//
	/**
	 * reset the huge wave time of each player.
	 */
	public static void resetPlayerWaveTime(PlayerEntity player) {
		final int lvl = PlayerUtil.getResource(player, Resources.TREE_LVL);
		final int cnt = getPlayerWaveCount(player.getRandom(), lvl);
		//not happen at the first 2000 ticks and the last 2000 ticks of the day.
		final int eachTime = 20000 / cnt;
		for(int i = 0; i < cnt; ++ i){
			final int offset = 2000 + i * eachTime + player.getRandom().nextInt(eachTime);
			final int pos = i;
			PlayerUtil.getOptManager(player).ifPresent(l -> {
				l.setWaveTime(pos, offset);
				l.setWaveTriggered(pos, false);
			});
		}
		PlayerUtil.getOptManager(player).ifPresent(l -> l.setTotalWaveCount(cnt));
	}

	public static void clearWaveTime(PlayerEntity player){
		PlayerUtil.getOptManager(player).ifPresent(l -> l.setTotalWaveCount(0));
	}

//	public static void giveInvasionBonusToPlayer(World world, PlayerEntity player) {
//		if(! PlayerUtil.isPlayerSurvival(player)) return ;//do not effect to creative players.
//		player.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent(l -> {
//			int cnt = l.getPlayerData().getResource(Resources.KILL_COUNT);
//			if(cnt >= 50) {//give reward if kill count reach 50
//				PlayerUtil.playClientSound(player, PVZSounds.COIN_COLLECT);
//			    PlayerUtil.addResource(player, Resources.MONEY, (cnt >= 200 ? 200 : 50));
//			    PlayerUtil.addResource(player, Resources.LOTTERY_CHANCE, (cnt >= 200 ? 5 : 3));
//			    for(int i = 0; i < (cnt >= 200 ? 2 : 1); ++ i) {
//		    	    player.addItem(getRandomItemForPlayer(world));
//			    }
//			    PlayerDataManager.OtherStats stats = l.getPlayerData().getOtherStats();
//			    //sync zombie wave time
//			    for(int i = 0; i < stats.zombieWaveTime.length; ++i) {
//			    	stats.zombieWaveTime[i] = 0;
//			    }
//			    stats.totalWaveCount = 0;
//			    syncWaveTime(player);
//			}
//			//reset kill count.
//			l.getPlayerData().setResource(Resources.KILL_COUNT, 0);
//		});
//	}
//
//	/**
//	 * get random enjoy card for bonus.
//	 */
//	private static ItemStack getRandomItemForPlayer(World world) {
//		PVZInvasionData data = PVZInvasionData.getOverWorldInvasionData(world);
//		for(IInvasionType ev : InvasionType.getInvasionEvents()) {
//			if(data.hasEvent(ev)) {
//				return ev.getBundle().getEnjoyCard(world.getRandom());
//			}
//		}
//		return ItemStack.EMPTY;
//	}

	//	private void spawnTombStone(BlockPos pos, int min, int max) {
//		final BlockPos blockPos = WorldUtil.getSuitableHeightRandomPos(world, pos, min, max);
//		TombStoneEntity.spawnTombStone(world, blockPos);
//	}

	//	/**
//	 * calculate how many tombstone will spawn each wave.
//	 */
//	private int getTombSpawnCount() {
//		final int max = this.currentWave * 3;
//		final int min = max / 2;
//		return MathUtil.getRandomMinMax(world.random, min, max);
//	}

	/**
	 * spawn a zombie invade team.
	 * {@link #spawnWaveZombies()}
	 */
	private boolean spawnZombieTeam(int cnt) {
		final BlockPos mid = WorldUtil.findRandomSpawnPos(world, player.blockPosition(), 20, 16, 48,
				b -> suitableInvasionPos(world, b) && world.getBlockState(b.below()).getFluidState().isEmpty());
		
		boolean flag = false;
		if(mid != null) {//find spawn position.
			for (int i = 0; i < cnt; ++ i){
				final SpawnType type = InvasionManager.spawnList.getRandomItem(world.random).get();
				final BlockPos pos = WorldUtil.findRandomSpawnPos(world, mid, 4, 1, 7, b -> type.checkPos(world, b));
				if(pos != null){
					flag = true;
					EntityUtil.onEntitySpawn(world, type.getSpawnType().create(world), pos);
				}
			}
			if(flag){
				//spawn team leader -- flag zombie.
				EntityUtil.onEntitySpawn(world, EntityRegister.FLAG_ZOMBIE.get().create(world), mid.offset(0, 1, 0));

				//spawn yeti zombie when it's Yeti Invasion.
				if(world.random.nextFloat() < InvasionManager.getYetiSpawnChance()){
					EntityUtil.onEntitySpawn(world, EntityRegister.YETI_ZOMBIE.get().create(world), mid.offset(0, 1, 0));
				}
			}
		}
		return flag;
	}

	/**
	 * calculate how many zombies will spawn each wave.
	 */
	private int getSpawnCount() {
		if(this.spawnCnt != 0) {
			return this.spawnCnt;
		}
		final int maxCnt = SPAWN_COUNT_EACH_WAVE[this.currentWave];
		final int minCnt = maxCnt / 2;
		return MathUtil.getRandomMinMax(world.random, minCnt, maxCnt);
	}

	/**
	 * max : 10 20 35 50 65 80 100 ...
	 * min : 20 40 60 80 100
	 */
	private static int getPlayerWaveCount(Random random, int lvl){
		final int max = Math.min(lvl <= 20 ? (lvl + 9) / 10 : lvl <= 80 ? (lvl - 6) / 15 + 2 : lvl / 20 + 3, MAX_WAVE_NUM);
		final int min = Math.max(1, Math.min(lvl <= 40 ? (lvl + 19) / 20 : (lvl + 39) / 40 + 1, max - 1));
		return MathUtil.getRandomMinMax(random, min, max);
	}

}
