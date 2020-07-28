package com.hungteen.pvz.capabilities.player;


import java.util.HashMap;

import com.hungteen.pvz.network.PVZPacketHandler;
import com.hungteen.pvz.network.PacketPlayerStats;
import com.hungteen.pvz.utils.enums.Resources;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.fml.network.PacketDistributor;


public class PlayerDataManager {

	private final PlayerEntity player;
	private final PlayerStats playerStats;
//	private final PlantStats plantStats;
	
	public PlayerDataManager(PlayerEntity player) {
		this.player = player;
		this.playerStats = new PlayerStats(this);
//		this.plantStats=new PlantStats(this);
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
//		plantStats.saveToNBT(baseTag);
		return baseTag;
	}

	public void loadFromNBT(CompoundNBT baseTag) {
		playerStats.loadFromNBT(baseTag);
//		plantStats.loadFromNBT(baseTag);
	}
//	
	public void cloneFromExistingPlayerData(PlayerDataManager data) {

		for(Resources res:Resources.values()) {
			this.playerStats.resources.put(res,data.playerStats.resources.get(res));
		}
//		for (Plants plant : Plants.values()) {
//			this.plantStats.plantLevel.put(plant, sourcePlayerData.plantStats.plantLevel.get(plant));
//			this.plantStats.plantXp.put(plant, sourcePlayerData.plantStats.plantXp.get(plant));
//			this.plantStats.isPlantLocked.put(plant,sourcePlayerData.plantStats.getIsPlantLocked(plant));
//		}
	}

	public final class PlayerStats{
		private final PlayerDataManager playerDataManager;
		private HashMap<Resources, Integer> resources = new HashMap<>(Resources.values().length);
		
		private PlayerStats(PlayerDataManager dataManager) {
			this.playerDataManager = dataManager;
			for(Resources res:Resources.values()) {
				if(res==Resources.TREE_LVL) resources.put(res, 1);
				else resources.put(res,0);
			}
		}
		
		public void addPlayerStats(Resources res,int num)
		{
			switch (res) {
			case TREE_LVL:
				addTreeLvl(num);
				break;

			default:
				break;
			}
			this.sendPacket(player,res);
		}
		
		private void addTreeLvl(int num)
		{
			int now;
			if(num>0) now=Math.min(100, resources.get(Resources.TREE_LVL)+num);
			else now=Math.max(0, resources.get(Resources.TREE_LVL)+num);
			resources.put(Resources.TREE_LVL, now);
		}
		
		public void sendPacket(PlayerEntity player,Resources res)
		{
			if (player instanceof ServerPlayerEntity) {
//				System.out.println(res.toString()+" "+resources.get(res));
				PVZPacketHandler.CHANNEL.send(
					PacketDistributor.PLAYER.with(()->{
						return (ServerPlayerEntity) player;
					}),
					new PacketPlayerStats(res.ordinal(),resources.get(res))
				);
			}
		}
		
		private void saveToNBT(CompoundNBT baseTag) {
			CompoundNBT statsNBT = new CompoundNBT();
			for(Resources res:Resources.values()) {
				statsNBT.putInt("player_"+res.toString(), resources.get(res));
			}
			baseTag.put("player_stats", statsNBT);
		}

		private void loadFromNBT(CompoundNBT baseTag) {
			CompoundNBT statsTag = baseTag.getCompound("player_stats");
			for(Resources res:Resources.values()) {
				resources.put(res, statsTag.getInt("player_"+res.toString()));
			}
		}
	}
	
//	public final class PlantStats
//	{
//		private final PlayerDataManager playerDataManager;
//		private HashMap<Plants, Integer> plantXp= new HashMap<Plants, Integer>(Plants.values().length);
//		private HashMap<Plants, Integer> plantLevel = new HashMap<Plants, Integer>(Plants.values().length);
//		private HashMap<Plants, Boolean> isPlantLocked = new HashMap<Plants, Boolean>(Plants.values().length);
//		
//		private PlantStats(PlayerDataManager dataManager) {
//			this.playerDataManager=dataManager;
//			for (Plants plant : Plants.values()) {
//				plantXp.put(plant, 0);
//				plantLevel.put(plant, 1);
//				isPlantLocked.put(plant, true);
//			}
//		}
//		
//		//get		
//		public int getPlantLevel(Plants plant)
//		{
//			return this.plantLevel.get(plant);
//		}
//		
//		public int getPlantXp(Plants plant)
//		{
//			return this.plantXp.get(plant);
//		}
//		
//		public boolean getIsPlantLocked(Plants plant)
//		{
//			return this.isPlantLocked.get(plant);
//		}
//		
//		//add
//		public void addPlantLevel(Plants plant,int lvl)
//		{
//			int now=this.getPlantLevel(plant)+lvl;
//			int maxLvl=PlantsUtil.getPlantMaxLvl(plant);
//			if(now>maxLvl) now=maxLvl;
//			else if(now<1) now=1;
//			this.plantLevel.put(plant, now);
//			this.sendPlantPacket(player, plant);
//		}
//		
//		public void addPlantXp(Plants plant,int num)
//		{
//			int lvl=this.getPlantLevel(plant);
//			int xp=this.getPlantXp(plant)+num;
//			if(num>0) {
//				int needXp=PlantsUtil.getPlantLvlUpXp(lvl);
//				while(xp>=needXp) {
//					xp-=needXp;
//					this.addPlantLevel(plant, 1);
//					lvl=this.getPlantLevel(plant);
//					needXp=PlantsUtil.getPlantLvlUpXp(lvl);
//				}
//			}
//			else {
//				while(xp<0) {
//					if(lvl==1) {
//						xp=0;
//						break;
//					}
//					this.addPlantLevel(plant, -1);
//					lvl=this.getPlantLevel(plant);
//					int needXp=PlantsUtil.getPlantLvlUpXp(lvl);
//					xp+=needXp;
//				}
//			}
//			this.plantXp.put(plant, xp);
//			this.sendPlantPacket(player, plant);
//		}
//		
//		public void setIsPlantLocked(Plants plant ,boolean is)
//		{
//			this.isPlantLocked.put(plant, is);
//			this.sendPlantPacket(player, plant);
//		}
//		
//		private void sendPlantPacket(EntityPlayer player,Plants plant)
//		{
//			if (player instanceof EntityPlayerMP) 
//				PacketHandler.CHANNEL.sendTo(new PacketPlantLvlData(plant, this.getPlantLevel(plant), this.getPlantXp(plant),this.getIsPlantLocked(plant)),(EntityPlayerMP) player);
//		}
//		
//		private void saveToNBT(NBTTagCompound baseTag) {
//			for (Plants plant : Plants.values()) {
//				NBTTagCompound plantNBT = new NBTTagCompound();
//
//				plantNBT.setInteger("PlantLevelxx", this.getPlantLevel(plant));
//				plantNBT.setInteger("PlantExpxx", this.getPlantXp(plant));
//				plantNBT.setBoolean("PlantLockedxx", this.getIsPlantLocked(plant));
//
//				baseTag.setTag(plant.toString(), plantNBT);
//			}
//		}
//
//		private void loadFromNBT(NBTTagCompound baseTag) {
//			for (Plants plant : Plants.values()) {
//				NBTTagCompound plantTag = baseTag.getCompoundTag(plant.toString());
//
//				this.plantLevel.put(plant, plantTag.getInteger("PlantLevelxx"));
//				this.plantXp.put(plant, plantTag.getInteger("PlantExpxx"));
//				this.isPlantLocked.put(plant,plantTag.getBoolean("PlantLockedxx"));
//			}
//		}
//	}
//	
//	public EntityPlayer getPlayer() {
//		return this.player;
//	}
//	
	public PlayerStats getPlayerStats() {
		return this.playerStats;
	}
	
//	public PlantStats getPlantStats()
//	{
//		return this.plantStats;
//	}
}
