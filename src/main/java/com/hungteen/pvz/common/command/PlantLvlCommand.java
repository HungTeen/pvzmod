package com.hungteen.pvz.common.command;

import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.capability.CapabilityHandler;
import com.hungteen.pvz.common.impl.PlantType;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.StringTextComponent;

import java.util.Collection;

public class PlantLvlCommand {

	public static void register(CommandDispatcher<CommandSource> dispatcher) {
        LiteralArgumentBuilder<CommandSource> builder = Commands.literal("plantlvl").requires((ctx) -> {return ctx.hasPermission(2);});
        for(IPlantType p : PlantType.getPlants()) {
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
        {
        	builder.then(Commands.argument("targets", EntityArgument.players()).then(Commands.literal("add").then(Commands.literal("all")
                .then(Commands.literal("xp").then(Commands.argument("amount", IntegerArgumentType.integer()).executes((command)->{
            		return addAllPlantXp(command.getSource(), EntityArgument.getPlayers(command, "targets"), IntegerArgumentType.getInteger(command, "amount"));
            	})))
                .then(Commands.literal("lvl").then(Commands.argument("amount", IntegerArgumentType.integer()).executes((command)->{
            		return addAllPlantLvl(command.getSource(), EntityArgument.getPlayers(command, "targets"), IntegerArgumentType.getInteger(command, "amount"));
            	})))
            )));
        }
        dispatcher.register(builder);
    }
	
	public static int addAllPlantLvl(CommandSource source, Collection<? extends ServerPlayerEntity> targets, int num) {
		for(ServerPlayerEntity player : targets) {
			player.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent(l -> {
				for(IPlantType plant : PlantType.getPlants()) {
					l.getPlayerData().addPAZLevel(plant, num);
					source.sendSuccess(new StringTextComponent(plant.getText().getString() + ":" + l.getPlayerData().getPAZLevel(plant)), true);
				}
			});
		}
		return targets.size();
	}
	
	public static int addAllPlantXp(CommandSource source, Collection<? extends ServerPlayerEntity> targets, int num) {
		for(ServerPlayerEntity player : targets) {
			player.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent(l -> {
				for(IPlantType plant : PlantType.getPlants()) {
					l.getPlayerData().addPAZXp(plant, num);
					source.sendSuccess(new StringTextComponent(plant.getText().getString() + ":" + l.getPlayerData().getPAZLevel(plant)), true);
				}
			});
		}
		return targets.size();
	}
	
	public static int addPlantLvl(CommandSource source, Collection<? extends ServerPlayerEntity> targets, IPlantType plant, int num) {
		for(ServerPlayerEntity player : targets) {
			player.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent(l -> {
				l.getPlayerData().addPAZLevel(plant, num);
				source.sendSuccess(new StringTextComponent(plant.getText().getString() + ":" + l.getPlayerData().getPAZLevel(plant)), true);
			});
		}
		return targets.size();
	}
	
	public static int addPlantXp(CommandSource source, Collection<? extends ServerPlayerEntity> targets, IPlantType plant, int num) {
		for(ServerPlayerEntity player : targets) {
			player.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent(l -> {
				l.getPlayerData().addPAZXp(plant, num);
				source.sendSuccess(new StringTextComponent(plant.getText().getString() + ":" + l.getPlayerData().getPAZLevel(plant)), true);
			});
		}
		return targets.size();
	}
	
	public static int queryPlantLvl(CommandSource source, Collection<? extends ServerPlayerEntity> targets, IPlantType plant) {
		for(ServerPlayerEntity player:targets) {
		    player.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent((l)->{
		    	source.sendSuccess(new StringTextComponent(plant.getText().getString() + ":" + l.getPlayerData().getPAZLevel(plant)), false);
			});
		}
		return targets.size();
	}
	
	public static int queryPlantXp(CommandSource source, Collection<? extends ServerPlayerEntity> targets, IPlantType plant) {
		for(ServerPlayerEntity player:targets) {
		    player.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent((l)->{
		    	source.sendSuccess(new StringTextComponent(plant.getText().getString() + ":" + l.getPlayerData().getPAZXp(plant)), false);
		    });
		}
		return targets.size();
	}
}
