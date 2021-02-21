package com.hungteen.pvz.command;

import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.command.CommandSource;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;

public class PVZCommandHandler {

    public static void init(FMLServerStartingEvent event) {
        CommandDispatcher<CommandSource> dispatcher = event.getCommandDispatcher();
        ResourceCommand.register(dispatcher);
        PlantLvlCommand.register(dispatcher);
        InvasionCommand.register(dispatcher);
    }
}
