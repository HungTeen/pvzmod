package com.hungteen.pvz.capabilities.player;


import java.util.HashMap;

import com.hungteen.pvz.network.AlmanacUnLockPacket;
import com.hungteen.pvz.network.PVZPacketHandler;
import com.hungteen.pvz.network.PlantStatsPacket;
import com.hungteen.pvz.network.PlayerStatsPacket;
import com.hungteen.pvz.utils.PlantUtil;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.enums.Almanacs;
import com.hungteen.pvz.utils.enums.Plants;
import com.hungteen.pvz.utils.enums.Resources;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.network.PacketDistributor;


public class PlayerDataManager {

	private final PlayerEntity player;
	private final PlayerStats playerStats;
	private final PlantStats plantStats;
	private final AlmanacStats almanacStats;
	
	public PlayerDataManager(PlayerEntity player) {
		this.player = player;
		this.playerStats = new PlayerStats(this);
		this.plantStats = new PlantStats(this);
		this.almanacStats = new AlmanacStats(this);
	}
	
//	public void tickPlayer()
//	{
//		if (player == null || player.isSpectator() || player.world.isRemote)
//			return;
//		if (player instanceof EntityPlayerMP) 
//			PacketHandler.CHANNEL.sendTo(new PacketPlayerData(stats.getPlayerLevel(),stats.getPlayerXp(),stats.getPlayerSunNum(),stats.getPlayerEnergyNum(),stats.getPlayerMoney()),(EntityPlayerMP) player);
//	}
	
	public CompoundNBT saveToNBT() {
		CompoundNBT baseTag = new CompoundNBT();
		playerStats.saveToNBT(baseTag);
		plantStats.saveToNBT(baseTag);
		almanacStats.saveToNBT(baseTag);
		return baseTag;
	}

	public void loadFromNBT(CompoundNBT baseTag) {
		playerStats.loadFromNBT(baseTag);
		plantStats.loadFromNBT(baseTag);
		almanacStats.loadFromNBT(baseTag);
	}
//	
	public void cloneFromExistingPlayerData(PlayerDataManager data) {
		//Resources
		for(Resources res:Resources.values()) {
			this.playerStats.resources.put(res,data.playerStats.resources.get(res));
		}
		//Plants
		for (Plants plant : Plants.values()) {
			this.plantStats.plantLevel.put(plant, data.plantStats.plantLevel.get(plant));
			this.plantStats.plantXp.put(plant, data.plantStats.plantXp.get(plant));
		}
		//Inventory
		for(int i=0;i<PlayerUtil.MAX_SLOT_NUM;i++) {
			this.playerStats.setItemStack(i, data.playerStats.getItemStack(i));
		}
		//Almanac
		for(Almanacs a:Almanacs.values()) {
			this.almanacStats.setAlmanacUnLocked(a, data.almanacStats.isAlmanacUnLocked(a));
		}
	}

	public final class PlayerStats{
		@SuppressWarnings("unused")
		private final PlayerDataManager playerDataManager;
		private HashMap<Resources, Integer> resources = new HashMap<>(Resources.values().length);
		private final Inventory inventory = new Inventory(PlayerUtil.MAX_SLOT_NUM);
		
		private PlayerStats(PlayerDataManager dataManager) {
			this.playerDataManager = dataManager;
			for(Resources res:Resources.values()) {
				if(res==Resources.TREE_LVL) resources.put(res, 1);
				else if(res==Resources.MAX_ENERGY_NUM) resources.put(res, 1);
				else if(res==Resources.SUN_NUM) resources.put(res, 50);
				else if(res==Resources.SLOT_NUM) resources.put(res, 18);
				else resources.put(res,0);
			}
		}
		
		public int getPlayerStats(Resources res){
			return resources.get(res);
		}
		
		public ItemStack getItemStack(int pos) {
			return this.inventory.getStackInSlot(pos);
		}
		
		public Inventory getInventory() {
			return this.inventory;
		}
		
		public void setItemStack(int pos, ItemStack stack) {
			this.inventory.setInventorySlotContents(pos, stack);
		}
		
		public void addPlayerStats(Resources res,int num){
			switch (res) {
			case TREE_LVL:{
				addTreeLvl(num);
				break;
			}
			case TREE_XP:{
				addTreeXp(num);
				break;
			}
			case MONEY:{
				addMoney(num);
				break;
			}
			case SUN_NUM:{
				addSunNum(num);
				break;
			}
			case ENERGY_NUM:{
				addEnergyNum(num);
				break;
			}
			case MAX_ENERGY_NUM:{
				int now = MathHelper.clamp(resources.get(Resources.MAX_ENERGY_NUM)+num, 1, PlayerUtil.MAX_ENERGY_NUM);
				resources.put(Resources.MAX_ENERGY_NUM, now);
			}
			case SLOT_NUM:{
				int now = MathHelper.clamp(resources.get(Resources.SLOT_NUM)+num, 18, PlayerUtil.MAX_SLOT_NUM);
				resources.put(Resources.SLOT_NUM, now);
			}
			default:
				break;
			}
			this.sendPacket(player,res);
		}
		
		private void addEnergyNum(int num){
			int now=resources.get(Resources.ENERGY_NUM);
			int max=resources.get(Resources.MAX_ENERGY_NUM);
			if(num>0) now=Math.min(max,now+num);
			else now=Math.max(0, now+num);
			resources.put(Resources.ENERGY_NUM, now);
		}
		
		private void addMoney(int num){
			int now=resources.get(Resources.MONEY);
			if(num>0) now=now+num;
			else now=Math.max(0, now+num);
			resources.put(Resources.MONEY, now);
		}
		
		private void addTreeXp(int num){
			int lvl=resources.get(Resources.TREE_LVL);
			int now=resources.get(Resources.TREE_XP);
			if(num>0) {
				int req=PlayerUtil.getPlayerLevelUpXp(lvl);
				while(lvl<PlayerUtil.MAX_TREE_LVL&&num+now>=req) {
					num-=req-now;
					addTreeLvl(1);
					lvl++;
					now=0;
					req=PlayerUtil.getPlayerLevelUpXp(lvl);
				}
				resources.put(Resources.TREE_XP, num+now);
			}else {
				while(lvl>1&&num>now) {
					num-=now;
					lvl--;
					now=PlayerUtil.getPlayerLevelUpXp(lvl);
					addTreeLvl(-1);
				}
				resources.put(Resources.TREE_XP, now-num);
			}
		}
		
		private void addSunNum(int num){
			int now=resources.get(Resources.SUN_NUM);
			int lvl=resources.get(Resources.TREE_LVL);
			if(num>0) now=Math.min(PlayerUtil.getPlayerMaxSunNum(lvl),now+num);
			else now=Math.max(0, now+num);
			resources.put(Resources.SUN_NUM, now);
		}
		
		private void addTreeLvl(int num){
			int now=resources.get(Resources.TREE_LVL);
			if(num>0) now=Math.min(PlayerUtil.MAX_TREE_LVL, now+num);
			else now=Math.max(1, now+num);
			resources.put(Resources.TREE_LVL, now);
		}
		
		public void sendPacket(PlayerEntity player,Resources res){
			if (player instanceof ServerPlayerEntity) {
//				System.out.println(res.toString()+" "+resources.get(res));
				PVZPacketHandler.CHANNEL.send(
					PacketDistributor.PLAYER.with(()->{
						return (ServerPlayerEntity) player;
					}),
					new PlayerStatsPacket(res.ordinal(),resources.get(res))
				);
			}
		}
		
		private void saveToNBT(CompoundNBT baseTag) {
			CompoundNBT statsNBT = new CompoundNBT();
			for(Resources res:Resources.values()) {
				statsNBT.putInt("player_"+res.toString(), resources.get(res));
			}
			baseTag.put("player_stats", statsNBT);
			ListNBT list = new ListNBT();
			for(int i=0;i<PlayerUtil.MAX_SLOT_NUM;i++) {
				if(!this.inventory.getStackInSlot(i).isEmpty()) {
					CompoundNBT nbt = new CompoundNBT();
					nbt.putInt("Slot", i);
					this.inventory.getStackInSlot(i).write(nbt);
					list.add(nbt);
				}
			}
			baseTag.put("Inventory", list);
		}

		private void loadFromNBT(CompoundNBT baseTag) {
			if(baseTag.contains("player_stats")) {
				CompoundNBT statsTag = baseTag.getCompound("player_stats");
			    for(Resources res:Resources.values()) {
			    	if(statsTag.contains("player_"+res.toString())) {
				        resources.put(res, statsTag.getInt("player_"+res.toString()));
			    	}
			    }
			}
			if(baseTag.contains("Inventory")) {
				ListNBT list = (ListNBT) baseTag.get("Inventory");
				for(int i=0;i<list.size();i++) {
					CompoundNBT nbt = list.getCompound(i);
					int pos = nbt.getInt("Slot");
					ItemStack stack = ItemStack.read(nbt);
					if(!stack.isEmpty()) {
						this.inventory.setInventorySlotContents(pos, stack);
					}
				}
			}
		}
		
	}
	
	public final class PlantStats{
		@SuppressWarnings("unused")
		private final PlayerDataManager playerDataManager;
		private HashMap<Plants, Integer> plantXp= new HashMap<Plants, Integer>(Plants.values().length);
		private HashMap<Plants, Integer> plantLevel = new HashMap<Plants, Integer>(Plants.values().length);
//		private HashMap<Plants, Boolean> isPlantLocked = new HashMap<Plants, Boolean>(Plants.values().length);
		
		private PlantStats(PlayerDataManager dataManager) {
			this.playerDataManager=dataManager;
			for (Plants plant : Plants.values()) {
				plantXp.put(plant, 0);
				plantLevel.put(plant, 1);
//				isPlantLocked.put(plant, true);
			}
		}
		
		//get		
		public int getPlantLevel(Plants plant)
		{
			return this.plantLevel.get(plant);
		}
		
		public int getPlantXp(Plants plant)
		{
			return this.plantXp.get(plant);
		}
		
//		public boolean getIsPlantLocked(Plants plant)
//		{
//			return this.isPlantLocked.get(plant);
//		}
		
		//add
		public void addPlantLevel(Plants plant,int lvl){
			int now=this.getPlantLevel(plant)+lvl;
			int maxLvl=PlantUtil.getPlantMaxLvl(plant);
			if(now>maxLvl) now=maxLvl;
			else if(now<1) now=1;
			this.plantLevel.put(plant, now);
			this.sendPlantPacket(player, plant);
		}
		
		public void addPlantXp(Plants plant,int num){
			int lvl=this.getPlantLevel(plant);
			int xp=this.getPlantXp(plant)+num;
			if(num>0) {
				int needXp=PlantUtil.getPlantLevelUpXp(plant,lvl);
				int maxLvl=PlantUtil.getPlantMaxLvl(plant);
				while(lvl<maxLvl&&xp>=needXp) {
					xp-=needXp;
					this.addPlantLevel(plant, 1);
					lvl=this.getPlantLevel(plant);
					needXp=PlantUtil.getPlantLevelUpXp(plant,lvl);
				}
				if(lvl==maxLvl) {
					xp=0;
				}
			}
			else {
				while(lvl>1&&xp<0) {
					this.addPlantLevel(plant, -1);
					lvl=this.getPlantLevel(plant);
					int needXp=PlantUtil.getPlantLevelUpXp(plant,lvl);
					xp+=needXp;
				}
				if(lvl==1&&xp<0) xp=0;
			}
			this.plantXp.put(plant, xp);
			this.sendPlantPacket(player, plant);
		}
		
//		public void setIsPlantLocked(Plants plant ,boolean is)
//		{
//			this.isPlantLocked.put(plant, is);
//			this.sendPlantPacket(player, plant);
//		}
		
		public void sendPlantPacket(PlayerEntity player,Plants plant){
			if (player instanceof ServerPlayerEntity) {
//				System.out.println(res.toString()+" "+resources.get(res));
				PVZPacketHandler.CHANNEL.send(
					PacketDistributor.PLAYER.with(()->{
						return (ServerPlayerEntity) player;
					}),
					new PlantStatsPacket(plant.ordinal(),plantLevel.get(plant),plantXp.get(plant))
				);
			}
		}
		
		private void saveToNBT(CompoundNBT baseTag) {
			for (Plants plant : Plants.values()) {
				CompoundNBT plantNBT = new CompoundNBT();

				plantNBT.putInt("plant_lvl", this.getPlantLevel(plant));
				plantNBT.putInt("plant_exp", this.getPlantXp(plant));
//				plantNBT.setBoolean("PlantLockedxx", this.getIsPlantLocked(plant));
				
				baseTag.put(plant.toString(), plantNBT);
			}
		}

		private void loadFromNBT(CompoundNBT baseTag) {
			for (Plants plant : Plants.values()) {
				CompoundNBT plantTag = (CompoundNBT) baseTag.get(plant.toString());
				if(plantTag==null) {
					continue;
				}
				this.plantLevel.put(plant, plantTag.getInt("plant_lvl"));
				this.plantXp.put(plant, plantTag.getInt("plant_exp"));
//				this.isPlantLocked.put(plant,plantTag.getBoolean("PlantLockedxx"));
			}
		}
	}
	
	public final class AlmanacStats {
		@SuppressWarnings("unused")
		private final PlayerDataManager manager;
		private HashMap<Almanacs, Boolean> unLock = new HashMap<>(Almanacs.values().length);
		
		public AlmanacStats(PlayerDataManager manager) {
			this.manager = manager;
			for(Almanacs a:Almanacs.values()) {
				unLock.put(a, false);
			}
		}
		
		public boolean isAlmanacUnLocked(Almanacs a) {
			return this.unLock.get(a);
		}
		
		public void setAlmanacUnLocked(Almanacs a, boolean is) {
			this.unLock.put(a, is);
			this.sendAlmanacPacket(player, a);
		}
		
		public void sendAlmanacPacket(PlayerEntity player,Almanacs a){
			if (player instanceof ServerPlayerEntity) {
				PVZPacketHandler.CHANNEL.send(
					PacketDistributor.PLAYER.with(()->{
						return (ServerPlayerEntity) player;
					}),
					new AlmanacUnLockPacket(a.ordinal(), this.isAlmanacUnLocked(a))
				);
			}
		}
		
		private void saveToNBT(CompoundNBT baseTag) {
			CompoundNBT statsNBT = new CompoundNBT();
			for(Almanacs a:Almanacs.values()) {
				statsNBT.putBoolean("lock_"+a.toString().toLowerCase(), unLock.get(a));
			}
			baseTag.put("almanacs_lock", statsNBT);
		}

		private void loadFromNBT(CompoundNBT baseTag) {
			if(baseTag.contains("almanacs_lock")) {
			    CompoundNBT statsTag = baseTag.getCompound("almanacs_lock");
			    for(Almanacs a:Almanacs.values()) {
			    	if(statsTag.contains("lock_"+a.toString().toLowerCase())) {
				        unLock.put(a, statsTag.getBoolean("lock_"+a.toString().toLowerCase()));
			    	}
			    }
			}
		}
		
	}
	
	public PlayerStats getPlayerStats() {
		return this.playerStats;
	}
	
	public PlantStats getPlantStats(){
		return this.plantStats;
	}
	
	public AlmanacStats getAlmanacStats() {
		return this.almanacStats;
	}
	
}
