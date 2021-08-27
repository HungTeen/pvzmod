package com.hungteen.pvz.common.misc.tag;

import net.minecraft.entity.EntityType;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.ITag.INamedTag;
import net.minecraft.util.ResourceLocation;

public class PVZEntityTypeTags {

	//minecraft
	//forge
	public static final INamedTag<EntityType<?>> PVZ_PLANT_TARGETS = forgeTag("pvz_plant_targets");
	public static final INamedTag<EntityType<?>> PVZ_ZOMBIE_TARGET = forgeTag("pvz_zombie_targets");
	public static final INamedTag<EntityType<?>> NOT_PVZ_PLANT_TARGET = forgeTag("not_pvz_plant_targets");
	public static final INamedTag<EntityType<?>> NOT_PVZ_ZOMBIE_TARGET = forgeTag("not_pvz_zombie_targets");
	//pvz
	
		
//	private static INamedTag<EntityType<?>> pvzTag(String name){
//		return EntityTypeTags.createOptional(StringUtil.prefix(name));
//    }
	
	private static INamedTag<EntityType<?>> forgeTag(String name){
        return EntityTypeTags.createOptional(new ResourceLocation("forge", name));
    }
	
//	private static INamedTag<EntityType<?>> mcTag(String name){
//        return EntityTypeTags.createOptional(new ResourceLocation(name));
//    }
	
}
