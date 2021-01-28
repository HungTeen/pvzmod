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
	BUNGEE(true, Bundles.RANDOM, Zombies.BUNGEE_ZOMBIE);
	
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
		ITextComponent text = new TranslationTextComponent("event.pvz." + ev.toString().toLowerCase());
		switch (ev) {
		case BUCKET:{text.applyTextStyles(TextFormatting.GRAY); break;}
		case WATER:{text.applyTextStyles(TextFormatting.DARK_BLUE); break;}
		case HALLOWEEN:{text.applyTextStyles(TextFormatting.GOLD); break;}
		case NEWSPAPER:{text.applyTextStyles(TextFormatting.DARK_GRAY); break;}
		case FOOTBALL:{text.applyTextStyles(TextFormatting.BLACK); break;}
		case YETI:{text.applyTextStyles(TextFormatting.AQUA); break;}
		case FOG:{text.applyTextStyles(TextFormatting.GRAY); break;}
		case RANDOM:{text.applyTextStyles(TextFormatting.GREEN); break;}
		case BUNGEE:{text.applyTextStyles(TextFormatting.RED); break;}
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
