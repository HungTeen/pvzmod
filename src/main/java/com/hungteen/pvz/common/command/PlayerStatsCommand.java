package com.hungteen.pvz.common.command;

import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.enums.Resources;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.level.ServerPlayer;

import java.util.Collection;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-10 17:39
 **/
public class PlayerStatsCommand {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        LiteralArgumentBuilder<CommandSourceStack> builder = Commands.literal("playerstats").requires((ctx) -> {return ctx.hasPermission(2);});
        for(Resources res : Resources.values()) {
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

    public static int addPlayerResource(CommandSourceStack source, Collection<? extends ServerPlayer> targets, Resources res, int num) {
        for(ServerPlayer player : targets) {
            PlayerUtil.getOptManager(player).ifPresent(l -> {
                l.addResource(res, num);
                source.sendSuccess(new TextComponent(res.getText().getString() + " : " + l.getResource(res)), true);
            });
        }
        return targets.size();
    }

    public static int queryPlayerResource(CommandSourceStack source,Collection<? extends ServerPlayer> targets,Resources res) {
        for(ServerPlayer player : targets) {
            PlayerUtil.getOptManager(player).ifPresent(l -> {
                source.sendSuccess(new TextComponent(res.getText().getString() + " : " + l.getResource(res)), false);
            });
        }
        return targets.size();
    }

    public static int setPlayerResource(CommandSourceStack source, Collection<? extends ServerPlayer> targets, Resources res, int num) {
        for(ServerPlayer player : targets) {
            PlayerUtil.getOptManager(player).ifPresent(l -> {
                l.setResource(res, num);
                source.sendSuccess(new TextComponent(res.getText().getString() + " : " + l.getResource(res)), true);
            });
        }
        return targets.size();
    }

}
