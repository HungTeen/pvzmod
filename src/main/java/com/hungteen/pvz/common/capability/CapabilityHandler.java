package com.hungteen.pvz.common.capability;

import com.hungteen.pvz.common.capability.player.IPVZPlayerCapability;
import com.hungteen.pvz.common.capability.player.PVZPlayerCapProvider;
import com.hungteen.pvz.common.capability.player.PlayerDataManager;
import com.hungteen.pvz.utils.Util;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-10 12:07
 **/
public class CapabilityHandler {

    public static void registerCapabilities(RegisterCapabilitiesEvent event){
        event.register(IPVZPlayerCapability.class);
    }

    public static void attachCapabilities(AttachCapabilitiesEvent<Entity> event){
        if(event.getObject() instanceof Player){
            event.addCapability(Util.prefix("player_data"), new PVZPlayerCapProvider((Player) event.getObject()));
        }
    }

    public static LazyOptional<IPVZPlayerCapability> getPlayerData(Player player){
        return player.getCapability(PVZPlayerCapProvider.PVZ_PLAYER_CAP);
    }

}
