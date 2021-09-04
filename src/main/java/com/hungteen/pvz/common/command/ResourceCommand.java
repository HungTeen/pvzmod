package com.hungteen.pvz.common.command;

import java.util.Collection;

import com.hungteen.pvz.common.capability.CapabilityHandler;
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
        LiteralArgumentBuilder<CommandSource> builder = Commands.literal("playerstats").requires((ctx) -> {return ctx.hasPermission(2);});
        for(Resources res:Resources.values()) {
        	builder.then(Commands.argument("targets", EntityArgument.players())
        	    .then(Commands.literal("add").then(Commands.literal(res.toString().toLowerCase()).then(Commands.argument("amount", IntegerArgumentType.integer()).executes((command)->{
    			    return addPlayerResource(command.getSource(), EntityArgument.getPlayers(command, "targets"), res, IntegerArgumentType.getInteger(command, "amount"));
    		    }))))
        	    .then(Commands.literal("query").then(Commands.literal(res.toString().toLowerCase()).executes((command)->{
    			    return queryPlayerResource(command.getSource(), EntityArgument.getPlayers(command, "targets"), res);
    		    })))
        	    .then(Commands.literal("set").then(Commands.literal(res.toString().toLowerCase()).then(Commands.argument("amount", IntegerArgumentType.integer()).executes((command)->{
    			    return setPlayerResource(command.getSource(), EntityArgument.getPlayers(command, "targets"), res, IntegerArgumentType.getInteger(command, "amount"));
    		    }))))
            );
        }
        dispatcher.register(builder);
    }
	
	public static int addPlayerResource(CommandSource source,Collection<? extends ServerPlayerEntity> targets,Resources res,int num) {
		for(ServerPlayerEntity player:targets) {
			player.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent(l -> {
				l.getPlayerData().addResource(res, num);
		    	source.sendSuccess(new StringTextComponent(res.getText().getString() + ":" + l.getPlayerData().getResource(res)), true);
		    });
		}
		return targets.size();
	}
	
	public static int queryPlayerResource(CommandSource source,Collection<? extends ServerPlayerEntity> targets,Resources res) {
		for(ServerPlayerEntity player:targets) {
			player.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent(l -> {
		    	source.sendSuccess(new StringTextComponent(res.getText().getString() + ":" + l.getPlayerData().getResource(res)), false);
		    });
		}
		return targets.size();
	}
	
	public static int setPlayerResource(CommandSource source, Collection<? extends ServerPlayerEntity> targets, Resources res, int num) {
		for(ServerPlayerEntity player:targets) {
			player.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent(l -> {
				l.getPlayerData().setResource(res, num);
		    	source.sendSuccess(new StringTextComponent(res.getText().getString() + ":" + l.getPlayerData().getResource(res)), true);
		    });
		}
		return targets.size();
	}
	
}
