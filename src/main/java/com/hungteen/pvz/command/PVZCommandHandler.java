package com.hungteen.pvz.command;

import com.hungteen.pvz.PVZMod;
import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.command.CommandSource;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;

@Mod.EventBusSubscriber(modid=PVZMod.MOD_ID)
public class PVZCommandHandler {

	@SubscribeEvent
    public static void onServerStaring(FMLServerStartingEvent event) {
        CommandDispatcher<CommandSource> dispatcher = event.getCommandDispatcher();
        ResourceCommand.register(dispatcher);
        PlantLvlCommand.register(dispatcher);
    }
}
