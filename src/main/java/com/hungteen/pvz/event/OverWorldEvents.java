package com.hungteen.pvz.event;

import javax.annotation.Nonnull;

import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.capability.CapabilityHandler;
import com.hungteen.pvz.capability.player.PlayerDataManager;
import com.hungteen.pvz.register.EntitySpawnRegister;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.enums.Events;
import com.hungteen.pvz.world.WaveManager;
import com.hungteen.pvz.world.data.WorldEventData;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;
import net.minecraftforge.event.TickEvent;


public class OverWorldEvents {

	private static final ITextComponent ZOMBIE_ATTACK = new TranslationTextComponent("event.pvz.zombie_attack").applyTextStyle(TextFormatting.DARK_RED);
	
	public static void tick(TickEvent.WorldTickEvent ev) {
		World world = ev.world;
		long totalTime = world.getDayTime();
		if(world.getGameTime() < PVZConfig.COMMON_CONFIG.WorldSettings.WorldEventSettings.SafeDayLength.get() * 24000) {
			return ;// still in safe day, do not have any event. 
		}
		int dayTime = (int) (totalTime % 24000);
		switch(dayTime) {
		case 99:{
			WorldEventData data = WorldEventData.getOverWorldEventData(world);
			data.setChanged(false);
			break;
		}
		case 100:{
			WorldEventData data = WorldEventData.getOverWorldEventData(world);
			if(! data.hasChanged()) {
				data.setChanged(true);
				activateZombieAttackEvents(world);
			}
			break;
		}
		case 23899:{
			WorldEventData data = WorldEventData.getOverWorldEventData(world);
			data.setChanged(false);
			break;
		}
		case 23900:{
			WorldEventData data = WorldEventData.getOverWorldEventData(world);
			if(! data.hasChanged()) {
				data.setChanged(true);
				deactivateZombieAttackEvents(world);
			}
			break;
		}
		}
		for(PlayerEntity player : world.getPlayers()) {
		    player.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent((l)->{
			    PlayerDataManager.OtherStats stats = l.getPlayerData().getOtherStats();
			    for(int i = 0 ; i < stats.zombieWaveTime.length; ++ i) {
				    int time = stats.zombieWaveTime[i];
				    if(time != 0 && time >= dayTime) {
					    if(time == dayTime) {
						    WaveManager manager = new WaveManager(player, i);
						    manager.spawnWaveZombies();
						    stats.zombieWaveTime[i] = 0;
					    }
					    break;
				    }
			    }
		    });
		}
	}
	
	/**
	 * check and activate attack event,
	 * do not activate in peaceful mode.
	 */
	public static void activateZombieAttackEvents(World world) {
		if(world.getDifficulty() != Difficulty.PEACEFUL && world.rand.nextInt(100) < PVZConfig.COMMON_CONFIG.WorldSettings.WorldEventSettings.ZombieAttackChance.get()) {
		    for(PlayerEntity pl : world.getPlayers()) {
			    pl.sendMessage(ZOMBIE_ATTACK);
			    PlayerUtil.playClientSound(pl, 3);
			    WaveManager.resetPlayerWaveTime(pl);
		    }
		    Events event = EntitySpawnRegister.getCurrentEventByRandom(world);
		    activateEvent(world, event);//activate event
		    if(PVZConfig.COMMON_CONFIG.WorldSettings.WorldEventSettings.ShowEventMessages.get()) {
		    	for(PlayerEntity pl : world.getPlayers()) {
		    		pl.sendMessage(Events.getEventText(event));
		    	}
		    }
		}
	}
	
	/**
	 * activate zombie attack event.
	 */
	private static void activateEvent(World world, @Nonnull Events event) {
		WorldEventData data = WorldEventData.getOverWorldEventData(world);
		if(! data.hasEvent(event)) {
			data.addEvent(event);
			EntitySpawnRegister.addEventSpawns(world, event);
		}
	}
	
	/**
	 * deactivate all events and remove zombie spawn
	 */
	public static void deactivateZombieAttackEvents(World world) {
		for(Events ev : Events.values()) {
			deactivateEvent(world, ev);
		}
		EntitySpawnRegister.removeEventSpawns(world);
	}
	
	/**
	 * deactivate all events
	 */
	private static void deactivateEvent(World world, @Nonnull Events event) {
		WorldEventData data = WorldEventData.getOverWorldEventData(world);
		if(data.hasEvent(event)) {
//			System.out.println(event);
			data.removeEvent(event);
		}
	}
	
}
