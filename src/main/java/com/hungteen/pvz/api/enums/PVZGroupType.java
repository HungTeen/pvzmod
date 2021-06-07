package com.hungteen.pvz.api.enums;

import net.minecraft.entity.Entity;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.GolemEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.util.math.MathHelper;

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
	CREATURES,//neutral creatures.
	PLANTS,//plants.
	OTHER_GURADS;//iron golems, snow golems, wolves and so on.
			
	public static PVZGroupType getGroup(int type) {
		type = MathHelper.clamp(type, -2, 2);
		return PVZGroupType.values()[type + 2];
	}
	
	public static PVZGroupType oppsite(PVZGroupType type) {
		return PVZGroupType.values()[4 - type.ordinal()];
	}
	
	public static boolean isOtherGuards(Entity entity) {
		if(entity instanceof GolemEntity) return true;
		if(entity instanceof TameableEntity) return true;
		if(entity instanceof AbstractVillagerEntity) return true;
		return false;
	}
	
	public static boolean isOtherMonsters(Entity entity) {
		if(entity instanceof IMob) return true;
		return false;
	}
	
	public static boolean checkCanAttack(PVZGroupType g1, PVZGroupType g2) {
		return g1 != g2;
	}
	
	public static boolean checkCanTarget(PVZGroupType g1, PVZGroupType g2) {
		return (g1.ordinal() - 2) * (g2.ordinal() - 2) < 0;
	}
	
}
