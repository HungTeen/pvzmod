package com.hungteen.pvz.utils;

import java.util.List;

import javax.annotation.Nullable;

import com.hungteen.pvz.api.enums.PVZGroupType;
import com.hungteen.pvz.client.gui.search.SearchOption;
import com.hungteen.pvz.common.capability.CapabilityHandler;
import com.hungteen.pvz.common.capability.player.IPlayerDataCapability;
import com.hungteen.pvz.common.capability.player.PlayerDataManager;
import com.hungteen.pvz.common.core.PlantType;
import com.hungteen.pvz.common.network.PVZPacketHandler;
import com.hungteen.pvz.common.network.toclient.AlmanacUnLockPacket;
import com.hungteen.pvz.common.network.toclient.PlaySoundPacket;
import com.hungteen.pvz.utils.enums.Resources;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.STitlePacket;
import net.minecraft.stats.Stats;
import net.minecraft.util.Util;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.network.PacketDistributor;

public class PlayerUtil {

	public static final int[] TREE_LVL_XP = new int[] {100, 250, 500, 1000, 2000, 4000, 8000, 12500, 18000, 25000, 32000, 40000, 50000, 63000, 75000, 88000, 100000, 120000, 150000, 180000}; 
	private static int OLD_PLAYER_LEVEL = 0;
	private static int CACHE_MAX_SUN = 0;
	
	/**
	 * a1 = 1000, d = 500, inc = 20. <br> 
	 * an = n * a1 + (n - 1) * n / 2.<br>
	 * {@link PlayerDataManager#addResource(Resources, int)}
	 */
	public static int getPlayerMaxSunNum(int lvl) {
		if(lvl == OLD_PLAYER_LEVEL) {
			return CACHE_MAX_SUN;
		} else {
			final int len = 20;
		    final int n = lvl / len;
		    final int sum = 1000 * n + n * (n - 1) / 2 * 500 + (lvl - len * n) * (50 + n * 25);
		    OLD_PLAYER_LEVEL = lvl;
		    return (CACHE_MAX_SUN = ConfigUtil.getBaseSun() + sum);
		}
	}
	
	/**
	 * {@link PlayerDataManager#addResource(Resources, int)}
	 */
	public static int getPlayerLevelUpXp(int lvl){
		final int pos = lvl / 5;
		return pos < TREE_LVL_XP.length ? TREE_LVL_XP[pos] : 200000;
	}
	
	public static int getPlayerWaveCount(int lvl) {
		if(lvl <= 10) return 1;
		if(lvl <= 25) return 2;
		if(lvl <= 40) return 3;
		if(lvl <= 60) return 4;
		return 5;
	}
	
	@Nullable
	public static PlayerDataManager getManager(PlayerEntity player) {
		if(isValidPlayer(player)) {
			final IPlayerDataCapability cap = player.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).orElse(null);
		    return cap != null ? cap.getPlayerData() : null;
		}
		return null;
	}
	
	public static int getResource(PlayerEntity player, Resources res) {
		final PlayerDataManager manager = getManager(player);
		return manager != null ? manager.getResource(res) : 0;
	}
	
	public static int getEmptyPos(PlayerEntity player) {
		final PlayerDataManager manager = getManager(player);
		return manager != null ? manager.getCurrentPos() : 0;
	}
	
	public static ItemStack getItemStack(PlayerEntity player, int pos) {
		final PlayerDataManager manager = getManager(player);
		return manager != null ? manager.getItemAt(pos) : ItemStack.EMPTY;
	}
	
	public static void setItemStack(PlayerEntity player, ItemStack stack, int pos) {
		final PlayerDataManager manager = getManager(player);
		if(manager != null) {
			manager.setItemAt(stack, pos);
		}
	}
	
	public static void addPlayerStats(PlayerEntity player, Resources res, int num) {
		if(isValidPlayer(player)) {
			player.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent(l -> {
			    l.getPlayerData().addResource(res, num);
		    });
		}
	}
	
	public static void addPlantLvl(PlayerEntity player, PlantType plant, int num) {
		if(isValidPlayer(player)) {
		    player.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent(l -> {
			    l.getPlayerData().getPlantStats().addPlantLevel(plant, num);
		    });
		}
	}
	
	public static void addPlantXp(PlayerEntity player, PlantType plant, int num) {
		if(isValidPlayer(player)) {
		    player.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent((l)->{
			    l.getPlayerData().getPlantStats().addPlantXp(plant, num);
		    });
		}
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
	
	public static void unLockAlmanac(PlayerEntity player, SearchOption a) {
		if (player instanceof ServerPlayerEntity) {
			PVZPacketHandler.CHANNEL.send(
				PacketDistributor.PLAYER.with(() -> {
					return (ServerPlayerEntity) player;
				}),
				new AlmanacUnLockPacket(a.ordinal(), true)
			);
		}
	}
	
	public static boolean isAlmanacUnlocked(ServerPlayerEntity player, SearchOption a) {
		if(a.isPlayer()) {
			return true;
		}
		if(a.isPlant() && a.getPlant().get().getSummonCard().isPresent()) {
			PlantType plant = a.getPlant().get();
			int amount = player.getStats().getValue(Stats.ITEM_USED.get(plant.getSummonCard().get()));
			return amount > 0;
		}
		return false;
	}
	
	/**
	 * get player's group.
	 * {@link EntityUtil#getEntityGroup(net.minecraft.entity.Entity)}
	 */
	public static PVZGroupType getPlayerGroupType(ServerPlayerEntity player) {
		final IPlayerDataCapability cap = player.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).orElse(null);
		if(cap != null) {
			return PVZGroupType.getGroup(cap.getPlayerData().getResource(Resources.GROUP_TYPE));
		}
		return PVZGroupType.getGroup(ConfigUtil.getPlayerInitialGroup());// get Group Error !
	}
	
	public static void playClientSound(PlayerEntity player, int id) {
		PVZPacketHandler.CHANNEL.send(PacketDistributor.PLAYER.with(()->{
			return (ServerPlayerEntity) player;
		}), new PlaySoundPacket(id));
	}

	public static void sendTitleToPlayer(PlayerEntity player, ITextComponent text) {
		if(player instanceof ServerPlayerEntity) {
	         ((ServerPlayerEntity) player).connection.send(new STitlePacket(STitlePacket.Type.TITLE, text));
		}
	}
	
	public static void sendSubTitleToPlayer(PlayerEntity player, ITextComponent text) {
		if(player instanceof ServerPlayerEntity) {
			 sendTitleToPlayer(player, new StringTextComponent(""));
	         ((ServerPlayerEntity) player).connection.send(new STitlePacket(STitlePacket.Type.SUBTITLE, text));
		}
	}
	
	public static void sendMsgToAll(World world, ITextComponent text) {
		world.getServer().getPlayerList().getPlayers().forEach(player -> {
			player.sendMessage(text, Util.NIL_UUID);
		});
	}
	
	/**
	 * get all players in the server.
	 */
	public static List<ServerPlayerEntity> getServerPlayers(World world){
		return world.getServer().getPlayerList().getPlayers();
	}
	
	public static boolean isPlayerSurvival(PlayerEntity player) {
		return ! player.isCreative() && ! player.isSpectator();
	}
	
	/**
	 * Avoid crash by fake player.
	 */
	public static boolean isValidPlayer(PlayerEntity player) {
		return ! (player instanceof FakePlayer);
	}
	
}
