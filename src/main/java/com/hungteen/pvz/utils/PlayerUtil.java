package com.hungteen.pvz.utils;

import com.hungteen.pvz.capability.CapabilityHandler;
import com.hungteen.pvz.capability.player.IPlayerDataCapability;
import com.hungteen.pvz.gui.almanac.Almanac;
import com.hungteen.pvz.network.AlmanacUnLockPacket;
import com.hungteen.pvz.network.PVZPacketHandler;
import com.hungteen.pvz.network.PlaySoundPacket;
import com.hungteen.pvz.utils.enums.Plants;
import com.hungteen.pvz.utils.enums.Resources;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.play.server.STitlePacket;
import net.minecraft.stats.Stats;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.network.PacketDistributor;

public class PlayerUtil {

	public static final int MAX_TREE_LVL = 100;
	public static final int MAX_ENERGY_NUM = 10;
	public static final int MAX_MONEY = 9999999;
	public static final int MAX_SLOT_NUM = 162;
	public static final int[] TREE_LVL_XP = new int[] {10,25,45,75,120,180,250,350,480,600,750,900,1080,1300,1600,2000,2500,3200,4000,5000}; 
	
	public static int getPlayerMaxSunNum(int lvl){
		if(lvl <= 40) {
			return 900 + lvl * 100;
		} else if(lvl <= 99) {
			return lvl * 250 - 5100;
		}
		return 19999;
	}
	
	public static int getPlayerWaveCount(int lvl) {
		if(lvl <= 10) return 1;
		if(lvl <= 25) return 2;
		if(lvl <= 40) return 3;
		if(lvl <= 60) return 4;
		return 5;
	}
	
	public static int getPlayerLevelUpXp(int lvl){
		int pos = lvl / 5;
		if(lvl >= MAX_TREE_LVL) {
			return 1000000000;
		}
		return TREE_LVL_XP[pos];
	}
	
	public static void addPlayerStats(PlayerEntity player ,Resources res,int num){
		player.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent((l)->{
			l.getPlayerData().getPlayerStats().addPlayerStats(res, num);
		});
	}
	
	public static void addPlantLvl(PlayerEntity player, Plants plant, int num) {
		player.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent((l)->{
			l.getPlayerData().getPlantStats().addPlantLevel(plant, num);
		});
	}
	
	public static void addPlantXp(PlayerEntity player, Plants plant, int num) {
		player.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent((l)->{
			l.getPlayerData().getPlantStats().addPlantXp(plant, num);
		});
	}
	
	public static void clonePlayerData(PlayerEntity oldPlayer, PlayerEntity newPlayer) {
		LazyOptional<IPlayerDataCapability> oldCap = oldPlayer.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY);
		LazyOptional<IPlayerDataCapability> newCap = newPlayer.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY);
		if(oldCap.isPresent()&&newCap.isPresent()) {
			newCap.ifPresent((l)->{
				oldCap.ifPresent((r)->{
					l.getPlayerData().cloneFromExistingPlayerData(r.getPlayerData());
				});
			});
		}
	}
	
	public static void unLockAlmanac(PlayerEntity player, Almanac a) {
//		player.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent((l)->{
//		    PlayerDataManager.AlmanacStats stats = l.getPlayerData().getAlmanacStats();
//		    if(!stats.isAlmanacUnLocked(a)) {
//			    stats.setAlmanacUnLocked(a, true);
//		    }
//	    });
		if (player instanceof ServerPlayerEntity) {
			PVZPacketHandler.CHANNEL.send(
				PacketDistributor.PLAYER.with(() -> {
					return (ServerPlayerEntity) player;
				}),
				new AlmanacUnLockPacket(a.ordinal(), true)
			);
		}
	}
	
	public static boolean isAlmanacUnlocked(ServerPlayerEntity player, Almanac a) {
		if(a.isPlayer()) return true;
		if(a.isPlant()) {
			Plants plant = a.getPlant().get();
			int amount = player.getStats().getValue(Stats.ITEM_USED.get(PlantUtil.getPlantSummonCard(plant)));
			return amount > 0;
		}
		return false;
	}
	
	public static void playClientSound(PlayerEntity player, int id) {
		PVZPacketHandler.CHANNEL.send(PacketDistributor.PLAYER.with(()->{
			return (ServerPlayerEntity) player;
		}), new PlaySoundPacket(id));
	}

	public static void sendTitleToPlayer(PlayerEntity player, ITextComponent text) {
		if(player instanceof ServerPlayerEntity) {
	         ((ServerPlayerEntity) player).connection.sendPacket(new STitlePacket(STitlePacket.Type.TITLE, text));
		}
	}
	
	public static void sendSubTitleToPlayer(PlayerEntity player, ITextComponent text) {
		if(player instanceof ServerPlayerEntity) {
			 sendTitleToPlayer(player, new StringTextComponent(""));
	         ((ServerPlayerEntity) player).connection.sendPacket(new STitlePacket(STitlePacket.Type.SUBTITLE, text));
		}
	}
	
	public static boolean isPlayerSurvival(PlayerEntity player) {
		return ! player.isCreative() && ! player.isSpectator();
	}
	
}
