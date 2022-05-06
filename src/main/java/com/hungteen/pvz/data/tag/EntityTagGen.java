package com.hungteen.pvz.data.tag;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.common.entity.PVZEntities;
import com.hungteen.pvz.common.entity.PVZMobCategories;
import com.hungteen.pvz.common.tag.PVZEntityTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Comparator;
import java.util.function.Predicate;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-08 21:49
 **/
public class EntityTagGen extends EntityTypeTagsProvider {

    public EntityTagGen(DataGenerator generatorIn, ExistingFileHelper helper) {
        super(generatorIn, PVZMod.MOD_ID, helper);
    }

    @Override
    protected void addTags() {
        /* mc tags */

        /* forge tags */

        //for plant group.
        this.tag(PVZEntityTags.PVZ_PLANT_GROUP_ENTITIES).addTag(PVZEntityTags.PVZ_PLANTS);

        //for zombie group.
        this.tag(PVZEntityTags.PVZ_ZOMBIE_GROUP_ENTITIES).addTag(PVZEntityTags.PVZ_ZOMBIES);

        //for other guardians group.
        this.tag(PVZEntityTags.PVZ_OTHER_GUARDIANS).add(
                EntityType.CAT, EntityType.DOLPHIN, EntityType.FOX, EntityType.WOLF,
                EntityType.LLAMA, EntityType.VILLAGER, EntityType.WANDERING_TRADER,
                EntityType.SNOW_GOLEM, EntityType.IRON_GOLEM, EntityType.POLAR_BEAR,
                EntityType.PANDA
        );

        //for zombie group.
        this.tag(PVZEntityTags.PVZ_OTHER_MONSTERS).add(getFilterTypes(type -> type.getCategory() == MobCategory.MONSTER));

        this.tag(PVZEntityTags.PVZ_NOT_MONSTERS).add(EntityType.PIG);
        this.tag(PVZEntityTags.PVZ_NOT_GUARDIANS).add(EntityType.PIG);

        /*
        PVZ Tags.
         */

        // for plant entities.
        this.tag(PVZEntityTags.PVZ_PLANTS)
                .add(getFilterTypes(type -> type.getCategory() == PVZMobCategories.PVZ_PLANT));
//                .add(EntityRegister.CRAZY_DAVE.get());

        // for zombie entities.
        this.tag(PVZEntityTags.PVZ_ZOMBIES)
                .add(getFilterTypes(type -> type.getCategory() == PVZMobCategories.PVZ_ZOMBIE));

        this.tag(PVZEntityTags.IGNORE_ATTRACTS)
                .add(EntityType.WITHER, EntityType.ENDER_DRAGON);

        this.tag(PVZEntityTags.IGNORE_FROZEN)
                .add(EntityType.SNOW_GOLEM, PVZEntities.SNOW_PEA.get(), PVZEntities.ICEBERG_LETTUCE.get());

        this.tag(PVZEntityTags.IGNORE_COLD).addTag(PVZEntityTags.IGNORE_FROZEN);

        this.tag(PVZEntityTags.HAS_INVULNERABLE_TIME)
                .add(EntityType.WITHER, EntityType.ENDER_DRAGON);

//        this.tag(PVZEntityTags.BUNGEE_SPAWNS)
//                .add(EntityRegister.NORMAL_ZOMBIE.get())
//                .add(EntityRegister.CONEHEAD_ZOMBIE.get())
//                .add(EntityRegister.BUCKETHEAD_ZOMBIE.get())
//                .add(EntityRegister.LADDER_ZOMBIE.get());

    }

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