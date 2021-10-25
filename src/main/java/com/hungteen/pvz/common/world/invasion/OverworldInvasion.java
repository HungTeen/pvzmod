package com.hungteen.pvz.common.world.invasion;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.api.types.IInvasionType;
import com.hungteen.pvz.api.types.IZombieType;
import com.hungteen.pvz.common.cache.FlagCache;
import com.hungteen.pvz.common.cache.InvasionCache;
import com.hungteen.pvz.common.command.InvasionCommand;
import com.hungteen.pvz.common.event.events.InvasionEvent;
import com.hungteen.pvz.common.impl.InvasionEvents;
import com.hungteen.pvz.common.impl.ZombieType;
import com.hungteen.pvz.common.impl.misc.InvasionType;
import com.hungteen.pvz.common.world.data.PVZInvasionData;
import com.hungteen.pvz.utils.ConfigUtil;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.others.WeightList;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.Util;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
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
		if(world.getDifficulty() != Difficulty.PEACEFUL && ! MinecraftForge.EVENT_BUS.post(new InvasionEvent.InvasionStartEvent(world))) {
			/* notify all players when invasion start. */
		    for(ServerPlayerEntity pl : PlayerUtil.getServerPlayers(world)) {
			    pl.sendMessage(ZOMBIE_ATTACK, Util.NIL_UUID);
			    PlayerUtil.playClientSound(pl, 3);
			    WaveManager.resetPlayerWaveTime(pl);
		    }
		    /* add difficulty */
		    PVZInvasionData data = PVZInvasionData.getOverWorldInvasionData(world);
		    data.addCurrentDifficulty(ConfigUtil.getIncDifficulty());
		    /* choose random spawn event */
		    IInvasionType event = getSpawnEvent(world);
		    activateEvent(world, event, FlagCache.isEdgar090505Defeated());
		    /* check assist event */
		    activateAssistEvents(world);
		    /* get zombie spawn list */
		    List<IZombieType> zombieList = new ArrayList<>();
		    for(IZombieType zombie : ZombieType.getZombies()) {
		    	if(data.hasZombieSpawnEntry(zombie)){
		    		zombieList.add(zombie);
		    	}
		    }
		    if(PVZConfig.COMMON_CONFIG.InvasionSettings.ShowEventMessages.get()) {
		    	PlayerUtil.getServerPlayers(world).forEach(player -> {
			        for(IInvasionType ev : InvasionType.getInvasionEvents()) {
				        if(data.hasEvent(ev)) {
					        player.sendMessage(ev.getText(), Util.NIL_UUID);
				        }
			        }
			        if(zombieList.size() > 0) {
			        	 final IFormattableTextComponent text = zombieList.get(0).getText();
			        	 for(int i = 1; i < zombieList.size(); ++ i) {
			        		 text.append(",").append(zombieList.get(i).getText());
		        	         player.sendMessage(text, Util.NIL_UUID);
			        	 }
			        }
			    });
		    }
		}
	}
	
	/**
	 * activate zombie spawn event.
	 * {@link InvasionCommand#addInvasionEvent(net.minecraft.command.CommandSource, InvasionEvents)}
	 */
	public static void activateEvent(World world, @Nonnull IInvasionType event, boolean force) {
		PVZInvasionData data = PVZInvasionData.getOverWorldInvasionData(world);
		if(! data.hasEvent(event)) {
			data.addEvent(event);
			addEventSpawns(world, event, force);
		}
	}
	
	/**
	 * add spawn of zombies in event spawn list.
	 */
	public static void addEventSpawns(World world, IInvasionType event, boolean force) {
		PVZInvasionData data = PVZInvasionData.getOverWorldInvasionData(world);
        for (IZombieType zombie : getInvasionSpawnZombies(world, event, force)) {
        	if(! data.hasZombieSpawnEntry(zombie)) {
        		data.addZombieSpawnEntry(zombie);
        	}
        }
        syncStartSpawnList(world);
    }
	
	/**
	 * activate zombie assist events.
	 * {@link #tick(net.minecraftforge.event.TickEvent.WorldTickEvent)}
	 */
	public static void activateAssistEvents(World world) {
		InvasionType.getAvailableAssistEvents(world).forEach(type -> {
			if(world.getRandom().nextInt(type.getChance()) == 0) {
				activateEvent(world, type, false);
			}
		});
	}
	
	/**
	 * deactivate all invasion events and remove zombie spawn
	 */
	public static void deactivateZombieAttackEvents(World world, boolean isNatural) {
	    boolean flag = false;
	    PVZInvasionData data = PVZInvasionData.getOverWorldInvasionData(world);
	    for(IInvasionType ev : InvasionType.getInvasionEvents()) {// has invasion event
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
		for(IInvasionType ev : InvasionType.getInvasionEvents()) {
			if(data.hasEvent(ev)) {
				data.removeEvent(ev);
			}
		}
		//remove zombie spawns.
		for(IZombieType zombie : ZombieType.getZombies()) {
    		if(data.hasZombieSpawnEntry(zombie)) {
    			data.removeZombieSpawnEntry(zombie);
    		}
        }
		syncEndSpawnList(world);
	}
	
	/**
	 * get spawn zombies for current invasion event.
	 */
	public static List<IZombieType> getInvasionSpawnZombies(World world, IInvasionType ev, boolean force){
		if(force || ev.equals(InvasionEvents.RANDOM)) {
			return ev.getSpawnZombies(world);
		}
		final List<IZombieType> zombies = new ArrayList<>();
		for(IZombieType zombie : ev.getSpawnZombies(world)) {
			if(zombie.getDifficulty() <= InvasionCache.getInvasionDifficulty()) {
				zombies.add(zombie);
			}
		}
		return zombies;
	}
	
	/**
	 * randomly get a spawn invasion event.
	 */
	private static IInvasionType getSpawnEvent(World world) {
		WeightList<IInvasionType> list = new WeightList<>();
		InvasionType.getAvailableSpawnEvents(world).forEach(p -> {
			list.addItem(p.getFirst(), p.getSecond());
		});
		return list.getRandomItem(world.random).get();
	}
	
	/**
	 * use in Invasion Command.
	 */
	public static void addZombie(World world, IZombieType zombie) {
		PVZInvasionData data = PVZInvasionData.getOverWorldInvasionData(world);
		data.addZombieSpawnEntry(zombie);
		syncStartSpawnList(world);
	}
	
	/**
	 * use in Invasion Command.
	 */
	public static void removeZombie(World world, IZombieType zombie) {
		PVZInvasionData data = PVZInvasionData.getOverWorldInvasionData(world);
		if(data.hasZombieSpawnEntry(zombie)) {
			data.removeZombieSpawnEntry(zombie);
			syncStartSpawnList(world);
		}
	}
	
	/**
	 * {@link InvasionCache#syncStartInvasionCache(net.minecraft.world.server.ServerWorld)}
	 */
	public static void syncStartSpawnList(World world) {
		syncEndSpawnList(world);
		PVZInvasionData data = PVZInvasionData.getOverWorldInvasionData(world);
        for (IZombieType zombie : ZombieType.getZombies()) {
        	if(data.hasZombieSpawnEntry(zombie)) {
        		InvasionCache.ZOMBIE_INVADE_SET.add(zombie);
        	}
        }
	}

	/**
	 * {@link InvasionCache#syncEndInvasionCache(net.minecraft.world.server.ServerWorld)}
	 */
	public static void syncEndSpawnList(World world) {
		InvasionCache.ZOMBIE_INVADE_SET.clear();
	}
	
	/**
	 * is still in safe day.
	 */
	private static long difSafeDay(World world) {
		return world.getGameTime() - PVZConfig.COMMON_CONFIG.InvasionSettings.SafeDayLength.get() * 24000;
	}
	
	private static long getLeftSafeDay(long len) {
		return (- len + 23999) / 24000;
	}
	
}
