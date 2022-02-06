package com.hungteen.pvz.data.tag;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.common.entity.EntityRegister;
import com.hungteen.pvz.common.entity.PVZEntityClassifications;
import com.hungteen.pvz.common.misc.tag.PVZEntityTypeTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.EntityTypeTagsProvider;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Comparator;
import java.util.function.Predicate;

public class EntityTypeTagGenerator extends EntityTypeTagsProvider{

	public EntityTypeTagGenerator(DataGenerator generatorIn, ExistingFileHelper helper) {
		super(generatorIn, PVZMod.MOD_ID, helper);
	}
	
	@Override
	protected void addTags() {
		/* mc tags */
		
		/* forge tags */

		//for plant group.
		this.tag(PVZEntityTypeTags.PVZ_PLANT_GROUP_ENTITIES).addTag(PVZEntityTypeTags.PVZ_PLANTS);

		//for zombie group.
		this.tag(PVZEntityTypeTags.PVZ_ZOMBIE_GROUP_ENTITIES).addTag(PVZEntityTypeTags.PVZ_ZOMBIES);

		//for other guardians group.
		this.tag(PVZEntityTypeTags.PVZ_OTHER_GUARDIANS).add(
				EntityType.CAT, EntityType.DOLPHIN, EntityType.FOX, EntityType.WOLF,
				EntityType.LLAMA, EntityType.VILLAGER, EntityType.WANDERING_TRADER,
				EntityType.SNOW_GOLEM, EntityType.IRON_GOLEM, EntityType.POLAR_BEAR,
				EntityType.PANDA
		);

		//for zombie group.
		this.tag(PVZEntityTypeTags.PVZ_OTHER_MONSTERS).add(getFilterTypes(type -> type.getCategory() == EntityClassification.MONSTER));

		this.tag(PVZEntityTypeTags.PVZ_NOT_MONSTERS).add(EntityType.PIG);
		this.tag(PVZEntityTypeTags.PVZ_NOT_GUARDIANS).add(EntityType.PIG);
		/* pvz tags */
		
		// for plant entities.
		this.tag(PVZEntityTypeTags.PVZ_PLANTS)
				.add(getFilterTypes(type -> type.getCategory() == PVZEntityClassifications.PVZ_PLANT))
				.add(EntityRegister.CRAZY_DAVE.get());

		// for zombie entities.
		this.tag(PVZEntityTypeTags.PVZ_ZOMBIES)
				.add(getFilterTypes(type -> type.getCategory() == PVZEntityClassifications.PVZ_ZOMBIE));

		this.tag(PVZEntityTypeTags.BUNGEE_SPAWNS)
				.add(EntityRegister.NORMAL_ZOMBIE.get())
				.add(EntityRegister.CONEHEAD_ZOMBIE.get())
				.add(EntityRegister.BUCKETHEAD_ZOMBIE.get())
				.add(EntityRegister.LADDER_ZOMBIE.get());

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
