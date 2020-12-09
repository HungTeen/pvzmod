package com.hungteen.pvz.utils.enums;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;

public enum Events {

	BUCKET(true, Zombies.FLAG_ZOMBIE, Zombies.NORMAL_ZOMBIE, Zombies.CONEHEAD_ZOMBIE, Zombies.POLE_ZOMBIE, Zombies.BUCKETHEAD_ZOMBIE),
	WATER(true, Zombies.FLAG_ZOMBIE, Zombies.SNORKEL_ZOMBIE, Zombies.ZOMBONI, Zombies.BOBSLE_TEAM, Zombies.LAVA_ZOMBIE), 
	HALLOWEEN(true, Zombies.FLAG_ZOMBIE, Zombies.TRICK_ZOMBIE, Zombies.PUMPKIN_ZOMBIE),
	NEWSPAPER(true, Zombies.FLAG_ZOMBIE, Zombies.NEWSPAPER_ZOMBIE, Zombies.OLD_ZOMBIE, Zombies.SUNDAY_EDITION_ZOMBIE),
	FOOTBALL(true, Zombies.FLAG_ZOMBIE, Zombies.SCREENDOOR_ZOMBIE, Zombies.DANCING_ZOMBIE, Zombies.FOOTBALL_ZOMBIE, Zombies.GIGA_FOOTBALL_ZOMBIE),
	RANDOM(true),
	FOG(false);
	
	public boolean isZombieAttackEvent;
	public Zombies[] zombies;
	public static final List<Events> ATTACK_EVENTS = new ArrayList<>();
	
	static {
		for(Events ev : Events.values()) {
			if(ev.isZombieAttackEvent) {
				ATTACK_EVENTS.add(ev);
			}
		}
	}
	
	public static ITextComponent getEventText(Events ev){
		ITextComponent text = new TranslationTextComponent("event.pvz." + ev.toString().toLowerCase());
		switch (ev) {
		case BUCKET:{text.applyTextStyles(TextFormatting.GRAY); break;}
		case WATER:{text.applyTextStyles(TextFormatting.DARK_BLUE); break;}
		case HALLOWEEN:{text.applyTextStyles(TextFormatting.GOLD); break;}
		case NEWSPAPER:{text.applyTextStyles(TextFormatting.DARK_GRAY); break;}
		case FOOTBALL:{text.applyTextStyles(TextFormatting.BLACK); break;}
		case RANDOM:{text.applyTextStyles(TextFormatting.GREEN); break;}
		default:
			break;
		}
		return text;
	}
	
	private Events(boolean isAttackEvent, Zombies...zombies) {
		this.isZombieAttackEvent = isAttackEvent;
		this.zombies = zombies;
	}
	
}
