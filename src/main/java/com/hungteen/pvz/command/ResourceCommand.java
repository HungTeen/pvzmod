package com.hungteen.pvz.command;

import java.util.Collection;

import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.enums.Resources;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;

import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.ServerPlayerEntity;

public class ResourceCommand {

	public static void register(CommandDispatcher<CommandSource> dispatcher) {
        LiteralArgumentBuilder<CommandSource> builder = Commands.literal("playerstats").requires((ctx) -> {return ctx.hasPermissionLevel(2);});
        for(Resources res:Resources.values()) {
        	builder.then(Commands.argument("targets", EntityArgument.players()).then(Commands.literal("add")
        			.then(Commands.literal(res.toString().toLowerCase()).then(Commands.argument("amount", IntegerArgumentType.integer()).executes((command)->{
    			return addPlayerResource(command.getSource(), EntityArgument.getPlayers(command, "targets"), res, IntegerArgumentType.getInteger(command, "amount"));
    		})))));
        }
        dispatcher.register(builder);
    }
	
	public static int addPlayerResource(CommandSource source,Collection<? extends ServerPlayerEntity> targets,Resources res,int num) {
		for(ServerPlayerEntity player:targets) {
		    PlayerUtil.addPlayerStats(player, res, num);
		}
		return targets.size();
	}
	
}
