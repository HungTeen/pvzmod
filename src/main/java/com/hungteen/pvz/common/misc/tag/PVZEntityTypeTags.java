package com.hungteen.pvz.common.misc.tag;

import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.entity.EntityType;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.ITag.INamedTag;
import net.minecraft.util.ResourceLocation;

public class PVZEntityTypeTags {

	//minecraft

	//forge
	public static final INamedTag<EntityType<?>> PVZ_PLANT_GROUP_ENTITIES = forgeTag("pvz_plant_group_entities");
	public static final INamedTag<EntityType<?>> PVZ_ZOMBIE_GROUP_ENTITIES = forgeTag("pvz_zombie_group_entities");
	public static final INamedTag<EntityType<?>> PVZ_OTHER_MONSTERS = forgeTag("pvz_other_monsters");
	public static final INamedTag<EntityType<?>> PVZ_OTHER_GUARDIANS = forgeTag("pvz_other_guardians");
	public static final INamedTag<EntityType<?>> PVZ_NOT_MONSTERS = forgeTag("pvz_not_monsters");
	public static final INamedTag<EntityType<?>> PVZ_NOT_GUARDIANS = forgeTag("pvz_not_guardians");

	//pvz
	public static final INamedTag<EntityType<?>> PVZ_PLANTS = pvzTag("pvz_plants");
	public static final INamedTag<EntityType<?>> PVZ_ZOMBIES = pvzTag("pvz_zombies");
	public static final INamedTag<EntityType<?>> BUNGEE_SPAWNS = pvzTag("bungee_spawns");
		
	private static INamedTag<EntityType<?>> pvzTag(String name){
		return EntityTypeTags.createOptional(StringUtil.prefix(name));
    }
	
	private static INamedTag<EntityType<?>> forgeTag(String name){
        return EntityTypeTags.createOptional(new ResourceLocation("forge", name));
    }
	
//	private static INamedTag<EntityType<?>> mcTag(String name){
//        return EntityTypeTags.createOptional(new ResourceLocation(name));
//    }
	
}
