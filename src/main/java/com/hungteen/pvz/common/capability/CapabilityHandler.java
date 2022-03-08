package com.hungteen.pvz.common.capability;


import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.common.capability.player.IPlayerDataCapability;
import com.hungteen.pvz.common.capability.player.PlayerDataCapability;
import com.hungteen.pvz.common.capability.player.PlayerDataProvider;
import com.hungteen.pvz.common.capability.player.PlayerDataStorage;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class CapabilityHandler {
	
	@CapabilityInject(IPlayerDataCapability.class)
	public static final Capability<IPlayerDataCapability> PLAYER_DATA_CAPABILITY = null;

	public static void registerCapabilities(){
		CapabilityManager.INSTANCE.register(IPlayerDataCapability.class, new PlayerDataStorage(), PlayerDataCapability::new);
		MinecraftForge.EVENT_BUS.register(CapabilityHandler.class);
	}
	
	@SubscribeEvent
    public static void attachCapability(AttachCapabilitiesEvent<Entity> event){
		Entity entity = event.getObject();
        if (entity instanceof Player){
        	event.addCapability(new ResourceLocation(PVZMod.MOD_ID, "player_data"), new PlayerDataProvider((Player) entity));
        }
    }
}
