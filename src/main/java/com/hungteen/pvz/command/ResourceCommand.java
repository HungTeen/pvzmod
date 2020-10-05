package com.hungteen.pvz.command;

import java.util.Collection;

import com.hungteen.pvz.capability.CapabilityHandler;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.enums.Resources;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;

import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.StringTextComponent;

public class ResourceCommand {

	public static void register(CommandDispatcher<CommandSource> dispatcher) {
        LiteralArgumentBuilder<CommandSource> builder = Commands.literal("playerstats").requires((ctx) -> {return ctx.hasPermissionLevel(2);});
        for(Resources res:Resources.values()) {
        	builder.then(Commands.argument("targets", EntityArgument.players())
        	    .then(Commands.literal("add").then(Commands.literal(res.toString().toLowerCase()).then(Commands.argument("amount", IntegerArgumentType.integer()).executes((command)->{
    			    return addPlayerResource(command.getSource(), EntityArgument.getPlayers(command, "targets"), res, IntegerArgumentType.getInteger(command, "amount"));
    		    }))))
        	    .then(Commands.literal("query").then(Commands.literal(res.toString().toLowerCase()).executes((command)->{
    			    return queryPlayerResource(command.getSource(), EntityArgument.getPlayers(command, "targets"), res);
    		    })))
            );
        }
        dispatcher.register(builder);
    }
	
	public static int addPlayerResource(CommandSource source,Collection<? extends ServerPlayerEntity> targets,Resources res,int num) {
		for(ServerPlayerEntity player:targets) {
		    PlayerUtil.addPlayerStats(player, res, num);
		}
		return targets.size();
	}
	
	public static int queryPlayerResource(CommandSource source,Collection<? extends ServerPlayerEntity> targets,Resources res) {
		for(ServerPlayerEntity player:targets) {
			player.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent((l)->{
		    	source.sendFeedback(new StringTextComponent(""+l.getPlayerData().getPlayerStats().getPlayerStats(res)), true);
		    });
		}
		return targets.size();
	}
	
}
