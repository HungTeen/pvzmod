package com.hungteen.pvz.common.datapack;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.common.impl.plant.PlantType;
import com.hungteen.pvz.common.network.PVZPacketHandler;
import com.hungteen.pvz.common.network.toclient.DatapackPacket;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.fml.network.PacketDistributor;

public class PVZDataPackManager {

    /**
     * {@link PVZMod#PVZMod()}
     */
    public static void addReloadListenerEvent(AddReloadListenerEvent event) {
        event.addListener(new PlantType.PlantTypeLoader());
        event.addListener(new LotteryTypeLoader());
        event.addListener(new InvasionTypeLoader());
        event.addListener(new ChallengeTypeLoader());
        event.addListener(new TransactionTypeLoader());
    }

    public static void sendSyncPacketsTo(PlayerEntity player){
        if(player instanceof ServerPlayerEntity) {
            LotteryTypeLoader.JSONS.entrySet().forEach(entry -> {
                PVZPacketHandler.CHANNEL.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) player), new DatapackPacket(LotteryTypeLoader.NAME, entry.getKey().toString(), entry.getValue().toString()));
            });

            TransactionTypeLoader.JSONS.entrySet().forEach(entry -> {
                PVZPacketHandler.CHANNEL.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) player), new DatapackPacket(TransactionTypeLoader.NAME, entry.getKey().toString(), entry.getValue().toString()));
            });

            ChallengeTypeLoader.JSONS.entrySet().forEach(entry -> {
                PVZPacketHandler.CHANNEL.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) player), new DatapackPacket(ChallengeTypeLoader.NAME, entry.getKey().toString(), entry.getValue().toString()));
            });
        }
    }

}
