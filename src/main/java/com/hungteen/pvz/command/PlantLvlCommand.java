package com.hungteen.pvz.command;

import java.util.Collection;

import com.hungteen.pvz.capability.CapabilityHandler;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.enums.Plants;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;

import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.StringTextComponent;

public class PlantLvlCommand {

	public static void register(CommandDispatcher<CommandSource> dispatcher) {
        LiteralArgumentBuilder<CommandSource> builder = Commands.literal("plantlvl").requires((ctx) -> {return ctx.hasPermission(2);});
        for(Plants p:Plants.values()) {
        	builder.then(Commands.argument("targets", EntityArgument.players()).then(Commands.literal("add").then(Commands.literal(p.toString().toLowerCase())
        	.then(Commands.literal("xp").then(Commands.argument("amount", IntegerArgumentType.integer()).executes((command)->{
    			return addPlantXp(command.getSource(), EntityArgument.getPlayers(command, "targets"), p, IntegerArgumentType.getInteger(command, "amount"));
    		})))
        	.then(Commands.literal("lvl").then(Commands.argument("amount", IntegerArgumentType.integer()).executes((command)->{
    			return addPlantLvl(command.getSource(), EntityArgument.getPlayers(command, "targets"), p, IntegerArgumentType.getInteger(command, "amount"));
    		})))
        	)));
        	builder.then(Commands.argument("targets", EntityArgument.players()).then(Commands.literal("query").then(Commands.literal(p.toString().toLowerCase())
                .then(Commands.literal("xp").executes((command)->{
            		return queryPlantXp(command.getSource(), EntityArgument.getPlayers(command, "targets"), p);
           		}))
               	.then(Commands.literal("lvl").executes((command)->{
           			return queryPlantLvl(command.getSource(), EntityArgument.getPlayers(command, "targets"), p);
           		}))
            )));
        }
        dispatcher.register(builder);
    }
	
	public static int addPlantLvl(CommandSource source,Collection<? extends ServerPlayerEntity> targets,Plants plant,int num) {
		for(ServerPlayerEntity player:targets) {
		    PlayerUtil.addPlantLvl(player, plant, num);
		}
		return targets.size();
	}
	
	public static int addPlantXp(CommandSource source,Collection<? extends ServerPlayerEntity> targets,Plants plant,int num) {
		for(ServerPlayerEntity player:targets) {
		    PlayerUtil.addPlantXp(player, plant, num);
		}
		return targets.size();
	}
	
	public static int queryPlantLvl(CommandSource source,Collection<? extends ServerPlayerEntity> targets,Plants plant) {
		for(ServerPlayerEntity player:targets) {
		    player.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent((l)->{
		    	source.sendSuccess(new StringTextComponent(""+l.getPlayerData().getPlantStats().getPlantLevel(plant)), true);
		    });
		}
		return targets.size();
	}
	
	public static int queryPlantXp(CommandSource source,Collection<? extends ServerPlayerEntity> targets,Plants plant) {
		for(ServerPlayerEntity player:targets) {
		    player.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent((l)->{
		    	source.sendSuccess(new StringTextComponent(""+l.getPlayerData().getPlantStats().getPlantXp(plant)), true);
		    });
		}
		return targets.size();
	}
}
