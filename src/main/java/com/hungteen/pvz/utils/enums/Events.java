package com.hungteen.pvz.utils.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;

public enum Events {

	BUCKET(true, Bundles.GRASS_DAY, Zombies.NORMAL_ZOMBIE, Zombies.CONEHEAD_ZOMBIE, Zombies.POLE_ZOMBIE, Zombies.BUCKETHEAD_ZOMBIE),
	WATER(true, Bundles.POOL_DAY, Zombies.NORMAL_ZOMBIE, Zombies.SNORKEL_ZOMBIE, Zombies.ZOMBONI, Zombies.BOBSLE_TEAM, Zombies.LAVA_ZOMBIE), 
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
	
	public static final List<Events> ATTACK_EVENTS = new ArrayList<>();
	public boolean isZombieAttackEvent;
	public final List<Zombies> zombies;
	public Optional<Bundles> bundle = Optional.empty();
	
	static {
		for(Events ev : Events.values()) {
			if(ev.isZombieAttackEvent) {
				ATTACK_EVENTS.add(ev);
			}
		}
	}
	
	public static ITextComponent getEventText(Events ev) {
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
	
	private Events(boolean isAttackEvent) {
		this.zombies = new ArrayList<>();
	}
	
	private Events(boolean isAttackEvent, Bundles bundle, Zombies...zombies) {
		this.isZombieAttackEvent = isAttackEvent;
		this.bundle = Optional.of(bundle);
		this.zombies = Arrays.asList(zombies);
	}
	
}
