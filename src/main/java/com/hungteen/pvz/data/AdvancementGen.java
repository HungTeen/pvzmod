package com.hungteen.pvz.data;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.common.advancement.trigger.SummonCardUseTrigger;
import com.hungteen.pvz.common.block.PVZBlocks;
import com.hungteen.pvz.common.impl.type.plant.PVZPlants;
import com.hungteen.pvz.common.item.PVZItems;
import com.hungteen.pvz.utils.Util;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.advancements.AdvancementProvider;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.function.Consumer;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-29 12:31
 **/
public class AdvancementGen extends AdvancementProvider {

    public AdvancementGen(DataGenerator generatorIn, ExistingFileHelper fileHelperIn) {
        super(generatorIn, fileHelperIn);
    }

    @Override
    protected void registerAdvancements(Consumer<Advancement> consumer, ExistingFileHelper fileHelper) {
        Advancement root = Advancement.Builder.advancement()
                .display(Blocks.HAY_BLOCK, title("root"), desc("root"), Util.texture("block/amethyst_block.png"), FrameType.TASK, false, true, false)
                .addCriterion("join_in_world", LocationTrigger.TriggerInstance.located(LocationPredicate.ANY))
                .save(consumer, prefix("root"));

        Advancement grassCarp = Advancement.Builder.advancement()
                .parent(root)
                .addCriterion("grass_carp", FilledBucketTrigger.TriggerInstance.filledBucket(ItemPredicate.Builder.item().of(PVZItems.GRASS_CARP_BUCKET.get()).build()))
                .display(PVZItems.GRASS_CARP_BUCKET.get(), title("grass_carp"), desc("grass_carp"), null, FrameType.TASK, true, true, false)
                .save(consumer, prefix("grass_carp"));

        Advancement essence = Advancement.Builder.advancement()
                .parent(root)
                .addCriterion("get_essence", InventoryChangeTrigger.TriggerInstance.hasItems(PVZItems.ORIGIN_ESSENCE.get()))
                .display(PVZItems.ORIGIN_ESSENCE.get(), title("essence"), desc("essence"), null, FrameType.TASK, true, true, false)
                .save(consumer, prefix("essence"));

        Advancement origin = Advancement.Builder.advancement()
                .parent(essence)
                .addCriterion("place_origin", PlacedBlockTrigger.TriggerInstance.placedBlock(PVZBlocks.ORIGIN_BLOCK.get()))
                .display(PVZItems.ORIGIN_ESSENCE.get(), title("origin"), desc("origin"), null, FrameType.TASK, true, true, false)
                .save(consumer, prefix("origin"));

        Advancement tasteBrain = Advancement.Builder.advancement()
                .parent(root)
                .addCriterion("taste_brain", ConsumeItemTrigger.TriggerInstance.usedItem(PVZItems.FAKE_BRAIN.get()))
                .display(PVZItems.FAKE_BRAIN.get(), title("taste_brain"), desc("taste_brain"), null, FrameType.TASK, true, true, false)
                .save(consumer, prefix("taste_brain"));

        Advancement firstSummon = Advancement.Builder.advancement()
                .parent(origin)
                .addCriterion("first_summon", SummonCardUseTrigger.Instance.usedCard())
                .display(PVZPlants.SUN_FLOWER.getSummonCard().get(), title("first_summon"), desc("first_summon"), null, FrameType.TASK, true, true, false)
                .save(consumer, prefix("first_summon"));

    }

    protected static String prefix(String name){
        return Util.prefix(name).toString();
    }

    protected static TranslatableComponent title(String name){
        return new TranslatableComponent("advancements.pvz." + name + ".title");
    }

    protected static TranslatableComponent desc(String name){
        return new TranslatableComponent("advancements.pvz." + name + ".desc");
    }

    @Override
    public String getName() {
        return "Plants vs Zombies advancements";
    }
}
