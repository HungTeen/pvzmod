package com.hungteen.pvz.data.tag;

import java.util.Comparator;
import java.util.function.Predicate;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.api.PVZAPI;
import com.hungteen.pvz.common.misc.tag.PVZEntityTypeTags;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.EntityTypeTagsProvider;
import net.minecraft.entity.EntityType;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

public class EntityTypeTagGenerator extends EntityTypeTagsProvider{

	public EntityTypeTagGenerator(DataGenerator generatorIn, ExistingFileHelper helper) {
		super(generatorIn, PVZMod.MOD_ID, helper);
	}
	
	@Override
	protected void addTags() {
		/* mc tags */
		
		/* forge tags */
		
		/* pvz tags */
		
		// for plant entities.
		PVZAPI.get().getPlants().forEach(type -> {
			type.getEntityType().ifPresent(entityType -> {
				this.tag(PVZEntityTypeTags.PVZ_PLANTS).add(entityType);
			});
		});
		
		// for zombie entities.
		PVZAPI.get().getZombies().forEach(type -> {
			type.getEntityType().ifPresent(entityType -> {
				this.tag(PVZEntityTypeTags.PVZ_ZOMBIES).add(entityType);
			});
		});
	}
	
	@SuppressWarnings("unused")
	private EntityType<?>[] getFilterTypes(Predicate<EntityType<?>> predicate) {
		return registry.stream()
				.filter(predicate)
				.sorted(Comparator.comparing(ForgeRegistries.ENTITIES::getKey))
				.toArray(EntityType<?>[]::new);
	}
	
	@Override
	public String getName() {
		return "Plants vs Zombies entity type tags";
	}

}
