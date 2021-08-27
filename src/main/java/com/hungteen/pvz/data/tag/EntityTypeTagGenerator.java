package com.hungteen.pvz.data.tag;

import com.hungteen.pvz.PVZMod;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.EntityTypeTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class EntityTypeTagGenerator extends EntityTypeTagsProvider{

	public EntityTypeTagGenerator(DataGenerator generatorIn, ExistingFileHelper helper) {
		super(generatorIn, PVZMod.MOD_ID, helper);
	}
	
	@Override
	protected void addTags() {
		//mc tag
		
		//forge tag
		
		//pvz tag
	}
	
//	private EntityType<?>[] getFilterTypes(Predicate<EntityType<?>> predicate) {
//		return registry.stream()
//				.filter(predicate)
//				.sorted(Comparator.comparing(ForgeRegistries.ENTITIES::getKey))
//				.toArray(EntityType<?>[]::new);
//	}
	
	@Override
	public String getName() {
		return "Plants vs Zombies entity type tags";
	}

}
