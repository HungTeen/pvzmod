package com.hungteen.pvz.command;

import com.hungteen.pvz.PVZMod;
import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.command.CommandSource;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = PVZMod.MOD_ID)
public class PVZCommandHandler {

	@SubscribeEvent
    public static void init(RegisterCommandsEvent event) {
        CommandDispatcher<CommandSource> dispatcher = event.getDispatcher();
        ResourceCommand.register(dispatcher);
        PlantLvlCommand.register(dispatcher);
        InvasionCommand.register(dispatcher);
    }
    
}
