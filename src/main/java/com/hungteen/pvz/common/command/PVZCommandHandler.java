package com.hungteen.pvz.common.command;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;
import net.minecraftforge.event.RegisterCommandsEvent;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-10 17:39
 **/
public class PVZCommandHandler {

    public static void init(RegisterCommandsEvent event) {
        CommandDispatcher<CommandSourceStack> dispatcher = event.getDispatcher();
        PlayerStatsCommand.register(dispatcher);
//        PAZCommand.register(dispatcher);
//        InvasionCommand.register(dispatcher);
//        ChallengeCommand.register(dispatcher);
    }

}
