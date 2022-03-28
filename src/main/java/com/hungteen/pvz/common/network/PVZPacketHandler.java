package com.hungteen.pvz.common.network;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.utils.Util;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-10 16:40
 **/
public class PVZPacketHandler {

    private static int id = 0;

    private static SimpleChannel CHANNEL;

    public static void init() {

        CHANNEL = NetworkRegistry.ChannelBuilder
                .named(Util.prefix("networking"))
                .networkProtocolVersion(() -> "1.0")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();

        CHANNEL.registerMessage(getId(), PlayerStatsPacket.class, PlayerStatsPacket::encode, PlayerStatsPacket::new, PlayerStatsPacket.Handler::onMessage);
        CHANNEL.registerMessage(getId(), PlaySoundPacket.class, PlaySoundPacket::encode, PlaySoundPacket::new, PlaySoundPacket.Handler::onMessage);
//        CHANNEL.registerMessage(id ++, OpenGuiPacket.class, OpenGuiPacket::encode, OpenGuiPacket::new, OpenGuiPacket.Handler::onMessage);
//        CHANNEL.registerMessage(id ++, ClickButtonPacket.class, ClickButtonPacket::encode, ClickButtonPacket::new, ClickButtonPacket.Handler::onMessage);
//        CHANNEL.registerMessage(id ++, SpawnParticlePacket.class, SpawnParticlePacket::encode, SpawnParticlePacket::new, SpawnParticlePacket.Handler::onMessage);
//        CHANNEL.registerMessage(id ++, OtherStatsPacket.class, OtherStatsPacket::encode, OtherStatsPacket::new, OtherStatsPacket.Handler::onMessage);
//        CHANNEL.registerMessage(id ++, UpdateMotionPacket.class, UpdateMotionPacket::encode, UpdateMotionPacket::new, UpdateMotionPacket.Handler::onMessage);
//        CHANNEL.registerMessage(id ++, EntityInteractPacket.class, EntityInteractPacket::encode, EntityInteractPacket::new, EntityInteractPacket.Handler::onMessage);
//        CHANNEL.registerMessage(id ++, PVZMouseScrollPacket.class, PVZMouseScrollPacket::encode, PVZMouseScrollPacket::new, PVZMouseScrollPacket.Handler::onMessage);
//        CHANNEL.registerMessage(id ++, CardInventoryPacket.class, CardInventoryPacket::encode, CardInventoryPacket::new, CardInventoryPacket.Handler::onMessage);
        CHANNEL.registerMessage(id ++, PAZStatsPacket.class, PAZStatsPacket::encode, PAZStatsPacket::new, PAZStatsPacket.Handler::onMessage);
//        CHANNEL.registerMessage(id ++, DatapackPacket.class, DatapackPacket::encode, DatapackPacket::new, DatapackPacket.Handler::onMessage);

    }

    public static <MSG> void sendToClient(ServerPlayer serverPlayer, MSG msg){
        CHANNEL.send(PacketDistributor.PLAYER.with(() -> serverPlayer), msg);
    }

    private static int getId(){
        return id ++;
    }

}
