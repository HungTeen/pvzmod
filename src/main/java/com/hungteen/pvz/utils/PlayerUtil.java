package com.hungteen.pvz.utils;

import com.hungteen.pvz.api.enums.PVZGroupType;
import com.hungteen.pvz.api.types.IPAZType;
import com.hungteen.pvz.common.capability.CapabilityHandler;
import com.hungteen.pvz.common.capability.player.IPlayerDataCapability;
import com.hungteen.pvz.common.capability.player.PlayerDataManager;
import com.hungteen.pvz.common.entity.EntityGroupHander;
import com.hungteen.pvz.common.item.spawn.card.SummonCardItem;
import com.hungteen.pvz.common.network.PVZPacketHandler;
import com.hungteen.pvz.common.network.toclient.PlaySoundPacket;
import com.hungteen.pvz.common.world.invasion.Invasion;
import com.hungteen.pvz.utils.enums.Resources;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.play.server.STitlePacket;
import net.minecraft.util.Util;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.fml.network.PacketDistributor;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

public class PlayerUtil {

	public static final int[] TREE_LVL_XP = new int[] {100, 250, 500, 1000, 2000, 4000, 5000, 6000, 7000, 8000, 10000, 12000, 15000, 18000, 21000, 25000, 30000, 350000, 40000, 50000};
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
		return pos < TREE_LVL_XP.length ? TREE_LVL_XP[pos] : 100000;
	}

	@Nullable
	public static Optional<PlayerDataManager> getOptManager(Player player) {
		return Optional.ofNullable(getManager(player));
	}

	@Nullable
	public static PlayerDataManager getManager(Player player) {
		if(isValidPlayer(player)) {
			final IPlayerDataCapability cap = player.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).orElse(null);
		    return cap != null ? cap.getPlayerData() : null;
		}
		return null;
	}
	
	public static void setResource(Player player, Resources res, int num) {
		getOptManager(player).ifPresent(m -> {
			m.setResource(res, num);
		});
	}
	
	public static int getResource(Player player, Resources res) {
		final PlayerDataManager manager = getManager(player);
		return manager != null ? manager.getResource(res) : res.min;
	}
	
	public static int getEmptyPos(Player player) {
		final PlayerDataManager manager = getManager(player);
		return manager != null ? manager.getCurrentPos() : 0;
	}
	
	public static ItemStack getItemStack(Player player, int pos) {
		final PlayerDataManager manager = getManager(player);
		return manager != null ? manager.getItemAt(pos) : ItemStack.EMPTY;
	}
	
	public static void setItemStack(Player player, ItemStack stack, int pos) {
		final PlayerDataManager manager = getManager(player);
		if(manager != null) {
			manager.setItemAt(stack, pos, true);
		}
	}
	
	public static void addResource(Player player, Resources res, int num) {
		if(isValidPlayer(player)) {
			player.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent(l -> {
			    l.getPlayerData().addResource(res, num);
		    });
		}
	}
	
	public static void setItemStackCD(Player player, ItemStack stack, int cd) {
		if(stack.getItem() instanceof SummonCardItem){
			PlayerUtil.getOptManager(player).ifPresent(l -> l.saveSummonCardCD((SummonCardItem) stack.getItem(), cd));
		}
		player.getCooldowns().addCooldown(stack.getItem(), cd);
	}
	
	public static void setPAZLock(Player player, IPAZType plant, boolean is) {
		Optional.ofNullable(getManager(player)).ifPresent(l -> l.setPAZLocked(plant, is));
	}
	
	public static boolean isPAZLocked(Player player, IPAZType plant) {
		final PlayerDataManager manager = getManager(player);
		return manager != null ? manager.isPAZLocked(plant) : true;
	}

	@Nonnull
	public static Invasion getInvasion(Player player) {
		final PlayerDataManager manager = getManager(player);
		return manager != null ? manager.getInvasion() : new Invasion(player);
	}
	
	/**
	 * get player's group.
	 * {@link EntityUtil#getEntityGroup(net.minecraft.world.entity.Entity)}
	 */
	public static PVZGroupType getPlayerGroupType(ServerPlayer player) {
		return EntityGroupHander.getPlayerGroup(player);
	}
	
	public static void playClientSound(Player player, SoundEvent ev) {
		if(ev != null) {
			PVZPacketHandler.CHANNEL.send(PacketDistributor.PLAYER.with(() -> {
				return (ServerPlayer) player;
			}), new PlaySoundPacket(ev.getRegistryName().toString()));
		}
	}

	public static void sendTitleToPlayer(Player player, Component text) {
		if(player instanceof ServerPlayer) {
	         ((ServerPlayer) player).connection.send(new STitlePacket(STitlePacket.Type.TITLE, text));
		}
	}
	
	public static void sendSubTitleToPlayer(Player player, Component text) {
		if(player instanceof ServerPlayer) {
			 sendTitleToPlayer(player, new StringTextComponent(""));
	         ((ServerPlayer) player).connection.send(new STitlePacket(STitlePacket.Type.SUBTITLE, text));
		}
	}
	
	public static void sendMsgToAll(Level world, Component text) {
		world.getServer().getPlayerList().getPlayers().forEach(player -> {
			sendMsgTo(player, text);
		});
	}
	
	public static void sendMsgTo(Player player, Component text) {
		player.sendMessage(text, Util.NIL_UUID);
	}
	
	/**
	 * get all players in the server.
	 */
	public static List<ServerPlayer> getServerPlayers(Level world){
		return world.getServer().getPlayerList().getPlayers();
	}
	
	public static boolean isPlayerSurvival(Player player) {
		return ! player.isCreative() && ! player.isSpectator();
	}
	
	/**
	 * Avoid crash by fake player.
	 */
	public static boolean isValidPlayer(Player player) {
		return player != null && ! (player instanceof FakePlayer);
	}
	
}
