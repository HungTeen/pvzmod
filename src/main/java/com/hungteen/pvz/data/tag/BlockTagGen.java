package com.hungteen.pvz.data.tag;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.api.PVZAPI;
import com.hungteen.pvz.common.tag.PVZBlockTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Comparator;
import java.util.function.Predicate;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-08 21:45
 **/
public class BlockTagGen extends BlockTagsProvider {

    public BlockTagGen(DataGenerator generatorIn, ExistingFileHelper helper) {
        super(generatorIn, PVZMod.MOD_ID, helper);
    }

    @Override
    protected void addTags() {
        PVZAPI.get().getEssences().forEach(e -> {
            //add to radiation block.
            e.getRadiationBlockTag().ifPresent(t -> {
                e.getRadiationBlock().ifPresent(b -> {
                    this.tag(t).add(b);
                });
            });
            //add to essence ore.
            this.tag(PVZBlockTags.ESSENCE_ORES).add(e.getEssenceOre());
        });
//        this.tag(PVZBlockTags.AMETHYST_ORES).add(BlockRegister.AMETHYST_ORE.get());
//        this.tag(BlockTags.CLIMBABLE).add(BlockRegister.STEEL_LADDER.get());
//        this.tag(PVZBlockTags.GOLD_TILES).add(BlockRegister.GOLD_TILE1.get(), BlockRegister.GOLD_TILE2.get(),
//                BlockRegister.GOLD_TILE3.get());
//        this.tag(PVZBlockTags.PLANT_SUIT_BLOCKS).addTag(PVZBlockTags.GOLD_TILES)
//                .add(Blocks.GRASS_BLOCK, BlockRegister.FLOWER_POT.get(), BlockRegister.LILY_PAD.get());
//        this.tag(BlockTags.LOGS).add(BlockRegister.NUT_LOG.get());
    }

    private Block[] getFilterBlocks(Predicate<Block> predicate) {
        return registry.stream()
                .filter(predicate)
                .sorted(Comparator.comparing(ForgeRegistries.BLOCKS::getKey))
                .toArray(Block[]::new);
    }

    @Override
    public String getName() {
        return "Plants vs Zombies block tags";
    }
}
