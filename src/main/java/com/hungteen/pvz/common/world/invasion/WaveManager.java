package com.hungteen.pvz.common.world.invasion;

public class WaveManager {

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
//		for(IInvasionType ev : InvasionType.getAllInvasionEvents()) {
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


}
