package com.hungteen.pvz.common.world.invasion;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.common.cache.InvasionCache;
import com.hungteen.pvz.common.world.data.PVZFlagData;
import com.hungteen.pvz.common.world.data.PVZInvasionData;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.enums.InvasionEvents;
import com.hungteen.pvz.utils.enums.Zombies;
import com.hungteen.pvz.utils.others.WeightList;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.Util;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;
import net.minecraftforge.event.TickEvent;


public class OverworldInvasion {

	private static final ITextComponent ZOMBIE_ATTACK = new TranslationTextComponent("event.pvz.zombie_attack").withStyle(TextFormatting.DARK_RED);
	private static final ITextComponent ATTACK_FINISH = new TranslationTextComponent("event.pvz.attack_finish").withStyle(TextFormatting.GREEN);
	private static final ITextComponent SAFE_DAY_INFO = new TranslationTextComponent("event.pvz.safe_day");
	private static final ITextComponent COUNT_DOWN_INFO = new TranslationTextComponent("event.pvz.count_down");
	private static final ITextComponent DAY = new TranslationTextComponent("event.pvz.day");
	public static final int PRE_START_TICK = 499;
	public static final int START_TICK = 500;
	public static final int PRE_END_TICK = 99;
	public static final int END_TICK = 100;
	
	public static void tick(TickEvent.WorldTickEvent ev) {
		World world = ev.world;
		long totalTime = world.getDayTime();
		int dayTime = (int) (totalTime % 24000);
		switch(dayTime) {
		case PRE_START_TICK:{
			PVZInvasionData data = PVZInvasionData.getOverWorldInvasionData(world);
			data.setChanged(false);
			break;
		}
		case START_TICK:{
			PVZInvasionData data = PVZInvasionData.getOverWorldInvasionData(world);
			if(! data.hasChanged()) {
				data.setChanged(true);
				deactivateZombieAttackEvents(world, false);//cancel all invasion happened yesterday.
				long dif = difSafeDay(world);
				final boolean isSafe = (dif < 0);
				int count = data.getCountDownDay();
				final boolean hasCount = data.hasCountDownDay();
				if(! hasCount && ! isSafe) {// no interval and not safe then invade happen !
					activateZombieAttackEvents(world);
				} else if(isSafe) {
					PlayerUtil.sendMsgToAll(world, new StringTextComponent(SAFE_DAY_INFO.getString() + " : " + getLeftSafeDay(dif) + DAY.getString()).withStyle(TextFormatting.GREEN));
				} else {
					PlayerUtil.sendMsgToAll(world, new StringTextComponent(COUNT_DOWN_INFO.getString() + " : " + count + DAY.getString()).withStyle(TextFormatting.RED));
				}
				data.decCountDownDay();
			}
			break;
		}
		case PRE_END_TICK:{
			PVZInvasionData data = PVZInvasionData.getOverWorldInvasionData(world);
			data.setChanged(false);
			break;
		}
		case END_TICK:{
			PVZInvasionData data = PVZInvasionData.getOverWorldInvasionData(world);
			if(! data.hasChanged()) {
				data.setChanged(true);
				deactivateZombieAttackEvents(world, true);
			}
			break;
		}
		}
		WaveManager.tickWave(world, dayTime);
		FogManager.tickFog(world);
	}
	
	/**
	 * check and activate attack event,
	 * do not activate in peaceful mode.
	 */
	public static void activateZombieAttackEvents(World world) {
		if(world.getDifficulty() != Difficulty.PEACEFUL) {
			//warn to all players when invasion start.
		    for(ServerPlayerEntity pl : world.getServer().getPlayerList().getPlayers()) {
			    pl.sendMessage(ZOMBIE_ATTACK, Util.NIL_UUID);
			    PlayerUtil.playClientSound(pl, 3);
			    WaveManager.resetPlayerWaveTime(pl);
		    }
		    //choose random spawn event
		    InvasionEvents event = getSpawnEvent(world);
		    activateEvent(world, event);
		    //check assist event
		    activateAssistEvents(world);
		    //get zombie spawn list
		    PVZInvasionData data = PVZInvasionData.getOverWorldInvasionData(world);
		    List<Zombies> zombieList = new ArrayList<>();
		    for(Zombies zombie : Zombies.values()) {
		    	if(data.hasZombieSpawnEntry(zombie)){
		    		zombieList.add(zombie);
		    	}
		    }
		    if(PVZConfig.COMMON_CONFIG.WorldSettings.WorldInvasionSettings.ShowEventMessages.get()) {
		    	world.getServer().getPlayerList().getPlayers().forEach(player -> {
			        for(InvasionEvents ev : InvasionEvents.values()) {
				        if(data.hasEvent(ev)) {
					        player.sendMessage(InvasionEvents.getEventText(ev), Util.NIL_UUID);
				        }
			        }
			        String zombieInfo = "";
			        for(int i = 0; i < zombieList.size(); ++ i) {
			        	zombieInfo += new TranslationTextComponent("entity.pvz." + zombieList.get(i).toString().toLowerCase()).getString()
			        			+ (i == zombieList.size() - 1 ? "" : ",");
			        }
			        player.sendMessage(new StringTextComponent(zombieInfo), Util.NIL_UUID);
			    });
		    }
		}
	}
	
	/**
	 * activate zombie spawn event.
	 */
	public static void activateEvent(World world, @Nonnull InvasionEvents event) {
		PVZInvasionData data = PVZInvasionData.getOverWorldInvasionData(world);
		if(! data.hasEvent(event)) {
			data.addEvent(event);
			addEventSpawns(world, event);
		}
	}
	
	/**
	 * add spawn of zombies in event spawn list.
	 */
	public static void addEventSpawns(World world, InvasionEvents event) {
		PVZInvasionData data = PVZInvasionData.getOverWorldInvasionData(world);
        for (Zombies zombie : getInvasionSpawnZombies(world, event)) {
        	if(! data.hasZombieSpawnEntry(zombie)) {
        		data.addZombieSpawnEntry(zombie);
        	}
        }
        syncStartSpawnList(world);
    }
	
	/**
	 * activate zombie assist events.
	 */
	public static void activateAssistEvents(World world) {
		PVZFlagData data = PVZFlagData.getGlobalFlagData(world);
		if(world.random.nextInt(PVZConfig.COMMON_CONFIG.WorldSettings.WorldInvasionSettings.EventChanceSettings.FogEventChance.get()) == 0) {
	    	activateEvent(world, InvasionEvents.FOG);
	    }
		if(data.isZombossDefeated()) {
			if(world.random.nextInt(PVZConfig.COMMON_CONFIG.WorldSettings.WorldInvasionSettings.EventChanceSettings.MiniEventChance.get()) == 0) {
	    	    activateEvent(world, InvasionEvents.MINI);
	        }
	        if(world.random.nextInt(PVZConfig.COMMON_CONFIG.WorldSettings.WorldInvasionSettings.EventChanceSettings.InvisEventChance.get()) == 0) {
	    	    activateEvent(world, InvasionEvents.INVIS);
	        }
		}
	}
	
	/**
	 * deactivate all invasion events and remove zombie spawn
	 */
	public static void deactivateZombieAttackEvents(World world, boolean isNatural) {
	    boolean flag = false;
	    PVZInvasionData data = PVZInvasionData.getOverWorldInvasionData(world);
	    for(InvasionEvents ev : InvasionEvents.values()) {// has invasion event
	    	flag |= data.hasEvent(ev);
	    }
		if(isNatural && flag) {//end invasion
			for(ServerPlayerEntity pl : world.getServer().getPlayerList().getPlayers()) {
		        pl.sendMessage(ATTACK_FINISH, Util.NIL_UUID);
		        PlayerUtil.playClientSound(pl, 4);
		        WaveManager.giveInvasionBonusToPlayer(world, pl);
	        }
		}
		//remove invasion event
		for(InvasionEvents ev : InvasionEvents.values()) {
			if(data.hasEvent(ev)) {
				data.removeEvent(ev);
			}
		}
		//remove zombie spawns.
		for(Zombies zombie : Zombies.values()) {
    		if(data.hasZombieSpawnEntry(zombie)) {
    			data.removeZombieSpawnEntry(zombie);
    		}
        }
		syncEndSpawnList(world);
	}
	
	public static List<Zombies> getInvasionSpawnZombies(World world, InvasionEvents ev){
		if(ev == InvasionEvents.RANDOM) return getRandomSpawnZombies(world);
		return ev.zombies;
	}
	
	private static List<Zombies> getRandomSpawnZombies(World world){
		final List<Zombies> zombies = new ArrayList<>();
		int zombieTypeCnt = world.random.nextInt(3) + 3;
		for(int i = 0 ;i < zombieTypeCnt; ++ i) {
			Zombies.ZOMBIE_SPAWN_LIST.getRandomItem(world.random).ifPresent(zombie -> {
				if(zombies.indexOf(zombie) == -1) {
				    zombies.add(zombie);
				}
			});
		}
		return zombies;
	}
	
	/**
	 * randomly get a spawn invasion event.
	 */
	private static InvasionEvents getSpawnEvent(World world) {
		WeightList<InvasionEvents> list = new WeightList<>();
		int sum = 0;
		for(InvasionEvents ev : InvasionEvents.getAvailableSpawnEvents(world)) {//get all attack events
			if(InvasionEvents.EVENT_CHANCE.containsKey(ev)) {
				int weight = InvasionEvents.EVENT_CHANCE.get(ev);
			    list.addItem(ev, weight);
			    sum += weight;
			}
		}
		list.setTotal(sum);
		return list.getRandomItem(world.random).get();
	}
	
	/**
	 * use in Invasion Command.
	 */
	public static void addZombie(World world, Zombies zombie) {
		PVZInvasionData data = PVZInvasionData.getOverWorldInvasionData(world);
		data.addZombieSpawnEntry(zombie);
		syncStartSpawnList(world);
	}
	
	/**
	 * use in Invasion Command.
	 */
	public static void removeZombie(World world, Zombies zombie) {
		PVZInvasionData data = PVZInvasionData.getOverWorldInvasionData(world);
		if(data.hasZombieSpawnEntry(zombie)) {
			data.removeZombieSpawnEntry(zombie);
			syncStartSpawnList(world);
		}
	}
	
	/**
	 * for server start sync.
	 */
	public static void syncStartSpawnList(World world) {
		syncEndSpawnList(world);
		PVZInvasionData data = PVZInvasionData.getOverWorldInvasionData(world);
        for (Zombies zombie : Zombies.values()) {
        	if(data.hasZombieSpawnEntry(zombie)) {
        		InvasionCache.ZOMBIE_INVADE_SET.add(zombie);
        	}
        }
	}

	/**
	 * for server end sync.
	 */
	public static void syncEndSpawnList(World world) {
		InvasionCache.ZOMBIE_INVADE_SET.clear();
	}
	
	/**
	 * is still in safe day.
	 */
	public static long difSafeDay(World world) {
		return world.getGameTime() - PVZConfig.COMMON_CONFIG.WorldSettings.WorldInvasionSettings.SafeDayLength.get() * 24000;
	}
	
	public static long getLeftSafeDay(long len) {
		return (- len + 23999) / 24000;
	}
	
}
