package com.hungteen.pvz.common.command;

import java.util.Collection;

import com.hungteen.pvz.common.world.data.PVZInvasionData;
import com.hungteen.pvz.common.world.invasion.OverworldInvasion;
import com.hungteen.pvz.common.world.invasion.WaveManager;
import com.hungteen.pvz.utils.enums.InvasionEvents;
import com.hungteen.pvz.utils.enums.Zombies;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;

import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class InvasionCommand {

	public static void register(CommandDispatcher<CommandSource> dispatcher) {
        LiteralArgumentBuilder<CommandSource> builder = Commands.literal("invasion").requires((ctx) -> {return ctx.hasPermission(2);});
        for(InvasionEvents event : InvasionEvents.values()) {
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
        for(Zombies zombie : Zombies.values()) {
        	builder.then(Commands.literal("zombie")
        			.then(Commands.literal("add").then(Commands.literal(zombie.toString().toLowerCase()).executes((commond)->{
        				return addZombie(commond.getSource(), zombie);
        			})))
        			.then(Commands.literal("remove").then(Commands.literal(zombie.toString().toLowerCase()).executes((commond)->{
        				return removeZombie(commond.getSource(), zombie);
        			})))
        		);
        }
        builder.then(Commands.literal("wave").then(Commands.argument("targets", EntityArgument.players()).then(Commands.argument("amount", IntegerArgumentType.integer()).executes((commond)->{
        	return spawnHugeWave(EntityArgument.getPlayers(commond, "targets"), IntegerArgumentType.getInteger(commond, "amount"), 1);
        }).then(Commands.argument("wave_num", IntegerArgumentType.integer()).executes(commond -> {
        	return spawnHugeWave(EntityArgument.getPlayers(commond, "targets"), IntegerArgumentType.getInteger(commond, "amount"), IntegerArgumentType.getInteger(commond, "wave_num"));
        })))));
        builder.then(Commands.literal("difficulty")
        		.then(Commands.literal("add").then(Commands.argument("amount", IntegerArgumentType.integer()).executes(commond -> {
        			return addDifficulty(commond.getSource(), IntegerArgumentType.getInteger(commond, "amount"));
		        })))
		        .then(Commands.literal("set").then(Commands.argument("amount", IntegerArgumentType.integer()).executes(commond -> {
        			return setDifficulty(commond.getSource(), IntegerArgumentType.getInteger(commond, "amount"));
		        })))
		        .then(Commands.literal("query").executes(commond -> {
        			return queryDifficulty(commond.getSource());
		        }))
		);
        dispatcher.register(builder);
    }
	
	private static int addDifficulty(CommandSource source, int amount) {
		PVZInvasionData data = PVZInvasionData.getOverWorldInvasionData(source.getLevel());
		data.addCurrentDifficulty(amount);
		queryDifficulty(source);
		return 0;
	}
	
	private static int setDifficulty(CommandSource source, int amount) {
		PVZInvasionData data = PVZInvasionData.getOverWorldInvasionData(source.getLevel());
		data.setCurrentDifficulty(amount);
		queryDifficulty(source);
		return 0;
	}
	
	private static int queryDifficulty(CommandSource source) {
		PVZInvasionData data = PVZInvasionData.getOverWorldInvasionData(source.getLevel());
		source.sendSuccess(new StringTextComponent(new TranslationTextComponent("command.pvz.difficulty").getString() + " : " + data.getCurrentDifficulty()), true);
		return 0;
	}
	
	private static int addZombie(CommandSource source, Zombies zombie) {
		if(zombie.chooseWeight > 0) {
			OverworldInvasion.addZombie(source.getLevel(), zombie);
			source.sendSuccess(new StringTextComponent(new TranslationTextComponent("command.pvz.add_zombie").getString() + ":" + zombie.getText().getString()), true);
		} else {
			source.sendSuccess(new StringTextComponent(new TranslationTextComponent("command.pvz.add_zombie_fail").getString()), false);
		}
		return 0;
	}
	
	private static int removeZombie(CommandSource source, Zombies zombie) {
		OverworldInvasion.removeZombie(source.getLevel(), zombie);
		source.sendSuccess(new StringTextComponent(new TranslationTextComponent("command.pvz.remove_zombie").getString() + ":" + zombie.getText().getString()), true);
		return 0;
	}
	
	private static int spawnHugeWave(Collection<? extends ServerPlayerEntity> targets, int num, int wave_num) {
		targets.forEach(player -> {
			WaveManager manager = new WaveManager(player, wave_num);
			if(num != 0) {
				manager.spawnCnt = num;
			}
			manager.spawnWaveZombies();
		});
		return targets.size();
	}
	
	public static int addInvasionEvent(CommandSource source, InvasionEvents event) {
		OverworldInvasion.activateEvent(source.getLevel(), event, true);
		source.sendSuccess(new StringTextComponent(new TranslationTextComponent("command.pvz.add_event").getString() + ":" + InvasionEvents.getEventText(event).getString()), true);
		return 0;
	}
	
	private static int clearInvasionEvent(CommandSource source) {
		OverworldInvasion.deactivateZombieAttackEvents(source.getLevel(), false);
		source.sendSuccess(new TranslationTextComponent("command.pvz.clear_events"), true);
		return 0;
	}
	
	private static int showInvasionEvent(CommandSource source, Collection<? extends ServerPlayerEntity> targets) {
		targets.forEach((player)->{
			PVZInvasionData data = PVZInvasionData.getOverWorldInvasionData(source.getLevel());
			for(InvasionEvents event : InvasionEvents.values()) {
				if(data.hasEvent(event)) {
					source.sendSuccess(InvasionEvents.getEventText(event), false);
				}
			}
		});
		return targets.size();
	}
	
}
