package com.hungteen.pvz.common.capability.player;


import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.api.PVZAPI;
import com.hungteen.pvz.api.types.IPAZType;
import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.client.gui.search.SearchOption;
import com.hungteen.pvz.common.advancement.trigger.MoneyTrigger;
import com.hungteen.pvz.common.advancement.trigger.PlantLevelTrigger;
import com.hungteen.pvz.common.advancement.trigger.SunAmountTrigger;
import com.hungteen.pvz.common.advancement.trigger.TreeLevelTrigger;
import com.hungteen.pvz.common.container.shop.MysteryShopContainer;
import com.hungteen.pvz.common.event.events.PlayerLevelUpEvent.PAZLevelUpEvent;
import com.hungteen.pvz.common.event.events.PlayerLevelUpEvent.TreeLevelUpEvent;
import com.hungteen.pvz.common.event.handler.PlayerEventHandler;
import com.hungteen.pvz.common.impl.PlantType;
import com.hungteen.pvz.common.item.spawn.card.SummonCardItem;
import com.hungteen.pvz.common.network.PVZPacketHandler;
import com.hungteen.pvz.common.network.toclient.CardInventoryPacket;
import com.hungteen.pvz.common.network.toclient.PlantStatsPacket;
import com.hungteen.pvz.common.network.toclient.PlayerStatsPacket;
import com.hungteen.pvz.common.world.invasion.WaveManager;
import com.hungteen.pvz.utils.PAZUtil;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.enums.Resources;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.network.PacketDistributor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PlayerDataManager {

	private final PlayerEntity player;
	/* player resources */
	private final HashMap<Resources, Integer> resources = new HashMap<>(Resources.values().length);
	/* summon card inventory */
	private final List<ItemStack> cards = new ArrayList<>();
	private int emptySlot;
	/* plant & zombie level */
	private final Map<IPAZType, Integer> pazLevel = new HashMap<>();
	private final Map<IPAZType, Integer> pazXp = new HashMap<>();
	/* plant & zombie cd */
	private final Map<IPAZType, Integer> pazCardCD = new HashMap<>();
	private final Map<IPAZType, Float> pazCardBar = new HashMap<>();
	/* plant & zombie lock */
	private final Map<IPAZType, Boolean> pazLocked = new HashMap<>();
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
				this.pazLevel.put(plant, 1);
				this.pazXp.put(plant, 0);
				this.pazCardCD.put(plant, 0);
				this.pazCardBar.put(plant, 0F);
				this.pazLocked.put(plant, true);
			});
		}
		this.otherStats = new OtherStats(this);
	}
	
	/**
	 * read {@link PlayerDataStorage#readNBT(net.minecraftforge.common.capabilities.Capability, IPlayerDataCapability, net.minecraft.util.Direction, net.minecraft.nbt.INBT)}.
	 */
	public void loadFromNBT(CompoundNBT baseTag) {
		{// load player resources.
			if(baseTag.contains("player_stats")) {//old
				CompoundNBT statsTag = baseTag.getCompound("player_stats");
			    for(Resources res:Resources.values()) {
			    	if(statsTag.contains("player_"+res.toString())) {
				        this.resources.put(res, statsTag.getInt("player_"+res.toString()));
			    	}
			    }
			}
			if(baseTag.contains("player_resources")) {//new
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
		{// load plants & zombies level.
			/*
			 * remain to keep old version's plant level.
			 */
			PlantType.getPlants().forEach(plant -> {
				final CompoundNBT plantTag = (CompoundNBT) baseTag.get(plant.toString());
				if(plantTag != null) {
				    if(plantTag.contains("player_plant_lvl")) {
					    this.pazLevel.put(plant, plantTag.getInt("player_plant_lvl"));
				    }
				    if(plantTag.contains("player_plant_exp")) {
					    this.pazXp.put(plant, plantTag.getInt("player_plant_exp"));
				    }
				}
			});
			/*
			 * new one.
			 */
			if(baseTag.contains("paz_level_info")) {
			    final CompoundNBT nbt = baseTag.getCompound("paz_level_info");
				PVZAPI.get().getPAZs().forEach(plant -> {
				    final CompoundNBT plantTag = (CompoundNBT) nbt.get(plant.getIdentity());
				    if(plantTag != null) {
				        if(plantTag.contains("paz_lvl")) {
					        this.pazLevel.put(plant, plantTag.getInt("paz_lvl"));
				        }
				        if(plantTag.contains("paz_xp")) {
					        this.pazXp.put(plant, plantTag.getInt("paz_xp"));
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
		otherStats.loadFromNBT(baseTag);
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
		{// save plant & zombie level.
			final CompoundNBT nbt = new CompoundNBT();
			PVZAPI.get().getPAZs().forEach(plant -> {
		        final CompoundNBT plantNBT = new CompoundNBT();
				plantNBT.putInt("paz_lvl", this.getPAZLevel(plant));
				plantNBT.putInt("paz_xp", this.getPAZXp(plant));
				nbt.put(plant.getIdentity(), plantNBT);
			});
			baseTag.put("paz_level_info", nbt);
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
		otherStats.saveToNBT(baseTag);
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
			this.setResource(Resources.KILL_COUNT, 0);
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
		{// plants level.
			for (IPlantType plant : PlantType.getPlants()) {
		       this.sendPAZLevelPacket(player, plant);
	        }
		}
		{// plant item cd.
			for (IPlantType plant : PlantType.getPlants()) {
			    if(plant.getSummonCard().isPresent()) {
			        player.getCooldowns().addCooldown(plant.getSummonCard().get(), this.getPlantCardCD(plant));
			    }
			}
		}
		//almanacs
		SearchOption.OPTION.forEach((a) -> {
			ServerPlayerEntity serverplayer = (ServerPlayerEntity) player;
			if(PlayerUtil.isAlmanacUnlocked(serverplayer, a)) {
				PlayerUtil.unLockAlmanac(serverplayer, a);
			}
		});
		
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
		switch (res) {
		case TREE_XP:{
			addTreeXp(now, num);
			break;
		}
		case SUN_NUM:{
			now = MathHelper.clamp(now + num, 0, PlayerUtil.getPlayerMaxSunNum(resources.get(Resources.TREE_LVL)));
			resources.put(Resources.SUN_NUM, now);
			SunAmountTrigger.INSTANCE.trigger((ServerPlayerEntity) player, now);
			break;
		}
		case ENERGY_NUM:{
			now = MathHelper.clamp(now + num, 0, resources.get(Resources.MAX_ENERGY_NUM));
			resources.put(Resources.ENERGY_NUM, now);
			break;
		}
		default:
			now = MathHelper.clamp(resources.get(res) + num, res.min, res.max);
			resources.put(res, now);
			if(res == Resources.TREE_LVL) {
				this.addResource(Resources.SUN_NUM, 0);
				TreeLevelTrigger.INSTANCE.trigger((ServerPlayerEntity) player, now);
			} else if(res == Resources.MONEY) {
				MoneyTrigger.INSTANCE.trigger((ServerPlayerEntity) player, now);
			}
			break;
		}
		this.sendResourcePacket(player, res);
	}
	
	/**
	 * add tree xp and level up.
	 */
	private void addTreeXp(int now, int num) {
		int lvl = resources.get(Resources.TREE_LVL);
		if(num > 0) {
			int req = PlayerUtil.getPlayerLevelUpXp(lvl);
			while(lvl < Resources.TREE_LVL.max && num + now >= req) {
				num -= req - now;
				this.addResource(Resources.TREE_LVL, 1);
				MinecraftForge.EVENT_BUS.post(new TreeLevelUpEvent(player, ++ lvl));
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
	
	public void sendResourcePacket(PlayerEntity player, Resources res){
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
	
	public void sendInventoryPacket(PlayerEntity player, int type, CompoundNBT nbt) {
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
	 * Operation about Plant Level.
	 */
	
	public void addPAZLevel(IPAZType plant, int lvl){
		lvl = MathHelper.clamp(this.getPAZLevel(plant) + lvl, 1, plant.getMaxLevel());
		this.pazLevel.put(plant, lvl);
		PlantLevelTrigger.INSTANCE.trigger((ServerPlayerEntity) player, lvl);
		this.sendPAZLevelPacket(player, plant);
	}
	
	public void addPAZXp(IPAZType plant, int num) {
		final int maxLvl = plant.getMaxLevel();
		int lvl = this.getPAZLevel(plant);
		int xp = this.getPAZXp(plant) + num;
		if(num > 0) {
			int needXp = PAZUtil.getPAZLevelUpXp(plant, lvl);
			while(lvl < maxLvl && xp >= needXp) {
				xp -= needXp;
				this.addPAZLevel(plant, 1);
				MinecraftForge.EVENT_BUS.post(new PAZLevelUpEvent(player, plant, ++ lvl));
				needXp = PAZUtil.getPAZLevelUpXp(plant, lvl);
			}
			if(lvl == maxLvl) xp = 0;
		} else {
			while(lvl > 1 && xp < 0) {
				this.addPAZLevel(plant, - 1);
				lvl = this.getPAZLevel(plant);
				int needXp = PAZUtil.getPAZLevelUpXp(plant, lvl);
				xp += needXp;
			}
			if(lvl == 1 && xp < 0) {
				xp = 0;
			}
		}
		this.pazXp.put(plant, xp);
		this.sendPAZLevelPacket(player, plant);
	}
	
	public int getPAZLevel(IPAZType plant){
		return this.pazLevel.get(plant);
	}
	
	public int getPAZXp(IPAZType plant){
		return this.pazXp.get(plant);
	}
	
	public void sendPAZLevelPacket(PlayerEntity player, IPAZType plant){
		if (player instanceof ServerPlayerEntity) {
			PVZPacketHandler.CHANNEL.send(
				PacketDistributor.PLAYER.with(()->{
					return (ServerPlayerEntity) player;
				}),
				new PlantStatsPacket(plant.getIdentity(), pazLevel.get(plant), pazXp.get(plant))
			);
		}
	}
	
	/*
	 * Operation about plant card CD.
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
		this.pazLocked.put(plant, is);
	}
	
	public boolean isPAZLocked(IPAZType plant) {
		return this.pazLocked.getOrDefault(plant, true);
	}
	
	public final class OtherStats{
		
		@SuppressWarnings("unused")
		private final PlayerDataManager manager;
		public int[] zombieWaveTime = new int[WaveManager.MAX_WAVE_NUM];
		public int[] mysteryGoods = new int[MysteryShopContainer.MAX_MYSTERY_GOOD];
		public int totalWaveCount;
		public int playSoundTick;
		public int updateGoodTick;
		public int lightLevel;
		
		public OtherStats(PlayerDataManager manager) {
			this.manager = manager;
			for(int i = 0; i < zombieWaveTime.length; ++ i) {
				zombieWaveTime[i] = 0;
			}
			this.totalWaveCount = 0;
			this.playSoundTick = 0;
			this.lightLevel = 0;
//			MysteryShopContainer.genNextGoods(player);
		}
		
		private void saveToNBT(CompoundNBT baseTag) {
			CompoundNBT statsNBT = new CompoundNBT();
			for(int i = 0; i < zombieWaveTime.length; ++ i) {
				statsNBT.putInt("zombieWaveTime_" + i, zombieWaveTime[i]);
			}
			baseTag.put("zombie_wave_time", statsNBT);
			baseTag.putInt("total_wave_cnt", this.totalWaveCount);
			baseTag.putInt("base_sound_tick", this.playSoundTick);
			CompoundNBT tmp = new CompoundNBT();
			for(int i = 0; i < mysteryGoods.length; ++ i) {
				tmp.putInt("mystery_good_" + i, mysteryGoods[i]);
			}
			baseTag.put("mystery_goods", tmp);
			baseTag.putInt("update_good_tick", this.updateGoodTick);
		}

		private void loadFromNBT(CompoundNBT baseTag) {
			if(baseTag.contains("zombie_wave_time")) {
				CompoundNBT nbt = baseTag.getCompound("zombie_wave_time");
				for(int i = 0; i < zombieWaveTime.length; ++ i) {
					zombieWaveTime[i] = nbt.getInt("zombieWaveTime_" + i);
				}
			}
			if(baseTag.contains("total_wave_cnt")) {
				this.totalWaveCount = baseTag.getInt("total_wave_cnt");
			}
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
	
}
