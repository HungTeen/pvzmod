package com.hungteen.pvz.common.capability.player;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.client.gui.search.SearchOption;
import com.hungteen.pvz.common.advancement.trigger.MoneyTrigger;
import com.hungteen.pvz.common.advancement.trigger.PlantLevelTrigger;
import com.hungteen.pvz.common.advancement.trigger.SunAmountTrigger;
import com.hungteen.pvz.common.advancement.trigger.TreeLevelTrigger;
import com.hungteen.pvz.common.container.shop.MysteryShopContainer;
import com.hungteen.pvz.common.core.PlantType;
import com.hungteen.pvz.common.event.events.PlayerLevelUpEvent.PlantLevelUpEvent;
import com.hungteen.pvz.common.event.events.PlayerLevelUpEvent.TreeLevelUpEvent;
import com.hungteen.pvz.common.event.handler.PlayerEventHandler;
import com.hungteen.pvz.common.item.spawn.card.SummonCardItem;
import com.hungteen.pvz.common.network.PVZPacketHandler;
import com.hungteen.pvz.common.network.toclient.CardInventoryPacket;
import com.hungteen.pvz.common.network.toclient.PlantStatsPacket;
import com.hungteen.pvz.common.network.toclient.PlayerStatsPacket;
import com.hungteen.pvz.common.world.invasion.WaveManager;
import com.hungteen.pvz.utils.PlantUtil;
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


public class PlayerDataManager {

	private final PlayerEntity player;
	/* player resources */
	private HashMap<Resources, Integer> resources = new HashMap<>(Resources.values().length);
	/* summon card inventory */
	private final List<ItemStack> cards = new ArrayList<>();
	private int emptySlot;
	/* plant level */
	private Map<PlantType, Integer> plantLevel = new HashMap<>();
	private Map<PlantType, Integer> plantXp = new HashMap<>();
	/* plant cd */
	private Map<PlantType, Integer> plantCardCD = new HashMap<>();
	private Map<PlantType, Float> plantCardBar = new HashMap<>();
	/* plant lock */
	private Map<PlantType, Boolean> plantLocked = new HashMap<>();
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
//				this.setItemAt(ItemStack.EMPTY, i);
				this.cards.add(ItemStack.EMPTY);
//				this.cards.add(Bundles.RANDOM_ALL.getEnjoyCard(player.getRandom()));
			}
		}
		{// init about plants.
			PlantType.getPlants().forEach(plant -> {
			    this.plantXp.put(plant, 0);
				this.plantLevel.put(plant, 1);
				this.plantCardCD.put(plant, 0);
				this.plantCardBar.put(plant, 0F);
				this.plantLocked.put(plant, true);
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
		{// load plant level.
			/*
			 * remain to keep old version's plant level.
			 */
			PlantType.getPlants().forEach(plant -> {
				final CompoundNBT plantTag = (CompoundNBT) baseTag.get(plant.toString());
				if(plantTag != null) {
				    if(plantTag.contains("player_plant_lvl")) {
					    this.plantLevel.put(plant, plantTag.getInt("player_plant_lvl"));
				    }
				    if(plantTag.contains("player_plant_exp")) {
					    this.plantXp.put(plant, plantTag.getInt("player_plant_exp"));
				    }
				}
			});
			/*
			 * new.
			 */
			if(baseTag.contains("plant_level_info")) {
			    final CompoundNBT nbt = baseTag.getCompound("plant_level_info");
			    PlantType.getPlants().forEach(plant -> {
				    final CompoundNBT plantTag = (CompoundNBT) nbt.get(plant.getIdentity());
				    if(plantTag != null) {
				        if(plantTag.contains("player_plant_lvl")) {
					        this.plantLevel.put(plant, plantTag.getInt("player_plant_lvl"));
				        }
				        if(plantTag.contains("player_plant_exp")) {
					        this.plantXp.put(plant, plantTag.getInt("player_plant_exp"));
				        }
				    }
			    });
			}
		}
		{// load plant card cd.
			if(baseTag.contains("plant_card_item_cd")) {
			    final CompoundNBT nbt = baseTag.getCompound("plant_card_item_cd");
			    PlantType.getPlants().forEach(plant -> {
					final CompoundNBT plantNBT = (CompoundNBT) nbt.get(plant.getIdentity());
					if(plantNBT != null) {
					    if(plantNBT.contains("plant_card_cd")) {
						    this.setPlantCardCD(plant, plantNBT.getInt("plant_card_cd"));
					    }
					    if(plantNBT.contains("plant_card_bar")) {
						    this.setPlantCardBar(plant, plantNBT.getInt("plant_card_bar"));
					    }
					}
				});
			}
		}
		{// load plant lock.
			if(baseTag.contains("plant_locks")) {
			    final CompoundNBT nbt = baseTag.getCompound("plant_locks");
			    PlantType.getPlants().forEach(plant -> {
					final CompoundNBT plantNBT = (CompoundNBT) nbt.get(plant.getIdentity());
					if(plantNBT != null) {
					    if(plantNBT.contains("plant_locked")) {
						    this.setPlantLocked(plant, plantNBT.getBoolean("plant_locked"));
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
		{// save plant level.
			final CompoundNBT nbt = new CompoundNBT();
			PlantType.getPlants().forEach(plant -> {
		        final CompoundNBT plantNBT = new CompoundNBT();
				plantNBT.putInt("player_plant_lvl", this.getPlantLevel(plant));
				plantNBT.putInt("player_plant_exp", this.getPlantXp(plant));
				nbt.put(plant.getIdentity(), plantNBT);
			});
			baseTag.put("plant_level_info", nbt);
		}
		{// save plant card cd.
			final CompoundNBT nbt = new CompoundNBT();
			for(PlantType plant : PlantType.getPlants()) {
				final CompoundNBT plantNBT = new CompoundNBT();
				nbt.putInt("plant_card_cd", this.plantCardCD.get(plant));
				nbt.putFloat("plant_card_bar", this.plantCardBar.get(plant));
				nbt.put(plant.getIdentity(), plantNBT);
			}
			baseTag.put("plant_card_item_cd", nbt);
		}
		{// save plant lock.
			final CompoundNBT nbt = new CompoundNBT();
			for(PlantType plant : PlantType.getPlants()) {
				final CompoundNBT plantNBT = new CompoundNBT();
				nbt.putBoolean("plant_locked", this.isPlantLocked(plant));
				nbt.put(plant.getIdentity(), plantNBT);
			}
			baseTag.put("plant_locks", nbt);
		}
		otherStats.saveToNBT(baseTag);
		return baseTag;
	}
//	
	public void cloneFromExistingPlayerData(PlayerDataManager data, boolean died) {
		this.loadFromNBT(data.saveToNBT());
		if(died) {
			if(! PVZConfig.COMMON_CONFIG.RuleSettings.KeepSunWhenDie.get()) {
				this.setResource(Resources.SUN_NUM, 50);
			}
		}
//		//other
//		for(int i = 0; i < WaveManager.MAX_WAVE_NUM; ++ i) {
//			this.otherStats.zombieWaveTime[i] = data.otherStats.zombieWaveTime[i];
//		}
//		this.otherStats.totalWaveCount = data.otherStats.totalWaveCount;
//		this.otherStats.playSoundTick = data.otherStats.playSoundTick;
//		for(int i = 0; i < MysteryShopContainer.MAX_MYSTERY_GOOD; ++ i) {
//			this.otherStats.mysteryGoods[i] = data.otherStats.mysteryGoods[i];
//		}
//		this.otherStats.updateGoodTick = data.otherStats.updateGoodTick;
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
			for (PlantType plant : PlantType.getPlants()) {
		       this.sendPlantLevelPacket(player, plant);
	        }
		}
		{// plant item cd.
			for (PlantType plant : PlantType.getPlants()) {
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
	 * {@link #addPlayerStats(Resources, int)}
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
	
	public void addPlantLevel(PlantType plant, int lvl){
		lvl = MathHelper.clamp(this.getPlantLevel(plant) + lvl, 1, plant.getMaxLevel());
		this.plantLevel.put(plant, lvl);
		PlantLevelTrigger.INSTANCE.trigger((ServerPlayerEntity) player, lvl);
		this.sendPlantLevelPacket(player, plant);
	}
	
	public void addPlantXp(PlantType plant, int num) {
		final int maxLvl = plant.getMaxLevel();
		int lvl = this.getPlantLevel(plant);
		int xp = this.getPlantXp(plant) + num;
		if(num > 0) {
			int needXp = PlantUtil.getPlantLevelUpXp(plant, lvl);
			while(lvl < maxLvl && xp >= needXp) {
				xp -= needXp;
				this.addPlantLevel(plant, 1);
				MinecraftForge.EVENT_BUS.post(new PlantLevelUpEvent(player, plant, ++ lvl));
				needXp = PlantUtil.getPlantLevelUpXp(plant, lvl);
			}
			if(lvl == maxLvl) xp = 0;
		} else {
			while(lvl > 1 && xp < 0) {
				this.addPlantLevel(plant, - 1);
				lvl = this.getPlantLevel(plant);
				int needXp = PlantUtil.getPlantLevelUpXp(plant, lvl);
				xp += needXp;
			}
			if(lvl == 1 && xp < 0) {
				xp = 0;
			}
		}
		this.plantXp.put(plant, xp);
		this.sendPlantLevelPacket(player, plant);
	}
	
	public int getPlantLevel(PlantType plant){
		return this.plantLevel.get(plant);
	}
	
	public int getPlantXp(PlantType plant){
		return this.plantXp.get(plant);
	}
	
	public void sendPlantLevelPacket(PlayerEntity player, PlantType plant){
		if (player instanceof ServerPlayerEntity) {
			PVZPacketHandler.CHANNEL.send(
				PacketDistributor.PLAYER.with(()->{
					return (ServerPlayerEntity) player;
				}),
				new PlantStatsPacket(plant.getId(), plantLevel.get(plant), plantXp.get(plant))
			);
		}
	}
	
	/*
	 * Operation about plant card CD.
	 */
	
	public void setPlantCardCD(PlantType plant, int tick) {
		this.plantCardCD.put(plant, tick);
	}
	
	public int getPlantCardCoolDown(PlantType plant) {
		return this.plantCardCD.get(plant);
	}
	
	public void setPlantCardBar(PlantType plant, float bar) {
		this.plantCardBar.put(plant, bar);
	}
	
	public float getPlantCardBarLength(PlantType plant) {
		return this.plantCardBar.get(plant);
	}
	
	public int getPlantCardCD(PlantType plant) {
		return (int) (this.plantCardBar.get(plant) * this.plantCardCD.get(plant));
	}

	/*
	 * Operation about plant lock.
	 */
	
	public void setPlantLocked(PlantType plant, boolean is) {
		this.plantLocked.put(plant, is);
	}
	
	public boolean isPlantLocked(PlantType plant) {
		return this.plantLocked.getOrDefault(plant, true);
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
