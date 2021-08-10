package com.hungteen.pvz.utils.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.common.world.data.PVZFlagData;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public enum InvasionEvents {

	BUCKET(true, Bundles.GRASS_DAY, Zombies.NORMAL_ZOMBIE, Zombies.CONEHEAD_ZOMBIE, Zombies.POLE_ZOMBIE, Zombies.BUCKETHEAD_ZOMBIE),
	WATER(true, Bundles.POOL_DAY, Zombies.NORMAL_ZOMBIE, Zombies.SNORKEL_ZOMBIE, Zombies.ZOMBONI, Zombies.BOBSLE_TEAM, Zombies.DOLPHIN_RIDER, Zombies.LAVA_ZOMBIE), 
	HALLOWEEN(true, Bundles.RANDOM, Zombies.NORMAL_ZOMBIE, Zombies.TRICK_ZOMBIE, Zombies.PUMPKIN_ZOMBIE),
	NEWSPAPER(true, Bundles.GRASS_NIGHT, Zombies.NORMAL_ZOMBIE, Zombies.NEWSPAPER_ZOMBIE, Zombies.OLD_ZOMBIE, Zombies.SUNDAY_EDITION_ZOMBIE),
	FOOTBALL(true, Bundles.GRASS_NIGHT, Zombies.NORMAL_ZOMBIE, Zombies.SCREENDOOR_ZOMBIE, Zombies.DANCING_ZOMBIE, Zombies.FOOTBALL_ZOMBIE, Zombies.GIGA_FOOTBALL_ZOMBIE),
	RANDOM(true, Bundles.RANDOM),
	FOG(false),
	YETI(true, Bundles.POOL_NIGHT, Zombies.NORMAL_ZOMBIE, Zombies.JACK_IN_BOX_ZOMBIE, Zombies.BALLOON_ZOMBIE, Zombies.DIGGER_ZOMBIE, Zombies.POGO_ZOMBIE),
	BUNGEE(true, Bundles.ROOF, Zombies.BUNGEE_ZOMBIE),
	METAL(true, Bundles.UPGRADE, Zombies.BUCKETHEAD_ZOMBIE, Zombies.SCREENDOOR_ZOMBIE, Zombies.FOOTBALL_ZOMBIE, Zombies.GIGA_FOOTBALL_ZOMBIE, Zombies.JACK_IN_BOX_ZOMBIE, Zombies.POGO_ZOMBIE, Zombies.DIGGER_ZOMBIE, Zombies.LADDER_ZOMBIE),
	ROOF(true, Bundles.ROOF, Zombies.NORMAL_ZOMBIE, Zombies.BUNGEE_ZOMBIE, Zombies.LADDER_ZOMBIE, Zombies.CATAPULT_ZOMBIE, Zombies.GARGANTUAR),
	GIANT(true, Bundles.UPGRADE, Zombies.GARGANTUAR, Zombies.SAD_GARGANTUAR),
	MINI(false),
	INVIS(false),
	ZOMBOTANY(true, Bundles.RANDOM, Zombies.PEASHOOTER_ZOMBIE, Zombies.WALLNUT_ZOMBIE, Zombies.GATLINGPEA_ZOMBIE, Zombies.TALLNUT_ZOMBIE, Zombies.SQUASH_ZOMBIE, Zombies.JALAPENO_ZOMBIE);
	
	public static final List<InvasionEvents> ATTACK_EVENTS = new ArrayList<>();
	public static final EnumMap<InvasionEvents, Integer> EVENT_CHANCE = new EnumMap<>(InvasionEvents.class);
	public boolean isZombieAttackEvent;
	public final List<Zombies> zombies;
	public Optional<Bundles> bundle = Optional.empty();
	
	static {
		//get event chance from config file
		EVENT_CHANCE.put(InvasionEvents.BUCKET, PVZConfig.COMMON_CONFIG.WorldSettings.WorldInvasionSettings.EventChanceSettings.BucketAttackChance.get());
		EVENT_CHANCE.put(InvasionEvents.WATER, PVZConfig.COMMON_CONFIG.WorldSettings.WorldInvasionSettings.EventChanceSettings.WaterAttackChance.get());
		EVENT_CHANCE.put(InvasionEvents.HALLOWEEN, PVZConfig.COMMON_CONFIG.WorldSettings.WorldInvasionSettings.EventChanceSettings.HalloweenAttackChance.get());
		EVENT_CHANCE.put(InvasionEvents.NEWSPAPER, PVZConfig.COMMON_CONFIG.WorldSettings.WorldInvasionSettings.EventChanceSettings.NewspaperAttackChance.get());
		EVENT_CHANCE.put(InvasionEvents.FOOTBALL, PVZConfig.COMMON_CONFIG.WorldSettings.WorldInvasionSettings.EventChanceSettings.FootballAttackChance.get());
		EVENT_CHANCE.put(InvasionEvents.RANDOM, PVZConfig.COMMON_CONFIG.WorldSettings.WorldInvasionSettings.EventChanceSettings.RandomAttackChance.get());
		EVENT_CHANCE.put(InvasionEvents.YETI, PVZConfig.COMMON_CONFIG.WorldSettings.WorldInvasionSettings.EventChanceSettings.YetiAttackChance.get());
		EVENT_CHANCE.put(InvasionEvents.BUNGEE, PVZConfig.COMMON_CONFIG.WorldSettings.WorldInvasionSettings.EventChanceSettings.BungeeAttackChance.get());
		EVENT_CHANCE.put(InvasionEvents.METAL, PVZConfig.COMMON_CONFIG.WorldSettings.WorldInvasionSettings.EventChanceSettings.MetalAttackChance.get());
		EVENT_CHANCE.put(InvasionEvents.ROOF, PVZConfig.COMMON_CONFIG.WorldSettings.WorldInvasionSettings.EventChanceSettings.RoofAttackChance.get());
		EVENT_CHANCE.put(InvasionEvents.GIANT, PVZConfig.COMMON_CONFIG.WorldSettings.WorldInvasionSettings.EventChanceSettings.GiantAttackChance.get());
		EVENT_CHANCE.put(InvasionEvents.ZOMBOTANY, PVZConfig.COMMON_CONFIG.WorldSettings.WorldInvasionSettings.EventChanceSettings.ZombotanyAttackChance.get());
		//get all invasion event that will spawn entities.
		for(InvasionEvents ev : InvasionEvents.values()) {
			if(ev.isZombieAttackEvent) {
				ATTACK_EVENTS.add(ev);
			}
		}
	}
	
	public static ITextComponent getEventText(InvasionEvents ev) {
		TranslationTextComponent text = new TranslationTextComponent("event.pvz." + ev.toString().toLowerCase());
		switch (ev) {
		case BUCKET:{text.withStyle(TextFormatting.GRAY); break;}
		case WATER:{text.withStyle(TextFormatting.DARK_BLUE); break;}
		case HALLOWEEN:{text.withStyle(TextFormatting.GOLD); break;}
		case NEWSPAPER:{text.withStyle(TextFormatting.DARK_GRAY); break;}
		case FOOTBALL:{text.withStyle(TextFormatting.BLACK); break;}
		case YETI:{text.withStyle(TextFormatting.AQUA); break;}
		case FOG:{text.withStyle(TextFormatting.GRAY); break;}
		case RANDOM:{text.withStyle(TextFormatting.GREEN); break;}
		case BUNGEE:{text.withStyle(TextFormatting.RED); break;}
		case METAL:{text.withStyle(TextFormatting.BLACK); break;}
		case ROOF:{text.withStyle(TextFormatting.GOLD); break;}
		case GIANT:{text.withStyle(TextFormatting.DARK_RED); break;}
		case MINI:{text.withStyle(TextFormatting.DARK_AQUA); break;}
		case INVIS:{text.withStyle(TextFormatting.DARK_GRAY); break;}
		case ZOMBOTANY:{text.withStyle(TextFormatting.DARK_GREEN); break;}
		default:
			break;
		}
		return text;
	}
	
	public static List<InvasionEvents> getAvailableSpawnEvents(World world) {
		return getAvailableInvasionEvents(world, ev -> ev.isZombieAttackEvent);
	}
	
	private static List<InvasionEvents> getAvailableInvasionEvents(World world, Predicate<InvasionEvents> pre) {
		List<InvasionEvents> list = new ArrayList<>();
		for(InvasionEvents ev : InvasionEvents.values()) {
			if(isAvailableInvasionEvent(world, ev) && pre.test(ev)) {
				list.add(ev);
			}
		}
		return list;
	}
	
	public static boolean isAvailableInvasionEvent(World world, InvasionEvents ev) {
		PVZFlagData data = PVZFlagData.getGlobalFlagData(world);
		switch (ev) {
		case RANDOM: 
		case BUNGEE:
		case METAL:
		case GIANT:
		case ZOMBOTANY:
		case MINI:
		case INVIS:return data.isZombossDefeated();
		default: return true;
		}
	}
	
	private InvasionEvents(boolean isAttackEvent) {
		this.zombies = new ArrayList<>();
	}
	
	private InvasionEvents(boolean isAttackEvent, Bundles bundle, Zombies...zombies) {
		this.isZombieAttackEvent = isAttackEvent;
		this.bundle = Optional.of(bundle);
		this.zombies = Arrays.asList(zombies);
	}
	
}
