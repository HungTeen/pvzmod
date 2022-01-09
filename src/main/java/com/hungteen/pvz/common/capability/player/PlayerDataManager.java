package com.hungteen.pvz.common.capability.player;


import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.api.PVZAPI;
import com.hungteen.pvz.api.events.PlayerLevelChangeEvent;
import com.hungteen.pvz.api.types.IPAZType;
import com.hungteen.pvz.common.advancement.trigger.MoneyTrigger;
import com.hungteen.pvz.common.advancement.trigger.PlantLevelTrigger;
import com.hungteen.pvz.common.advancement.trigger.SunAmountTrigger;
import com.hungteen.pvz.common.advancement.trigger.TreeLevelTrigger;
import com.hungteen.pvz.common.container.shop.MysteryShopContainer;
import com.hungteen.pvz.common.event.handler.PlayerEventHandler;
import com.hungteen.pvz.common.item.spawn.card.SummonCardItem;
import com.hungteen.pvz.common.misc.PVZPacketTypes;
import com.hungteen.pvz.common.network.PAZStatsPacket;
import com.hungteen.pvz.common.network.PVZPacketHandler;
import com.hungteen.pvz.common.network.toclient.CardInventoryPacket;
import com.hungteen.pvz.common.network.toclient.OtherStatsPacket;
import com.hungteen.pvz.common.network.toclient.PlayerStatsPacket;
import com.hungteen.pvz.common.world.invasion.MissionManager;
import com.hungteen.pvz.common.world.invasion.WaveManager;
import com.hungteen.pvz.utils.PAZUtil;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.StringUtil;
import com.hungteen.pvz.utils.enums.Resources;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.network.PacketDistributor;

import java.util.*;


public class PlayerDataManager {

	private final PlayerEntity player;
	/* player resources */
	private final HashMap<Resources, Integer> resources = new HashMap<>(Resources.values().length);
	/* summon card inventory */
	private final List<ItemStack> cards = new ArrayList<>();
	private int emptySlot;
	/* plant & zombie point */
	private final Map<IPAZType, Integer> pazPoint = new HashMap<>();
	/* plant & zombie cd */
	private final Map<IPAZType, Integer> pazCardCD = new HashMap<>();
	private final Map<IPAZType, Float> pazCardBar = new HashMap<>();
	/* plant & zombie lock */
	private final Map<IPAZType, Boolean> pazLocked = new HashMap<>();
	/* wave */
	private int[] waveTime = new int[WaveManager.MAX_WAVE_NUM];
	private boolean[] waveTriggered = new boolean[WaveManager.MAX_WAVE_NUM];
	private int totalWaveCount;
	/* mission */
	private LinkedList<Integer> killQueue = new LinkedList<>();
	public int killInSecond;
	/* misc data */
	public String lastVersion = StringUtil.INIT_VERSION;
	private final OtherStats otherStats;
	
	public PlayerDataManager(PlayerEntity player) {
		this.player = player;
		{// init player resources.
			for(Resources res : Resources.values()) {
				resources.put(res, Resources.getInitialValue(res));
			}
		}
		{// init card inventory.
			for(int i = 0; i <= Resources.SLOT_NUM.max; ++ i) {
				this.cards.add(ItemStack.EMPTY);
			}
		}
		{// init about plants & zombies.
			PVZAPI.get().getPAZs().forEach(plant -> {
				this.pazPoint.put(plant, 0);
				this.pazCardCD.put(plant, 0);
				this.pazCardBar.put(plant, 0F);
				this.pazLocked.put(plant, true);
			});
		}
		{//wave.
			for(int i = 0; i < WaveManager.MAX_WAVE_NUM; ++ i){
				this.waveTime[i] = 0;
				this.waveTriggered[i] = false;
			}
			this.totalWaveCount = 0;
		}
		{
			this.killQueue.clear();
			this.killInSecond = 0;
		}
		{// init misc data.
			this.otherStats = new OtherStats(this);
		}
	}
	
	/**
	 * read {@link PlayerDataStorage#readNBT(net.minecraftforge.common.capabilities.Capability, IPlayerDataCapability, net.minecraft.util.Direction, net.minecraft.nbt.INBT)}.
	 */
	public void loadFromNBT(CompoundNBT baseTag) {
		{// load player resources.
			if(baseTag.contains("player_stats")) {//old.
				CompoundNBT statsTag = baseTag.getCompound("player_stats");
			    for(Resources res:Resources.values()) {
			    	if(statsTag.contains("player_"+res.toString())) {
				        this.resources.put(res, statsTag.getInt("player_"+res.toString()));
			    	}
			    }
			}
			if(baseTag.contains("player_resources")) {//new.
				CompoundNBT statsTag = baseTag.getCompound("player_resources");
			    for(Resources res:Resources.values()) {
			    	if(statsTag.contains(res.toString().toLowerCase())) {
				        this.resources.put(res, statsTag.getInt(res.toString().toLowerCase()));
			    	}
			    }
			}
		}
		{// load card inventory.
			if(baseTag.contains("card_inventory")) {
				CompoundNBT nbt = baseTag.getCompound("card_inventory");
				for(int i = 0; i <= Resources.SLOT_NUM.max; ++ i) {
					if(nbt.contains("slot_" + i)) {
						this.cards.set(i, ItemStack.of(nbt.getCompound("slot_" + i)));
					}
				}
				if(nbt.contains("current_pos")) {
					this.emptySlot = nbt.getInt("current_pos");
				}
			}
		}
		{// load plants & zombies point.
			if(baseTag.contains("paz_point_info")) {
			    final CompoundNBT nbt = baseTag.getCompound("paz_point_info");
				PVZAPI.get().getPAZs().forEach(plant -> {
				    final CompoundNBT plantTag = (CompoundNBT) nbt.get(plant.getIdentity());
				    if(plantTag != null) {
				        if(plantTag.contains("paz_point")) {
					        this.pazPoint.put(plant, plantTag.getInt("paz_point"));
				        }
				    }
			    });
			}
		}
		{// load plant & zombie card cd.
			if(baseTag.contains("paz_card_item_cd")) {
			    final CompoundNBT nbt = baseTag.getCompound("paz_card_item_cd");
				PVZAPI.get().getPAZs().forEach(plant -> {
					final CompoundNBT plantNBT = (CompoundNBT) nbt.get(plant.getIdentity());
					if(plantNBT != null) {
					    if(plantNBT.contains("paz_card_cd")) {
						    this.setPAZCardCD(plant, plantNBT.getInt("paz_card_cd"));
					    }
					    if(plantNBT.contains("paz_card_bar")) {
						    this.setPAZCardBar(plant, plantNBT.getInt("paz_card_bar"));
					    }
					}
				});
			}
		}
		{// load plant & zombie lock.
			if(baseTag.contains("paz_locks")) {
			    final CompoundNBT nbt = baseTag.getCompound("paz_locks");
				PVZAPI.get().getPAZs().forEach(plant -> {
					final CompoundNBT plantNBT = (CompoundNBT) nbt.get(plant.getIdentity());
					if(plantNBT != null) {
					    if(plantNBT.contains("paz_locked")) {
						    this.setPAZLocked(plant, plantNBT.getBoolean("paz_locked"));
					    }
					}
				});
			}
		}
		if(baseTag.contains("wave_nbt")){//wave.
			final CompoundNBT nbt = baseTag.getCompound("wave_nbt");
			for(int i = 0; i < WaveManager.MAX_WAVE_NUM; ++ i){
				this.waveTime[i] = nbt.getInt("wave_time_" + i);
				this.waveTriggered[i] = nbt.getBoolean("wave_triggered_" + i);
			}
			this.totalWaveCount = nbt.getInt("wave_count");
		}
		if(baseTag.contains("mission_nbt")){
			final CompoundNBT nbt = baseTag.getCompound("mission_nbt");
			for(int i = 0; i < MissionManager.KILL_IN_SECOND; ++ i){
				if(nbt.contains("kill_count" + i)){
					this.killQueue.add(nbt.getInt("kill_count" + i));
				}
			}
			this.killInSecond = nbt.getInt("kill_in_second");
		}
		{// load misc data.
			if(baseTag.contains("last_join_version")) {
				this.lastVersion = baseTag.getString("last_join_version");
			}
			this.otherStats.loadFromNBT(baseTag);
		}
	}
	
	/**
	 * write {@link PlayerDataStorage#writeNBT(net.minecraftforge.common.capabilities.Capability, IPlayerDataCapability, net.minecraft.util.Direction)}.
	 */
	public CompoundNBT saveToNBT() {
		CompoundNBT baseTag = new CompoundNBT();
		{// save player resources.
			CompoundNBT statsNBT = new CompoundNBT();
			for(Resources res : Resources.values()) {
				statsNBT.putInt(res.toString().toLowerCase(), resources.get(res));
			}
			baseTag.put("player_resources", statsNBT);
		}
		{// save card inventory.
			CompoundNBT nbt = new CompoundNBT();
			for(int i = 0; i < Resources.SLOT_NUM.max; ++ i) {
				nbt.put("slot_" + i, this.getItemAt(i).save(new CompoundNBT()));
			}
			nbt.putInt("current_pos", this.emptySlot);
			baseTag.put("card_inventory", nbt);
		}
		{// save plant & zombie point.
			final CompoundNBT nbt = new CompoundNBT();
			PVZAPI.get().getPAZs().forEach(plant -> {
		        final CompoundNBT plantNBT = new CompoundNBT();
				plantNBT.putInt("paz_point", this.getPAZPoint(plant));
				nbt.put(plant.getIdentity(), plantNBT);
			});
			baseTag.put("paz_point_info", nbt);
		}
		{// save plant & zombie card cd.
			final CompoundNBT nbt = new CompoundNBT();
			PVZAPI.get().getPAZs().forEach(plant -> {
				final CompoundNBT plantNBT = new CompoundNBT();
				nbt.putInt("paz_card_cd", this.pazCardCD.get(plant));
				nbt.putFloat("paz_card_bar", this.pazCardBar.get(plant));
				nbt.put(plant.getIdentity(), plantNBT);
			});
			baseTag.put("paz_card_item_cd", nbt);
		}
		{// save plant & zombie lock.
			final CompoundNBT nbt = new CompoundNBT();
			PVZAPI.get().getPAZs().forEach(plant -> {
				final CompoundNBT plantNBT = new CompoundNBT();
				nbt.putBoolean("paz_locked", this.isPAZLocked(plant));
				nbt.put(plant.getIdentity(), plantNBT);
			});
			baseTag.put("paz_locks", nbt);
		}
		{//wave.
			final CompoundNBT nbt = new CompoundNBT();
			for(int i = 0; i < WaveManager.MAX_WAVE_NUM; ++ i){
				nbt.putInt("wave_time_" + i, this.waveTime[i]);
				nbt.putBoolean("wave_triggered_" + i, this.waveTriggered[i]);
			}
			nbt.putInt("wave_count", this.totalWaveCount);
			baseTag.put("wave_nbt", nbt);
		}
		{//mission.
			final CompoundNBT nbt = new CompoundNBT();
			for(int i = 0; i < killQueue.size(); ++ i){
				nbt.putInt("kill_count" + i, killQueue.get(i));
			}
			nbt.putInt("kill_in_second", killInSecond);
			baseTag.put("mission_nbt", nbt);
		}
		{// load misc data.
			baseTag.putString("last_join_version", this.lastVersion);
			this.otherStats.saveToNBT(baseTag);
		}
		return baseTag;
	}
//	
	public void cloneFromExistingPlayerData(PlayerDataManager data, boolean died) {
		this.loadFromNBT(data.saveToNBT());
		
		/* reset some value when die */
		if(died) {
			if(! PVZConfig.COMMON_CONFIG.RuleSettings.KeepSunWhenDie.get()) {
				this.setResource(Resources.SUN_NUM, 50);
			}
			this.setResource(Resources.NO_FOG_TICK, 0);
//			this.setResource(Resources.KILL_COUNT, 0);
		}
	}
	
	/**
	 * run when player join world.
	 * {@link PlayerEventHandler#onPlayerLogin(PlayerEntity)}
	 */
	public void init() {
		this.syncBounds();
		this.syncToClient();
	}
	
	/**
	 * avoid render out of bound.
	 * {@link #init()}
	 */
	private void syncBounds() {
		{// player resources.
		    for(Resources res : Resources.values()) {
			    this.addResource(res, 0);
		    }
		}
	}
	
	/**
	 * sync data to client side.
	 * {@link #init()}
	 */
	private void syncToClient() {
		{// player resources.
		    for(Resources res : Resources.values()) {
			    this.sendResourcePacket(player, res);
		    }
		}
		{// card inventory.
			for(int i = 0; i <= Resources.SLOT_NUM.max; ++ i) {
				this.sendInventoryPacket(player, i, this.getItemAt(i).save(new CompoundNBT()));
			}
			this.setCurrentPos(this.getCurrentPos());
		}
		{// plants & zombies syncs.
			PVZAPI.get().getPAZs().forEach(plant -> {
				this.sendPAZPointPacket(player, plant);//level.
				if(plant.getSummonCard().isPresent()) {//card cd.
					player.getCooldowns().addCooldown(plant.getSummonCard().get(), this.getPlantCardCD(plant));
				}
				this.sendPAZLockPacket(player, plant);//lock.
			});
		}
		{//wave.
			this.sendAllWavePacket(player);
		}
	}

	/*
	 * Operations of Player Resources.
	 */
	
	public int getResource(Resources res){
		return this.resources.get(res);
	}
	
	/**
	 * check (min, max) and sync send packet.
	 */
	public void setResource(Resources res, int num) {
		resources.put(res, num);
		if(res == Resources.TREE_LVL) {
			resources.put(Resources.TREE_XP, 0);
			this.addResource(Resources.TREE_XP, 0);
		}
		this.addResource(res, 0);
	}
	
	public void addResource(Resources res, int num) {
		int now = resources.get(res);
		final int old = now;
		
		if(res == Resources.TREE_XP) {
			addTreeXp(now, num);
		} else if(res == Resources.SUN_NUM) {
			now = MathHelper.clamp(now + num, 0, PlayerUtil.getPlayerMaxSunNum(resources.get(Resources.TREE_LVL)));
			resources.put(Resources.SUN_NUM, now);
			if(player instanceof ServerPlayerEntity){
				SunAmountTrigger.INSTANCE.trigger((ServerPlayerEntity) player, now);
			}
		} else if(res == Resources.ENERGY_NUM) {
			now = MathHelper.clamp(now + num, 0, resources.get(Resources.MAX_ENERGY_NUM));
			resources.put(Resources.ENERGY_NUM, now);
		} else{
			now = MathHelper.clamp(now + num, res.min, res.max);
			resources.put(res, now);

			if(res == Resources.TREE_LVL) {
				this.addResource(Resources.SUN_NUM, 0);
				if(player instanceof ServerPlayerEntity){
					TreeLevelTrigger.INSTANCE.trigger((ServerPlayerEntity) player, now);
					if(old != now){
						MinecraftForge.EVENT_BUS.post(new PlayerLevelChangeEvent(player, old, now));
					}
				}
			} else if(res == Resources.MONEY) {
				if(player instanceof ServerPlayerEntity){
					MoneyTrigger.INSTANCE.trigger((ServerPlayerEntity) player, now);
				}
			} else if(res == Resources.MISSION_VALUE){
				if(this.getResource(Resources.MISSION_TYPE) == MissionManager.MissionType.INSTANT_KILL.ordinal()){
					this.killInSecond ++;
				}
			}
		}
		
		this.sendResourcePacket(player, res);
	}
	
	/**
	 * add tree xp and maxLevel up.
	 */
	private void addTreeXp(int now, int num) {
		int lvl = resources.get(Resources.TREE_LVL);
		if(num > 0) {
			int req = PlayerUtil.getPlayerLevelUpXp(lvl);
			while(lvl < Resources.TREE_LVL.max && num + now >= req) {
				num -= req - now;
				this.addResource(Resources.TREE_LVL, 1);
				now = 0;
				req = PlayerUtil.getPlayerLevelUpXp(lvl);
			}
			resources.put(Resources.TREE_XP, num + now);
		} else {
			num = - num;
			while(lvl > 1 && num > now) {
				num -= now;
				-- lvl;
				now = PlayerUtil.getPlayerLevelUpXp(lvl);
				this.addResource(Resources.TREE_LVL, - 1);
			}
			resources.put(Resources.TREE_XP, Math.max(now - num, 0));
		}
	}
	
	private void sendResourcePacket(PlayerEntity player, Resources res){
		if (player instanceof ServerPlayerEntity) {
			PVZPacketHandler.CHANNEL.send(
				PacketDistributor.PLAYER.with(() -> {
					return (ServerPlayerEntity) player;
				}),
				new PlayerStatsPacket(res.ordinal(), resources.get(res))
			);
		}
	}
	
	/*
	 * Operation about SummonCard Inventory.
	 */
	
	public void onScrollInventory(double delta) {
		final int maxSlot = this.getResource(Resources.SLOT_NUM);
		final int now = this.emptySlot;
		final int next = (now + (delta > 0 ? -1 : 1) + (maxSlot + 1)) % (maxSlot + 1);
		
		player.setItemInHand(Hand.MAIN_HAND, this.getItemAt(next));
		this.setCurrentPos(next);
	}
	
	public void onSwitchCard() {
		if(player.getMainHandItem().getItem() instanceof SummonCardItem) {
		    this.setItemAt(player.getMainHandItem(), this.emptySlot);
		}
	}
	
	public ItemStack getItemAt(int pos) {
		return this.cards.get(pos);
	}
	
	public void setItemAt(ItemStack stack, int pos) {
		pos = MathHelper.clamp(pos, 0, this.cards.size() - 1);
		this.cards.set(pos, stack);
		this.sendInventoryPacket(player, pos, this.cards.get(pos).save(new CompoundNBT()));
	}
	
	public void setCurrentPos(int pos) {
		this.emptySlot = pos;
		CompoundNBT nbt = new CompoundNBT();
		nbt.putInt(CardInventoryPacket.FLAG, this.emptySlot);
		this.sendInventoryPacket(player, -1, nbt);
	}
	
	public int getCurrentPos() {
		return this.emptySlot;
	}
	
	private void sendInventoryPacket(PlayerEntity player, int type, CompoundNBT nbt) {
		if (player instanceof ServerPlayerEntity) {
			PVZPacketHandler.CHANNEL.send(
				PacketDistributor.PLAYER.with(() -> {
					return (ServerPlayerEntity) player;
				}),
				new CardInventoryPacket(type, nbt)
			);
		}
	}
	
	/*
	 * Operation about Plant Points.
	 */
	
	public void addPAZPoint(IPAZType plant, int num){
		final int old = this.getPAZPoint(plant);
		final int lvl = MathHelper.clamp(old + num, 0, PAZUtil.DEFAULT_MAX_POINTS);
		this.pazPoint.put(plant, lvl);
		if(player instanceof ServerPlayerEntity){
			PlantLevelTrigger.INSTANCE.trigger((ServerPlayerEntity) player, lvl);
			if(lvl != old){
				MinecraftForge.EVENT_BUS.post(new PlayerLevelChangeEvent.PAZLevelChangeEvent(player, plant, old, lvl));
			}
		}
		this.sendPAZPointPacket(player, plant);
	}
	
	public int getPAZPoint(IPAZType plant){
		return this.pazPoint.get(plant);
	}
	
	private void sendPAZPointPacket(PlayerEntity player, IPAZType plant){
		if (player instanceof ServerPlayerEntity) {
			PVZPacketHandler.CHANNEL.send(
				PacketDistributor.PLAYER.with(()->{
					return (ServerPlayerEntity) player;
				}),
				new PAZStatsPacket(PAZStatsPacket.PAZPacketTypes.POINT, plant.getIdentity(), pazPoint.get(plant))
			);
		}
	}
	
	/*
	 * Operation about plant card CD.
	 * just store data, no need to manually sync to client.
	 */
	
	public void setPAZCardCD(IPAZType plant, int tick) {
		this.pazCardCD.put(plant, tick);
	}
	
	public int getPAZCardCoolDown(IPAZType plant) {
		return this.pazCardCD.get(plant);
	}
	
	public void setPAZCardBar(IPAZType plant, float bar) {
		this.pazCardBar.put(plant, bar);
	}
	
	public float getPAZCardBarLength(IPAZType plant) {
		return this.pazCardBar.get(plant);
	}
	
	public int getPlantCardCD(IPAZType plant) {
		return (int) (this.pazCardBar.get(plant) * this.pazCardCD.get(plant));
	}

	/*
	 * Operation about plant lock.
	 */
	
	public void setPAZLocked(IPAZType plant, boolean is) {
		final boolean old = this.isPAZLocked(plant);
		this.pazLocked.put(plant, is);
		if(old != is) {
			this.sendPAZLockPacket(player, plant);
		}
	}
	
	public boolean isPAZLocked(IPAZType plant) {
		return this.pazLocked.getOrDefault(plant, true);
	}

	private void sendPAZLockPacket(PlayerEntity player, IPAZType plant){
		if (player instanceof ServerPlayerEntity) {
			PVZPacketHandler.CHANNEL.send(
					PacketDistributor.PLAYER.with(() -> {
						return (ServerPlayerEntity) player;
					}),
					new PAZStatsPacket(PAZStatsPacket.PAZPacketTypes.UNLOCK, plant.getIdentity(), this.isPAZLocked(plant))
			);
		}
	}

	/*
	Operation about wave.
	 */

	public void setWaveTime(int pos, int data){
		if(pos >= 0 && pos < WaveManager.MAX_WAVE_NUM) {
		    this.waveTime[pos] = data;
		    this.sendWavePacket(player, pos, data);
		}
	}

	public void setTotalWaveCount(int cnt){
		this.totalWaveCount = cnt;
		this.sendWavePacket(player, -1, cnt);
	}

	public void setWaveTriggered(int pos, boolean is){
		if(pos >= 0 && pos < WaveManager.MAX_WAVE_NUM) {
			this.waveTriggered[pos] = is;
		    this.sendWaveFlagPacket(player, pos, is);
		}
	}

	public int getTotalWaveCount() {
		return totalWaveCount;
	}

	public boolean getWaveTriggered(int pos) {
		return waveTriggered[pos];
	}

	public int getWaveTime(int pos) {
		return waveTime[pos];
	}

	private void sendWavePacket(PlayerEntity player, int pos, int data){
		if (player instanceof ServerPlayerEntity) {
			PVZPacketHandler.CHANNEL.send(
					PacketDistributor.PLAYER.with(() -> {
						return (ServerPlayerEntity) player;
					}),
					new OtherStatsPacket(PVZPacketTypes.WAVE, pos, data)
			);
		}
	}

	private void sendWaveFlagPacket(PlayerEntity player, int pos, boolean flag){
		if (player instanceof ServerPlayerEntity) {
			PVZPacketHandler.CHANNEL.send(
					PacketDistributor.PLAYER.with(() -> {
						return (ServerPlayerEntity) player;
					}),
					new OtherStatsPacket(PVZPacketTypes.WAVE_FLAG, pos, flag)
			);
		}
	}

	private void sendAllWavePacket(PlayerEntity player){
		for (int i = 0; i < WaveManager.MAX_WAVE_NUM; ++ i){
			sendWavePacket(player, i, this.waveTime[i]);
			sendWaveFlagPacket(player, i, this.waveTriggered[i]);
		}
		sendWavePacket(player, -1, this.totalWaveCount);
	}

	/*
	Operation about Mission.
	 */

	public void resetMission(MissionManager.MissionType type){
		this.setResource(Resources.MISSION_TYPE, type.ordinal());
		this.setResource(Resources.MISSION_VALUE, 0);
		this.setResource(Resources.MISSION_STAGE, 0);
		this.killQueue.clear();
		this.killInSecond = 0;
	}

	public void updateKillQueue(){
		if(killQueue.size() < MissionManager.KILL_IN_SECOND){
			killQueue.push(killInSecond);
		} else{
			this.addResource(Resources.MISSION_VALUE, - killQueue.pop());
			killQueue.push(killInSecond);
			killInSecond = 0;
		}
	}

	public void clearMission(){
		resetMission(MissionManager.MissionType.EMPTY);
	}

	public final class OtherStats{
		
		@SuppressWarnings("unused")
		private final PlayerDataManager manager;
		public int[] mysteryGoods = new int[MysteryShopContainer.MAX_MYSTERY_GOOD];
		public int playSoundTick;
		public int updateGoodTick;
		public int lightLevel;
		
		public OtherStats(PlayerDataManager manager) {
			this.manager = manager;
			this.playSoundTick = 0;
			this.lightLevel = 0;
//			MysteryShopContainer.genNextGoods(player);
		}
		
		private void saveToNBT(CompoundNBT baseTag) {
			baseTag.putInt("base_sound_tick", this.playSoundTick);
			CompoundNBT tmp = new CompoundNBT();
			for(int i = 0; i < mysteryGoods.length; ++ i) {
				tmp.putInt("mystery_good_" + i, mysteryGoods[i]);
			}
			baseTag.put("mystery_goods", tmp);
			baseTag.putInt("update_good_tick", this.updateGoodTick);
		}

		private void loadFromNBT(CompoundNBT baseTag) {
			if(baseTag.contains("base_sound_tick")) {
				this.playSoundTick = baseTag.getInt("base_sound_tick");
			}
			if(baseTag.contains("mystery_goods")) {
				CompoundNBT nbt = baseTag.getCompound("mystery_goods");
				for(int i = 0; i < mysteryGoods.length; ++ i) {
					mysteryGoods[i] = nbt.getInt("mystery_good_" + i);
				}
			}
			if(baseTag.contains("update_good_tick")) {
				this.updateGoodTick = baseTag.getInt("update_good_tick");
			}
		}
	}
	
	public OtherStats getOtherStats() {
		return this.otherStats;
	}

	protected Optional<ServerPlayerEntity> getServerPlayerOpt(){
		if(this.player instanceof ServerPlayerEntity){
			return Optional.ofNullable((ServerPlayerEntity) this.player);
		}
		return Optional.empty();
	}
	
}
