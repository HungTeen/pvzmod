package com.hungteen.pvz.common.command;

import com.hungteen.pvz.api.PVZAPI;
import com.hungteen.pvz.api.types.IPAZType;
import com.hungteen.pvz.utils.PlayerUtil;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.ServerPlayerEntity;

import java.util.Collection;

public class PAZCommand {

	public static void register(CommandDispatcher<CommandSource> dispatcher) {
        LiteralArgumentBuilder<CommandSource> builder = Commands.literal("pazstats").requires((ctx) -> {return ctx.hasPermission(2);});
		PVZAPI.get().getPAZs().forEach(p -> {
			// Operations of paz locks.
			builder.then(Commands.literal("lock")
					.then(Commands.literal("set")
							.then(Commands.argument("targets", EntityArgument.players())
									.then(Commands.literal(p.getIdentity())
											.then(Commands.argument("state", BoolArgumentType.bool()).executes((command)-> {
												return setPAZLock(command.getSource(), EntityArgument.getPlayers(command, "targets"), p, BoolArgumentType.getBool(command, "state"));
											})))
									.then(Commands.literal("plants")
											.then(Commands.argument("state", BoolArgumentType.bool()).executes((command)-> {
												return setPlantLocks(command.getSource(), EntityArgument.getPlayers(command, "targets"), BoolArgumentType.getBool(command, "state"));
											})))
									.then(Commands.literal("zombies")
											.then(Commands.argument("state", BoolArgumentType.bool()).executes((command)-> {
												return setZombieLocks(command.getSource(), EntityArgument.getPlayers(command, "targets"), BoolArgumentType.getBool(command, "state"));
											})))
									.then(Commands.literal("all")
											.then(Commands.argument("state", BoolArgumentType.bool()).executes((command)-> {
												return setAllLocks(command.getSource(), EntityArgument.getPlayers(command, "targets"), BoolArgumentType.getBool(command, "state"));
											})))))
					.then(Commands.literal("query")
							.then(Commands.argument("targets", EntityArgument.players())
									.then(Commands.literal(p.getIdentity()).executes((command)-> {
										return queryPAZLock(command.getSource(), EntityArgument.getPlayers(command, "targets"), p);
									})))));
        });
        dispatcher.register(builder);
    }

	private static int setZombieLocks(CommandSource source, Collection<? extends ServerPlayerEntity> targets, boolean is) {
		PVZAPI.get().getZombies().forEach(p -> {
			setPAZLock(source, targets, p, is);
		});
		return targets.size();
	}

	private static int setPlantLocks(CommandSource source, Collection<? extends ServerPlayerEntity> targets, boolean is) {
		PVZAPI.get().getPlants().forEach(p -> {
			setPAZLock(source, targets, p, is);
		});
		return targets.size();
	}

	private static int setAllLocks(CommandSource source, Collection<? extends ServerPlayerEntity> targets, boolean is) {
		PVZAPI.get().getPAZs().forEach(p -> {
			setPAZLock(source, targets, p, is);
		});
		return targets.size();
	}

	private static int setPAZLock(CommandSource source, Collection<? extends ServerPlayerEntity> targets, IPAZType plant, boolean is) {
		for(ServerPlayerEntity player : targets) {
			PlayerUtil.setPAZLock(player, plant, is);
			source.sendSuccess(plant.getText().append(" : " + PlayerUtil.isPAZLocked(player, plant)), true);
		}
		return targets.size();
	}

	private static int queryPAZLock(CommandSource source, Collection<? extends ServerPlayerEntity> targets, IPAZType plant) {
		for(ServerPlayerEntity player:targets) {
			source.sendSuccess(plant.getText().append(" : " + PlayerUtil.isPAZLocked(player, plant)), true);
		}
		return targets.size();
	}
	
}
