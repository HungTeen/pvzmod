package com.hungteen.pvz.command;

import java.util.Collection;

import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.enums.Resources;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;

import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.ServerPlayerEntity;

public class ResourceCommand {

	public static void register(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(Commands.literal("playerstats").requires((ctx) -> {return ctx.hasPermissionLevel(2);})
              .then(Commands.argument("targets", EntityArgument.players()).then(Commands.literal("add")
    		  .then(Commands.literal("lvl").then(Commands.argument("amount", IntegerArgumentType.integer()).executes((command)->{
    			return addPlayerResource(command.getSource(),EntityArgument.getPlayers(command, "targets"),Resources.TREE_LVL,IntegerArgumentType.getInteger(command, "amount"));
    		  })))
    		  .then(Commands.literal("xp").then(Commands.argument("amount", IntegerArgumentType.integer()).executes((command)->{
      			return addPlayerResource(command.getSource(),EntityArgument.getPlayers(command, "targets"),Resources.TREE_XP,IntegerArgumentType.getInteger(command, "amount"));
      		  })))
    		  .then(Commands.literal("sun").then(Commands.argument("amount", IntegerArgumentType.integer()).executes((command)->{
      			return addPlayerResource(command.getSource(),EntityArgument.getPlayers(command, "targets"),Resources.SUN_NUM,IntegerArgumentType.getInteger(command, "amount"));
      		  })))
    		  .then(Commands.literal("energy").then(Commands.argument("amount", IntegerArgumentType.integer()).executes((command)->{
      			return addPlayerResource(command.getSource(),EntityArgument.getPlayers(command, "targets"),Resources.ENERGY_NUM,IntegerArgumentType.getInteger(command, "amount"));
      		  })))
    		  .then(Commands.literal("max_energy").then(Commands.argument("amount", IntegerArgumentType.integer()).executes((command)->{
      			return addPlayerResource(command.getSource(),EntityArgument.getPlayers(command, "targets"),Resources.MAX_ENERGY_NUM,IntegerArgumentType.getInteger(command, "amount"));
      		  })))
    		  .then(Commands.literal("money").then(Commands.argument("amount", IntegerArgumentType.integer()).executes((command)->{
      			return addPlayerResource(command.getSource(),EntityArgument.getPlayers(command, "targets"),Resources.MONEY,IntegerArgumentType.getInteger(command, "amount"));
      		  })))
    		  .then(Commands.literal("gem").then(Commands.argument("amount", IntegerArgumentType.integer()).executes((command)->{
      			return addPlayerResource(command.getSource(),EntityArgument.getPlayers(command, "targets"),Resources.GEM_NUM,IntegerArgumentType.getInteger(command, "amount"));
      		  })))
    		  .then(Commands.literal("slot").then(Commands.argument("amount", IntegerArgumentType.integer()).executes((command)->{
      			return addPlayerResource(command.getSource(),EntityArgument.getPlayers(command, "targets"),Resources.MAX_CARD_SLOT,IntegerArgumentType.getInteger(command, "amount"));
      		  })))
    		  )));
    }
	
	public static int addPlayerResource(CommandSource source,Collection<? extends ServerPlayerEntity> targets,Resources res,int num) {
		for(ServerPlayerEntity player:targets) {
		    PlayerUtil.addPlayerStats(player, res, num);
		}
		return targets.size();
	}
}
