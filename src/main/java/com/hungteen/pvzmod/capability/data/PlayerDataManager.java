package com.hungteen.pvzmod.capability.data;

import java.util.HashMap;

import com.hungteen.pvzmod.packet.PacketHandler;
import com.hungteen.pvzmod.packet.PacketPlantLvlData;
import com.hungteen.pvzmod.packet.PacketPlayerData;
import com.hungteen.pvzmod.util.PlantsUtil;
import com.hungteen.pvzmod.util.PlayerUtil;
import com.hungteen.pvzmod.util.enums.Plants;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;

public class PlayerDataManager {

	private final EntityPlayer player;
	private final PlayerStats playerStats;
	private final PlantStats plantStats;
	
	public PlayerDataManager(EntityPlayer player) {
		this.player = player;
		this.playerStats = new PlayerStats(this);
		this.plantStats=new PlantStats(this);
	}
	
//	public void tickPlayer()
//	{
//		if (player == null || player.isSpectator() || player.world.isRemote)
//			return;
//		if (player instanceof EntityPlayerMP) 
//			PacketHandler.CHANNEL.sendTo(new PacketPlayerData(stats.getPlayerLevel(),stats.getPlayerXp(),stats.getPlayerSunNum(),stats.getPlayerEnergyNum(),stats.getPlayerMoney()),(EntityPlayerMP) player);
//	}
	
	public NBTTagCompound saveToNBT() {
		NBTTagCompound baseTag = new NBTTagCompound();
		playerStats.saveToNBT(baseTag);
		plantStats.saveToNBT(baseTag);
		return baseTag;
	}

	public void loadFromNBT(NBTTagCompound baseTag) {
		playerStats.loadFromNBT(baseTag);
		plantStats.loadFromNBT(baseTag);
	}
	
	public void cloneFromExistingPlayerData(PlayerDataManager sourcePlayerData) {

		for (Plants plant : Plants.values()) {
			this.plantStats.plantLevel.put(plant, sourcePlayerData.plantStats.plantLevel.get(plant));
			this.plantStats.plantXp.put(plant, sourcePlayerData.plantStats.plantXp.get(plant));
			this.plantStats.isPlantLocked.put(plant,sourcePlayerData.plantStats.getIsPlantLocked(plant));
		}
		this.playerStats.playerEnergyNum=sourcePlayerData.playerStats.playerEnergyNum;
		this.playerStats.playerLevel=sourcePlayerData.playerStats.playerLevel;
		this.playerStats.playerMoney=sourcePlayerData.playerStats.playerMoney;
		this.playerStats.playerSunNum=sourcePlayerData.playerStats.playerSunNum;
		this.playerStats.playerXp=sourcePlayerData.playerStats.playerXp;
	}

	public final class PlayerStats{
		private final PlayerDataManager playerDataManager;
		private int playerMoney;  //钱
		private int playerSunNum; //阳光数
		private int playerEnergyNum; //能量豆数
		private int playerLevel;  //玩家智慧树等级
		private int playerXp;   //玩家智慧树经验
		
		private PlayerStats(PlayerDataManager dataManager) {
			this.playerDataManager = dataManager;
			this.playerSunNum=0;
			this.playerEnergyNum=0;
			this.playerMoney=0;
			this.playerLevel=1;
			this.playerXp=0;
		}
		
		//get
		public int getPlayerLevel()
		{
			return this.playerLevel;
		}
		
		public int getPlayerXp()
		{
			return this.playerXp;
		}
		
		public int getPlayerSunNum()
		{
			return this.playerSunNum;
		}
		
		public int getPlayerMoney()
		{
			return this.playerMoney;
		}
		
		public int getPlayerEnergyNum()
		{
			return this.playerEnergyNum;
		}
		
		//add
		public void addPlayerLevel(int lvl)
		{
			this.playerLevel+=lvl;
			if(this.playerLevel>=100) {
				this.playerLevel=100;
			}
			this.sendPacket(player);
		}
		
		public void addPlayerXp(int xp)
		{
			int lvl=this.getPlayerLevel();
			if(xp>0) {//+
				this.playerXp+=xp;
				int needXp=PlayerUtil.getPlayerLevelUpXp(lvl);
				while(this.playerXp>=needXp){//可升级
					this.playerXp-=needXp;
					this.addPlayerLevel(1);
					lvl=this.getPlayerLevel();
					needXp=PlayerUtil.getPlayerLevelUpXp(lvl);
				}
			}
			else {//-
				this.playerXp-=xp;
				while(this.playerXp<0) {
					if(lvl==1) {
						this.playerXp=0;
						break;
					}
					this.addPlayerLevel(-1);
					this.playerXp+=PlayerUtil.getPlayerLevelUpXp(lvl);
				}
			}
			this.sendPacket(player);
		}
		
		public void addPlayerSunNum(int num)
		{
//			System.out.println(num);
			int lvl=this.getPlayerLevel();
			int now=this.playerSunNum+num;
			int maxSunNum=PlayerUtil.getPlayerMaxSunNum(lvl);
			if(now>maxSunNum) {
				now=maxSunNum;
			}
			else if(now<0) {
				now=0;
			}
//			System.out.println(now);
			this.playerSunNum=now;
			this.sendPacket(player);
		}
		
		public void addPlayerMoney(int m)
		{
			this.playerMoney+=m;
			if(this.playerMoney>=9999999) {//1e7
				this.playerMoney=9999999;
			}
			this.sendPacket(player);
		}
		
		public void addPlayerEnergyNum(int num)
		{
			int lvl=this.getPlayerLevel();
			int maxNum=PlayerUtil.getPlayerMaxEnergyNum(lvl);
			int now=this.playerEnergyNum+num;
			if(now>maxNum) now=maxNum;
			else if(now<0) now=0;
			this.playerEnergyNum=now;
			this.sendPacket(player);
		}
		
		private void sendPacket(EntityPlayer player)
		{
			if (player instanceof EntityPlayerMP) 
				PacketHandler.CHANNEL.sendTo(new PacketPlayerData(this.playerLevel,this.playerXp,this.playerSunNum,this.playerEnergyNum,this.playerMoney),(EntityPlayerMP) player);
		}
		
		private void saveToNBT(NBTTagCompound baseTag) {
			NBTTagCompound playerNBT = new NBTTagCompound();
			playerNBT.setInteger("PlayerLevelxx",this.playerLevel);
			playerNBT.setInteger("PlayerExpxx", this.playerXp);
			playerNBT.setInteger("PlayerMoneyxx", this.playerMoney);
			playerNBT.setInteger("PlayerSunNumxx", this.playerSunNum);
			playerNBT.setInteger("PlayerEnergyNumxx",this.playerEnergyNum);
			baseTag.setTag("PLAYERLEVELxx", playerNBT);
			
		}

		private void loadFromNBT(NBTTagCompound baseTag) {
			NBTTagCompound playerTag = baseTag.getCompoundTag("PLAYERLEVELxx");
			this.playerLevel=playerTag.getInteger("PlayerLevelxx");
			this.playerXp=playerTag.getInteger("PlayerExpxx");
			this.playerMoney=playerTag.getInteger("PlayerMoneyxx");
			this.playerSunNum=playerTag.getInteger("PlayerSunNumxx");
			this.playerEnergyNum=playerTag.getInteger("PlayerEnergyNumxx");
		}
	}
	
	public final class PlantStats
	{
		private final PlayerDataManager playerDataManager;
		private HashMap<Plants, Integer> plantXp= new HashMap<Plants, Integer>(Plants.values().length);
		private HashMap<Plants, Integer> plantLevel = new HashMap<Plants, Integer>(Plants.values().length);
		private HashMap<Plants, Boolean> isPlantLocked = new HashMap<Plants, Boolean>(Plants.values().length);
		
		private PlantStats(PlayerDataManager dataManager) {
			this.playerDataManager=dataManager;
			for (Plants plant : Plants.values()) {
				plantXp.put(plant, 0);
				plantLevel.put(plant, 1);
				isPlantLocked.put(plant, true);
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
		
		public boolean getIsPlantLocked(Plants plant)
		{
			return this.isPlantLocked.get(plant);
		}
		
		//add
		public void addPlantLevel(Plants plant,int lvl)
		{
			int now=this.getPlantLevel(plant)+lvl;
			int maxLvl=PlantsUtil.getPlantMaxLvl(plant);
			if(now>maxLvl) now=maxLvl;
			else if(now<1) now=1;
			this.plantLevel.put(plant, now);
			this.sendPlantPacket(player, plant);
		}
		
		public void addPlantXp(Plants plant,int num)
		{
			int lvl=this.getPlantLevel(plant);
			int xp=this.getPlantXp(plant)+num;
			if(num>0) {
				int needXp=PlantsUtil.getPlantLvlUpXp(lvl);
				while(xp>=needXp) {
					xp-=needXp;
					this.addPlantLevel(plant, 1);
					lvl=this.getPlantLevel(plant);
					needXp=PlantsUtil.getPlantLvlUpXp(lvl);
				}
			}
			else {
				while(xp<0) {
					if(lvl==1) {
						xp=0;
						break;
					}
					this.addPlantLevel(plant, -1);
					lvl=this.getPlantLevel(plant);
					int needXp=PlantsUtil.getPlantLvlUpXp(lvl);
					xp+=needXp;
				}
			}
			this.plantXp.put(plant, xp);
			this.sendPlantPacket(player, plant);
		}
		
		public void setIsPlantLocked(Plants plant ,boolean is)
		{
			this.isPlantLocked.put(plant, is);
			this.sendPlantPacket(player, plant);
		}
		
		private void sendPlantPacket(EntityPlayer player,Plants plant)
		{
			if (player instanceof EntityPlayerMP) 
				PacketHandler.CHANNEL.sendTo(new PacketPlantLvlData(plant, this.getPlantLevel(plant), this.getPlantXp(plant),this.getIsPlantLocked(plant)),(EntityPlayerMP) player);
		}
		
		private void saveToNBT(NBTTagCompound baseTag) {
			for (Plants plant : Plants.values()) {
				NBTTagCompound plantNBT = new NBTTagCompound();

				plantNBT.setInteger("PlantLevelxx", this.getPlantLevel(plant));
				plantNBT.setInteger("PlantExpxx", this.getPlantXp(plant));
				plantNBT.setBoolean("PlantLockedxx", this.getIsPlantLocked(plant));

				baseTag.setTag(plant.toString(), plantNBT);
			}
		}

		private void loadFromNBT(NBTTagCompound baseTag) {
			for (Plants plant : Plants.values()) {
				NBTTagCompound plantTag = baseTag.getCompoundTag(plant.toString());

				this.plantLevel.put(plant, plantTag.getInteger("PlantLevelxx"));
				this.plantXp.put(plant, plantTag.getInteger("PlantExpxx"));
				this.isPlantLocked.put(plant,plantTag.getBoolean("PlantLockedxx"));
			}
		}
	}
	
	public EntityPlayer getPlayer() {
		return this.player;
	}
	
	public PlayerStats getPlayerStats() {
		return this.playerStats;
	}
	
	public PlantStats getPlantStats()
	{
		return this.plantStats;
	}
}
