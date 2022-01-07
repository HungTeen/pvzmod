package com.hungteen.pvz.common.command;

import com.hungteen.pvz.common.world.invasion.InvasionManager;
import com.hungteen.pvz.common.world.invasion.InvasionType;
import com.hungteen.pvz.common.world.invasion.PVZInvasionData;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.command.arguments.ResourceLocationArgument;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.Collection;

public class InvasionCommand {

	public static void register(CommandDispatcher<CommandSource> dispatcher) {
        LiteralArgumentBuilder<CommandSource> builder = Commands.literal("invasion").requires((ctx) -> {return ctx.hasPermission(2);});

		builder.then(Commands.literal("event")
				.then(Commands.literal("set")
						.then(Commands.argument("invasion_type", ResourceLocationArgument.id()).suggests(PVZSuggestionProviders.ALL_INVASIONS)
								.executes((commond) -> {
									return setInvasionEvent(commond.getSource(), ResourceLocationArgument.getId(commond, "invasion_type"));
								})))
				.then(Commands.literal("add")
						.then(Commands.argument("invasion_type", ResourceLocationArgument.id()).suggests(PVZSuggestionProviders.ALL_INVASIONS)
								.executes((commond) -> {
									return addInvasionEvent(commond.getSource(), ResourceLocationArgument.getId(commond, "invasion_type"));
								})))
				.then(Commands.literal("remove")
						.then(Commands.argument("invasion_type", ResourceLocationArgument.id()).suggests(PVZSuggestionProviders.ALL_INVASIONS)
								.executes((commond) -> {
									return removeInvasionEvent(commond.getSource(), ResourceLocationArgument.getId(commond, "invasion_type"));
								})))
				.then(Commands.literal("show")
						.then(Commands.argument("targets", EntityArgument.players())
								.executes((commond)->{
									return showInvasionEvent(commond.getSource(), EntityArgument.getPlayers(commond, "targets"));
								})))
				.then(Commands.literal("stop")
						.executes((commond)->{
							return stopInvasionEvent(commond.getSource());
						}))
		);
//        for (IZombieType zombie : ZombieType.getZombies()) {
//        	builder.then(Commands.literal("zombie")
//        			.then(Commands.literal("add").then(Commands.literal(zombie.toString().toLowerCase()).executes((commond)->{
//        				return addZombie(commond.getSource(), zombie);
//        			})))
//        			.then(Commands.literal("remove").then(Commands.literal(zombie.toString().toLowerCase()).executes((commond)->{
//        				return removeZombie(commond.getSource(), zombie);
//        			})))
//        		);
//        }
//        builder.then(Commands.literal("wave").then(Commands.argument("targets", EntityArgument.players()).then(Commands.argument("amount", IntegerArgumentType.integer()).executes((commond)->{
//        	return spawnHugeWave(EntityArgument.getPlayers(commond, "targets"), IntegerArgumentType.getInteger(commond, "amount"), 1);
//        }).then(Commands.argument("wave_num", IntegerArgumentType.integer()).executes(commond -> {
//        	return spawnHugeWave(EntityArgument.getPlayers(commond, "targets"), IntegerArgumentType.getInteger(commond, "amount"), IntegerArgumentType.getInteger(commond, "wave_num"));
//        })))));
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
	
//	private static int spawnHugeWave(Collection<? extends ServerPlayerEntity> targets, int num, int wave_num) {
//		targets.forEach(player -> {
//			WaveManager manager = new WaveManager(player, wave_num);
//			if(num != 0) {
//				manager.spawnCnt = num;
//			}
//			manager.spawnWaveZombies();
//		});
//		return targets.size();
//	}

	public static int setInvasionEvent(CommandSource source, ResourceLocation res) {
		InvasionType type = InvasionManager.getInvasion(res);
		if(type != null){
			if(! type.isAssistInvasion()){
				PVZInvasionData.getOverWorldInvasionData(source.getLevel()).setSpawnInvasion(res);
				source.sendSuccess(new TranslationTextComponent("command.pvz.invasion.set", res.toString()), true);
			} else{
				source.sendFailure(new TranslationTextComponent("command.pvz.invasion.assist", res.toString()));
			}
		} else{
			source.sendFailure(new TranslationTextComponent("command.pvz.invasion.invalid", res.toString()));
		}
		return 0;
	}

	public static int addInvasionEvent(CommandSource source, ResourceLocation res) {
		InvasionType type = InvasionManager.getInvasion(res);
		if(type != null){
			if(type.isAssistInvasion()){
				PVZInvasionData.getOverWorldInvasionData(source.getLevel()).addAssistInvasion(res);
				source.sendSuccess(new TranslationTextComponent("command.pvz.invasion.add", res.toString()), true);
			} else{
				source.sendFailure(new TranslationTextComponent("command.pvz.invasion.spawn", res.toString()));
			}
		} else{
			source.sendFailure(new TranslationTextComponent("command.pvz.invasion.invalid", res.toString()));
		}
		return 0;
	}

	public static int removeInvasionEvent(CommandSource source, ResourceLocation res) {
		InvasionType type = InvasionManager.getInvasion(res);
		if(type != null){
			if(type.isAssistInvasion()){
				PVZInvasionData.getOverWorldInvasionData(source.getLevel()).removeAssistInvasion(res);
				source.sendSuccess(new TranslationTextComponent("command.pvz.invasion.remove", res.toString()), true);
			} else{
				source.sendFailure(new TranslationTextComponent("command.pvz.invasion.spawn", res.toString()));
			}
		} else{
			source.sendFailure(new TranslationTextComponent("command.pvz.invasion.invalid", res.toString()));
		}
		return 0;
	}
	
	private static int stopInvasionEvent(CommandSource source) {
		InvasionManager.deactivateZombieAttackEvents(source.getLevel(), false);
		source.sendSuccess(new TranslationTextComponent("command.pvz.invasion.clear"), true);
		return 0;
	}
	
	private static int showInvasionEvent(CommandSource source, Collection<? extends ServerPlayerEntity> targets) {
		targets.forEach(player -> {
			InvasionManager.getActiveInvasions().forEach(type -> {
				source.sendSuccess(type.getText(), true);
			});
		});
		return targets.size();
	}
	
}
