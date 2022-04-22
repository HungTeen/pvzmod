package com.hungteen.pvz.common.command;

import com.hungteen.pvz.api.PVZAPI;
import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.api.types.IZombieType;
import com.hungteen.pvz.api.types.base.IPAZType;
import com.hungteen.pvz.utils.PlayerUtil;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.server.level.ServerPlayer;

import java.util.Collection;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-22 20:25
 **/
public class PAZStatsCommand {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        LiteralArgumentBuilder<CommandSourceStack> builder = Commands.literal("pazstats").requires((ctx) -> {return ctx.hasPermission(2);});
        PVZAPI.get().getPAZTypes().forEach(p -> {
            // Operations of paz locks.
            builder.then(Commands.literal("unlock")
                    .then(Commands.argument("targets", EntityArgument.players())
                            .then(Commands.literal("set")
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
                                            }))))
                            .then(Commands.literal("query")
                                    .then(Commands.literal(p.getIdentity()).executes((command)-> {
                                        return queryPAZLock(command.getSource(), EntityArgument.getPlayers(command, "targets"), p);
                                    })))));
        });
        dispatcher.register(builder);
    }

    private static int setZombieLocks(CommandSourceStack source, Collection<? extends ServerPlayer> targets, boolean is) {
        PVZAPI.get().getPAZTypes().stream().filter(IZombieType.class::isInstance).forEach(p -> {
            setPAZLock(source, targets, p, is);
        });
        return targets.size();
    }

    private static int setPlantLocks(CommandSourceStack source, Collection<? extends ServerPlayer> targets, boolean is) {
        PVZAPI.get().getPAZTypes().stream().filter(IPlantType.class::isInstance).forEach(p -> {
            setPAZLock(source, targets, p, is);
        });
        return targets.size();
    }

    private static int setAllLocks(CommandSourceStack source, Collection<? extends ServerPlayer> targets, boolean is) {
        PVZAPI.get().getPAZTypes().forEach(p -> {
            setPAZLock(source, targets, p, is);
        });
        return targets.size();
    }

    private static int setPAZLock(CommandSourceStack source, Collection<? extends ServerPlayer> targets, IPAZType plant, boolean is) {
        for(ServerPlayer player : targets) {
            PlayerUtil.setPAZLock(player, plant, is);
            source.sendSuccess(plant.getText().append(" : " + PlayerUtil.isPAZLocked(player, plant)), true);
        }
        return targets.size();
    }

    private static int queryPAZLock(CommandSourceStack source, Collection<? extends ServerPlayer> targets, IPAZType plant) {
        for(ServerPlayer player:targets) {
            source.sendSuccess(plant.getText().append(" : " + PlayerUtil.isPAZLocked(player, plant)), true);
        }
        return targets.size();
    }

}
