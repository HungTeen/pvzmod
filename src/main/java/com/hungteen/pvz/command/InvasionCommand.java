package com.hungteen.pvz.command;

import java.util.Collection;

import com.hungteen.pvz.event.OverWorldEvents;
import com.hungteen.pvz.utils.enums.Events;
import com.hungteen.pvz.world.WaveManager;
import com.hungteen.pvz.world.data.WorldEventData;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;

import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.Util;

public class InvasionCommand {

	public static void register(CommandDispatcher<CommandSource> dispatcher) {
        LiteralArgumentBuilder<CommandSource> builder = Commands.literal("invasion").requires((ctx) -> {return ctx.hasPermission(2);});
        for(Events event : Events.values()) {
        	builder.then(Commands.literal("event")
        			.then(Commands.literal("add").then(Commands.literal(event.toString().toLowerCase()).executes((commond)->{
        				return addInvasionEvent(commond.getSource(), event);
        			})))
        		);
        }
        builder.then(Commands.literal("event")
        	    .then(Commands.literal("clear").executes((commond)->{
        	    	return clearInvasionEvent(commond.getSource());
        	    }))
        	    .then(Commands.literal("show").then(Commands.argument("targets", EntityArgument.players()).executes((commond)->{
        	    	return showInvasionEvent(commond.getSource(), EntityArgument.getPlayers(commond, "targets"));
        	    })))
        	);
        builder.then(Commands.literal("wave").then(Commands.argument("targets", EntityArgument.players()).then(Commands.argument("amount", IntegerArgumentType.integer()).executes((commond)->{
        	return spawnHugeWave(EntityArgument.getPlayers(commond, "targets"), IntegerArgumentType.getInteger(commond, "amount"));
        }))));
        dispatcher.register(builder);
    }
	
	private static int spawnHugeWave(Collection<? extends ServerPlayerEntity> targets, int num) {
		targets.forEach((player)->{
//			if(PlayerUtil.isPlayerSurvival(player)) {
				WaveManager manager = new WaveManager(player, 0);
				if(num != 0) manager.spawnCnt = num;
				manager.spawnWaveZombies();
//			}
		});
		return targets.size();
	}
	
	private static int addInvasionEvent(CommandSource source, Events event) {
		OverWorldEvents.activateEvent(source.getLevel(), event);
		return 0;
	}
	
	private static int clearInvasionEvent(CommandSource source) {
		OverWorldEvents.deactivateZombieAttackEvents(source.getLevel(), false);
		return 0;
	}
	
	private static int showInvasionEvent(CommandSource source, Collection<? extends ServerPlayerEntity> targets) {
		targets.forEach((player)->{
			WorldEventData data = WorldEventData.getOverWorldEventData(source.getLevel());
			for(Events event : Events.values()) {
				if(data.hasEvent(event)) {
					player.sendMessage(Events.getEventText(event), Util.NIL_UUID);
				}
			}
		});
		return targets.size();
	}
	
}
