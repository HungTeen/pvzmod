package com.hungteen.pvz.common.command;

import com.hungteen.pvz.common.world.raid.RaidManager;
import com.hungteen.pvz.utils.ConfigUtil;
import com.hungteen.pvz.utils.PlayerUtil;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
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

public class CRaidCommand {

	public static void register(CommandDispatcher<CommandSource> dispatcher) {
		LiteralArgumentBuilder<CommandSource> builder = Commands.literal("craid").requires(ctx -> ctx.hasPermission(2));
		builder.then(Commands.literal("add").then(Commands.argument("type", ResourceLocationArgument.id())
				.then(Commands.argument("pos", BlockPosArgument.blockPos()).executes((command) -> {
					return addRaid(command.getSource(), getResourceLocation(command, "type"),
							BlockPosArgument.getLoadedBlockPos(command, "pos"));
				}))));
		builder.then(Commands.literal("remove").then(Commands.literal("nearby")
				.then(Commands.argument("pos", BlockPosArgument.blockPos()).executes((command) -> {
					return removeNearby(command.getSource(), BlockPosArgument.getLoadedBlockPos(command, "pos"));
				}))).then(Commands.literal("all").executes((command) -> {
							return removeAll(command.getSource());
				})));
		builder.then(Commands.literal("list").then(Commands.argument("targets", EntityArgument.players()).executes((command) -> {
			return showAllRaid(command.getSource(), EntityArgument.getPlayers(command, "targets"));
		})));
		dispatcher.register(builder);
	}

	private static int addRaid(CommandSource source, ResourceLocation res, BlockPos pos) {
		if(RaidManager.getRaidComponent(res) != null) {
			if(! RaidManager.hasRaidNearby(source.getLevel(), pos)) {
				RaidManager.createRaid(source.getLevel(), res, pos);
			} else {
				source.sendFailure(new TranslationTextComponent("info.craid.has_raid"));
			}
		} else {
			source.sendFailure(new TranslationTextComponent("info.craid.search_fail"));
		}
		return 1;
	}
	
	private static int removeNearby(CommandSource source, BlockPos pos) {
		RaidManager.getRaids(source.getLevel()).forEach(raid -> {
			if(raid.getCenter().closerThan(pos, ConfigUtil.getRaidRange())) {
				raid.remove();
			}
		});
		return 1;
	}
	
	private static int removeAll(CommandSource source) {
		RaidManager.getRaids(source.getLevel()).forEach(raid -> {
			raid.remove();
		});
		return 1;
	}

	private static int showAllRaid(CommandSource source, Collection<? extends ServerPlayerEntity> targets) {
		RaidManager.getRaids(source.getLevel()).forEach(raid -> {
			targets.forEach(p -> PlayerUtil.sendMsgTo(p, new StringTextComponent(raid.getCenter().toString())));
		});
		return 1;
	}
	
	private static ResourceLocation getResourceLocation(CommandContext<CommandSource> source, String string) {
		return source.getArgument(string, ResourceLocation.class);
	}

}
