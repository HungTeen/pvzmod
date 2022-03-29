package com.hungteen.pvz.data.tag;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.api.PVZAPI;
import com.hungteen.pvz.common.block.PVZBlocks;
import com.hungteen.pvz.common.block.cubes.EssenceOreBlock;
import com.hungteen.pvz.common.tag.PVZBlockTags;
import com.hungteen.pvz.utils.BlockUtil;
import com.hungteen.pvz.utils.Util;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.OreBlock;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeBlockTagsProvider;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.Arrays;
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
        /* mine with pickaxe */
        //add ores.
        BlockUtil.getFilterBlocks(block -> Util.inPVZ(block.getRegistryName()) && block instanceof OreBlock).forEach(b -> {
            this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(b);
        });
        Arrays.asList(
                PVZBlocks.AMETHYST_BLOCK, PVZBlocks.ORIGIN_BLOCK
        ).forEach(b -> {
            this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(b.get());
        });

        /* mine with axe */
        this.tag(BlockTags.MINEABLE_WITH_AXE);

        /* mine with shovel */
        this.tag(BlockTags.MINEABLE_WITH_SHOVEL);

        /* mine with hoe */
        this.tag(BlockTags.MINEABLE_WITH_HOE);

        /* 0 : wood level */
        this.tag(Tags.Blocks.NEEDS_WOOD_TOOL);

        /* 0+ : gold level */
        this.tag(Tags.Blocks.NEEDS_GOLD_TOOL);

        /* 1 : stone level */
        this.tag(BlockTags.NEEDS_STONE_TOOL);

        /* 2 : iron level */
        //essence ores need iron level.
        BlockUtil.getFilterBlocks(block -> Util.inPVZ(block.getRegistryName()) && block instanceof EssenceOreBlock).forEach(b -> {
            this.tag(BlockTags.NEEDS_IRON_TOOL).add(b);
        });
        Arrays.asList(
                PVZBlocks.ORIGIN_BLOCK
        ).forEach(b -> {
            this.tag(BlockTags.NEEDS_IRON_TOOL).add(b.get());
        });

        /* 3 : diamond level */
        Arrays.asList(
                PVZBlocks.AMETHYST_ORE, PVZBlocks.AMETHYST_BLOCK
        ).forEach(b -> {
            this.tag(BlockTags.NEEDS_DIAMOND_TOOL).add(b.get());
        });

        /* 4 : netherite level */
        this.tag(Tags.Blocks.NEEDS_NETHERITE_TOOL);

        /* common tags */
        BlockUtil.getFilterBlocks(b -> b instanceof EssenceOreBlock).forEach(block -> {
            this.tag(PVZBlockTags.ESSENCE_ORES).add(block);
        });
//        this.tag(PVZBlockTags.AMETHYST_ORES).add(BlockRegister.AMETHYST_ORE.get());
//        this.tag(BlockTags.CLIMBABLE).add(BlockRegister.STEEL_LADDER.get());
//        this.tag(PVZBlockTags.GOLD_TILES).add(BlockRegister.GOLD_TILE1.get(), BlockRegister.GOLD_TILE2.get(),
//                BlockRegister.GOLD_TILE3.get());
        //grass carp can change it into grass block.
        this.tag(PVZBlockTags.DIRT_NO_GRASS).add(Blocks.DIRT, Blocks.COARSE_DIRT, Blocks.ROOTED_DIRT, Blocks.PODZOL);
        this.tag(PVZBlockTags.PLANT_SUIT_BLOCKS)
//        .addTag(PVZBlockTags.GOLD_TILES)
                .add(Blocks.GRASS_BLOCK)
                .add(Blocks.MOSS_BLOCK)
                ;
//                        BlockRegister.FLOWER_POT.get(), BlockRegister.LILY_PAD.get());
//        this.tag(BlockTags.LOGS).add(BlockRegister.NUT_LOG.get());
    }

    @Override
    public String getName() {
        return "Plants vs Zombies block tags";
    }
}
