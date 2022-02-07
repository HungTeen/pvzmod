package com.hungteen.pvz.common.command;

import com.hungteen.pvz.common.world.challenge.ChallengeManager;
import com.hungteen.pvz.utils.ConfigUtil;
import com.hungteen.pvz.utils.PlayerUtil;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.BlockPosArgument;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.command.arguments.ResourceLocationArgument;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.Collection;

public class ChallengeCommand {

    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        LiteralArgumentBuilder<CommandSource> builder = Commands.literal("challenge").requires(ctx -> ctx.hasPermission(2));
        builder.then(Commands.literal("add")
                .then(Commands.argument("challenge_type", ResourceLocationArgument.id()).suggests(PVZSuggestionProviders.ALL_CHALLENGES)
                        .then(Commands.argument("pos", BlockPosArgument.blockPos()).executes(command -> {
                            return addChallenge(command.getSource(), ResourceLocationArgument.getId(command, "challenge_type"), BlockPosArgument.getLoadedBlockPos(command, "pos"));
                        }))));
        builder.then(Commands.literal("remove")
                .then(Commands.literal("nearby")
                        .then(Commands.argument("pos", BlockPosArgument.blockPos()).executes((command) -> {
                            return removeNearby(command.getSource(), BlockPosArgument.getLoadedBlockPos(command, "pos"));
                        }))).then(Commands.literal("all").executes((command) -> {
                    return removeAll(command.getSource());
                })));
        builder.then(Commands.literal("list")
                .then(Commands.argument("targets", EntityArgument.players()).executes((command) -> {
                    return showAllChallenge(command.getSource(), EntityArgument.getPlayers(command, "targets"));
                })));

        dispatcher.register(builder);
    }

    private static int addChallenge(CommandSource source, ResourceLocation res, BlockPos pos) {
        if (ChallengeManager.getChallengeByResource(res) != null) {
            if (!ChallengeManager.hasChallengeNearby(source.getLevel(), pos)) {
                ChallengeManager.createChallenge(source.getLevel(), res, pos);
            } else {
                source.sendFailure(new TranslationTextComponent("command.pvz.exist"));
            }
        } else {
            source.sendFailure(new TranslationTextComponent("command.pvz.no", res.toString()));
        }
        return 1;
    }

    private static int removeNearby(CommandSource source, BlockPos pos) {
        ChallengeManager.getChallenges(source.getLevel()).forEach(raid -> {
            if (raid.getCenter().closerThan(pos, ConfigUtil.getRaidRange())) {
                raid.remove();
            }
        });
        return 1;
    }

    private static int removeAll(CommandSource source) {
        ChallengeManager.getChallenges(source.getLevel()).forEach(raid -> {
            raid.remove();
        });
        return 1;
    }

    private static int showAllChallenge(CommandSource source, Collection<? extends ServerPlayerEntity> targets) {
        ChallengeManager.getChallenges(source.getLevel()).forEach(raid -> {
            targets.forEach(p -> PlayerUtil.sendMsgTo(p, new StringTextComponent(raid.getCenter().toString())));
        });
        return 1;
    }

}
