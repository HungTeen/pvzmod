package com.hungteen.pvz.api.enums;

/**
 * Use to specify creatures' group.
 * Plants will attack zombies and other monsters firstly.
 * If there is no target for plants to attack and they are attacked by other guards or creatures, they will attack back too.
 * Zombies will attack plants and other guards firstly.
 * If there is no target for zombies to attack and they are attacked by other monsters or creatures, they will attack back too.
 * By the way, entities in plant group will never attack each other, zombie group too.
 */
public enum PVZGroupType {

	OTHER_MONSTERS,//other monsters except pvz zombies.
	ZOMBIES,//zombies.
	NEUTRALS,//neutral creatures.
	PLANTS,//plants.
	OTHER_GUARDIANS;//iron golems, snow golems, wolves and so on.

//	public static PVZGroupType oppsite(PVZGroupType type) {
//		return PVZGroupType.values()[4 - type.ordinal()];
//	}
	
}
