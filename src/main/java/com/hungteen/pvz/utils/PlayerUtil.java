package com.hungteen.pvz.utils;

import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.api.enums.PVZGroupType;
import com.hungteen.pvz.api.types.base.IPAZType;
import com.hungteen.pvz.common.capability.CapabilityHandler;
import com.hungteen.pvz.common.capability.player.IPVZPlayerCapability;
import com.hungteen.pvz.common.capability.player.PlayerDataManager;
import com.hungteen.pvz.common.entity.EntityGroupHandler;
import com.hungteen.pvz.common.item.spawn.card.SummonCardItem;
import com.hungteen.pvz.common.network.PVZPacketHandler;
import com.hungteen.pvz.common.network.PlaySoundPacket;
import com.hungteen.pvz.utils.enums.Resources;
import net.minecraft.Util;
import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.protocol.game.ClientboundSetSubtitleTextPacket;
import net.minecraft.network.protocol.game.ClientboundSetTitleTextPacket;
import net.minecraft.server.commands.TitleCommand;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.network.PacketDistributor;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-10 16:11
 **/
public class PlayerUtil {

    private static int OLD_PLAYER_LEVEL = 0;
    private static int CACHE_MAX_SUN = 0;

    public static Optional<PlayerDataManager> getOptManager(Player player) {
        return Optional.ofNullable(getManager(player));
    }

    @Nullable
    public static PlayerDataManager getManager(Player player) {
        if(isValidPlayer(player)) {
            final IPVZPlayerCapability cap = CapabilityHandler.getPlayerData(player).orElse(null);
            return cap != null ? cap.get() : null;
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

    public static void addResource(Player player, Resources res, int num) {
        getOptManager(player).ifPresent(m -> {
            m.addResource(res, num);
        });
    }

    public static void setItemStackCD(Player player, ItemStack stack, int cd) {
        if(stack.getItem() instanceof SummonCardItem){
            PlayerUtil.getOptManager(player).ifPresent(l -> l.saveSummonCardCD((SummonCardItem) stack.getItem(), cd));
        }
        player.getCooldowns().addCooldown(stack.getItem(), cd);
    }

    public static void setPAZLock(Player player, IPAZType plant, boolean is) {
        PlayerUtil.getOptManager(player).ifPresent(l -> l.setPAZLocked(plant, is));
    }

    public static boolean isPAZLocked(Player player, IPAZType plant) {
        final PlayerDataManager manager = getManager(player);
        return manager != null ? manager.isPAZLocked(plant) : true;
    }

    /**
     * get player's group.
     */
    public static PVZGroupType getPlayerGroupType(ServerPlayer player) {
        return EntityGroupHandler.getPlayerGroup(player);
    }

    public static void playClientSound(Player player, SoundEvent ev) {
        if(ev != null) {
            PVZPacketHandler.sendToClient((ServerPlayer) player, new PlaySoundPacket(ev.getRegistryName().toString()));
        }
    }

    public static void sendTitleToPlayer(Player player, Component text) {
        if(player instanceof ServerPlayer) {
            ((ServerPlayer) player).connection.send(new ClientboundSetTitleTextPacket(text));
        }
    }

    public static void sendSubTitleToPlayer(Player player, Component text) {
        if(player instanceof ServerPlayer) {
            sendTitleToPlayer(player, new TextComponent(""));
            ((ServerPlayer) player).connection.send(new ClientboundSetSubtitleTextPacket(text));
        }
    }

    public static void sendMsgToAll(Level world, Component text) {
        world.getServer().getPlayerList().getPlayers().forEach(player -> {
            sendMsgTo(player, text);
        });
    }

    /**
     * send a system chat message to player.
     */
    public static void sendMsgTo(Player player, Component text) {
        player.sendMessage(text, Util.NIL_UUID);
    }

    /**
     * display some tips to player in the middle bar.
     */
    public static void sendTipTo(Player player, Component text) {
        player.displayClientMessage(text, true);
    }

    /**
     * get all players in the server.
     */
    public static List<ServerPlayer> getServerPlayers(Level world){
        return world.getServer().getPlayerList().getPlayers();
    }

    /**
     * player is in survival mode for special judgement.
     */
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
