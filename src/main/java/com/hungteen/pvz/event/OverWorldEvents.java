package com.hungteen.pvz.event;

import javax.annotation.Nonnull;

import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.register.EntitySpawnRegister;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.enums.Events;
import com.hungteen.pvz.world.data.WorldEventData;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;
import net.minecraftforge.event.TickEvent;


public class OverWorldEvents {

	public static void tick(TickEvent.WorldTickEvent ev) {
		World world = ev.world;
		long totalTime = world.getDayTime();
//		world.rand.setSeed(totalTime);
		if(world.getGameTime() < PVZConfig.COMMON_CONFIG.WorldSettings.WorldEventSettings.SafeDayLength.get() * 24000) {
			return ;
		}
		int time=(int) (totalTime % 24000);
		switch(time) {
		case 99:{
			WorldEventData data = WorldEventData.getOverWorldEventData(world);
			data.setChanged(false);
			break;
		}
		case 100:{
			WorldEventData data = WorldEventData.getOverWorldEventData(world);
			if(!data.getChanged()) {
				data.setChanged(true);
//				System.out.println(getZombieAttackChance());
				if(world.getDifficulty() != Difficulty.PEACEFUL && (data.getIsZomBossDefeated() || world.rand.nextInt(100) < getZombieAttackChance())) {//attack chance
					activateZombieAttackEvents(world);
				}
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
			if(!data.getChanged()) {
				data.setChanged(true);
				deactivateZombieAttackEvents(world);
			}
			break;
		}
		}
	}
	
	public static void activateZombieAttackEvents(World world) {
		for (PlayerEntity pl : world.getPlayers()) {
			pl.sendMessage(new TranslationTextComponent("event.pvz.zombie_attack").applyTextStyle(TextFormatting.DARK_RED));
			world.playSound(null, pl.getPosition(), SoundRegister.HUGE_WAVE.get(), SoundCategory.AMBIENT, 1f, 1f);
		}
		int type = world.rand.nextInt(Events.values().length);
		activateEvent(world, Events.values()[type]);
	}
	
	public static void activateEvent(World world, @Nonnull Events event) {
		WorldEventData data = WorldEventData.getOverWorldEventData(world);
		if(!data.hasEvent(event)) {
			data.addEvent(event);
			EntitySpawnRegister.addEventSpawns(event);
		}
	}
	
	public static void deactivateZombieAttackEvents(World world) {
		for(Events ev:Events.values()) {
			deactivateEvent(world, ev);
		}
	}
	
	public static void deactivateEvent(World world,@Nonnull Events event) {
		WorldEventData data = WorldEventData.getOverWorldEventData(world);
		if(data.hasEvent(event)) {
			data.removeEvent(event);
			EntitySpawnRegister.removeEventSpawns(event);
		}
	}
	
	public static int getZombieAttackChance() {
		return PVZConfig.COMMON_CONFIG.WorldSettings.WorldEventSettings.ZombieAttackChance.get();
	}
}
